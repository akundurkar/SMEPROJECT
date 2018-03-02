package in.adcast.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CampaignDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer branchId;
	private Integer offerTypeId;
	
	private Date offerDate;
	private Date endDate;
	private String offerType;	
	private String sendBy;
	private Date reminderNotice;
	private String emailSubject;
	private String smsTemplate;
	private String emailTemplate;
	private Integer offerId;
	private boolean offerStatus;
	private Double offerDiscountPer;
	private String locationName;
	private String contactNo;
	
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	private List<String> customerType;
	
	

	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	
	public Integer getOfferTypeId() {
		return offerTypeId;
	}
	public void setOfferTypeId(Integer offerTypeId) {
		this.offerTypeId = offerTypeId;
	}
	
	
	public Date getOfferDate() {
		return offerDate;
	}
	public void setOfferDate(Date offerDate) {
		this.offerDate = offerDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	public String getOfferType() {
		return offerType;
	}
	public void setOfferType(String offerType) {
		this.offerType = offerType.trim();
	}
	
	public String getSendBy() {
		return sendBy;
	}
	public void setSendBy(String sendBy) {
		this.sendBy = sendBy.trim();
	}
	
	public Date getReminderNotice() {
		return reminderNotice;
	}
	public void setReminderNotice(Date reminderNotice) {
		this.reminderNotice = reminderNotice;
	}
	
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject.trim();
	}
	
	public String getSmsTemplate() {
		return smsTemplate;
	}
	public void setSmsTemplate(String smsTemplate) {
		this.smsTemplate = smsTemplate.trim();
	}
	
	public String getEmailTemplate() {
		return emailTemplate;
	}
	public void setEmailTemplate(String emailTemplate) {
		this.emailTemplate = emailTemplate.trim();
	}
	
	
	public boolean isOfferStatus() {
		return offerStatus;
	}
	public void setOfferStatus(boolean offerStatus) {
		this.offerStatus = offerStatus;
	}
	
	
	public List<String> getCustomerType() {
		return customerType;
	}
	public void setCustomerType(List<String> customerType) {
		this.customerType = customerType;
	}
	
	
	public void setOfferId(Integer offerId){
		this.offerId = offerId;
	}
	
	public Integer getOfferId(){
		return this.offerId;
	}
	public Double getOfferDiscountPer() {
		return offerDiscountPer;
	}
	public void setOfferDiscountPer(Double offerDiscountPer) {
		this.offerDiscountPer = offerDiscountPer;
	}
	@Override
	public String toString() {
		return "CampaignDto [branchId=" + branchId + ", offerTypeId=" + offerTypeId + ", offerDate=" + offerDate
				+ ", endDate=" + endDate + ", offerType=" + offerType + ", sendBy=" + sendBy + ", reminderNotice="
				+ reminderNotice + ", emailSubject=" + emailSubject + ", smsTemplate=" + smsTemplate
				+ ", emailTemplate=" + emailTemplate + ", offerId=" + offerId + ", offerStatus=" + offerStatus
				+ ", offerDiscountPer=" + offerDiscountPer + ", customerType=" + customerType + "]";
	}
	

}
