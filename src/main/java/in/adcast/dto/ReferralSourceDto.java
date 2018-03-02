package in.adcast.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ReferralSourceDto implements Serializable
{
	
	private Integer id;
	private Integer referralCode;
	private Integer referralPoint;
	
	private String userId;
	private String referralName;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getReferralCode() {
		return referralCode;
	}
	public void setReferralCode(Integer referralCode) {
		this.referralCode = referralCode;
	}
	public Integer getReferralPoint() {
		return referralPoint;
	}
	public void setReferralPoint(Integer referralPoint) {
		this.referralPoint = referralPoint;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId.trim();
	}
	
	public String getReferralName() {
		return referralName;
	}
	public void setReferralName(String referralName) {
		this.referralName = referralName.trim();
	}
	
	
	@Override
	public String toString() {
		return "ReferralSourceDto [id=" + id + ", referralCode=" + referralCode + ", referralPoint=" + referralPoint
				+ ", userId=" + userId + ", referralName=" + referralName + "]";
	}
	

	
}
