package com.facegarden.modules.diancan.entity;

import com.facegarden.common.persistence.DataEntity;

/**
 * 实体类
 */
public class MealInfo extends DataEntity<MealInfo> {
	
	private static final long serialVersionUID = 1L;
	
	private String mealName;
	private String mealType;
	private String showDate;
	
	public MealInfo() {
		super();
	}

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public String getShowDate() {
		return showDate;
	}

	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}
	
}