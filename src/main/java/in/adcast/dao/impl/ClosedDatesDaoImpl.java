package in.adcast.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;
import in.adcast.dao.ClosedDatesdao;
import in.adcast.dao.ReferralMasterdatadao;
import in.adcast.dao.TaxDetailsMasterdatadao;
import in.adcast.dto.ReferralSourceDto;
import in.adcast.dto.TaxDto;
import in.adcast.model.ClosedDates;
import in.adcast.model.OrderMasterdata;
import in.adcast.model.ReferralMasterdata;
import in.adcast.model.TaxMasterdata;

@SuppressWarnings("serial")
@Repository
public class ClosedDatesDaoImpl extends GenericDAOImpl<ClosedDates,Integer> implements ClosedDatesdao  
{

	@Override
	public List<ClosedDates> getAllClosedDates(Integer branchId) {
		
		Query query = entityManager.createQuery("SELECT cd FROM ClosedDates cd where cd.branch.id = " + branchId);
		 return (List<ClosedDates>) query.getResultList();
	}

	
}
