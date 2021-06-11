package com.project.chat.main.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.chat.config.SessionConfig;
import com.project.chat.login.LoginVO;
import com.project.chat.main.dao.MemberDao;
import com.project.chat.member.MemberVO;
import com.project.chat.util.SHA256Util;

@Service
public class MemberService implements MemberServiceImpl{

	@Autowired
	private MemberDao memberDao;
	
	public int join(MemberVO member) {
		return memberDao.join(member);
	}
	public int updateAuthKey(Map<String, String> map) {
		return memberDao.updateAuthKey(map);
	}
	
	public int login(LoginVO loginVO, HttpServletResponse response, HttpSession session) {
		
		LoginVO mem = memberDao.login(loginVO.getMemId());
		
		if(mem != null) {
			// SHA256 암호화
			String salt = getSaltById(loginVO.getMemId());
			loginVO.setMemPassword(SHA256Util.getEncrypt(loginVO.getMemPassword(), salt));
			
			// 로그인 성공, 비밀번호 일치 여부
			if(loginVO.getMemPassword().equals(mem.getMemPassword())) {
				
				// 아이디 중복 체크
				SessionConfig.getSessionidCheck("user_id", loginVO.getMemId().toString());

				Cookie cookie = new Cookie("rememberID", loginVO.getMemId());
				if(loginVO.getRememberId()) {
					cookie.setMaxAge(60*60*24*30);
				} else {
					cookie.setMaxAge(0);
				} 
				cookie.setPath("/");
				response.addCookie(cookie); 
				
				// 사용자 세션 설정				
				session.setAttribute("user_id", loginVO.getMemId());
				session.setAttribute("login", loginVO);
				session.setMaxInactiveInterval(30*60);  
				  
				return 1; 
			} else { 
				// 비밀번호 일치하지 않을 경우
				return 0;
			}
		} else {
			// 아이디 없을 경우
			return -1;
		}
		
		
	}
	public String getSaltById(String memId) {
		return memberDao.getSaltById(memId);
	}
	public String findId(String email) {
		return memberDao.findId(email);
	}
	public String findPassword(String memId, String memEmail) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memId", memId);
		map.put("memEmail", memEmail);
		
		int result = memberDao.findPassword(map);
		
		if(result == 1) {
			StringBuffer ranNum = new StringBuffer();
			
			Random ran = new Random();
			for(int i=0; i<6; i++) {
				ranNum.append(ran.nextInt(10));
			}
			
			String salt = SHA256Util.generateSalt();
			MemberVO member = new MemberVO();
			member.setSalt(salt); 
			
			String password = ranNum.toString();
			password = SHA256Util.getEncrypt(password, salt);
			
			map.put("memPassword", password);
			map.put("salt", salt);
			
			memberDao.updatePassword(map);
			
			return ranNum.toString();
		} else {
			return "-1";
		}
		
	}
	
	public int idCheck(String id) {
		return memberDao.idCheck(id);
	}
	public int modify(MemberVO member) {
		// TODO Auto-generated method stub
		return memberDao.modify(member);
	}
	public MemberVO selectMember(String memberId) {
		// TODO Auto-generated method stub
		return memberDao.selectMember(memberId);
	}
	public int joinConfirm(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return memberDao.joinConfirm(map);
	}
	public int memberAuthStatus(LoginVO loginVO) {
		// TODO Auto-generated method stub
		return memberDao.memberAuthStatus(loginVO);
	}
}
