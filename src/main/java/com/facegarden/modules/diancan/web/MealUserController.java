package com.facegarden.modules.diancan.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.facegarden.modules.diancan.entity.MealUser;
import com.facegarden.modules.diancan.service.MealUserService;

@Controller
@RequestMapping(value = "${adminPath}/diancan/mealUserinfo")
public class MealUserController extends BaseController{
	
	@Autowired
	private MealUserService mealUserService;
	
	@ModelAttribute
	public MealUser get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return mealUserService.get(id);
		}else{
			return new MealUser();
		}
	}
	
	/**
	 * 用户页面
	 */
	@RequestMapping(value={"info",""})
	public String info(MealUser mealUser,HttpServletRequest request,HttpServletResponse response,Model model){
		Page<MealUser> page = mealUserService.findPage(new Page<MealUser>(request, response), mealUser); 
	    model.addAttribute("page", page);
		return "modules/sys/mealUserlnfo";
	}
	
	/**
	 * 增加页面
	 */
	@RequestMapping(value = "form")
	@RequiresPermissions("diancan:view")
	public String form(MealUser mealUser, Model model) {
		model.addAttribute("mealUser", mealUser);
		return "modules/sys/mealUserForm";
	}
	
	/**
	 * 修改方法
	 */
	@RequestMapping(value = "save")
	public String save(MealUser mealUser, Model model, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/diancan/mealUserinfo/";
		}
		if (!beanValidator(model, mealUser)){
			return form(mealUser, model);
		}
		mealUserService.save(mealUser);
		addMessage(redirectAttributes, "保存用户成功");
		return "redirect:" + adminPath + "/diancan/mealUserinfo/";
	}
	
	/**
	 * 删除方法
	 */
	@RequestMapping(value = "delete")
	public String delete(MealUser mealUser, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/diancan/mealUserinfo/";
		}
		mealUserService.delete(mealUser);
		addMessage(redirectAttributes, "删除用户成功");
		return "redirect:" + adminPath + "/diancan/mealUserinfo/";
	}
	
}