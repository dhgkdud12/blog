<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">
    <form action="/auth/loginProc" method="post">
        <div class="form-group">
            <label for="username">username</label>
            <input type="text" name= "username" class="form-control" placeholder="Enter username" id="username">
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name= "password" class="form-control" placeholder="Enter password" id="password">
        </div>

        <button id="btn-login" name="remember" class="btn btn-primary">로그인</button>
        <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=530ac854a553fa4e48c6335a5aba64ee&redirect_uri=http://localhost:9090/auth/kakao/callback&response_type=code"><img src="/image/kakao_login_button.png"/></a>
    </form>
</div>
<%--<script src="/js/user.js"></script>--%>
<%@ include file="../layout/footer.jsp"%>


