package in.adcast.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.adcast.dto.OrganisationDto;

@Service
@Transactional
public interface OrganisationService {
	
	/**
	 * This will create a user account. 
	 * @param LoginDto loginDto.
	 * @return 	UserDto.
	 */

	public Integer createNewOrganisation(OrganisationDto organisationDto);
	
	public OrganisationDto updateOrganisationDetails(OrganisationDto organisationDto);
	
}
