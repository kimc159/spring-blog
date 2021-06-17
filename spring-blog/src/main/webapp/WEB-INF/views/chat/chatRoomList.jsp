<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
	<div class="user_find_area">
		<h2>채팅</h2>
		<div class="find_list"> 
			<ul class="list type_chat" id="findList">
				<c:forEach items="${lastChatMessage}" var="list"> 
					<li>
						<c:choose>
							<c:when test="${currentUser eq list.to_id}">
								<div class="img_area">
									<img src="/resources/images/profile_default.jpg" />
								</div>
								<a class="text_area" href="/chat?from_id=${list.to_id}&to_id=${list.from_id}">
									<span class="chat_user">${list.from_id}</span>
									<span class="last_text">${list.message}</span>
									<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:MM" value="${list.time}"/></span> 
									
								</a>
							</c:when>  
							<c:otherwise>
								<div class="img_area">
									<img src="/resources/images/profile_default.jpg" />
								</div>
								<a class="text_area" href="/chat?from_id=${list.from_id}&to_id=${list.to_id}">
									<span class="chat_user">${list.to_id}</span>  
									<span class="last_text">${list.message}</span>  
									<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:MM" value="${list.time}"/></span>
								</a>
							</c:otherwise>
						</c:choose>
					</li>
				</c:forEach>
			</ul> 
		</div>
	</div> 
</body>
</html>