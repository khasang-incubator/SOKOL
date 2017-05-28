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
                <input type="hidden" name="requestId" value="${request.requestId}">
                <input type="hidden" name="pageNumber" value="${pagingParameters.pageNumber}">
                <input type="hidden" name="sortBy" value="${pagingParameters.sortBy}">
                <input type="hidden" name="sortOrder" value="${pagingParameters.sortOrder}">
                <input type="hidden" name="sortOrderHeader" value="${pagingParameters.sortOrderHeader}">

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
                        <select name="requestTypeId" id="inputType" class="form-control">
                            <c:forEach items="${requestTypeAll}" var="requestType">
                                <c:if test="${requestType.id == request.requestType.id}">
                                    <option value="${requestType.id}" selected>
                                        <c:out value="${requestType.title}"/>
                                    </option>
                                </c:if>
                                <c:if test="${requestType.id != request.requestType.id}">
                                    <option value="${requestType.id}">
                                        <c:out value="${requestType.title}"/>
                                    </option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputDepartment" class="control-label col-sm-3">Департамент</label>
                    <div class="col-sm-8">
                        <select name="departmentId" id="inputDepartment" class="form-control">
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
                        <select name="requestStatusId" id="inputStatus" class="form-control">
                            <c:set value="${requestStatusAll}" var="requestStatus"/>
                            <c:choose>
                                <c:when test="${request.status.requestStatusId == '1'}"> <%--статус "новый"--%>
                                    <option value="${requestStatus.get(0).requestStatusId}" selected>
                                        <c:out value="${requestStatus.get(0).requestStatusName}"/> <%--"новая"--%>
                                    </option>
                                    <option value="${requestStatus.get(1).requestStatusId}">
                                        <c:out value="${requestStatus.get(1).requestStatusName}"/> <%--"в работе"--%>
                                    </option>
                                </c:when>
                                <c:when test="${request.status.requestStatusId == '2'}"> <%--статус "в работе"--%>
                                    <option value="${requestStatus.get(1).requestStatusId}" selected>
                                        <c:out value="${requestStatus.get(1).requestStatusName}"/>
                                    </option>
                                    <option value="${requestStatus.get(2).requestStatusId}">
                                        <c:out value="${requestStatus.get(2).requestStatusName}"/>
                                    </option>
                                    <option value="${requestStatus.get(3).requestStatusId}">
                                        <c:out value="${requestStatus.get(3).requestStatusName}"/>
                                    </option>
                                </c:when>
                                <c:when test="${request.status.requestStatusId == '3'}"> <%--статус "закрыта"--%>
                                    <option value="${requestStatus.get(1).requestStatusId}">
                                        <c:out value="${requestStatus.get(1).requestStatusName}"/>
                                    </option>
                                    <option value="${requestStatus.get(2).requestStatusId}" selected>
                                        <c:out value="${requestStatus.get(2).requestStatusName}"/>
                                    </option>
                                    <option value="${requestStatus.get(4).requestStatusId}">
                                        <c:out value="${requestStatus.get(4).requestStatusName}"/>
                                    </option>
                                </c:when>
                                <c:when test="${request.status.requestStatusId == '4'}"> <%--статус "отклонена"--%>
                                    <option value="${requestStatus.get(3).requestStatusId}" selected>
                                        <c:out value="${requestStatus.get(3).requestStatusName}"/>
                                    </option>
                                    <option value="${requestStatus.get(4).requestStatusId}">
                                        <c:out value="${requestStatus.get(4).requestStatusName}"/>
                                    </option>
                                </c:when>
                                <c:when test="${request.status.requestStatusId == '5'}"> <%--статус "на доработку"--%>
                                    <option value="${requestStatus.get(1).requestStatusId}">
                                        <c:out value="${requestStatus.get(1).requestStatusName}"/>
                                    </option>
                                    <option value="${requestStatus.get(4).requestStatusId}" selected>
                                        <c:out value="${requestStatus.get(4).requestStatusName}"/>
                                    </option>
                                     </c:when>
                            </c:choose>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3">Приложение</label>
                    <div class="col-sm-8">
                        <input type="file" name="attachedFile">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-3">
                    </div>
                    <div class="col-sm-8">
                        <c:set value="${request.fileName}" var="fileName"/>
                        <c:choose>
                            <c:when test="${fileName == ''}"> <%--нет файла для скачивания--%>
                                <a title="Скачать"
                                   href="#">
                                    Нет файла для скачивания
                                </a>
                            </c:when>

                            <c:when test="${fileName != ''}">
                                <a title="Скачать"
                                   href="/requestList/download?requestId=${request.requestId}">
                                    <c:out value="${fileName}"/>
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
                            <a href="/requestList/assignedTo?requestId=${request.requestId}"
                               class="btn-work pull-left">ВЗЯТЬ В РАБОТУ</a>
                        </c:if>
                        <a href="/requestList/list?pageNumber=${pagingParameters.pageNumber}&sortBy=${pagingParameters.sortBy}
                                &sortOrder=${pagingParameters.sortOrder}&sortOrderHeader=${pagingParameters.sortOrderHeader}"
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

