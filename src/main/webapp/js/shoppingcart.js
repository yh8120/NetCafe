let shoppingCart = new Map();

class Product {
	constructor(productId, productPrice, productUnit) {
		this.productId = productId;
		this.productPrice = productPrice;
		this.productUnit = productUnit;
	}
}

function creatUnitButton(productName) {
	const clonedButton = $($("#unit-button-template").html());
	clonedButton.find(".btn").attr("data-pname", productName);

	clonedButton.find(".btn-increase").click(function() {
		const productName = $(this).data("pname");
		let product = shoppingCart.get(productName);
		product.productUnit++
		shoppingCart.set(productName, product);
		createTableRow(shoppingCart);
	});

	clonedButton.find(".btn-decrease").click(function() {
		const productName = $(this).data("pname");
		let product = shoppingCart.get(productName);
		product.productUnit--
		shoppingCart.set(productName, product);
		createTableRow(shoppingCart);
	});
	return clonedButton;
}

function createTableRow(shoppingCart) {
	$("#shoppingCartBody").empty();
	let sumPrice = 0;
	shoppingCart.forEach((value, name) => {
		const clonedRow = $($("#cart-row-template").html());
		const unitButton = $(creatUnitButton(name));
		clonedRow.find("#productId").text(value.productId);
		clonedRow.find("#productName").text(name);
		clonedRow.find("#productUnit").text(value.productUnit);
		const totalPrice = value.productPrice * value.productUnit;
		clonedRow.find("#priceXUnit").text(totalPrice);
		clonedRow.find("#productUnitButton").append(unitButton);
		$("#shoppingCartBody").append(clonedRow);
		sumPrice = sumPrice + totalPrice;
	})
	const sumPriceRow = $($("#sum-price-template").html());
	sumPriceRow.find("#sumPrice").text(sumPrice);
	$("#shoppingCartBody").append(sumPriceRow);
}

$(document).ready(function() {
	$('#navbar-toggler').trigger("click");

	$(".addcart").click(function() {
		const productName = $(this).text();
		if (shoppingCart.get(productName)) {
			let product = shoppingCart.get(productName);
			product.productUnit++
			shoppingCart.set(productName, product);
		} else {
			const productId = $(this).data("id");
			const productPrice = $(this).data("price");
			const product = new Product(productId, productPrice, 1)
			shoppingCart.set(productName, product);
		}
		createTableRow(shoppingCart);
	});

	$("#cartSubmit").click(function() {
		let cartArray = new Array;
		shoppingCart.forEach((value, name) => {
			if (value.productUnit != 0) cartArray.push(value);
		})
		const cartJSON = JSON.stringify(cartArray)

		const form = $(this).parents('form');
		$('<input>').attr({
			'type': 'hidden',
			'name': 'cartJSON',
			'value': cartJSON
		}).appendTo(form);
		form.submit();

	});

});