package in.adcast.dao;


import java.util.List;
import in.adcast.common.GenericDAO;
import in.adcast.model.ServiceOffered;


public interface ServiceOfferedDao extends GenericDAO<ServiceOffered, Integer> 
{
	
	public List<ServiceOffered> findbyName(String branchName);
		
	//To DO:Deprecate This
	public List<ServiceOffered> listServicesByBranchId(Integer branchId);
	
}
