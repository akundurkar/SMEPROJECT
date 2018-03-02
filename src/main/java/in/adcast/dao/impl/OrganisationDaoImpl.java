/**
 * 
 */
package in.adcast.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;
import in.adcast.dao.OrganisationDao;
import in.adcast.model.Organisation;




/**
 * @author Mahendra
 *
 */
@SuppressWarnings("serial")
@Repository
public class OrganisationDaoImpl extends GenericDAOImpl<Organisation,Integer> implements OrganisationDao {

	@Override
	public List<Organisation> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
