package in.adcast.services;

import in.adcast.dto.LoginDto;
import in.adcast.dto.UserDto;
import in.adcast.exception.AuthenticationFailedException;

public interface LoginService {
	
	/**
	 * This returns Login by Email id. 
	 * @param LoginDto loginDto.
	 * @return 	UserDto.
	 */

	public UserDto findByEmailId(LoginDto loginDto) throws AuthenticationFailedException;
	
	/**
	 * This returns Login by login id. 
	 * @param LoginDto loginDto.
	 * @return 	UserDto.
	 */
	public UserDto findByLoginId(LoginDto loginDto) throws AuthenticationFailedException;
	
	/**
	 * This returns verify account. 
	 * @param LoginDto loginDto.
	 * @return 	UserDto.
	 */
	public UserDto checkAccountExistance(LoginDto loginDto);
	
	/**
	 * This returns Password reset Link Send to Mail. 
	 * @param LoginDto loginDto.
	 * @return 	boolean.
	 */
	public boolean resetPasswordLink(LoginDto loginDto);
	
	/**
	 * This returns XYZ. 
	 * @param String token.
	 * @return 	boolean.
	 */
	public boolean resetpassword(String token);
	
	/**
	 * This returns Password change. 
	 * @param LoginDto loginDto.
	 * @return 	boolean.
	 */
	public boolean changePassword(LoginDto loginDto);

	/**
	 * This method saves firebaseId of User . 
	 * @param LoginDto loginDto.
	 * @return 	UserDto.
	 */
	public UserDto saveFirebaseId(LoginDto loginDto);

	public UserDto findByUUID(String userId);

}
