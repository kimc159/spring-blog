<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
	<div class="user_find_area">
		<h2>친구</h2> 
		<div class="find_list">  
			<ul class="list type_chat" id="findList">
				<c:forEach items="${list}" var="list"> 
					<li>
						<div class="img_area">
							<img src="/resources/images/profile_default.jpg" />
						</div>
						<a class="text_area no_text" href="/chat?from_id=${list.id}&to_id=${list.friend_id}">
							<span class="chat_user">${list.friend_id}</span>
						</a>
					</li>
				</c:forEach>
			</ul> 
		</div>
	</div> 
</body>
</html>