<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %> 

<div class="formArea">
	<h2>글 작성</h2>
	<form method="post" action="/board/write">
		<input type="hidden" name="writer" id="writer" placeholder="작성자" value="${login.memId}"/>
		<div class="boardInputArea">
			<label for="writer" id="writerLabel">작성자 : ${login.memId} </label>
		</div>
		<div class="boardInputArea">
			<input type="text" name="title" style="width: 40%;" placeholder="제목"/>
		</div>
		<div class="boardInputArea">
			<textarea id="summernote" name="content"></textarea>
		</div>
		
		
		<div class="btnArea">
			<button type="button" onclick="goWrite(this.form)">글 작성</button>
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
	if (content.trim() == ''){
		alert("내용을 입력해주세요");
		return false;
	}
	frm.submit();
}
</script>
</body>
</html>