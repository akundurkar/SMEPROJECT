package in.adcast.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;
import in.adcast.dao.FilterCustomerForOfferDao;
import in.adcast.model.FilterCustomerForoffer;


@SuppressWarnings("serial")
@Repository
public class FilterCustomerForOfferDaoImpl extends GenericDAOImpl<FilterCustomerForoffer, Integer> implements FilterCustomerForOfferDao {

	@Override
	public List<FilterCustomerForoffer> getFilterCustomerByOfferId(Integer offerId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
