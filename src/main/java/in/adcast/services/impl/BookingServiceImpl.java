package in.adcast.services.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import in.adcast.common.utils.AppConstant;
import in.adcast.common.utils.SMEUtils;
import in.adcast.common.utils.AppConstant.NotificationSubscriptionType;
import in.adcast.common.utils.AppConstant.OrderStatus;
import in.adcast.dao.BranchDao;
import in.adcast.dao.ClientDao;
import in.adcast.dao.InvoiceDao;
import in.adcast.dao.ClientOrderDao;
import in.adcast.dao.FeedbackSubdataDao;
import in.adcast.dao.OrganisationDao;
import in.adcast.dao.ServiceMasterDataDao;
import in.adcast.dao.ServiceOfferedDao;
import in.adcast.dao.SubOrderDao;
import in.adcast.dao.UserDao;

import in.adcast.dto.BookingDto;
import in.adcast.dto.ClientDto;
import in.adcast.dto.FeedBackDto;
import in.adcast.dto.OrderDto;
import in.adcast.dto.ServiceStaffDto;
import in.adcast.dto.TaxDto;
import in.adcast.exception.CustomRuntimeException;

import in.adcast.mapper.BookingMapper;
import in.adcast.mapper.ClientMapper;
import in.adcast.mapper.FeedbackSubdataMapper;
import in.adcast.mapper.OrderMapper;
import in.adcast.mapper.SubOrderMapper;

import in.adcast.model.ApplicationUser;
import in.adcast.model.Branch;
import in.adcast.model.Client;
import in.adcast.model.Invoice;
import in.adcast.model.ClientOrder;
import in.adcast.model.FeedbackSubdata;
import in.adcast.model.Organisation;
import in.adcast.model.ServiceOffered;
import in.adcast.model.SubOrder;
import in.adcast.model.TaxMasterdata;
import in.adcast.services.BookingService;
import in.adcast.services.NotificationService;



@Service
@Transactional
public class BookingServiceImpl implements BookingService {
	
	private static final Logger LOGGER = Logger.getLogger(BookingServiceImpl.class);
	
	@Autowired
	private BookingMapper bookingMapper;

	@Autowired
	private SubOrderMapper subOrderMapper;
	
	@Autowired
	private ClientMapper clientMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private ClientOrderDao orderDao;
	
	@Autowired
	private SubOrderDao subOrderDao;

	@Autowired
	private OrganisationDao organisationDao;
	
	@Autowired
	private InvoiceDao invoiceDao;
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private ServiceOfferedDao serviceOfferedDao;
	
	@Autowired
	private ServiceMasterDataDao masterDatadao;
	
	@Autowired 
	private UserDao userDao;	
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired 
	private BranchDao branchDao;
	
	@Autowired
	private FeedbackSubdataMapper feedbackSubdataMapper;
	
	@Autowired
	private FeedbackSubdataDao feedbackSubdataDao;
	

	@Override
	public boolean bookServiceAppoitment(BookingDto bookingDto) {
		LOGGER.info("bookServiceAppoitment(BookingDto bookingDto) ---Start");
		boolean success = false;
		try{
			ClientOrder order = bookingMapper.prepareEntity(bookingDto);
			order.setOrderStatus(AppConstant.OrderStatus.BOOKED.getValue());
			Invoice invoice = invoiceDao.create(new Invoice());	
			
			setInvoiceNumber(order, invoice);
			
			BigDecimal orderTotal = new BigDecimal(0);
			//Calculate Total and set Total
			
			for(ServiceStaffDto serviceStaffDto:bookingDto.getServiceAndStaffList()){
				ServiceOffered serviceOffered = serviceOfferedDao.findById(serviceStaffDto.getServiceId());
				BigDecimal servicePrice = serviceOffered.getPrice();
				orderTotal = servicePrice.add(orderTotal,MathContext.UNLIMITED);
			}
			
			order.setTotal(orderTotal);
			order = orderDao.create(order);		
			// create Suborder
			
			createAndUpdateSuborder(order,bookingDto,null);
			
			//---------------send email start
			System.out.println("mail created...............");
			
			//mailService.SendMail(user.getEmailId(), confirmationLinkMessage,title);
			
			Map<String, Object> freeMarkerModel = new HashMap<String,Object>();
			
		
			freeMarkerModel.put("customerName", bookingDto.getClientFirstName()+"  "+bookingDto.getClientLastName());
			freeMarkerModel.put("branchLocation", order.getBranch().getLocationName());
			freeMarkerModel.put("orderId", order.getId());
			freeMarkerModel.put("orderTime", order.getCreatedOn().toGMTString());
			freeMarkerModel.put("branchMobile", order.getBranch().getPhone());
			List<String> serviceList = new LinkedList<>();
			int serviceSize = bookingDto.getServiceAndStaffList().size();
			int serviceIndex = 1;
			
			for(ServiceStaffDto serviceStaffDto:bookingDto.getServiceAndStaffList()){
					
				SubOrder subOrder = subOrderMapper.prepareEntity(serviceStaffDto);
				
				if(serviceIndex < serviceSize){
					serviceList.add(subOrder.getServiceOffered().getServiceName()+",");
				}
				else{
					serviceList.add(subOrder.getServiceOffered().getServiceName());
				}
				serviceIndex++;
			}
				
			freeMarkerModel.put("services", serviceList);
			
			String subject = "SME Order Placed";
			
			notificationService.sendMail("orderBookedTemplate.html",order.getClient().getEmailId(),freeMarkerModel,subject);
			
			System.out.println("--------------mail send success");
			//---------------send email end
			success = true;
		}catch(HibernateException e){
			LOGGER.error("bookServiceAppoitment(BookingDto bookingDto)"+e);		
			e.printStackTrace();
		}
		LOGGER.info("bookServiceAppoitment(BookingDto bookingDto) ---End");		
		return success;
	}

