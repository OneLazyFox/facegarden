package com.facegarden.modules.diancan.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.facegarden.common.config.Global;
import com.facegarden.common.persistence.Page;
import com.facegarden.common.utils.StringUtils;
import com.facegarden.common.web.BaseController;
import com.facegarden.modules.diancan.entity.WechatMsg;
import com.facegarden.modules.diancan.service.WechatMsgService;

@Controller
@RequestMapping(value = "${adminPath}/diancan/msg")
public class WechatMsgController extends BaseController {

	@Autowired
	private WechatMsgService wechatMsgService;
	
	@ModelAttribute
	public WechatMsg get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return wechatMsgService.get(id);
		}else{
			return new WechatMsg();
		}
	}

	@RequestMapping(value = "form")
	public String form(WechatMsg wechatMsg, Model model) {
		model.addAttribute("diancan", wechatMsg);
		return "modules/diancan/wechatMsgForm";
	}

	
	@RequestMapping(value = { "list", "" })
	public String list(WechatMsg wechatMsg, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WechatMsg> page = wechatMsgService.findPage(new Page<WechatMsg>(request, response), wechatMsg); 
        model.addAttribute("page", page);
		return "modules/diancan/wechatMsgList";
	}

	@RequestMapping(value = "delete")
	
	public String delete(WechatMsg wechatMsg, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/diancan/msg/";
		}
		wechatMsgService.delete(wechatMsg);
		addMessage(redirectAttributes, "删除用户账号成功");
		return "redirect:" + adminPath + "/diancan/msg/";
	}

	@RequestMapping(value = "save") // @Valid
	public String save(WechatMsg wechatMsg, Model model, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/diancan/msg/";
		}
		if (!beanValidator(model, wechatMsg)) {
			return form(wechatMsg, model);
		}
		wechatMsgService.save(wechatMsg);
		addMessage(redirectAttributes, "保存用户账号'" + wechatMsg.getAccount() + "'成功");
		return "redirect:" + adminPath + "/diancan/msg/";
	}
	
}       