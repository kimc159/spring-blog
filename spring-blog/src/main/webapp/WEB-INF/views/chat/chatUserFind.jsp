<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
	<div class="user_find_area">
		<h2>친구 찾기</h2>
		<div class="find_search">
			<input type="text" id="findUserName">
			<button type="button" onClick="chatUserFind()">찾기</button>
		</div>
		<div class="find_list">
			<ul class="list type_chat" id="findList">
			</ul>  
		</div>
	</div>
	<script>
		function chatUserFind() {
			var memID = $("#findUserName").val();
			
			$.ajax({ 
				url: "./chatUserFind",
				data: {
					memID : memID
				},
				type:"post",
				dayaType:"json",
				success : function(rs) {
					$("#findList").html("");  
					var result = rs;  
					
					if(result.searchUser !== null) {  
						addList(result);
					} else { 
						alert("없는 사용자입니다.");
					}
				},
				error : function(error) {
					console.log(error);
				}
			})
		}

		function userFriendAdd(login_id, friend_id) {
			var login_id = login_id;
			var friend_id = friend_id;
			
			$.ajax({ 
				url: "./userFriendAdd", 
				data: {
					login_id : login_id,
					friend_id : friend_id
				},
				type:"post",
				dayaType:"json",
				success : function(rs) {
					var result = rs.result; 
					
					if(result == 1) {
						alert("친구추가가 완료되었습니다.");
					} else {
						alert("이미 친구입니다.");
					} 
				},
				error : function(error) {
					console.log(error); 
				}
			})
		}
		
		function addList(result) {
			var searchUser = result.searchUser;
			var loginUser = result.loginUser;
			var findList = $("#findList");
			var html = '';
			
		/* 	html += '<li>'; 
			html += '<div class="img_area">';
			html += '<img src="/resources/images/profile_default.jpg" />';
			html += '</div>';
			html += '<span>'+searchUser.memId+'</span>';
			html += '<span class="add"> + </span>';
			html += '<button type="button" onClick="location.href=\'/chat?from_id='+loginUser.memId+'&to_id='+searchUser.memId+'\'">대화하기</span>';
			html += '</li>'; 
		 */
			html += '<li>';
				html += '<div class="img_area">';
					html += '<img src="/resources/images/profile_default.jpg" />';
				html += '</div>';
				html += '<div class="text_area no_text">';
					html += '<span class="chat_user">'+searchUser.memId+'</span>';
					html += '<ul class="right_list">';
						html += '<li>'; 
							html += '<span class="add" onClick="userFriendAdd(\''+loginUser.memId+'\',\''+searchUser.memId+'\');">친구추가</span>';
						html += '</li>';
						html += '<li>';
							html += '<a class="converse" href="/chat?from_id='+loginUser.memId+'&to_id='+searchUser.memId+'">대화하기</a>';
						html += '</li>';
					html += '</ul>';
				html += '</div>';
			html += '<li>'; 
			
			findList.append(html); 
		}
	</script>
</body>
</html>