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
<title>料金管理</title>
</head>
<body class="pb-5">
    <header class="navbar navbar-expand-lg navbar-light bg-light mb-2">
        <div class="container-fluid">
            <h1 class="navbar-brand">料金管理</h1>

        </div>
    </header>
    <div class="container">
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
                        <th>開始</th>
                        <th>終了</th>
                        <th>開始<br>時刻
                        </th>
                        <th>終了<br>時刻
                        </th>
                        <th>基本<br>料金
                        </th>
                        <th>基本<br>時間
                        </th>
                        <th>追加<br>料金
                        </th>
                        <th>追加<br>時間
                        </th>
                        <th colspan="2">操作</th>
                    </tr>
                    <tr>
                        <c:forEach items="${planListAll}" var="plicePlan">
                            <tr>
                                <td>
                                    <c:out value="${plicePlan.planId }" />
                                </td>
                                <td>
                                    <c:out value="${plicePlan.planName }" />
                                </td>
                                <td>
                                    <fmt:formatDate value="${plicePlan.planStart}" pattern="y-M-d" />
                                </td>
                                <td>
                                    <fmt:formatDate value="${plicePlan.planEnd}" pattern="y-M-d" />
                                </td>
                                <td>
                                    <c:out value="${plicePlan.startTime }" />
                                </td>
                                <td>
                                    <c:out value="${plicePlan.endTime }" />
                                </td>
                                <td>
                                    <c:out value="${plicePlan.basicPrice }" />
                                </td>
                                <td>
                                    <c:out value="${plicePlan.basicTime }" />
                                </td>
                                <td>
                                    <c:out value="${plicePlan.addPrice }" />
                                </td>
                                <td>
                                    <c:out value="${plicePlan.addTime }" />
                                </td>
                                <td>
                                    <a href="updateRoom?planId=<c:out value="${plicePlan.planId }"/>">更新</a>
                                </td>
                                <td>
                                    <a href="deleteRoom?planId=<c:out value="${plicePlan.planId }"/>">削除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tr>
                </table>
                <a href="addPricePlan" class="btn btn-success">プラン作成</a>
            </div>
        </div>
    </div>
    <footer class="navbar justify-content-end navbar-expand navbar-dark bg-secondary fixed-bottom">
        <ul class="navbar-nav">
            <li class="nav-item me-2"><a href="listRoom" class="btn btn-primary">ルーム管理</a></li>
            <li class="nav-item me-2"><a href="listUser" class="btn btn-primary">従業員管理</a></li>
            <li class="nav-item me-2"><button disabled class="btn btn-light">料金管理</button></li>
            <li class="nav-item me-2"><a href="listProduct" class="btn btn-primary">商品管理</a></li>
            <li class="nav-item me-2"><a href="manager" class="btn btn-warning">入室管理</a></li>
            <li class="nav-item me-2"><a href="logout" id="logout" class="btn btn-danger">ログアウト</a></li>
        </ul>
    </footer>
    <script src="js/jquery-3.6.1.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
