package in.adcast.services.impl;

import java.util.List;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import in.adcast.dao.BranchDao;
import in.adcast.dao.FilterCustomerForOfferDao;
import in.adcast.dao.OfferDao;
import in.adcast.dao.OfferTemplateDao;
import in.adcast.dao.OfferTypeDao;
import in.adcast.dao.UserDao;

import in.adcast.dto.CampaignDto;
import in.adcast.dto.ClientDto;
import in.adcast.dto.OfferDto;
import in.adcast.dto.OfferTypeDto;

import in.adcast.exception.CustomRuntimeException;

import in.adcast.mapper.CampaignMapper;
import in.adcast.mapper.FilterCustomerForOfferDaoMapper;
import in.adcast.mapper.OfferTemplateMapper;
import in.adcast.mapper.OfferTypeMapper;

import in.adcast.model.ApplicationUser;
import in.adcast.model.Branch;
import in.adcast.model.FilterCustomerForoffer;
import in.adcast.model.OfferTemplate;
import in.adcast.model.OfferType;
import in.adcast.model.Offers;

import in.adcast.services.CampaignService;



@Service
@Transactional
public class CampaignServiceImpl implements CampaignService 
{
	private static final Logger LOGGER = Logger.getLogger(CampaignServiceImpl.class);
	
	@Autowired
	private BranchDao branchDao;
	
	@Autowired 
	private UserDao userDao;
	
	@Autowired
	private CampaignMapper campaignMapper;
	
	@Autowired
	private OfferTypeMapper offerTypeMapper;
	
	@Autowired
	private OfferDao offerDao;
	
	@Autowired
	private OfferTypeDao offerTypeDao; 
	
	@Autowired
	private OfferTemplateDao offerTemplateDao;
	
	@Autowired
	private OfferTemplateMapper offerTemplateMapper;
	
	@Autowired
	private FilterCustomerForOfferDao filterCustomerForOfferDao;
	
	@Autowired
	private FilterCustomerForOfferDaoMapper filterCustomerForOfferDaoMapper;
	
	
	@Override
	public boolean saveNewOffer(CampaignDto campaignDto) {
		LOGGER.info("saveNewOffer(CampaignDto campaignDto) ---Start");
		
		boolean success=false;
		
		try{
			
			//save Offer
			Branch branch= branchDao.findById(campaignDto.getBranchId());
			Offers offers= campaignMapper.prpareEntity(campaignDto);
			offers.setBranch(branch);
			offerDao.create(offers);
			
			
			//save Offer Template
			OfferTemplate offerTemplate= offerTemplateMapper.prpareEntity(campaignDto);
			offerTemplate.setOffers(offers);
			offerTemplateDao.create(offerTemplate);
			
			//save Filtered CustomerForOfffer
			List<FilterCustomerForoffer> filterCustomerForOfferList=filterCustomerForOfferDaoMapper.prepareDto(campaignDto.getCustomerType());
			for(FilterCustomerForoffer filterCustomerForoffer: filterCustomerForOfferList)
			{
				filterCustomerForoffer.setOffers(offers);
				filterCustomerForOfferDao.create(filterCustomerForoffer);
			}
			success=true;
			
		}catch(HibernateException e)
		{
		    e.printStackTrace();	
			LOGGER.error("saveNewOffer(CampaignDto campaignDto)"+e);			
		}
		
		LOGGER.info("saveNewOffer(CampaignDto campaignDto)---End");		
		return success;
	}


