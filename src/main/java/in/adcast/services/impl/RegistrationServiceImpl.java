package in.adcast.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.hibernate.HibernateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.adcast.common.utils.AppConstant;
import in.adcast.common.utils.AppConstant.Account;
import in.adcast.common.utils.AppConstant.AccountRole;

import in.adcast.crypto.SMECoder;

import in.adcast.dao.RoleDao;
import in.adcast.dao.UserDao;

import in.adcast.dto.RegistrationDto;

import in.adcast.exception.CustomRuntimeException;

import in.adcast.model.ApplicationUser;
import in.adcast.model.Role;

import in.adcast.services.NotificationService;
import in.adcast.services.RegistrationService;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private NotificationService notificationService;
	
	@Override
	public boolean register(RegistrationDto registrationDto) {boolean success=false;

	ApplicationUser user = new ApplicationUser();
	user.setEmailId(registrationDto.getEmail());
	user.setLoginId(registrationDto.getEmail());
	user.setPassword(SMECoder.encrypt(registrationDto.getPassword()));

	Role role = null;
	//check for Already Register
	try{
		Set<Role> roles = new HashSet<Role>();
		if(userDao.checkAccountExistance(user.getLoginId())!=null){

			success = false;

		} else {

			//user.setConfirmation false
			// save token in DB

			//String HEX_TOKEN = ShowADUtils.generateHexToken();

			//user.setConfirmationToken(HEX_TOKEN);

			UUID uniqueKey = UUID.randomUUID();
			user.setConfirmationToken(uniqueKey.toString());

			switch(registrationDto.getRole()){
			case "SHOP_OWNER":
				role = roleDao.findById(AccountRole.SHOP_OWNER.getValue());
				//user.setProfileCompletion(70);
				break;
			case "ADMINSHOP":
				role = roleDao.findById(AccountRole.ADMIN_SHOP.getValue());
				//user.setProfileCompletion(80);
				break;

			}			
			user.setProfileCompletion(25);
			user.setUserStatus(Account.DEACTIVE.getValue());  //get status from enum 1 Active, 2 New , 0 Delted
			user.setLastLogin(new Date());

			//set role Media Owner 
			//depending on Input
			//Initially Admin of Advertiser, Admin Media Owner
			//create enum

			roles.add(role);



		System.out.println("user created..--");
		String confirmationLink=null;
		
		confirmationLink = AppConstant.SERVER_URL+"/services/rest/emailconfirmation?token="+user.getConfirmationToken();
			
			user.setRoles(roles);
			userDao.create(user);

			String resetLink = AppConstant.SERVER_URL+"/unsubscribe";	 


			//send Mail  
			if(confirmationLink != null){
				
				String title = "";
				System.out.println("mail created...............");
				
				//mailService.SendMail(user.getEmailId(), confirmationLinkMessage,title);
				
				Map<String, Object> freeMarkerModel = new HashMap<String,Object>();
				freeMarkerModel.put("resetLink", resetLink);
				freeMarkerModel.put("confirmationLink", confirmationLink);
				String subject = "SME Registration Confirmation";
				
				notificationService.sendMail("confirmationTemplate.html",user.getEmailId(),freeMarkerModel,subject);
				
				System.out.println("--------------mail send success");
				success = true;
			}
			else
				System.out.println("************mail send error******************");
			success = false;
		}

	}catch(HibernateException e){
		
		throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	return success;}

	@Override
	public boolean emailconfirmation(String token) {boolean emailconfirmed = false;
	//String plainData = ShowADCoder.decrypt(token);
	//params 0 -->email
	//params 1 -->unique token
	//String params[] = plainData.split("-");
	ApplicationUser user = new ApplicationUser();
	//user.setEmailId(params[0]);
	//user.setConfirmationToken(params[1]);

	user.setConfirmationToken(token);
	try{
		if(userDao.confirmUserRegistration(user))
		{
			emailconfirmed = true;
		}
	}catch(HibernateException e){		
		throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//userDao.create((registrationMapper.mapDtoToEntity(registrationDto)));	
	return emailconfirmed;
}
	
}