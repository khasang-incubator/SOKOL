<%--
  Created by IntelliJ IDEA.
  User: denspbru
  Date: 13.11.16
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${errorMessage != null}">
    <div class="alert alert-danger">
            ${errorMessage}
    </div>
</c:if>
<div class="container">
    <!-- FORM  -->
    <div class="form-wrapper">
        <sf:form method="post" id="userForm" action="/users/${user.id}" cssClass="form-horizontal">
            <div class="form-body">
                <div class="form-group">
                    <label for="inputLogin" class="control-label col-sm-3">ID сотрудника</label>
                    <div class="col-sm-8">
                        <p class="form-control-static">${user.id}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputLogin" class="control-label col-sm-3">Логин</label>
                    <div class="col-sm-8">
                        <input name="login" id="inputLogin" class="form-control" placeholder="Имя пользователя"
                               value="${user.login}" required autofocus/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputFio" class="control-label col-sm-3">ФИО сотрудника</label>
                    <div class="col-sm-8">
                        <input name="fio" id="inputFio" class="form-control" placeholder="ФИО сотрудника"
                               value="${user.fio}" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail" class="control-label col-sm-3">Эл. почта</label>
                    <div class="col-sm-8">
                        <input name="email" id="inputEmail" class="form-control" placeholder="Эл. почта"
                               value="${user.email}" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail" class="control-label col-sm-3">Пароль</label>
                    <div class="col-sm-8">
                        <input name="password" type="password" id="inputPassoword" class="form-control"
                               placeholder="Пароль" value="12345" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputConfirmPassword" class="control-label col-sm-3">Подтверждение пароля</label>
                    <div class="col-sm-8">
                        <input name="confirmPassword" type="password" id="inputConfirmPassword" class="form-control"
                               placeholder="Подтверждение пароля" value="" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="roleId" class="control-label col-sm-3">Роль</label>
                    <div class="col-sm-8">
                        <select name="roleId" class="form-control" id="roleId">
                            <c:forEach var="role" items="${roles}">
                                <c:choose>
                                    <c:when test="${role.id == user.role.id}">
                                        <option value="${role.id}" selected="1">${role.description}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${role.id}">${role.description}</option>
                                    </c:otherwise>
                                </c:choose>

                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputDepartmen" class="control-label col-sm-3">Подразделение</label>
                    <div class="col-sm-8">
                        <select name="departmentId" class="form-control" id="inputDepartmen">
                            <c:forEach var="department" items="${departments}">
                                <c:choose>
                                    <c:when test="${department.id == user.department.id}">
                                        <option value="${department.id}" selected="1">${department.title}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${department.id}">${department.title}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="control-label col-sm-3"></div>
                    <div class="col-sm-8">

                        <a href="#" onclick="document.forms['userForm'].submit();"
                           class="btn-save pull-left">СОХРАНИТЬ</a>
                        <a href="/users/list" class="btn-close pull-right">ЗАКРЫТЬ</a>
                    </div>
                </div>

            </div>
        </sf:form>

        <c:choose>
            <c:when test="${user.id == ''}"> <%-- новый тип запроса --%>
            </c:when>
            <c:when test="${user.id != ''}"> <%-- редактирование типа запроса --%>
                <div class="audit-info">
                    <table>
                        <tr>
                            <th>АВТОР</th>
                            <td>${user.createdBy}</td>
                            <th>ДАТА СОЗДАНИЯ</th>
                            <td>${user.createdDate}</td>
                        </tr>
                        <tr>
                            <th>ИЗМЕНЕНО</th>
                            <td>${user.updatedBy}</td>
                            <th>ДАТА ИЗМЕНЕНИЯ</th>
                            <td>${user.updatedDate}</td>
                        </tr>
                    </table>
                </div>
            </c:when>
        </c:choose>
    </div>
    <!-- /FORM -->
</div>