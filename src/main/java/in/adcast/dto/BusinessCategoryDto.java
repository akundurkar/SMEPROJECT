package in.adcast.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BusinessCategoryDto implements Serializable{
	
	private Integer businessCategoryid;
	
	private String businessCategoryName;
	
	private List<BusinessTypeDto> businessTypeList;
	
	
	public Integer getBusinessCategoryid() {
		return businessCategoryid;
	}

	public void setBusinessCategoryid(Integer businessCategoryid) {
		this.businessCategoryid = businessCategoryid;
	}

	public String getBusinessCategoryName() {
		return businessCategoryName;
	}

	public void setBusinessCategoryName(String businessCategoryName) {
		this.businessCategoryName = businessCategoryName.trim();
	}
    
	public List<BusinessTypeDto> getBusinessTypeList() {
		return businessTypeList;
	}

	public void setBusinessTypeList(List<BusinessTypeDto> businessTypeList) {
		this.businessTypeList = businessTypeList;
	}

	@Override
	public String toString() {
		return "BusinessCategoryDto [businessCategoryid=" + businessCategoryid + ", businessCategoryName="
				+ businessCategoryName + ", businessTypeList=" + businessTypeList + "]";
	}


}
