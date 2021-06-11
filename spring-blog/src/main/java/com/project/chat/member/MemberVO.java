package com.project.chat.member;


public class MemberVO {
	private String memId;
	private String memPassword;
	private String memName;
	private String memGender;
	private String memBirthDay;
	private String memEmail;
	private int memPhone;
	private String authKey;
	private int authStatus;
	private String salt;
	
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
	public String getMemGender() {
		return memGender;
	}
	public void setMemGender(String memGender) {
		this.memGender = memGender;
	}
	public String getMemBirthDay() {
		return memBirthDay;
	}
	public void setMemBirthDay(String memBirthDay) {
		this.memBirthDay = memBirthDay;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public int getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(int memPhone) {
		this.memPhone = memPhone;
	}
	public String getAuthKey() {
		return authKey;
	}
	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}
	public int getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(int authStatus) {
		this.authStatus = authStatus;
	}
	
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memPassword=" + memPassword + ", memName=" + memName + ", memGender="
				+ memGender + ", memBirthDay=" + memBirthDay + ", memEmail=" + memEmail + ", memPhone=" + memPhone
				+ ", authKey=" + authKey + ", authStatus=" + authStatus + "]";
	}
}
