<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<sec:authentication var="user" property="principal"/>


<!-- Header -->
<c:url var="root_url" value="/"/>
<nav class="navbar navbar-default navbar-static-top topnav header" role="navigation">
    <div class="container topnav">
        <!-- Brand and toggle get grouped for better mobile display -->
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAnonymous()">
                    <li>
                        <%--Регистрация--%>
                        <a href="${root_url}register"><s:message code="registration"/></a>
                    </li>
                    <li>
                        <%--Войти--%>
                        <a href="${root_url}login"><s:message code="enter"/></a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li class="profile-photo">
                        <img src="${root_url}img/nophoto.png"/>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">${user.getUsername()}
                            <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <%--Мой профиль--%>
                            <li><a href="/myprofile"><s:message code="my_profile"/></a></li>
                            <%--Справка--%>
                            <li><a href="#"><s:message code="help"/></a></li>
                            <%--Выход--%>
                            <li><a href="/static/j_spring_security_logout"><s:message code="exit"/></a></li>
                        </ul>
                    </li>
                </sec:authorize>
            </ul>
            <a class="navbar-brand" href="#"><img src="${root_url}img/Logo1.png"/></a>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <%--Главная--%>
                    <li><a href="/"><s:message code="main"/></a></li>
                    <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
                        <%--Запросы--%>
                        <li><a href="/requestList/list?pageNumber=1&sortBy=id&sortOrder=ASC"><s:message code="requests"/></a></li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                        <li class="dropdown">
                            <%--Настройки--%>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><s:message code="settings"/><b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <%--Департаменты--%>
                                <li><a href="/department/list"><s:message code="departments"/></a></li>
                                <%--Пользователи--%>
                                <li><a href="/users/list"><s:message code="users"/></a></li>
                                <%--Типы запросов--%>
                                <li><a href="/requestType/list"><s:message code="request_types"/></a></li>
                            </ul>
                        </li>
                    </sec:authorize>
                    <li><a href="/mypanel?mylocale=en">EN</a></li>
                    <li><a href="/mypanel?mylocale=ru">RU</a></li>
                </ul>
            </div>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<div class="info-line">
    <ul class="nav navbar-nav navbar-right">
        <li class="wing">
            <img src="${root_url}img/wing.png"/>
        </li>
    </ul>
    <div class="container topnav caption">
        <ul class="nav navbar-nav">
            <li><s:message code="${headerTitle}"/></li>
        </ul>
    </div>
</div>
<!-- /Header -->


