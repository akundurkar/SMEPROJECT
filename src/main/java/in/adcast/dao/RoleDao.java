package in.adcast.dao;


import java.util.List;
import in.adcast.common.GenericDAO;
import in.adcast.model.Role;


public interface RoleDao extends GenericDAO<Role, Integer> {

	List<Role> findAll();
	
}
