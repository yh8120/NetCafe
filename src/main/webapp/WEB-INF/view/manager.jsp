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

    <div class="accordion accordion-flush" id="accordionFlushExample">
      <div class="accordion-item">
        <h2 class="accordion-header" id="flush-headingOne">
          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
            data-bs-target="#flush-collapseOne" aria-expanded="false"
            aria-controls="flush-collapseOne">Accordion Item #1</button>
        </h2>
        <div id="flush-collapseOne" class="accordion-collapse collapse"
          aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
          <div class="accordion-body">
            Placeholder content for this accordion, which is intended to demonstrate the
            <code>.accordion-flush</code>
            class. This is the first item's accordion body.
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <c:forEach items="${roomList}" var="room">
        <div class="col-12 mb-2">
          <a class="btn btn-success "><c:out value="${room.roomName }" /></a>
          <div class="no-vacancy">
            <a href="" class="btn btn-success col-3 mr-2">入室</a> <a href=""
              class="btn btn-success col-3 mr-2">予約</a> <a href="" class="btn btn-success col-3">清掃</a>
          </div>
          <div class="vacancy">
            <a href="" class="btn btn-success col-2 mr-2">退室</a> <a href=""
              class="btn btn-success col-2 mr-2">販売</a> <a href=""
              class="btn btn-success col-2 mr-2">レンタル</a> <a href="" class="btn btn-success col-2">ロスタイム</a>
          </div>

        </div>
      </c:forEach>
      <div class="col">
        <a href="addCustomer" class="btn btn-success">会員登録</a> <a href="index" class="btn btn-light">POS管理</a>
      </div>
    </div>
  </div>
  <script src="js/jquery-3.6.1.min.js"></script>
  <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
