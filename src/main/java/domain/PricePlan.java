package domain;

import java.util.Date;

public class PricePlan {
	private Integer planId;
	private String planName;
	private Date planStart;
	private Date planEnd;
	private String startTime;
	private String endTime;
	private Integer basicPrice;
	private Integer basicTime;
	private Integer addPrice;
	private Integer addTime;
	private PlanScope planScope;
	private Integer taxTypeId;
	private String taxTypeName;
	private Double taxRate;
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public Date getPlanStart() {
		return planStart;
	}
	public void setPlanStart(Date planStart) {
		this.planStart = planStart;
	}
	public Date getPlanEnd() {
		return planEnd;
	}
	public void setPlanEnd(Date planEnd) {
		this.planEnd = planEnd;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getBasicPrice() {
		return basicPrice;
	}
	public void setBasicPrice(Integer basicPrice) {
		this.basicPrice = basicPrice;
	}
	public Integer getBasicTime() {
		return basicTime;
	}
	public void setBasicTime(Integer basicTime) {
		this.basicTime = basicTime;
	}
	public Integer getAddPrice() {
		return addPrice;
	}
	public void setAddPrice(Integer addPrice) {
		this.addPrice = addPrice;
	}
	public Integer getAddTime() {
		return addTime;
	}
	public void setAddTime(Integer addTime) {
		this.addTime = addTime;
	}
	public PlanScope getPlanScope() {
		return planScope;
	}
	public void setPlanScope(PlanScope planScope) {
		this.planScope = planScope;
	}
	public Integer getTaxTypeId() {
		return taxTypeId;
	}
	public void setTaxTypeId(Integer taxTypeId) {
		this.taxTypeId = taxTypeId;
	}
	public String getTaxTypeName() {
		return taxTypeName;
	}
	public void setTaxTypeName(String taxTypeName) {
		this.taxTypeName = taxTypeName;
	}
	public Double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
	
	

}