	@Override
	public boolean cancelServiceAppoitment(BookingDto bookingDto) {
		LOGGER.info("cancelServiceAppoitment(BookingDto bookingDto) ---Start");
		boolean success = false;
		try{
			ClientOrder order = orderDao.findById(bookingDto.getBookingId());
			order.setOrderStatus(OrderStatus.NOSHOW.getValue());
			order.setLastUpdated(new Date());
			orderDao.update(order);
			success = true;
		}catch(HibernateException e){
			LOGGER.error("cancelServiceAppoitment(BookingDto bookingDto)"+e);
			e.printStackTrace();
		}
		LOGGER.info("cancelServiceAppoitment(BookingDto bookingDto) ---End");		
		return success;
	}

	@Override
	public boolean changeServiceStatusAppoitment(BookingDto bookingDto) {
		LOGGER.info("changeServiceStatusAppoitment(BookingDto bookingDto) ---Start");
		boolean success = false;
		try{
			ClientOrder order = orderDao.findById(bookingDto.getBookingId());
			if(null != order)
				order.setLastUpdated(new Date());
			if(null != bookingDto.getServiceAndStaffList()){
				if(null != order){
					createAndUpdateSuborder(order,bookingDto,order.getSubOrders());
					
					BigDecimal paidAmount = new BigDecimal(order.getTotal().toString());			
					if(null != bookingDto.getLastMinDiscount()){
						paidAmount = order.getTotal().subtract(bookingDto.getLastMinDiscount());
					}
					
					
					if(null != bookingDto.getLastMinDiscountPer()){
						MathContext mc = new MathContext(4); 
						BigDecimal differenceAmount = paidAmount.multiply(new BigDecimal(bookingDto.getLastMinDiscountPer()), mc);
						differenceAmount = differenceAmount.divide(new BigDecimal(100), RoundingMode.FLOOR);
						paidAmount = paidAmount.subtract(differenceAmount);
					}
					
					if(null != bookingDto.getTaxRate()){
						MathContext mc = new MathContext(4); 
						BigDecimal differenceAmount = paidAmount.multiply(new BigDecimal(bookingDto.getTaxRate()), mc);
						differenceAmount = differenceAmount.divide(new BigDecimal(100), RoundingMode.FLOOR);
						paidAmount = paidAmount.add(differenceAmount);
					}
					
					order.setAmountPaid(paidAmount);
				}else{
					order = bookServiceAppoitmentAtCheckout(bookingDto);
				}
			}
			
			Map<String, Object> freeMarkerModel;
			Client customer = order.getClient();;
			Branch branch = order.getBranch();;
			String subject;
			String customerName = customer.getFirstName() + " "+customer.getLastName();
			String feedbackLink = "www.finess.com/feedback/customerid";
			switch(bookingDto.getOrderStatus()){
				case "CONFIRMED":
					order.setOrderStatus(OrderStatus.CONFIRMED.getValue());															
					if(customer.getNotificationSubscriptionType() == NotificationSubscriptionType.BOTH.getValue()){
						if(null != customer.getEmailId()){
							freeMarkerModel = new HashMap<String,Object>();						
							freeMarkerModel.put("customerName",  customerName);
							freeMarkerModel.put("branchLocation", branch.getLocationName());
							freeMarkerModel.put("orderId", order.getId());						
							freeMarkerModel.put("branchMobile", branch.getPhone());
							freeMarkerModel.put("organizationName", branch.getOrganisation().getOrganisationName());
							freeMarkerModel.put("orderTime", order.getCreatedOn().toGMTString());
							List<String> serviceList = new LinkedList<>();
							int serviceSize = order.getSubOrders().size();
							int serviceIndex = 1;
							for(SubOrder suborder:order.getSubOrders()){
								if(serviceIndex < serviceSize){
									serviceList.add(suborder.getServiceOffered().getServiceName()+",");
								}
								else{
									serviceList.add(suborder.getServiceOffered().getServiceName());
								}
								serviceIndex++;
							}
							freeMarkerModel.put("services", serviceList);
							subject = "Your booking is confirmed";
						
							notificationService.sendMail("orderConfirmationTemplate.html",customer.getEmailId(),freeMarkerModel,subject);
						}
						if(null != customer.getMobile()){
							StringBuilder messegeBuilder = new StringBuilder();
							messegeBuilder.append("Hi ");
							messegeBuilder.append(customerName);
							messegeBuilder.append("Your new appointment with booking reference "+order.getId()+" is confirmed at");
							messegeBuilder.append(branch.getLocationName()+" "+order.getCreatedOn());
							//sendSMSService.sendSMS(customer.getMobile(), messegeBuilder.toString());
						}
					}
					else if(customer.getNotificationSubscriptionType() == AppConstant.NotificationSubscriptionType.EMAIL.getValue()){
						if(null != customer.getEmailId()){
							freeMarkerModel = new HashMap<String,Object>();
							
							freeMarkerModel.put("customerName",  customerName);
							freeMarkerModel.put("branchLocation", branch.getLocationName());
							freeMarkerModel.put("orderId", order.getId());						
							freeMarkerModel.put("branchMobile", branch.getPhone());
							freeMarkerModel.put("organizationName", branch.getOrganisation().getOrganisationName());
							freeMarkerModel.put("orderTime", order.getCreatedOn().toGMTString());
							List<String> serviceList = new LinkedList<>();
							int serviceSize = order.getSubOrders().size();
							int serviceIndex = 1;
							for(SubOrder suborder:order.getSubOrders()){
								if(serviceIndex < serviceSize){
									serviceList.add(suborder.getServiceOffered().getServiceName()+",");
								}
								else{
									serviceList.add(suborder.getServiceOffered().getServiceName());
								}
								serviceIndex++;
							}
							freeMarkerModel.put("services", serviceList);
							subject = "Your booking is confirmed";
							
							notificationService.sendMail("orderConfirmationTemplate.html",customer.getEmailId(),freeMarkerModel,subject);
						}
					}else if(customer.getNotificationSubscriptionType() == AppConstant.NotificationSubscriptionType.SMS.getValue()){
						if(null != customer.getMobile()){
							StringBuilder messegeBuilder = new StringBuilder();
							messegeBuilder.append("Hi ");
							messegeBuilder.append(customerName);
							messegeBuilder.append("Your new appointment with booking reference "+order.getId()+" is confirmed at");
							messegeBuilder.append(branch.getLocationName()+" "+order.getCreatedOn());
							//sendSMSService.sendSMS(customer.getMobile(), messegeBuilder.toString());
						}
					}
					break;
				case "COMPLETED":
					order.setOrderStatus(OrderStatus.COMPLETE.getValue());					
					break;
				case "CHECKOUT":
					
					order.setOrderStatus(OrderStatus.CHECKOUT.getValue());
					order.setOrderPaymentDone(true);
					customer = order.getClient();
					branch = order.getBranch();
					if(customer.getNotificationSubscriptionType() == NotificationSubscriptionType.BOTH.getValue()){
						if(null != customer.getEmailId()){
							freeMarkerModel = new HashMap<String,Object>();
							freeMarkerModel.put("customerName", customer.getFirstName() + " "+customer.getLastName() );
							freeMarkerModel.put("branchLocation", branch.getLocationName());
							freeMarkerModel.put("orderId", order.getId());
							/*freeMarkerModel.put("feedbackLink", "www.finess.com/feedback/customerid");*/
							freeMarkerModel.put("feedbackLink", "http://localhost:9000/#feedBack/"+customer.getId()+"_"+order.getId()+"_"+branch.getId());
							freeMarkerModel.put("branchMobile", branch.getPhone());
							freeMarkerModel.put("organizationName", branch.getOrganisation().getOrganisationName());					
							subject = "Please give us feedback ";
							
							notificationService.sendMail("thankyou.html",customer.getEmailId(),freeMarkerModel,subject);
						}
						if(null != customer.getMobile()){
							StringBuilder messegeBuilder = new StringBuilder();
							messegeBuilder.append("Hi ");
							messegeBuilder.append(customerName);
							messegeBuilder.append(", We just wanted to say thanks for visiting us at "+branch.getLocationName());
							messegeBuilder.append(". We hope you enjoyed your visit and look forward to seeing you return soon.");
	
							messegeBuilder.append(" Your feedback is valuable for continous improvement. ");
							messegeBuilder.append("Please rate us "+feedbackLink);
							messegeBuilder.append(" Thank you");
							//sendSMSService.sendSMS(customer.getMobile(), messegeBuilder.toString());
						}
					}
					else if(customer.getNotificationSubscriptionType() == AppConstant.NotificationSubscriptionType.EMAIL.getValue()){
						if(null != customer.getEmailId()){
							freeMarkerModel = new HashMap<String,Object>();
							freeMarkerModel.put("customerName", customer.getFirstName() + " "+customer.getLastName() );
							freeMarkerModel.put("branchLocation", branch.getLocationName());
							freeMarkerModel.put("orderId", order.getId());
							/*freeMarkerModel.put("feedbackLink", "www.finess.com/feedback/customerid");*/
							freeMarkerModel.put("feedbackLink", "http://localhost:9000/#feedBack/"+customer.getId()+"_"+order.getId()+"_"+branch.getId());
							freeMarkerModel.put("branchMobile", branch.getPhone());
							freeMarkerModel.put("organizationName", branch.getOrganisation().getOrganisationName());					
							subject = "Please give us feedback ";
							
							notificationService.sendMail("thankyou.html",customer.getEmailId(),freeMarkerModel,subject);
						}
					}
					else if(customer.getNotificationSubscriptionType() == AppConstant.NotificationSubscriptionType.SMS.getValue()){
						if(null != customer.getMobile()){
							StringBuilder messegeBuilder = new StringBuilder();
							messegeBuilder.append("Hi ");
							messegeBuilder.append(customerName);
							messegeBuilder.append(", We just wanted to say thanks for visiting us at "+branch.getLocationName());
							messegeBuilder.append(". We hope you enjoyed your visit and look forward to seeing you return soon.");
	
							messegeBuilder.append(" Your feedback is valuable for continous improvement. ");
							messegeBuilder.append("Please rate us "+feedbackLink);
							messegeBuilder.append(" Thank you");
							//sendSMSService.sendSMS(customer.getMobile(), messegeBuilder.toString());
						}
					}
					break;
				case "REJECTED":
					order.setOrderStatus(OrderStatus.REJECT.getValue());
					break;
				default:
			}
			orderDao.update(order);
			success = true;
		}catch(HibernateException e){
			LOGGER.error("changeServiceStatusAppoitment(BookingDto bookingDto)"+e);
			e.printStackTrace();
		}
		LOGGER.info("changeServiceStatusAppoitment(BookingDto bookingDto) ---End");		
		return success;
	}

