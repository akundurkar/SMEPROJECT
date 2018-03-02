/**
 * 
 */
package in.adcast.dao.impl;



import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;
import in.adcast.common.utils.AppConstant;
import in.adcast.common.utils.SMEUtils;
import in.adcast.dao.ClientOrderDao;
import in.adcast.model.ClientOrder;







/**
 * @author Mahendra
 *
 */
@SuppressWarnings("serial")
@Repository
public class ClientOrderImpl extends GenericDAOImpl<ClientOrder,Integer> implements ClientOrderDao {

	@Override
	public List<ClientOrder> findAllUpcomingOrders(Integer orgId) {
		Query query = entityManager.createQuery("SELECT o FROM ClientOrder o WHERE o.orderStatus =:orderStatus AND o.createdOn >= CURDATE() AND "+
				"o.client.id IN (select c.id from Client c where c.organisation.id=:organisationId )");
		
		query.setParameter("organisationId", orgId);
		query.setParameter("orderStatus", AppConstant.OrderStatus.CONFIRMED.getValue());
		return (List<ClientOrder>) query.getResultList();
	}

	@Override
	public List<ClientOrder> getOrderStatusDetails(Integer organizationId,Integer branchId) {
				Query query = entityManager.createQuery("SELECT o FROM ClientOrder o WHERE o.client.id in (select c.id from Client c where c.organisation.id=:organisationId ) AND o.orderStatus=:orderStatus AND o.branch.id =:branchId");
				
				query.setParameter("organisationId", organizationId);
				query.setParameter("orderStatus",AppConstant.OrderStatus.BOOKED.getValue());
				query.setParameter("branchId", branchId);
				return (List<ClientOrder>) query.getResultList();
	}

	@Override
	public List<ClientOrder> getDashBoardDetails(Integer branchId) {
				Query query = entityManager.createQuery("SELECT o FROM ClientOrder o WHERE o.branch.id =:branchId AND o.createdOn >= CURDATE()");
				query.setParameter("branchId", branchId);
				return (List<ClientOrder>) query.getResultList();
	}
	

	@Override
	public List<ClientOrder> getDateWiseOrder(Date filterDate, Integer branchId) {
		       
		        Date dateBefore = SMEUtils.dateAfterInputDate(0,filterDate);
		        Date dateAfter =SMEUtils.dateAfterInputDate(1,filterDate);
		
				Query query = entityManager.createQuery("SELECT o FROM ClientOrder o WHERE o.branch.id =:branchId AND o.createdOn between :dateBefore and :dateAfter");
			
				query.setParameter("dateBefore", dateBefore);
				query.setParameter("dateAfter", dateAfter);
				query.setParameter("branchId", branchId);
				return (List<ClientOrder>) query.getResultList();
	}
	
	// not working query 
	@Override
	public List<ClientOrder> getWeekWiseOrder(Date filterDate, Integer branchId) {
		
				Query query = entityManager.createQuery("SELECT co FROM ClientOrder co WHERE co.branch.id =:branchId AND co.createdOn between :weekStartDate and :weekEndDate");
			
				query.setParameter("branchId", branchId);
				return (List<ClientOrder>) query.getResultList();
	}
	
	@Override
	public List<ClientOrder> getMonthWiseOrder(Integer branchId) {
		
				Date monthDate[];
				monthDate = SMEUtils.getMonthDates();
				
				Query query = entityManager.createQuery("SELECT co FROM ClientOrder co WHERE co.branch.id =:branchId AND co.createdOn between :monthStartDate and :monthEndDate");
				
				query.setParameter("monthStartDate", monthDate[0]);
				query.setParameter("monthEndDate", monthDate[1]);
				query.setParameter("branchId", branchId);
				return (List<ClientOrder>) query.getResultList();
	}


	@Override
	public Long getOrderCountforClient(Integer clientId) {
		
				Query query = entityManager.createQuery("SELECT Count(o) FROM ClientOrder o WHERE o.client.id =:clientId");
			
				query.setParameter("clientId", clientId);
				
				return (Long) query.getSingleResult();
	}

	@Override
	public Long getOrderCountforClient(Integer clientId,Integer orderStatus ) {
		
				Query query = entityManager.createQuery("SELECT Count(o) FROM ClientOrder o WHERE o.client.id =:clientId AND o.orderStatus = :orderStatus");
			
				query.setParameter("clientId", clientId);
				query.setParameter("orderStatus", orderStatus);
				
				return (Long) query.getSingleResult();
	}

	@Override
	public BigDecimal totalSalePerClient(Integer clientId) {
		
				String total = "SELECT SUM(o.total) FROM ClientOrder o WHERE o.client.id =:clientId";
				
				Query query =entityManager.createQuery(total);
				
				query.setParameter("clientId", clientId);
				
				return (BigDecimal) query.getSingleResult();
	}

	@Override
	public BigDecimal amountPaidPerClient(Integer clientId) {
		
				String amountPaid = "SELECT SUM(o.amountPaid) FROM ClientOrder o WHERE o.client.id =:clientId";
				
				Query query =entityManager.createQuery(amountPaid);
				
				query.setParameter("clientId", clientId);
				
				return (BigDecimal) query.getSingleResult();
	}
	
	
	@Override
	public List<ClientOrder> getUpcomingAppointmentForClient(Integer clientId) {
		
				Query query =entityManager.createQuery("SELECT co FROM ClientOrder co WHERE co.client.id =:clientId  AND co.createdOn >= CURDATE()");
				
				query.setParameter("clientId", clientId);
				
				query.setMaxResults(10); //ToDo : change constant
				
				return (List<ClientOrder>) query.getResultList();
	}

	@Override
	public List<ClientOrder> getAppointmentHistoryForClient(Integer clientId) {
				
				Query query =entityManager.createQuery("SELECT o FROM ClientOrder o WHERE o.client.id =:clientId order by o.createdOn");
				
				query.setParameter("clientId", clientId);
				
				query.setMaxResults(10); //ToDo : change constant
				
				return (List<ClientOrder>) query.getResultList();
				
	}

	

	
}
