<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="/js/Chart.min.js"></script>
<script type="text/javascript">
    function displayLeftLineChart() {
        var data = {

            labels: [${graphDataIn_Date}],
            datasets: [
                {
                    label: "My Second dataset",
                    fillColor: "rgba(212,253,80,0.2)",
                    strokeColor: "rgba(212,253,80,1)",
                    pointColor: "rgba(212,253,80,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(151,187,205,1)",
                    data: [${graphDataIn_Count}]
                }
            ]
        };
        var ctx = document.getElementById("lineLeftChart").getContext("2d");
        var options = { };
        var lineChart = new Chart(ctx).Line(data, options);
    }

    function displayRightLineChart(){
        var data = {
            labels: [${graphDataOut_Date}],
            datasets: [
                {
                    label: "My Second dataset",
                    fillColor: "rgba(212,253,80,0.2)",
                    strokeColor: "rgba(212,253,80,1)",
                    pointColor: "rgba(212,253,80,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(151,187,205,1)",
                    data: [${graphDataOut_Count}]
                }
            ]
        };
        var ctx = document.getElementById("lineRightChart").getContext("2d");
        var options = { };
        var lineChart = new Chart(ctx).Line(data, options);
    }
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
        window.location = '/requestList/edit?idRequest=' + requestId+ '&pagenumber=1&sortBy=id&sortOrder=ASC&sortOrderHeader=ASC';
//        $('#myModal .modal-title').text("Изменить запрос");
//        $('#requestId').val($('#request-' + requestId).find('.request-id').val());
//        $('#title').val($('#request-' + requestId).find('.request-title').text());
//        $('#description').val($('#request-' + requestId).find('.request-description').text());
//        $('#myModal').modal("show");
    }
    $(document).ready(function () {
        displayLeftLineChart();
        displayRightLineChart();
    });
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

                <span class="col-header"><s:message code="status"/></span>
                <%--<span class="col-header">ВХОДЯЩИЕ</span>--%>

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
                        <div class="box">
                            <canvas id="lineLeftChart" height="300" width="400"></canvas>
                        </div>
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
                                    <td><a href="javascript: onEditRequest(${request.requestId})">${request.title}</a></td>
                                    <td>${request.status.requestStatusName}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>

            <div class="col col-md-6  mypanel-col right">
                <span class="col-header">ИСХОДЯЩИЕ</span>

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
                        <div class="box">
                            <canvas id="lineRightChart" height="300" width="400"></canvas>
                        </div>
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
                                    <td><a href="javascript: onEditRequest(${request.requestId})">${request.title}</a></td>
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