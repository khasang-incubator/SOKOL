<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${errorMessage != null}">
    <div class="alert alert-danger">
            ${errorMessage}
    </div>
</c:if>
<div class="container">
    <!-- FORM  -->
    <div class="form-wrapper">
        <sf:form method="post" commandName="requestType" action="${requestType.id}" cssClass="form-horizontal">
            <div class="form-body">

                <div class="form-group">
                    <label class="control-label col-sm-3">ID</label>
                    <div class="col-sm-8">
                        <p class="form-control-static">${requestType.id}</p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputTitle"  class="control-label col-sm-3">Название</label>
                    <div class="col-sm-8">
                        <input name="title" id="inputTitle" class="form-control" placeholder="Название подразделения" value="${requestType.title}" required autofocus/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputDescrition"  class="control-label col-sm-3">Описание</label>
                    <div class="col-sm-8">
                        <textarea  name="description" id="inputDescrition" placeholder="Описание типа запроса" class="form-control" rows="3" >${requestType.description}</textarea>
                    </div>
                </div>

                <div class="form-group">
                    <div class="control-label col-sm-3"></div>
                    <div class="col-sm-8">

                        <a href="#" onclick="document.forms['requestType'].submit();" class="btn-save pull-left">СОХРАНИТЬ</a>
                        <a href="/requestType/list" class="btn-close pull-right">ЗАКРЫТЬ</a>
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