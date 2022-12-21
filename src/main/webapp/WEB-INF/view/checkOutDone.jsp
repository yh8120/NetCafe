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
<link href="css/receipt.css" rel="stylesheet" media="print">
<title>入室管理</title>
</head>
<body>
    <div id="receipt">
        <h1 class="print-hidden">お会計</h1>
        <div class="receipt-hidden">
            <header>
                <img src="images/posh.jpg" alt="" />
                <h2>領収書</h2>
                <p><c:out value="${shop.shopName}" /></p>
                <p><c:out value="${shop.shopAddress}" /></p>
                <p>TEL:<c:out value="${shop.shopPhoneNumber}" /></p>
                <p><c:out value="${receipt.printedTime}" /></p>
                <table>
                    <tr>
                        <td colspan="2" class="bt">
                            <small>会員番号:<c:out value="${roomUsedData.customerId}" /></small>
                        </td>
                        <td colspan="2" class="text-end bt">
                            <small>入店番号:【●●●】</small>
                        </td>
                    </tr>
                </table>
            </header>
        </div>
        <main>
            <table class="table table-bordered">
                <tr>
                    <th colspan="4" class="text-center bigt">【 ルーム料金 】</th>
                </tr>
                <tr>
                    <th colspan="2">ご利用ブース:</th>
                    <td colspan="2">
                        <c:out value="${roomUsedData.roomName}" />
                    </td>
                </tr>
                <tr>
                    <th colspan="2">入室時間:</th>
                    <td colspan="2">
                        <fmt:formatDate value="${roomUsedData.startTime}" pattern="y/M/d hh:mm:ss" />
                    </td>
                </tr>
                <tr>
                    <th colspan="2">退室時間:</th>
                    <td colspan="2">
                        <fmt:formatDate value="${roomUsedData.checkOutTime}"
                            pattern="y/M/d hh:mm:ss" />
                    </td>
                </tr>
                <tr>
                    <th colspan="2">滞在時間:</th>
                    <td colspan="2">
                        <c:out value="${timeDisplay}" />
                    </td>
                </tr>
                <tr>
                    <th colspan="2">ロスタイム:</th>
                    <td colspan="2">00:00:00</td>
                </tr>
                <tr>
                    <th colspan="2">24時間パック</th>
                    <td colspan="2" class="text-end">
                        <small>\</small>
                        <c:out value="${roomUsedData.roomPrice}" />
                        <small>込</small>
                    </td>
                </tr>
                <tr class="bb">
                    <th colspan="2">一般値引</th>
                    <td colspan="2" class="text-end">
                        <small>\</small>【●●●】<small>込</small>
                    </td>
                </tr>
                <tr>
                    <th colspan="2"><c:out value="${roomUsedData.roomName}" /></th>
                    <td colspan="1">【●●●】</td>
                    <td colspan="1" class="text-end">
                        <small>\</small>【●●●】
                    </td>
                </tr>
                <tr class="receipt-hidden">
                    <td>
                        <br />
                    </td>
                </tr>
                <tr>
                    <th colspan="4" class="text-center bigt">【 販売・レンタル・その他 】</th>
                </tr>
                <c:forEach items="${shoppingCartList}" var="cart">
                    <tr>
                        <th colspan="2"><c:out value="${cart.productName }" /></th>
                        <td>
                            <c:out value="${cart.productUnit }" />
                        </td>
                        <td class="text-end">
                            <small>\</small>
                            <c:out value="${cart.totalPrice}" />
                            <small>込</small>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <th colspan="2">小計</th>
                    <td colspan="2" class="text-end">
                        <small>\</small>
                        <c:out value="${shoppingPrice}" />
                    </td>
                </tr>
                <tr class="receipt-hidden">
                    <td>
                        <br />
                    </td>
                </tr>
                <tr>
                    <th colspan="2" class="bt bigt">合計</th>
                    <td colspan="1" class="bt text-end">
                        <small>(内税\<c:out value="${receiptData.sumTax}" />)
                        </small>
                    </td>
                    <td colspan="1" class="bt text-end bigt">
                        <small>\</small>
                        <c:out value="${receiptData.sumPrice}" />
                    </td>
                </tr>
                <tr>
                    <th colspan="2">お預り</th>
                    <td colspan="2" class="text-end">
                        <small>\</small>
                        <c:out value="${receiptData.payment}" />
                    </td>
                </tr>
                <tr class="bigt">
                    <th colspan="2">釣銭</th>
                    <td colspan="2" class="text-end">
                        <small>\</small>
                        <c:out value="${receiptData.changeMoney}" />
                    </td>
                </tr>
                <tr class="receipt-hidden">
                    <td>
                        <br />
                    </td>
                </tr>
            </table>
        </main>
        <div class="receipt-hidden">
            <footer>
                <p>ご利用いただきありがとうございました。</p>
                <p>またご利用くださいませ(^_-)-☆</p>
                <table>
                    <tr>
                        <td>
                            <br />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="bt">
                            <small>担当者:<c:out value="${receiptData.userId}" /></small>
                        </td>
                        <td colspan="2" class="text-end bt">
                            <small>レシートNO:<c:out value="${receiptData.receiptId}" /></small>
                        </td>
                    </tr>
                </table>
            </footer>
        </div>
        <footer
            class="navbar justify-content-end navbar-expand navbar-dark bg-secondary fixed-bottom  print-hidden">
            <ul class="navbar-nav">
                <li class="nav-item"><button id="receiptPrint" class="btn btn-primary me-2"
                        onclick="window.print();">レシート印刷</button></li>
                <li class="nav-item"><a href="manager" class="btn btn-light me-2">戻る</a></li>
            </ul>
        </footer>
    </div>

</body>
</html>
