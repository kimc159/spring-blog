<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<div class="boardArea">
	 <div class="searchArea">
		 <form action="/board/list" method="post">
		 	<input type="hidden" value="${scri.perPageNum}" name="perPageNum" />
		 	<select name="searchType">
		 		<option value="all" <c:if test="${scri.searchType == 'all'}">selected</c:if>>전체</option>
		 		<option value="title" <c:if test="${scri.searchType == 'title'}">selected</c:if>>제목</option>
		 		<option value="content" <c:if test="${scri.searchType == 'content'}">selected</c:if>>내용</option>
		 	</select>
		 	<input type="text" name="keyword" value="<c:out value='${scri.keyword}' />"/>
		 	<button type="submit">검색</button>
		</form>
	 </div>
 	<div class="tableArea">
		<table border="1">
			<thead>
				<tr>
					<th>순서</th>
					<th>제목</th>
					<th>내용</th>
					<th>날짜</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>group</th>
					<th>seq</th>
					<th>level</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="list">
				<tr>
					<td>${list.seq}</td>
					<td><a href="boardDetail?seq=${list.seq}">${list.title}</a></td> 
					<td>${list.content}</td>
					<td><fmt:formatDate pattern="yyyy-mm-dd hh:mm:ss" value="${list.regDate}" /></td>
					<td>${list.writer}</td> 
					<td>${list.cnt}</td> 
					<td>${list.board_group}</td> 
					<td>${list.board_seq}</td>
					<td>${list.board_level}</td> 
				</tr>
			</c:forEach>
			</tbody>
		</table>
 	</div>
	<div class="btnArea">
		<a href="/board/write">글쓰기</a>
	</div>
	<div class="pagingArea">
		<ul class="pagination">
			<c:if test="${pageMaker.prev}"> 
				<li>
					<a href="list<c:url value="${pageMaker.makeSearch(pageMaker.startPage - 1, scri.perPageNum)}" />">이전</a>
				</li>
			</c:if>
			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
				<c:choose>
					<c:when test="${scri.page == idx}">
						<li>
							<a href="list<c:url value="${pageMaker.makeSearch(idx, scri.perPageNum)}"/>" style="font-weight:bold;">${idx}</a> 
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="list<c:url value="${pageMaker.makeSearch(idx, scri.perPageNum)}"/>">${idx}</a> 
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach> 
			<c:if test="${pageMaker.next  && pageMaker.endPage > 0}"> 
				<li>
					<a href="list<c:url value="${pageMaker.makeSearch(pageMaker.endPage + 1, scri.perPageNum)}"/>">다음</a>
				</li>
			</c:if>
		</ul>
	</div>
	
	<select name="perPageNum" id="perPageSelect">
		<option value="3" <c:if test="${scri.perPageNum == 3}">selected</c:if>>3</option>
		<option value="5" <c:if test="${scri.perPageNum == 5}">selected</c:if>>5</option>
		<option value="10" <c:if test="${scri.perPageNum == 10}">selected</c:if>>10</option>
	</select>
</div>
<script>

$(function() {
	$("#perPageSelect").on("change", function() {
		var perPageNum = $(this).val();
		location.href="/board/list?page=1&perPageNum=" + perPageNum;
	});
});
</script>
</body>
</html>