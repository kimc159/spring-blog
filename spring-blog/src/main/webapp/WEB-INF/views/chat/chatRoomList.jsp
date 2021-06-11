<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
	<div class="user_find_area">
		<h2>채팅</h2>
		<div class="find_search">
			<input type="text" id="findUserName">
			<button type="button" onClick="">찾기</button>
		</div>
		<div class="find_list">
			<ul class="list" id="findList">
				<c:forEach items="${roomList}" var="list">
					<li>
						<c:choose>
							<c:when test="${currentUser eq list.to_id}">
								<span>${list.from_id}</span>
								<button type="button" onclick="location.href='/chat?from_id=${list.to_id}&to_id=${list.from_id}'">대화하기</button>
							</c:when> 
							<c:otherwise>
								<span>${list.to_id}</span>
								<button type="button" onclick="location.href='/chat?from_id=${list.from_id}&to_id=${list.to_id}'">대화하기</button>
							</c:otherwise>
						</c:choose>
						
					</li>
				</c:forEach>
			</ul> 
		</div>
	</div> 
</body>
</html>