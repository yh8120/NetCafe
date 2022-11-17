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
            <th>保管場所ID</th>
            <th>保管場所名</th>
            <th colspan="2">操作</th>
          </tr>
          <tr>
            <c:forEach items="${locationList}" var="location">
              <tr>
                <td>
                  <c:out value="${location.id }" />
                </td>
                <td>
                  <c:out value="${location.name }" />
                </td>
                <td>
                  <a href="updateLocation?id=<c:out value="${location.id }"/>">更新</a>
                </td>
                <td>
                  <a href="deleteLocation?id=<c:out value="${location.id }"/>">削除</a>
                </td>
              </tr>
            </c:forEach>
          </tr>
        </table>
        <a href="listItem" class="btn btn-primary">備品の確認</a> <a href="addLocation" class="btn btn-success">保管場所の追加</a>
      </div>
    </div>
  </div>
  <script src="js/jquery-3.6.1.min.js"></script>
  <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
