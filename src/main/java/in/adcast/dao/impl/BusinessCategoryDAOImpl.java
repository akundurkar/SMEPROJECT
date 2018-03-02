
package in.adcast.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;
import in.adcast.dao.BusinessCategoryDao;
import in.adcast.model.BusinessCategoryMasterdata;





@SuppressWarnings("serial")
@Repository
public class BusinessCategoryDAOImpl extends GenericDAOImpl<BusinessCategoryMasterdata,Integer> implements BusinessCategoryDao {

	@Override
	public List<BusinessCategoryMasterdata> getAllBusinessType() {
		
		Query query = entityManager.createQuery("SELECT bc FROM BusinessCategoryMasterdata bc");
	
		return (List<BusinessCategoryMasterdata>)query.getResultList();
	}


}
