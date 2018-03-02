package in.adcast.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ClientNameDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1181025638420911146L;

	private String name;
	private String mobile;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name.trim();
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile.trim();
	}
	
	@Override
	public String toString() {
		return "ClientNameDto [name=" + name + ", mobile=" + mobile + "]";
	}
	
	
	
	
	
}
