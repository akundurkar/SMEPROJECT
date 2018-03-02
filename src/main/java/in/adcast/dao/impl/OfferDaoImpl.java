package in.adcast.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;
import in.adcast.dao.OfferDao;
import in.adcast.model.Offers;


@Repository
public class OfferDaoImpl extends GenericDAOImpl<Offers,Integer>  implements OfferDao  
{
	@SuppressWarnings("unchecked")
	@Override
	public List<Offers> getOffersByBranch(Integer branchId)
			{
				
				Query query = entityManager.createQuery("SELECT o FROM  Offers o where o.branch.id = " + branchId);
				//Query query = entityManager.createQuery("SELECT o FROM Offers o where  o.reminderNotice = DATE(sysdate()) and o.offerStatus = 1 and o.offerSent = 0");
				//Query query = entityManager.createQuery("SELECT o FROM offers o where o.offer_status = 1 and o.offer_sent is NOT NULL"); 
				//and o.offer_status = 1 and o.offer_sent is NOT NULL");

				/*System.out.println("query"+query);*/
				
				return (List<Offers>) query.getResultList();
			   
			}

	@SuppressWarnings("unchecked")
	@Override
	public List<Offers> findActiveOffers() {
		//Query query = entityManager.createQuery("SELECT o FROM  Offers o");		
		Query query = entityManager.createQuery("SELECT o FROM Offers o where  o.reminderNotice = DATE(sysdate()) and o.offerStatus = 1 and o.offerSent = 0");
		
		return (List<Offers>) query.getResultList();
	}

	@Override
	public List<Offers> findBirthdayOffers() {
		Query query = entityManager.createQuery("SELECT o FROM  Offers o where o.offerType.id = 3 AND o.offerStatus = true");		
		return (List<Offers>) query.getResultList();
	}

	@Override
	public Offers getOffer(Integer branchId) {
	
		
		Query query = entityManager.createQuery("SELECT o FROM  Offers o where o.branch.id = " + branchId);
		
		return  (Offers) query.getSingleResult();
	}

	
}
