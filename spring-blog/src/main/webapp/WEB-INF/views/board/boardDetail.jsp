<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %> 
<style>

</style>
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
		<div class="btnArea">
			<a href="/board/replyForm?boardGroup=${board.boardGroup}&boardSeq=${board.boardSeq}&boardLevel=${board.boardLevel}">댓글</a>
			<a href="/board/list">글목록</a>
			<c:if test="${login.memId == board.writer}">
				<a href="/board/modify?seq=${board.seq}&board_writer=${board.writer}">수정</a>
				<button type="button" onClick="del(${board.seq}, ${board.writer})">삭제</button>
			</c:if>
		</div>
	</div> 
</div>
<script>
	function del(seq, board_writer) {
		var chk = confirm("삭제하시겠습니까?");
		
		if(chk) {
			location.href="/board/delete?seq=" + seq + "&board_writer=" + board_writer;
		}
		
	}
</script>
</body>
</html>