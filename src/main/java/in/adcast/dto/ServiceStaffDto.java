package in.adcast.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ServiceStaffDto implements Serializable{
	
	
	private Integer serviceId;
	private Integer staffId;
	private Integer serviceCount;
	
	private String serviceName;
	private String staffName;
	
	private BigDecimal staffRevenue;
	
	private Double serviceTime;
	
	
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public Integer getServiceCount() {
		return serviceCount;
	}
	public void setServiceCount(Integer serviceCount) {
		this.serviceCount = serviceCount;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName.trim();
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName.trim();
	}


	public BigDecimal getStaffRevenue() {
		return staffRevenue;
	}
	public void setStaffRevenue(BigDecimal staffRevenue) {
		this.staffRevenue = staffRevenue;
	}
	
	public Double getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(Double serviceTime) {
		this.serviceTime = serviceTime;
	}
	
	
	@Override
	public String toString() {
		return "ServiceStaffDto [serviceId=" + serviceId + ", staffId=" + staffId + ", serviceCount=" + serviceCount
				+ ", serviceName=" + serviceName + ", staffName=" + staffName + ", staffRevenue=" + staffRevenue
				+ ", serviceTime=" + serviceTime + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
}
