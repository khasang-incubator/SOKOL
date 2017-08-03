<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">

<s:url var="authUrl" value="/static/j_spring_security_check"/>
    <div class="login-frame" >
    <form class="form-signin" action="${authUrl}" method="post">
        <h2 class="form-signin-heading">Вход</h2>
        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <div class="username-fld" >
        <label for="inputEmail" class="sr-only">Email address</label>
        <input name="j_username" type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
        </div>
        <label for="inputPassword" class="sr-only">Password</label>
        <input name="j_password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox">
            <label>
                <%--<input type="checkbox" id="j_remember" name="_spring_security_remember_me" > Remember me--%>
                <input type="checkbox" id="j_remember" name="remember-me" > Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>

        <a class="btn btn-lg btn-info btn-block" href="/register">Регистрация</a>
    </form>
    </div>
</div> <!-- /container -->
