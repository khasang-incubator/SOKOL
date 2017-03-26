<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    function confirm_delete(type_id) {
        if (confirm("you want to delete the user?")) {
            window.location = "/requestType/" + type_id + "/delete";
        } else {
            return false;
        }
        return true;
    }

    function confirmAction(id) {
        if (confirm("Вы действительно хотите удалить № " + id + " ?")) {
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

        <%--<div style="display: inline-block; width: 300px; float: right; margin-top: -5px;">--%>
        <%--<div class="input-group">--%>
        <%--<input type="text" class="form-control" placeholder="Поиск">--%>
        <%--<div class="input-group-btn">--%>
        <%--<button class="btn btn-default" type="submit">--%>
        <%--<i class="glyphicon glyphicon-search"></i>--%>
        <%--</button>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--</div>--%>
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
                        <%--<td class="del-cell"><a class="del-btn" href="return confirm_delete(${list.id})"></a></td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="table-footer">
            <%--<table>--%>
            <%--<tr>--%>
            <%--<td>Страницы:</td>--%>
            <%--<td>--%>
            <%--<ul class="pagination">--%>
            <%--<li>--%>
            <%--<a href="" aria-label="Previous"><span aria-hidden="true">«</span></a>--%>
            <%--</li>--%>

            <%--<li>--%>
            <%--<a href="#">1</a>--%>
            <%--</li>--%>

            <%--<li>--%>
            <%--<a href="#">2</a>--%>
            <%--</li>--%>

            <%--<li>--%>
            <%--<a href="" aria-label="Previous"><span aria-hidden="true">»</span></a>--%>
            <%--</li>--%>
            <%--</ul>--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--</table>--%>
        </div>
    </div>

</div>