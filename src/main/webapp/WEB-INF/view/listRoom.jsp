<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<title>施設管理</title>
</head>
<body class="pb-5">
    <header class="navbar navbar-expand-lg navbar-light bg-light mb-2">
        <div class="container-fluid">
            <h1 class="navbar-brand">ルーム管理</h1>

        </div>
    </header>
    <div class="container">
        <div class="row">
            <div class="col">
                <table class="table table-bordered">
                    <tr>
                        <th>順序</th>
                        <th>ID</th>
                        <th>名前</th>
                        <th>タイプ</th>
                        <th colspan="2">操作</th>
                    </tr>
                    <tr>
                        <c:forEach items="${roomList}" var="room">
                            <tr>
                                <td>
                                    <c:out value="${room.roomOrder }" />
                                </td>
                                <td>
                                    <c:out value="${room.roomId }" />
                                </td>
                                <td>
                                    <c:out value="${room.roomName }" />
                                </td>
                                <td>
                                    <c:out value="${room.roomTypeName }" />
                                </td>
                                <td>
                                    <a href="updateRoom?roomId=<c:out value="${room.roomId }"/>">更新</a>
                                </td>
                                <td>
                                    <a href="deleteRoom?roomId=<c:out value="${room.roomId }"/>">削除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tr>
                </table>
                <a href="addRoom" class="btn btn-success">ルーム作成</a>
            </div>
        </div>
    </div>
    <footer class="navbar justify-content-end navbar-expand navbar-dark bg-secondary fixed-bottom">
        <ul class="navbar-nav">
            <li class="nav-item me-2"><button disabled class="btn btn-light">ルーム管理</button></li>
            <li class="nav-item me-2"><a href="listUser" class="btn btn-primary">従業員管理</a></li>
            <li class="nav-item me-2"><a href="listPricePlan" class="btn btn-primary">料金管理</a></li>
            <li class="nav-item me-2"><a href="" class="btn btn-primary">商品管理</a></li>
            <li class="nav-item me-2"><a href="manager" class="btn btn-warning">入室管理</a></li>
            <li class="nav-item me-2"><a href="logout" id="logout" class="btn btn-danger">ログアウト</a></li>
        </ul>
    </footer>
    <script src="js/jquery-3.6.1.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
