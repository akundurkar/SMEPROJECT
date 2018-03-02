package in.adcast.services;


import in.adcast.dto.RegistrationDto;

public interface RegistrationService {
	
	
	
	
	/**
	 * This returns XYZ. 
	 * @param RegistrationDto registrationDto.
	 * @return boolean.
	 */
	public boolean register(RegistrationDto registrationDto);
	
	/**
	 * This returns XYZ. 
	 * @param String token.
	 * @return boolean.
	 */
	public boolean emailconfirmation(String token);

}
