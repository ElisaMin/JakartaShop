<%@ page import="me.heizi.jsp.shopShit.utils.FindCookieKt" %>
<%@ page import="me.heizi.jsp.shopShit.R" %><%--@elvariable id="p" type="me.heizi.jsp.shopShit.dao.entities.Product"--%>
<%--@elvariable id="status" type="Int"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Heizi
  Date: 2021/1/5
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <title>商品详细</title>
</head>
<body class="bg-light">
<div class="container-lg mt-5 rounded-3 shadow-lg p-0">
    <c:choose >

        <c:when test="${status == 1}">
            <div class="row mb-3" >
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    购物车加入成功
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </div>
        </c:when>
        <c:when test="${status=-1}">
            <div class="row mb-3" >
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    请登入!
                    <button type="button"  data-bs-dismiss="alert" >登入</button>
                </div>
            </div>
        </c:when>
    </c:choose>
    <div class="row p-0">
        <div class="col-7">
            <div class="row mt-2">
                <img src="img/${p.image}" class="w-100" alt="商品详细图">
            </div>
            <div class="row mt-2">
                <h6 class="text-secondary">商品描述:</h6>
            </div>
            <div class="row mt-2">
                ${p.info}
            </div>
        </div>
        <div class="col bg-white p-3 pt-5">
            <div class="row">
                <h6 class="text-secondary">商品名称:</h6>
            </div>
            <div class="row mx-3">
                ${p.name}
            </div>
            <div class="row mt-4">
                <h6 class="text-secondary">发货地点:</h6>
            </div>
            <div class="row mx-3">
                南宁
            </div>
            <div class="row mt-4">
                <h6 class="text-secondary">上架时间:</h6>
            </div>
            <div class="row mx-3">
                ${p.createTime}
            </div>
            <div class="row mt-4">
                <h6 class="text-secondary">月销量:</h6>
            </div>
            <div class="row mx-3">
                ${p.sold}
            </div>
            <div class="row mt-4">
                <h6 class="text-secondary">库存:</h6>
            </div>
            <div class="row mx-3">
                ${p.quantity}
            </div>
            <c:if test='<%=FindCookieKt.findCookie(request.getCookies(), R.cookie.id) != null%>'>
                <form method="post">
                    <input type="hidden" name="user_id" value='<%=FindCookieKt.findCookie(request.getCookies(), R.cookie.id)%>' >
                    <button name="id" value="${p.id}" type="submit" class=" mx-1 mt-5 row w-100 btn btn-outline-primary">加入购物车</button>
                </form>
            </c:if>
            <c:otherwise>
                <button class="mx-1 mt-5 row w-100 btn btn-outline-primary">登入</button>
            </c:otherwise>
        </div>
    </div>
</div>
</body>
</html>