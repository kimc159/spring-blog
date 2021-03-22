package com.project.blog.main.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.blog.login.LoginVO;
import com.project.blog.member.MemberVO;

public interface MemberServiceImpl {
	public int join(MemberVO member);
	public int login(LoginVO loginVO, HttpServletResponse response, HttpSession session);
	public int updateAuthKey(Map map);
	public String getSaltById(String memId);
}
