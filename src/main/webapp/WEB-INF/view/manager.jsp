<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<title>入室管理</title>
</head>
<body>
  <div class="container">
    <h1>入室管理</h1>
    <div class="row">
      <div class="col">
        <table class="table table-bordered">
          <tr>
            <th>名前</th>
            <th colspan="2">操作</th>
          </tr>
          <tr>
            <c:forEach items="${roomList}" var="room">
              <tr>
                <td>
                  <c:out value="${room.roomName }" />
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
        <a href="addCustomer" class="btn btn-success">会員登録</a>
        <a href="index" class="btn btn-light">POS管理</a>
      </div>
    </div>
  </div>
  <script src="js/jquery-3.6.1.min.js"></script>
  <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
