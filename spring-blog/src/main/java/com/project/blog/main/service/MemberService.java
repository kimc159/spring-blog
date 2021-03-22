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
			
			// ����� ����
			if(loginVO.getMemPassword().equals(mem.getMemPassword())) {
				
				// ���̵� ������ ���� ��Ű �� ����
				Cookie cookie = new Cookie("user_checked", mem.getMemId());
				
				if(loginVO.getRememberId().equals("true")) {
					cookie.setMaxAge(60 * 60 * 24);
				} else {
					cookie.setMaxAge(0);
				}
				cookie.setPath("/");
				response.addCookie(cookie);
				
				// ���� ���� �� ��������
				session.setAttribute("login", loginVO);
				session.setMaxInactiveInterval(60 * 60);
				 
				return 1;
			} else {
				// ��й�ȣ Ʋ��
				return 0;
			}
		} else {
			// ����� ����
			return -1;
		}
		
		
	}
	public String getSaltById(String memId) {
		return memberDao.getSaltById(memId);
	}
}
