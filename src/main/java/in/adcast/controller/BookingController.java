package in.adcast.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.adcast.common.utils.AppConstant;
import in.adcast.common.utils.SMEUtils;
import in.adcast.dto.BookingDto;
import in.adcast.dto.ClientDto;
import in.adcast.dto.FeedBackDto;
import in.adcast.dto.OrderDto;
import in.adcast.dto.ServiceStaffDto;
import in.adcast.exception.ClientNotFoundException;
import in.adcast.exception.CustomRuntimeException;
import in.adcast.services.BookingService;

@RestController
public class BookingController {

	private static final String COMMON_SERVICE_PATH = "/rest/booking/";
	@Autowired
	private BookingService bookingService;
		
	@RequestMapping(value=COMMON_SERVICE_PATH+"newClient", method=RequestMethod.POST)	
	public String bookServiceAppoitmentNewClient(@RequestBody BookingDto bookingDtoReq ,HttpServletResponse response,HttpServletRequest request){
		
		boolean success = false ;
		bookingDtoReq.setIpaddress(request.getRemoteAddr());
		if("NEWUSER".equals(bookingDtoReq.getExistingUser())){
			success = bookingService.bookServiceAppoitmentNewClient(bookingDtoReq);
		}
		else{
			success = bookingService.bookServiceAppoitment(bookingDtoReq);
		}
		System.out.println(bookingDtoReq.toString());
		
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	@RequestMapping(value=COMMON_SERVICE_PATH+"oldclient", method=RequestMethod.POST)	
	public String bookServiceAppoitment(@RequestBody BookingDto bookingDtoReq ,HttpServletResponse response,HttpServletRequest request){
		
		boolean success = false ;
		bookingDtoReq.setIpaddress(request.getRemoteAddr());
		success = bookingService.bookServiceAppoitment(bookingDtoReq);
		
		System.out.println(bookingDtoReq.toString());
		
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	@RequestMapping(value=COMMON_SERVICE_PATH+"cancel", method=RequestMethod.POST)	
	public String cancelServiceAppoitment(@RequestBody BookingDto bookingDtoReq ,HttpServletResponse response){
		
		boolean success = false ;
		success = bookingService.cancelServiceAppoitment(bookingDtoReq);
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	@RequestMapping(value=COMMON_SERVICE_PATH+"changeOrderStatus", method=RequestMethod.POST)	
	public String changeServiceStatusAppoitment(@RequestBody BookingDto bookingDtoReq ,HttpServletResponse response,HttpServletRequest request){

		boolean success = false ;
		bookingDtoReq.setIpaddress(request.getRemoteAddr());
		success = bookingService.changeServiceStatusAppoitment(bookingDtoReq);
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	@RequestMapping(value=COMMON_SERVICE_PATH+"rescheduleServiceAppoitment", method=RequestMethod.POST)	
	public String rescheduleServiceAppoitment(@RequestBody BookingDto bookingDtoReq ,HttpServletResponse response){
		
		boolean success = false ;
		success = bookingService.rescheduleServiceAppoitment(bookingDtoReq);
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	@RequestMapping(value=COMMON_SERVICE_PATH+"markPaymentForService", method=RequestMethod.POST)	
	public String markPaymentForService(@RequestBody BookingDto bookingDtoReq ,HttpServletResponse response){
		
		boolean success = false ;
		success = bookingService.markPaymentForService(bookingDtoReq);
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	@RequestMapping(value=COMMON_SERVICE_PATH+"searchLastBookingDetails",method=RequestMethod.GET)
	public List<BookingDto> searchLastBookingDetails(@RequestParam(required = false)Integer clientId)
	{
		BookingDto bookingDto = null ;
		List<BookingDto> bookingDtoList = new LinkedList<BookingDto>();
		try{
			bookingDto = bookingService.searchLastBookingDetails(clientId);
			bookingDtoList.add(bookingDto);
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return bookingDtoList;
		
	}
	
	@RequestMapping(value=COMMON_SERVICE_PATH+"getOrderStatus/{userId}/{branchId}",method=RequestMethod.GET)
	public List<OrderDto> getOrderStatus(@PathVariable("userId") String userId,@PathVariable("branchId") Integer branchId)
	{ 
		
		List<OrderDto> OrderDtoList = null;
		
		try{
			
			OrderDtoList = bookingService.getOrderStatusDetails(userId,branchId);
			
		}catch(Exception e)
		{
			
			e.printStackTrace();
			
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return OrderDtoList;
		
	}
	
		
	@RequestMapping(value=COMMON_SERVICE_PATH+"getDateWiseOrder",method=RequestMethod.GET)
	public List<OrderDto> getDateWiseOrder(@RequestParam(required = false) String filterDate,@RequestParam(required = false) Integer branchId)
	{ 
		
		List<OrderDto> OrderDtoList = null;
		
		try{
			
			Date date =SMEUtils.getDateFromString(filterDate,"yyyy-MM-dd");
			
			OrderDtoList = bookingService.getDateWiseOrder(date,branchId);
			
		}catch(Exception e)
		{
			
			e.printStackTrace();
			
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return OrderDtoList;
		
	}
	
	
	@RequestMapping(value=COMMON_SERVICE_PATH+"getWeekWiseOrder",method=RequestMethod.GET)
	public List<OrderDto> getWeekWiseOrder(@RequestParam(required = false) String filterDate,@RequestParam(required = false) Integer branchId)
	{ 
		
		List<OrderDto> OrderDtoList = null;
		
		try{
			
			Date date =SMEUtils.getDateFromString(filterDate,"yyyy-MM-dd");
			
			OrderDtoList = bookingService.getWeekWiseOrder(date,branchId);
			
		}catch(Exception e)
		{
			
			e.printStackTrace();
			
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return OrderDtoList;
		
	}
	
	@RequestMapping(value=COMMON_SERVICE_PATH+"getMonthWiseOrder",method=RequestMethod.GET)
	public List<OrderDto> getMonthWiseOrder(@RequestParam(required = false) Integer branchId)
	{ 
	
		List<OrderDto> OrderDtoList = null;
		
		try{
			
			OrderDtoList = bookingService.getMonthWiseOrder(branchId);
			
		}catch(Exception e)
		{
			
			e.printStackTrace();
			
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return OrderDtoList;
		
	}
	
	
	@RequestMapping(value=COMMON_SERVICE_PATH+"getDashBoardDetails",method=RequestMethod.GET)
	public List<OrderDto> getDashBoardDetails(@RequestParam(required = false) Integer branchId)
	{ 
		
		List<OrderDto> orderDtoList = null;
		
		try{
			
			orderDtoList = bookingService.getDashBoardDetails(branchId);
			
		}catch(Exception e)
		{
			
			e.printStackTrace();
			
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return orderDtoList;
		
	}
	
	@RequestMapping(value=COMMON_SERVICE_PATH+"getTopServices",method=RequestMethod.GET)
	public List<ServiceStaffDto> getTopServices(@RequestParam(required = false) Integer branchId)
	{ 
		
		List<ServiceStaffDto> serviceStaffDtosList = null;
		
		try{
			
			serviceStaffDtosList = bookingService.getTopServices(branchId);
			
		}catch(Exception e)
		{
			
			e.printStackTrace();
			
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return serviceStaffDtosList;
		
	}
	
	
	@RequestMapping(value=COMMON_SERVICE_PATH+"getTopStaff",method=RequestMethod.GET)
	public List<ServiceStaffDto> getTopStaff(@RequestParam(required = false) Integer branchId)
	{ 
		
		List<ServiceStaffDto> serviceStaffDtosList = null;
		
		try{
			
			serviceStaffDtosList = bookingService.getTopStaff(branchId);
			
		}catch(Exception e)
		{
			
			e.printStackTrace();
			
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return serviceStaffDtosList;
		
	}
	
	@RequestMapping(value=COMMON_SERVICE_PATH+"/getOrderDetails", method=RequestMethod.POST)
	public BookingDto getOrderDetails(@RequestParam(required = false) Integer orderId) 
	{
		
		BookingDto bookingDto = bookingService.getOrderDetails(orderId);
		
		return bookingDto;
	
	}	
	
	@RequestMapping(value=COMMON_SERVICE_PATH+"setFeedback", method=RequestMethod.POST)	
	public String setFeedback(@RequestBody FeedBackDto FeedBackDtoReq ,HttpServletResponse response){
		
		System.out.println("in setFeedback");
		boolean success = false ;
	
			success = bookingService.setFeedback(FeedBackDtoReq);
	
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	@RequestMapping(value=COMMON_SERVICE_PATH +"/getAllFeedback", method=RequestMethod.GET)
	public 	List<FeedBackDto> getAllFeedback(@RequestParam(required = false) Integer branchId) {
		
		List<FeedBackDto> feedBackDtoList = null;
		try{
			feedBackDtoList = bookingService.getAllFeedback(branchId);
			
		} catch (Exception e){
			
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return feedBackDtoList;
		
	}
	
}
