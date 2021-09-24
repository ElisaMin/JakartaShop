<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Heizi
  Date: 2021/1/7
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-x.css">
    <title>购物车</title>
    <script>
        function put(value,id,price) {

        }
    </script>
</head>
<body>
<div class="container-lg mt-5">
    <div class="card ">
        <table class="table table-hover table-striped m-0">
            <thead class="bg-light">
            <tr>
                <th scope="col" colspan="2">名称</th>
                <th scope="col" >单价(元)</th>
                <th scope="col" >数量</th>
                <th scope="col" >小计</th>
                <th scope="col" >操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="p">
                <tr>
                    <th scope="row"></th>
                    <td>${p.product.name}</td>
                    <td>${p.product.price}</td>
                    <td><input onfocusout="put(this.value,${p.id},${p.product.price});" style="width: 3rem;" value="1" type="number"></td>
                    <td><a id="count"></a>></td>
                    <td>
                        <form  >
                            <input type="hidden" name="_method" value="DELETE">
                            <button name="id" value="${p.id}" type="submit" class="btn btn-link">删除</button>
                        </form>
                    </td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
        <div class="row justify-content-end mx-2 p-2 my-3">
            <div class="col-2">
                一共: <a id="sum"></a>
            </div>
            <div class="col-2"><form method="post"><button type="submit" class="btn btn-outline-primary">下单</button></form></div>
        </div>
    </div>
</div>
</body>
</html>