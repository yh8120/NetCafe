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
<title>管理画面</title>
</head>
<body>
<div class="container">
<h1>ログインしました</h1>
<p>
<a href="manager" class="btn btn-primary">管理システム</a>
<a href="setting" class="btn btn-primary">店舗設定</a>
<a href="" class="btn btn-primary">売上集計</a>
</p>
<p>
<a href="logout" id="logout" class="btn btn-danger">ログアウト</a>
</p>
</div>
<script src="js/jquery-3.6.1.min.js"></script>
  <script src="js/bootstrap.bundle.min.js"></script>
  <script>
      $(document).ready(function() {
        $("#logout").click(function() {
          return confirm("ログアウトしますか？");
        });
      });
    </script>
</body>
</html>