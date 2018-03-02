package in.adcast.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class OrderDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1181025638420911146L;
	
	private Integer id;
	private Integer orderId;
	
	private String invoiceNo;
	private String clientName;
	private String clientlastname;
	private String userId;
	private String orderStatus;
	
	private List<ServiceStaffDto> serviceNameAndStaffNameList;

	private BigDecimal total;
	
	private Date date;

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
	
	public String getInvoiceNo() {
		return invoiceNo;
	}
	
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo.trim();
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
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId.trim();
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus.trim();
	}
	
	public List<ServiceStaffDto> getServiceNameAndStaffNameList() {
		return serviceNameAndStaffNameList;
	}
	public void setServiceNameAndStaffNameList(List<ServiceStaffDto> serviceNameAndStaffNameList) {
		this.serviceNameAndStaffNameList = serviceNameAndStaffNameList;
	}
	
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
