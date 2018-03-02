package in.adcast.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class OfferDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1181025638420911146L;
	
	private Integer offerId;
	private boolean offerStatus;
	public Integer getOfferId() {
		return offerId;
	}

	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}
	
	public void setOfferStatus(boolean offerStatus){
		this.offerStatus = offerStatus;
	}
	
	public boolean getOfferStatus(){
		return this.offerStatus;
	}
	
}
