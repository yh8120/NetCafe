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
    <h1>施設更新</h1>
    <p><a href="logout">ログアウト</a></p>
    <div class="row">
      <div class="col">
        <form action="" method="post">
          <table class="table table-bordered">
            <tr>
              <th>ID</th>
              <td>
                <c:out value="${id}" />
              </td>
            </tr>
            <tr>
              <th>保管場所</th>
              <td>
                <c:if test="${not empty nameError}">
                  <p><c:out value="${nameError}" /></p>
                </c:if>
                <input type="text" name="name" value=<c:out value="${name}"/>>
              </td>
            </tr>
            
          </table>
          <input type="submit" value="更新" class="btn btn-primary"> <a href="listLocation" class="btn btn-light">キャンセル</a>
        </form>
      </div>
    </div>
  </div>
  <script src="js/jquery-3.6.1.min.js"></script>
  <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
