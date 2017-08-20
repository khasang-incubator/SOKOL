<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="/" var="root" />
<div class="main" id="main-screen">
    <nav class="navbar navbar-default navbar-static-top topnav sokol" role="navigation">
        <div class="container topnav">
            <!-- Brand and toggle get grouped for better mobile display -->
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="enter">
                        <a href="${root}login">ВОЙТИ</a>
                    </li>
                    <li>
                        <a href="${root}register">РЕГИСТРАЦИЯ</a>
                    </li>
                    <li>
                        <select class="selectpicker lang rus" style="height: 30px" data-width="70px" onchange="document.location= '/?mylocale='+this.value">
                            <option class="language-bar rus" onclick="alert('eng')" value="ru"></option>
                            <option class="language-bar eng" selected="1" onclick="alert('eng')" value="en"></option>
                        </select>

                    </li>

                </ul>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="#main-screen">ГЛАВНАЯ</a>
                        </li>
                        <li><a href="#our-services">О НАС</a></li>
                        <li><a href="#our-services" >СЕРВИСЫ</a></li>
                    </ul>
                </div>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

</div>
<div class="services">
    <div class="row yellow-line text-center ">
        <div class="col-centered text">СЕРВИСЫ</div>
    </div>

    <div class="our-services" id="our-services" class="min-width: 1200px;">
        <div class="header row text-center">
            <span >МЫ ПРЕДЛАГАЕМ</span>
        </div>
        <div class="row-fluid text-center" style="min-width: 1000px;">
            <div style="display:inline-block; width:1000px;min-width:1000px;">
                <div class="col-md-4 col1">
                    <p>Мы предлагаем эффективное решения для контроля рабочих процессов внутри предприятия из одних рук. Это позволяет предоставить ряд конкурентных преимуществ, посредством увеличения эффективности Ваших подразделений.</p>
                </div>
                <div class="col-md-4 col2">
                    <p> Мы предлагаем эффективное решения для контроля рабочих процессов внутри предприятия из одних рук. Это позволяет предоставить ряд конкурентных преимуществ, посредством увеличения эффективности Ваших подразделений. </p>
                </div>
                <div class="col-md-4 col3">
                    <p> Мы предлагаем эффективное решения для контроля рабочих процессов внутри предприятия из одних рук. Это позволяет предоставить ряд конкурентных преимуществ, посредством увеличения эффективности Ваших подразделений. </p>
                </div>
            </div>
        </div>
    </div>

</div>

<div class="try-it">
    <div class="row text-center try-it-now">
        ИСПЫТАЙ <B>СОКОЛ</B> ПРЯМО СЕЙЧАС!
    </div>

    <div class="row text-center">
        <a href="${root}register" class="enter">ВОЙТИ
            <span>В ТЕСТОВОМ РЕЖИМЕ</span>
        </a>
    </div>


</div>
