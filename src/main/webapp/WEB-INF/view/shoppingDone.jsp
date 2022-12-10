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
<title>ルーム販売</title>
</head>
<body style="padding: 0rem 0rem 5rem">
<h1>ルーム販売商品に設定しました</h1>
    <div class="container">
        <div class="row">
            <div class="col">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">商品ID</th>
                            <th scope="col">商品名</th>
                            <th scope="col">個数</th>
                            <th scope="col">金額</th>
                        </tr>
                    </thead>
                    <tbody id="shoppingCartBody">
                        <c:forEach items="${cartArray}" var="cart">
                            <tr>
                                <th scope="row"><c:out value="${cart.productId}" /></th>
                                <td>
                                    <c:out value="${cart.productName}" />
                                </td>
                                <td>
                                    <c:out value="${cart.productUnit}" />
                                </td>
                                <td>
                                    <c:out value="${cart.totalPrice}" />
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                                <th scope="row" colspan="3">合計</th>
                                <td>
                                    <span ><c:out value="${sumCartPrice}" /></span>円
                                </td>
                                <td></td>
                            </tr>
                    </tbody>
                </table>


            </div>
        </div>
    </div>

    <footer class="navbar justify-content-end navbar-expand navbar-dark bg-secondary fixed-bottom">
        <ul class="navbar-nav">
            <li class="nav-item"><a href="manager" class="btn btn-danger me-2">戻る</a></li>
        </ul>
    </footer>

    <script src="js/jquery-3.6.1.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>

</body>
</html>
