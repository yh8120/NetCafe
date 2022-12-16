<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<title>設定画面</title>
</head>
<body>
    <header class="navbar navbar-expand-lg navbar-light bg-light mb-2">
        <div class="container-fluid">
            <h1 class="navbar-brand">店舗設定</h1>

        </div>
    </header>
    <div class="container"></div>
    <footer class="navbar justify-content-end navbar-expand navbar-dark bg-secondary fixed-bottom">
        <ul class="navbar-nav">
            <li class="nav-item me-2"><a href="listRoom" class="btn btn-primary">ルーム管理</a></li>
            <li class="nav-item me-2"><a href="listUser" class="btn btn-primary">従業員管理</a></li>
            <li class="nav-item me-2"><a href="" class="btn btn-primary">料金管理</a></li>
            <li class="nav-item me-2"><a href="" class="btn btn-primary">商品管理</a></li>
            <li class="nav-item me-2"><a href="manager" class="btn btn-light">入室管理</a></li>
            <li class="nav-item me-2"><a href="logout" id="logout" class="btn btn-danger">ログアウト</a></li>
        </ul>
    </footer>
    <script src="js/jquery-3.6.1.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>

</body>
</html>