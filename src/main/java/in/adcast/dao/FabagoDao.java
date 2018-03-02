package in.adcast.dao;


import java.util.List;
import in.adcast.common.GenericDAO;
import in.adcast.model.BusinessTypeMasterdata;


public interface FabagoDao extends GenericDAO<BusinessTypeMasterdata, Integer> 
{
	
	public List<BusinessTypeMasterdata> getBusinessTypeName(Integer organizationId,String queri);
	
}
