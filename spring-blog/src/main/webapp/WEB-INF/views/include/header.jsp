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
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js?js"></script> 
	<!-- sockJS -->
	<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>  
	<c:set var="currentUser" value="${login.memId}" /> 
</head>
<body> 
	<div id="header" class="header"> 
	    <div class="headerInner">
	    	<ul class="leftMenu">
				<c:if test="${not empty login.memId}">
		            <li>
		                <a href="/chat/chatUserFind">친구찾기</a> 
		            </li>
		            <li>
		                <a href="/chat/chatRoomList">채팅</a> 
		            </li>
	            </c:if>
	    	</ul>
	        <h1 class="logo">
	            <a href="/board/list" data-trans="블로그">블로그</a>
	        </h1>
	        <ul class="rightMenu">
				<c:if test="${empty login.memId}">
		            <li>
		                <a href="/login" data-trans="로그인">로그인</a>
		            </li>
		            <li>
		                <a href="/join" data-trans="회원가입">회원가입</a>
		            </li>
	            </c:if>
				<c:if test="${not empty login.memId}"> 
		            <li> 
		                <span><em data-trans="${login.memId}">${login.memId}</em> <em data-trans="님">님</em></span>
		            </li>
		            <li>
		                <a href="/board/list" data-trans="일반게시판">일반게시판</a>
		            </li>
		            <li>
		                <a href="/boardFile/list" data-trans="파일게시판">파일게시판</a>
		            </li>
		            <li>
		                <a href="/join/modify" data-trans="정보수정">정보수정</a>
		            </li>
		            <li>
		                <a href="/logout" data-trans="로그아웃">로그아웃</a>
		            </li>
	            </c:if>
	        </ul>
	    </div>
	</div>