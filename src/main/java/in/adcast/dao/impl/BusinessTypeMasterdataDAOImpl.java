
package in.adcast.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;

import in.adcast.dao.BusinessTypeMasterdataDao;

import in.adcast.model.BusinessTypeMasterdata;


@SuppressWarnings("serial")
@Repository
public class BusinessTypeMasterdataDAOImpl extends GenericDAOImpl<BusinessTypeMasterdata,Integer> implements BusinessTypeMasterdataDao {

	@Override
	public List<BusinessTypeMasterdata> findAll() {
		
		Query query = entityManager.createQuery("SELECT bt FROM BusinessTypeMasterdata bt");
		
		return (List<BusinessTypeMasterdata>)query.getResultList();
	}

}
