<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<sec:authentication var="user" property="principal"/>

<c:if test="${errorMessage != null}">
    <div class="alert alert-danger">
            ${errorMessage}
    </div>
</c:if>

<div class="container">
    <!-- FORM  -->
    <div class="form-wrapper">
        <sf:form method="post" id="requestForm" action="/requestList/add"
                 cssClass="form-horizontal" enctype="multipart/form-data">
            <div class="form-body">
                <input type="hidden" name="creator" value="${user.getUsername()}"/>
                <input type="hidden" name="pageNumber" value="${pagingParameters.pageNumber}">
                <input type="hidden" name="sortBy" value="${pagingParameters.sortBy}">
                <input type="hidden" name="sortOrder" value="${pagingParameters.sortOrder}">
                <input type="hidden" name="sortOrderHeader" value="${pagingParameters.sortOrderHeader}">
                <div class="form-group">
                    <%--Название--%>
                    <label for="inputTitle" class="control-label col-sm-3"><s:message code="title"/></label>
                    <div class="col-sm-8">
                        <input name="title" id="inputTitle" class="form-control" placeholder="<s:message code="request_title"/>"
                               required autofocus/>
                    </div>
                </div>

                <div class="form-group">
                    <%--Тип запроса--%>
                    <label for="inputType" class="control-label col-sm-3"><s:message code="request_type"/></label>
                    <div class="col-sm-8">
                        <select name="requestTypeId" id="inputType" class="form-control">
                            <c:forEach items="${requestTypeAll}" var="requestType">
                                <option value="${requestType.id}">
                                    <c:out value="${requestType.title}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <%--Приложение--%>
                    <label class="control-label col-sm-3">Приложение</label>
                    <div class="col-sm-8">
                        <input type="file" name="attachedFile">
                    </div>
                </div>

                <div class="form-group">
                    <%--Описание--%>
                    <label for="inputDescription" class="control-label col-sm-3"><s:message code="desc"/></label>
                    <div class="col-sm-8">
                        <textarea name="description" id="inputDescription" placeholder="<s:message code="request_type_desc"/>"
                                  class="form-control" rows="3"></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <div class="control-label col-sm-3"></div>
                    <div class="col-sm-8">
                        <a href="#" onclick="document.forms['requestForm'].submit();" class="btn-save pull-left">СОХРАНИТЬ</a>
                        <a href="/requestList/list?pageNumber=${pagingParameters.pageNumber}&sortBy=${pagingParameters.sortBy}
                                    &sortOrder=${pagingParameters.sortOrder}&sortOrderHeader=${pagingParameters.sortOrderHeader}"
                           class="btn-close pull-right">ЗАКРЫТЬ</a>
                    </div>
                </div>
            </div>
        </sf:form>
    </div>
    <!-- /FORM -->
</div>
