	private ClientOrder bookServiceAppoitmentAtCheckout(BookingDto bookingDto) {
		LOGGER.info("bookServiceAppoitment(BookingDto bookingDto) ---Start");		
		ClientOrder order = null;
		try{
			order = bookingMapper.prepareEntity(bookingDto);
			order.setOrderStatus(AppConstant.OrderStatus.BOOKED.getValue());
			Invoice invoice = invoiceDao.create(new Invoice());	
			
			setInvoiceNumber(order, invoice);
			
			BigDecimal orderTotal = new BigDecimal(0);
			//Calculate Total and set Total
			for(ServiceStaffDto serviceStaffDto:bookingDto.getServiceAndStaffList()){
				ServiceOffered serviceOffered = serviceOfferedDao.findById(serviceStaffDto.getServiceId());
				BigDecimal servicePrice = serviceOffered.getPrice();
				orderTotal = servicePrice.add(orderTotal,MathContext.UNLIMITED);
			}
			BigDecimal paidAmount = new BigDecimal(orderTotal.toString());			
			if(null != order.getLastMinDiscount()){
				paidAmount = orderTotal.subtract(order.getLastMinDiscount());
			}
			if(null != order.getLastMinDiscountPer()){
				MathContext mc = new MathContext(4); 
				BigDecimal differenceAmount = paidAmount.multiply(new BigDecimal(order.getLastMinDiscountPer()), mc);
				differenceAmount = differenceAmount.divide(new BigDecimal(100), RoundingMode.FLOOR);
				paidAmount = paidAmount.subtract(differenceAmount);
			}
			if(null != bookingDto.getTaxRate()){
				MathContext mc = new MathContext(4); 
				BigDecimal differenceAmount = paidAmount.multiply(new BigDecimal(bookingDto.getTaxRate()), mc);
				differenceAmount = differenceAmount.divide(new BigDecimal(100), RoundingMode.FLOOR);
				paidAmount = paidAmount.add(differenceAmount);
			}
			
			order.setTotal(orderTotal);
			order.setAmountPaid(paidAmount);
			order = orderDao.create(order);		
			// create Suborder
			
			createAndUpdateSuborder(order,bookingDto,null);
						
		}catch(HibernateException e){
			LOGGER.error("bookServiceAppoitment(BookingDto bookingDto)"+e);		
			e.printStackTrace();
		}
		LOGGER.info("bookServiceAppoitment(BookingDto bookingDto) ---End");		
		return order;
	
	}

