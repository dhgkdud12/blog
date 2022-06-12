<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">
    <c:choose>
        <c:when test="${!empty boards}">
            <div>검색결과</div>
        </c:when>
        <c:otherwise>
            <div>검색결과가 없습니다.</div>
        </c:otherwise>
    </c:choose>
    <div class="form-row">
        <div class="col-7"></div>
        <div class="form-group col-5">
            <form class="form-inline mr-sm-2" action="/board/search">
                <select class="form-control" name="type" style="width: 30%; margin:4px;" >
                    <option>제목</option>
                    <option>내용</option>
                    <option>글쓴이</option>
                </select>
                <input class="form-control mr-sm-2" name="keyword" style="width: 50%;">
                <button id="btn-search" class="btn btn-success">검색</button>
            </form>
        </div>
    </div>

    <c:if test="${!empty boards}">
        <c:forEach var="board" items="${boards}">
            <div class="card m-2" >
                <div class="card-body">
                    <h4 class="card-title">${board.title}</h4>
                    <a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
                </div>
            </div>
        </c:forEach>
    </c:if>




<%--페이징--%>

</div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>


