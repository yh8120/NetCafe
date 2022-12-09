package domain;

import java.util.Date;
import java.util.List;

public class ReceiptData {
	private Integer receiptId;
	private Integer roomId;
	private Integer shopId;
	private Integer userId;
	private Integer customerId;
	private Date checkOutTime;
	private Integer sumPrice;
	private Integer sumTax;
	private Integer innerTax;
	private Integer innerTaxReduced;
	private Integer payment;
	private Integer changeMoney;
	private Date startTime;
	private Long timeSpent;
	//shopより
	private String shopName;
	private String shopPhoneNumber;
	private String shopAddress;
	//userより
	private String name;
	//salesDataより
	private List<SalesData> salesData;
	
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
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public Date getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	public Integer getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(Integer sumPrice) {
		this.sumPrice = sumPrice;
	}
	public Integer getInnerTax() {
		return innerTax;
	}
	public void setInnerTax(Integer innerTax) {
		this.innerTax = innerTax;
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Long getTimeSpent() {
		return timeSpent;
	}
	public void setTimeSpent(Long timeSpent) {
		this.timeSpent = timeSpent;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SalesData> getSalesData() {
		return salesData;
	}
	public void setSalesData(List<SalesData> salesData) {
		this.salesData = salesData;
	}
	
	

}
