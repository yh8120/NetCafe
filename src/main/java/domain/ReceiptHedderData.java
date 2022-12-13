package domain;

import java.util.Date;

public class ReceiptHedderData {
	private Integer receiptId;
	private Integer shopId;
	private String shopName;
	private String shopPhoneNumber;
	private String shopAddress;
	private Integer userId;
	private Date printedTime;
	private Integer receiptType;
	private String receiptTypeName;
	
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
	public Integer getReceiptType() {
		return receiptType;
	}
	public void setReceiptType(Integer receiptType) {
		this.receiptType = receiptType;
	}
	public String getReceiptTypeName() {
		return receiptTypeName;
	}
	public void setReceiptTypeName(String receiptTypeName) {
		this.receiptTypeName = receiptTypeName;
	}
	
	

}
