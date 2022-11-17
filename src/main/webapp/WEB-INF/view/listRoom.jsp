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
<body>
  <div class="container">
    <h1>施設リスト</h1>
    <p><a href="logout">ログアウト</a></p>
    <div class="row">
      <div class="col">
        <table class="table table-bordered">
          <tr>
            <th>部屋番号</th>
            <th>名前</th>
            <th>部屋タイプ</th>
            <th colspan="2">操作</th>
          </tr>
          <tr>
            <c:forEach items="${roomList}" var="room">
              <tr>
                <td>
                  <c:out value="${room.roomNumber }" />
                </td>
                <td>
                  <c:out value="${room.roomName }" />
                </td>
                <td>
                  <c:out value="${room.roomTypeName }" />
                </td>
                <td>
                  <a href="updateLocation?id=<c:out value="${room.roomNumber }"/>">更新</a>
                </td>
                <td>
                  <a href="deleteLocation?id=<c:out value="${room.roomNumber }"/>">削除</a>
                </td>
              </tr>
            </c:forEach>
          </tr>
        </table>
        <a href="addRoom" class="btn btn-success">ルーム作成</a>
        <a href="setting" class="btn btn-light">戻る</a>
      </div>
    </div>
  </div>
  <script src="js/jquery-3.6.1.min.js"></script>
  <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
