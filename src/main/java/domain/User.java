package domain;

public class User {
	
	private Integer id;
	private String loginId;
	private String loginPass;
	private String name;
	private Integer userClassId;
	private String userClassName;
	
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	

}
