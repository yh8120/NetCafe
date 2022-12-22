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
<title>ルーム管理</title>
</head>
<body>
<div class="container">
  <h1>ルーム作成</h1>
  <div class="row">
    <div class="col">
      <form action="" method="post">
          <div class="mb-3">
            <label for="formRoomName">ルーム名</label>
            <c:if test="${not empty roomNameError }">
              <div class="error-message">
                <c:out value="${roomNameError }"></c:out>
              </div>
            </c:if>
            <input type="text" name="roomName" id="formRoomName" class="form-control" value=<c:out value="${roomName }"/>>
          </div>
          <div class="mb-3">
            <label for="formRoomTypeId">ルームタイプ</label> <select name="roomTypeId" id="formRoomTypeId" class="form-control">
              <c:forEach items="${roomTypeList}" var="roomType">
                <option value="<c:out value="${roomType.roomTypeId}" />" <c:if test="${roomType.roomTypeId == roomTypeId}">selected</c:if>>
                  <c:out value="${roomType.roomTypeName}" />
                </option>
              </c:forEach>
            </select>
          </div>
          <div class="mb-3">
            <input type="submit" class="btn btn-success" value="登録">
            <a href="listRoom" class="btn btn-light">キャンセル</a>
          </div>
        </form>
    </div>
  </div>
</div>
<script src="js/jquery-3.6.1.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
