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
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

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
                    <label for="inputLogin" class="control-label col-sm-3"><s:message code="id_employee"/></label>
                    <div class="col-sm-8">
                        <p class="form-control-static">${user.id}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputLogin" class="control-label col-sm-3"><s:message code="login"/></label>
                    <div class="col-sm-8">
                        <input name="login" id="inputLogin" class="form-control" placeholder="<s:message code='login'/>"
                               value="${user.login}" required autofocus/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputFio" class="control-label col-sm-3"><s:message code="fio_employee"/></label>
                    <div class="col-sm-8">
                        <input name="fio" id="inputFio" class="form-control" placeholder="<s:message code='fio_employee'/>"
                               value="${user.fio}" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail" class="control-label col-sm-3"><s:message code="email"/></label>
                    <div class="col-sm-8">
                        <input name="email" id="inputEmail" class="form-control" placeholder="<s:message code='email'/>"
                               value="${user.email}" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail" class="control-label col-sm-3"><s:message code="password"/></label>
                    <div class="col-sm-8">
                        <input name="password" type="password" id="inputPassoword" class="form-control"
                               placeholder="Пароль" value="12345" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputConfirmPassword" class="control-label col-sm-3"><s:message code="confirm_password"/></label>
                    <div class="col-sm-8">
                        <input name="confirmPassword" type="password" id="inputConfirmPassword" class="form-control"
                               placeholder="<s:message code='confirm_password'/>" value="" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="roleId" class="control-label col-sm-3"><s:message code="role"/></label>
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
                    <label for="inputDepartment" class="control-label col-sm-3"><s:message code="department"/></label>
                    <div class="col-sm-8">
<%--                        <select name="departmentId" class="form-control" id="inputDepartment">
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
                        </select>--%>

                        <select name="departmentId" id="inputDepartment" class="form-control">
                            <c:forEach items="${departments}" var="department">
                                <c:if test="${department.deleted == false}">
                                    <option value="${department.id}">
                                        <c:out value="${department.title}"/>
                                    </option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <%--<div class="form-group">--%>
                    <%--<label for="roleId" class="control-label col-sm-3"><s:message code="language"/></label>--%>
                    <%--<div class="col-sm-8">--%>
                        <%--<select name="language" class="form-control" id="language">--%>
                            <%--<option value="RU">Русский</option>--%>
                            <%--<option value="EN">English</option>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                <%--</div>--%>



                <div class="form-group">
                    <div class="control-label col-sm-3"></div>
                    <div class="col-sm-8">

                        <a href="#" onclick="document.forms['userForm'].submit();"
                           class="btn-save pull-left"><s:message code="save"/></a>
                        <a href="/users/list" class="btn-close pull-right"><s:message code="close"/></a>
                    </div>
                </div>

            </div>
        </sf:form>

        <c:choose>
            <c:when test="${user.id == ''}"> <%-- новый пользователь --%>
            </c:when>
            <c:when test="${user.id != ''}"> <%-- редактирование поьзователя--%>
                <div class="audit-info">
                    <table>
                        <tr>
                                <%--АВТОР--%>
                            <th><s:message code="author"/></th>
                            <td>${user.createdBy}</td>
                                <%--ДАТА СОЗДАНИЯ--%>
                            <th><s:message code="date_of_creation"/></th>
                            <td>${user.createdDate}</td>
                        </tr>
                        <tr>
                                <%--ИЗМЕНЕНО--%>
                            <th><s:message code="changed"/></th>
                            <td>${user.updatedBy}</td>
                                <%--ДАТА ИЗМЕНЕНИЯ--%>
                            <th><s:message code="date_of_change"/></th>
                            <td>${user.updatedDate}</td>
                        </tr>
                    </table>
                </div>
            </c:when>
        </c:choose>
    </div>
    <!-- /FORM -->
</div>