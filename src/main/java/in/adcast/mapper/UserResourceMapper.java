package in.adcast.mapper;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.adcast.common.utils.AppConstant;
import in.adcast.crypto.SMECoder;
import in.adcast.dao.UserDao;
import in.adcast.dto.StaffDto;
import in.adcast.dto.UserDto;
import in.adcast.model.ApplicationUser;

@Component
public class UserResourceMapper {

	@Autowired
	private UserDao userDao;
	
	public ApplicationUser prepareEntity(UserDto userDto){
		
		ApplicationUser user = new ApplicationUser();
		
		if(null!=userDto.getUserId()) user.setId(userDao.findByUUID(userDto.getUserId()).getId());
		if(null!=userDto.getLastName()) user.setFirstName(userDto.getLastName());
		return user;
	}
	
	public UserDto prepareDto(ApplicationUser user){
		UserDto userDto = new UserDto();
		
		if(null!=user.getId())
			userDto.setStaffId(user.getId());
		if(null!=user.getUniqueId())
			userDto.setUserId(user.getUniqueId());
		if(null!=user.getFirstName()) 
			userDto.setFirstName((user.getFirstName()));
		if(null!=user.getLastName()) 
			userDto.setLastName((user.getLastName()));
		if(null!=user.getFirstName())
			userDto.setFullName(user.getFirstName()+" "+user.getLastName());
		if(null!= user.getMobile())
			userDto.setMobile(user.getMobile());
		if (null!= user.getEmailId())
			userDto.setEmail(user.getEmailId());
		
		return userDto;
	}

	public ApplicationUser prepareEntity(StaffDto staffDto) {
		
		ApplicationUser staff = new ApplicationUser();
		
		if(null!=staffDto.getStaffId())
			staff.setId(Integer.parseInt(staffDto.getStaffId()));
		if(null!=staffDto.getfName() && null!=staffDto.getlName())
		{
			staff.setFirstName(staffDto.getfName());
			staff.setLastName(staffDto.getlName());
			staff.setPassword(SMECoder.encrypt(staffDto.getfName()+"@"+staffDto.getlName()));
		}
		
		if(null!=staffDto.getMob())
			staff.setMobile(staffDto.getMob());
		if(null!=staffDto.getEmail())
		{
			staff.setEmailId(staffDto.getEmail());
			staff.setLoginId(staffDto.getEmail());
		}
		if(null!=staffDto.getStartDate())
			staff.setStartDate(staffDto.getStartDate());
		if(null!=staffDto.getFinishDate())
			staff.setFinishDate(staffDto.getFinishDate());
		if(null!=staffDto.getAddress())
			staff.setAddress1(staffDto.getAddress());
		if(null!=staffDto.getCity())
			staff.setCity(staffDto.getCity().toUpperCase());
		if(null!=staffDto.getState())
			staff.setState(staffDto.getState().toUpperCase());
		if(null!=staffDto.getPincode())
			staff.setPincode(staffDto.getPincode());
		if(null!=staffDto.getUserPermission())
			staff.setUserPermission(staffDto.getUserPermission());
		staff.setLastLogin(new Date());
		staff.setUserStatus(AppConstant.Account.ACTIVE.getValue());
		staff.setProfileCompletion(100);
		
		return staff;
	}

	public StaffDto prepareDto(ApplicationUser applicationUser, String string) {
		
		return null;
	}
	
	public StaffDto prepareStaffDto(ApplicationUser user){
		StaffDto staffDto = new StaffDto();
		
         if(user.getFirstName()!=null)
        	 staffDto.setfName(user.getFirstName());
         if(user.getLastName()!=null)
        	 staffDto.setlName(user.getLastName());
         if(user.getMobile()!=null)
        	 staffDto.setMob(user.getMobile());
         if(user.getEmailId()!=null)
        	 staffDto.setEmail(user.getEmailId());
         if(user.getStartDate()!=null)
        	 staffDto.setStartDate(user.getStartDate());
         if(user.getAddress1()!=null)
        	 staffDto.setAddress(user.getAddress1());
         if(user.getCity()!=null)
        	 staffDto.setCity(user.getCity());
         if(user.getState()!=null)
        	 staffDto.setState(user.getState());
         if(user.getPincode()!=null)
        	 staffDto.setPincode(user.getPincode());
         if(user.getUserPermission()!=null)
        	 staffDto.setUserPermission(user.getUserPermission());
         
		
		return staffDto;
		
	}
	
	

	
}
