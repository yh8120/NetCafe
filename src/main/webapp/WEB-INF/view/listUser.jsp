<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<title>ユーザー管理</title>
</head>
<body>
  <div class="container">
    <h1>ユーザーリスト</h1>
    <div class="row">
      <div class="col">
        <table class="table table-bordered">
          <tr>
            <th>ID</th>
            <th>名前</th>
            <th>ログインID</th>
            <th>ログインPASS</th>
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
                  <c:out value="${user.loginPass }" />
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
         <a href="addUser" class="btn btn-success">ユーザーの追加</a>
        <a href="setting" class="btn btn-light">戻る</a>
      </div>
    </div>
  </div>
  <script src="js/jquery-3.6.1.min.js"></script>
  <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
