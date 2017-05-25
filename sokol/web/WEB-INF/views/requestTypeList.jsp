<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    function confirmDeletion(id) {
        if (confirm("Вы действительно хотите удалить тип запроса № " + id + " ?")) {
            return true;
        } else {
            return false;
        }
    }
</script>

<c:url var="root_url" value="/"/>
<div class="container">
    <div class="table-header">
        <a href="${root_url}requestType/add" class="create-btn btn-danger">НОВЫЙ</a>
    </div>

    <div class="table-wrapper">
        <table class="list-table">
            <thead>
            <th>#</th>
            <th style="width: 20%">НАЗВАНИЕ</th>
            <th style="width: 45%">ОПИСАНИЕ</th>
            <th>УДАЛИТЬ</th>
            </thead>
            <tbody>
            <c:forEach items="${requestTypes}" var="list" step="1" varStatus="loopStatus">
                <tr class="${loopStatus.index % 2 == 0 ? 'alt' : ''}">
                    <td><c:out value="${list.id}"/></td>
                    <td><a href="/requestType/${list.id}"> <c:out value="${list.title}"/></a></td>
                    <td><c:out value="${list.description}"/></td>
                    <td class="del-cell"><a class="del-btn" href="/requestType/delete/${list.id}"
                                            onclick="return confirmDeletion(${list.id})"></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>