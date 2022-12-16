package domain;

public class User {
	
	private Integer userid;
	private String loginId;
	private String loginPass;
	private String userName;
	private Integer userClassId;
	private String userClassName;
	private Integer shopId;
	private String shopName;
	
	public String getUserClassName() {
		return userClassName;
	}
	public void setUserClassName(String userClassName) {
		this.userClassName = userClassName;
	}
	public Integer getUserClassId() {
		return userClassId;
	}
	public void setUserClassId(Integer userClassId) {
		this.userClassId = userClassId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserId() {
		return userid;
	}
	public void setUserId(Integer userId) {
		this.userid = userId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginPass() {
		return loginPass;
	}
	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public Integer getUserid() {
		return userid;
	}
	public String getShopName() {
		return shopName;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	

}
