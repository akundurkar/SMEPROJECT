package in.adcast.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class OrganisationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1181025638420911146L;
	
	private Integer id;
	
	private String userId;
	private String businessName;
	private String city;
	private String state;
	private String officephone;
	private String website;
	private String pincode;
	private Integer businesssCategory;
	private String info;
	private String adress1;
	private String adress2;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId.trim();
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName.trim();
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
	public String getOfficephone() {
		return officephone;
	}
	public void setOfficephone(String officephone) {
		this.officephone = officephone.trim();
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website.trim();
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode.trim();
	}
	public Integer getBusinesssCategory() {
		return businesssCategory;
	}
	public void setBusinesssCategory(Integer businesssCategory) {
		this.businesssCategory = businesssCategory;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info.trim();
	}
	public String getAdress1() {
		return adress1;
	}
	public void setAdress1(String adress1) {
		this.adress1 = adress1.trim();
	}
	public String getAdress2() {
		return adress2;
	}
	public void setAdress2(String adress2) {
		this.adress2 = adress2.trim();
	}
	
	
	
	@Override
	public String toString() {
		return "OrganisationDto [id=" + id + ", userId=" + userId + ", businessName=" + businessName + ", city=" + city
				+ ", state=" + state + ", officephone=" + officephone + ", website=" + website + ", pincode=" + pincode
				+ ", businesssCategory=" + businesssCategory + ", info=" + info + "]";
	}
	
	
	
	
}
