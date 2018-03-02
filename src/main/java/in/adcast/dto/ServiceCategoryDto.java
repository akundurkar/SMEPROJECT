package in.adcast.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ServiceCategoryDto implements Serializable{
	
	private Integer id;
	
	private String serviceGroupName;
	
	private List<ServiceDto> serviceList;
	private List<ServiceDto> myServiceList;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getServiceGroupName() {
		return serviceGroupName;
	}
	public void setServiceGroupName(String serviceGroupName) {
		this.serviceGroupName = serviceGroupName.trim();
	}
	
	
	public List<ServiceDto> getServiceList() {
		return serviceList;
	}
	public void setServiceList(List<ServiceDto> serviceList) {
		this.serviceList = serviceList;
	}
	
	public List<ServiceDto> getMyServiceList() {
		return myServiceList;
	}
	public void setMyServiceList(List<ServiceDto> myServiceList) {
		this.myServiceList = myServiceList;
	}
	
	
	@Override
	public String toString() {
		return "ServiceCategoryDto [id=" + id + ", serviceGroupName=" + serviceGroupName + ", serviceList="
				+ serviceList + ", myServiceList=" + myServiceList + "]";
	}
	
	
}
