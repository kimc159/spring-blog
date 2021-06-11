package com.project.chat.main.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.chat.login.LoginVO;
import com.project.chat.member.MemberVO;

public interface MemberServiceImpl {
	public int join(MemberVO member);
	public int login(LoginVO loginVO, HttpServletResponse response, HttpSession session);
	public int updateAuthKey(Map<String, String> map);
	public String getSaltById(String memId);
	public String findId(String email);
	public String findPassword(String id, String email);
	public int idCheck(String id);
	public int modify(MemberVO member);
	public MemberVO selectMember(String memberId);
	public int joinConfirm(Map<String, Object> map);
	public int memberAuthStatus(LoginVO loginVO);
}
