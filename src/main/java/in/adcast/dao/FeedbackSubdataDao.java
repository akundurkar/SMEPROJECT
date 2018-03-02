package in.adcast.dao;



import java.util.List;

import in.adcast.common.GenericDAO;
import in.adcast.model.FeedbackSubdata;



public interface FeedbackSubdataDao  extends GenericDAO<FeedbackSubdata, Integer>{

	List<FeedbackSubdata> findAll(Integer branchId);
	
	
}
