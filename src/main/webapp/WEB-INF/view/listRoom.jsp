<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<title>施設管理</title>
</head>
<body class="pb-5">
	<nav class="navbar navbar-expand-sm navbar-dark bg-secondary mb-4">
		<div class="container">
			<a class="navbar-brand me-5" href="manager">
				<img src="images/posh.jpg" alt="" width="72" />
			</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav">
					<li class="nav-item text-end"><a class="nav-link" href="manager">店舗管理</a></li>
					<li class="nav-item text-end"><a class="nav-link" href="addCustomer">会員登録</a></li>
					<li class="nav-item text-end"><a class="nav-link" href="#">売上集計</a></li>
					<li class="nav-item dropdown text-end"><a class="nav-link active dropdown-toggle"
							aria-current="page" href="#" id="navbarDropdownMenuLink" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> 店舗設定</a>
						<ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDropdownMenuLink"
							style="z-index: 1021;">
							<li><a class="dropdown-item active text-center" aria-current="true" href="listRoom">ルーム管理</a></li>
							<li><a class="dropdown-item text-center" href="listUser">従業員管理</a></li>
							<li><a class="dropdown-item text-center" href="listPricePlan">料金管理</a></li>
							<li><a class="dropdown-item text-center" href="listProduct">商品管理</a></li>
							<li><a class="dropdown-item text-center" href="listReceipt">レシート表示</a></li>
							<li><a id="logout-button" class="dropdown-item text-center" href="logout">ログアウト</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row mb-1">
			<div class="col-auto d-flex align-items-center">
				<span class="display-6 me-3">ルームリスト</span>
				<a href="addRoom">
					<img src="images/add_button.svg" alt="" width="30" />
				</a>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<c:if test="${not empty message }">
					<div class="error-message">
						<c:out value="${message}"></c:out>
					</div>
				</c:if>
				<div class="table-frame">
					<table class="table table-striped text-center text-nowrap table-hover">
						<thead class="sticky-top bg-light">
							<tr>
								<th>順序</th>
								<th>ID</th>
								<th>名前</th>
								<th>タイプ</th>
								<th colspan="2">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${roomList}" var="room">
								<tr>
									<td><c:out value="${room.roomOrder }" /></td>
									<td><c:out value="${room.roomId }" /></td>
									<td><c:out value="${room.roomName }" /></td>
									<td><c:out value="${room.roomTypeName }" /></td>
									<td><a href="updateRoom?roomId=<c:out value="${room.roomId }"/>">更新</a></td>
									<td><a href="deleteRoom?roomId=<c:out value="${room.roomId }"/>">削除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery-3.6.1.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>
	<script>
		$(document).ready(function() {

			$("#logout-button").on("click", function() {
				if (window.confirm("本当にログアウトしますか？")) {
					return true;
				} else {
					return false
				}
			});
		});
	</script>
</body>
</html>
