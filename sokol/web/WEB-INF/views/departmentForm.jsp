<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<c:if test="${errorMessage != null}">
    <div class="alert alert-danger">
            ${errorMessage}
    </div>
</c:if>
<c:url var="root_url" value="/"/>
<div class="container">
    <!-- FORM  -->
    <div class="form-wrapper">
        <%--<sf:form method="post" id="departmentForm" action="${root_url}department/add"  cssClass="form-horizontal">--%>
            <sf:form method="post" id="departmentForm" action="/department/${department.id}"  cssClass="form-horizontal">
            <%--action="/users/${user.id}"--%>
        <div class="form-body">

            <div class="form-group">
                <label class="control-label col-sm-3">ID подразделения</label>
                <div class="col-sm-8">
                    <p class="form-control-static">${department.id}</p>
                </div>
            </div>

            <div class="form-group">
                <label for="inputTitle"  class="control-label col-sm-3">Название</label>
                <div class="col-sm-8">
                    <input name="title" id="inputTitle" class="form-control" placeholder="Название подразделения" value="${department.title}" required autofocus/>
                </div>
            </div>

            <div class="form-group">
                <div class="control-label col-sm-3"></div>
                <div class="col-sm-8">

                    <a href="#" onclick="document.forms['departmentForm'].submit();" class="btn-save pull-left">СОХРАНИТЬ</a>
                    <a href="/department/list" class="btn-close pull-right">ЗАКРЫТЬ</a>
                </div>
            </div>


        </div>
        </sf:form>
        <div class="audit-info">
            <table>
                <tr>
                    <th>АВТОР</th>
                    <td>Кузнецов Денис</td>
                    <th>ДАТА СОЗДАНИЯ</th>
                    <td>01.01.2019 12:78:21</td>
                </tr>
                <tr>
                    <th>ИЗМЕНЕНО</th>
                    <td>Кузнецов Денис</td>
                    <th>ДАТА ИЗМЕНЕНИЯ</th>
                    <td>01.01.2019 12:78:02</td>
                </tr>
            </table>
        </div>
    </div>
    <!-- /FORM -->
</div>