package in.adcast.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ClientProfileDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1181025638420911146L;

	private String clientId;
	private String fname;
	private String lname;	
	private String ownermobile;
	private String email;
	private String gender;
	private String notificationSendBy;
	
	private Date dob;
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email.trim();
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender.trim();
	}
	public String getNotificationSendBy() {
		return notificationSendBy;
	}
	public void setNotificationSendBy(String notificationSendBy) {
		this.notificationSendBy = notificationSendBy.trim();
	}
	
	

	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	
	@Override
	public String toString() {
		return "ClientProfileDto [clientId=" + clientId + ", fname=" + fname + ", lname=" + lname + ", ownermobile="
				+ ownermobile + ", email=" + email + ", gender=" + gender + ", dob=" + dob + "]";
	}
	
	
}
