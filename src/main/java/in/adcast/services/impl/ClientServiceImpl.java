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
import in.adcast.dao.ClientOrderDao;
import in.adcast.dao.UserDao;
import in.adcast.dto.AppointmentHistoryDto;
import in.adcast.dto.ClientDto;
import in.adcast.dto.ClientNameDto;
import in.adcast.dto.ClientProfileDto;
import in.adcast.dto.ClientReviewDto;
import in.adcast.dto.ContactDto;
import in.adcast.dto.OrderCountDto;
import in.adcast.dto.UpcomingAppointmentDto;
import in.adcast.exception.CustomRuntimeException;

import in.adcast.mapper.ClientMapper;
import in.adcast.mapper.ClientNameMapper;
import in.adcast.mapper.ClientProfileMapper;
import in.adcast.mapper.ClientReviewMapper;
import in.adcast.mapper.ContactMapper;
import in.adcast.mapper.OrderMapper;
import in.adcast.model.ApplicationUser;
import in.adcast.model.Client;
import in.adcast.model.ClientReview;
import in.adcast.model.Contact;
import in.adcast.model.ClientOrder;

import in.adcast.services.ClientService;


@Service
@Transactional
public class ClientServiceImpl extends GenericDAOImpl<Client,Integer> implements ClientService 
{
	private static final Logger LOGGER = Logger.getLogger(ClientServiceImpl.class);
	
	@Autowired 
	private ClientMapper clientMapper;
	
	@Autowired
	private ClientReviewMapper clientReviewMapper;
	
	@Autowired
	private ContactMapper contactMapper;
	
	@Autowired
	private ClientNameMapper clientNameMapper;
	
	@Autowired
	private ClientProfileMapper clientProfileMapper;
	
	@Autowired 
	private ClientDao clientDao;
	
	@Autowired 
	private ClientOrderDao orderDao;
	
	@Autowired
	private ClientReviewDao clientReviewDao;
	
	@Autowired
	private ContactDao contactDao;
	
	@Autowired 
	private UserDao userDao;
	
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public Integer addNewClient(ClientDto clientDto) 
	{
		LOGGER.info("addNewClient(ClientDto clientDto) ---Start");
		Integer clientId=null;
		
		try
		{
			if(null!=clientDto)
			{
				ApplicationUser applicationUser=userDao.findByUUID(clientDto.getUserId());
				Client client=clientMapper.prepareEntity(clientDto);
				client.setOrganisation(applicationUser.getOrganisation());
				clientDao.create(client);
				clientId=  client.getId();
				
			}
			
		}catch(HibernateException e)
		{
			LOGGER.error("addNewClient(ClientDto clientDto)"+e);
			e.printStackTrace();
		}
		LOGGER.info("addNewClient(ClientDto clientDto)---End");
		return  clientId;
	}

	
	@Override
	public boolean saveClientAddress(ClientDto clientDto) 
	{
				LOGGER.info("saveClientAddress(ClientDto clientDto) ---Start");
				boolean reasonStatus=false;
				
				try
				{
					if(null!=clientDto)
					{
						ApplicationUser applicationUser=userDao.findByUUID(clientDto.getUserId());
						Client client=clientMapper.prepareEntity(clientDto);
						client.setOrganisation(applicationUser.getOrganisation());
						client.setId(14);
						clientDao.update(client);
						reasonStatus= true;
					}else
					{
						reasonStatus=false;
					}
					
				}catch(HibernateException e)
				{
					e.printStackTrace();
					LOGGER.error("saveClientAddress(ClientDto clientDto)"+e);			
				}
				
				LOGGER.info("saveClientAddress(ClientDto clientDto)---End");
				
				return reasonStatus;
		}


