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

<title>管理システムログイン</title>
</head>
<body id="loginPage">
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-6">
        <div class="card login-panel">
          <div class="card-header">ネットカフェ管理システム ログイン</div>
          <div class="card-body">
            <c:if test="${not empty error}">
              <div class="alert alert-danger">ログインIDかパスワードが正しくありません。</div>
            </c:if>

            <form action="" method="post">
              <div class="mb-3">
                <c:if test="${not empty loginIdError }">
                  <div class="error-message">
                    <c:out value="${loginIdError}"></c:out>
                  </div>
                </c:if>
                <input type="text" name="loginId" placeholder="ログインID" class="form-control" value=<c:out value="${loginId }"/>>
              </div>
              <div class="mb-3">
                <c:if test="${not empty loginPassError }">
                  <div class="error-message">
                    <c:out value="${loginPassError }"></c:out>
                  </div>
                </c:if>
                <input type="password" name="loginPass" placeholder="パスワード" class="form-control" value=<c:out value="${loginPass }"/>>
              </div>
              <div class="mb-3">
                <input type="submit" class="btn btn-primary btn-block" value="ログイン">
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="js/jquery-3.6.1.min.js"></script>
  <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
