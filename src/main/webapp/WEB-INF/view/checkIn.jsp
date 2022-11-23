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
<title>入室管理</title>
</head>
<body>
<div class="container">
  <h1>入室管理</h1>
  <div class="row">
    <div class="col">
      <form action="" method="post">
          <div class="mb-3">
            <label for="formCustomerId">会員番号</label>
            <c:if test="${not empty customerIdError }">
              <div class="error-message">
                <c:out value="${customerIdError}"></c:out>
              </div>
            </c:if>
            <input type="number" name="customerId" id="formCustomerId" class="form-control" value=<c:out value="${customerId }"/>>
          </div>
          <div class="mb-3">
            <input type="submit" class="btn btn-success" value="登録">
            <a href="manager" class="btn btn-light">キャンセル</a>
          </div>
        </form>
    </div>
  </div>
</div>
<script src="js/jquery-3.6.1.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
