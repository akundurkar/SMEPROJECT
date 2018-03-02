package in.adcast.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BusinessTypeDto implements Serializable{
	
	private Integer businessTypeId;
	
	private String businessTypeName;
	
	private Integer businessCategoryTypeId;
	
	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public String getBusinessTypeName() {
		return businessTypeName;
	}

	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}

	public Integer getBusinessCategoryTypeId() {
		return businessCategoryTypeId;
	}

	public void setBusinessCategoryTypeId(Integer businessCategoryTypeId) {
		this.businessCategoryTypeId = businessCategoryTypeId;
	}
	
	
	


	
	
}
