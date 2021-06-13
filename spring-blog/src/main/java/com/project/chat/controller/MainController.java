package com.project.chat.controller;

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

import com.project.chat.board.PapagoTranslate;
import com.project.chat.login.LoginVO;
import com.project.chat.mail.MailSendService;
import com.project.chat.main.service.MemberService;
import com.project.chat.member.MemberVO;
import com.project.chat.util.SHA256Util;
import com.project.chat.util.ScriptUtils;

@Controller
public class MainController{

	@Autowired
	private MemberService service;
	
	@Autowired
	private MailSendService mss;

	@Autowired
	private PapagoTranslate papago; 
	
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
		
		int result = service.login(loginVO, response, session);

		Map<String, Object> rs = new HashMap<String, Object>();
		
		rs.put("result", result); 
		return rs;
	}
	
	@RequestMapping(value="/logout") 
	public void logout(HttpServletResponse response, HttpSession session) {

		session.invalidate();
		try {
			ScriptUtils.alertAndMovePage(response, "로그아웃되었습니다.", "/login");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	@RequestMapping(value="/join/joinOk", method=RequestMethod.POST)
	public  String joinOk(MemberVO member, HttpServletResponse response, Model model) throws IOException {

		if(member.getMemPassword() == null || member.getMemPassword().equals("")) {
			 model.addAttribute("url", "/join/modify");
			 model.addAttribute("msg", "");
			 return "common/redirect";
		}
		// SHA256 암호화
		String salt = SHA256Util.generateSalt();
		member.setSalt(salt);
		
		// SHA256 암호화
		String password = member.getMemPassword();
		password = SHA256Util.getEncrypt(password, salt);
		member.setMemPassword(password);
		member.setAuthKey("1");
		 // ����
		int result = service.join(member);

		if(result == 1) {
			 // 회원가입 완료
			 
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
			 model.addAttribute("msg", "회원가입에 실패하였습니다.");
			 return "common/redirect";
		} 
	 }
	 
     // 이메일 인증
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
			model.addAttribute("msg", "이메일 인증에 실패하였습니다.");
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
		 
		
		String salt = SHA256Util.generateSalt();
		member.setSalt(salt);
		
		String password = member.getMemPassword();
		password = SHA256Util.getEncrypt(password, salt);
		member.setMemPassword(password);

		int result = service.modify(member);
		
		if(result == 1) {
			 model.addAttribute("url", "/board/list");
			 model.addAttribute("msg", "수정이 완료되었습니다.");
			
			 return "common/redirect"; 
		} else {
			 model.addAttribute("url", "/join/modify");
			 model.addAttribute("msg", "수정에 실패하였습니다.");
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
