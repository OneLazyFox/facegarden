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
import com.facegarden.modules.diancan.entity.MealInfo;
import com.facegarden.modules.diancan.service.MealInfService;

@Controller
@RequestMapping(value = "${adminPath}/diancan/MealInfo")
public class MealInfController extends BaseController {
	
	@Autowired
	private MealInfService mealInfService;
	
	@ModelAttribute
	public MealInfo get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return mealInfService.get(id);
		}else{
			return new MealInfo();
		}
	}
	
	/**
	 * 增加页面
	 */
	@RequestMapping(value = "form")
	@RequiresPermissions("diancan:view")
	public String form(MealInfo mealInfo, Model model) {
		model.addAttribute("mealInfo", mealInfo);
		return "modules/sys/MealInfoForm";
	}
	
	/**
	 * 用户页面
	 */
	@RequestMapping(value={"Info",""})
	public String info(MealInfo mealInfo,HttpServletRequest request,HttpServletResponse response,Model model){
		Page<MealInfo> page = mealInfService.findPage(new Page<MealInfo>(request, response), mealInfo); 
	    model.addAttribute("page", page);
		return "modules/sys/MealInfo";
	}
	
	/*@RequestMapping(value = {"Info", ""})
	public String list(MealInfo mealInfo, Model model) {
		model.addAttribute("list", mealInfService.findList(mealInfo));
		return "modules/sys/MealInfo";
	}*/
	
	/**
	 * 修改方法
	 */
	@RequestMapping(value = "save")
	public String save(MealInfo mealInfo, Model model, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/diancan/MealInfo/";
		}
		if (!beanValidator(model, mealInfo)){
			return form(mealInfo, model);
		}
		mealInfService.save(mealInfo);
		addMessage(redirectAttributes, "保存菜谱成功");
		return "redirect:" + adminPath + "/diancan/MealInfo/";
	}
	
	/**
	 * 删除方法
	 */
	@RequestMapping(value = "delete")
	public String delete(MealInfo mealInfo, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/diancan/MealInfo/";
		}
		mealInfService.delete(mealInfo);
		addMessage(redirectAttributes, "删除菜谱成功");
		return "redirect:" + adminPath + "/diancan/MealInfo/";
	}
	
}