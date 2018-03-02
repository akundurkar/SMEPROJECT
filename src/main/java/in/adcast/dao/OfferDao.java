package in.adcast.dao;


import java.util.List;
import in.adcast.common.GenericDAO;
import in.adcast.model.Offers;


public interface OfferDao  extends GenericDAO<Offers, Integer>{
	
	public List<Offers> getOffersByBranch(Integer branchId);

	public List<Offers> findActiveOffers();

	public List<Offers> findBirthdayOffers();

	public Offers getOffer(Integer branchId);

}
