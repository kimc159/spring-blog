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
			<ul class="list" id="findList">
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
		 
		function addList(result) {
			var searchUser = result.searchUser;
			var loginUser = result.loginUser; 
			var findList = $("#findList");
			var html = '';
			
			console.log(result);
			html += '<li>'; 
			html += '<span>'+searchUser.memId+'</span>';
			html += '<button type="button" onClick="location.href=\'/chat?from_id='+loginUser.memId+'&to_id='+searchUser.memId+'\'">대화하기</span>';
			html += '</li>';
			
			findList.append(html); 
		}
	</script>
</body>
</html>