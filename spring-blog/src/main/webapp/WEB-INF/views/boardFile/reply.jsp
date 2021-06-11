<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %> 
<h2 style="text-align: center;">글 작성</h2><br><br><br>

<div style="width: 60%; margin: auto;">
	<form method="post" action="/boardFile/reply">
		<input type="hidden" name="boardGroup" value="${board.boardGroup}" />
		<input type="hidden" name="boardSeq" value="${board.boardSeq}" />
		<input type="hidden" name="boardLevel" value="${board.boardLevel}" />
		
		<div class="boardInputArea">
			<label for="writer" id="writerLabel">작성자 : ${user_id}</label>
			<input type="hidden" name="writer" value="${user_id}" />
		</div> 
		<div class="boardInputArea">
			<input type="text" name="title" style="width: 40%;" placeholder="제목" />
		</div>
		<br> 
		<div class="boardInputArea">
			<textarea id="summernote" name="content"></textarea>
		</div>
		<div class="btnArea">
			<button type="button" onclick="goWrite(this.form)">댓글 작성</button>
			<button type="button" onclick="history.back(-1);">취소</button>
		</div>
	</form>
</div>
<script>
function goWrite(frm) { 
	var title = frm.title.value;
	var writer = frm.writer.value;
	var content = frm.content.value;
	
	if (title.trim() == ''){
		alert("제목을 입력해주세요");
		return false;
	}
	if (writer.trim() == ''){
		alert("작성자를 입력해주세요");
		return false;
	}
	if (content.trim() == ''){
		alert("내용을 입력해주세요");
		return false;
	}
	frm.submit();
}
</script>
</body>
</html>