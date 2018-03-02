package in.adcast.mapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.adcast.common.utils.AppConstant;
import in.adcast.common.utils.SMEUtils;
import in.adcast.dao.BranchDao;
import in.adcast.dao.ClientDao;
import in.adcast.dto.BookingDto;
import in.adcast.dto.ServiceDto;
import in.adcast.model.Client;
import in.adcast.model.ClientOrder;
import in.adcast.model.SubOrder;




@Component
public class BookingMapper {

	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private BranchDao branchDao;
	
	public ClientOrder prepareEntity(BookingDto bookingDto) {		
		
		ClientOrder order=new ClientOrder();
		if(bookingDto.getClientFirstName() != null && bookingDto.getClientFirstName() !=null && bookingDto.getClientFirstName().length() > 0 && bookingDto.getClientFirstName().length()>0){
			order.setClient(clientDao.getClientByMobileAndName(bookingDto.getOrganizationId(),bookingDto.getMobile(),bookingDto.getClientFirstName(),bookingDto.getClientLastName()));
		}
		else
		{
			order.setClient(clientDao.getClientByMobileOrName(bookingDto.getOrganizationId(),bookingDto.getMobile()).get(0));
		}
		if(null != bookingDto.getIpaddress()){
			order.setIpAdress(bookingDto.getIpaddress());
		}
		if(null != bookingDto)
		{
			order.setOnlineBooking(false);
			order.setLastUpdated(new Date());
			order.setOrderPaymentDone(false);				
		}
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
			order.setCreatedOn(cal.getTime());
			
		}
		if(null != bookingDto.getBranchId()){
			order.setBranch(branchDao.findById(bookingDto.getBranchId()));
		}
		if(null != bookingDto.getLastMinDiscountPer()){
			order.setLastMinDiscountPer(bookingDto.getLastMinDiscountPer());
		}
		if(null != bookingDto.getLastMinDiscount()){
			order.setLastMinDiscount(bookingDto.getLastMinDiscount());
		}
		if(null != bookingDto.getTaxType()){
			order.setTaxType(bookingDto.getTaxType());
		}
		if(null != bookingDto.getTaxRate()){
			order.setTaxRate(bookingDto.getTaxRate());
		}
		
		return order;
	}

	public BookingDto prepareDto(ClientOrder order) {
		
		BookingDto bookingDto = new BookingDto();
		
			bookingDto.setBookingId(order.getId());
			bookingDto.setDate(order.getCreatedOn());
			bookingDto.setTotalCost(order.getTotal());
			bookingDto.setClientFirstName(order.getClient().getFirstName());
			bookingDto.setClientLastName(order.getClient().getLastName());
			bookingDto.setMobile(order.getClient().getMobile());
			bookingDto.setClientAddress(order.getClient().getAdress1());
			bookingDto.setClientCity(order.getClient().getCity());
			bookingDto.setClientState(order.getClient().getState());
			bookingDto.setClientPincode(order.getClient().getPincode());
			bookingDto.setInvoiceNo(order.getInvoiceNo());
			
			switch(AppConstant.OrderStatus.values()[order.getOrderStatus()].toString()){
			
				case "BOOKED":
					bookingDto.setOrderStatus(AppConstant.OrderStatus.BOOKED.toString());
					break;
					
				case "CONFIRMED":
					bookingDto.setOrderStatus(AppConstant.OrderStatus.CONFIRMED.toString());
					break;
					
				case "REJECT":				
					bookingDto.setOrderStatus(AppConstant.OrderStatus.REJECT.toString());
					break;
					
				case "NOSHOW":
					bookingDto.setOrderStatus(AppConstant.OrderStatus.NOSHOW.toString());
					break;	
					
				case "COMPLETE":
					bookingDto.setOrderStatus(AppConstant.OrderStatus.COMPLETE.toString());
					break;	
					
				case "CHECKOUT":
					bookingDto.setOrderStatus(AppConstant.OrderStatus.CHECKOUT.toString());
					break;	
					
			}
			
			List<ServiceDto> serviceDtoList  = new ArrayList<>();
				  
				for(SubOrder subOrder: order.getSubOrders()){
					 
					 ServiceDto serviceDto = new ServiceDto();
					 serviceDto.setServiceId(subOrder.getServiceOffered().getId());
					 serviceDto.setServiceName(subOrder.getServiceOffered().getServiceName());
					 serviceDto.setRetailPrice(subOrder.getServiceOffered().getPrice());
					 serviceDto.setStaffName(subOrder.getApplicationUser().getFirstName()+" "+subOrder.getApplicationUser().getLastName());
					 serviceDtoList.add(serviceDto);
			
				  }
				  
			bookingDto.setServiceList(serviceDtoList);
			
		
		return bookingDto;
	}

	public ClientOrder prepareEntity(BookingDto bookingDto, Client client) {		
		
		ClientOrder order=new ClientOrder();
		if(null != client)
			order.setClient(client);
		
		if(null != bookingDto.getIpaddress()){
			order.setIpAdress(bookingDto.getIpaddress());
		}
		if(null != bookingDto)
		{
			order.setOnlineBooking(true);
			order.setLastUpdated(new Date());
			order.setOrderPaymentDone(false);				
		}
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
			order.setCreatedOn(cal.getTime());
			
		}
		if(null != bookingDto.getBranchId()){
			order.setBranch(branchDao.findById(bookingDto.getBranchId()));
		}
		if(null != bookingDto.getLastMinDiscountPer()){
			order.setLastMinDiscountPer(bookingDto.getLastMinDiscountPer());
		}
		if(null != bookingDto.getLastMinDiscount()){
			order.setLastMinDiscount(bookingDto.getLastMinDiscount());
		}
		if(null != bookingDto.getTaxType()){
			order.setTaxType(bookingDto.getTaxType());
		}
		if(null != bookingDto.getTaxRate()){
			order.setTaxRate(bookingDto.getTaxRate());
		}
		return order;
	}
	
	
	
}
