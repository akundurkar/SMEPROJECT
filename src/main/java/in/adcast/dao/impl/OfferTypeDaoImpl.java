/**
 * 
 */
package in.adcast.dao.impl;



import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;
import in.adcast.dao.OfferTypeDao;
import in.adcast.dto.OfferTypeDto;
import in.adcast.model.OfferType;





/**
 * @author Mahendra
 *
 */
@SuppressWarnings("serial")
@Repository
public class OfferTypeDaoImpl extends GenericDAOImpl<OfferType,Integer> implements OfferTypeDao {

	@Override
	public void test() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OfferType> searcEventNameList(Integer organisationId) {

			Query query = entityManager.createQuery("SELECT ot FROM OfferType ot WHERE ot.organisation.id=:organisationId And ot.event=1");
			query.setParameter("organisationId", organisationId);
			return (List<OfferType>) query.getResultList();

	}

	@Override
	public List<OfferType> searcFestivalNameList(Integer organisationId) {
		Query query = entityManager.createQuery("SELECT ot FROM OfferType ot WHERE ot.organisation.id=:organisationId And ot.festival=1");
		query.setParameter("organisationId", organisationId);
		return (List<OfferType>) query.getResultList();
	}

	@Override
	public boolean saveOfferTypeDetails(OfferTypeDto offerTypeDto) {
		// TODO Auto-generated method stub
		return false;
	}

	
		
}
