package domain;

import java.util.Date;

public class Room {
	private Integer roomId;
	private String roomName;
	private Integer roomTypeId;
	private String roomTypeName;
	private Integer roomOrder;
	private Integer cleaningStatus;
	private String cleaningName;
	
	private Integer customerId;
	private String customerName;
	private Date startTime;
	private Long losstTime;
	private Integer roomDiscount;
	private String roomUuid;
	private Boolean inUse;
	
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
	public Integer getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public String getRoomTypeName() {
		return roomTypeName;
	}
	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}
	public Integer getRoomOrder() {
		return roomOrder;
	}
	public void setRoomOrder(Integer roomOrder) {
		this.roomOrder = roomOrder;
	}
	public Integer getCleaningStatus() {
		return cleaningStatus;
	}
	public void setCleaningStatus(Integer cleaningStatus) {
		this.cleaningStatus = cleaningStatus;
	}
	public String getCleaningName() {
		return cleaningName;
	}
	public void setCleaningName(String cleaningName) {
		this.cleaningName = cleaningName;
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
	public Long getLosstTime() {
		return losstTime;
	}
	public void setLosstTime(Long losstTime) {
		this.losstTime = losstTime;
	}
	public Integer getRoomDiscount() {
		return roomDiscount;
	}
	public void setRoomDiscount(Integer roomDiscount) {
		this.roomDiscount = roomDiscount;
	}
	public String getRoomUuid() {
		return roomUuid;
	}
	public void setRoomUuid(String roomUuid) {
		this.roomUuid = roomUuid;
	}
	public Boolean getInUse() {
		return inUse;
	}
	public void setInUse(Boolean inUse) {
		this.inUse = inUse;
	}
	
	

}
