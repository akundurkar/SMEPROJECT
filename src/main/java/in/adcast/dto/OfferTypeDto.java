package in.adcast.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class OfferTypeDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String userId;
	private String offerType;
	private String offerTypeName;
	
	private Date offerDate;
	

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
	
	public String getOfferType() {
		return offerType;
	}
	public void setOfferType(String offerType) {
		this.offerType = offerType.trim();
	}

	public String getOfferTypeName() {
		return offerTypeName;
	}
	public void setOfferTypeName(String offerTypeName) {
		this.offerTypeName = offerTypeName.trim();
	}
	
	
	public Date getOfferDate() {
		return offerDate;
	}
	public void setOfferDate(Date offerDate) {
		this.offerDate = offerDate;
	}
	
	
	
	@Override
	public String toString() {
		return "OfferTypeDto [id=" + id + ", userId=" + userId + ", offerType=" + offerType + ", offerTypeName="
				+ offerTypeName + ", offerDate=" + offerDate + "]";
	}
	
	
	
}
