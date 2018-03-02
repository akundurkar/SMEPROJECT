package in.adcast.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ClientHistoryDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1181025638420911146L;
	
	private String serviceHisId;

	public String getServiceHisId() {
		return serviceHisId;
	}

	public void setServiceHisId(String serviceHisId) {
		this.serviceHisId = serviceHisId.trim();
	}			
	
}
