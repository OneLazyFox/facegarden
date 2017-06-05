package com.facegarden.modules.diancan.entity;

import com.facegarden.common.persistence.DataEntity;

public class WechatMsg extends DataEntity<WechatMsg> {

	private static final long serialVersionUID = 1L;
	
	private Integer account;
	private String content;
	
	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}