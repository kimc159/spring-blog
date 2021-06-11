<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %> 
</head>
<body>
<div class="formArea">
	<h2>글 수정</h2>
	<form method="post" action="/boardFile/modify" enctype="multipart/form-data"> 
		<input type="hidden" name="seq" value="${board.seq}" />
		<input type="hidden" name="writer" value="${board.writer}" />
		
		<div class="boardInputArea"> 
			<label for="writer" id="writerLabel">작성자 : ${board.writer}</label>
		</div>
		<div class="boardInputArea">
			<input type="text" name="title" style="width: 40%;" placeholder="제목" value="${board.title}"  />
		</div>
		<div class="boardInputArea" style="margin-bottom:10px;">
			<textarea id="summernote" name="content">${board.content}</textarea>
		</div>
		<c:if test="${empty board.org_file_name}">
			<div class="boardInputArea">
				<input type="file" name="file"/>
			</div>
		</c:if>
		<c:if test="${not empty board.org_file_name}"> 
			<div class="boardInputArea">
				<span class="uploaded_file">${board.org_file_name} - <button type="button" onClick="removeFile(this)" >X</button></span>
				<input type="file" name="file" class="select_file" style="display:none;" />
			</div> 
		</c:if> 
		<div class="btnArea"> 
			<button type="button" onclick="goWrite(this.form)">글 수정</button>
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
	
	var modifyForm = confirm("수정하시겠습니까?");
	
	if(modifyForm) {
		frm.submit();
	}
	
}
</script>
</body>
</html>