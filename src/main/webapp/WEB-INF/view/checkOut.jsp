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
<body class="pb-5">
    <div class="container">
        <h1>お会計</h1>
        <div class="row">
            <div class="col">

                <table class="table table-bordered">
                    <c:if test="${not empty roomUsedData.roomId }">
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
                            <th>会員名</th>
                            <td>
                                <c:out value="${roomUsedData.customerName }" />
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty shoppingCartList}">
                        <tr>
                            <th>商品料金</th>
                            <td>
                                <table class="table">
                                    <thead class="bg-white">
                                        <tr>
                                            <th scope="col">商品ID</th>
                                            <th scope="col">商品名</th>
                                            <th scope="col">個数</th>
                                            <th scope="col">金額</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${shoppingCartList}" var="cart">
                                            <tr>
                                                <td>
                                                    <c:out value="${cart.productId }" />
                                                </td>
                                                <td>
                                                    <c:out value="${cart.productName }" />
                                                </td>
                                                <td>
                                                    <c:out value="${cart.productUnit }" />
                                                </td>
                                                <td>
                                                    <fmt:formatNumber value="${cart.totalPrice }"
                                                        type="CURRENCY" currencyCode="JPY"
                                                        maxFractionDigits="0" />
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                    <tr>
                                        <td colspan="2">
                                            <fmt:formatNumber value="${shoppingPrice}"
                                                type="CURRENCY" currencyCode="JPY"
                                                maxFractionDigits="0" />
                                            <small class="text-muted"> (内税<fmt:formatNumber
                                                    value="${shoppingTax}" type="CURRENCY"
                                                    currencyCode="JPY" maxFractionDigits="0" />)
                                            </small>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty roomUsedData.roomId }">
                        <tr>
                            <th>ルーム料金</th>
                            <td>
                                <table class="table">
                                    <thead class="sticky-top bg-white">
                                        <tr>
                                            <th scope="col">利用時間</th>
                                            <th scope="col">適用プラン</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <c:out value="${timeDisplay}" />
                                            </td>
                                            <td>
                                                <c:out value="${roomUsedData.planName }" />
                                            </td>
                                        </tr>
                                    </tbody>
                                    <tr>
                                        <td colspan="2">
                                            <fmt:formatNumber value="${roomUsedData.roomPrice}"
                                                type="CURRENCY" currencyCode="JPY"
                                                maxFractionDigits="0" />
                                            <small class="text-muted"> (内税<fmt:formatNumber
                                                    value="${roomUsedData.roomTax}" type="CURRENCY"
                                                    currencyCode="JPY" maxFractionDigits="0" />)
                                            </small>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <th>お会計</th>
                        <td>
                            <fmt:formatNumber value="${receiptData.sumPrice}" type="CURRENCY"
                                currencyCode="JPY" maxFractionDigits="0" />
                        </td>
                    </tr>
                    <tr>
                        <th>内税</th>
                        <td>
                            <fmt:formatNumber value="${receiptData.sumTax}" type="CURRENCY"
                                currencyCode="JPY" maxFractionDigits="0" />
                        </td>
                    </tr>
                </table>
                <div class="row">
                    <div class="col">
                        <c:if test="${not empty paymentError }">
                            <div class="error-message">
                                <c:out value="${paymentError}"></c:out>
                            </div>
                        </c:if>
                    </div>
                </div>
                <form action="" method="post">

                    <footer
                        class="navbar justify-content-end navbar-expand navbar-dark bg-secondary fixed-bottom">
                        <ul class="navbar-nav">
                            <li class="nav-item me-2">
                                    <div class="input-group col-auto">
                                        <span class="input-group-text">預り金</span> <input
                                            type="number" pattern="\d*" name="payment"
                                            id="formPayment" class="form-control" min="0"
                                            max="999999" value=<c:out value="${payment}"/>>
                                        <span class="input-group-text">円</span>
                                    </div>
                            </li>
                            <li class="nav-item me-2"><input type="submit" value="清算"
                                class="btn btn-primary"></li>
                            <li class="nav-item me-2"><a href="manager" class="btn btn-light">キャンセル</a></li>
                        </ul>
                    </footer>
                </form>
            </div>
        </div>
    </div>
    <script src="js/jquery-3.6.1.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script>
					$(document).ready(function() {
						$("form").submit(function() {
							return confirm("清算しますか？");
						});
					});
				</script>
</body>
</html>
