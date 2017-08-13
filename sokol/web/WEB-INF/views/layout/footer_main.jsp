<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

<!-- Footer -->
<footer>
    <div class="footer">
        <nav class="navbar navbar-default  navbar-static-bottom" role="navigation">
            <div class="container">
                <div class="row text-center">
                    <div class="col-lg-12">
                        <ul class="list-inline">
                            <li class="footer-menu-divider">&sdot;</li>
                            <li>
                                <%--ГЛАВНАЯ--%>
                                <a href="#about"><s:message code="main"/></a>
                            </li>
                            <li>
                                <%--НАС--%>
                                <a href="#about"><s:message code="about_us"/></a>
                            </li>
                            <li>
                                <%--СЕРВИСЫ--%>
                                <a href="#"><s:message code="services"/></a>
                            </li>
                            <li class="footer-menu-divider">&sdot;</li>
                        </ul>
                        <div class="social">
                            <a href="javascript: alert('Under construction')" class="net1"></a>
                            <a href="javascript: alert('Under construction')" class="net2"></a>
                            <a href="javascript: alert('Under construction')" class="net3"></a>
                            <a href="javascript: alert('Under construction')" class="net4"></a>
                        </div>
                        <p class="copyright text-muted small"> Sokol IT &copy; 2016</p>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</footer>