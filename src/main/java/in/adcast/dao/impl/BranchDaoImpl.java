package in.adcast.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;
import in.adcast.dao.BranchDao;
import in.adcast.model.Branch;

@SuppressWarnings("serial")
@Repository
public class BranchDaoImpl extends GenericDAOImpl<Branch, Integer> implements BranchDao {

	@Override
	public List<Branch> findbyName(String branchName) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<Branch> findAll(Integer organizationId) {
		Query query = entityManager.createQuery("SELECT b FROM Branch b where b.organisation.id = " + organizationId);
		return (List<Branch>) query.getResultList();
	}

}
