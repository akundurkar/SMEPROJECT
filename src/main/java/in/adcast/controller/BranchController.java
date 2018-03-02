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
import in.adcast.dto.BranchDto;
import in.adcast.dto.BranchScheduleDto;
import in.adcast.dto.ServiceCategoryDto;
import in.adcast.dto.ServiceDto;
import in.adcast.dto.ServiceMasterdto;
import in.adcast.dto.ServiceNamesDto;
import in.adcast.dto.StaffDto;
import in.adcast.dto.UserDto;

import in.adcast.exception.CustomRuntimeException;

import in.adcast.services.BranchService;
import in.adcast.services.LoginService;


@RestController
public class BranchController {
	private static final String COMMON_SERVICE_PATH = "/rest/branch/";
	
	@Autowired
	private BranchService branchService;
	
	@Autowired
	private LoginService service ;
	
	@RequestMapping(value=COMMON_SERVICE_PATH+"addNewBranch", method=RequestMethod.POST)	
	public UserDto addNewBranch(@RequestBody BranchDto branchDto ,HttpServletResponse response){
		
		boolean success;	
		success = branchService.addNewBranch(branchDto);
		if(success)
			return service.findByUUID(branchDto.getUserId());
		else
			return null;
	}
	
	@RequestMapping(value=COMMON_SERVICE_PATH+"/deleteLocationDetails", method =RequestMethod.POST)
	public String  deleteLocationDetails(@RequestBody BranchDto branchDto){
		
		boolean success = branchService.deleteLocationDetails(branchDto.getBranchId());
		
		if(success){
			return AppConstant.SUCCESS;
			}
			else{
				return AppConstant.ERROR;
			}
	}
	
	
	
	
	@RequestMapping(value="/rest/updateBranchInformation", method=RequestMethod.POST)	
	public BranchDto updateBranchInformation(@RequestBody BranchDto branchDtoReq ,HttpServletResponse response){
		
		BranchDto branchDto = branchService.updateBranchInformation(branchDtoReq);
		if(branchDto != null)
			return branchDto;
		else
			return null;
	}
	
	
	@RequestMapping(value=COMMON_SERVICE_PATH+"setBranchSchedule", method=RequestMethod.POST)	
	public String setBranchSchedule(@RequestBody BranchScheduleDto branchScheduleDto ,HttpServletResponse response){
	
		boolean success = false ;
		success = branchService.setBranchSchedule(branchScheduleDto);
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}		
	
	@RequestMapping(value="/rest/addNewService", method=RequestMethod.POST)	
	public String addNewService(@RequestBody ServiceDto serviceDtoReq ,HttpServletResponse response){
		boolean success = false ;
		success = branchService.addNewService(serviceDtoReq);
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
		
	}
	
	
	@RequestMapping(value="/rest/changeServiceDetails", method=RequestMethod.POST)	
	public List<ServiceDto> changeServiceDetails(@RequestBody ServiceDto serviceDtoReq ,HttpServletResponse response){
		return branchService.changeServiceDetails(serviceDtoReq);		
	}
	
	
	@RequestMapping(value="/rest/showAllServicesAtBranch", method=RequestMethod.POST)	
	public List<ServiceDto> showAllServicesAtBranch(@RequestBody ServiceDto serviceDtoReq ,HttpServletResponse response){
		return branchService.showAllServicesAtBranch(serviceDtoReq);
	}
	
	@RequestMapping(value="/rest/deleteServiceDetails", method =RequestMethod.POST)
	public String  deleteServiceDetails(@RequestBody ServiceDto serviceDto){
		
		boolean success = branchService.deleteServiceDetails(serviceDto.getServiceId());
		
		if(success){
			return AppConstant.SUCCESS;
			}
			else{
				return AppConstant.ERROR;
			}
	}
	
	
	@RequestMapping(value="/rest/addNewStaff", method=RequestMethod.POST)	
	public List<StaffDto> addNewStaff(@RequestBody StaffDto staffDtoReq ,HttpServletResponse response){
		return branchService.addNewStaff(staffDtoReq);
	}
	

