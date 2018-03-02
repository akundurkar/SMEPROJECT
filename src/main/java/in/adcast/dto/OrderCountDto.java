package in.adcast.dto;

import java.io.Serializable;
import java.math.BigDecimal;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class OrderCountDto implements Serializable {


	private Long orderCount;
	private Long noShowCount;
	private BigDecimal totalSales;
	private BigDecimal outstanding;
	
	
	public Long getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Long orderCount) {
		this.orderCount = orderCount;
	}
	public Long getNoShowCount() {
		return noShowCount;
	}
	public void setNoShowCount(Long noShowCount) {
		this.noShowCount = noShowCount;
	}
	public BigDecimal getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(BigDecimal totalSales) {
		this.totalSales = totalSales;
	}
	public BigDecimal getOutstanding() {
		return outstanding;
	}
	public void setOutstanding(BigDecimal outstanding) {
		this.outstanding = outstanding;
	}
	
}
