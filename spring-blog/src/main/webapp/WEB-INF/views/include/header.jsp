<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jstl include -->
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- jstl include -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- tags include --> 
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html> 
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>블로그 사이트</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.2.4.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js"></script> 
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
	
</head>
<body>
	<div id="header" class="header">
	    <div class="headerInner">
	        <ul class="leftMenu">
	            <li>
	                <a href="/board/list">게시판</a>
	            </li>
	        </ul>
	        <h1 class="logo">
	            <a href="/main">블로그</a>
	        </h1>
	        <ul class="rightMenu">
				<c:if test="${empty login.memId}">
		            <li>
		                <a href="/login">로그인</a>
		            </li>
		            <li>
		                <a href="/join">회원가입</a>
		            </li>
	            </c:if>
				<c:if test="${not empty login.memId}">
		            <li>
		                <span><em>${login.memId}</em> 님</span>
		            </li>
		            <li>
		                <a href="/join">정보수정</a>
		            </li>
		            <li>
		                <a href="/logout">로그아웃</a>
		            </li>
	            </c:if>
	        </ul>
	    </div>
	</div>