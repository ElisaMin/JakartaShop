<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Heizi
  Date: 2021/1/9
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <title>商品添加</title>
</head>
<body class="bg-dark">
<div class="container-lg mt-5">
    <div class="card">
        <div class="card-title bg-light">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a href="" class="nav-link">主页</a>
                </li>
                <li class="nav-item">
                    <a href="" class="nav-link">用户管理</a>
                </li>
                <li class="nav-item">
                    <a href="" class="nav-link active">商品添加</a>
                </li>
                <li class="nav-item">
                    <a href="" class="nav-link">商品管理</a>
                </li>
                <li class="nav-item">
                    <a href="" class="nav-link">订单管理</a>
                </li>
            </ul>
        </div>
        <div class="card-body">
            <form>
                <div class="mb-3">
                    <select class="form-select" aria-label="Default select example">
                        <c:forEach items="${t}" var="type">
                            <option selected value="${type.id}" >${type.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <input type="text" name="name" class="form-control" placeholder="商品名称">
                </div>
                <div class="mb-3 row">
                    <div class="col-3">
                        <input type="number" name="prc" class="form-control "  placeholder="价格">
                    </div>
                    <div class="col-3">
                        <input type="text" class="form-control "  placeholder="发货地">
                    </div>
                    <div class="col input-group">
                        <span class="input-group-text">商品图片</span>
                        <input type="hidden" name="pid">
                        <input type="file" id="pic" class="form-control" onclick="alert('未完成!~~')" >
                    </div>
                </div>
                <div class="mb-3">
                    <textarea class="form-control" name="det" rows="14"  placeholder="商品简介"  ></textarea>
                </div>
                <div class="mb-3">
                    <button type="submit" class="btn btn-primary">添加</button>
                    <button type="button" onclick="let a = document.getElementsByTagName('input'); for (i in a){a[i].value=null} ;false" class="btn btn-outline-primary">重置</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>