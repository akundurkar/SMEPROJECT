package in.adcast.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CancelOrderDto implements Serializable
{
	
	private Integer id;
	private String userId;
	private String cancel_order_reason;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId.trim();
	}
	
	public String getCancel_order_reason(){
		return cancel_order_reason;
	}
	public void setCancel_order_reason(String cancel_order_reason) {
		
		this.cancel_order_reason = cancel_order_reason.trim();
	}

	@Override
	public String toString() {
		return "CancelOrderDto [id=" + id + ", userId=" + userId + ", cancel_order_reason=" + cancel_order_reason + "]";
	}

	
	
	
	
}
