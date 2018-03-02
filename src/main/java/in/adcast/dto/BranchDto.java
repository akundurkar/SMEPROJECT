package in.adcast.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BranchDto implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1181025638420911146L;
    
	private Integer branchId;
	
	private String userId;
	private String locationName;
	private String phone;
	private String area;
	private String city;
	private String state;
	private String locationPin;
	private String longitude;
	private String latitude;
	private String gender;
	private String contactNo;
	private String country;
	
	private Boolean shopType;
	private Boolean online_bookng_status;
	private Boolean wiFiAvailable;
	
	
	
	
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId.trim();
	}
	
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName.trim();
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone.trim();
	}
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area.trim();
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city.trim();
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state.trim();
	}
	
	public String getLocationPin() {
		return locationPin;
	}
	public void setLocationPin(String locationPin) {
		this.locationPin = locationPin.trim();
	}
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude.trim();
	}
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude.trim();
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender.trim();
	}
	
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo.trim();
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country.trim();
	}
	
	public Boolean getShopType() {
		return shopType;
	}
	public void setShopType(Boolean shopType) {
		this.shopType = shopType;
	}
	
	public Boolean getOnline_bookng_status() {
		return online_bookng_status;
	}
	public void setOnline_bookng_status(Boolean online_bookng_status) {
		this.online_bookng_status = online_bookng_status;
	}
	
	public Boolean getWiFiAvailable() {
		return wiFiAvailable;
	}
	public void setWiFiAvailable(Boolean wiFiAvailable) {
		this.wiFiAvailable = wiFiAvailable;
	}
	
	
	@Override
	public String toString() {
		return "BranchDto [branchId=" + branchId + ", userId=" + userId + ", locationName=" + locationName + ", phone="
				+ phone + ", area=" + area + ", city=" + city + ", state=" + state + ", locationPin=" + locationPin
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", gender=" + gender + ", contactNo="
				+ contactNo + ", country=" + country + ", shopType=" + shopType + ", online_bookng_status="
				+ online_bookng_status + ", wiFiAvailable=" + wiFiAvailable + "]";
	}
	
	
	
	
	
}
