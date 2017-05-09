<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<c:if test="${errorMessage != null}">
    <div class="alert alert-danger">
            ${errorMessage}
    </div>
</c:if>
<div class="container">
    <!-- FORM  -->
    <div class="form-wrapper">
        <sf:form method="post" action="/requestList/edit" id="requestForm" cssClass="form-horizontal"
                 enctype="multipart/form-data">
            <div class="form-body">
                <input type="hidden" name="idrequest" value="${request.requestId}">
                <input type="hidden" name="pageNumber" value="${pageNumber}">
                <input type="hidden" name="sortBy" value="${sortBy}">
                <input type="hidden" name="sortOrder" value="${sortOrder}">
                <input type="hidden" name="sortOrderHeader" value="${sortOrderHeader}">
                <div class="form-group">
                    <label class="control-label col-sm-3">Номер запроса</label>
                    <div class="col-sm-4">
                        <p class="form-control-static">${request.requestId}</p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputTitle" class="control-label col-sm-3">Название</label>
                    <div class="col-sm-8">
                        <input name="title" id="inputTitle" class="form-control" placeholder="Название запроса"
                               value="${request.title}" required autofocus/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputType" class="control-label col-sm-3">Тип запроса</label>
                    <div class="col-sm-8">
                        <select name="idrequesttypes" id="inputType" class="form-control">
                            <c:forEach items="${requestTypeAll}" var="requesttype">
                                <c:if test="${requesttype.id == request.requestType.id}">
                                    <option value="${requesttype.id}" selected>
                                        <c:out value="${requesttype.title}"/>
                                    </option>
                                </c:if>
                                <c:if test="${requesttype.id != request.requestType.id}">
                                    <option value="${requesttype.id}">
                                        <c:out value="${requesttype.title}"/>
                                    </option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputDepartment" class="control-label col-sm-3">Департамент</label>
                    <div class="col-sm-8">
                        <select name="iddepartment" id="inputDepartment" class="form-control">
                            <c:forEach items="${departmentAll}" var="department">
                                <c:if test="${department.id == request.department.id}">
                                    <option value="${department.id}" selected>
                                        <c:out value="${department.title}"/>
                                    </option>
                                </c:if>
                                <c:if test="${department.id != request.department.id}">
                                    <option value="${department.id}">
                                        <c:out value="${department.title}"/>
                                    </option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputStatus" class="control-label col-sm-3">Статус запроса</label>
                    <div class="col-sm-8">
                        <select name="idrequeststatus" id="inputStatus" class="form-control">
                            <c:set value="${requestStatusAll}" var="requeststatus"/>
                            <c:choose>
                                <c:when test="${request.status.requestStatusId == '1'}"> <%--статус "новый"--%>
                                    <option value="${requeststatus.get(0).requestStatusId}" selected>
                                        <c:out value="${requeststatus.get(0).requestStatusName}"/> <%--"новая"--%>
                                    </option>
                                    <option value="${requeststatus.get(1).requestStatusId}">
                                        <c:out value="${requeststatus.get(1).requestStatusName}"/> <%--"в работе"--%>
                                    </option>
                                </c:when>
                                <c:when test="${request.status.requestStatusId == '2'}"> <%--статус "в работе"--%>
                                    <option value="${requeststatus.get(1).requestStatusId}" selected>
                                        <c:out value="${requeststatus.get(1).requestStatusName}"/>
                                    </option>
                                    <option value="${requeststatus.get(2).requestStatusId}">
                                        <c:out value="${requeststatus.get(2).requestStatusName}"/>
                                    </option>
                                    <option value="${requeststatus.get(3).requestStatusId}">
                                        <c:out value="${requeststatus.get(3).requestStatusName}"/>
                                    </option>
                                </c:when>
                                <c:when test="${request.status.requestStatusId == '3'}"> <%--статус "закрыта"--%>
                                    <option value="${requeststatus.get(1).requestStatusId}">
                                        <c:out value="${requeststatus.get(1).requestStatusName}"/>
                                    </option>
                                    <option value="${requeststatus.get(2).requestStatusId}" selected>
                                        <c:out value="${requeststatus.get(2).requestStatusName}"/>
                                    </option>
                                    <option value="${requeststatus.get(4).requestStatusId}">
                                        <c:out value="${requeststatus.get(4).requestStatusName}"/>
                                    </option>
                                </c:when>
                                <c:when test="${request.status.requestStatusId == '4'}"> <%--статус "отклонена"--%>
                                    <option value="${requeststatus.get(3).requestStatusId}" selected>
                                        <c:out value="${requeststatus.get(3).requestStatusName}"/>
                                    </option>
                                    <option value="${requeststatus.get(4).requestStatusId}">
                                        <c:out value="${requeststatus.get(4).requestStatusName}"/>
                                    </option>
                                </c:when>
                                <c:when test="${request.status.requestStatusId == '5'}"> <%--статус "на доработку"--%>
                                    <option value="${requeststatus.get(1).requestStatusId}">
                                        <c:out value="${requeststatus.get(1).requestStatusName}"/>
                                    </option>
                                    <option value="${requeststatus.get(4).requestStatusId}" selected>
                                        <c:out value="${requeststatus.get(4).requestStatusName}"/>
                                    </option>
                                     </c:when>
                            </c:choose>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3">Приложение</label>
                    <div class="col-sm-8">
                        <input type="file" name="file">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-3">
                    </div>
                    <div class="col-sm-8">
                        <c:set value="${request.file_name}" var="filename"/>
                        <c:choose>
                            <c:when test="${filename == ''}"> <%--нет файла для скачивания--%>
                                <a title="Скачать"
                                   href="#">
                                    Нет файла для скачивания
                                </a>
                            </c:when>

                            <c:when test="${filename != ''}">
                                <a title="Скачать"
                                   href="/requestList/download?idRequest=${request.requestId}">
                                    <c:out value="${filename}"/>
                                </a>
                            </c:when>
                        </c:choose>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputDescription" class="control-label col-sm-3">Описание</label>
                    <div class="col-sm-8">
                        <textarea name="description" id="inputDescription" placeholder="Описание типа запроса"
                                  class="form-control" rows="3">${request.description}</textarea>
                    </div>
                </div>

                <div class="form-group">
                    <div class="control-label col-sm-3"></div>
                    <div class="col-sm-8">
                        <c:if test="${request.status.requestStatusId == '1' && request.assignedTo.id == null}"> <%--статус "новый" и запрос никому не назначе--%>
                            <a href="/requestList/assignedTo?idRequest=${request.requestId}"
                               class="btn-work pull-left">ВЗЯТЬ В РАБОТУ</a>
                        </c:if>
                        <a href="/requestList/list?pageNumber=${pageNumber}&sortBy=${sortBy}&sortOrder=${sortOrder}&sortOrderHeader=${sortOrderHeader}"
                           class="btn-close pull-right">ЗАКРЫТЬ</a>
                        <a href="#" onclick="document.forms['requestForm'].submit();" class="btn-save pull-right">СОХРАНИТЬ</a>
                    </div>
                </div>
            </div>
        </sf:form>
        <div class="audit-info">
            <table>
                <tr>
                    <th>АВТОР</th>
                    <td>${request.createdBy}</td>
                    <th>ДАТА СОЗДАНИЯ</th>
                    <td>${request.createdDate}</td>
                </tr>
                <tr>
                    <th>ИЗМЕНЕНО</th>
                    <td>${request.updatedBy}</td>
                    <th>ДАТА ИЗМЕНЕНИЯ</th>
                    <td>${request.updatedDate}</td>
                </tr>
            </table>
        </div>
    </div>
    <!-- /FORM -->
</div>

