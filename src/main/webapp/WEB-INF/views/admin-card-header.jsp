<%--
  Created by IntelliJ IDEA.
  User: Heizi
  Date: 2021/9/21
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="card-title">
    <ul class="nav nav-tabs" id="nav-tab-admin">

        <li class="nav-item">
            <a href="../" class="nav-link">主页</a>
        </li>
        <li class="nav-item">
            <a href="user" class="nav-link">用户管理</a>
        </li>
        <li class="nav-item">
            <a href="append" class="nav-link">商品添加</a>
        </li>
        <li class="nav-item">
            <a href="product/1" class="nav-link active">商品管理</a>
        </li>
        <li class="nav-item">
            <a href="order" class="nav-link">订单管理</a>
        </li>
    </ul>
</div>
<script>
    (()=>{let p = window.location.pathname.split("/")
        let lock;
        let before = ''
        for (let x of p) {
            if (lock === true) before+="../"
            if (x === "managing") {
                lock = false
                continue
            }
            if (lock===false) lock = true
        }
        for (let a of document.querySelectorAll("#nav-tab-admin>*>a")) {
            a.href = before+a.attributes.href.value
        }})()

</script>