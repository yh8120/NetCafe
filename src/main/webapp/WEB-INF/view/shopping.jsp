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
<body style="padding:0rem 0rem 5rem">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">商品販売</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item"><a class="nav-link active" aria-current="page"
                                href="#">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Link</a></li>
                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
                                href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                                aria-expanded="false"> Dropdown </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#">Action</a></li>
                                <li><a class="dropdown-item" href="#">Another action</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="#">Something else here</a></li>
                            </ul></li>
                        <li class="nav-item"><a class="nav-link disabled" href="#"
                                tabindex="-1" aria-disabled="true">Disabled</a></li>
                    </ul>
                    <form class="d-flex">
                        <input class="form-control me-2" type="search" placeholder="Search"
                            aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </nav>
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



    <!-- ↓↓↓アコーディオンここから↓↓↓ -->
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

                <a
                    href="addProduct?roomId=<c:out value="${roomId }"/>&productId=<c:out value="${product.productId }"/>"
                    class="btn btn-success col-auto mr-2 mb-1">
                    <c:out value="${product.productName }" />
                </a>

                <c:if test="${vs.last}">
            </div>
        </div>
    </div>
    <!-- ↑↑↑アコーディオンここまで↑↑↑ -->
    </c:if>




    </c:forEach>

    </div>

    </div>
    </div>
    <footer class="navbar justify-content-end navbar-expand navbar-dark bg-secondary fixed-bottom">
            <ul class="navbar-nav">
                <li class="nav-item"><a href="#" class="btn btn-primary me-2">カート</a></li>
                <li class="nav-item"><a href="#" class="btn btn-primary me-2">決定</a></li>
                <li class="nav-item"><a href="manager" class="btn btn-danger me-2">キャンセル</a></li>
            </ul>
        </footer>
    <script src="js/jquery-3.6.1.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script>
		let shoppingCart = new List();
		
	</script>
</body>
</html>
