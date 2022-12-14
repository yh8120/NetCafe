package domain;

import java.util.Date;

public class RoomStatus {
	private Integer roomUsageId;//1
	private Integer roomId;//2
	private Integer customerId;//3
	private String customerName;//4
	private Date startTime;//5
	private Long losstTime;//6
	private Integer roomDiscount;//7
	private String roomUuid;//8
	private Boolean inUse;//9
	public Integer getRoomUsageId() {
		return roomUsageId;
	}
	public void setRoomUsageId(Integer roomUsageId) {
		this.roomUsageId = roomUsageId;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
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
