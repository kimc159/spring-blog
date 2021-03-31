package com.project.blog.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@Autowired
	private MailSendService mss;

	@RequestMapping(value = "/redirect")
	public String redirect() {
		return "common/redirect";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login/login";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {

		return "join/join";
	}
	
	@RequestMapping(value = "/join/modify", method = RequestMethod.GET)
	public String modify(HttpSession session, Model model) {
		model.addAttribute("member", service.selectMember(session.getAttribute("user_id").toString()));
		return "join/modify";
	}

	@ResponseBody
	@RequestMapping(value = "/login/loginOk")
	public Map<String, Object> loginOk(LoginVO loginVO, HttpServletResponse response, HttpSession session) {
		
		int result = service.login(loginVO,response,session);

		Map<String, Object> rs = new HashMap<String, Object>();
		if (result == 1) {
			int authStatus = service.memberAuthStatus(loginVO);
			
			if(authStatus == 1) {// 이메일 인증 한 상태
				rs.put("result", 1);
			} else { // 이메일 인증 안한 상태
				rs.put("result", -2);
			}
			
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
	public  String joinOk(MemberVO member, HttpServletResponse response, Model model) throws IOException {

		if(member.getMemPassword() == null || member.getMemPassword().equals("")) {
			 model.addAttribute("url", "/join/modify");
			 model.addAttribute("msg", "비밀번호를 입력해주세요.");
			 return "common/redirect";
		}
		
		// 임의의 난수 생성
		String salt = SHA256Util.generateSalt();
		member.setSalt(salt);
		
		// SHA256 비밀번호 암호화
		String password = member.getMemPassword();
		password = SHA256Util.getEncrypt(password, salt);
		member.setMemPassword(password);
		member.setAuthKey("1");
		 // 저장
		int result = service.join(member);

		if(result == 1) {
			 // 인증 키 생성 및 이메일 발솔
			 
			String authKey = mss.sendAuthMail(member.getMemEmail());
			member.setAuthKey(authKey);
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("email", member.getMemEmail()); 
			map.put("authKey", authKey);
			 
			 service.updateAuthKey(map);
			 model.addAttribute("url", "/login");
			 model.addAttribute("msg", "회원가입이 완료되었습니다.");
			
			 return "common/redirect"; 
		} else {
			 model.addAttribute("url", "/join");
			 model.addAttribute("msg", "데이터베이스 오류가 발생했습니다.");
			 return "common/redirect";
		} 
	 }
	 
	 @RequestMapping(value="/join/confirm")
	 public String joinConfirm(Model model, @RequestParam("email") String email, @RequestParam("authKey") String authKey) {
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("email", email);
		 map.put("authKey", authKey);
		 
		int result = service.joinConfirm(map);

		if(result == 1) {
			model.addAttribute("url", "/login");
			model.addAttribute("msg", "이메일 인증이 완료되었습니다.");	
		} else {
			model.addAttribute("url", "/login");
			model.addAttribute("msg", "이메일 인증이 오류가 발생했습니다.");
		}
		 return "common/redirect";
	 }
	 
	 @RequestMapping(value="/join/modifyOk", method=RequestMethod.POST)
	 public  String modifyOk(MemberVO member, Model model) throws IOException {
		
		 if(member.getMemPassword() == null || member.getMemPassword().equals("")) {
			 model.addAttribute("url", "/join/modify");
			 model.addAttribute("msg", "비밀번호를 입력해주세요.");
			 return "common/redirect";
		 }
		 
		// 임의의 난수 생성
		String salt = SHA256Util.generateSalt();
		member.setSalt(salt);
		
		// SHA256 비밀번호 암호화
		String password = member.getMemPassword();
		password = SHA256Util.getEncrypt(password, salt);
		member.setMemPassword(password);

		 // 저장
		int result = service.modify(member);
		
		if(result == 1) {
			 model.addAttribute("url", "/board/list");
			 model.addAttribute("msg", "정보수정이 완료되었습니다.");
			
			 return "common/redirect"; 
		} else {
			 model.addAttribute("url", "/join/modify");
			 model.addAttribute("msg", "데이터베이스 오류가 발생했습니다.");
			 return "common/redirect";
		} 
		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value = "findId", method=RequestMethod.POST)
	 public Map<String, Object> findId(@RequestParam("email") String email) {
		 
		 String result = service.findId(email);
		 
		 Map<String, Object> rs = new HashMap<String, Object>();
		 
		 rs.put("result", result);
		 
		 return rs;
	 }
	 
	 @ResponseBody
	 @RequestMapping(value = "findPassword", method=RequestMethod.POST)
	 public Map<String, Object> findPassword(@RequestParam("id") String id, @RequestParam("email") String email) {
		 
		 String result = service.findPassword(id, email);
		 
		 Map<String, Object> rs = new HashMap<String, Object>();
		 
		 rs.put("result", result);
		 
		 return rs;
	 }
	 
	 @ResponseBody
	 @RequestMapping(value = "idCheck", method=RequestMethod.POST)
	 public Map<String, Object> idCheck(@RequestParam("id") String id) {
		 
		 int result = service.idCheck(id);
		 
		 Map<String, Object> rs = new HashMap<String, Object>();
		 
		 rs.put("result", result);
		 
		 return rs;
	 }
	 
}
