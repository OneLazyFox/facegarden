package com.facegarden.modules.diancan.entity;

import com.facegarden.common.persistence.DataEntity;


public class MealOrder extends DataEntity<MealOrder> {

	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String mealNo;
	private String status;
	private String price;
	private String paymentStatus;
 
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMealNo() {
		return mealNo;
	}

	public void setMealNo(String mealNo) {
		this.mealNo = mealNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;

	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
}