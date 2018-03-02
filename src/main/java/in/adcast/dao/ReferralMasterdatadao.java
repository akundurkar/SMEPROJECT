package in.adcast.dao;


import java.util.List;
import in.adcast.common.GenericDAO;
import in.adcast.dto.ReferralSourceDto;
import in.adcast.model.ReferralMasterdata;


public interface ReferralMasterdatadao extends GenericDAO<ReferralMasterdata, Integer> {
	
	public boolean savereferralResource(ReferralSourceDto ReferralSourceDto);

	public List<ReferralMasterdata> findAllReferralSourceDetails(Integer id);
	
}