	@RequestMapping(value="/rest/changeStaffDetails", method=RequestMethod.POST)	
	public List<StaffDto> changeStaffDetails(@RequestBody StaffDto staffDtoReq ,HttpServletResponse response){
		return branchService.changeStaffDetails(staffDtoReq);
	}
	
	
	@RequestMapping(value="/rest/addNewServiceGroup", method=RequestMethod.POST)	
	public String addNewServiceGroup(@RequestBody ServiceMasterdto serviceMasterdto ,HttpServletResponse response)
	{
		boolean result = false ;
		result= branchService.addNewServiceGroup(serviceMasterdto);
		if(result)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	
	@RequestMapping(value=COMMON_SERVICE_PATH +"/list", method=RequestMethod.GET)		
	public 	List<BranchDto> getAllLocation(@RequestParam(required = false) String userId) 
	{
		List<BranchDto> branchDtoList = null;
		
		try{
			
			branchDtoList = branchService.findAll(userId,null);
			
		}catch (Exception e){
			
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return branchDtoList;
	}
	
	@RequestMapping(value=COMMON_SERVICE_PATH +"/getCompanyLocation", method=RequestMethod.GET)
	public BranchDto getCompanyLocation(@RequestParam(required = false) Integer branchId)
	{
		BranchDto branchDto = null;
		try{
			branchDto=branchService.getCompanyLocation(branchId);
		} catch (Exception e){
			
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return branchDto;
	}
	
	@RequestMapping(value=COMMON_SERVICE_PATH +"/listBranchesForOrg", method=RequestMethod.GET)		
	public 	List<BranchDto> getAllLocationForOrganization(@RequestParam(required = false) Integer orgId) 
	{
		List<BranchDto> branchDtoList = null;
		
		try{
			
			branchDtoList = branchService.findAll(null,orgId);
			
		}catch (Exception e){
			
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return branchDtoList;
	}
	
	@RequestMapping(value="rest/listServices", method=RequestMethod.GET)		
	public 	List<ServiceCategoryDto> listServices(@RequestParam(required = false) Integer organisationId) 
	{
		List<ServiceCategoryDto> servicesDtoList = null;
		
		try{
			
			servicesDtoList = branchService.listServicesByOrganisation(organisationId);
			
		}catch (Exception e){
			
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return servicesDtoList;
	}
	
	
	@RequestMapping(value=COMMON_SERVICE_PATH +"/searchServiceNameList", method=RequestMethod.GET)		
	public 	List<ServiceNamesDto> searchServiceNameList(@RequestParam(required = false) String userId) 
	{
		List<ServiceNamesDto> serviceNameList=null;
	
		try{
			
			serviceNameList = branchService.searchServiceNameList(userId);
			
		} catch (Exception e){
			
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return serviceNameList;
	}
	
	@RequestMapping(value=COMMON_SERVICE_PATH +"/searchStaffNameList", method=RequestMethod.GET)
	public List<UserDto> searchStaffNameList(@RequestParam(required = false) Integer organisationId)
	{
		List<UserDto> staffNameList=null;
		
		try{
			
			staffNameList = branchService.searchStaffNameList(organisationId);
			
		}catch(Exception e){
			
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return staffNameList;
	}
	
	
	@RequestMapping(value=COMMON_SERVICE_PATH +"/searchServiceDetailsList", method=RequestMethod.GET)		
	public 	List<ServiceMasterdto> getAllServiceDetails(@RequestParam(required = false) String userId) 
	{
		List<ServiceMasterdto> serviceDtoList = null;
		
		try{
			serviceDtoList = branchService.getAllServiceDetails(userId);
			
		} catch (Exception e){
			
            e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return serviceDtoList;
	}
	

	@RequestMapping(value=COMMON_SERVICE_PATH +"/searchStaffDetailsList", method=RequestMethod.GET)
	public List<UserDto> getAllStaffDetails(@RequestParam(required = false) String userId) {
		
		List<UserDto> userDtosList = null;
		try{
			userDtosList = branchService.getAllStaffDetails(userId);
			
		}catch(Exception e){
			
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return userDtosList; 
	}
	

	
}