	@Override
	public boolean rescheduleServiceAppoitment(BookingDto bookingDto) {
		LOGGER.info("rescheduleServiceAppoitment(BookingDto bookingDto) ---Start");
		boolean success = false;
		
		System.out.println("in service imple ");
		try{
			ClientOrder order = orderDao.findById(bookingDto.getBookingId());
			if(null != order)
				order.setLastUpdated(new Date());
			if(null != bookingDto.getBookingDate())
			{	
			
				String dateString = SMEUtils.dateAsString("yyyy-MM-dd",bookingDto.getBookingDate());
				String datePart[] = dateString.split("-");
				String timePart = bookingDto.getTime();
				String timeRepresentation = timePart.substring(5);
				timePart = timePart.substring(0, 5);
				String time[] = timePart.split(":");
				int hrs = Integer.parseInt(time[0]);
				int min = Integer.parseInt(time[1]);
				if(timeRepresentation.equals("PM")){
					if(hrs != 12){					
						hrs = hrs +12;
					}
					else
					{
						hrs = 0;
					}
				}
				
				Calendar cal = Calendar.getInstance();			
				if(time.length == 2)
					cal.set(Integer.parseInt(datePart[0]), Integer.parseInt(datePart[1])-1, Integer.parseInt(datePart[2]), hrs, min, 0);
				else
					cal.set(Integer.parseInt(datePart[0]), Integer.parseInt(datePart[1])-1, Integer.parseInt(datePart[2]), hrs, 0, 0);
				
				
				/*String dateString = SMEUtils.dateAsString("yyyy-MM-dd",bookingDto.getBookingDate());
				String datePart[] = dateString.split("-");
				String timePart = bookingDto.getTime();
				String time[] = timePart.split(":");
				Calendar cal = Calendar.getInstance();
				if(time.length == 2)
					cal.set(Integer.parseInt(datePart[0]), Integer.parseInt(datePart[1]), Integer.parseInt(datePart[2]), Integer.parseInt(time[0]), Integer.parseInt(time[1]), 0);
				else
					cal.set(Integer.parseInt(datePart[0]), Integer.parseInt(datePart[1]), Integer.parseInt(datePart[2]), Integer.parseInt(time[0]), 0, 0);*/
				order.setCreatedOn(cal.getTime());
				
			}
		
			orderDao.update(order);
			
			success = true;
			
		}catch(HibernateException e){
			LOGGER.error("rescheduleServiceAppoitment(BookingDto bookingDto)"+e);
			e.printStackTrace();
		}
		LOGGER.info("rescheduleServiceAppoitment(BookingDto bookingDto) ---End");		
		return success;
	}

