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
    <h1>お会計</h1>
    <div class="row">
      <div class="col">
        <table class="table table-bordered">
          <tr>
            <th>ルーム</th>
            <td>
              <c:out value="${roomUsedData.roomName }" />
            </td>
          </tr>
          <tr>
            <th>会員番号</th>
            <td>
              <c:out value="${roomUsedData.customerId }" />
            </td>
          </tr>
          <tr>
            <th>名前</th>
            <td>
              <c:out value="${roomUsedData.customerName }" />
            </td>
          </tr>
          <tr>
            <th>ご利用時間</th>
            <td>
              <c:out value="${timeDisplay}" />
            </td>
          </tr>
          <tr>
            <th>お会計</th>
            <td>
              <fmt:formatNumber value="${receiptData.sumPrice}" type="CURRENCY" currencyCode="JPY"
                maxFractionDigits="0" />
            </td>
          </tr>
          <tr>
            <th>お預り金</th>
            <td>
              <fmt:formatNumber value="${receiptData.payment}" type="CURRENCY" currencyCode="JPY"
                maxFractionDigits="0" />
            </td>
          </tr>
          <tr>
            <th>お釣り</th>
            <td>
              <fmt:formatNumber value="${receiptData.changeMoney}" type="CURRENCY" currencyCode="JPY"
                maxFractionDigits="0" />
            </td>
          </tr>
        </table>
        <a href="manager" class="btn btn-light">戻る</a>
      </div>
    </div>
  </div>
  <script src="js/jquery-3.6.1.min.js"></script>
  <script src="js/bootstrap.bundle.min.js"></script>

</body>
</html>
