package com.facegarden.modules.diancan.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.facegarden.common.config.Global;
import com.facegarden.common.persistence.Page;
import com.facegarden.common.web.BaseController;
import com.facegarden.modules.diancan.entity.MealOrder;
import com.facegarden.modules.diancan.service.MealOrderService;

@Controller
@RequestMapping(value = "${adminPath}/diancan/order")
public class MealOrderController extends BaseController {

	@Autowired
	private MealOrderService mealOrderService;
	
	@ModelAttribute
	public MealOrder get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return mealOrderService.get(id);
		}else{
			return new MealOrder();
		}
	}

	@RequestMapping(value = "form")
	public String form(MealOrder mealOrder, Model model) {
		model.addAttribute("diancan", mealOrder);
		return "modules/diancan/mealOrderForm";
	}

	
	@RequestMapping(value = { "list", "" })
	public String list(MealOrder mealOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MealOrder> page = mealOrderService.findPage(new Page<MealOrder>(request, response), mealOrder); 
        model.addAttribute("page", page);
		return "modules/diancan/mealOrderList";
	}

	@RequestMapping(value = "delete")
	
	public String delete(MealOrder mealOrder, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/diancan/order/";
		}
		mealOrderService.delete(mealOrder);
		addMessage(redirectAttributes, "删除订单用户成功");
		return "redirect:" + adminPath + "/diancan/order/";
	}

	@RequestMapping(value = "save") // @Valid
	public String save(MealOrder mealOrder, Model model, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/diancan/order/";
		}
		if (!beanValidator(model, mealOrder)) {
			return form(mealOrder, model);
		}
		mealOrderService.save(mealOrder);
		addMessage(redirectAttributes, "保存订单用户'" + mealOrder.getId() + "'成功");
		return "redirect:" + adminPath + "/diancan/order/";
	}

}