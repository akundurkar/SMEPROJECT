package in.adcast.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.adcast.common.Constants;
import in.adcast.common.utils.AppConstant;

import in.adcast.dto.LoginDto;
import in.adcast.dto.UserDto;

import in.adcast.exception.AuthenticationFailedException;

import in.adcast.services.LoginService;


@RestController

public class LoginController {
		
	@Autowired
	private LoginService service ;
	
	private boolean isCached;
	
	
	@RequestMapping(value="/rest/login", method=RequestMethod.POST)	
	public UserDto login(@RequestBody LoginDto loginDto,HttpServletResponse response){
		
		if(!isCached){			
			isCached = true;			
		}	
		
		//check for first time login (i.e. email is confirmed or not) 
		UserDto dto = null;
		try{
			
		 //dto = service.findByEmailId(loginDto);
		 dto = service.findByLoginId(loginDto);
		 dto.setLoginStatus('Y'); // Accoount present user validated
		
		}catch(AuthenticationFailedException ae){
		
			dto = service.checkAccountExistance(loginDto);
			
			if(dto != null){
				
				dto.setLoginStatus('E');  //Account present 
			
			}else{
			
				dto = new UserDto();
				dto.setLoginStatus('N');
			}
		}
				
		return dto;
	}
	
	
	
	@RequestMapping(value="/rest/login/checkEmailExistance", method=RequestMethod.POST)
	public String checkEmailExistance(@RequestBody LoginDto loginDto){		
		UserDto userDto = service.checkAccountExistance(loginDto);
		if(userDto != null)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	

	
	@RequestMapping(value="/rest/login/saveFirebaseId", method=RequestMethod.POST)
	public String saveFirebaseId(@RequestBody LoginDto loginDto){
		
		UserDto userDto = service.saveFirebaseId(loginDto);
		if(userDto != null)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	
	@RequestMapping(value="/rest/login/resetpasswordlink", method=RequestMethod.POST)
	public String resetpasswordLink(@RequestBody LoginDto loginDto){
		
		boolean success = service.resetPasswordLink(loginDto);
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	
	@RequestMapping(value="/rest/login/resetpassword", method=RequestMethod.GET)
	@ResponseBody
	public void resetpassword(HttpServletResponse response,@RequestParam String token){
		
		try {
			//token = URLDecoder.decode(token, "UTF-8");
			if(service.resetpassword(token))
			{
				//redirect to login screen
				response.sendRedirect(Constants.SERVER_URL+"/#passwordreset");
			}
			else{
				//redirect to link is not valid
				response.sendRedirect(Constants.SERVER_URL+"/expiredEmailConfirmation");
			}
		} catch (IOException e) {

			System.out.println("emailconfirmation service failed");
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/rest/login/changepassword", method=RequestMethod.POST)
	@ResponseBody
	public String changePassword(@RequestBody LoginDto loginDto){
		boolean success = service.changePassword(loginDto);
		if(success)
		return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
}
