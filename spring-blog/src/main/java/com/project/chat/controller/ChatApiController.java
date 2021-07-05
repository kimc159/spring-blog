package com.project.chat.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.chat.login.LoginVO;
import com.project.chat.main.service.MemberService;
import com.project.chat.util.ResponseDto;

@RestController
public class ChatApiController {

//	@Autowired
//	private MemberService service;
//	
//
//	@PostMapping("/login/loginOk")
//	public ResponseDto<Integer> loginOk(@RequestBody LoginVO loginVO, HttpServletResponse response, HttpSession session) {
//		
//		int result = service.login(loginVO, response, session);   
//		
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}
//	
}
