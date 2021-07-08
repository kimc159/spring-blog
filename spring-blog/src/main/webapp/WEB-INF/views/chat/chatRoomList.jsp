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
									<span class="last_text"><span class="message_read">${list.message_read}</span>${list.message}</span> 
									<span class="time"><fmt:formatDate pattern="yyyy-MM-dd H:mm" value="${list.time}"/></span> 
								</a>
							</c:when>
							<c:otherwise>
								<div class="img_area">
									<img src="/resources/images/profile_default.jpg" />
								</div>
								<a class="text_area" href="/chat?from_id=${list.from_id}&to_id=${list.to_id}">
									<span class="chat_user">${list.to_id}</span>
									<span class="last_text"><span class="message_read">${list.message_read}</span>${list.message}</span>   
									<span class="time"><fmt:formatDate pattern="yyyy-MM-dd H:mm" value="${list.time}"/></span>
								</a>
							</c:otherwise>
						</c:choose>
						<span class="room_delete" onClick="roomDelete(this, ${list.room_id})">X</span>
					</li>
				</c:forEach>  
			</ul> 
		</div>
	</div> 
	
	<script>
		
		
		let sock = new SockJS("http://localhost:8090/echo-ws");
		sock.onopen = onOpen;
		sock.onmessage = onMessage; 
		sock.onclose = onClose;
		
		function onOpen() {
			console.log("socket open");
		}
		// 서버로부터 메시지를 받았을 때 
		function onMessage(msg) {
			
			var from_id = JSON.parse(msg.data.split("&")[1]).from_id;
			var to_id = JSON.parse(msg.data.split("&")[1]).to_id; 
			var room_id = JSON.parse(msg.data.split("&")[1]).room_id;
			var last_message = JSON.parse(msg.data.split("&")[1]).message;
			var time = msg.data.split("&")[2].substring(0, msg.data.split("&")[2].length-3);
			var create_room = msg.data.split("&")[3];  
			var roomSelector = $("#findList");
			var html = '';  
			 
			if(create_room === "true") { 
				html += '<li>';  
				html += '<div class="img_area">';
				html += '	<img src="/resources/images/profile_default.jpg" />';
				html += '</div>';
				html += '<a class="text_area" href="/chat?from_id='+from_id+'&to_id='+to_id+'" >';
				html += '	<span class="chat_user">'+from_id+'</span>';
				html += '	<span class="last_text">'+last_message+'</span>'; 
				html += '<span class="time">'+time+'</span>'; 
				html += '</a>';
				html += '</li>'; 
				
				roomSelector.prepend(html); 
			}
		} 
		
		// 서버와 연결을 끊었을 때
		function onClose(evt) {
			console.log("socket close");
		}
		
		// 채팅방 삭제
		function roomDelete(thisobj, room_id) {
			
			var $this = thisobj;
			var check = confirm('대화방을 나가시겠습니까?');  
			
			if(confirm) {
				$.ajax({ 
					url: "./roomDelete",
					data: {
						room_id : room_id
					},
					type:"post",
					dayaType:"json",
					success : function(rs) {
						
						var result = rs.result;
						
						if(result === 1) {
							$this.closest("li").remove();
							
						}
					},
					error : function(error) {
						console.log(error);
					}
				})
			}
			
		}
	</script> 
</body>
</html>