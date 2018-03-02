package in.adcast.services;

import java.util.List;

import in.adcast.dto.BusinessCategoryDto;
import in.adcast.dto.LoginDto;
import in.adcast.dto.OrganisationDto;
import in.adcast.dto.OwnerDto;
import in.adcast.dto.UserDto;

public interface UserService {
	
	/**
	 * This will create a user account. 
	 * @param LoginDto loginDto.
	 * @return 	UserDto.
	 */

	public UserDto createUserAccount(LoginDto loginDto);

	public void update(String userId, Integer organisation_id);

	public OrganisationDto getOrgDetailsByUserID(String userId);

	public OwnerDto getOwnerDetailsByUserID(String userId);
	
	public List<BusinessCategoryDto> getAllBusinessType();

	public OwnerDto updateOwnerDetails(OwnerDto ownerDtoReq);

	
	
}
