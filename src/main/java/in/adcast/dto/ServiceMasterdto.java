package in.adcast.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ServiceMasterdto implements Serializable 
{

	
	private Integer serviceId;
	
	private String userId;
	private String groupName;
	
	

	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId.trim();
	}  
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName.trim();
	}
	
	@Override
	public String toString() {
		return "ServiceMasterdto [serviceId=" + serviceId + ", userId=" + userId + ", groupName=" + groupName + "]";
	}
	


}
