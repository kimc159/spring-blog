<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %> 
	<h1> MEMBER JOIN OK </h1>
	
	ID : ${member.memId} <br />
	PW : ${member.memPassword} <br />
	Name : ${member.memName} <br />
	BirthDay : ${member.memBirthDay} <br />
	Mail : ${member.memEmail} <br />
	Phone : ${member.memPhone} <br />  
	
	
	<a href="${cp}/"> MAIN </a>
</body>
</html>
 