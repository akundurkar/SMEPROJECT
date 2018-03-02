package in.adcast.dto;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import in.adcast.model.StaffSchedule;

@JsonInclude(Include.NON_NULL)
public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1181025638420911146L;
	
	private Integer staffId; 
	
	private int organisationId;
	private int profileCompletion;
	
	private int userroles[];
	
	private String userId;
	private String fullName;
	private String firstName;
	private String lastName;
	private String mobile;
	private String email;
	private String password;
	private String jobTitle;
	private String role;
	private String roleType;
	private String organisationType;
	private String organisationName;
    private String lastLogin;
	private String loginId;
	private StaffScheduleDto staffScheduleDto;
	
	private Character loginStatus;
	private Character status;
	private Character isTeamMember;
	
	private Date lastLoginDatetime;
	//private String roleName;
	
	
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	
	
	public int getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}
	public int getProfileCompletion() {
		return profileCompletion;
	}
	public void setProfileCompletion(int profileCompletion) {
		this.profileCompletion = profileCompletion;
	}
	
	
	public int[] getUserroles() {
		return userroles;
	}
	public void setUserroles(int[] userroles) {
		this.userroles = userroles;
	}
	
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId.trim();
	}
    
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName.trim();
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName.trim();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName.trim();
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile.trim();
	}
	
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
	
	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle.trim();
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role.trim();
	}
	
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType.trim();
	}
	
	public String getOrganisationType() {
		return organisationType;
	}

	public void setOrganisationType(String organisationType) {
		this.organisationType = organisationType.trim();
	}
	
	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName.trim();
	}
	
	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin.trim();
	}
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId.trim();
	}
	
	
	
	public Character getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Character loginStatus) {
		this.loginStatus = loginStatus;
	}
    
	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}
	
	public Character getIsTeamMember() {
		return isTeamMember;
	}

	public void setIsTeamMember(Character isTeamMember) {
		this.isTeamMember = isTeamMember;
	}
	
	
	public StaffScheduleDto getStaffScheduleDto() {
		return staffScheduleDto;
	}
	public void setStaffScheduleDto(StaffScheduleDto staffScheduleDto) {
		this.staffScheduleDto = staffScheduleDto;
	}
	public Date getLastLoginDatetime() {
		return lastLoginDatetime;
	}

	public void setLastLoginDatetime(Date lastLoginDatetime) {
		this.lastLoginDatetime = lastLoginDatetime;
	}
	
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		result.append(this.getClass().getName());
		result.append(" Object {");
		result.append(newLine);

		//determine fields declared in this class only (no fields of superclass)
		Field[] fields = this.getClass().getDeclaredFields();

		//print field names paired with their values
		for (Field field : fields) {
			result.append("  ");
			try {
				result.append(field.getName());
				result.append(": ");
				//requires access to private field:
				result.append(field.get(this));
			} catch (IllegalAccessException ex) {
				System.out.println(ex);
			}
			result.append(newLine);
		}
		result.append("}");

		return result.toString();
	}

	
}
