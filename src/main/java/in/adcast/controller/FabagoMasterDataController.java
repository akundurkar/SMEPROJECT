package in.adcast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import in.adcast.dto.BusinessTypeNameDto;
import in.adcast.exception.CustomRuntimeException;
import in.adcast.services.FabagoService;


@RestController
public class FabagoMasterDataController 
{

	private static final String COMMON_SERVICE_PATH = "/rest/fabago/";
	
	@Autowired
	private FabagoService fabagoService;
	
	@RequestMapping(value=COMMON_SERVICE_PATH +"/searchBusinessType/{queri}/{organizationId}", method=RequestMethod.GET)		
	public 	List<BusinessTypeNameDto> searchBusinessType(@PathVariable String queri,@PathVariable Integer organizationId) {

		List<BusinessTypeNameDto> availableName=null;
	
		try{
			
			availableName = fabagoService.searchBusinessTypeName(organizationId,queri);
		
		}catch (Exception e){
			
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return availableName;
	}

	

	
	
	
}
