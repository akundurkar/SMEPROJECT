package in.adcast.dao;


import java.util.List;
import in.adcast.common.GenericDAO;
import in.adcast.dto.TaxDto;
import in.adcast.model.TaxMasterdata;


public interface TaxDetailsMasterdatadao extends GenericDAO<TaxMasterdata, Integer> {
	
	public boolean saveTaxDetails(TaxDto taxDto);

	public List<TaxMasterdata> findAllTaxDetails(Integer id);
	
}
