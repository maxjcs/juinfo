package com.hlx.juinfo.common.vo;

import java.util.HashMap;
import java.util.Map;


public class EmailMessage {
	private String mailId;
	private String toAccount;
	private String title;
	private Map<String, Object> attributes = new HashMap<String, Object>();
	
	public String getMailId() {
		return mailId;
	}
	
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	
	public String getToAccount() {
		return toAccount;
	}
	
	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public boolean setAttribute(String key, Object value) {
		return this.attributes.put(key, value) == null;
	}
	
	public Map<String, Object> getAttribute() {
		return this.attributes;
	}
}
