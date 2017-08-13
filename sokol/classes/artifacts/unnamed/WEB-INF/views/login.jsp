<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">

<s:url var="authUrl" value="/static/j_spring_security_check"/>
    <div class="login-frame" >
    <form class="form-signin" action="${authUrl}" method="post">
        <%--Вход--%>
        <h2 class="form-signin-heading"><s:message code="entry"/></h2>
        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <div class="username-fld" >
            <%--Email address--%>
        <label for="inputEmail" class="sr-only"><s:message code="email"/></label>
        <input name="j_username" type="email" id="inputEmail" class="form-control" placeholder="<s:message code="email"/>" required autofocus>
        </div>
        <%--Password--%>
        <label for="inputPassword" class="sr-only"><s:message code="password"/></label>
        <input name="j_password" type="password" id="inputPassword" class="form-control" placeholder="<s:message code="password"/>" required>
        <div class="checkbox">
            <label>
                <%--<input type="checkbox" id="j_remember" name="_spring_security_remember_me" > Remember me--%>
                    <%--Remember me--%>
                <input type="checkbox" id="j_remember" name="remember-me" > <s:message code="remember_me"/>
            </label>
        </div>
        <%--Войти--%>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><s:message code="enter"/></button>
        <%--Регистрация--%>
        <a class="btn btn-lg btn-info btn-block" href="/register"><s:message code="registration"/></a>
    </form>
    </div>
</div> <!-- /container -->
