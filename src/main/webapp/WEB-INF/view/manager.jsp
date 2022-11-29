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
      <div class="accordion accordion-flush" id="accordionFlushExample">

        <c:forEach items="${roomList}" var="room">

          <c:if test="${not empty room.roomOrder}">

            <div class="accordion-item">
              <h2 class="accordion-header" id="flush-heading${room.roomId }">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                  data-bs-target="#flush-collapse${room.roomId }" aria-expanded="false"
                  aria-controls="flush-collapse${room.roomId }">
                  <c:if test="${room.cleaningId != 1}">
                    <c:out value="※${room.cleaningName }※" />
                  </c:if>

                  <c:out value="◆　${room.roomName }" />

                  <c:if test="${not empty room.customerId}">
                    <c:out value="　◆会員：${room.customerId }　◆入室：" />
                    <fmt:formatDate value="${room.started }" pattern="d日HH時mm分" />
                  </c:if>

                </button>
              </h2>
              <div id="flush-collapse${room.roomId }" class="accordion-collapse collapse"
                aria-labelledby="flush-heading${room.roomId }"
                data-bs-parent="#accordionFlushExample">
                <div class="accordion-body">
                  <c:choose>
                    <c:when test="${not empty room.customerId }">
                      <div class="vacancy">
                        <a
                          href="checkOut?roomId=<c:out value="${room.roomId }"/>"
                          class="btn btn-success col-2 mr-2">退室</a>
                        <a href="" class="btn btn-success col-2 mr-2">販売</a>
                        <a href="" class="btn btn-success col-2 mr-2">レンタル</a>
                        <a href="" class="btn btn-success col-2">ロスタイム</a>
                      </div>
                    </c:when>
                    <c:otherwise>
                      <div class="no-vacancy">
                        <c:if test="${room.cleaningId != 3}">
                          <a href="checkIn?roomId=<c:out value="${room.roomId }"/>"
                            class="btn btn-success col-2 mr-2">入室</a>
                          <a href="" class="btn btn-success col-3 mr-2">予約</a>
                        </c:if>

                        <c:if test="${room.cleaningId == 2}">
                          <a href="cleaning?roomId=<c:out value="${room.roomId }"/>&cleaningId=1"
                            class="btn btn-success col-2 mr-2">清掃完了</a>
                        </c:if>

                        <c:if test="${room.cleaningId == 1}">
                          <a href="cleaning?roomId=<c:out value="${room.roomId }"/>&cleaningId=2"
                            class="btn btn-success col-2 mr-2">未清掃に戻す</a>
                        </c:if>

                        <c:if test="${room.cleaningId == 3}">
                          <a href="cleaning?roomId=<c:out value="${room.roomId }"/>&cleaningId=2"
                            class="btn btn-success col-2 mr-2">点検解除</a>
                        </c:if>

                        <c:if test="${room.cleaningId == 1}">
                          <a href="cleaning?roomId=<c:out value="${room.roomId }"/>&cleaningId=3"
                            class="btn btn-success col-2 mr-2">点検</a>
                        </c:if>

                      </div>
                    </c:otherwise>
                  </c:choose>
                </div>
              </div>
            </div>
          </c:if>

        </c:forEach>

      </div>
      <div class="col">
        <a href="addCustomer" class="btn btn-success">会員登録</a>
        <a href="index" class="btn btn-light">POS管理</a>
      </div>
    </div>
  </div>
  <script src="js/jquery-3.6.1.min.js"></script>
  <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
