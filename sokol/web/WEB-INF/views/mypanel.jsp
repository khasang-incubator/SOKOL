<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script type="text/javascript">
    function onCreateRequest() {
        window.location = '/addRequestCreator'
//        $('#myModal .modal-title').text("Создать запрос");
//        $('#requestId').val('');
//        $('#title').val('');
//        $('#description').val('');
//
//        $('#myModal').modal("show");
    }

    function onEditRequest(requestId) {
        window.location = '/addRequestPerformer?idRequest=' + requestId;
//        $('#myModal .modal-title').text("Изменить запрос");
//        $('#requestId').val($('#request-' + requestId).find('.request-id').val());
//        $('#title').val($('#request-' + requestId).find('.request-title').text());
//        $('#description').val($('#request-' + requestId).find('.request-description').text());
//        $('#myModal').modal("show");
    }
</script>
<!--
<a href="#" class="btn btn-primary" id="create_request" onclick="onCreateRequest()">Создать запрос</a>
-->
<!-- ================================= -->
<div class="container">
    <!-- SCREEN  -->

    <div class="mypanel-wrapper">
        <div class="row">
            <div class="col col-md-6 mypanel-col">
                <span class="col-header" title="Запросы, назначенные мне в работу">ВХОДЯЩИЕ</span>

                <div class="panel panel-sokol">
                    <div class="panel-heading">КОЛИЧЕСТВО ЗАПРОСОВ</div>
                    <div class="panel-body">
                        <table class="score">
                            <tr>
                                <th>НОВЫЕ</th>
                                <th>ВЫПОЛНЯЮТСЯ</th>
                                <th>ВЫПОЛНЕНО</th>
                            </tr>
                            <tr>
                                <td>${scoreIn.countNew}</td>
                                <td>${scoreIn.countInProgress}</td>
                                <td>${scoreIn.countClosed}</td>
                            </tr>
                        </table>
                    </div>
                </div>

                <div class="panel panel-sokol">
                    <div class="panel-heading">ПРОИЗВОДИТЕЛЬНОСТЬ</div>
                    <div class="panel-body">
                        <a href="#" class="graph-btn">ЗА ДЕНЬ</a>
                        <a href="#" class="graph-btn active">ЗА НЕДЕЛЮ</a>
                        <a href="#" class="graph-btn">ЗА МЕСЯЦ</a>
                        <a href="#" class="graph-btn">ЗА ГОД</a>
                        <img src="img/graph.png" width="100%"/>
                    </div>
                </div>

                <div class="panel panel-sokol">
                    <div class="panel-heading">ЗАПРОСЫ</div>
                    <div class="panel-body">
                        <table class="list-table">
                            <thead>
                            <th><a href="#">ID</a></th>
                            <th><a href="#" class="sort-up">НАЗВАНИЕ</a></th>
                            <th><a href="#" class="sort-down">СТАТУС</a></th>
                            </thead>
                            <tbody>
                            <c:forEach var="request" items="${forMeRequests}" step="1" varStatus="loopStatus">
                                <tr class="${loopStatus.index % 2 == 0 ? 'alt' : ''}">
                                    <td class="id-cell">${request.requestId}</td>
                                    <td><a href="onEditRequest(${request.requestId})">${request.title}</a></td>
                                    <td>${request.status.requestStatusName}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>

            <div class="col col-md-6  mypanel-col right">
                <span class="col-header" title="Запросы, созданные мной">ИСХОДЯЩИЕ</span>

                <div class="panel panel-sokol">
                    <div class="panel-heading">КОЛИЧЕСТВО ЗАПРОСОВ</div>
                    <div class="panel-body">
                        <table class="score">
                            <tr>
                                <th>НОВЫЕ</th>
                                <th>ВЫПОЛНЯЮТСЯ</th>
                                <th>ВЫПОЛНЕНО</th>
                            </tr>
                            <tr>
                                <td>${scoreOut.countNew}</td>
                                <td>${scoreOut.countInProgress}</td>
                                <td>${scoreOut.countClosed}</td>
                            </tr>
                        </table>
                    </div>
                </div>

                <div class="panel panel-sokol">
                    <div class="panel-heading">ПРОИЗВОДИТЕЛЬНОСТЬ</div>
                    <div class="panel-body">
                        <a href="#" class="graph-btn">ЗА ДЕНЬ</a>
                        <a href="#" class="graph-btn active">ЗА НЕДЕЛЮ</a>
                        <a href="#" class="graph-btn">ЗА МЕСЯЦ</a>
                        <a href="#" class="graph-btn">ЗА ГОД</a>
                        <img src="img/graph.png" width="100%"/>
                    </div>
                </div>

                <div class="panel panel-sokol">
                    <div class="panel-heading">ЗАПРОСЫ</div>
                    <div class="panel-body">
                        <table class="list-table">
                            <thead>
                            <th><a href="#">ID</a></th>
                            <th><a href="#" class="sort-up">НАЗВАНИЕ</a></th>
                            <th><a href="#" class="sort-down">СТАТУС</a></th>
                            </thead>
                            <tbody>
                            <c:forEach var="request" items="${myRequests}" step="1" varStatus="loopStatus">
                                <tr class="${loopStatus.index % 2 == 0 ? 'alt' : ''}">
                                    <td class="id-cell">${request.requestId}</td>
                                    <td><a href="onEditRequest(${request.requestId})">${request.title}</a></td>
                                    <td>${request.status.requestStatusName}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>

    </div>
    <!-- /SCREEN -->
</div>
<!-- ================================= -->