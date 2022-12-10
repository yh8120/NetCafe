package domain;

import java.util.Date;

public class TempReceipt {
	private Integer roomId;
	private String roomName;
	private Date startTime;
	private Date checkOutTime;
	private Long stayTime;
	private Integer planId;
	private String planName;
	private Integer customerId;
	private String customerName;
	private Integer sumPrice;
	private Integer sumTax;
	private Integer sumDiscount;
	private Integer roomPrice;
	private Integer roomTax;
	private Integer payment;
	private Integer changeMoney;
	private Integer receiptId;
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getCheckOutTime() {
		return checkOutTime;
	}
	
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	public Long getStayTime() {
		return stayTime;
	}
	public void setStayTime(Long stayTime) {
		this.stayTime = stayTime;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public Integer getSumDiscount() {
		return sumDiscount;
	}
	public void setSumDiscount(Integer sumDiscount) {
		this.sumDiscount = sumDiscount;
	}
	public Integer getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(Integer roomPrice) {
		this.roomPrice = roomPrice;
	}
	public Integer getRoomTax() {
		return roomTax;
	}
	public void setRoomTax(Integer roomTax) {
		this.roomTax = roomTax;
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
	public Integer getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}
	

	

}
