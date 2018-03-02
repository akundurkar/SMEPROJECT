package in.adcast.services.impl;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.HashSet;

import java.util.LinkedList;

import java.util.List;

import java.util.Map;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import in.adcast.common.utils.AppConstant;
import in.adcast.common.utils.SMEUtils;
import in.adcast.dao.BranchDao;
import in.adcast.dao.BranchScheduleDao;
import in.adcast.dao.OrganisationDao;
import in.adcast.dao.RoleDao;
import in.adcast.dao.ServiceMasterDataDao;
import in.adcast.dao.ServiceOfferedDao;
import in.adcast.dao.UserDao;

import in.adcast.dto.BranchDto;
import in.adcast.dto.BranchScheduleDto;
import in.adcast.dto.ServiceCategoryDto;
import in.adcast.dto.ServiceDto;
import in.adcast.dto.ServiceMasterdto;
import in.adcast.dto.ServiceNamesDto;
import in.adcast.dto.StaffDto;
import in.adcast.dto.StaffScheduleDto;
import in.adcast.dto.UserDto;

import in.adcast.exception.CustomRuntimeException;

import in.adcast.mapper.BranchMapper;
import in.adcast.mapper.BranchScheduleMapper;
import in.adcast.mapper.ServiceMasterMapper;
import in.adcast.mapper.ServiceOfferedMapper;
import in.adcast.mapper.StaffScheduleMapper;
import in.adcast.mapper.UserResourceMapper;

