package in.adcast.dao;


import java.util.List;
import in.adcast.common.GenericDAO;
import in.adcast.dto.TaxDto;
import in.adcast.model.ClosedDates;
import in.adcast.model.TaxMasterdata;


public interface ClosedDatesdao extends GenericDAO<ClosedDates, Integer> {

	List<ClosedDates> getAllClosedDates(Integer branchId);
	
	
	
}
