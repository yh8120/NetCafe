package domain;

public class SalesData {
	private Integer receiptId;
	private Integer storeId;
	private Integer userId;
	private Integer customerId;
	private Integer sales;
	
	public Integer getReceiptId() {
		return receiptId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public Integer getUserId() {
		return userId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public Integer getSales() {
		return sales;
	}
	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public void setSales(Integer sales) {
		this.sales = sales;
	}

}
