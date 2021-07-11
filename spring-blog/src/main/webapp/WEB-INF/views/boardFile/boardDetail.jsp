<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %> 
<body>
<div id="outter">
	<div class="boardDetail">
		<div class="titleArea">
			<h3 class="title">제목: ${board.title}</h3>
		</div>
		<div class="writerArea">
			작성자: ${board.writer}
			<span><fmt:formatDate value="${board.regDate}" pattern="yyyy.MM.dd"/></span>
			
		</div>
		<div class="contentArea">${board.content}</div>
		<div>
			<a href="/boardFile/download?stored_file_name=${board.stored_file_name}&org_file_name=${board.org_file_name}">${board.org_file_name}</a>
		</div> 
		<div class="btnArea">
			<a href="/boardFile/replyForm?boardGroup=${board.boardGroup}&boardSeq=${board.boardSeq}&boardLevel=${board.boardLevel}">댓글</a>
			<a href="/boardFile/list">글목록</a>
			<c:if test="${login.memId == board.writer}">
				<a href="/boardFile/modify?seq=${board.seq}&writer=${board.writer}">수정</a>
				<button type="button" onClick="del(${board.seq}, ${board.writer}, ${board.boardGroup})">삭제</button>  
			</c:if>
		</div> 
	</div> 
</div> 
<script>
	function del(seq, boardWriter, boardGroup) {
		var chk = confirm("삭제하시겠습니까?"); 
		
		if(chk) {
			location.href="/boardFile/delete?seq=" + seq + "&writer=" + boardWriter + "&boardGroup=" + boardGroup;
		}
		
	}
</script>
</body>
</html>