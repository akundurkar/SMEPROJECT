package in.adcast.services.impl;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.adcast.dao.BusinessTypeMasterdataDao;
import in.adcast.dao.OrganisationDao;
import in.adcast.dao.UserDao;

import in.adcast.dto.OrganisationDto;

import in.adcast.mapper.OrganisationMapper;

import in.adcast.model.ApplicationUser;
import in.adcast.model.BusinessTypeMasterdata;
import in.adcast.model.Organisation;

import in.adcast.services.OrganisationService;

@Service
@Transactional
public class OrganisationServiceImpl implements OrganisationService {

	private static final Logger LOGGER = Logger.getLogger(OrganisationServiceImpl.class);
	
	@Autowired
	private OrganisationMapper organisationMapper;
	
	@Autowired
	private OrganisationDao organisationDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BusinessTypeMasterdataDao businessTypeMasterdataDao;
	
	@Override
	public Integer createNewOrganisation(OrganisationDto organisationDto) {
		LOGGER.info("createNewOrganisation(OrganisationDto organisationDto) ---Start");
		
		Organisation organisation= organisationMapper.prepareEntity(organisationDto);
		ApplicationUser currentUser = userDao.findByUUID(organisationDto.getUserId());		
		Organisation organisation1 = null;
		try{			
			organisation.getApplicationUsers().add(currentUser);
			if(null!= organisationDto.getBusinesssCategory()){
				BusinessTypeMasterdata businessTypeMasterdata  = businessTypeMasterdataDao.findById(organisationDto.getBusinesssCategory());
				organisation.setBusinessTypeMasterdata(businessTypeMasterdata);
			}
			
			organisationDao.create(organisation);		
			/*currentUser.setOrganisation(organisation);
			userDao.update(currentUser);*/
		}catch(HibernateException e){
			e.printStackTrace();
			LOGGER.error("createNewOrganisation(OrganisationDto organisationDto)"+e);			
		}
		LOGGER.info("createNewOrganisation(OrganisationDto organisationDto) ---End");
		return organisation.getId();
	}

	@Override
	public OrganisationDto updateOrganisationDetails(OrganisationDto organisationDto) {
		LOGGER.info("updateOrganisationDetails(OrganisationDto organisationDto) ---Start");
		
		Organisation organisationUpdates= organisationMapper.prepareEntity(organisationDto);
		OrganisationDto organisationDtoUpdated = null;
		try{
			ApplicationUser applicationUser=userDao.findByUUID(organisationDto.getUserId());
			Organisation organisation = applicationUser.getOrganisation();
			
			if(null != organisationUpdates.getAdress1()) 
				organisation.setAdress1(organisationUpdates.getAdress1());
			if(null != organisationUpdates.getAdress2()) 
				organisation.setAdress2(organisationUpdates.getAdress2());
			if(null != organisationUpdates.getCity()) 
				organisation.setCity(organisationUpdates.getCity());
			if(null != organisationUpdates.getOrganisationName()) 
				organisation.setOrganisationName(organisationUpdates.getOrganisationName());
			if(null != organisationUpdates.getPhone()) 
				organisation.setPhone(organisationUpdates.getPhone());
			if(null != organisationUpdates.getPincode()) 
				organisation.setPincode(organisationUpdates.getPincode());
			if(null != organisationUpdates.getState()) 
				organisation.setState(organisationUpdates.getState());
			if(null != organisationUpdates.getWebsite()) 
				organisation.setWebsite(organisationUpdates.getWebsite());
			if(null != organisationUpdates.getDetailInfo())
				organisation.setDetailInfo(organisationUpdates.getDetailInfo());
			if(organisation.getBusinessTypeMasterdata().getId()!=organisationDto.getBusinesssCategory())
			{
				BusinessTypeMasterdata businessTypeMasterdata  = businessTypeMasterdataDao.findById(organisationDto.getBusinesssCategory());
			    organisation.setBusinessTypeMasterdata(businessTypeMasterdata);
			}
			
			organisation = organisationDao.update(organisation);
			
			organisationDtoUpdated = organisationMapper.prepareDto(organisation);
			
		}catch(HibernateException e){
			e.printStackTrace();
			LOGGER.error("updateOrganisationDetails(OrganisationDto organisationDto)  --Error"+e);			
		}
		LOGGER.info("updateOrganisationDetails(OrganisationDto organisationDto)---End");
		return organisationDtoUpdated;
	}

}
