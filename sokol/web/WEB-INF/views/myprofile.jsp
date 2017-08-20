<%--
  Created by IntelliJ IDEA.
  User: denspbru
  Date: 19.04.17
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
        <sf:form method="post" id="myprofile" action="/myprofile" cssClass="form-horizontal">
            <input type="hidden" id="id" name="id" value="${user.id}">
            <div class="form-body">
                <div class="form-group">
                    <label  class="control-label col-sm-3"><s:message code="id_employee"/></label>
                    <div class="col-sm-8">
                        <p class="form-control-static">${user.id}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label  class="control-label col-sm-3"><s:message code="login"/></label>
                    <div class="col-sm-8">
                        <p class="form-control-static">${user.login}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputFio" class="control-label col-sm-3"><s:message code="fio_employee"/></label>
                    <div class="col-sm-8">
                        <input name="fio" id="inputFio" class="form-control" placeholder="<s:message code='fio_employee'/>" value="${user.fio}" required autofocus/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail" class="control-label col-sm-3"><s:message code="email"/></label>
                    <div class="col-sm-8">
                        <input name="email" id="inputEmail" class="form-control" placeholder="<s:message code='email'/>" value="${user.email}" required />
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail" class="control-label col-sm-3"><s:message code="password"/></label>
                    <div class="col-sm-8">
                        <input name="password" type="password" id="inputPassoword" class="form-control" placeholder="<s:message code='password'/>" value="12345" required />
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputConfirmPassword" class="control-label col-sm-3"><s:message code="confirm_password"/></label>
                    <div class="col-sm-8">
                        <input name="confirmPassword" type="password" id="inputConfirmPassword" class="form-control" placeholder="<s:message code='confirm_password'/>" value="" required />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3"><s:message code="role"/></label>
                    <div class="col-sm-8">
                        <p class="form-control-static">${user.role.description}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputDepartmen" class="control-label col-sm-3"><s:message code="department"/> </label>
                    <div class="col-sm-8">
                        <select name="departmentId"  class="form-control" id="inputDepartmen">
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
                    <label for="language" class="control-label col-sm-3"><s:message code="language"/></label>
                    <div class="col-sm-8">
                        <select name="language" class="form-control" id="language">
                            <option value="RU">Русский</option>
                            <option value="EN">English</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="control-label col-sm-3"></div>
                    <div class="col-sm-8">

                        <a href="#" onclick="document.forms['myprofile'].submit();" class="btn-save pull-left"><s:message code="save"/></a>
                        <a href="/users/list" class="btn-close pull-right"><s:message code="close"/></a>
                    </div>
                </div>

            </div>
        </sf:form>
        <div class="audit-info">
            <table>
                <tr>
                    <%--АВТОР--%>
                    <th><s:message code="author"/></th>
                    <td>${requestType.createdBy}</td>
                    <%--ДАТА СОЗДАНИЯ--%>
                    <th><s:message code="date_of_creation"/></th>
                    <td>${requestType.createdDate}</td>
                </tr>
                <tr>
                    <%--ИЗМЕНЕНО--%>
                    <th><s:message code="changed"/></th>
                    <td>${requestType.updatedBy}</td>
                    <%--ДАТА ИЗМЕНЕНИЯ--%>
                    <th><s:message code="date_of_change"/></th>
                    <td>${requestType.updatedDate}</td>
                </tr>
            </table>
        </div>
    </div>
    <!-- /FORM -->
</div>