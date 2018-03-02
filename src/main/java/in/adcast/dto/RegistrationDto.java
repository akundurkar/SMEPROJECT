package in.adcast.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class RegistrationDto implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String loginId;
	private String email;
	private String organisationName;
	private String organisationType;
	private String role;
	private String password;
	private String userId;
	
	/*private String roleId; //Role Type Id
	*/
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId.trim();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email.trim();
	}
	public String getOrganisationName() {
		return organisationName;
	}
	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName.trim();
	}
	public String getOrganisationType() {
		return organisationType;
	}
	public void setOrganisationType(String organisationType) {
		this.organisationType = organisationType.trim();
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role.trim();
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password.trim();
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId.trim();
	}
	
	/*public String getRoleId() {
		  return roleId;
	  }
	  public void setRoleId(String roleId) {
		  this.roleId = roleId.trim();
	  }*/
	
	@Override
	public String toString() {
		return "RegistrationDto [id=" + id + ", loginId=" + loginId + ", email=" + email + ", organisationName="
				+ organisationName + ", organisationType=" + organisationType + ", role=" + role + ", password="
				+ password + ", userId=" + userId + "]";
	}
}