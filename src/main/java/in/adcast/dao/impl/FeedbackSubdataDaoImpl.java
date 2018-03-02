/**
 * 
 */
package in.adcast.dao.impl;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;

import in.adcast.dao.FeedbackSubdataDao;
import in.adcast.model.FeedbackSubdata;







/**
 * @author Mahendra
 *
 */
@SuppressWarnings("serial")
@Repository
public class FeedbackSubdataDaoImpl extends GenericDAOImpl<FeedbackSubdata,Integer> implements FeedbackSubdataDao {

	@Override
	public List<FeedbackSubdata> findAll(Integer branchId) {
		Query query = entityManager.createQuery("SELECT fbsd FROM FeedbackSubdata fbsd where fbsd.branch.id = " + branchId);
	    return (List<FeedbackSubdata>) query.getResultList();
	}

	

	

	
}
