package in.adcast.dao;


import java.util.List;

import in.adcast.common.GenericDAO;
import in.adcast.model.SubOrder;


public interface SubOrderDao extends GenericDAO<SubOrder, Integer> 
{

	public void test();

	public List<SubOrder> getTopServices(Integer branchId);

	public List<SubOrder> getTopStaff(Integer organisationId);
	
}
