package in.adcast.dao.impl;

import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;
import in.adcast.dao.OfferTemplateDao;
import in.adcast.model.OfferTemplate;


@SuppressWarnings("serial")
@Repository
public class OfferTemplateDaoImpl extends GenericDAOImpl<OfferTemplate,Integer> implements OfferTemplateDao {

	@Override
	public OfferTemplate getOfferTemplate() {
		
		OfferTemplate offerTemplate=new OfferTemplate();
		// TODO Auto-generated method stub
		return offerTemplate;
	}

	
	
}
