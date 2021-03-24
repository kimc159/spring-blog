package com.project.blog.main.dao;

import java.util.Map;

import com.project.blog.login.LoginVO;
import com.project.blog.member.MemberVO;

public interface MemberDaoImpl {
	public int join(MemberVO member);
	public LoginVO login(String memId);
	public int updateAuthKey(Map map);
	public String getSaltById(String memId);
	public String findId(String email);
	public int findPassword(Map<String, Object> map);
	public int updatePassword(Map<String, Object> map);
}