	@Override
	public boolean markPaymentForService(BookingDto bookingDto) {
		LOGGER.info("markPaymentForService(BookingDto bookingDto) ---Start");
		boolean success = false;
		try{
			ClientOrder order = orderDao.findById(1);
			if(null != order)
				order.setLastUpdated(new Date());
			orderDao.update(order);
			success = true;
		}catch(HibernateException e){
			LOGGER.error("markPaymentForService(BookingDto bookingDto)"+e);
			e.printStackTrace();
		}
		LOGGER.info("markPaymentForService(BookingDto bookingDto) ---End");		
		return success;
	}

	
	private void setInvoiceNumber(ClientOrder order, Invoice invoice) {
		LOGGER.info("setInvoiceNumber(ClientOrder order, Invoice invoice) ---Start");
		Date today = Calendar.getInstance().getTime(); 
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd/HHmmss/");
		StringBuffer invoiceNo = new StringBuffer();
		invoiceNo.append(df.format(today));
		invoiceNo.append(order.getClient().getFirstName());
		invoiceNo.append("/"+invoice.getInvoiceId());
		order.setInvoiceNo(invoiceNo.toString());
		LOGGER.info("setInvoiceNumber(ClientOrder order, Invoice invoice) ---End");
	}
	
	private void createAndUpdateSuborder(ClientOrder order,BookingDto bookingDto ,Set<SubOrder> subOrders) {
		LOGGER.info("createAndUpdateSuborder(ClientOrder order,BookingDto bookingDto ) ---Start");
		if(null != subOrders){
			
				Map<Integer,SubOrder> subOrderMap = new HashMap<>();
				for(SubOrder subOrder:subOrders){
					subOrderMap.put(subOrder.getServiceOffered().getId(), subOrder);
				}		
				BigDecimal orderTotal = order.getTotal();
				for(ServiceStaffDto serviceStaffDto1:bookingDto.getServiceAndStaffList()){
					if(null != subOrderMap.get(serviceStaffDto1.getServiceId())){
						SubOrder subOrder3 = subOrderMap.get(serviceStaffDto1.getServiceId());
						subOrder3.setApplicationUser(userDao.findById(serviceStaffDto1.getStaffId()));
						subOrderDao.update(subOrder3);
					}
					else{
						SubOrder subOrder1 = subOrderMapper.prepareEntity(serviceStaffDto1);
						if(null != subOrder1){
							subOrder1.setClientOrder(order);
							orderTotal = orderTotal.add(subOrder1.getServiceCost(),MathContext.UNLIMITED);
							subOrderDao.create(subOrder1);
						}
					}
				}				
				order.setTotal(orderTotal);		
		}
		else{
			for(ServiceStaffDto serviceStaffDto:bookingDto.getServiceAndStaffList()){
				SubOrder subOrder2 = subOrderMapper.prepareEntity(serviceStaffDto);
				subOrder2.setClientOrder(order);
				
				subOrderDao.create(subOrder2);
			}
		}
		LOGGER.info("createAndUpdateSuborder(ClientOrder order,BookingDto bookingDto ) ---End");
	}

