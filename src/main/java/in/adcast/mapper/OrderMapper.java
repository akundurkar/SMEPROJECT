package in.adcast.mapper;


import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import in.adcast.common.utils.AppConstant;
import in.adcast.dto.AppointmentHistoryDto;
import in.adcast.dto.OrderDto;
import in.adcast.dto.ServiceStaffDto;
import in.adcast.dto.UpcomingAppointmentDto;
import in.adcast.model.ClientOrder;
import in.adcast.model.SubOrder;


@Component
public class OrderMapper {

	public List<OrderDto> prepareDto(List<ClientOrder> ordersList) {
		
		List<OrderDto> orderDtoList = new ArrayList<>();
		
		for(ClientOrder order: ordersList){
			
			OrderDto orderDto = new OrderDto();
			
			orderDto.setId(order.getClient().getId());
			orderDto.setOrderId(order.getId());
			orderDto.setClientName(order.getClient().getFirstName());
			orderDto.setDate(order.getCreatedOn());
			/*orderDto.setOrderStatus(order.getOrderStatus());*/ // add switchcase for order status
			switch(AppConstant.OrderStatus.values()[order.getOrderStatus()].toString()){
			
				case "BOOKED" : 
					orderDto.setOrderStatus(AppConstant.OrderStatus.BOOKED.toString());
				    break;
				
				case "CONFIRMED":
					orderDto.setOrderStatus(AppConstant.OrderStatus.CONFIRMED.toString());
					break;
					
				case "REJECT" :
					orderDto.setOrderStatus(AppConstant.OrderStatus.REJECT.toString());
					break;
				
				case "NOSHOW" :
					orderDto.setOrderStatus(AppConstant.OrderStatus.NOSHOW.toString());
					break;
				
				case "COMPLETE" :
					orderDto.setOrderStatus(AppConstant.OrderStatus.COMPLETE.toString());
					break;
					
				case "CHECKOUT":
					orderDto.setOrderStatus(AppConstant.OrderStatus.CHECKOUT.toString());
				    break;	
		
			}
			
			orderDtoList.add(orderDto);
		}
		
		return orderDtoList;
	}

	public OrderDto prepareDto(ClientOrder order) {
		
		OrderDto orderDto = new OrderDto();
		
		if(null!=order.getId())
			orderDto.setId(order.getId());
		if(null!=order.getClient())
			orderDto.setClientName(order.getClient().getFirstName());
		    orderDto.setClientlastname(order.getClient().getLastName());
		if(null!=order.getCreatedOn())
		    orderDto.setDate(order.getCreatedOn());
		if(null!=order.getInvoiceNo())
			orderDto.setInvoiceNo(order.getInvoiceNo());
		if(null!=order.getTotal())
		    orderDto.setTotal(order.getTotal());
		
 		return orderDto;
	}

	public List<OrderDto> prepareDashBoardDto(List<ClientOrder> ordersList) {
		
		List<OrderDto> orderDtoList = new ArrayList<>();
		
		for(ClientOrder order: ordersList){
			
			OrderDto orderDto = new OrderDto();
			
			orderDto.setOrderId(order.getId());
			
			orderDto.setDate(order.getCreatedOn());
			
			orderDto.setClientName(order.getClient().getFirstName());
			orderDto.setClientlastname(order.getClient().getLastName());
			switch(AppConstant.OrderStatus.values()[order.getOrderStatus()].toString()){
				
				case "BOOKED" : 
					orderDto.setOrderStatus(AppConstant.OrderStatus.BOOKED.toString());
				    break;
				
				case "CONFIRMED":
					orderDto.setOrderStatus(AppConstant.OrderStatus.CONFIRMED.toString());
					break;
					
				case "REJECT" :
					orderDto.setOrderStatus(AppConstant.OrderStatus.REJECT.toString());
					break;
				
				case "NOSHOW" :
					orderDto.setOrderStatus(AppConstant.OrderStatus.NOSHOW.toString());
					break;
				
				case "COMPLETE" :
					orderDto.setOrderStatus(AppConstant.OrderStatus.COMPLETE.toString());
					break;
					
				case "CHECKOUT":
					orderDto.setOrderStatus(AppConstant.OrderStatus.CHECKOUT.toString());
				    break;	
			
			}
			
				List<ServiceStaffDto> serviceStaffDtoList = new ArrayList<>();
				
				for(SubOrder subOrder : order.getSubOrders()){
					
					ServiceStaffDto serviceStaffDto = new ServiceStaffDto();
					
						serviceStaffDto.setServiceName(subOrder.getServiceOffered().getServiceName());
						serviceStaffDto.setStaffName(subOrder.getApplicationUser().getFirstName()+" "+subOrder.getApplicationUser().getLastName());
					
					serviceStaffDtoList.add(serviceStaffDto);
					
				}
				
			orderDto.setServiceNameAndStaffNameList(serviceStaffDtoList);
			orderDtoList.add(orderDto);
		}
		
		return orderDtoList;
	}

