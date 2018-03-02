package in.adcast.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.adcast.common.utils.AppConstant;
import in.adcast.dto.BusinessCategoryDto;
import in.adcast.dto.OrganisationDto;
import in.adcast.dto.OwnerDto;
import in.adcast.exception.CustomRuntimeException;
import in.adcast.services.OrganisationService;
import in.adcast.services.UserService;


@RestController
public class BusinessAccountController {

	@Autowired
	private OrganisationService organisationService;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/rest/businessacccontroller/saveCompanyDetails", method=RequestMethod.POST)	
	public String saveCompanyDetails(@RequestBody OrganisationDto organisationDto ,HttpServletResponse response){		
		
		Integer organisation_id = organisationService.createNewOrganisation(organisationDto);
		if(organisation_id != null)
		{
			userService.update(organisationDto.getUserId(),organisation_id);			
			return AppConstant.SUCCESS;
		}
		else
		{
			return AppConstant.ERROR;
		}
	}
	
	@RequestMapping(value="/rest/getCompanyProfileDetails", method=RequestMethod.POST)
	public OrganisationDto getCompanyProfileDetails(@RequestParam(required = false) String userId) {
		
		OrganisationDto dto = userService.getOrgDetailsByUserID(userId);
		
		return dto;
	}
	
	@RequestMapping(value="/rest/getAllBusinessType", method=RequestMethod.GET)
	public List<BusinessCategoryDto> getAllBusinessType() {
		
		List<BusinessCategoryDto> businessCategoryDtoList = null;
		
		try{
		
			businessCategoryDtoList= userService.getAllBusinessType();
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return businessCategoryDtoList;
	}
	
	@RequestMapping(value="/rest/updateCompanyDetails", method=RequestMethod.PUT)	
	public OrganisationDto updateCompanyDetails(@RequestBody OrganisationDto organisationDtoReq ,HttpServletResponse response){	
	
		return organisationService.updateOrganisationDetails(organisationDtoReq);
		
	}
	
	
	

	@RequestMapping(value="/rest/getOwnerDetails", method=RequestMethod.POST)
	public OwnerDto getOwnerDetails(@RequestParam(required = false) String userId) {
		
		OwnerDto dto = userService.getOwnerDetailsByUserID(userId);
		
		return dto;
	
	}
	
	@RequestMapping(value="/rest/updateOwnerDetails", method=RequestMethod.POST)	
	public OwnerDto updateOwnerDetails(@RequestBody OwnerDto ownerDtoReq ,HttpServletResponse response){	
	
		return userService.updateOwnerDetails(ownerDtoReq);
		
	}
	
}
