<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<c:if test="${errorMessage != null}">
    <div class="alert alert-danger">
            ${errorMessage}
    </div>
</c:if>
<c:url var="root_url" value="/"/>
<div class="container">
    <!-- FORM  -->
    <div class="form-wrapper">
        <sf:form method="post" id="departmentForm" action="/department/${department.id}" cssClass="form-horizontal">
            <%--action="/users/${user.id}"--%>
            <div class="form-body">

                <div class="form-group">
                    <%--ID департамента--%>
                    <label class="control-label col-sm-3"><s:message code="id_department"/></label>
                    <div class="col-sm-8">
                        <p class="form-control-static">${department.id}</p>
                    </div>
                </div>
                <div class="form-group">
                    <%--Название--%>
                    <label for="inputTitle" class="control-label col-sm-3"><s:message code="title"/></label>
                    <div class="col-sm-8">
                        <input name="title" id="inputTitle" class="form-control" placeholder="<s:message code="department_title"/>"
                               value="${department.title}" required autofocus/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="control-label col-sm-3"></div>
                    <div class="col-sm-8">

                        <a href="#" onclick="document.forms['departmentForm'].submit();" class="btn-save pull-left"><s:message code="save"/></a>
                        <a href="/department/list" class="btn-close pull-right"><s:message code="close"/></a>
                    </div>
                </div>
            </div>
        </sf:form>
        <c:choose>
            <c:when test="${department.id == ''}"> <%-- новый тип запроса --%>
            </c:when>
            <c:when test="${department.id != ''}"> <%-- редактирование типа запроса --%>
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
            </c:when>
        </c:choose>
        <!-- /FORM -->
    </div>
</div>