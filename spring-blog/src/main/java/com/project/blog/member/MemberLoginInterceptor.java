package com.project.blog.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.blog.login.LoginVO;
import com.project.blog.util.ScriptUtils;

public class MemberLoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		
		if(login == null) {
			ScriptUtils.alertAndMovePage(response, "로그인이 필요합니다.", "/login");
			return false;
		}

		
		return true;
	}
}
