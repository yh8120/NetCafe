<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<title>商品管理</title>
</head>
<body class="pb-5">
    <header class="navbar navbar-expand-lg navbar-light bg-light mb-2">
        <div class="container-fluid">
            <h1 class="navbar-brand">商品管理</h1>

        </div>
    </header>
    <div class="container">
        <div class="row">
            <div class="col">
                <table class="table table-bordered">
                    <tr>
                        <th>ID</th>
                        <th>名前</th>
                        <th>価格<br>(￥)</th>
                        <th>商品タイプ</th>
                        <th>内税<br>(％)
                        </th>
                        <th>登録</th>
                        <th>更新</th>
                        <th colspan="2">操作</th>
                    </tr>
                    <tr>
                        <c:forEach items="${productList}" var="product">
                            <tr>
                                <td>
                                    <c:out value="${product.productId }" />
                                </td>
                                <td>
                                    <c:out value="${product.productName }" />
                                </td>
                                <td>
                                    <c:out value="${product.productPrice}" />
                                </td>
                                <td>
                                    <c:out value="${product.productTypeName}" />
                                </td>
                                <td>
                                    <c:out value="${product.taxRate}" />
                                </td>
                                <td>
                                    <fmt:formatDate value="${product.registered}" pattern="y-M-d HH:MM" />
                                </td>
                                <td>
                                    <fmt:formatDate value="${product.updated}" pattern="y-M-d HH:MM" />
                                </td>
                                <td>
                                    <a href="updateProduct?productId=<c:out value="${product.productId }"/>">更新</a>
                                </td>
                                <td>
                                    <a class="deleteProduct" href="deleteProduct?productId=<c:out value="${product.productId }"/>">削除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tr>
                </table>
                <a href="addProduct" class="btn btn-success">商品登録</a>
            </div>
        </div>
    </div>
    <footer class="navbar justify-content-end navbar-expand navbar-dark bg-secondary fixed-bottom">
        <ul class="navbar-nav">
            <li class="nav-item me-2"><a href="listRoom" class="btn btn-primary">ルーム管理</a></li>
            <li class="nav-item me-2"><a href="listUser" class="btn btn-primary">従業員管理</a></li>
            <li class="nav-item me-2"><a href="listPricePlan" class="btn btn-primary">料金管理</a></li>
            <li class="nav-item me-2"><button disabled class="btn btn-light">商品管理</button></li>
            <li class="nav-item me-2"><a href="manager" class="btn btn-warning">入室管理</a></li>
            <li class="nav-item me-2"><a href="logout" id="logout" class="btn btn-danger">ログアウト</a></li>
        </ul>
    </footer>
    <script src="js/jquery-3.6.1.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script>
    $(document).ready(function() {
		$(".deleteProduct").click(function() {
			return confirm("本当に削除しますか？");
		});
	});
    </script>
</body>
</html>
