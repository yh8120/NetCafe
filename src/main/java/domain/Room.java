package domain;

import java.util.Date;

public class Room {
	private Integer roomId;
	private String roomName;
	private Integer roomTypeId;
	private String roomTypeName;
	private Integer roomOrder;
	private Integer customerId;
	private String customerName;
	private Date started;
	private Integer cleaningId;
	private String cleaningName;
	private Boolean inUse;
	
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


	public Date getStarted() {
		return started;
	}


	public void setStarted(Date started) {
		this.started = started;
	}


	public Integer getRoomOrder() {
		return roomOrder;
	}


	public void setRoomOrder(Integer roomOrder) {
		this.roomOrder = roomOrder;
	}


	public String getRoomTypeName() {
		return roomTypeName;
	}


	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}


	public Room() {
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


	public Integer getRoomTypeId() {
		return roomTypeId;
	}


	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}


	public Integer getCleaningId() {
		return cleaningId;
	}


	public void setCleaningId(Integer cleaningId) {
		this.cleaningId = cleaningId;
	}


	public String getCleaningName() {
		return cleaningName;
	}


	public void setCleaningName(String cleaningName) {
		this.cleaningName = cleaningName;
	}


	public Boolean getInUse() {
		return inUse;
	}


	public void setInUse(Boolean inUse) {
		this.inUse = inUse;
	}

}
