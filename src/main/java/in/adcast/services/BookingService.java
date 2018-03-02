package in.adcast.services;

import java.util.Date;
import java.util.List;

import in.adcast.dto.BookingDto;
import in.adcast.dto.FeedBackDto;
import in.adcast.dto.OrderDto;
import in.adcast.dto.ServiceStaffDto;

public interface BookingService {
	
	/**
	 * This will create a user account. 
	 * @param LoginDto loginDto.
	 * @return 	UserDto.
	 */
	
		public boolean bookServiceAppoitment(BookingDto bookingDto);
		
		public boolean cancelServiceAppoitment(BookingDto bookingDto);
		
		public boolean changeServiceStatusAppoitment(BookingDto bookingDto);
		
		public boolean rescheduleServiceAppoitment(BookingDto bookingDto);
		
		public boolean markPaymentForService(BookingDto bookingDto);

		public boolean bookServiceAppoitmentNewClient(BookingDto bookingDtoReq);

		public BookingDto searchLastBookingDetails(Integer clientId);

		public List<OrderDto> getOrderStatusDetails(String userId,Integer branchId);
		
		public BookingDto getOrderDetails(Integer orderId);

		public List<OrderDto> getDashBoardDetails(Integer branchId);
		
		public List<ServiceStaffDto> getTopServices(Integer branchId);

		public List<ServiceStaffDto> getTopStaff(Integer branchId);

		public List<OrderDto> getDateWiseOrder(Date filterDate, Integer branchId);

		public List<OrderDto> getWeekWiseOrder(Date filterDate, Integer branchId);

		public List<OrderDto> getMonthWiseOrder(Integer branchId);

		public boolean setFeedback(FeedBackDto feedBackDtoReq);

		public List<FeedBackDto> getAllFeedback(Integer branchId);

		
		
	}
