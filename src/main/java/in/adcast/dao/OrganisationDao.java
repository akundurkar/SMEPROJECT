package in.adcast.dao;


import java.util.List;
import in.adcast.common.GenericDAO;
import in.adcast.model.Organisation;


public interface OrganisationDao extends GenericDAO<Organisation, Integer> {

	List<Organisation> findAll();
	
}
