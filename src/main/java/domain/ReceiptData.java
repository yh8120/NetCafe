package domain;

import java.util.Date;
import java.util.List;

public class ReceiptData {
	private Integer receiptId;
	private Integer shopId;
	private Integer userId;
	private Integer customerId;
	private Date salesDate;
	private Integer sumPrice;
	private Integer payment;
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
	public Date getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}
	public Integer getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(Integer sumPrice) {
		this.sumPrice = sumPrice;
	}
	public Integer getPayment() {
		return payment;
	}
	public void setPayment(Integer payment) {
		this.payment = payment;
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
