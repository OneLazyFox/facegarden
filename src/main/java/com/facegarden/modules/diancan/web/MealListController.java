package com.facegarden.modules.diancan.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.facegarden.common.config.Global;
import com.facegarden.common.persistence.Page;
import com.facegarden.common.web.BaseController;
import com.facegarden.modules.diancan.entity.MealList;
import com.facegarden.modules.diancan.service.MealListService;

@Controller
@RequestMapping(value = "${adminPath}/diancan/mealList")
public class MealListController extends BaseController {
	
	@Autowired
	private MealListService mealListService;
	
	@RequestMapping(value = {"info",""})
	public String info(MealList mealList,HttpServletRequest request,HttpServletResponse response, Model model){
		Page<MealList> page = mealListService.findPage(new Page<MealList>(request, response), mealList); 
	    model.addAttribute("page", page);
		return "modules/sys/mealListList";
	}
	
  /*@RequiresPermissions("diancan:mealList:edit")*/
	@RequestMapping(value = "delete")
	public String delete(MealList mealList, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/diancan/mealList/";
		}
		mealListService.delete(mealList);
		addMessage(redirectAttributes, "删除菜单组合成功");
		return "redirect:" + adminPath + "/diancan/mealList/";
	}
	
	@RequestMapping(value = "add")
	public void add(String context){
		context="今天中午吃：海菜、海鱼、鸡肉、火腿、蔬菜";
		mealListService.split(context);
	}
	
}