package in.adcast.mapper;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.adcast.dao.OrganisationDao;
import in.adcast.dao.RoleDao;

import in.adcast.dto.LoginDto;
import in.adcast.dto.UserDto;

import in.adcast.model.ApplicationUser;
import in.adcast.model.Organisation;
import in.adcast.model.Role;


//import in.showad.model.RefOrganisationType;
//import in.showad.model.RefUserstatus;

@Component
public class LoginMapper {

	@Autowired
	private RoleDao roleDao;
	
	
	@Autowired
	private OrganisationDao organisationDao;
	
	
	//@Autowired
	//private OrganisationTypeDao organisationTypeDao;
	
	//@Autowired
	//private UserStatusDao userStatusDao;
	
	
	
	public ApplicationUser prpareEntity(LoginDto productDto){
				
		//RefUserstatus refUserstatus = userStatusDao.findById(new Integer(1));;
		
		Role role1 = roleDao.findById(1);
		Role role2 = roleDao.findById(2);
		
		Organisation organisation = new Organisation();
		//organisation.setOrganisationName(productDto.getOrganisationName());
		
		organisation.setAdress1("add");
		
		organisation.setPhone("12345");
		
		organisation.setCity("city");
		
		//organisation.setRefOrganisationType(refOrganisationType);
		
		organisationDao.create(organisation);
				
		ApplicationUser user1 = new ApplicationUser();
		user1.setEmailId((productDto.getEmail()));
		
		
		user1.setFirstName("First Name");
		user1.setMobile("123456");
		user1.setPassword(productDto.getPassword());
		
		//user1.setRefUserstatus(refUserstatus);
		user1.setOrganisation(organisation);
		
		user1.getRoles().add(role1);
		user1.getRoles().add(role2);
		return user1;
		
		//return product;
	}
	public UserDto prepareDto(ApplicationUser user){
			
		 
		UserDto userDto = new UserDto();
				
		//userDto.setOrganisationType(user.getOrganisation().getRefOrganisationType().getType());
		
		userDto.setFullName(user.getFirstName() + " " + user.getLastName());
		
		if(null != user.getOrganisation())			
			userDto.setOrganisationId(user.getOrganisation().getId());
		
		if(null != user.getUniqueId())
			userDto.setUserId(user.getUniqueId());
		
		Set<Role> roles = user.getRoles();
		
		int[] rolesArray = new int[roles.size()];
		
		int i = 0;
		for (Role role : roles) {
			rolesArray[i]= role.getId();
			i++;
		}		
		
		userDto.setUserroles(rolesArray);
		
		if(null!=user.getEmailId()) userDto.setEmail(user.getEmailId());
		
		
		if(null!=user.getLoginId()) userDto.setLoginId(user.getLoginId());

		
		//if(null!=user.getPassword()) userDto.setPassword(user.getPassword());
		
		if(null!=user.getProfileCompletion()) userDto.setProfileCompletion(user.getProfileCompletion());
		
		if(null!=user.getOrganisation()) userDto.setOrganisationId(user.getOrganisation().getId());
		
		if(null!=user.getFirstName()) userDto.setFirstName(user.getFirstName());
		
		if(null!=user.getLastName()) userDto.setLastName(user.getLastName());
		
		//if(null!=user.getMobile()) userDto.setMobile(user.getMobile());
		
		return userDto;
	}
}
