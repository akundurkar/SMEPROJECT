package in.adcast.services.impl;

import java.util.Date;

import java.util.UUID;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.adcast.common.Constants;

import in.adcast.common.utils.SMEUtils;

import in.adcast.crypto.SMECoder;

import in.adcast.dao.UserDao;

import in.adcast.dto.LoginDto;
import in.adcast.dto.UserDto;

import in.adcast.exception.AuthenticationFailedException;
import in.adcast.exception.CustomRuntimeException;

import in.adcast.mapper.LoginMapper;

import in.adcast.model.ApplicationUser;

import in.adcast.services.LoginService;


@Service
@Transactional
public class LoginServiceImpl implements LoginService{
	
	private static final Logger LOGGER = Logger.getLogger(LoginServiceImpl.class);	
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private LoginMapper loginMapper;
	
	
	@Override
	public UserDto findByLoginId(LoginDto inLoginDto) throws AuthenticationFailedException {
		LOGGER.info("findByLoginId(LoginDto inLoginDto) ---Start");
		UserDto userDto = null;
		try{
		ApplicationUser applicationUser = userDao.authenticateUser(inLoginDto.getLoginId(),SMECoder.encrypt(inLoginDto.getPassword()));
		
		userDto = new UserDto();
		
		if(null != applicationUser){
			applicationUser.setUniqueId(UUID.randomUUID().toString().replace("-", ""));
			
			applicationUser.setLastLogin(new Date());			
			userDao.update(applicationUser);
			
			userDto = loginMapper.prepareDto(applicationUser);
			
		}
		}catch(HibernateException e){
			LOGGER.error("findByLoginId(LoginDto inLoginDto)"+e);
			e.printStackTrace();
			throw new AuthenticationFailedException("ACCOUNT DOES NOT EXISTS");
		}
		LOGGER.info("findByLoginId(LoginDto inLoginDto) ---End");
		return userDto;
	}


