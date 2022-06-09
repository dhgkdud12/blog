<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>
<div class="container">
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <br/><br/>
    <div class="card">
        <div class="card-header">댓글 리스트</div>
        <ul id="reply-box" class="list-group">
            <c:forEach var="reply" items="${replys}">
                <c:if test="${reply.user.id == principal.user.id}">
                    <li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
                        <div>${reply.content}</div>
                        <div class="d-flex ">
                            <div class="font-italic">작성자: ${reply.user.username} &nbsp;</div>
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


