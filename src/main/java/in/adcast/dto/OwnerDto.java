package in.adcast.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class OwnerDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1181025638420911146L;
	
	private Integer id;
	
	private String userId;
	private String ownerName;
	private String ownerSurname;
	private String ownerMobile;
	private String ownerAddress1;
	private String ownerAddress2;
	private String ownerState;
	private String ownerCity;
	private String postalCode;
	
	
	
	
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
	
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName.trim();
	}
	
	public String getOwnerSurname() {
		return ownerSurname;
	}
	public void setOwnerSurname(String ownerSurname) {
		this.ownerSurname = ownerSurname.trim();
	}
	
	public String getOwnerMobile() {
		return ownerMobile;
	}
	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile.trim();
	}
	
	public String getOwnerAddress1() {
		return ownerAddress1;
	}
	public void setOwnerAddress1(String ownerAddress1) {
		this.ownerAddress1 = ownerAddress1.trim();
	}
	
	public String getOwnerAddress2() {
		return ownerAddress2;
	}
	public void setOwnerAddress2(String ownerAddress2) {
		this.ownerAddress2 = ownerAddress2.trim();
	}
	
	public String getOwnerState() {
		return ownerState;
	}
	public void setOwnerState(String ownerState) {
		this.ownerState = ownerState.trim();
	}
	
	public String getOwnerCity() {
		return ownerCity;
	}
	public void setOwnerCity(String ownerCity) {
		this.ownerCity = ownerCity.trim();
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode.trim();
	}
	
	@Override
	public String toString() {
		return "OwnerDto [id=" + id + ", userId=" + userId + ", ownerName=" + ownerName + ", ownerSurname="
				+ ownerSurname + ", ownerMobile=" + ownerMobile + ", ownerAddress1=" + ownerAddress1
				+ ", ownerAddress2=" + ownerAddress2 + ", ownerState=" + ownerState + ", ownerCity=" + ownerCity
				+ ", postalCode=" + postalCode + "]";
	}
	
}
