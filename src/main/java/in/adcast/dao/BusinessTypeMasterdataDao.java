package in.adcast.dao;




import java.util.List;

import in.adcast.common.GenericDAO;

import in.adcast.model.BusinessTypeMasterdata;



public interface BusinessTypeMasterdataDao extends GenericDAO<BusinessTypeMasterdata, Integer> {


  public List<BusinessTypeMasterdata> findAll();
}