	public List<OrderDto> prepareDateWiseDto(List<ClientOrder> ordersList) {
		
		List<OrderDto> orderDtoList = new ArrayList<>();
				
				for(ClientOrder order: ordersList){
					
					OrderDto orderDto = new OrderDto();
					
					orderDto.setOrderId(order.getId());
					
					orderDto.setClientName(order.getClient().getFirstName());
					
					orderDto.setDate(order.getCreatedOn());
					
					orderDto.setTotal(order.getTotal());
					
					List<ServiceStaffDto> serviceStaffDtoList = new ArrayList<>();
					
					for(SubOrder subOrder : order.getSubOrders()){
						
						ServiceStaffDto serviceStaffDto = new ServiceStaffDto();
						
							serviceStaffDto.setServiceName(subOrder.getServiceOffered().getServiceName());
							serviceStaffDto.setStaffName(subOrder.getApplicationUser().getFirstName()+" "+subOrder.getApplicationUser().getLastName());
							serviceStaffDto.setServiceTime(subOrder.getServiceOffered().getDurationInHrs());
							
						
						serviceStaffDtoList.add(serviceStaffDto);
						
					}
					
					orderDto.setServiceNameAndStaffNameList(serviceStaffDtoList);
					
					switch(AppConstant.OrderStatus.values()[order.getOrderStatus()].toString()){
					
					case "BOOKED" : 
						orderDto.setOrderStatus(AppConstant.OrderStatus.BOOKED.toString());
					    break;
					
					case "CONFIRMED":
						orderDto.setOrderStatus(AppConstant.OrderStatus.CONFIRMED.toString());
						break;
						
					case "REJECT" :
						orderDto.setOrderStatus(AppConstant.OrderStatus.REJECT.toString());
						break;
					
					case "NOSHOW" :
						orderDto.setOrderStatus(AppConstant.OrderStatus.NOSHOW.toString());
						break;
					
					case "COMPLETE" :
						orderDto.setOrderStatus(AppConstant.OrderStatus.COMPLETE.toString());
						break;
						
					case "CHECKOUT":
						orderDto.setOrderStatus(AppConstant.OrderStatus.CHECKOUT.toString());
					    break;	
				
				}
					
					
					
				orderDtoList.add(orderDto);
			}
			
			return orderDtoList;
	}
	
	
	public List<UpcomingAppointmentDto> prepareUpcomingAppointmentDto(List<ClientOrder> clientOrdersList) {
		
		List<UpcomingAppointmentDto> upcomingAppointmentDtosList = new ArrayList<>();
		
			for(ClientOrder order: clientOrdersList){
				
				for(SubOrder subOrder : order.getSubOrders()){
					
					UpcomingAppointmentDto upcomingAppointmentDto = new UpcomingAppointmentDto();
				
						upcomingAppointmentDto.setOrderId(order.getId());
						upcomingAppointmentDto.setClientName(order.getClient().getFirstName());
						upcomingAppointmentDto.setClientlastname(order.getClient().getLastName());
						upcomingAppointmentDto.setDate(order.getCreatedOn());
						switch(AppConstant.OrderStatus.values()[order.getOrderStatus()].toString()){
						
						case "BOOKED" : 
							upcomingAppointmentDto.setOrderStatus(AppConstant.OrderStatus.BOOKED.toString());
						    break;
						
						case "CONFIRMED":
							upcomingAppointmentDto.setOrderStatus(AppConstant.OrderStatus.CONFIRMED.toString());
							break;
							
						case "REJECT" :
							upcomingAppointmentDto.setOrderStatus(AppConstant.OrderStatus.REJECT.toString());
							break;
						
						case "NOSHOW" :
							upcomingAppointmentDto.setOrderStatus(AppConstant.OrderStatus.NOSHOW.toString());
							break;
						
						case "COMPLETE" :
							upcomingAppointmentDto.setOrderStatus(AppConstant.OrderStatus.COMPLETE.toString());
							break;
							
						case "CHECKOUT":
							upcomingAppointmentDto.setOrderStatus(AppConstant.OrderStatus.CHECKOUT.toString());
						    break;	
					
					}
					
							upcomingAppointmentDto.setServiceId(subOrder.getServiceOffered().getId());
							upcomingAppointmentDto.setServiceName(subOrder.getServiceOffered().getServiceName());
							upcomingAppointmentDto.setServiceTime(subOrder.getServiceOffered().getDurationInHrs());
							upcomingAppointmentDto.setStaffId(subOrder.getApplicationUser().getId());
							upcomingAppointmentDto.setStaffName(subOrder.getApplicationUser().getFirstName()+" "+subOrder.getApplicationUser().getLastName());
							
						upcomingAppointmentDtosList.add(upcomingAppointmentDto);
				}
			 	
			}
			
		return upcomingAppointmentDtosList;
	}

