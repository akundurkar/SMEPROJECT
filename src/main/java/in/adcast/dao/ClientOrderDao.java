package in.adcast.dao;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import in.adcast.common.GenericDAO;
import in.adcast.model.ClientOrder;


public interface ClientOrderDao extends GenericDAO<ClientOrder, Integer> {

	public List<ClientOrder> findAllUpcomingOrders(Integer orgId);
 	
	public List<ClientOrder> getOrderStatusDetails(Integer organizationId,Integer branchId);

	public List<ClientOrder> getDashBoardDetails(Integer branchId);
	
	public List<ClientOrder> getDateWiseOrder(Date filterDate, Integer branchId);
	
	public List<ClientOrder> getWeekWiseOrder(Date filterDate, Integer branchId);
	
	public List<ClientOrder> getMonthWiseOrder(Integer branchId);

	public Long getOrderCountforClient(Integer clientId);

	public Long getOrderCountforClient(Integer clientId, Integer orderStatus );

	public BigDecimal totalSalePerClient(Integer clientId);

	public BigDecimal amountPaidPerClient(Integer clientId);

	public List<ClientOrder> getAppointmentHistoryForClient(Integer clientId);

	public List<ClientOrder> getUpcomingAppointmentForClient(Integer clientId);

}