	@Override
	public UserDto checkAccountExistance(LoginDto loginDto) {
		LOGGER.info("checkAccountExistance(LoginDto loginDto) ---Start");
		UserDto userDto = null;
		try{
		ApplicationUser userFromDB = userDao.checkAccountExistance(loginDto.getEmail());
		if(null != userFromDB){
		
			userDto = loginMapper.prepareDto(userFromDB);
		}
		}catch(HibernateException e){
			LOGGER.error("checkAccountExistance(LoginDto loginDto)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("checkAccountExistance(LoginDto loginDto) ---End");
		return userDto;
	}


	@Override
	public boolean resetPasswordLink(LoginDto loginDto) {
		LOGGER.info("resetPasswordLink(LoginDto loginDto) ---Start");
		boolean success = false;
		String confirmationLink=null;
		String HEX_TOKEN = SMEUtils.generateHexToken();
		ApplicationUser user = new ApplicationUser();
		user.setEmailId(loginDto.getEmail());
		
		user = userDao.checkAccountExistance(loginDto.getEmail());
		
		UUID uniqueKey = UUID.randomUUID();
		user.setResetPassword(uniqueKey.toString());
		
		
		String tokenEncode = SMECoder.encrypt(user.getEmailId()+"-"+HEX_TOKEN);
		tokenEncode = user.getResetPassword();
		//confirmationLink = "https://app.bookmyled.com/services/rest/login/resetpassword?token="+tokenEncode;
			
		confirmationLink = Constants.SERVER_URL+"/services/rest/login/resetpassword?token="+tokenEncode;
		
		//confirmationLink ="http://127.0.0.1:8080/services/rest/login/resetpassword?token="+tokenEncode;
		//String resetLink = Constants.SERVER_URL+"/unsubscribe";
		if(confirmationLink != null){
			String confirmationLinkMessage = "<html><body><b>bookmyLED account</b>"+
					"<h1 style=\"color:BLUE\">Password Recovery</h1>"+
					"<p>Dear, "+user.getFirstName()+" "+user.getLastName()+
					"<br>we have received a password change request from your account </p>"+
					"<a href=\""+confirmationLink+"\">click here</a> to reset your password.</p><p>"+
					"Thanks,<br/>The bookmyLED account team</p></body></html>";
			String title = "bookmyLED Password Recovery";			
			SMEUtils.SendMail(user.getEmailId(), confirmationLinkMessage,title);			
			success = true;
			user.setResetPassValidity(new Date());
			userDao.update(user);
		} else
		{
			success = false;
		}
		LOGGER.info("resetPasswordLink(LoginDto loginDto) ---End");
		return success;
	}


	@Override
	public boolean resetpassword(String token) {
		LOGGER.info("resetpassword(String token) ---Start");
		boolean resetPasswordConfirmed = false;
		//String plainData = ShowADCoder.decrypt(token);
		//params 0 -->email
		//params 1 -->unique token
		//String params[] = plainData.split("-");
		ApplicationUser user = new ApplicationUser();
		//user.setEmailId(params[0]);
		//user.setConfirmationToken(params[1]);
		
		user.setResetPassword(token);
		try{
		if(userDao.confirmResetpassword(user))
		{
			resetPasswordConfirmed  = true;
		}
		}catch(HibernateException e){
			LOGGER.error("resetpassword(String token)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//userDao.create((registrationMapper.mapDtoToEntity(registrationDto)));
		LOGGER.info("resetpassword(String token) ---End");
		return resetPasswordConfirmed ;
	}


	@Override
	public boolean changePassword(LoginDto loginDto) {
		LOGGER.info("changePassword(LoginDto loginDto) ---Start");
		boolean passwordChanged= false;
		ApplicationUser user = null;
		try{
			user = userDao.checkAccountExistance(loginDto.getEmail());
			if(null != user.getResetPassword()){
			
		Date cur_time = new Date();
		long diff = cur_time.getTime() - user.getResetPassValidity().getTime();
		//long diffSeconds = diff / 1000;
	  long diffMinutes = diff / (60 * 1000);
	  /*long diffHours = diff / (60 * 60 * 1000);
	  long diffDays = diff / (24 * 60 * 60 * 1000);*/
		if(diffMinutes < 30){
		
			user.setPassword(SMECoder.encrypt(loginDto.getPassword()));
			user.setResetPassword("");
			userDao.update(user);
			passwordChanged = true;
		}
		else{
			user.setResetPassword("");
			userDao.update(user);
		}
		}else
		{
			user.setResetPassword("");
			userDao.update(user);
		}
		}catch(HibernateException e){
			LOGGER.error("changePassword(LoginDto loginDto)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("changePassword(LoginDto loginDto) ---End");
		return passwordChanged;
	}


	@Override
	public UserDto findByEmailId(LoginDto inLoginDto) throws AuthenticationFailedException {
		LOGGER.info("findByEmailId(LoginDto inLoginDto) ---Start");
		UserDto userDto = null;
		try{
		ApplicationUser applicationUser = userDao.authenticateUser(inLoginDto.getEmail(),SMECoder.encrypt(inLoginDto.getPassword()));
		
		userDto = new UserDto();
		
		if(null != applicationUser){
			applicationUser.setUniqueId(UUID.randomUUID().toString().replace("-", ""));
			
			applicationUser.setLastLogin(new Date());			
			userDao.update(applicationUser);
			
			userDto = loginMapper.prepareDto(applicationUser);
			
		}
		}catch(HibernateException e){
			LOGGER.error("findByEmailId(LoginDto inLoginDto)"+e);
			e.printStackTrace();
			throw new AuthenticationFailedException("ACCOUNT DOES NOT EXISTS");
		}
		LOGGER.info("findByEmailId(LoginDto inLoginDto) ---End");
		return userDto;
	}


	@Override
	public UserDto saveFirebaseId(LoginDto loginDto) {
		LOGGER.info("saveFirebaseId(LoginDto loginDto) ---Start");
		UserDto userDto = null;
		try{
		ApplicationUser applicationUser = userDao.findByUUID(loginDto.getUniqueId());
		
		userDto = new UserDto();
		
		if(null != applicationUser){
			applicationUser.setFirebaseId(loginDto.getFirebaseId());
			
			applicationUser.setLastLogin(new Date());			
			userDao.update(applicationUser);
			
			userDto = loginMapper.prepareDto(applicationUser);
			
		}
		}catch(HibernateException e){
			LOGGER.error("saveFirebaseId(LoginDto loginDto)"+e);
			e.printStackTrace();
		}
		LOGGER.info("saveFirebaseId(LoginDto loginDto) ---End");
		return userDto;
	}


	@Override
	public UserDto findByUUID(String userId) {
		
		userDao.findByUUID(userId);

		LOGGER.info("findByUUID(String userId) ---Start");
		UserDto userDto = null;
		try{
		ApplicationUser applicationUser = userDao.findByUUID(userId);			
		
		if(null != applicationUser){					
			userDao.update(applicationUser);			
			userDto = loginMapper.prepareDto(applicationUser);			
		}
		}catch(HibernateException e){
			LOGGER.error("findByUUID(String userId)"+e);
			e.printStackTrace();
		}
		LOGGER.info("findByUUID(String userId) ---End");
		return userDto;
	
	}

}
