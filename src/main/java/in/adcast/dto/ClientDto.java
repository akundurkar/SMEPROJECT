package in.adcast.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ClientDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1181025638420911146L;
	
	private Integer orderid;
	
	private String userId;
	private String clientId;
	private String fname;
	private String lname;	
	private String ownermobile;
	private String ownertelephone;
	private String email;
	private String notificationSendBy;
	private String date;
	private String month;
	private String year;
	private String ownercity;
	private String ownerstate;
	private String ownercode;
	private String gender;
	private String clientAddress;
	private String appointmentDetail;
	private Date dob;
	
	private Boolean maritalStatus;
	
	
	
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId.trim();
	}
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId.trim();
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname.trim();
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname.trim();
	}
	public String getOwnermobile() {
		return ownermobile;
	}
	public void setOwnermobile(String ownermobile) {
		this.ownermobile = ownermobile.trim();
	}
	public String getOwnertelephone() {
		return ownertelephone;
	}
	public void setOwnertelephone(String ownertelephone) {
		this.ownertelephone = ownertelephone.trim();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email.trim();
	}
	public String getNotificationSendBy() {
		return notificationSendBy;
	}
	public void setNotificationSendBy(String notificationSendBy) {
		this.notificationSendBy = notificationSendBy.trim();
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date.trim();
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month.trim();
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year.trim();
	}
	public String getOwnercity() {
		return ownercity;
	}
	public void setOwnercity(String ownercity) {
		this.ownercity = ownercity.trim();
	}
	public String getOwnerstate() {
		return ownerstate;
	}
	public void setOwnerstate(String ownerstate) {
		this.ownerstate = ownerstate.trim();
	}
	public String getOwnercode() {
		return ownercode;
	}
	public void setOwnercode(String ownercode) {
		this.ownercode = ownercode.trim();
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender.trim();
	}
	
	
	public Date getDob() {
		return this.dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;		
	}
	
	
	public Boolean getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(Boolean maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	
	
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	public String getAppointmentDetail() {
		return appointmentDetail;
	}
	public void setAppointmentDetail(String appointmentDetail) {
		this.appointmentDetail = appointmentDetail;
	}
	
		
}
