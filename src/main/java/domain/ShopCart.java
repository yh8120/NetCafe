package domain;

import java.util.List;

public class ShopCart {
	private Integer roomId;
	private List<SalesData> salesData;
	
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public List<SalesData> getSalesData() {
		return salesData;
	}
	public void setSalesData(List<SalesData> salesData) {
		this.salesData = salesData;
	}

}
