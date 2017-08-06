<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<c:url var="root_url" value="/"/>
<div class="container">
    <div class="table-header">
        <%--НОВЫЙ--%>
        <a href="${root_url}requestType/add" class="create-btn btn-danger"><s:message code="new"/></a>
    </div>

    <div class="table-wrapper">
        <table class="list-table">
            <thead>
            <th style="width: 5%">#</th>
            <%--НАЗВАНИЕ--%>
            <th style="width: 30%"><s:message code="title"/></th>
            <%--ОПИСАНИЕ--%>
            <th style="width: 30%"><s:message code="desc"/></th>
            <%--ДЕПАРТАМЕНТ--%>
            <th style="width: 25%"><s:message code="department"/></th>
            <%--УДАЛИТЬ--%>
            <th style="width: 10%"><s:message code="remove"/></th>
            </thead>
            <tbody>
            <c:forEach items="${requestTypes}" var="list" step="1" varStatus="loopStatus">
                <tr class="${loopStatus.index % 2 == 0 ? 'alt' : ''}">
                    <td><c:out value="${list.id}"/></td>
                    <td><a href="/requestType/${list.id}"> <c:out value="${list.title}"/></a></td>
                    <td><c:out value="${list.description}"/></td>
                    <td><c:out value="${list.department.title}"/></td>
                    <td class="del-cell"><a class="del-btn" href="/requestType/delete/${list.id}"
                                            onclick="return confirmDeletion(${list.id}, 'тип запроса')"></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>