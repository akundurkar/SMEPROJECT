package in.adcast.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;

import in.adcast.dao.ReferralMasterdatadao;

import in.adcast.dto.ReferralSourceDto;

import in.adcast.model.ReferralMasterdata;


@SuppressWarnings("serial")
@Repository
public class ReferralMasterdataDaoImpl extends GenericDAOImpl<ReferralMasterdata,Integer> implements ReferralMasterdatadao  
{

	@Override
	public boolean savereferralResource(ReferralSourceDto ReferralSourceDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ReferralMasterdata> findAllReferralSourceDetails(Integer organizationId) {
		Query query = entityManager.createQuery("SELECT r FROM ReferralMasterdata r where r.organisation.id = " + organizationId);
		return (List<ReferralMasterdata>) query.getResultList();
	}
	
}
