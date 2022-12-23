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
<title>商品管理</title>
</head>
<body>
    <div class="container">
        <h1>商品登録</h1>
        <div class="row">
            <div class="col">
                <form action="" method="post">
                    <div class="mb-3">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th scope="col"><label for="formStartTime">商品名</label></th>
                                    <th scope="col"><label for="formProductPrice">価格</label></th>
                                    <th scope="col"><label for="formProductType">カテゴリ</label></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="pb-5">
                                    <td>
                                        <input type="text" name="productName" id="formProductName" class="form-control"
                                            value=<c:out value="${product.productName }"/>>
                                    </td>
                                    <td>
                                        <div class="input-group">
                                            <input type="number" name="productPrice" id="formProductPrice"
                                            class="form-control" value=<c:out value="${product.productPrice }"/> min="0"
                                            max="999999">
                                            <span class="input-group-text">円</span>
                                        </div>
                                        
                                    </td>
                                    <td>
                                        <select name="productTypeId" id="formProductTypeId" class="form-control">
                                            <c:forEach items="${productTypeList}" var="productType">
                                                <option value="<c:out value="${productType.productTypeId}" />"
                                                    <c:if test="${productType.productTypeId == productTypeId}">selected</c:if>>
                                                    <c:out value="${productType.productTypeName}" />
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="mb-3">
                        <input type="submit" class="btn btn-success" value="登録">
                        <a href="listProduct" class="btn btn-light">キャンセル</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="js/jquery-3.6.1.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
