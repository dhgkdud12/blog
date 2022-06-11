<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp"%>
<div class="container">
    <div class="form-row">
        <div class="col-7"></div>
        <div class="form-group col-5">
            <form class="form-inline mr-sm-2" >
                <select class="form-control" style="width: 30%;">
                    <option>제목</option>
                    <option>내용</option>
                    <option>글쓴이</option>
                </select>
                <input class="form-control mr-sm-2" type="text" id="search-text" style="width: 50%;">
                <button id="btn-search" class="btn btn-success">검색</button>
            </form>
        </div>
    </div>

    <c:forEach var="board" items="${boards.content}">
        <div class="card m-2" >
            <div class="card-body">
                <h4 class="card-title">${board.title}</h4>
                <a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
            </div>
        </div>
    </c:forEach>


<%--페이징--%>
    <ul class="pagination justify-content-center">
        <c:choose>
            <c:when test="${boards.first}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${boards.last}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:otherwise>
        </c:choose>
    </ul>

</div>
<script src="/js/board.js"></script>
<%@ include file="layout/footer.jsp"%>


