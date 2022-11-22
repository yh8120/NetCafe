package domain;

public class Room {
	private Integer roomId;
	private String roomName;
	private Integer roomTypeId;
	private String roomTypeName;
	private Integer roomOrder;
	
	
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

}
