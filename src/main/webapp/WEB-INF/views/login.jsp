<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Heizi
  Date: 2021/1/5
  Time: 22:28
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
    <title>登入</title>
</head>
<body>
<div class="container p-5">
    <div class="h1 mt-5 mb-2">登入</div>
    <div class="card ">
        <div class="card-body"> <form method="post" class=" needs-validation" novalidate>
            <div class="mb-3">
                <label for="username" class="form-label">用户名</label>
                <input type="text" class="form-control" id="username" placeholder="name@example.com" name="username" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">密码</label>
                <input type="password" name="password" id="password" class="form-control" required>
            </div>
            <div class="mb-3 row justify-content-between">
                <div class="col-3">
                    <button type=submit class="btn btn-outline-primary"  >登入</button>
                    <button type="button" class="btn btn-outline-secondary" onclick="window.location.href='resign'">注册</button>
                </div>
                <div class="col-2">
                    <div class="btn btn-link">忘记密码?</div>
                </div>
            </div>
        </form> </div>
    </div>

    <c:choose>
        <%--@elvariable id="status" type="Int"--%>
        <c:when test="${status == -1}">
            <div class="row"> 
                <div class="row mb-3" >
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        用户名或密码错误
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </div>
            </div>
        </c:when>
        <c:when test="${status == 1}">
            <div class="row">
                <div class="row mb-3" >
                    <div class="alert alert-success alert-dismissible fade show" role="alert">成功!
                        <script>
                            setTimeout(()=>{window.location.href="/"},3000)
                        </script>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </div>
            </div>
        </c:when>
    </c:choose>

</div>
</body>
</html>