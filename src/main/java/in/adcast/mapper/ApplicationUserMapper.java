package in.adcast.mapper;

import org.springframework.stereotype.Component;

import in.adcast.dto.OwnerDto;

import in.adcast.model.ApplicationUser;

@Component
public class ApplicationUserMapper {

	public ApplicationUser prepareEntity(OwnerDto ownerDto) {
		
		ApplicationUser applicationUser=new ApplicationUser();
		
		if(ownerDto.getId()!=null)
			applicationUser.setId(ownerDto.getId());
		if(ownerDto.getOwnerName()!=null)
            applicationUser.setFirstName(ownerDto.getOwnerName());
		if(ownerDto.getOwnerSurname()!=null)
			applicationUser.setLastName(ownerDto.getOwnerSurname());
		if(ownerDto.getOwnerAddress1()!=null)
			applicationUser.setAddress1(ownerDto.getOwnerAddress1());
		if(ownerDto.getOwnerAddress2()!=null)
			applicationUser.setAddress2(ownerDto.getOwnerAddress2());
		if(ownerDto.getOwnerCity()!=null)
			applicationUser.setCity(ownerDto.getOwnerCity().toUpperCase());
		if(ownerDto.getOwnerState()!=null)
			applicationUser.setState(ownerDto.getOwnerState().toUpperCase());
		if(ownerDto.getOwnerMobile()!=null)
			applicationUser.setMobile(ownerDto.getOwnerMobile());
		if(ownerDto.getPostalCode()!=null)
			applicationUser.setPincode(ownerDto.getPostalCode());
		
		return applicationUser;
	}

	public OwnerDto prepareDto(ApplicationUser applicationUser) {
		
		OwnerDto ownerDto = new OwnerDto();
	
		if(applicationUser.getFirstName()!=null)
			ownerDto.setOwnerName(applicationUser.getFirstName());
		if(applicationUser.getLastName()!=null)
			ownerDto.setOwnerSurname(applicationUser.getLastName());
		if(applicationUser.getAddress1()!=null)
			ownerDto.setOwnerAddress1(applicationUser.getAddress1());
		if(applicationUser.getAddress2()!=null)
			ownerDto.setOwnerAddress2(applicationUser.getAddress2());
		if(applicationUser.getMobile()!=null)
			ownerDto.setOwnerMobile(applicationUser.getMobile());
		if(applicationUser.getCity()!=null)
			ownerDto.setOwnerCity(applicationUser.getCity());
		if(applicationUser.getState()!=null)
			ownerDto.setOwnerState(applicationUser.getState());
	    if(applicationUser.getPincode()!=null)
	    	ownerDto.setPostalCode(applicationUser.getPincode());
		
		return ownerDto;
		
	}

}
