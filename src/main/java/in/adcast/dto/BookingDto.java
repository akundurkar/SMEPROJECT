package in.adcast.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BookingDto implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1181025638420911146L;
	private Integer bookingId;
	private Integer branchId;
	private String orderStatus;
	
	//newly Added Fields
	
	private Integer clientId;
	private Integer organizationId;
	 
	private Date date;
	private Date bookingDate;
	private Date clientDOB;
	
	private String repeats;
	private String time;
	private String bookingType; 	
	private String mobile;
	private String newClientMobile;
	private String clientFirstName;
	private String clientLastName;
	private String ipaddress;
	private String serviceName;
	private String clientEmail;
    private String clientGender;
    private String clientAddress;
    private String clientCity;
    private String clientState;
    private String clientPincode;
    private String existingUser;
    private String invoiceNo;
    private BigDecimal lastMinDiscount;
    private Double lastMinDiscountPer;
    private String taxType;
	private Double taxRate;
    private BigDecimal amountPaid;
	
	private List<Integer> serviceIdList;
	private List<ServiceDto> serviceList;
	private List<ServiceStaffDto> serviceAndStaffList;
	
    private BigDecimal totalCost;
    private BigDecimal serviceCost;

    private Boolean maritalStatus;
    
   

    public static long getSerialversionuid() {
		return serialVersionUID;
	}
        
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus ;
	}
		
	
	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getClientDOB() {
		return clientDOB;
	}

	public void setClientDOB(Date clientDOB) {
		this.clientDOB = clientDOB;
	}
	
	
	public String getRepeats() {
		return repeats;
	}

	public void setRepeats(String repeats) {
		this.repeats = repeats ;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time ;
	}
	
	public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType ;
	}
    
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile ;
	}
	
	public String getClientFirstName() {
		return clientFirstName;
	}

	public void setClientFirstName(String clientFirstName) {
		this.clientFirstName = clientFirstName ;
	}

	public String getClientLastName() {
		return clientLastName;
	}

	public void setClientLastName(String clientLastName) {
		this.clientLastName = clientLastName ;
	}
	
	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress ;
	}
    
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName ;
	}
	
	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail ;
	}

	public String getClientGender() {
		return clientGender;
	}

	public void setClientGender(String clientGender) {
		this.clientGender = clientGender ;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress ;
	}
	
	public String getClientCity() {
		return clientCity;
	}

	public void setClientCity(String clientCity) {
		this.clientCity = clientCity ;
	}

	public String getClientState() {
		return clientState;
	}

	public void setClientState(String clientState) {
		this.clientState = clientState ;
	}

	public String getClientPincode() {
		return clientPincode;
	}

	public void setClientPincode(String clientPincode) {
		this.clientPincode = clientPincode ;
	}
	
	public String getExistingUser() {
		return existingUser;
	}

	public void setExistingUser(String existingUser) {
		this.existingUser = existingUser ;
	}
	
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo ;
	}
	
	
	public List<Integer> getServiceIdList() {
		return serviceIdList;
	}

	public void setServiceIdList(List<Integer> serviceIdList) {
		this.serviceIdList = serviceIdList;
	}
	
	public List<ServiceDto> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<ServiceDto> serviceList) {
		this.serviceList = serviceList;
	}
	
	public List<ServiceStaffDto> getServiceAndStaffList() {
		return serviceAndStaffList;
	}

	public void setServiceAndStaffList(List<ServiceStaffDto> serviceAndStaffList) {
		this.serviceAndStaffList = serviceAndStaffList;
	}
	
	
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	
	public BigDecimal getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(BigDecimal serviceCost) {
		this.serviceCost = serviceCost;
	}
    

	public Boolean getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Boolean maritalStatus) {
		this.maritalStatus = maritalStatus;
	}


	public Date getBookingDate() {
		return bookingDate;
	}


	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}


	public String getNewClientMobile() {
		return newClientMobile;
	}


	public void setNewClientMobile(String newClientMobile) {
		this.newClientMobile = newClientMobile;
	}


	public Integer getBranchId() {
		return branchId;
	}


	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}


	public Integer getBookingId() {
		return this.bookingId;
	}
	
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public BigDecimal getLastMinDiscount() {
		return lastMinDiscount;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public void setLastMinDiscount(BigDecimal lastMinDiscount) {
		this.lastMinDiscount = lastMinDiscount;
	}

	public Double getLastMinDiscountPer() {
		return lastMinDiscountPer;
	}

	public void setLastMinDiscountPer(Double lastMinDiscountPer) {
		this.lastMinDiscountPer = lastMinDiscountPer;
	}

	public BigDecimal getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}

	@Override
	public String toString() {
		return "BookingDto [bookingId=" + bookingId + ", branchId=" + branchId + ", orderStatus=" + orderStatus
				+ ", clientId=" + clientId + ", organizationId=" + organizationId + ", date=" + date + ", bookingDate="
				+ bookingDate + ", clientDOB=" + clientDOB + ", repeats=" + repeats + ", time=" + time
				+ ", bookingType=" + bookingType + ", mobile=" + mobile + ", newClientMobile=" + newClientMobile
				+ ", clientFirstName=" + clientFirstName + ", clientLastName=" + clientLastName + ", ipaddress="
				+ ipaddress + ", serviceName=" + serviceName + ", clientEmail=" + clientEmail + ", clientGender="
				+ clientGender + ", clientAddress=" + clientAddress + ", clientCity=" + clientCity + ", clientState="
				+ clientState + ", clientPincode=" + clientPincode + ", existingUser=" + existingUser + ", invoiceNo="
				+ invoiceNo + ", lastMinDiscount=" + lastMinDiscount + ", lastMinDiscountPer=" + lastMinDiscountPer
				+ ", taxType=" + taxType + ", taxRate=" + taxRate + ", amountPaid=" + amountPaid + ", serviceIdList="
				+ serviceIdList + ", serviceList=" + serviceList + ", serviceAndStaffList=" + serviceAndStaffList
				+ ", totalCost=" + totalCost + ", serviceCost=" + serviceCost + ", maritalStatus=" + maritalStatus
				+ "]";
	}

	
}
