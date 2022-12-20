$(document).ready(function() {
	const customerId = $("#formCustomerId").val();
	if (!customerId) {
		$(".customerData").attr("disabled", "disabled");
	}

	$("#formCustomerId").change(function() {

		let data = { customerId: $("#formCustomerId").val() }

		$.ajax({
			url: "http://localhost:8080/NetCafe/addCustomer", // 通信先のURL
			type: "PUT", // 使用するHTTPメソッド
			data: JSON.stringify(data),
			// dataType:"json", // 応答のデータの種類
			dataType: "text", // 応答のデータの種類(xml/html/script/json/jsonp/text)

		}).done(function(res) {
			$(".customerData").val("");
			$(".customerData").removeAttr("disabled");
			const customer = JSON.parse(res);
			if (customer) {
				$("#formCustomerId").val(customer.customerId);
				$("#formCustomerClassId").val(customer.customerClassId);
				$("#formLastName").val(customer.lastName);
				$("#formFirstName").val(customer.firstName);
				$("#formLastKana").val(customer.lastKana);
				$("#formFirstKana").val(customer.firstKana);
				$("#formSexId").val(customer.sexId);
				$("#formCardId").val(customer.cardId);
				$("#formCardNumber").val(customer.cardNumber);
				$("#formBirthday").val(customer.strBirthday);
				$("#formZipcodePost").val(customer.zipcodePost);
				$("#formZipcodeCity").val(customer.zipcodeCity);
				$("#formAddressState").val(customer.addressState);
				$("#formAddressCity").val(customer.addressCity);
				$("#formAddressStreet").val(customer.addressStreet);
				$("#formAddressRoom").val(customer.addressRoom);
				$("#formMemo").val(customer.memo);
				$("#formPhoneNumberA").val(customer.phoneNumberA);
				$("#formPhoneNumberB").val(customer.phoneNumberB);
				$("#formPhoneNumberC").val(customer.phoneNumberC);
				$("#formEMailUserName").val(customer.eMailUserName);
				$("#formEMailDomain").val(customer.eMailDomain);
				$("#regestered").text(customer.strRegestered);
				$("#updated").text(customer.strUpdated);

				if (customer.canCopyNumber) {
					$("#formCardNumber").removeAttr("disabled");
				} else {
					$("#formCardNumber").attr("disabled", "disabled");
				}
			} else {
				$("#formCustomerClassId").val(1);
				$("#formSexId").val(1);
				$("#formCardId").val(1);
			}

		})
			.fail(function(res) { alert("error"); })
			.always(function() { });
	});

	$("#formCardId").change(function() {
		const canCopyNumber = $("#formCardId option:selected").data("can");
		if (canCopyNumber) {
			$("#formCardNumber").removeAttr("disabled");
		} else {
			$("#formCardNumber").attr("disabled", "disabled");
		}
	});
});