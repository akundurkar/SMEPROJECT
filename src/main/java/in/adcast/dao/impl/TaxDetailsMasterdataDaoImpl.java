package in.adcast.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;

import in.adcast.dao.ReferralMasterdatadao;
import in.adcast.dao.TaxDetailsMasterdatadao;
import in.adcast.dto.ReferralSourceDto;
import in.adcast.dto.TaxDto;
import in.adcast.model.OrderMasterdata;
import in.adcast.model.ReferralMasterdata;
import in.adcast.model.TaxMasterdata;

@SuppressWarnings("serial")
@Repository
public class TaxDetailsMasterdataDaoImpl extends GenericDAOImpl<TaxMasterdata,Integer> implements TaxDetailsMasterdatadao  
{

	@Override
	public boolean saveTaxDetails(TaxDto taxDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TaxMasterdata> findAllTaxDetails(Integer organizationId) {
		Query query = entityManager.createQuery("SELECT t FROM TaxMasterdata t where t.organisation.id = " + organizationId);
	    return (List<TaxMasterdata>) query.getResultList();
		}
	
	
}
