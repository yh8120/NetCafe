<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<title>従業員管理</title>
</head>
<body class="pb-5">
    <header class="navbar navbar-expand-lg navbar-light bg-light mb-2">
        <div class="container-fluid">
            <h1 class="navbar-brand">従業員管理</h1>

        </div>
    </header>
    <div class="container">
        <h1>ユーザーリスト</h1>
        <div class="row">
            <div class="col">
                <c:if test="${not empty message }">
                    <div class="error-message">
                        <c:out value="${message}"></c:out>
                    </div>
                </c:if>
                <table class="table table-bordered">
                    <tr>
                        <th>ID</th>
                        <th>名前</th>
                        <th>ログインID</th>
                        <th>権限</th>
                        <th colspan="2">操作</th>
                    </tr>
                    <tr>
                        <c:forEach items="${userList}" var="user">
                            <tr>
                                <td>
                                    <c:out value="${user.userId }" />
                                </td>
                                <td>
                                    <c:out value="${user.userName }" />
                                </td>
                                <td>
                                    <c:out value="${user.loginId }" />
                                </td>
                                <td>
                                    <c:out value="${user.userClassName }" />
                                </td>
                                <td>
                                    <a href="updateUser?userId=<c:out value="${user.userId }"/>">更新</a>
                                </td>
                                <td>
                                    <a href="deleteUser?userId=<c:out value="${user.userId }"/>">削除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tr>
                </table>
                <a href="addUser" class="btn btn-success">従業員追加</a>
            </div>
        </div>
    </div>
    <footer class="navbar justify-content-end navbar-expand navbar-dark bg-secondary fixed-bottom">
        <ul class="navbar-nav">
            <li class="nav-item me-2"><a href="listRoom" class="btn btn-primary">ルーム管理</a></li>
            <li class="nav-item me-2"><button disabled class="btn btn-light">従業員管理</button></li>
            <li class="nav-item me-2"><a href="listPricePlan" class="btn btn-primary">料金管理</a></li>
            <li class="nav-item me-2"><a href="listProduct" class="btn btn-primary">商品管理</a></li>
            <li class="nav-item me-2"><a href="manager" class="btn btn-warning">入室管理</a></li>
            <li class="nav-item me-2"><a href="logout" id="logout" class="btn btn-danger">ログアウト</a></li>
        </ul>
    </footer>
    <script src="js/jquery-3.6.1.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
