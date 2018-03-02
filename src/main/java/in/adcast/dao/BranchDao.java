package in.adcast.dao;


import java.util.List;
import in.adcast.common.GenericDAO;
import in.adcast.model.Branch;


public interface BranchDao extends GenericDAO<Branch, Integer> {
		
	public List<Branch> findbyName(String branchName);

	public List<Branch> findAll(Integer id);
		
}
