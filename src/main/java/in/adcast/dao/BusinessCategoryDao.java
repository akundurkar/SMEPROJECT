package in.adcast.dao;


import java.util.List;

import in.adcast.common.GenericDAO;
import in.adcast.model.BusinessCategoryMasterdata;



public interface BusinessCategoryDao extends GenericDAO<BusinessCategoryMasterdata, Integer> {

	public List<BusinessCategoryMasterdata> getAllBusinessType();

}
