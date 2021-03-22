<%@ include file="../include/header.jsp" %> 

<div class = "container">
    <table class = "table table-striped">
        <thead>
        <tr>
            <th>번호</th>
            <th>방 이름</th>
            <th>입장버튼</th>
        </tr>
        </thead>
        <tbody>
        <tr th:each="room : ${rooms}">
            <td th:text="${room.roomId}"></td>
            <td th:text="${room.name}"></td>
            <td>
                <a class = "btn btn-primary" th:href = "@{/rooms/{id} (id = ${room.roomId})}"></a>
            </td>
        </tr>
        </tbody>
    </table>
    <a class = "btn btn-primary pull-right" href = "/new">새로 만들기</a>
</div>


</body>
</html>