	@Override
	public List<ClientDto> findAll(String userId) {
		LOGGER.info("findAll(String userId) ---- Start");
		List<ClientDto> clientDtos = null;
		try{
			ApplicationUser applicationUser=userDao.findByUUID(userId);
			List<Client> clientList = clientDao.findAll(applicationUser.getOrganisation().getId());
			List<ClientOrder> orderList = orderDao.findAllUpcomingOrders(applicationUser.getOrganisation().getId());
			Map<Integer,ClientOrder> orderMap = new HashMap<>();
			for(ClientOrder order:orderList){
				orderMap.put(order.getClient().getId(), order);
			}
			clientDtos = new ArrayList<ClientDto>();
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
			for(Client client : clientList){
				ClientDto clientDto = clientMapper.prepareDto(client);
				ClientOrder curOrder = orderMap.get(client.getId());
				if(curOrder != null){
					cal.setTime(curOrder.getCreatedOn());								
					System.out.println(sdf.format(cal.getTime()));
					clientDto.setAppointmentDetail(sdf.format(cal.getTime()));
					clientDto.setOrderid(curOrder.getId());
					
				}
				else{
					clientDto.setAppointmentDetail("NA");
					
				}
				clientDtos.add(clientDto);
			}
		}catch(HibernateException e){
			LOGGER.error("findAll("+userId+")"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("findAll(String userId) ---- End");
		return clientDtos;
	}


	@Override
	public List<ClientNameDto> searchClientByMobileORName(Integer organizationId,String queri) {
		LOGGER.info("searchClientByMobileORName(String queri) ---- Start");
		
		List<Client> clientlist=null;
		List<ClientNameDto> clientNameList =null;
		try{
			if (null!=queri){
				 
				clientlist = clientDao.getClientByMobileOrName(organizationId,queri);
				
				clientNameList = clientNameMapper.prepareDto(clientlist);
				
			}	
		}
		catch(HibernateException e){
			LOGGER.error("searchClientByMobileORName(String queri)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		LOGGER.info("searchClientByMobileORName(String queri) ---- End");
		return clientNameList;
	}


	@Override
	public ClientProfileDto findClientProfile(Integer clientId) {
		LOGGER.info("findClientProfile(Integer clientId) ---- Start");	
		
		ClientProfileDto clientProfileDto = null;
	
		try{
			
			Client  client = clientDao.findById(clientId);
			
			clientProfileDto = clientProfileMapper.prepareDto(client);
		}
		catch(HibernateException e){
			LOGGER.error("findClientProfile(Integer clientId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("findClientProfile(Integer clientId)---- End");	
		return clientProfileDto;
	}
	
	
	@Override
	public OrderCountDto getOrderCountforClient(Integer clientId) {
		
		LOGGER.info("getOrderCountforClient(Integer clientId)------------Start");
		
		OrderCountDto orderCountDto = new OrderCountDto();

		
		try{
			
			Long totalOrderCount =orderDao.getOrderCountforClient(clientId);
			
			if(totalOrderCount != 0){
				Long totalNoShowOrderCount =orderDao.getOrderCountforClient(clientId , AppConstant.OrderStatus.NOSHOW.getValue());
			
				BigDecimal totalSale = orderDao.totalSalePerClient(clientId);
				
				BigDecimal amountPaid = orderDao.amountPaidPerClient(clientId);
				
				BigDecimal outstanding = totalSale.subtract(amountPaid);					
				
				orderCountDto.setOrderCount(totalOrderCount);
				orderCountDto.setNoShowCount(totalNoShowOrderCount);
				orderCountDto.setTotalSales(totalSale);
				orderCountDto.setOutstanding(outstanding);
			}else{
				Long ZERO = (long) 0;
				orderCountDto.setOrderCount(ZERO);
				orderCountDto.setNoShowCount(ZERO);
				orderCountDto.setTotalSales(new BigDecimal(ZERO));
				orderCountDto.setOutstanding(new BigDecimal(ZERO));
			}
			
		}catch(HibernateException e){
			LOGGER.error("getOrderCountforClient(Integer clientId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	    
		LOGGER.info("getOrderCountforClient(Integer clientId)------------End");
		return orderCountDto;
	}
	
	@Override
	public List<UpcomingAppointmentDto> getUpcomingAppointmentForClient(Integer clientId) {
		LOGGER.info("getUpcomingAppointmentForClient(Integer clientId) -------------Start");
		
		List<UpcomingAppointmentDto> upcomingAppointmentDtosList = null;
		
		List<ClientOrder> clientOrdersList = null;
		
		try{
			clientOrdersList =orderDao.getUpcomingAppointmentForClient(clientId);
			
			upcomingAppointmentDtosList =orderMapper.prepareUpcomingAppointmentDto(clientOrdersList);
			
		}catch(HibernateException e){
			LOGGER.error("getAppointmentHistoryForClient(Integer clientId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		LOGGER.info("getUpcomingAppointmentForClient(Integer clientId) -------------End");
		return upcomingAppointmentDtosList;
	}

	
	
	@Override
	public List<AppointmentHistoryDto> getAppointmentHistoryForClient(Integer clientId) {
		LOGGER.info("getAppointmentHistoryForClient(Integer clientId) ---------Start");
		
		List<AppointmentHistoryDto> appointmentHistoryDtosList = null;
		
		List<ClientOrder> orderList = null;
		
		try{
			orderList = orderDao.getAppointmentHistoryForClient(clientId);
			
			appointmentHistoryDtosList = orderMapper.prepareAppointmentHistoryDto(orderList);
			
		}catch(HibernateException e){
			LOGGER.error("getAppointmentHistoryForClient(Integer clientId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	    
		
		LOGGER.info("getAppointmentHistoryForClient(Integer clientId)  --------End");
		return appointmentHistoryDtosList;
	}
	
	
	@Override
	public ClientDto getClientEditDetails(Integer clientId) {
		LOGGER.info("getClientEditDetails(Integer clientId) ---- Start");	
		
		ClientDto clientDto = null;
		
		try{
			
			Client  client = clientDao.findById(clientId);
			
			clientDto = clientMapper.prepareDto(client);
		}
		catch(HibernateException e){
			
			LOGGER.error("getClientEditDetails(Integer clientId)"+e);
			e.printStackTrace();
		}
		LOGGER.info("getClientEditDetails(Integer clientId)---- End");	
		return clientDto;
	}


	@Override
	public ClientDto updateClientEditDetails(ClientDto clientDtoReq) {
		LOGGER.info("updateClientEditDetails(ClientDto clientDtoReq) ---Start");

		ClientDto clientDto = null;
		
		try{
			Client client= clientDao.findById(Integer.parseInt(clientDtoReq.getClientId()));

			Client clientUpdate = clientMapper.prepareEntity(clientDtoReq);
			
			if(null!=clientUpdate.getFirstName())
				client.setFirstName(clientUpdate.getFirstName());
			if(null!=clientUpdate.getLastName())
				client.setLastName(clientUpdate.getLastName());
			if(null!=clientUpdate.getMobile())
				client.setMobile(clientUpdate.getMobile());
			if(null!=clientUpdate.getTelephone())
				client.setTelephone(clientUpdate.getTelephone());
			if(null!=clientUpdate.getEmailId())
				client.setEmailId(clientUpdate.getEmailId());
			if(null!=clientUpdate.getDob())
				client.setDob(clientUpdate.getDob());
			if(null!=clientUpdate.getNotificationSubscriptionType())
			    client.setNotificationSubscriptionType(clientUpdate.getNotificationSubscriptionType());
			if(null!=clientUpdate.getGender())
				client.setGender(clientUpdate.getGender());
			if(null!=clientUpdate.getMaritalStatus())
				client.setMaritalStatus(clientUpdate.getMaritalStatus());
			if(null !=clientUpdate.getAdress1())
				client.setAdress1(clientUpdate.getAdress1());
			if(null!=clientUpdate.getCity())
				client.setCity(clientUpdate.getCity());
			if(null!=clientUpdate.getState())
				client.setState(clientUpdate.getState());
			if(null!=clientUpdate.getPincode())
			    client.setPincode(clientUpdate.getPincode());
			
			clientUpdate = clientDao.update(client);
				
			clientDto = clientMapper.prepareDto(clientUpdate);
			
		}catch(HibernateException e){
			LOGGER.error("updateClientEditDetails(ClientDto clientDtoReq)  --Error"+e);
			e.printStackTrace();
		}
		LOGGER.info("updateClientEditDetails(ClientDto clientDtoReq) ---End");
	
		return clientDto;
	}


	@Override
	public boolean saveClientReview(ClientReviewDto clientReviewDto) {
		LOGGER.info("saveClientReview(ClientReviewDto clientReviewDto) ---Start");
		
		boolean status= false;
		
		try{
			if (null!=clientReviewDto){
				
				ApplicationUser applicationUser=userDao.findByUUID(clientReviewDto.getUserId());
				ClientReview clientReview=clientReviewMapper.prepareEntity(clientReviewDto);
				clientReview.setOrganisation(applicationUser.getOrganisation());
				clientReviewDao.create(clientReview);
				
				status= true;
			}
			else
			{
				status=false;
			}
		}catch(HibernateException e){
			e.printStackTrace();
		LOGGER.error("saveClientReview(ClientReviewDto clientReviewDto)"+e);
		
		}
		
		LOGGER.info("saveClientReview(ClientReviewDto clientReviewDto)----End");
		
		return status;
	}


	@Override
	public boolean saveContact(ContactDto contactDto) {
		LOGGER.info("saveContact(ContactDto contactDto) ------Start");
		
		boolean status= false;
		
		try{
			if (null!=contactDto){
				
				ApplicationUser applicationUser=userDao.findByUUID(contactDto.getUserId());
				Contact  contact=contactMapper.prepareEntity(contactDto);
				contact.setOrganisation(applicationUser.getOrganisation());
				contactDao.create(contact);
				
				status= true;
			}
			else
			{
	    	 status=false;
			}
		}catch(HibernateException e){
			e.printStackTrace();
			LOGGER.error("saveContact(ContactDto contactDto)"+e);
			
		}
		
		LOGGER.info("saveContact(ContactDto contactDto)-------End");
		
		return status;
	}
	
	public List<ClientNameDto> filterClientByBirthday(Integer organizationId){
		LOGGER.info("filterClientByBirthday()-------Start");
		
		String date = SMEUtils.todaysDateString("yyyy-MM-dd");
		
		List<Client> clientlist=null;
		List<ClientNameDto> clientNameList =null;
		
		try{
			 
				clientlist = clientDao.getClientHaveingDOB(organizationId,date);
				
				clientNameList = clientNameMapper.prepareDto(clientlist);
			
		}catch(HibernateException e){
			
				LOGGER.error("filterClientByBirthday()"+e);
				e.printStackTrace();
				throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
				
		}
		LOGGER.info("filterClientByBirthday()-------End");
		
		return clientNameList;
		
	}
	
     public List<ClientNameDto> filterClientByGender(Integer organizationId,char gender){
		LOGGER.info("filterClientByGender(char gender)-------Start");
		
		
		List<Client> clientlist=null;
		List<ClientNameDto> clientNameList =null;
		try{
		
				clientlist = clientDao.getClientByGender(organizationId,gender);
				
				clientNameList = clientNameMapper.prepareDto(clientlist);
			
		}catch(HibernateException e){
			
				LOGGER.error("filterClientByGender(char gender)"+e);
				e.printStackTrace();
				throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
				
		}
		
		LOGGER.info("filterClientByGender(char gender)-------End");
		
		return clientNameList;
		
	}
     
     public List<ClientNameDto> filterClientByAge(int age){
 		LOGGER.info("filterClientByAge(int age)-------Start");
 		
 		
 		List<Client> clientlist=null;
 		List<ClientNameDto> clientNameList =null;
 		try{
 		
 			    String date = SMEUtils.todaysDateString("yyyy-MM-dd");
 			    
 			    
 				/*clientlist = clientDao.getClientByAge(date);*/
 				
 				clientNameList = clientNameMapper.prepareDto(clientlist);
 			
 		}catch(HibernateException e){
 			
 				LOGGER.error("filterClientByAge(int age)"+e);
 				e.printStackTrace();
 				throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
 				
 		}
 		
 		LOGGER.info("filterClientByAge(int age)-------End");
 		
 		return clientNameList;
 		
 	}


	

	


	

}
