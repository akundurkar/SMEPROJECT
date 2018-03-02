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

import in.adcast.common.utils.AppConstant;
import in.adcast.dto.RegistrationDto;

import in.adcast.services.RegistrationService;

@RestController
public class RegistrationController {

	@Autowired
	private RegistrationService service ;
	

	@RequestMapping(value="/rest/registerUser", method=RequestMethod.POST)
	@ResponseBody
	public String register(@RequestBody RegistrationDto registrationDto){
		System.out.println("in registerUser()");
		service.register(registrationDto);
		System.out.println("---registerUser() success");
		return AppConstant.SUCCESS;
	}

	@RequestMapping(value="/rest/emailconfirmation", method=RequestMethod.GET)
	@ResponseBody
	public void emailconfirmation(HttpServletResponse response,@RequestParam String token){
		
		try {
			//token = URLDecoder.decode(token, "UTF-8");
			if(service.emailconfirmation(token))
			{
				//redirect to login screen
				response.sendRedirect("http://localhost:9000/#emailConfirmed");
			}
			else{
				//redirect to link is not valid
				response.sendRedirect("http://localhost:9000/expiredEmailConfirmation");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("emailconfirmation service failed");
			e.printStackTrace();
		}
	}	
}
