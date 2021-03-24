<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
	<script>
		alert("${msg}");a
		location.href = "<c:out value='${pageContext.request.contextPath}'/>${url}";
	</script>
</body>
</html>
