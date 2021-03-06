<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript">
    function onSearchClick(){
        var searchStr = $("#idSearch").val();
        document.location.href = "/requestList/list?pageNumber=1&sortBy=id&sortOrder=desc&findText=" + searchStr;
    }
</script>

<c:url var="root_url" value="/"/>
<div class="container">
    <div class="table-header">
        <%--НОВЫЙ--%>
        <a href="${root_url}requestList/add?pageNumber=${pageNumber}&sortBy=${sortBy}&sortOrder=${sortOrder}&sortOrderHeader=${sortOrderHeader}"
           class="create-btn btn-danger"><s:message code="new"/></a>

        <div style="display: inline-block; width: 300px; float: right; margin-top: -5px;">
               <div class="input-group">
                    <input type="text" class="form-control" id="idSearch" placeholder="<s:message code="search"/>">
                    <div class="input-group-btn">
                        <%--Поиск--%>
                        <a class="btn btn-default" title="<s:message code="search"/>"
                           href="#" onclick="onSearchClick(); return false;" role="button">
                            <i class="glyphicon glyphicon-search"></i>
                        </a>
                    </div>
               </div>
        </div>

    </div>

    <div class="table-wrapper">
        <table class="list-table">
            <thead>

            <th style="width: 3%">#</th>
            <th style="width: 8%">
                <%--СТАТУС--%>
                <a href="/requestList/list?pageNumber=${pageNumber}&sortBy=status&sortOrder=${sortOrderHeader}"
                   class="${sortBy.equals('status')? imgBy : ''}"><s:message code="status"/></a>
            </th>
            <th style="width: 14%">
                <%--НАЗВАНИЕ--%>
                <a href="/requestList/list?pageNumber=${pageNumber}&sortBy=title&sortOrder=${sortOrderHeader}&findText=${findText}"
                   class="${sortBy.equals('title')? imgBy : ''}"><s:message code="title"/></a>
            </th>
            <th style="width: 14%">
                <%--ОПИСАНИЕ--%>
               <a href="/requestList/list?pageNumber=${pageNumber}&sortBy=description&sortOrder=${sortOrderHeader}&findText=${findText}"
                   class="${sortBy.equals('description')? imgBy : ''}"><s:message code="desc"/></a>
            </th>
            <th style="width: 8%">
                <%--СОЗДАТЕЛЬ--%>
                <a href="/requestList/list?pageNumber=${pageNumber}&sortBy=createdBy&sortOrder=${sortOrderHeader}"
                   class="${sortBy.equals('createdBy')? imgBy : ''}"><s:message code="author"/></a>
            </th>
            <th style="width: 10%">
                <%--ИСПОЛНИТЕЛЬ--%>
                <a href="/requestList/list?pageNumber=${pageNumber}&sortBy=assignedTo&sortOrder=${sortOrderHeader}"
                   class="${sortBy.equals('assignedTo')? imgBy : ''}"><s:message code="responsible"/></a>
            </th>
            <th style="width: 8%">
                <%--ТИП--%>
                <a href="/requestList/list?pageNumber=${pageNumber}&sortBy=requestType&sortOrder=${sortOrderHeader}"
                   class="${sortBy.equals('requestType')? imgBy : ''}"><s:message code="type"/></a>
            </th>
            <%--УДАЛИТЬ--%>
            <th style="width: 5%"><s:message code="remove"/></th>

            </thead>
            <tbody>
            <c:forEach items="${requestAll}" var="lists" step="1" varStatus="loopStatus">

                <tr class="${loopStatus.index % 2 == 0 ? 'alt' : ''}">
                    <td><c:out value="${lists.requestId}"/></td>
                    <td><c:out value="${lists.status.requestStatusName}"/></td>
                    <td>
                        <a title="Редактирование запроса"
                           <%--href="/requestList/edit?requestId=${lists.requestId}&pageNumber=${pageNumber}&sortBy=${sortBy}&sortOrder=${sortOrder}&sortOrderHeader=${sortOrderHeader}">--%>
                            href="/requestList/${lists.requestId}?pageNumber=${pageNumber}&sortBy=${sortBy}&sortOrder=${sortOrder}&sortOrderHeader=${sortOrderHeader}">
                            <c:out value="${lists.title}"/>
                        </a>
                    </td>
                    <td><c:out value="${lists.description}"/></td>
                    <td><c:out value="${lists.createdBy}"/></td>
                    <td><c:out value="${lists.assignedTo.fio}"/></td>
                    <td><c:out value="${lists.requestType.title}"/></td>

                    <sec:authorize access="hasAnyRole('ROLE_USER')">
                        <c:if test="${(lists.createdBy == userName)||((numberDepartmentByUser == lists.requestType.department.id))}">
                            <td class="del-cell"><a class="del-btn" href="/requestList/delete?requestId=${lists.requestId}"
                                                    onclick="return confirmDeletion(${lists.requestId}, 'запрос')"></a></td>
                        </c:if>
                        <c:if test="${(!(lists.createdBy == userName))&&(!(numberDepartmentByUser == lists.requestType.department.id))}">
                            <td class="del-cell"><span class="del-no-btn" href="#"></span></td>
                        </c:if>
                    </sec:authorize>

                    <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                        <td class="del-cell"><a class="del-btn" href="/requestList/delete?requestId=${lists.requestId}"
                                                onclick="return confirmDeletion(${lists.requestId}, 'запрос')"></a></td>
                    </sec:authorize>




                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="table-footer">
            <table>
                <tr>
                    <%--Страницы--%>
                    <td><s:message code="pages"/>:</td>
                    <td>
                        <ul class="pagination">
                            <li>
                                <a href="" aria-label="Previous"><span aria-hidden="true">&laquo</span></a>
                            </li>
                            <c:forEach items="${pageTotal}" var="pageNumber" step="1">
                                <li>
                                    <a href="/requestList/list?pageNumber=${pageNumber.intValue()}&sortBy=${sortBy}&sortOrder=${sortOrder}&findText=${findText}">
                                        <c:out value="${pageNumber.intValue()}"/>
                                    </a>
                                </li>
                            </c:forEach>
                            <li>
                                <a href="" aria-label="Previous"><span aria-hidden="true">&raquo</span></a>
                            </li>
                        </ul>

                    </td>
                </tr>
            </table>
        </div>
    </div>

</div>

