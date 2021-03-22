<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %> 
	
	결과 ${loginResult}
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.2.4.min.js"></script>
	<script> 
		$(function() {
			var loginResult = ${loginResult}; 
			
			if(loginResult === 1) { 
				alert("로그인이 완료되었습니다.");
				location.href="/";  
			} else if(loginResult === 0) {
				alert("비밀번호가 일치하지 않습니다.");
				location.href="/login";
			} else {
				alert("없는 회원입니다.");
				location.href="/login";
			}
		});
	</script>
</body>
</html>
