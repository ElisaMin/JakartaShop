<%--
  Created by IntelliJ IDEA.
  User: Heizi
  Date: 2021/9/17
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/bootstrap-x.css">
    <title>购物车</title>
</head>
<body class="bg-dark">
<div class="container-lg mt-5">
    <div class="card ">
        <div class="card-title">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a href="" class="nav-link">主页</a>
                </li>
                <li class="nav-item">
                    <a href="" class="nav-link">用户管理</a>
                </li>
                <li class="nav-item">
                    <a href="" class="nav-link">商品添加</a>
                </li>
                <li class="nav-item">
                    <a href="" class="nav-link active">商品管理</a>
                </li>
                <li class="nav-item">
                    <a href="" class="nav-link">订单管理</a>
                </li>
            </ul>
        </div>
        <div class="card-body">
            <table class="table table-hover table-striped m-0">
                <thead class="bg-light">
                <tr>
                    <th scope="col" colspan="2">名称</th>
                    <th scope="col" >单价(元)</th>
                    <th scope="col" >数量</th>
                    <th scope="col" >操作</th>
                </tr>
                </thead>
                <tbody>

                <tr>
                    <th scope="row"></th>
                    <td>蟑螂奶 环保 新品</td>
                    <td>16.00</td>
                    <td><input style="width: 3rem;" value="1" type="number"></td>
                    <td><button type="button" class="btn btn-link">删除</button></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>