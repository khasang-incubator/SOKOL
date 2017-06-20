<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <s:url var="authUrl" value="/j_spring_security_check"/>
    <div class="login-frame" >
        <form class="form-signin" action="/register" method="post">
            <h2 class="form-signin-heading">Регистрация</h2>
            <div class="form-group">
                <label for="inputEmail" class="sr-only">Эл. почта</label>
                <input name="email" id="inputEmail" class="form-control" placeholder="Эл. почта" required autofocus/>
            </div>
            <div class="form-group">
                <label for="inputFIO" class="sr-only">Ф.И.О</label>
                <input name="fio" type="fio" id="inputFIO" class="form-control" placeholder="Ф.И.О" required>
            </div>

            <div class="form-group">
                <label for="inputPassword" class="sr-only">Пароль</label>
                <input name="Password" type="password"  id="inputPassword" class="form-control" placeholder="Пароль" required/>
            </div>

            <div class="form-group">
                <label for="confirmPassword" class="sr-only">Подтверждение пароля</label>
                <input name="confirmPassword" type="password" id="confirmPassword" class="form-control" placeholder="Подтверждение пароля" required/>
            </div>
            <div class="form-group">
                <label for="roleId" class="sr-only">Пароль</label>
                <select name="roleId" class="form-control" id="roleId">
                    <c:forEach var="role" items="${roles}">
                        <option value="${role.id}">${role.description}</option>
                    </c:forEach>
                </select>
            </div>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Зарегистрироваться</button>
            <a class="btn btn-lg btn-info btn-block" href="/register">Отмена</a>

        </form>
    </div>
</div> <!-- /container -->
