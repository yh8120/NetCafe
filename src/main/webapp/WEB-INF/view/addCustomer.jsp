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
					<span class="p-country-name" style="display: none;">Japan</span>

					<div class="mb-3">
						<label for="formCustomerId">会員番号</label>
						<c:if test="${not empty customerIdError }">
							<div class="error-message">
								<c:out value="${customerIdError}"></c:out>
							</div>
						</c:if>
						<input type="text" name="customerId" id="formCustomerId" class="form-control" value=<c:out value="${customerId }"/>>
					</div>

					<div class="mb-3">
						<label for="formCustomerClass">会員クラス</label>
						<select disabled name="customerClassId" id="formCustomerClassId" class="form-control">
							<c:forEach items="${customerClassList}" var="customerClass">
								<option id="formCustomerClassId<c:out value="${customerClass.customerClassId}" />" value="<c:out value="${customerClass.customerClassId}" />"
									<c:if test="${customerClass.customerClassId == customerClassId}">selected</c:if>>
									<c:out value="${customerClass.customerClassName}" />
								</option>
							</c:forEach>
						</select>
					</div>

					<div class="mb-3">
						<label for="formName">姓</label>
						<c:if test="${not empty lastNameError }">
							<div class="error-message">
								<c:out value="${lastNameError}"></c:out>
							</div>
						</c:if>
						<input disabled type="text" name="lastName" id="formLastName" class="form-control" value=<c:out value="${lastName }"/>>
						<label for="formFirstName">名</label>
						<c:if test="${not empty firstNameError }">
							<div class="error-message">
								<c:out value="${firstNameError}"></c:out>
							</div>
						</c:if>
						<input disabled type="text" name="firstName" id="formFirstName" class="form-control" value=<c:out value="${firstName }"/>>
					</div>

					<div class="mb-3">
						<label for="formKana">かな</label>
						<c:if test="${not empty lastKanaError }">
							<div class="error-message">
								<c:out value="${lastKanaError}"></c:out>
							</div>
						</c:if>
						<input disabled type="text" name="lastKana" id="formLastKana" class="form-control" value=<c:out value="${lastKana }"/>>
						<label for="formFirstKana">名</label>
						<c:if test="${not empty firstKanaError }">
							<div class="error-message">
								<c:out value="${firstKanaError}"></c:out>
							</div>
						</c:if>
						<input disabled type="text" name="firstKana" id="formFirstKana" class="form-control" value=<c:out value="${firstKana }"/>>
					</div>

					<div class="mb-3">
						<label for="formSexId">性別</label>
						<select disabled name="sexId" id="formSexId" class="form-control">
							<c:forEach items="${sexList}" var="sex">
								<option value="<c:out value="${sex.sexId}" />" <c:if test="${sex.sexId == SexId}">selected</c:if>>
									<c:out value="${sex.sexName}" />
								</option>
							</c:forEach>
						</select>
					</div>

					<div class="mb-3">
						<label for="formCardId">身分証</label>
						<select disabled name="cardId" id="formCardId" class="form-control">
							<c:forEach items="${idCardList}" var="idCard">
								<option value="<c:out value="${idCard.cardId}" />" <c:if test="${idCard.cardId == cardId}">selected</c:if>>
									<c:out value="${idCard.cardName}" />
								</option>
							</c:forEach>
						</select>
					</div>

					<div class="mb-3">
						<label for="formCardNumber">身分証番号</label>
						<c:if test="${not empty cardNumberError }">
							<div class="error-message">
								<c:out value="${cardNumberError}"></c:out>
							</div>
						</c:if>
						<input disabled type="text" name="cardNumber" id="formCardNumber" class="form-control" value=<c:out value="${cardNumber }"/>>
					</div>

					<div class="mb-3">
						<label for="formBirthday">生年月日</label>
						<c:if test="${not empty birthdayError }">
							<div class="error-message">
								<c:out value="${birthdayError}"></c:out>
							</div>
						</c:if>
						<div class="col-auto">
							<input disabled type="date" name="birthday" id="formBirthday" class="form-control" value=<c:out value="${birthday }"/>>
						</div>
					</div>

					<div class="mb-3">
						<label for="formZipcodePost">郵便番号</label>
						<c:if test="${not empty zipcodeError }">
							<div class="error-message">
								<c:out value="${zipcodeError}"></c:out>
							</div>
						</c:if>
						<input disabled type="number" name="zipcodePost" id="formZipcodePost" class="form-control p-postal-code" size="3" maxlength="3" value=<c:out value="${zipcodePost }"/>>
						-
						<input disabled type="number" name="zipcodCity" id="formZipcodeCity" class="form-control p-postal-code" size="4" maxlength="4" value=<c:out value="${zipcodeCity }"/>>
					</div>

					<div class="mb-3">
						<label for="formAddressState">都道府県</label>
						<c:if test="${not empty addressStateError }">
							<div class="error-message">
								<c:out value="${addressStateError}"></c:out>
							</div>
						</c:if>
						<input disabled type="text" name="addressState" id="formAddressState" class="form-control p-region" value=<c:out value="${addressState }"/>>
						<label for="formAddressCity">市区町村</label>
						<c:if test="${not empty addressCityError }">
							<div class="error-message">
								<c:out value="${addressCityError}"></c:out>
							</div>
						</c:if>
						<input disabled type="text" name="addressCity" id="formAddressCity" class="form-control p-locality" value=<c:out value="${addressCity }"/>>
						<label for="formAddressStreet">番地</label>
						<c:if test="${not empty addressStreetError }">
							<div class="error-message">
								<c:out value="${addressStreetError}"></c:out>
							</div>
						</c:if>
						<input disabled type="text" name="addressStreet" id="formAddressStreet" class="form-control p-street-address p-extended-address" value=<c:out value="${addressStreet }"/>>
						<label for="formAddress">建物・部屋</label>
						<c:if test="${not empty addressRoomError }">
							<div class="error-message">
								<c:out value="${addressRoomError}"></c:out>
							</div>
						</c:if>
						<input disabled type="text" name="addressRoom" id="formAddressRoom" class="form-control p-region p-locality p-street-address p-extended-address" value=<c:out value="${addressRoom }"/>>
					</div>

					<div class="mb-3">
						<label for="formMemo">メモ</label>
						<input type="text" name="memo" id="formMemo" class="form-control" value=<c:out value="${memo }"/>>
					</div>
					<div class="mb-3">
						<label for="formPhoneNumber1">電話番号</label>
						<c:if test="${not empty phoneNumberError }">
							<div class="error-message">
								<c:out value="${phoneNumberError}"></c:out>
							</div>
						</c:if>
						<input disabled type="text" name="phoneNumber1" id="formPhoneNumber1" class="form-control" value=<c:out value="${phoneNumberA }"/>>
						-
						<input disabled type="text" name="phoneNumber2" id="formPhoneNumber2" class="form-control" value=<c:out value="${phoneNumberB }"/>>
						-
						<input disabled type="text" name="phoneNumber3" id="formPhoneNumber3" class="form-control" value=<c:out value="${phoneNumberC }"/>>
						-
					</div>
					<div class="mb-3">
						<label for="formEMail">メールアドレス</label>
						<c:if test="${not empty eMailAddressError }">
							<div class="error-message">
								<c:out value="${eMailAddressError}"></c:out>
							</div>
						</c:if>
						<input disabled type="text" name="eMailUserName" id="formEMailUserName" class="form-control" value=<c:out value="${eMailUserName }"/>>
						@
						<input disabled type="text" name="eMailDomain" id="formEMailDomain" class="form-control" value=<c:out value="${eMailDomain }"/>>
					</div>
					<div class="mb-3">
						<input disabled type="submit" class="btn btn-success" value="登録">
						<a href="manager" class="btn btn-light">キャンセル</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="https://yubinbango.github.io/yubinbango/yubinbango.js" charset="UTF-8"></script>
	<script src="js/jquery-3.6.1.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>
	<script>
		$("#formCustomerId").change(
				function() {
					let data = {
						customerId : $("#formCustomerId").val()
					};

					$.ajax({
						url : "http://localhost:8080/NetCafe/addCustomer", // 通信先のURL
						type : "PUT", // 使用するHTTPメソッド
						data : JSON.stringify(data),
						// dataType:"json", // 応答のデータの種類
						dataType : "text", // 応答のデータの種類(xml/html/script/json/jsonp/text)

					}).done(
							function(res) {
								const customer = JSON.parse(res);
								$("#formCustomerId").attr("value",
										customer.customerId).removeAttr(
										"disabled");
								$("#formCustomerClassId").attr("value",
										customer.customerClassId).removeAttr(
										"disabled");
								$("#formLastName").attr("value",
										customer.lastName).removeAttr(
										"disabled");
								$("#formFirstName").attr("value",
										customer.firstName).removeAttr(
										"disabled");
								$("#formLastKana").attr("value",
										customer.lastKana).removeAttr(
										"disabled");
								$("#formFirstKana").attr("value",
										customer.firstKana).removeAttr(
										"disabled");
								$("#formSexId").attr("value", customer.sexId)
										.removeAttr("disabled");
								$("#formCardId").attr("value", customer.cardId)
										.removeAttr("disabled");
								$("#formCardNumber").attr("value",
										customer.cardNumber).removeAttr(
										"disabled");
								$("#formBirthday").attr("value",
										customer.birthday).removeAttr(
										"disabled");
								$("#formZipcodePost").attr("value",
										customer.zipcodePost).removeAttr(
										"disabled");
								$("#formZipcodeCity").attr("value",
										customer.zipcodeCity).removeAttr(
										"disabled");
								$("#formAddressState").attr("value",
										customer.addressState).removeAttr(
										"disabled");
								$("#formAddressCity").attr("value",
										customer.addressCity).removeAttr(
										"disabled");
								$("#formAddressStreet").attr("value",
										customer.addressStreet).removeAttr(
										"disabled");
								$("#formAddressRoom").attr("value",
										customer.addressRoom).removeAttr(
										"disabled");
								$("#formMemo").attr("value", customer.memo)
										.removeAttr("disabled");
								$("#formPhoneNumber1").attr("value",
										customer.phoneNumber1).removeAttr(
										"disabled");
								$("#formPhoneNumber2").attr("value",
										customer.phoneNumber2).removeAttr(
										"disabled");
								$("#formPhoneNumber3").attr("value",
										customer.phoneNumber3).removeAttr(
										"disabled");
								$("#formEMailUserName").attr("value",
										customer.eMailUserName).removeAttr(
										"disabled");
								$("#formEMailDomain").attr("value",
										customer.eMailDomain).removeAttr(
										"disabled");
								$("form submit").removeAttr("disabled");

								// failは、通信に失敗した時に実行される
							}).fail(function(res) {
						alert("error");
						// alwaysは、成功/失敗に関わらず実行される
					}).always(function() {
						alert("complete");
					});

					console.log(3)
				});
	</script>
</body>
</html>
