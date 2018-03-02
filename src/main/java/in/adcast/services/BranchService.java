package in.adcast.services;

import java.util.List;

import in.adcast.dto.BranchDto;
import in.adcast.dto.BranchScheduleDto;
import in.adcast.dto.ScheduleDto;
import in.adcast.dto.ServiceCategoryDto;
import in.adcast.dto.ServiceDto;
import in.adcast.dto.ServiceMasterdto;
import in.adcast.dto.ServiceNamesDto;
import in.adcast.dto.StaffDto;
import in.adcast.dto.UserDto;

public interface BranchService {
	
	/**
	 * This will create a user account. 
	 * @param LoginDto loginDto.
	 * @return 	UserDto.
	 */
	
	public boolean addNewBranch(BranchDto branchDto);
	
	public BranchDto updateBranchInformation(BranchDto branchDto);
	
	public boolean setBranchSchedule(BranchScheduleDto branchScheduleDto);		
	
	public boolean addNewService(ServiceDto serviceDto);
	
	public List<ServiceDto> changeServiceDetails(ServiceDto serviceDto);
	
	public List<ServiceDto> showAllServicesAtBranch(ServiceDto serviceDto);
	
	public List<StaffDto> addNewStaff(StaffDto staffDto);
	
	public List<StaffDto> changeStaffDetails(StaffDto staffDto);
	
	public Boolean addNewServiceGroup(ServiceMasterdto serviceMasterdto);

	public List<BranchDto> findAll(String userId,Integer orgId);
	
	public BranchDto getCompanyLocation(Integer branchId);
	
	public List<ServiceCategoryDto> listServices(String branchId);
	
	public List<ServiceCategoryDto> listServicesByOrganisation(Integer organisationId);

	public List<ServiceNamesDto> searchServiceNameList(String userId);

	public List<UserDto> searchStaffNameList(Integer organisationId);

	public List<ServiceMasterdto> getAllServiceDetails(String userId);

	public List<UserDto> getAllStaffDetails(String userId);

	public boolean deleteServiceDetails(Integer serviceId);

	public boolean deleteLocationDetails(Integer branchId);

}
