<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<c:url var="root_url" value="/"/>
<div class="container">
    <div class="table-header">
        <%--НОВЫЙ--%>
        <a href="${root_url}department/add" class="create-btn btn-danger"><s:message code="new"/></a>
    </div>

    <div class="table-wrapper">
        <table class="list-table">
            <thead>
            <th>#</th>
            <%--НАЗВАНИЕ--%>
            <th><s:message code="title"/></th>
            <%--СОЗДАНО--%>
            <th><s:message code="created"/></th>
            <%--УДАЛИТЬ--%>
            <th><s:message code="remove"/></th>
            </thead>
            <tbody>
            <c:forEach items="${departmentList}" var="list" step="1" varStatus="loopStatus">
                <tr class="${loopStatus.index % 2 == 0 ? 'alt' : ''}">
                    <c:if test="${list.deleted == false}">
                        <td><c:out value="${list.id}"/></td>
                        <td><a href="/department/${list.id}"> <c:out value="${list.title}"/></a></td>
                        <td><c:out value="${list.createdDate}"/></td>
                        <td class="del-cell"><a class="del-btn" href="/department/delete/${list.id}"
                                                onclick="return confirmDeletion(${list.id})"></a></td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="table-footer">

        </div>
    </div>

</div>
