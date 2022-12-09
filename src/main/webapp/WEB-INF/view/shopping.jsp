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
<title>ルーム販売</title>
</head>
<body style="padding: 0rem 0rem 5rem">
    <header class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <h2 class="navbar-brand">商品販売</h2>
            <button id="navbar-toggler" class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
                <img src="images/cart_icon.svg" alt="cart_icon" width="32" height="32">
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">商品ID</th>
                                        <th scope="col">商品名</th>
                                        <th scope="col">個数</th>
                                        <th scope="col">金額</th>
                                        <th scope="col">操作</th>
                                    </tr>
                                </thead>
                                <tbody id="shoppingCartBody">

                                </tbody>
                            </table>


                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <div class="container">



        <div class="row">
            <div class="accordion accordion-flush" id="accordionFlushExample">
                <c:set var="productTypeId">0</c:set>
                <c:forEach items="${productList}" var="product" varStatus="vs">



                    <c:if test="${product.productTypeId != productTypeId}">
                        <c:set var="productTypeId">
                            <c:out value="${product.productTypeId }" />
                        </c:set>
                        <c:if test="${!vs.first}">
            </div>
        </div>
    </div>
    </c:if>



    <div class="accordion-item">
        <h2 class="accordion-header" id="flush-heading${product.productTypeId }">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                data-bs-target="#flush-collapse${product.productTypeId }" aria-expanded="false"
                aria-controls="flush-collapse${product.productTypeId }">
                <c:out value="◆　${product.productTypeName }" />
            </button>
        </h2>
        <div id="flush-collapse${product.productTypeId }" class="accordion-collapse collapse"
            aria-labelledby="flush-heading${product.productTypeId }"
            data-bs-parent="#accordionFlushExample">
            <div class="accordion-body">
                </c:if>

                <button data-id="${product.productId }" data-price="${product.productPrice}"
                    class="addcart btn btn-success col-auto mr-2 mb-1">
                    <c:out value="${product.productName }" />
                </button>

                <c:if test="${vs.last}">
            </div>
        </div>
    </div>
    </c:if>




    </c:forEach>

    </div>

    </div>
    </div>
    <footer class="navbar justify-content-end navbar-expand navbar-dark bg-secondary fixed-bottom">
        <ul class="navbar-nav">
            <li class="nav-item"><form action="" method="post"><button id="cartSubmit" class="btn btn-primary me-2">決定</button></form></li>
            <li class="nav-item"><a href="manager" class="btn btn-danger me-2">キャンセル</a></li>
        </ul>
    </footer>


    <template id="cart-row-template">
        <tr>
            <th scope="row" id="productId"></th>
            <td id="productName"></td>
            <td id="productUnit"></td>
            <td id="priceXUnit"></td>
            <td id="productUnitButton"></td>
        </tr>
    </template>

    <template id="unit-button-template">
        <div>
            <button class="btn btn-increase">
                <img src="images/increase_icon.svg" alt="cart_icon" width="32" height="32">
            </button>
            <button class="btn btn-decrease">
                <img src="images/decrease_icon.svg" alt="cart_icon" width="32" height="32">
            </button>
        </div>
    </template>

    <template id="sum-price-template">
        <tr>
            <th scope="row" colspan="3" class="text-end">合計</th>
            <td>
                <span id="sumPrice"></span>円
            </td>
            <td></td>
        </tr>
    </template>


    <script src="js/jquery-3.6.1.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/shoppingcart.js"></script>

</body>
</html>
