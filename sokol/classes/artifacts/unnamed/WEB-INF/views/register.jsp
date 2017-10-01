<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <s:url var="authUrl" value="/j_spring_security_check"/>
    <div class="login-frame" >
        <form class="form-signin" action="/register" method="post">
            <h2 class="form-signin-heading"><s:message code="reg_form"/></h2>
            <div class="form-group">
                <label for="inputEmail" class="sr-only"><s:message code="email"/></label>
                <input name="email" id="inputEmail" class="form-control" placeholder="<s:message code='email'/>" required autofocus/>
            </div>
            <div class="form-group">
                <label for="inputFIO" class="sr-only"><s:message code="initials"/></label>
                <input name="fio" type="fio" id="inputFIO" class="form-control" placeholder="<s:message code='initials'/>" required>
            </div>

            <div class="form-group">
                <label for="inputPassword" class="sr-only"><s:message code="password"/></label>
                <input name="Password" type="password"  id="inputPassword" class="form-control" placeholder="<s:message code='password'/>" required/>
            </div>

            <div class="form-group">
                <label for="confirmPassword" class="sr-only"><s:message code="confirm_password"/></label>
                <input name="confirmPassword" type="password" id="confirmPassword" class="form-control" placeholder="<s:message code='confirm_password'/>" required/>
            </div>
            <div class="form-group">
                <label for="roleId" class="sr-only"><s:message code="password"/></label>
                <select name="roleId" class="form-control" id="roleId">
                    <c:forEach var="role" items="${roles}">
                        <option value="${role.id}"><s:message code="${role.name}"/></option>
                    </c:forEach>
                </select>
            </div>

            <button class="btn btn-lg btn-primary btn-block" type="submit"><s:message code="register"/></button>
            <a class="btn btn-lg btn-info btn-block" href="/register"><s:message code="cancel"/></a>

        </form>
    </div>
</div> <!-- /container -->
