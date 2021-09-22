<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Heizi
  Date: 2021/9/22
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/bootstrap-x.css">
    <title>订单维护</title>
</head>
<body class="bg-dark" >
<div class="container-lg mt-5">
    <div class="card ">
        <%@include file="admin-card-header.jsp"%>
        <div class="card-body">
            <table class="table table-hover table-striped m-0">
                <thead class="bg-light">
                <tr>
                    <th scope="col" >序号</th>
                    <th scope="col" >类型</th>
                    <th scope="col">名称/手机/用户名</th>
                    <th scope="col">数量</th>
                    <th scope="col">价格</th>
                    <th colspan="3" ></th>
                </tr>
                </thead>
                <tbody>

                <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.6.0.min.js"></script>
                <script>
                    function showResult(result, msgBf){
                        alert((msgBf ? msgBf : "执行") + (result.status == 200 ? "成功" : "失败")+"\n" +
                            "原因:"+result.responseText)
                    }
                    function send(url,method,data,msg) {
                        $.ajax(url,{method:method,data:data,complete(resp) {
                            showResult(resp,msg)
                        }})
                    }
                    function getOid(inner) {return inner.parentElement.parentElement.attributes["oid"].value;}
                    function out(self) {
                        let data = {}
                        data[self.name] = self.value
                        send("sub/"+getOid(self),"PUT",data)
                    }
                </script>
                <c:forEach var="order" items="${orders}">
                    <tr oid="${order.id}" s="0">
                        <th scope="row">${order.id}</th>
                        <td>订单</td>
                        <th>${order.user.name}</th>
                        <td></td>
                        <td>${order.price}</td>
                        <td><c:choose>
                            <c:when test="${order.isDone}"><button type="button" class="btn btn-link mx-1">删除</button>|<button type="button" class="btn btn-link mx-1" >完成</button></c:when>
                            <c:otherwise>订单已完成</c:otherwise>
                        </c:choose></td>
                    </tr>
                    <c:forEach items="${order.subOrders}" var="sub" >
                        <tr oid="${sub.id}" s="1">
                            <th scope="row">${sub.id}</th>
                            <td>${sub.product.type.name}</td>
                            <th>${sub.product.name}</th>
                            <td><input type="number" style="width: 4rem;" name="amt" value="${sub.amount}" onfocusout="out(this)"></td>
                            <td>${sub.price}</td>
                            <td><button type="button" class="btn btn-link">删除</button></td>
                        </tr>
                    </c:forEach>
                </c:forEach>
                <script>
                    //TODO 绑定所有OnClick之类的
                    $("td>button").click(()=>{
                        let self = $(this)
                        var url,method
                        let isSub = self.parentElement.parentElement.attributes.s.value
                        if (isSub == 1) url = "sub"
                        if (isSub == 0) url = "order"
                        if (!url) return ;else url+="/"+getOid(self)
                        if (self.innerText === "删除") method="DELETE"
                        if (self.innerText === "完成") method="PUT"
                        if (!method) return;
                        send(url,method)
                    })
                </script>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>