	@Override
	public List<CampaignDto> getCampaignDetails(Integer branchId) {
		LOGGER.info("getCampaignDetails(Integer branchId) ---- Start");

		List<CampaignDto> campaignDtoList = null;
		
		try{
			List<Offers> offerList =offerDao.getOffersByBranch(branchId);
			
			campaignDtoList=campaignMapper.prepareDto(offerList);
			
		}catch(HibernateException e){
			LOGGER.error("getCampaignDetails"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("getCampaignDetails(Integer branchId) ---- End");
		return campaignDtoList;
	}

	
	@Override
	public CampaignDto getCampaignTemplate(Integer branchId) {
		LOGGER.info("getCampaignTemplate(Integer branchId) ---- Start");

		CampaignDto campaignDto = null;
		
		try{
	     	Offers offer=offerDao.getOffer(branchId);
			
			OfferTemplate OfferTemplateList =offerTemplateDao.findById(offer.getId());
			
			campaignDto=campaignMapper.prepareDtoOffersTemplate(OfferTemplateList);
			
		}catch(HibernateException e){
			LOGGER.error("getCampaignTemplate"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("getCampaignTemplate(Integer branchId) ---- End");
		return campaignDto;
	}

	@Override
	public List<OfferTypeDto> searcEventNameList(String userId) {
		LOGGER.info("searcEventNameList(String userId) ---- Start");
		
		List<OfferTypeDto> offerTypeDtosList =null;
		List<OfferType> offerTypesList =null;
		
		try{
			if (null!=userId){
				
				ApplicationUser applicationUser= userDao.findByUUID(userId);
				
				offerTypesList= offerTypeDao.searcEventNameList(applicationUser.getOrganisation().getId());
				
				offerTypeDtosList=offerTypeMapper.prepareDto(offerTypesList);
				
			}
			
		}
		catch(HibernateException e){
			e.printStackTrace();
			LOGGER.error("searcEventNameList(String userId)"+e);
		}
		
		LOGGER.info("searcEventNameList(String userId) ---- End");
		return offerTypeDtosList;
	}


	@Override
	public List<OfferTypeDto> searchFestivalNameList(String userId) {
		
			LOGGER.info("searchFestivalNameList(String userId) ---- Start");
			
			List<OfferTypeDto> offerTypeDtosList =null;
			List<OfferType> offerTypesList =null;
			
			try{
				if (null!=userId){
					
					ApplicationUser applicationUser= userDao.findByUUID(userId);
					
					offerTypesList= offerTypeDao.searcFestivalNameList(applicationUser.getOrganisation().getId());
					
					offerTypeDtosList=offerTypeMapper.prepareDto(offerTypesList);
					
				}
				
			}
			catch(HibernateException e){
				e.printStackTrace();
				LOGGER.error("searchFestivalNameList(String userId)"+e);
			}
			
			LOGGER.info("searchFestivalNameList(String userId)---- End");
			return offerTypeDtosList;
		}


	@Override
	public boolean saveOfferTypeDetails(OfferTypeDto offerTypeDto) {
		LOGGER.info("saveOfferTypeDetails(OfferTypeDto offerTypeDto) ---Start");
		boolean success=false;
		
		try{
			if(null!=offerTypeDto.getOfferType()){
				
			ApplicationUser applicationUser= userDao.findByUUID(offerTypeDto.getUserId());
			OfferType offerType = offerTypeMapper.prepareEntity(offerTypeDto);
			offerType.setOrganisation(applicationUser.getOrganisation());
			offerTypeDao.create(offerType);
			success= true;
			}
			else{
				success=false;
			}
		
		}catch(HibernateException e){
			
			e.printStackTrace();
			LOGGER.error("saveOfferTypeDetails(OfferTypeDto offerTypeDto)"+e);	
		}
		LOGGER.info("saveOfferTypeDetails(OfferTypeDto offerTypeDto) ---End");
		return success;
	}


	@Override
	public List<CampaignDto> getEditCampaignDetails(Integer branchId) {
		LOGGER.info("getEditCampaignDetails(Integer branchId) --- Start");
		
		List<CampaignDto> campaignDtoList=null;
		
		try{
			/*ApplicationUser applicationUser= userDao.findByUUID(offerTypeDto.getUserId());*/
			List<Offers> offers =offerDao.getOffersByBranch(branchId);
			
			campaignDtoList =campaignMapper.prepareDto(offers);
		}catch(HibernateException e){
			e.printStackTrace();
		}
		
		LOGGER.info("getEditCampaignDetails(Integer branchId) --- End");
		return campaignDtoList;
	}


	@Override
	public List<ClientDto> getAllCampaignTemplate() {
		LOGGER.info("getAllCampaignTemplate() --- Start");
		
		List<ClientDto> clientDtosList = null;
		
		try {
			//offerDao.getOffers();
			
			
		}catch(HibernateException e){
			LOGGER.info("getAllCampaignTemplate()"+e);
			e.printStackTrace();
			
		}
		
		LOGGER.info("getAllCampaignTemplate() --- End");
		return null;
	}


	@Override
	public boolean changeOfferStatus(OfferDto offerDto) {
		LOGGER.info("changeOfferStatus(OfferDto offerDto) --- Start");				
		Offers offers;
		boolean success = false;
		try{			
			Offers offer = offerDao.findById(offerDto.getOfferId());
			offer.setOfferStatus(offerDto.getOfferStatus());
			offers=offerDao.update(offer);
			if(offers != null){
				success = true;
			}
		}catch(HibernateException e){
			e.printStackTrace();
		}		
		LOGGER.info("changeOfferStatus(OfferDto offerDto) --- End");
		return success;
	}
	
}
