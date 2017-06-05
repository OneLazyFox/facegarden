package com.facegarden.modules.diancan.entity;

import com.facegarden.common.persistence.DataEntity;

/**
 * 实体类
 */
public class MealUser  extends DataEntity<MealUser> {
	
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String account;
	private String type;
	
	public MealUser(){
		super();
	}
 
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}