	@Override
	public boolean bookServiceAppoitmentNewClient(BookingDto bookingDtoReq) {
		LOGGER.info("bookServiceAppoitmentNewClient(BookingDto bookingDtoReq) ---Start");
		boolean success = false;
		try{
			//create client
			Client client = clientMapper.prepareEntity(bookingDtoReq);
			Organisation organisation=organisationDao.findById(bookingDtoReq.getOrganizationId());
			client.setOrganisation(organisation);
			client = clientDao.create(client);			
			ClientOrder order = bookingMapper.prepareEntity(bookingDtoReq,client);
			order.setClient(client);
			order.setOrderStatus(AppConstant.OrderStatus.BOOKED.getValue());
			Invoice invoice = invoiceDao.create(new Invoice());	
			
			setInvoiceNumber(order, invoice);
			
			BigDecimal orderTotal = new BigDecimal(0);
			//Calculate Total and set Total
			
			for(ServiceStaffDto serviceStaffDto:bookingDtoReq.getServiceAndStaffList()){
				ServiceOffered serviceOffered = serviceOfferedDao.findById(serviceStaffDto.getServiceId());
				BigDecimal servicePrice = serviceOffered.getPrice();
				orderTotal = servicePrice.add(orderTotal,MathContext.UNLIMITED);
			}
			
			order.setTotal(orderTotal);
			order = orderDao.create(order);
			
			
			// create Suborder
			
			createAndUpdateSuborder(order,bookingDtoReq,null);
			
			success = true;
		}catch(HibernateException e){
			LOGGER.error("bookServiceAppoitmentNewClient(BookingDto bookingDtoReq)"+e);		
			e.printStackTrace();
		}
		LOGGER.info("bookServiceAppoitmentNewClient(BookingDto bookingDtoReq) ---End");		
		return success;
	
	}

