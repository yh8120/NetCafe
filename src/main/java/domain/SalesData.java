package domain;

public class SalesData {
	private Integer salesDataId;
	private Integer receiptId;
	private Integer productId;
	private String productName;
	private Integer productPrice;
	private Integer numberProduct;
	
	public Integer getSalesDataId() {
		return salesDataId;
	}
	public void setSalesDataId(Integer salesDataId) {
		this.salesDataId = salesDataId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Integer getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	public Integer getNumberProduct() {
		return numberProduct;
	}
	public void setNumberProduct(Integer numberProduct) {
		this.numberProduct = numberProduct;
	}
	public Integer getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}
	
	

}