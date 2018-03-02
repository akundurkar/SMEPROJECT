package in.adcast.dao;


import java.util.List;
import in.adcast.common.GenericDAO;
import in.adcast.model.FilterCustomerForoffer;


public interface FilterCustomerForOfferDao extends GenericDAO<FilterCustomerForoffer, Integer>{
	
	public List<FilterCustomerForoffer> getFilterCustomerByOfferId(Integer offerId);

}
