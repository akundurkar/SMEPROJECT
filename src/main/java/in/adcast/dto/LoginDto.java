package in.adcast.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class LoginDto implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	private String loginId;
	private String uniqueId;
	private String firebaseId;
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email.trim();
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password.trim();
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId.trim();
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId.trim();
	}
	public String getFirebaseId() {
		return firebaseId;
	}
	public void setFirebaseId(String firebaseId) {
		this.firebaseId = firebaseId.trim();
	}
	
	
	
	@Override
	public String toString() {
		return "LoginDto [email=" + email + ", password=" + password + ", loginId=" + loginId + ", uniqueId=" + uniqueId
				+ ", firebaseId=" + firebaseId + "]";
	}
	
	
	
}