	@Override
	public BookingDto searchLastBookingDetails(Integer clientId) {
		LOGGER.info("searchLastBookingDetails(Integer clientId)  -------Start");
		
		BookingDto bookingDto = null;
		
		try{
			ClientOrder order = masterDatadao.searchLastBookingDetails(clientId);
			bookingDto = bookingMapper.prepareDto(order);
			
		}catch(HibernateException e){
			LOGGER.error("searchLastBookingDetails(Integer clientId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		LOGGER.info("searchLastBookingDetails(Integer clientId)  -------End");
		return bookingDto;
	}

	@Override
	public List<OrderDto> getOrderStatusDetails(String userId,Integer branchId) {
		LOGGER.info("getOrderStatusDetails(String userId)  -------Start");
		List<ClientOrder> ordersList =null;
		List<OrderDto> orderDtosList = null;
		try{
			ApplicationUser applicationUser = userDao.findByUUID(userId);
			 ordersList = orderDao.getOrderStatusDetails(applicationUser.getOrganisation().getId(),branchId);

			orderDtosList = orderMapper.prepareDto(ordersList);
						
		}catch(HibernateException e){
			LOGGER.error("getOrderStatusDetails(String userId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		LOGGER.info("getOrderStatusDetails(String userId)  -------End");
		return orderDtosList;
	}

	@Override
	public BookingDto getOrderDetails(Integer orderId) {
		LOGGER.info("getOrderDetails(Integer orderId)  -------Start");
		
		ClientOrder order = null;
		BookingDto bookingDto = null;
		try{
			order = orderDao.findById(orderId);
			bookingDto = bookingMapper.prepareDto(order);
			
		}catch(HibernateException e){
			
			LOGGER.error("getOrderDetails(Integer orderId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("getOrderDetails(Integer orderId)  -------End");
		return bookingDto;
	}

	@Override
	public List<OrderDto> getDashBoardDetails(Integer branchId) {
		LOGGER.info("getDashBoardDetails(String organizationId, Integer branchId)  -------Start");
		
		List<ClientOrder> ordersList =null;
		
		List<OrderDto> orderDtosList = null;
		
		try{
			
			 ordersList = orderDao.getDashBoardDetails(branchId);
			 
			 orderDtosList = orderMapper.prepareDashBoardDto(ordersList);
			 
		}catch(HibernateException e){
			LOGGER.error("getDashBoardDetails(String organizationId, Integer branchId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		LOGGER.info("getDashBoardDetails(String organizationId, Integer branchId)  -------End");
		return orderDtosList;
	}
	
	@Override
	public List<ServiceStaffDto> getTopServices(Integer branchId) {
		LOGGER.info("getTopServices(Integer branchId)----------Start ");
		
		List<SubOrder> subOrdersList = null;
		
		List<ServiceStaffDto> serviceStaffDtosList = null;
		
		try{
		
			subOrdersList = subOrderDao.getTopServices(branchId);
			
			serviceStaffDtosList = subOrderMapper.prepareServiceStaffDto(subOrdersList);
			
		}catch(HibernateException e){
			
			LOGGER.error("getTopServices(Integer branchId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		LOGGER.info("getTopServices(Integer branchId)----------End ");
		return serviceStaffDtosList;
	}

	@Override
	public List<ServiceStaffDto> getTopStaff(Integer branchId) {
		LOGGER.info("getTopStaff(Integer branchId)  -----------Start");
		
		List<SubOrder> subOrdersList = null;
		
		List<ServiceStaffDto> serviceStaffDtosList = null;
		
		try{
			
			Branch branch = branchDao.findById(branchId);
			
			subOrdersList = subOrderDao.getTopStaff(branch.getOrganisation().getId());
			
			serviceStaffDtosList = subOrderMapper.prepareServiceStaffDto(subOrdersList);
			
		}catch(HibernateException e){
			
			LOGGER.error("getTopStaff(Integer branchId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		LOGGER.info("getTopStaff(Integer branchId)  -----------End");
		return serviceStaffDtosList;
	}

	@Override
	public List<OrderDto> getDateWiseOrder(Date filterDate, Integer branchId) {
		LOGGER.info("getDateWiseOrder(Date filterDate, Integer branchId)-----------Start");
		
		List<ClientOrder> ordersList =null;
		List<OrderDto> orderDtosList = null;
		
		try{
			
			ordersList = orderDao.getDateWiseOrder(filterDate,branchId);
			
			orderDtosList = orderMapper.prepareDateWiseDto(ordersList);
			
		}catch(HibernateException e){
			
			LOGGER.error("getDateWiseOrder(Date filterDate, Integer branchId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		LOGGER.info("getDateWiseOrder(Date filterDate, Integer branchId)-----------End");
		return orderDtosList;
	}

	@Override
	public List<OrderDto> getWeekWiseOrder(Date filterDate, Integer branchId) {
		LOGGER.info("getWeekWiseOrder(Date filterDate, Integer branchId)-----------Start");
		
		List<ClientOrder> clientOrdersList =null;
		List<OrderDto> clientOrderDtosList = null;
		
		try{
			
			clientOrdersList = orderDao.getWeekWiseOrder(filterDate,branchId);
			
			clientOrderDtosList = orderMapper.prepareDateWiseDto(clientOrdersList);
			
		}catch(HibernateException e){
			
			LOGGER.error("getWeekWiseOrder(Date filterDate, Integer branchId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		LOGGER.info("getWeekWiseOrder(Date filterDate, Integer branchId)-----------End");
		return clientOrderDtosList;
	}

	@Override
	public List<OrderDto> getMonthWiseOrder(Integer branchId) {
		LOGGER.info("getMonthWiseOrder(Integer branchId)-----------Start");
		
		List<ClientOrder> clientOrdersList =null;
		List<OrderDto> clientOrderDtosList = null;
		
		try{
			
			clientOrdersList = orderDao.getMonthWiseOrder(branchId);
			
			clientOrderDtosList = orderMapper.prepareDateWiseDto(clientOrdersList);
			
		}catch(HibernateException e){
			
			LOGGER.error("getMonthWiseOrder(Integer branchId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		LOGGER.info("getMonthWiseOrder(Integer branchId)-----------End");
		return clientOrderDtosList;
	}

	@Override
	public boolean setFeedback(FeedBackDto feedBackDtoReq) {
		LOGGER.info("setFeedback(FeedBackDto feedBackDtoReq)----------------Start");
		
		boolean feedBackStatus = false;
		
		try
		{
			
				List<FeedbackSubdata> feedbackSubdataList = feedbackSubdataMapper.prepareEntityList(feedBackDtoReq);
				Client client = clientDao.findById(feedBackDtoReq.getClientId());
				ClientOrder clientOrder =orderDao.findById(feedBackDtoReq.getOrderId());
				Branch branch = branchDao.findById(feedBackDtoReq.getBranchId());
				
				for (FeedbackSubdata feedbackSubdata : feedbackSubdataList){
					
					feedbackSubdata.setClient(client);
					
					feedbackSubdata.setClientOrder(clientOrder);
					
					feedbackSubdata.setBranch(branch);
					
					feedbackSubdataDao.create(feedbackSubdata);
					
				}
				
				feedBackStatus = true;
				
		}catch(HibernateException e)
		{
		LOGGER.error("setFeedback(FeedBackDto feedBackDtoReq)"+e);
		e.printStackTrace();
		}
		
		LOGGER.info("setFeedback(FeedBackDto feedBackDtoReq)----------------End");
		return feedBackStatus;
	}

	@Override
	public List<FeedBackDto> getAllFeedback(Integer branchId) {
		LOGGER.info("getAllFeedback(Integer branchId)------------------Start");
		
		List<FeedBackDto> feedBackDtosList = new ArrayList<FeedBackDto>();
		
		try{
			List<FeedbackSubdata> feedbackSubdataList = feedbackSubdataDao.findAll(branchId);
			
			for(FeedbackSubdata feedbackSubdata : feedbackSubdataList){
				feedBackDtosList.add(feedbackSubdataMapper.prepareDto(feedbackSubdata));
			}
			
		}catch(HibernateException e){
			LOGGER.error("getAllFeedback(Integer branchId)"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		LOGGER.info("getAllFeedback(Integer branchId)------------------End");
		return feedBackDtosList;
	}
	
}
