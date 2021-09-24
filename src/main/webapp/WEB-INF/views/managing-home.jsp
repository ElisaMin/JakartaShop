<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/bootstrap-x.css">
    <title>首页</title>
</head>
<body class="bg-dark">
    <div class="container mt-5">
        <div class="row  justify-content-center">
            <div class="col-2">
                <h1 class="text-white fit-width d-inline">欢迎回来</h1>
            </div>
        </div>
        <div class="row justify-content-center mt-3">
            <div class="col-5">
                <div class="row">
                    <button class="btn btn-outline-warning mb-2" href="user" >用户管理</button>
                    <button class="btn btn-outline-success mb-2" href="append" >添加商品</button>
                    <button class="btn btn-outline-success mb-2" href="product/1" >管理商品</button>
                    <button class="btn btn-outline-info mb-2" href="order/1" >订单管理</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        document.querySelectorAll("button").forEach((e)=>{
            e.onclick = ()=>{window.location.href += e.attributes.href.value}

        })
    </script>
</body>
</html>