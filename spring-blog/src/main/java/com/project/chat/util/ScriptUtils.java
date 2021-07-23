package com.project.chat.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ScriptUtils {
	
	public static void init(HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
	}
	
	public static void alert(HttpServletResponse response, String message) throws IOException {
		init(response);
		PrintWriter out = response.getWriter();
		out.println("<script>alert('"+message+"');</script>");
		out.flush();
	}

	public static void alertAndMovePage(HttpServletResponse response, String message, String movePage) throws IOException {
		init(response);
		PrintWriter out = response.getWriter();
		out.println("<script>alert('"+message+"'); location.href='"+movePage+"';</script>");
		out.flush();
	}
 
	public static void alertAndbackPage(HttpServletResponse response, String message) throws IOException {
		init(response);
		PrintWriter out = response.getWriter();
		out.println("<script>alert('"+message+"'); history.back(-1);</script>");
		out.flush();
	}

    public static void movePage(HttpServletResponse response, String movePage) throws IOException {
        init(response);
        PrintWriter out = response.getWriter();
        out.println("location.href='"+movePage+"';</script>");
        out.flush();
    }
}
