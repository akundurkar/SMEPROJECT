/**
 * 
 */
package in.adcast.dao.impl;



import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;
import in.adcast.common.utils.SMEUtils;
import in.adcast.dao.SubOrderDao;
import in.adcast.model.SubOrder;




/**
 * @author Mahendra
 *
 */
@SuppressWarnings("serial")
@Repository
public class SubOrderDAOImpl extends GenericDAOImpl<SubOrder,Integer> implements SubOrderDao {

	@Override
	public void test() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SubOrder> getTopServices(Integer branchId) {
		
		/*Date monthDate[];
		monthDate = SMEUtils.getMonthDates();*/
		
		/*Query query = entityManager.createQuery("SELECT COUNT(service_id) AS service_count,service_id "

												+ " FROM sub_order "
										
												+ "WHERE service_id IN (SELECT id "
														     
														     + "FROM service_offered "
														     
														     + " WHERE branch_id = :branchId "
														     + " AND   order_id IN (SELECT id "
																	 +"FROM client_order "
																	 +"WHERE created_on "
																	 + " BETWEEN :monthStartDate "
																	 + "AND "
																	 + ":monthEndDate "
																	+")"
																		
																	
														    +" ) "
												+"GROUP BY service_id "
												+"ORDER BY service_count DESC LIMIT 5"
												);*/
		
		/*above query in single line*/
		
		/*Query query = entityManager.createQuery(" SELECT COUNT(so.service_id) AS service_count,so.service_id FROM sub_order so  WHERE so.service_id IN (SELECT se.id  FROM service_offered se WHERE se.branch_id = 1 AND   se.order_id IN (SELECT co.id FROM client_order co WHERE created_on BETWEEN  :monthStartDate AND :monthEndDate ) ) GROUP BY so.service_id ORDER BY service_count DESC LIMIT 5");*/

		
		Query query = entityManager.createQuery(" SELECT COUNT(subo.serviceOffered.id) AS serviceCount,subo.serviceOffered.id "

							+" FROM SubOrder subo "
					
							+" WHERE subo.serviceOffered.id IN (SELECT seof.id "
									     
											     + "FROM ServiceOffered seof "
											     
											     + " WHERE seof.branch.id = :branchId "
											     				
											     + " ) "
											      
							+" GROUP BY subo.serviceOffered.id "
									      
							+" ORDER BY serviceCount DESC  "
												);
		
		
		
		/*String topService =  " SELECT COUNT(subo.serviceOffered.id) AS serviceCount,subo.serviceOffered.id "

							+" FROM SubOrder subo "
					
							+" WHERE subo.serviceOffered.id IN (SELECT seof.id "
									     
											     + "FROM ServiceOffered seof "
											     
											     + " WHERE seof.branch.id = :branchId "
											     				
											     + " ) "
											      
							+" GROUP BY subo.serviceOffered.id "
									      
							+" ORDER BY serviceCount DESC  ";
											
		
		Query query =entityManager.createQuery(topService);*/
		
		
		/*query.setParameter("monthStartDate", monthDate[0]);
		query.setParameter("monthEndDate", monthDate[1]);*/
		query.setParameter("branchId", branchId);
		return (List<SubOrder>) query.getResultList();
	
		
	}

	@Override
	public List<SubOrder> getTopStaff(Integer organisationId) {
		
		Date monthDate[];
		monthDate = SMEUtils.getMonthDates();
		
		String topStaff =  " SELECT COUNT(service_cost) AS total_revenue,staff_id  "

							+" FROM sub_order "
					
							+" WHERE staff_id IN (SELECT id "
									     
											     + "FROM application_user "
											     
											     + " WHERE organisation_id = :organisationId "
											     
											     + " AND   order_id IN (SELECT id "
											     
																	 +" FROM client_order "
											     
																	 +" WHERE created_on "
																	 
																	 +" BETWEEN :monthStartDate "
																	 +" AND "
																	 +" :monthEndDate )"
																	
											      + " ) "
											      
							+" GROUP BY staff_id "
									      
							+" ORDER BY total_revenue DESC LIMIT 5 ) ";
											
		
		Query query =entityManager.createQuery(topStaff);
		
		query.setParameter("monthStartDate", monthDate[0]);
		query.setParameter("monthEndDate", monthDate[1]);
		query.setParameter("organisationId", organisationId);
		return (List<SubOrder>) query.getResultList();
		
	}

	
		
}
