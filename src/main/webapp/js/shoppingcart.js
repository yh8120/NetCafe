let shoppingCart=new Map();

function createTableRow(shoppingCart){
  $("#shoppingCartBody").empty();
  shoppingCart.forEach((value, name) => {
    const clonedRow = $($("#cart-row-template").html());
    clonedRow.find(".productName").text(name);
    clonedRow.find(".productUnit").text(value);
    $("#shoppingCartBody").append(clonedRow);
  })
}

$(document).ready(function () {
  $(".addcart").click(function () {
    const productName =$(this).text();
    if(shoppingCart.get(productName)){
      shoppingCart.set(productName,shoppingCart.get(productName)+1);
    }else{
      shoppingCart.set(productName,1);
    }
    createTableRow(shoppingCart);
  });
});
