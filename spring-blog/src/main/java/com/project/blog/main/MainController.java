package com.project.blog.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.blog.login.LoginVO;
import com.project.blog.mail.MailSendService;
import com.project.blog.main.service.MemberService;
import com.project.blog.member.MemberVO;
import com.project.blog.util.SHA256Util;

@Controller
public class MainController {

	@Autowired
	private MemberService service;
	
	private MailSendService mss = new MailSendService();

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String chat() {
		return "/test";
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "login/login";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {

		return "join/join";
	}

	@ResponseBody
	@RequestMapping(value = "/login/loginOk")
	public Map<String, Object> loginOk(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		int result = service.login(loginVO, response, session);

		Map<String, Object> rs = new HashMap<String, Object>();
		if (result == 1) {
			rs.put("result", 1);
		} else if (result == 0) {
			rs.put("result", 0);
		} else {
			rs.put("result", -1);
		}

		return rs;
	}
	
	@RequestMapping(value="logout")
	public String logout(LoginVO loginVO, HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/login";
	}
	
	 @RequestMapping(value="/join/joinOk", method=RequestMethod.POST)
	 public  String joinOk(MemberVO member) {
		
		String salt = SHA256Util.generateSalt();
		member.setSalt(salt); 
		
		String password = member.getMemPassword();
		password = SHA256Util.getEncrypt(password, salt);
		member.setMemPassword(password);

		 // 저장
		 int result = service.join(member);
		 // 인증 키 생성 및 이메일 발솔
		// String authKey = mss.sendAuthMail(member.getMemEmail());
		// member.setAuthKey(authKey);
		 
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("email", member.getMemEmail());
		// map.put("authKey", authKey);
		 
		 //service.updateAuthKey(map);
		 
		 return "redirect:/login";
	 /*int result = service.join(member);
		 
		 //임의의 authKey 생성 & 이메일 발송 
		 String authKey = mss.sendAuthMail(member.getMemEmail()); 
		 member.setAuthKey(authKey);
		 
		 Map<String, String> map = new HashMap<String, String>(); 
		 map.put("email", member.getMemEmail()); 
		 map.put("authKey", member.getAuthKey());
		 System.out.println(map);
		 
		 //DB에 authKey 업데이트 
		 service.updateAuthKey(map); 
		 return "redirect: /"; */
	 }
	 

//	@RequestMapping(value="/join/joinConfirm")
//	public String joinConfirm(MemberVO member) {
//		 
//		int result = service.join(member);
//		 //임의의 authKey 생성 & 이메일 발송
//        String authKey = mss.sendAuthMail(member.getMemEmail());
//        member.setAuthKey(authKey);
//
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("email", member.getMemEmail());
//        map.put("authKey", member.getAuthKey());
//        System.out.println(map);
//
//      //DB에 authKey 업데이트
//        service.updateAuthKey(map);
//		return "redirect: /";
//	}
	 
}
