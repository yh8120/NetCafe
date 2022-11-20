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
<title>会員管理</title>
</head>
<body>
  <div class="container">
    <h1>会員登録</h1>
    <div class="row">
      <div class="col">
        <form class="h-adr" action="" method="post">
        <span class="p-country-name" style="display:none;">Japan</span>
          <div class="mb-3">
            <label for="formCustomerId">会員番号</label>
            <c:if test="${not empty customerIdError }">
              <div class="error-message">
                <c:out value="${customerIdError}"></c:out>
              </div>
            </c:if>
            <input type="text" name="customerId" id="formCustomerId" class="form-control"
              value=<c:out value="${customerId }"/>>
          </div>
          <div class="mb-3">
            <label for="formCustomerClass">会員クラス</label>
            <select name="customerClassId" id="formCustomerClass" class="form-control">
              <c:forEach items="${customerClassList}" var="customerClass">
                <option value="<c:out value="${customerClass.customerClassId}" />"
                  <c:if test="${customerClass.customerClassId == customerClassId}">selected</c:if>>
                  <c:out value="${customerClass.customerClassName}" />
                </option>
              </c:forEach>
            </select>
          </div>
          <div class="mb-3">
            <label for="formCustomerName">名前</label>
            <c:if test="${not empty customerNameError }">
              <div class="error-message">
                <c:out value="${customerNameError}"></c:out>
              </div>
            </c:if>
            <input type="text" name="customerName" id="formCustomerName" class="form-control"
              value=<c:out value="${customerName }"/>>
          </div>
          <div class="mb-3">
            <label for="formSexId">性別</label>
            <select name="customerSexId" id="formSexId" class="form-control">
              <c:forEach items="${sexList}" var="sex">
                <option value="<c:out value="${sex.sexId}" />"
                  <c:if test="${sex.sexId == customerSexId}">selected</c:if>>
                  <c:out value="${sex.sexName}" />
                </option>
              </c:forEach>
            </select>
          </div>
          <div class="mb-3">
            <label for="formCard">身分証</label>
            <select name="customerCardId" id="formCard" class="form-control">
              <c:forEach items="${cardList}" var="card">
                <option value="<c:out value="${card.cardId}" />"
                  <c:if test="${card.cardId == customerCardId}">selected</c:if>>
                  <c:out value="${card.cardName}" />
                </option>
              </c:forEach>
            </select>
          </div>
          <div class="mb-3">
            <label for="formCustomerCardNumber">身分証番号</label>
            <c:if test="${not empty customerCardNumberError }">
              <div class="error-message">
                <c:out value="${customerCardNumberError}"></c:out>
              </div>
            </c:if>
            <input type="text" name="customerCardNumber" id="formCustomerCardNumber" class="form-control"
              value=<c:out value="${customerCardNumber }"/>>
          </div>
          <div class="mb-3">
            <label for="formCustomerBirthday">生年月日</label>
            <c:if test="${not empty customerBirthdayError }">
              <div class="error-message">
                <c:out value="${customerBirthdayError}"></c:out>
              </div>
            </c:if>
            <div class="col-auto">
              <input type="date" name="customerBirthday" id="formCustomerBirthday"
                class="form-control" value=<c:out value="${customerBirthday }"/>>
            </div>
          </div>
          <div class="mb-3">
            <label for="formCustomerZipcode">郵便番号</label>
            <c:if test="${not empty customerZipcodeError }">
              <div class="error-message">
                <c:out value="${customerZipcodeError}"></c:out>
              </div>
            </c:if>
            <input type="text" name="customerZipcode" id="formCustomerZipcode" class="form-control p-postal-code"  maxlength="8"
              value=<c:out value="${customerZipcode }"/>>
          </div>
          <div class="mb-3">
            <label for="formCustomerAddress">住所</label>
            <c:if test="${not empty customerAddressError }">
              <div class="error-message">
                <c:out value="${customerAddressError}"></c:out>
              </div>
            </c:if>
            <input type="text" name="customerAddress" id="formCustomerAddress" class="form-control p-region p-locality p-street-address p-extended-address"
              value=<c:out value="${customerAddress }"/>>
          </div>
          <div class="mb-3">
            <label for="formCustomerMemo">メモ</label>
            <input type="text" name="customerMemo" id="formCustomerMemo" class="form-control"
              value=<c:out value="${customerMemo }"/>>
          </div>
          <div class="mb-3">
            <label for="formCustomerPhone">電話番号</label>
            <c:if test="${not empty customerPhoneError }">
              <div class="error-message">
                <c:out value="${customerPhoneError}"></c:out>
              </div>
            </c:if>
            <input type="text" name="customerPhone" id="formCustomerPhone" class="form-control"
              value=<c:out value="${customerPhone }"/>>
          </div>
          <div class="mb-3">
            <label for="formCustomerMail">メールアドレス</label>
            <c:if test="${not empty customerMailError }">
              <div class="error-message">
                <c:out value="${customerMailError}"></c:out>
              </div>
            </c:if>
            <input type="text" name="customerMail" id="formCustomerMail"
              class="form-control" value=<c:out value="${customerMail }"/>>
          </div>
          <div class="mb-3">
            <input type="submit" class="btn btn-success" value="登録">
            <a href="manager" class="btn btn-light">キャンセル</a>
          </div>
        </form>
      </div>
    </div>
  </div>
  <script src="https://yubinbango.github.io/yubinbango/yubinbango.js" charset="UTF-8"></script>
  <script src="js/jquery-3.6.1.min.js"></script>
  <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
