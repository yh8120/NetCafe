package domain;

import java.util.Date;

public class ReceiptData {
	private Integer receiptId;
	private Integer shopId;
	private String shopName;
	private String shopPhoneNumber;
	private String shopAddress;
	private Integer userId;
	private Date printedTime;
	private Integer sumPrice;
	private Integer sumTax;
	private Integer payment;
	private Integer changeMoney;
	
	public Integer getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopPhoneNumber() {
		return shopPhoneNumber;
	}
	public void setShopPhoneNumber(String shopPhoneNumber) {
		this.shopPhoneNumber = shopPhoneNumber;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getPrintedTime() {
		return printedTime;
	}
	public void setPrintedTime(Date printedTime) {
		this.printedTime = printedTime;
	}
	public Integer getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(Integer sumPrice) {
		this.sumPrice = sumPrice;
	}
	public Integer getSumTax() {
		return sumTax;
	}
	public void setSumTax(Integer sumTax) {
		this.sumTax = sumTax;
	}

	public Integer getPayment() {
		return payment;
	}
	public void setPayment(Integer payment) {
		this.payment = payment;
	}
	public Integer getChangeMoney() {
		return changeMoney;
	}
	public void setChangeMoney(Integer changeMoney) {
		this.changeMoney = changeMoney;
	}
	


}
