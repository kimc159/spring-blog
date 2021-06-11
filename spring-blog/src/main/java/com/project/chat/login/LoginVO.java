package com.project.chat.login;

public class LoginVO {
	String memId;
	String memPassword;
	String memName;
	String memEmail;
	boolean rememberId;
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPassword() {
		return memPassword;
	}
	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public boolean getRememberId() {
		return rememberId;
	}
	public void setRememberId(boolean rememberId) {
		this.rememberId = rememberId;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	
}
