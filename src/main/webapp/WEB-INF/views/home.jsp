<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Heizi
  Date: 2021/1/5
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>购物网站</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-x.css">
    <style>
        .card-item {
            width: 13rem;
            height: 16rem;
            padding: 0;
        }
        .card-item> .card-img {
            height: 9rem;
        }
        .card-item>.card-body {
            padding-top: 0.5rem;
            padding-bottom: 0.5rem;
        }
        .row-overflow {
            /* overflow-x: scroll; */
        }
    </style>
</head>
<body class="bg-light">
<div class="">
    <nav class="navbar navbar-expand-sm navbar-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">EsBuy</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarID"
                    aria-controls="navbarID" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarID">
                <div class="navbar-nav">
                    <a class="nav-link active" aria-current="page" href="#">主页</a>
                </div>
                <div class="navbar-nav">
                    <a href="" class="nav-link">登入</a>
                </div>
                <div class="navbar-nav">
                    <a href="" class="nav-link">注册</a>
                </div>
            </div>
        </div>
    </nav>
    <main class="container-lg">
        <div class="row ">
            <menu class=" card col-3  m-0">

            </menu>
            <div class="card col-9 overflow-hidden" style="height: 20rem;">
                <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"></li>
                        <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"></li>
                        <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"></li>
                    </ol>
                    <div class="carousel-inner">
                        <div class="carousel-item">
                            <img src="..." class="d-block w-100" alt="...">
                            <div class="bg-dark"><h1>123</h1></div>
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </a>
                </div>
            </div>
        </div>
        <div class="row rounded-2 p-3 mt-3 bg-secondary justify-content-between">
            <div class="col-2">
                <h3 class="text-white">今日特惠</h3>
            </div>
        </div>
        <div class="row mt-3 d-flex row-overflow ">
            <%--@elvariable id="list" type="java.util.List"--%>
            <c:forEach items="${list}" var="p">
                <div class="card card-item mx-1" onclick="goto(${p.id})">
                    <div class="card-img bg-success">

                    </div>
                    <div class="card-body">
                        <div class="row .card-title overflow-hidden flex-nowrap">
                            ${p.name}
                        </div>
                        <!-- <div class="row text-secondary">
                            中科大
                        </div> -->
                        <div class="row justify-content-center mt-1 ">
                            <div class="fit-width p-0 ">
                                <h3 class="text-danger d-inline">${p.price} 元</h3>
                            </div>
                        </div>
                        <div class="row text-secondary mt-2">
                            <div class="badge bg-primary fit-width me-1 ">● 月销 ${p.sold}</div>
                            <div class="badge bg-primary fit-width ">● 钦州</div>
                            <c:if test="${p.hit==1}">
                                <div class="badge bg-danger fit-width ">● 火!</div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>

    </main>
</div>
<script src="js/bootstrap.min.js"></script>
<script>
    function goto(id) {
        window.location.href="info?id="+id;
    }
</script>
</body>
</html>