package com.facegarden.modules.diancan.entity;

import java.util.Date;

import com.facegarden.common.persistence.DataEntity;

public class MealList extends DataEntity<MealList> {
	
	private static final long serialVersionUID = 1L;
	
	private  String listName;
	private  Integer mealNo;
	private  String showDate;    
	private Date beginDate;		// 开始日期
	private Date endDate;		// 结束日期
	
	public MealList() {
		super();
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public Integer getMealNo() {
		return mealNo;
	}

	public void setMealNo(Integer mealNo) {
		this.mealNo = mealNo;
	}
	
	public String getShowDate() {
		return showDate;
	}

	public void setShowDate(String showDate) {                                                   
		this.showDate = showDate;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}