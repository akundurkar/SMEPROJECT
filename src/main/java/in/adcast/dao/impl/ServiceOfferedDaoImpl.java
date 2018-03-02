package in.adcast.dao.impl;

import java.util.List;

import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import in.adcast.common.GenericDAOImpl;
import in.adcast.dao.ServiceOfferedDao;
import in.adcast.dto.ServiceDto;
import in.adcast.model.ServiceMasterdata;
import in.adcast.model.ServiceOffered;

@SuppressWarnings("serial")
@Repository
public class ServiceOfferedDaoImpl  extends GenericDAOImpl<ServiceOffered,Integer>  implements ServiceOfferedDao {

	@Override
	public List<ServiceOffered> findbyName(String branchName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ServiceOffered> listServicesByBranchId(Integer branchId) {
		Query query = entityManager.createQuery("SELECT so FROM ServiceOffered so WHERE so.branch.id=:branchId order by so.serviceMasterdata.id");
		query.setParameter("branchId", branchId);
		return (List<ServiceOffered>) query.getResultList();
	}

	
}
