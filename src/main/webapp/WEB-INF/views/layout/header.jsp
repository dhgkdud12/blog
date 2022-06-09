<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>HaYoung 블로그</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>
<body>
<%--<h1>${principal}</h1>--%>
<ul id="nav3" class="nav justify-content-end bg-light">
    <li class="nav-item">
        <a class="nav-link active" href="/">Home</a>
    </li>
    <c:choose>
        <%--            <c:when test="${empty sessionScope.principal}">--%>
        <c:when test="${empty principal}">
            <li class="nav-item">
                <a class="nav-link" href="/auth/loginForm">로그인</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/auth/joinForm">회원가입</a>
            </li>
        </c:when>
        <c:otherwise>
            <li class="nav-item">
                <a class="nav-link" href="/board/saveForm">글쓰기</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/board/myWriteForm">내가 쓴 글</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/board/myReplyForm">내가 쓴 댓글</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/user/updateForm">회원정보</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout">로그아웃</a>
            </li>
        </c:otherwise>
    </c:choose>

</ul>
<br/>