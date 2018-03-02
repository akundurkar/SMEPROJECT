package in.adcast.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ServiceDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1181025638420911146L;
	
	private Integer serviceId;
	private Integer branchId;
	private Integer serviceGroupId;
	
	private String userId;
	private String serviceName;
	private String pricingTime;
	private String treatmentType;
	private String availableFor;
	private String extraTimeType;
	private String location;
	private String staffName; 
	
	private Double durationTime;
	
	private Double tax;
	
	private BigDecimal retailPrice;
	private BigDecimal specialPrice;
	private BigDecimal discount;
	
	
	
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	
	public Integer getServiceGroupId() {
		return serviceGroupId;
	}
	public void setServiceGroupId(Integer serviceGroupId) {
		this.serviceGroupId = serviceGroupId;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId.trim();
	}
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName.trim();
	}
	
	public String getPricingTime() {
		return pricingTime;
	}
	public void setPricingTime(String pricingTime) {
		this.pricingTime = pricingTime.trim();
	}
	
	public String getTreatmentType() {
		return treatmentType;
	}
	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType.trim();
	}
	
	public String getAvailableFor() {
		return availableFor;
	}
	public void setAvailableFor(String availableFor) {
		this.availableFor = availableFor.trim();
	}
	
	public String getExtraTimeType() {
		return extraTimeType;
	}
	public void setExtraTimeType(String extraTimeType) {
		this.extraTimeType = extraTimeType.trim();
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location.trim();
	}
	
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName.trim();
	}
	
	
	
	public Double getDurationTime() {
		return durationTime;
	}
	public void setDurationTime(Double durationTime) {
		this.durationTime = durationTime;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	
	
	
	public BigDecimal getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}
	
	public BigDecimal getSpecialPrice() {
		return specialPrice;
	}
	public void setSpecialPrice(BigDecimal specialPrice) {
		this.specialPrice = specialPrice;
	}
	
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	
	
	
	
	
}
