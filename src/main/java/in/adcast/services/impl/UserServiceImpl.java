package in.adcast.services.impl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.adcast.dao.BusinessCategoryDao;
import in.adcast.dao.OrganisationDao;
import in.adcast.dao.UserDao;
import in.adcast.dto.BusinessCategoryDto;
import in.adcast.dto.BusinessTypeDto;
import in.adcast.dto.LoginDto;
import in.adcast.dto.OrganisationDto;
import in.adcast.dto.OwnerDto;
import in.adcast.dto.UserDto;
import in.adcast.exception.CustomRuntimeException;
import in.adcast.mapper.ApplicationUserMapper;
import in.adcast.mapper.BusinessTypeMapper;
import in.adcast.mapper.OrganisationMapper;
import in.adcast.model.ApplicationUser;
import in.adcast.model.BusinessCategoryMasterdata;
import in.adcast.model.BusinessTypeMasterdata;
import in.adcast.model.Organisation;

import in.adcast.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OrganisationDao organisationDao;
	
	@Autowired
	private BusinessCategoryDao businessCategoryDao;
	
	@Autowired
	private BusinessTypeMapper businessTypeMapper;
	
	@Autowired
	private ApplicationUserMapper applicationUserMapper;
	
	@Autowired
	private OrganisationMapper organisationMapper;
	
	@Override
	public UserDto createUserAccount(LoginDto loginDto) {
		LOGGER.info("createUserAccount(LoginDto loginDto) ---Start ");
		
		LOGGER.info("createUserAccount(LoginDto loginDto) ---End");
		return null;
	}

	@Override
	public void update(String userId, Integer organisation_id) {
		LOGGER.info("update(String userId, Integer organisation_id) ---Start ");
		try{
		ApplicationUser currentUser = userDao.findByUUID(userId);
		Organisation organisation = organisationDao.findById(organisation_id);
		currentUser.setOrganisation(organisation);
		Set<ApplicationUser> applicationUsers = new HashSet<>();
		applicationUsers.add(currentUser);
		organisation.setApplicationUsers(applicationUsers);
		if(currentUser.getProfileCompletion() <75)
			currentUser.setProfileCompletion(75);
		userDao.update(currentUser);
		}catch(HibernateException e){
			e.printStackTrace();
			LOGGER.error("update(String userId, Integer organisation_id)"+e);			
		}catch(Exception ee){
			ee.printStackTrace();
			LOGGER.error("update(String userId, Integer organisation_id)"+ee);
		}
		LOGGER.info("update(String userId, Integer organisation_id) ---End");
	}
	
	
	@Override
	public OrganisationDto getOrgDetailsByUserID(String userId) {
		LOGGER.info("getOrgDetailsByUserID(String userId) ---Start");
		
		Organisation organisation = null;
		OrganisationDto organisationDtoDb =null;
		
		try{
			ApplicationUser applicationUser=userDao.findByUUID(userId);
			organisation = applicationUser.getOrganisation();
			organisationDtoDb = organisationMapper.prepareDto(organisation);
				
		}catch(Exception e){
			LOGGER.error("getOrgDetailsByUserID(String userId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		LOGGER.info("getOrgDetailsByUserID(String userId) ---End");
		return organisationDtoDb;
	}
	
	
	@Override
	public List<BusinessCategoryDto> getAllBusinessType() {
		
		LOGGER.info("getAllBusinessType()  -----------------Start");
		
		List<BusinessCategoryDto> businessCategoryDtoList = new LinkedList<BusinessCategoryDto>();
		
		try{
			List<BusinessCategoryMasterdata> businessCategoryMasterDataList = businessCategoryDao.getAllBusinessType();
			
			for(BusinessCategoryMasterdata businessCategoryMasterDatas : businessCategoryMasterDataList)
			{
				BusinessCategoryDto businessCategoryDto = new BusinessCategoryDto();
				
					businessCategoryDto.setBusinessCategoryid(businessCategoryMasterDatas.getId());
					businessCategoryDto.setBusinessCategoryName(businessCategoryMasterDatas.getBusinessCategoryType());
					
							List<BusinessTypeMasterdata> businessTypeMasterdataList = new LinkedList<>();
							
							List<BusinessTypeDto> businessTypeDtoList =new LinkedList<>();
							
							businessTypeMasterdataList.addAll(businessCategoryMasterDatas.getBusinessTypeMasterdatas());
							
							for(BusinessTypeMasterdata businessTypeMasterdata : businessTypeMasterdataList){
								
								BusinessTypeDto businessTypeDto = businessTypeMapper.prepareDto(businessTypeMasterdata);
								
								businessTypeDtoList.add(businessTypeDto);
								
							}
					
					businessCategoryDto.setBusinessTypeList(businessTypeDtoList);
				
				businessCategoryDtoList.add(businessCategoryDto);
				
			}
			
		}catch(HibernateException e){
			
			LOGGER.error("getAllBusinessType()"+e);
			
			e.printStackTrace();
			
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		LOGGER.info("getAllBusinessType()  -----------------End");
	
		return businessCategoryDtoList;
	}
	

	@Override
	public OwnerDto getOwnerDetailsByUserID(String userId) {
		LOGGER.info(" getOwnerDetailsByUserID(String userId) -----Start");
		
		OwnerDto ownerDto = null;
		
		try{
			
			ApplicationUser applicationUser=userDao.findByUUID(userId);
			ownerDto = applicationUserMapper.prepareDto(applicationUser);
			
		}catch(Exception e){
			
			LOGGER.error("getOwnerDetailsByUserID(String userId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		LOGGER.info(" getOwnerDetailsByUserID(String userId) -----End");
		return ownerDto;
	}
	
	
	
	
	

	@Override
	public OwnerDto updateOwnerDetails(OwnerDto ownerDtoReq) {
		LOGGER.info("updateOwnerDetails(OwnerDto ownerDtoReq)------Start");
		
		ApplicationUser applicationUserUpdates = applicationUserMapper.prepareEntity(ownerDtoReq);
		
		OwnerDto ownerDtoUpdated = null;
		
		try{
			
			ApplicationUser applicationUser = userDao.findByUUID(ownerDtoReq.getUserId());
			
			
			if(null!= applicationUserUpdates.getFirstName())
				applicationUser.setFirstName(applicationUserUpdates.getFirstName());
			if(null!=applicationUserUpdates.getLastName())
				applicationUser.setLastName(applicationUserUpdates.getLastName());
			if(null!=applicationUserUpdates.getMobile())
				applicationUser.setMobile(applicationUserUpdates.getMobile());
			if(null!=applicationUserUpdates.getAddress1())
				applicationUser.setAddress1(applicationUserUpdates.getAddress1());
			if(null!=applicationUserUpdates.getAddress2())
				applicationUser.setAddress2(applicationUserUpdates.getAddress2());
			if(null!=applicationUserUpdates.getCity())
				applicationUser.setCity(applicationUserUpdates.getCity());
			if(null!=applicationUserUpdates.getState())
				applicationUser.setState(applicationUserUpdates.getState());
			if(null!=applicationUserUpdates.getPincode())
				applicationUser.setPincode(applicationUserUpdates.getPincode());
			
			applicationUser.setProfileCompletion(50);;
			applicationUser = userDao.update(applicationUser);
			
			ownerDtoUpdated = applicationUserMapper.prepareDto(applicationUser);
			
		}catch(HibernateException e){
			
			e.printStackTrace();
			LOGGER.error("updateOwnerDetails(OwnerDto ownerDtoReq)  --Error"+e);
			
		}
		
		LOGGER.info("updateOwnerDetails(OwnerDto ownerDtoReq)------End");
		return ownerDtoUpdated;
		
	}

	



	
}
