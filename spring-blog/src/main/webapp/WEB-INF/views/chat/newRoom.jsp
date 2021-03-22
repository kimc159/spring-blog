<%@ include file="../include/header.jsp" %> 
<div class="container">

    <form class="form-signing" action="/room/new" th:object="${form}" method= "post">
        <h2 class="form-signing-heading">새로운 채팅방 생성</h2>
        <label for="title" class="sr-only">채팅방 이름</label>
        <input type="text" th:field="*{name}" id="title" class="form-control" placeholder="채팅방 이름을 입력해주세요" required autofocus>
        <br>
        <button class="btn btn-primary" type="submit">생성하기</button>
        <button class="btn btn-danger" onclick = "location.href='/'">돌아가기</button>
    </form>

</div> <!-- /container -->


</body>
</html>