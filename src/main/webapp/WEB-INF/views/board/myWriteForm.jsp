<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>
<div class="container">
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <br/><br/>
    <div class="card">
        <div class="card-header">내가 쓴 글 리스트</div>
        <ul id="board-box" class="list-group">
            <c:forEach var="board" items="${boards}">
                <c:if test="${board.user.id == principal.user.id}">
                    <li id="board-${board.id}" class="list-group-item d-flex justify-content-between">
                        <div>제목: ${board.title}</div>
                        <div>내용: ${board.content}</div>
                        <div class="d-flex ">
                            <div class="font-italic">작성자: ${board.user.username} &nbsp;</div>
                        </div>
                    </li>
                </c:if>
            </c:forEach>
        </ul>
    </div>
</div>

<script>
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>


