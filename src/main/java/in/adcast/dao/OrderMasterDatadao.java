package in.adcast.dao;

import java.util.List;

import in.adcast.common.GenericDAO;
import in.adcast.dto.CancelOrderDto;
import in.adcast.model.OrderMasterdata;

public interface OrderMasterDatadao extends GenericDAO<OrderMasterdata, Integer> {
	
	public boolean savecancelOrderReason(CancelOrderDto cancelOrderDto);

	public List<OrderMasterdata> findAllCancellationReason(Integer id);
}


