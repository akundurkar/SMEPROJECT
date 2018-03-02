package in.adcast.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TaxDto implements Serializable
{
	
	private Integer taxId;



	private String userId;
	private String taxName;
	
	private BigDecimal taxRate;
	
	
	
	public Integer getTaxId() {
		return taxId;
	}
	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId.trim();
	}
	public String getTaxName() {
		return taxName;
	}
	public void setTaxName(String taxName) {
		this.taxName = taxName.trim();
	}
	
	
	public BigDecimal getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	
	@Override
	public String toString() {
		return "TaxDto [taxId=" + taxId + ", userId=" + userId + ", taxName=" + taxName + ", taxRate=" + taxRate + "]";
	}
	
	
	
}
