package domain;

import java.util.Date;

public class RoomUsedData {
	private Integer roomUsedDataId;
	private Integer receiptId;
	private Integer roomId;
	private String roomName;
	private Integer customerId;
	private String customerName;
	private Date startTime;
	private Date checkOutTime;
	private Long stayTime;
	private Integer losstTime;
	private Integer planId;
	private String planName;
	private Integer roomPrice;
	private Integer roomDiscount;
	private Integer roomTotalPrice;
	private Integer TaxType;
	private String TaxName;
	private Integer roomTax;
	
	public Integer getRoomUsedDataId() {
		return roomUsedDataId;
	}
	public void setRoomUsedDataId(Integer roomUsedDataId) {
		this.roomUsedDataId = roomUsedDataId;
	}
	
	public Integer getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getCheckOutTime() {
		return checkOutTime;
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
	public Integer getLosstTime() {
		return losstTime;
	}
	public void setLosstTime(Integer losstTime) {
		this.losstTime = losstTime;
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
	public Integer getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(Integer roomPrice) {
		this.roomPrice = roomPrice;
	}
	public Integer getRoomDiscount() {
		return roomDiscount;
	}
	public void setRoomDiscount(Integer roomDiscount) {
		this.roomDiscount = roomDiscount;
	}
	public Integer getRoomTotalPrice() {
		return roomTotalPrice;
	}
	public void setRoomTotalPrice(Integer roomTotalPrice) {
		this.roomTotalPrice = roomTotalPrice;
	}
	public Integer getTaxType() {
		return TaxType;
	}
	public void setTaxType(Integer taxType) {
		TaxType = taxType;
	}
	public String getTaxName() {
		return TaxName;
	}
	public void setTaxName(String taxName) {
		TaxName = taxName;
	}
	public Integer getRoomTax() {
		return roomTax;
	}
	public void setRoomTax(Integer roomTax) {
		this.roomTax = roomTax;
	}
}
