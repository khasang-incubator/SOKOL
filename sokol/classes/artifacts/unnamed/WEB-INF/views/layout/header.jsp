<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                        <a href="${root_url}register">Регистрация</a>
                    </li>
                    <li>
                        <a href="${root_url}login">Войти</a>
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
                            <li><a href="/myprofile">Мой профиль</a></li>
                            <li><a href="#">Справка</a></li>
                            <li><a href="/static/j_spring_security_logout">Выход</a></li>
                        </ul>
                    </li>
                </sec:authorize>
            </ul>
            <a class="navbar-brand" href="#"><img src="${root_url}img/Logo1.png"/></a>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/">Главная</a></li>
                    <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
                        <li><a href="/requestList/list?pagenumber=1&sortBy=id&sortOrder=ASC">Запросы</a></li>
                        <li><a href="/requestList/list?pageNumber=1&sortBy=id&sortOrder=ASC">Запросы</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Настройки<b
                                    class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <!-- <li class="divider"></li> -->
                                <li><a href="/department/list">Департаменты</a></li>
                                <li><a href="/users/list">Пользователи</a></li>
                                <li><a href="/requestType/list">Типы запросов</a></li>
                            </ul>
                        </li>
                    </sec:authorize>
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
            <li>${headerTitle}</li>
        </ul>
    </div>
</div>
<!-- /Header -->


