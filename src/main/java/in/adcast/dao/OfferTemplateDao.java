package in.adcast.dao;


import in.adcast.common.GenericDAO;
import in.adcast.model.OfferTemplate;


public interface OfferTemplateDao extends GenericDAO<OfferTemplate, Integer> {
	
	public OfferTemplate getOfferTemplate();	

}
