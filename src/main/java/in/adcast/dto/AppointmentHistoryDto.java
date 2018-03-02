package in.adcast.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AppointmentHistoryDto implements Serializable {


	private Integer id;
	private Integer orderId;
	private Integer serviceId;
	private Integer staffId;
	
	private String clientName;
	private String clientlastname;
	private String serviceName;
	private String staffName;
	private String orderStatus;
	
	private Date date;
	
	private Double serviceTime;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	
	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	


	
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName.trim();
	}

	public String getClientlastname() {
		return clientlastname;
	}

	public void setClientlastname(String clientlastname) {
		this.clientlastname = clientlastname.trim();
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
	
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus.trim();
	}



	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	

	
	public Double getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(Double serviceTime) {
		this.serviceTime = serviceTime;
	}

	
	@Override
	public String toString() {
		return "AppointmentHistoryDto [id=" + id + ", orderId=" + orderId + ", serviceId=" + serviceId + ", staffId="
				+ staffId + ", clientName=" + clientName + ", clientlastname=" + clientlastname + ", serviceName="
				+ serviceName + ", staffName=" + staffName + ", orderStatus=" + orderStatus + ", date=" + date
				+ ", serviceTime=" + serviceTime + "]";
	}

	
	
	
	
	
	
}