	public List<AppointmentHistoryDto> prepareAppointmentHistoryDto(List<ClientOrder> orderList) {
		
		List<AppointmentHistoryDto> appointmentHistoryDtosList = new ArrayList<>();
		
			for(ClientOrder order: orderList){
				
				for(SubOrder subOrder : order.getSubOrders()){
					
					AppointmentHistoryDto appointmentHistoryDto = new AppointmentHistoryDto();
				
						appointmentHistoryDto.setOrderId(order.getId());
						appointmentHistoryDto.setClientName(order.getClient().getFirstName());
						appointmentHistoryDto.setClientlastname(order.getClient().getLastName());
						appointmentHistoryDto.setDate(order.getCreatedOn());
						switch(AppConstant.OrderStatus.values()[order.getOrderStatus()].toString()){
						
						case "BOOKED" : 
							appointmentHistoryDto.setOrderStatus(AppConstant.OrderStatus.BOOKED.toString());
						    break;
						
						case "CONFIRMED":
							appointmentHistoryDto.setOrderStatus(AppConstant.OrderStatus.CONFIRMED.toString());
							break;
							
						case "REJECT" :
							appointmentHistoryDto.setOrderStatus(AppConstant.OrderStatus.REJECT.toString());
							break;
						
						case "NOSHOW" :
							appointmentHistoryDto.setOrderStatus(AppConstant.OrderStatus.NOSHOW.toString());
							break;
						
						case "COMPLETE" :
							appointmentHistoryDto.setOrderStatus(AppConstant.OrderStatus.COMPLETE.toString());
							break;
							
						case "CHECKOUT":
							appointmentHistoryDto.setOrderStatus(AppConstant.OrderStatus.CHECKOUT.toString());
						    break;	
					
					}
					
							appointmentHistoryDto.setServiceId(subOrder.getServiceOffered().getId());
							appointmentHistoryDto.setServiceName(subOrder.getServiceOffered().getServiceName());
							appointmentHistoryDto.setServiceTime(subOrder.getServiceOffered().getDurationInHrs());
							appointmentHistoryDto.setStaffId(subOrder.getApplicationUser().getId());
							appointmentHistoryDto.setStaffName(subOrder.getApplicationUser().getFirstName()+" "+subOrder.getApplicationUser().getLastName());
							
					appointmentHistoryDtosList.add(appointmentHistoryDto);
				}
			 	
			}
			
		return appointmentHistoryDtosList;
	}

	


}
