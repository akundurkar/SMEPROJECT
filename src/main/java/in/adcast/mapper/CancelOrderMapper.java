package in.adcast.mapper;

import org.springframework.stereotype.Component;
import in.adcast.dto.CancelOrderDto;
import in.adcast.model.OrderMasterdata;


@Component
public class CancelOrderMapper 
{

	public OrderMasterdata prpareEntity(CancelOrderDto cancelOrderDto){
		
		OrderMasterdata orderMasterData =new OrderMasterdata();
		
		if(null!=cancelOrderDto.getCancel_order_reason())
			orderMasterData.setCancelReason(cancelOrderDto.getCancel_order_reason());	
			
		return orderMasterData;
	}
	
	
	public CancelOrderDto prepareDto(OrderMasterdata masterdata){
			 
			CancelOrderDto cancelOrderDto = new CancelOrderDto();
				
			if(null != masterdata.getCancelReason())
			
				cancelOrderDto.setCancel_order_reason(masterdata.getCancelReason());
		
			if(null != masterdata.getId())
			
				cancelOrderDto.setId(masterdata.getId());
		
	
		return cancelOrderDto;
	}
	
	
}
