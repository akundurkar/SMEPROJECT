package in.adcast.dao;


import java.util.List;
import in.adcast.common.GenericDAO;
import in.adcast.dto.OfferTypeDto;
import in.adcast.model.OfferType;


public interface OfferTypeDao extends GenericDAO<OfferType, Integer> {

	public void test();

	public List<OfferType> searcEventNameList(Integer organisationId);

	public List<OfferType> searcFestivalNameList(Integer organisationId);
	
	public boolean saveOfferTypeDetails(OfferTypeDto offerTypeDto);

}
