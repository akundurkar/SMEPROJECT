package in.adcast.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class StaffDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1181025638420911146L;
	
	private Integer userPermission;
	
	private String staffId;
	private String userId;
	private String fName;
	private String lName;
	private String mob;
	private String email;
	private String state;
	private String city;
	private String pincode;
	private String address;
	
	private Date startDate;
	private Date finishDate;
	
	private String[] serviceCheckbox;
	private String[] assignLocation;
	

	
	
	public Integer getUserPermission() {
		return userPermission;
	}
	public void setUserPermission(Integer userPermission) {
		this.userPermission = userPermission;
	}
	
	
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId.trim();
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId.trim();
	}
	
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName.trim();
	}

	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName.trim();
	}

	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob.trim();
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email.trim();
	}
    
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state.trim();
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city.trim();
	}

	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode.trim();
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address.trim();
	}
	
	
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	
	
	public String[] getServiceCheckbox() {
		return serviceCheckbox;
	}
	public void setServiceCheckbox(String[] serviceCheckbox) {
		this.serviceCheckbox = serviceCheckbox;
	}

	public String[] getAssignLocation() {
		return assignLocation;
	}
	public void setAssignLocation(String[] assignLocation) {
		this.assignLocation = assignLocation;
	}
	
	
	@Override
	public String toString() {
		return "StaffDto [userPermission=" + userPermission + ", staffId=" + staffId + ", userId=" + userId + ", fName="
				+ fName + ", lName=" + lName + ", mob=" + mob + ", email=" + email + ", state=" + state + ", city="
				+ city + ", pincode=" + pincode + ", address=" + address + ", startDate=" + startDate + ", finishDate="
				+ finishDate + ", serviceCheckbox=" + Arrays.toString(serviceCheckbox) + ", assignLocation="
				+ Arrays.toString(assignLocation) + "]";
	}
	
	

	
	
}
