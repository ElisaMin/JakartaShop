<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Heizi
  Date: 2021/9/2
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${isLoginSuccess}">
  <script>alert("注册成功");window.history.back()</script>
</c:if>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <title>注册</title>
</head>
<body>
<div class="container-lg mt-5">
  <c:if test="${isGoToHome==true}">
    <script>setTimeout(()=>{window.history.back()},500)</script>
  </c:if>
  <c:if test="${responseMessage!=null}">
    <div class="alert alert-success alert-dismissible fade show" role="alert">
      ${responseMessage}

      <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
  </c:if>
  <div class="card">
    <div class="card-header">
      <div class="card-title"><h3>注册会员</h3></div>
    </div>

    <div class="card-body">
      <form action="" class="" method="post">
        <div class="input-group row mt-3">
          <input type="text" class=" col-4 form-control" placeholder="昵称" name="names"  required>
        </div>
        <div class="input-group row mt-3 ">
          <input type="password" class=" col-4 form-control" placeholder="密码" name="password" id="password" required>
        </div>
        <div class="input-group row mt-3">
          <input type="text" class=" col-4 form-control" placeholder="手机" name="phone" id="phone" required>
          <div class="col-2">
            <button onclick="return true" class="btn btn-outline-primary" >注册</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
<script src="js/bootstrap.min.js"></script>
</body>
</html>