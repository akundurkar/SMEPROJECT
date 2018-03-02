/**
 * 
 */
package in.adcast.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;
import in.adcast.dao.RoleDao;
import in.adcast.model.Role;



/**
 * @author Mahendra
 *
 */
@SuppressWarnings("serial")
@Repository
public class RoleDaoImpl extends GenericDAOImpl<Role,Integer> implements RoleDao {

	@Override
	public List<Role> findAll() {
		//get the query here
		
		Query query = entityManager.createQuery("SELECT e FROM Role e");
	    return (List<Role>) query.getResultList();
		
	}



}
