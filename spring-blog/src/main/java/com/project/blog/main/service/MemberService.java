package com.project.blog.main.service;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blog.login.LoginVO;
import com.project.blog.main.dao.MemberDao;
import com.project.blog.member.MemberVO;
import com.project.blog.util.SHA256Util;

@Service
public class MemberService implements MemberServiceImpl{

	@Autowired
	private MemberDao memberDao;
	
	public int join(MemberVO member) {
		return memberDao.join(member);
	}
	public int updateAuthKey(Map map) {
		return memberDao.updateAuthKey(map);
	}
	
	public int login(LoginVO loginVO, HttpServletResponse response, HttpSession session) {
		
		MemberVO mem = memberDao.login(loginVO.getMemId());
		
		if(mem != null) {
			// SHA256
			String salt = getSaltById(loginVO.getMemId());
			loginVO.setMemPassword(SHA256Util.getEncrypt(loginVO.getMemPassword(), salt));
			
			// 사용자 있음
			if(loginVO.getMemPassword().equals(mem.getMemPassword())) {
				
				// 아이디 저장을 위한 쿠키 값 생성
				Cookie cookie = new Cookie("user_checked", mem.getMemId());
				
				if(loginVO.getRememberId().equals("true")) {
					cookie.setMaxAge(60 * 60 * 24);
				} else {
					cookie.setMaxAge(0);
				}
				cookie.setPath("/");
				response.addCookie(cookie);
				
				// 세션 생성 및 유지시한
				session.setAttribute("login", loginVO);
				session.setMaxInactiveInterval(60 * 60);
				 
				return 1;
			} else {
				// 비밀번호 틀림
				return 0;
			}
		} else {
			// 사용자 없음
			return -1;
		}
		
		
	}
	public String getSaltById(String memId) {
		return memberDao.getSaltById(memId);
	}
}
