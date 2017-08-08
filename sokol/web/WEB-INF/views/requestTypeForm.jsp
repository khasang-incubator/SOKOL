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
        <sf:form method="post" commandName="requestType" action="${requestType.id}" cssClass="form-horizontal">
            <div class="form-body">

                <div class="form-group">
                    <%--ID типа запроса--%>
                    <label class="control-label col-sm-3"><s:message code="id_request_type"/></label>
                    <div class="col-sm-8">
                        <p class="form-control-static">${requestType.id}</p>
                    </div>
                </div>

                <div class="form-group">
                    <%--Название--%>
                    <label for="inputTitle"  class="control-label col-sm-3"><s:message code="title"/></label>
                    <div class="col-sm-8">
                        <input name="title" id="inputTitle" class="form-control" placeholder="<s:message code="request_type_title"/>" value="${requestType.title}" required autofocus/>
                    </div>
                </div>
                <div class="form-group">
                    <%--Департамент--%>
                    <label for="inputDepartment" class="control-label col-sm-3"><s:message code="department"/></label>
                    <div class="col-sm-8">
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

                <div class="form-group">
                    <%--Описание--%>
                    <label for="inputDescription"  class="control-label col-sm-3"><s:message code="desc"/></label>
                    <div class="col-sm-8">
                        <textarea  name="description" id="inputDescription" placeholder="<s:message code="request_type_desc"/>" class="form-control" rows="3" >${requestType.description}</textarea>
                    </div>
                </div>

                <div class="form-group">
                    <div class="control-label col-sm-3"></div>
                    <div class="col-sm-8">

                        <a href="#" onclick="document.forms['requestType'].submit();" class="btn-save pull-left"><s:message code="save"/></a>

                        <a href="/requestType/list" class="btn-close pull-right"><s:message code="close"/></a>
                    </div>
                </div>
            </div>
        </sf:form>
        <c:choose>
            <c:when test="${requestType.id == ''}"> <%-- новый тип запроса --%>
            </c:when>
            <c:when test="${requestType.id != ''}"> <%-- редактирование типа запроса --%>
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
    </div>
    <!-- /FORM -->
</div>



