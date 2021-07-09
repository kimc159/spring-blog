<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %> 
<div class="chat_room">
	<h2 class="to_user">${room.to_id} </h2>
	 <div id="chatBox" class="chatBox"> 
	    <div class="card">
	        <div id="chatbox-area">
	            <div class="card-content chat-content">
	                <div class="content">
	                	<c:forEach var="list" items="${chatList}" varStatus="status">
	                		<c:choose>
		                		<c:when test="${currentUser == chatList[status.index].from_id}">
				                    <div class="chat-message-group writer-user">
				                        <div class="chat-messages">
				                            <div class="message"><span class="message_read">${list.message_read}</span> ${list.message}</div> 
				                            <div class="from"><fmt:formatDate pattern="yyyy-MM-dd H:mm" value="${list.time}" /></div>
				                        </div> 
				                    </div>
		                		</c:when>
	                			<c:otherwise>
				                    <div class="chat-message-group">
				                        <div class="chat-messages">
				                            <div class="message"><span class="message_read">${list.message_read}</span> ${list.message}</div> 
				                            <div class="from"><fmt:formatDate pattern="yyyy-MM-dd H:mm" value="${list.time}" /></div>
				                        </div>
				                    </div>
	                			</c:otherwise>
	                		</c:choose> 
	                	</c:forEach>
	                </div> 
	            </div>
	            <div id="chatBox-textbox" class="card-footer">
	                <textarea placeholder="메시지 입력" class="chat-textarea" id="chatInput"></textarea>
	               	<button type="button" id="chatBtn" onClick="send();">send</button>
	            </div>
	        </div>
	    </div>
	</div>
</div>
	
	<script>
	
		$(function() {
			$("#chatbox-area").find(".card-content").scrollTop($(".content").innerHeight());  
		});
		
		var from_id = '<c:out value="${currentUser}" />';
		var to_id = '<c:out value="${room.to_id}" />';
		var room_id = <c:out value="${room.room_id}" />;
		
		
		let sock = new SockJS("http://localhost:8090/socket");
		sock.onopen = onOpen;
		sock.onmessage = onMessage; 
		sock.onclose = onClose;  
		//메세지 전송 함수 : 입력된 글자를 불러와서 서버에 전송
		function send(){ 
		        var text = $("#chatInput").val();
		         
			    if(!text){
				    return;
			    }
			    sendMessage(text);
			    
			    $("#chatInput").val(""); 
	    } 
		function onOpen() {
			console.log("socket open");
		}
		// 메시지 전송 
		function sendMessage(message) {
			var chatInfo = {
					from_id : from_id,
					to_id : to_id,
					room_id : room_id,
					message : message
			}
			sock.send(JSON.stringify(chatInfo));
		}
		
		// 서버로부터 메시지를 받았을 때 
		function onMessage(msg) {
			
			var data = msg.data.split("&");
			var jsonData = JSON.parse(data[1]); 
			var createRoom = data[3];
			var html = ''; 
			
			// 첫 채팅방 생성한 사람의 room_id 대입
			if(jsonData.from_id === from_id && jsonData.to_id === to_id) {
				room_id = jsonData.room_id;
			}
			
			// 첫 채팅방 생성했거나 room_id가 같으면
			if((createRoom === "true" && jsonData.from_id === from_id && jsonData.to_id === to_id) || jsonData.room_id === room_id) { 
				 
				if(from_id === data[0]) { // 내가 보낸 메시지일 경우
					html +='<div class="chat-message-group writer-user">';	
				} else { // 내가 보낸 메시지가 아닐 경우
					html +='<div class="chat-message-group">';  
				} 
				 
		        html +='    <div class="chat-messages">';
		        html +='        <div class="message"><span class="message_read">1</span>' + jsonData.message + '</div>';
		        html +='        <div class="from">'+data[2]+'</div>';
		        html +='    </div>'; 
		        html +='</div>';
		        
				$("#chatbox-area").find(".content").append(html);   
				$("#chatbox-area").find(".card-content").scrollTop($(".content").innerHeight());
				
			} else {
				return;
			}
		} 
		// 서버와 연결을 끊었을 때
		function onClose(evt) {
			console.log("socket close");
		}
	</script> 
</body>
</html>