package domain;

import java.util.Date;

public class ShoppingCart {
	private Integer shoppingCartId;
	private Date salesTime;
	private Integer roomId;
	private Integer productId;
	private String productName;
	private Integer productPrice;
	private Integer productUnit;
	private Integer totalPrice;
	private Integer discount;
	private Integer taxType;
	private String taxName;
	private Integer innerTax;
	
	public Integer getShoppingCartId() {
		return shoppingCartId;
	}
	public void setShoppingCartId(Integer shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	public Integer getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(Integer productUnit) {
		this.productUnit = productUnit;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public Date getSalesTime() {
		return salesTime;
	}
	public void setSalesTime(Date salesTime) {
		this.salesTime = salesTime;
	}
	public Integer getTaxType() {
		return taxType;
	}
	public void setTaxType(Integer taxType) {
		this.taxType = taxType;
	}
	public String getTaxName() {
		return taxName;
	}
	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}
	public Integer getInnerTax() {
		return innerTax;
	}
	public void setInnerTax(Integer innerTax) {
		this.innerTax = innerTax;
	}
	

}