import in.adcast.model.ApplicationUser;
import in.adcast.model.Branch;
import in.adcast.model.BranchSchedule;
import in.adcast.model.Organisation;
import in.adcast.model.Role;
import in.adcast.model.ServiceMasterdata;
import in.adcast.model.ServiceOffered;
import in.adcast.model.StaffSchedule;
import in.adcast.services.BranchService;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

	private static final Logger LOGGER = Logger.getLogger(BranchServiceImpl.class);
	
	@Autowired
	private BranchMapper branchMapper;
	
	@Autowired
	private ServiceMasterMapper serviceMasterMapper;
	
	@Autowired
	private ServiceOfferedMapper serviceOfferedMapper;

	@Autowired
	private UserResourceMapper userResourceMapper;
	
	@Autowired
	private BranchDao branchDao;
	
	@Autowired
	private ServiceMasterDataDao masterDatadao;

	@Autowired
	private ServiceOfferedDao serviceOfferedDao;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private OrganisationDao organisationDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BranchScheduleMapper branchScheduleMapper;
	
	@Autowired
	private BranchScheduleDao branchScheduleDao;
	
	@Autowired
	StaffScheduleMapper staffScheduleMapper;
	
	@Override
	public boolean addNewBranch(BranchDto branchDto) 
	{
		LOGGER.info("addNewBranch(BranchDto branchDto) ---Start");
		boolean success = false;
		try{
			
			ApplicationUser applicationUser=userDao.findByUUID(branchDto.getUserId());
			Branch branch = branchMapper.prepareEntity(branchDto);
			branch.setOrganisation(applicationUser.getOrganisation());
			branchDao.create(branch);
			if(applicationUser.getProfileCompletion() < 100)
			{
				applicationUser.setProfileCompletion(100);
				userDao.update(applicationUser);
			}			
			success = true;
		}catch(HibernateException e)
		{
			
			LOGGER.error("addNewBranch(BranchDto branchDto)"+e);	
			e.printStackTrace();
		}
		LOGGER.info("addNewBranch(BranchDto branchDto) ---End");		
		return success;
	}

	@Override
	public BranchDto updateBranchInformation(BranchDto branchDto) {
		
		LOGGER.info("updateBranchInformation(BranchDto branchDto) ---Start");
		
		BranchDto branchDtos = null;
		Branch branch = null;
		try{
			branch = branchDao.findById(branchDto.getBranchId());
			
			Branch branchUpdate = branchMapper.prepareEntity(branchDto);
			
			if(null!=branchUpdate.getLocationName())
				branch.setLocationName(branchUpdate.getLocationName());
			if(null!=branchUpdate.getContactNo())
				branch.setContactNo(branchUpdate.getContactNo());
			if(null!=branchUpdate.getPhone())
				branch.setPhone(branchUpdate.getPhone());
			if(null!=branchUpdate.getState())
				branch.setState(branchUpdate.getState());
			if(null!=branchUpdate.getArea())
				branch.setArea(branchUpdate.getArea());
			if(null!=branchUpdate.getPincode())
				branch.setPincode(branchUpdate.getPincode());
			if(null!=branchUpdate.getLangitude())
				branch.setLangitude(branchUpdate.getLangitude());
			if(null!=branchUpdate.getLatitude())
				branch.setLatitude(branchUpdate.getLatitude());
			if(null!=branchUpdate.getAcAvailable())
				branch.setAcAvailable(branchUpdate.getAcAvailable());
			if(null!=branchUpdate.getOnlineBookingStatus())
				branch.setOnlineBookingStatus(branchUpdate.getOnlineBookingStatus());
			if(null!=branchUpdate.getWifiAvailable())
				branch.setWifiAvailable(branchUpdate.getWifiAvailable());
			
			if(null != new Integer(branchUpdate.getGender()))
			{
				
				switch(AppConstant.GenderType.values()[branchUpdate.getGender()].toString())
				{
				
				case "UNISEX":
					
					branch.setGender(AppConstant.GenderType.UNISEX.getValue());
					
					break;
					
				case "FEMALE":
					
					branch.setGender(AppConstant.GenderType.FEMALE.getValue());
					
					break;
				
				case "MALE":
					
					branch.setGender(AppConstant.GenderType.MALE.getValue());
					
					break;
				}
				
			}
				
				branchUpdate=branchDao.update(branch);
				
				branchDtos =branchMapper.prepareDto(branchUpdate);
			
		}catch(HibernateException e){
			LOGGER.error("updateBranchInformation(BranchDto branchDto)"+e);
			e.printStackTrace();
		}
		LOGGER.info("updateBranchInformation(BranchDto branchDto) ---End");
		
		return branchDtos;
	}
	
	@Override
	public boolean setBranchSchedule(BranchScheduleDto branchScheduleDto) {
		LOGGER.info("setBranchSchedule(BranchScheduleDto branchScheduleDto) ---Start");
		
		boolean branchScheduleDetailsStatus=false;
		
		try
		{
			
			List<BranchSchedule> branchScheduleList= branchScheduleMapper.prpareEntity(branchScheduleDto);
			
			Branch branch= branchDao.findById(branchScheduleDto.getBranchId());
		
			List<BranchSchedule> getbranchSchedulesList =masterDatadao.getBranchScheduleDetails(branchScheduleDto.getBranchId());
			
			if(getbranchSchedulesList.size() > 0){
			 
					Map<Integer, BranchSchedule> checkWeekDay = new HashMap<Integer,BranchSchedule>();
					
					for(BranchSchedule branchScheduleDB : getbranchSchedulesList){
						
						checkWeekDay.put(branchScheduleDB.getWeekDay(), branchScheduleDB);
					}
	
					for (BranchSchedule branchSchedule : branchScheduleList){
				   
						    branchSchedule.setBranch(branch);
							
							BranchSchedule obj = checkWeekDay.get(branchSchedule.getWeekDay());
							
							if(obj != null){
								
								//if update days time of week for existing branch in branch_schedule table 
								
								obj.setOpenTime(branchSchedule.getOpenTime());
								obj.setCloseTime(branchSchedule.getCloseTime());
								
								branchScheduleDao.update(obj);
							
							}else{
								
								//if create remaining days time of week for existing branch in  branch_schedule table 
								
								branchScheduleDao.create(branchSchedule);
							}
					}
				
			}else{
				
					//if new branch week schedule save to branch_schedule table
					
					for (BranchSchedule branchSchedule : branchScheduleList){
						
						branchSchedule.setBranch(branch);
						
						branchScheduleDao.create(branchSchedule);
					}
			}
			
			
			branchScheduleDetailsStatus = true;

		}catch(HibernateException e)
		{
			LOGGER.error("setBranchSchedule(BranchScheduleDto branchScheduleDto)"+e);
			e.printStackTrace();
		}
		
		LOGGER.info("setBranchSchedule(BranchScheduleDto branchScheduleDto) ---End");
		
		return branchScheduleDetailsStatus;
	}

		
	@Override
	public boolean addNewService(ServiceDto serviceDto) {
		LOGGER.info("addNewService(ServiceDto serviceDto) ---Start");
		boolean success = false ;
		List<ServiceDto> serviceOfferedList = null;
		List<ServiceOffered> serviceOfferedLists = new ArrayList<>();
	
		try{
			
			ApplicationUser applicationUser=userDao.findByUUID(serviceDto.getUserId());
			ServiceOffered service=serviceOfferedMapper.prepareEntity(serviceDto);
			
			//this field must be added in user interface
			//Branch branch=branchDao.findById(serviceDto.getBranchId());
			
			//ToDo: Set Branch Id
			Branch branch=branchDao.findById(1);
			service.setBranch(branch);
			ServiceMasterdata serviceMasterData=masterDatadao.findById(serviceDto.getServiceGroupId());
					service.setServiceMasterdata(serviceMasterData);
			serviceOfferedDao.create(service);
			success = true;
			
		}catch(HibernateException e){
			LOGGER.error("addNewService(ServiceDto serviceDto)"+e);	
			e.printStackTrace();
		}
		LOGGER.info("addNewService(ServiceDto serviceDto) ---End");		
		return success;
		
	}

	
	@Override
	public List<ServiceDto> changeServiceDetails(ServiceDto serviceDto) {
		LOGGER.info("changeServiceDetails(ServiceDto serviceDto) ---Start");
		List<ServiceOffered> serviceOfferedList = null;
		List<ServiceDto> serviceDtoList = new LinkedList<>();
		try{			
			ServiceOffered serviceOffered = serviceOfferedMapper.prepareEntity(serviceDto);
			//TODO Update branch information from branchDto
						
			serviceOfferedDao.update(serviceOffered);
			
			serviceOfferedList = serviceOfferedDao.findbyName("branchname");
			
			for(ServiceOffered serviceOffered2: serviceOfferedList){
				ServiceDto serviceDto1 = new ServiceDto();
				serviceDtoList.add(serviceDto1);
			}
		}catch(HibernateException e){
			LOGGER.error("changeServiceDetails(ServiceDto serviceDto)"+e);
			e.printStackTrace();
		}
		LOGGER.info("changeServiceDetails(ServiceDto serviceDto) ---End");
		
		return serviceDtoList;
	}

	@Override
	public List<ServiceDto> showAllServicesAtBranch(ServiceDto serviceDto) {
		LOGGER.info("showAllServicesAtBranch(ServiceDto serviceDto) ---Start");
		List<ServiceOffered> serviceOfferedList = null;
		List<ServiceDto> serviceDtoList = new LinkedList<>();
		try{			
			ServiceOffered serviceOffered = serviceOfferedMapper.prepareEntity(serviceDto);
			//TODO Update branch information from branchDto
						
			serviceOffered = serviceOfferedDao.update(serviceOffered);
			
			serviceOfferedList = serviceOfferedDao.findbyName("branchname");
			
			for(ServiceOffered serviceOffered2: serviceOfferedList){
				ServiceDto serviceDto1 = new ServiceDto();
				serviceDtoList.add(serviceDto1);
			}
		}catch(HibernateException e){
			LOGGER.error("showAllServicesAtBranch(ServiceDto serviceDto)"+e);
			e.printStackTrace();
		}
		LOGGER.info("showAllServicesAtBranch(ServiceDto serviceDto) ---End");
		
		return serviceDtoList;
	}

	@Override
	public List<StaffDto> addNewStaff(StaffDto staffDto) {
		LOGGER.info("addNewStaff(StaffDto staffDto) ---Start");
		List<ServiceOffered> serviceOfferedList = null;
		List<StaffDto> staffDtoList = new LinkedList<>();
		try{			
			ApplicationUser staffUser= userResourceMapper.prepareEntity(staffDto);
			
			Organisation organisation = organisationDao.findById(1);
			staffUser.setOrganisation(organisation);
			
			HashSet<Role> roles = new HashSet<>();
			Role role = new Role();
			roleDao.findById(AppConstant.AccountRole.EMPLOYEE_SHOP.getValue());
			roles.add(role);
			staffUser.setRoles(roles);
			
			staffUser = userDao.create(staffUser);
			
			List<ApplicationUser> staffList = userDao.findAllStaffDetails(staffUser.getOrganisation().getId());
			
			for(ApplicationUser applicationUser: staffList){
				 StaffDto staffDto1 = userResourceMapper.prepareDto(applicationUser,"staff");
				 staffDtoList.add(staffDto1);
			}
		}catch(HibernateException e){
			LOGGER.error("addNewStaff(StaffDto staffDto)"+e);	
			e.printStackTrace();
		}
		LOGGER.info("addNewStaff(StaffDto staffDto) ---End");
		
		return staffDtoList ;
	}

	@Override
	public List<StaffDto> changeStaffDetails(StaffDto staffDto) {
		LOGGER.info("changeStaffDetails(StaffDto staffDto) ---Start");
		List<ServiceOffered> serviceOfferedList = null;
		List<StaffDto> staffDtoList = new LinkedList<>();
		try{			
			ApplicationUser staffFromDB = userDao.findById(1);
			
			userDao.update(staffFromDB);
			List<ApplicationUser> staffList = userDao.findAllStaffDetails(staffFromDB.getOrganisation().getId());
			
			for(ApplicationUser applicationUser: staffList){
				 StaffDto staffDto1 = userResourceMapper.prepareDto(applicationUser,"staff");
				 staffDtoList.add(staffDto1);
			}
		}catch(HibernateException e){
			LOGGER.error("changeStaffDetails(StaffDto staffDto)"+e);
			e.printStackTrace();
		}
		LOGGER.info("changeStaffDetails(StaffDto staffDto) ---End");
		
		return staffDtoList ;
	}

	@Override
	public Boolean addNewServiceGroup(ServiceMasterdto serviceMasterdto) {
		LOGGER.info("addNewServiceGroup(ServiceMasterdto serviceMasterdto) ---Start");
		
		boolean success = false ;
		try{
			
			ApplicationUser applicationUser=userDao.findByUUID(serviceMasterdto.getUserId());
			ServiceMasterdata  serviceMasterdata= serviceMasterMapper.prepareEntity(serviceMasterdto);
			serviceMasterdata.setOrganisation(applicationUser.getOrganisation());
			masterDatadao.create(serviceMasterdata);
			success =true;
			
		}catch(HibernateException e){
			LOGGER.error("addNewServiceGroup(ServiceMasterdto serviceMasterdto)"+e);
			e.printStackTrace();
		}
		LOGGER.info("addNewServiceGroup(ServiceMasterdto serviceMasterdto) ---End");		
		return success;
	}

	@Override
	public List<BranchDto> findAll(String userId,Integer orgId) {
		LOGGER.info("findAll(String userId)---- Start");
		List<BranchDto> branchDtos = null;
		List<Branch> branchList = null;
		try{
			if(null != userId)
			{
				ApplicationUser applicationUser=userDao.findByUUID(userId);
				branchList = branchDao.findAll(applicationUser.getOrganisation().getId());
			}
			else{
				branchList = branchDao.findAll(orgId);
			}
			branchDtos = new ArrayList<BranchDto>();

			for(Branch branch : branchList){
				branchDtos.add(branchMapper.prepareDto(branch));
			}
		}catch(HibernateException e){
			LOGGER.error("findAll(String userId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("findAll(String userId)---- End");
		return branchDtos;
	}
	
	
	@Override
	public BranchDto getCompanyLocation(Integer branchId) {
		LOGGER.info("getCompanyLocations(Integer branchId)--------------Start");
		
		BranchDto branchDto = null;
		
		try{
			Branch branch = branchDao.findById(branchId);
			
			branchDto = branchMapper.prepareDto(branch);
			
		}catch(HibernateException e){
			
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		LOGGER.info("getCompanyLocations(Integer branchId)--------------End");
		return branchDto;
	}
	
	
	@Override
	public List<ServiceCategoryDto> listServices(String branchId) {
		LOGGER.info("listServices(String branchId) ---- Start");
		List<ServiceDto> serviceDtos = null;
		List<ServiceCategoryDto> categoryDtoList=new ArrayList<>();
		Map<Integer,String> serviceIdObjectList=new HashMap<>();
		try{
			
			/*ApplicationUser applicationUser=userDao.findByUUID(userId);*/
			List<ServiceOffered> serviceOffereds = serviceOfferedDao.listServicesByBranchId(Integer.parseInt(branchId));
			serviceDtos = new ArrayList<ServiceDto>();
			Multimap<Integer, ServiceDto> multimap = ArrayListMultimap.create();

			for(ServiceOffered serviceOffered : serviceOffereds)
			{
				ServiceDto serviceDto=serviceOfferedMapper.prepareDto(serviceOffered);
				serviceDtos.add(serviceDto);
				multimap.put(serviceOffered.getServiceMasterdata().getId(),serviceDto);	
				
				if(!serviceIdObjectList.keySet().contains(serviceOffered.getServiceMasterdata().getId()))
				{
					serviceIdObjectList.put(serviceOffered.getServiceMasterdata().getId(),serviceOffered.getServiceMasterdata().getServiceCategoty());
				}
				
			}
			System.out.println(multimap.keySet());
			for(Integer key :multimap.keySet())
			{
				ServiceCategoryDto categoryDto=new ServiceCategoryDto();
				categoryDto.setId(key);
				categoryDto.setServiceGroupName(serviceIdObjectList.get(key));
				List<ServiceDto> serviceDtoList=(List<ServiceDto>) multimap.get(key);
				categoryDto.setServiceList(serviceDtoList);
				categoryDtoList.add(categoryDto);
				
			}
			
		}catch(HibernateException e)
		{
			LOGGER.error("listServices(String branchId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("listServices(String branchId) ---- End");
		return categoryDtoList;
	}

	@Override
	public List<ServiceNamesDto> searchServiceNameList(String userId) {
		LOGGER.info("searchServiceNameList(String userId) ---- Start");
		
		List<ServiceOffered> serviceOfferedDataList = null;
		List<ServiceNamesDto> serviceNameList = null;
		try{
			if (null!=userId){
				ApplicationUser applicationUser=userDao.findByUUID(userId);
			
				serviceOfferedDataList = masterDatadao.searchServiceNameList(applicationUser.getOrganisation().getId());
				
				serviceNameList = serviceMasterMapper.prepareDto(serviceOfferedDataList);
			}
		}
		catch(HibernateException e){
			LOGGER.error("searchServiceNameList(String userId)"+e);
			e.printStackTrace();
		}
		LOGGER.info("searchServiceNameList(String userId) ---- End");
		return serviceNameList;
	}
	
	@Override
	public List<UserDto> searchStaffNameList(Integer organisationId) {
		LOGGER.info("searchStaffNameList(Integer organisationId) ---- Start");
		
		List<ApplicationUser> applicationUserList = null;
		List<UserDto> staffNameList=null;
		try{
			if(null!=organisationId){
				staffNameList=new ArrayList<>();
				//ApplicationUser applicationUser=userDao.findByUUID(userId);	
				applicationUserList=masterDatadao.searchStaffNameList(organisationId);
				for(ApplicationUser applicationUsers:applicationUserList)
				{
					UserDto userDto = userResourceMapper.prepareDto(applicationUsers);					
					staffNameList.add(userDto);
				}			
			}	
		}catch(HibernateException e){
			LOGGER.error("searchStaffNameList(Integer organisationId)"+e);
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		LOGGER.info("searchStaffNameList(Integer organisationId)---- End");
		return staffNameList;
	}

	@Override
	public List<ServiceCategoryDto> listServicesByOrganisation(Integer organisationId) {
		LOGGER.info("listServicesByOrganisation(Integer organisationId)---- Start");
		List<ServiceCategoryDto> serviceCategoryDtos = new LinkedList<ServiceCategoryDto>();
		
		try{
			
			//ApplicationUser applicationUser=userDao.findByUUID(userId);
			List<ServiceMasterdata> serviceMasterDatalist=masterDatadao.listAllServicesByOrganisationId(organisationId);
			
			for(ServiceMasterdata serviceMasterdata:serviceMasterDatalist)
			{
				ServiceCategoryDto serviceCategoryDto=new ServiceCategoryDto();
				serviceCategoryDto.setId(serviceMasterdata.getId());
				serviceCategoryDto.setServiceGroupName(serviceMasterdata.getServiceCategoty());
				List<ServiceOffered> serviceList=new LinkedList<>();
				List<ServiceDto> serviceDtoList=new LinkedList<>();
				serviceList.addAll(serviceMasterdata.getServiceOffereds());
				for(ServiceOffered serviceOffered:serviceList)
				{
				
				ServiceDto serviceDto=serviceOfferedMapper.prepareDto(serviceOffered);
				serviceDtoList.add(serviceDto);
					
				}
				
				serviceCategoryDto.setServiceList(serviceDtoList);
				serviceCategoryDtos.add(serviceCategoryDto);
			}
			
			
		}catch(HibernateException e){
			LOGGER.error("listServicesByOrganisation(Integer organisationId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("listServicesByOrganisation(Integer organisationId) ---- End");
		return serviceCategoryDtos;
	}

	@Override
	public List<ServiceMasterdto> getAllServiceDetails(String userId) {
		LOGGER.info("getAllServiceDetails(String userId) ---- Start");
		
		List<ServiceMasterdto> serviceDtos = new ArrayList<>();
		try{
			ApplicationUser applicationUser=userDao.findByUUID(userId);
			List<ServiceMasterdata> serviceMasterList = masterDatadao.getAllServiceDetails(applicationUser.getOrganisation().getId());
			
			for(ServiceMasterdata serviceMasterdata : serviceMasterList){
				ServiceMasterdto serviceMasterdto =serviceMasterMapper.prpareDto(serviceMasterdata);
				serviceDtos.add(serviceMasterdto);
			}
		}catch(HibernateException e){
			LOGGER.error("getAllServiceDetails(String userId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		LOGGER.info("getAllServiceDetails(String userId) ---- End");
		return serviceDtos;
	}

	@Override
	public List<UserDto> getAllStaffDetails(String userId) {
		LOGGER.info("getAllStaffDetails(String userId) ---- Start");
		
		List<UserDto> userDtosList = new ArrayList<>();
		
		try{
			ApplicationUser applicationUser = userDao.findByUUID(userId);
			List<ApplicationUser> applicationUsersList = masterDatadao.searchStaffNameList(applicationUser.getOrganisation().getId());
			
			for(ApplicationUser  applicationUser2 : applicationUsersList){
				UserDto userDto =userResourceMapper.prepareDto(applicationUser2);
				List<StaffSchedule> staffScheduleList = new LinkedList<>();
				staffScheduleList.addAll(applicationUser2.getStaffSchedules());
				StaffScheduleDto staffScheduleDto = staffScheduleMapper.prepareDto(staffScheduleList);
				SMEUtils.setWeekDates(staffScheduleDto);
				userDto.setStaffScheduleDto(staffScheduleDto);
				userDtosList.add(userDto);
			}
			
		}catch(HibernateException e){
			LOGGER.error("getAllStaffDetails(String userId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		LOGGER.info("getAllStaffDetails(String userId) ---- End");
		return userDtosList;
	}

	@Override
	public boolean deleteServiceDetails(Integer serviceId) {
		LOGGER.info("deleteServiceDetails(Integer serviceId)-------Start");
		
		boolean success = false;
		
		ServiceOffered serviceOffered = serviceOfferedDao.findById(serviceId);
		
		try{
			
			serviceOfferedDao.delete(serviceOffered);
			
			success = true ;
			
		}catch(HibernateException e){
			
			LOGGER.error("deleteServiceDetails(Integer serviceId)"+e);
			
			throw new CustomRuntimeException("HibernateException EXCEPTION",  e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		LOGGER.info("deleteServiceDetails(Integer serviceId)-------End");
		return success;
	}

	@Override
	public boolean deleteLocationDetails(Integer branchId) {
		LOGGER.info("deleteLocationDetails(Integer branchId) ---------- Start");
		
		boolean success = false;
		
		Branch branch = branchDao.findById(branchId);
		
		try{
			
			branchDao.delete(branch);
			
			success = true;

		}catch(HibernateException e){
		    
			LOGGER.error("deleteLocationDetails(Integer branchId) "+e);
			
			throw new CustomRuntimeException("HibernateException Exception", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		LOGGER.info("deleteLocationDetails(Integer branchId) ---------- End");
		return success;
	}

	

	
	
}
