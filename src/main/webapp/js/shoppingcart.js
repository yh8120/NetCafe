let shoppingCart=new Map();

class Product{
  constructor(productId,productPrice,productUnit){
    this.productId=productId;
    this.productPrice=productPrice;
    this.productUnit=productUnit;
  }
}
function createUnitButton(productName){
  console.log(productName);
  const clonedButton = $($("#unit-button-template").html());
  clonedButton.find(".btn").attr("data-pname",productName);

  clonedButton.find(".btn-increase").click(function () {
    const productName = $(this).data("pname");
    let product = shoppingCart.get(productName);
    product.unit++
    shoppingCart.set(productName,product);
    createTableRow(shoppingCart);
  });

  clonedButton.find(".btn-decrease").click(function () {
    const productName = $(this).data("pname");
    let product = shoppingCart.get(productName);	
    product.unit--
    shoppingCart.set(productName,product);
    createTableRow(shoppingCart);
  });
  return clonedButton;
}

function createTableRow(shoppingCart){
  $("#shoppingCartBody").empty();
  let sumPrice=0;
  shoppingCart.forEach((value, name) => {
    console.log(name);
    const clonedRow = $($("#cart-row-template").html());
    const unitButton = $(createUnitButton(name));
    clonedRow.find("#productId").text(value.id);
    clonedRow.find("#productName").text(name);
    clonedRow.find("#productUnit").text(value.unit);
    clonedRow.find("#priceXUnit").text(value.price*value.unit);
    clonedRow.find("#productUnitButton").append(unitButton);
    $("#shoppingCartBody").append(clonedRow);
    sumPrice=sumPrice+(value.price*value.unit);
    console.log(sumPrice)
  })
  const sumPriceRow = $($("#sum-price-template").html());
  sumPriceRow.find("#sumPrice").text(sumPrice);
  console.log(2)
  $("#shoppingCartBody").append(sumPriceRow);
}

$(document).ready(function () {
  $('#navbar-toggler').trigger("click");

  $(".addcart").click(function () {
    const productName =$(this).text();
    if(shoppingCart.get(productName)){
      let product = shoppingCart.get(productName);
      product.unit++
      shoppingCart.set(productName,product);
    }else{
      const id = $(this).data("id");
      const price = $(this).data("price");
      const product =new Product(id,price,1)
      shoppingCart.set(productName,product);
    }
    createTableRow(shoppingCart);
  });

  $("#cartSubmit").click(function(){
	let cartArray=new Array;
	shoppingCart.forEach((value, name) => {
		value[productName]=name;
		cartArray.push(value);
	})
    const cartJSON = JSON.stringify(cartArray)
    console.log(cartArray)
    console.log(cartJSON)
    $.post(location.href,cartJSON);
  });



});
