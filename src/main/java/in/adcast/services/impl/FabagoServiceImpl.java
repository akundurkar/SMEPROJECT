package in.adcast.services.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.adcast.common.GenericDAOImpl;
import in.adcast.common.utils.AppConstant;
import in.adcast.common.utils.SMEUtils;

import in.adcast.dao.ClientDao;
import in.adcast.dao.ClientReviewDao;
import in.adcast.dao.ContactDao;
import in.adcast.dao.FabagoDao;
import in.adcast.dao.ClientOrderDao;
import in.adcast.dao.UserDao;
import in.adcast.dto.AppointmentHistoryDto;
import in.adcast.dto.BusinessTypeNameDto;
import in.adcast.dto.ClientDto;
import in.adcast.dto.ClientNameDto;
import in.adcast.dto.ClientProfileDto;
import in.adcast.dto.ClientReviewDto;
import in.adcast.dto.ContactDto;
import in.adcast.dto.OrderCountDto;
import in.adcast.dto.UpcomingAppointmentDto;
import in.adcast.exception.CustomRuntimeException;
import in.adcast.mapper.BusinessTypeNameMapper;
import in.adcast.mapper.ClientMapper;
import in.adcast.mapper.ClientNameMapper;
import in.adcast.mapper.ClientProfileMapper;
import in.adcast.mapper.ClientReviewMapper;
import in.adcast.mapper.ContactMapper;
import in.adcast.mapper.OrderMapper;
import in.adcast.model.ApplicationUser;
import in.adcast.model.BusinessTypeMasterdata;
import in.adcast.model.Client;
import in.adcast.model.ClientReview;
import in.adcast.model.Contact;
import in.adcast.model.ClientOrder;

import in.adcast.services.ClientService;
import in.adcast.services.FabagoService;


@Service
@Transactional
public class FabagoServiceImpl extends GenericDAOImpl<Client,Integer> implements FabagoService 
{
	private static final Logger LOGGER = Logger.getLogger(FabagoServiceImpl.class);
	
	@Autowired 
	private FabagoDao fabagoDao;
	
	@Autowired 
	private BusinessTypeNameMapper businessTypeNameMapper;

	@Override
	public List<BusinessTypeNameDto> searchBusinessTypeName(Integer organizationId, String queri) {
		LOGGER.info("searchBusinessTypeName(Integer organizationId, String queri) ---- Start");
		
		List<BusinessTypeMasterdata> businessTypeList = null;
		List<BusinessTypeNameDto> businessTypeNameList = null;
		
		try{
			if (null!=queri){
				 
				businessTypeList = fabagoDao.getBusinessTypeName(organizationId,queri);
				
				businessTypeNameList = businessTypeNameMapper.prepareDto(businessTypeList);
				
			}	
		}
		catch(HibernateException e){
			LOGGER.error("searchBusinessTypeName(Integer organizationId, String queri)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		LOGGER.info("searchBusinessTypeName(Integer organizationId, String queri) ---- End");
		return businessTypeNameList;
	}


	

	

	


	

}
