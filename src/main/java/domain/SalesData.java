package domain;

import java.util.Date;

public class SalesData {
	private Integer salesDataId;
	private Date salesTime;
	private Integer roomId;
	private Integer productId;
	private Integer productUnit;
	private Integer totalPrice;
	private Integer discount;
	private Integer innerTax;
	
	
	public Integer getSalesDataId() {
		return salesDataId;
	}
	public void setSalesDataId(Integer salesDataId) {
		this.salesDataId = salesDataId;
	}
	public Date getSalesTime() {
		return salesTime;
	}
	public void setSalesTime(Date salesTime) {
		this.salesTime = salesTime;
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
	public Integer getInnerTax() {
		return innerTax;
	}
	public void setInnerTax(Integer innerTax) {
		this.innerTax = innerTax;
	}
	
	


}
