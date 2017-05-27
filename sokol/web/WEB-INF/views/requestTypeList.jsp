<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url var="root_url" value="/"/>
<div class="container">
    <div class="table-header">
        <a href="${root_url}requestType/add" class="create-btn btn-danger">НОВЫЙ</a>
    </div>

    <div class="table-wrapper">
        <table class="list-table">
            <thead>
            <th style="width: 5%">#</th>
            <th style="width: 30%">НАЗВАНИЕ</th>
            <th style="width: 30%">ОПИСАНИЕ</th>
            <th style="width: 25%">ДЕПАРТАМЕНТ</th>
            <th style="width: 10%">УДАЛИТЬ</th>
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