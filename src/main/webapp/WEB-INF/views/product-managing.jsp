<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/bootstrap-x.css">
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.6.0.min.js"></script>
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
            <small>离开输入即可修改参数</small>
            <script>

                function showResult(result, msgBf){
                    alert((msgBf ? msgBf : "执行") + (result.status == 200 ? "成功" : "失败")+"\n" +
                        "原因:"+result.responseText)
                }
                function getPid(inner) {return inner.parentElement.parentElement.attributes["p"].value;}
                function onDelete(self){
                    $.ajax("",{method: "DELETE",data:{id:getPid(self)},complete(resp){
                            showResult(resp,"删除")
                            window.location.reload()
                        }})
                }
                function inputFocusOut(self) {
                    let data = {};
                    data["pid"] = getPid(self)
                    data[self.name] = self.value
                    $.ajax("",{
                        method:"PUT",
                        data:data,
                        complete:showResult
                    })

                }
            </script>
            <script>
            </script>
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
                <%--@elvariable id="products" type="java.util.List"--%>
                <%--@elvariable id="p" type="me.heizi.jsp.shop.entities.Product"--%>
                <c:forEach items="${products}" var="p" >
                    <tr p="${p.id}">
                        <th scope="row"></th>
                        <td>${p.name}</td>
                        <td><input value="${p.price}" type="number" name="prc" onfocusout="inputFocusOut(this)"></td>
                        <td><input style="width: 3rem;" value="${p.quantity}" type="number" name="qtt" onfocusout="inputFocusOut(this)"  ></td>
                        <td><button type="button" class="btn btn-link" onclick="onDelete(this)">删除</button></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="card-footer" >
            <script>
                let n = window.location.href.split("/")
                const path = n[n.length-1]
                n=void 0
                function step(isNext) {
                    window.location.href = isNext? n+1 : n-1
                }
            </script>
            <button class="btn btn-outline-secondary" onclick="step(true)">下一页</button>
            <button class="btn btn-outline-secondary" onclick="step(false)">下一页</button>
        </div>
    </div>
</div>
</body>
</html>