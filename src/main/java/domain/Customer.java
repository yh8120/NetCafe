package domain;

import java.util.Date;

public class Customer {
	
	private Integer customerId;
	private Integer customerClassId;
	private String customerClassName;
	private String lastName;
	private String firstName;
	private String lastKana;
	private String firstKana;
	private Integer SexId;
	private String SexName;
	private Integer cardId;
	private String cardName;
	private String cardNumber;
	private Date birthday;
	private String zipcodePost;
	private String zipcodeCity;
	private String addressState;
	private String addressCity;
	private String addressStreet;
	private String addressRoom;
	private String Memo;
	private String phoneNumber;
	private String eMailUserName;
	private String eMailDomain;
	private Date Regestered;
	private Date Updated;
	
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getCustomerClassId() {
		return customerClassId;
	}
	public void setCustomerClassId(Integer customerClassId) {
		this.customerClassId = customerClassId;
	}
	public String getCustomerClassName() {
		return customerClassName;
	}
	public void setCustomerClassName(String customerClassName) {
		this.customerClassName = customerClassName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastKana() {
		return lastKana;
	}
	public void setLastKana(String lastKana) {
		this.lastKana = lastKana;
	}
	public String getFirstKana() {
		return firstKana;
	}
	public void setFirstKana(String firstKana) {
		this.firstKana = firstKana;
	}
	public Integer getSexId() {
		return SexId;
	}
	public void setSexId(Integer sexId) {
		SexId = sexId;
	}
	public String getSexName() {
		return SexName;
	}
	public void setSexName(String sexName) {
		SexName = sexName;
	}
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date customerBirthday) {
		this.birthday = customerBirthday;
	}
	public String getZipcodePost() {
		return zipcodePost;
	}
	public void setZipcodePost(String zipcodePost) {
		this.zipcodePost = zipcodePost;
	}
	public String getZipcodeCity() {
		return zipcodeCity;
	}
	public void setZipcodeCity(String zipcodeCity) {
		this.zipcodeCity = zipcodeCity;
	}
	public String getAddressState() {
		return addressState;
	}
	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}
	public String getAddressCity() {
		return addressCity;
	}
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}
	public String getAddressStreet() {
		return addressStreet;
	}
	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}
	public String getAddressRoom() {
		return addressRoom;
	}
	public void setAddressRoom(String addressRoom) {
		this.addressRoom = addressRoom;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String geteMailUserName() {
		return eMailUserName;
	}
	public void seteMailUserName(String eMailUserName) {
		this.eMailUserName = eMailUserName;
	}
	public String geteMailDomain() {
		return eMailDomain;
	}
	public void seteMailDomain(String eMailDomain) {
		this.eMailDomain = eMailDomain;
	}
	public Date getRegestered() {
		return Regestered;
	}
	public void setRegestered(Date regestered) {
		Regestered = regestered;
	}
	public Date getUpdated() {
		return Updated;
	}
	public void setUpdated(Date updated) {
		Updated = updated;
	}

}
