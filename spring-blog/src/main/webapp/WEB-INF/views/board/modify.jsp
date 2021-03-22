<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %> 
</head>
<body>
<h2 style="text-align: center;">글 수정</h2><br><br><br>

<div style="width: 60%; margin: auto;">
	<form method="post" action="/modify">
		<input type="hidden" name="seq" value="${board.seq}" />
		<input type="text" name="writer" style="width: 20%;" placeholder="작성자" value="${board.writer}" /><br>
		<input type="text" name="title" style="width: 40%;" placeholder="제목" value="${board.title}" />
		<br><br> 
		<textarea id="summernote" name="content">${board.content}</textarea>
		<input id="subBtn" type="button" value="글 수정" style="float: right;" onclick="goWrite(this.form)"/>
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
	
	var modifyForm = confirm("수정하시겠습니까?");
	
	if(modifyForm) {
		frm.submit();
	}
	
}
</script>
</body>
</html>