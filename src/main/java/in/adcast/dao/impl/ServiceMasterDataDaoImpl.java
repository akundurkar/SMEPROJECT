package in.adcast.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import in.adcast.common.GenericDAOImpl;
import in.adcast.dao.ServiceMasterDataDao;
import in.adcast.model.ApplicationUser;
import in.adcast.model.BranchSchedule;
import in.adcast.model.ClientOrder;
import in.adcast.model.ServiceMasterdata;
import in.adcast.model.ServiceOffered;
import in.adcast.model.StaffSchedule;

@SuppressWarnings("serial")
@Repository
public class ServiceMasterDataDaoImpl extends GenericDAOImpl<ServiceMasterdata,Integer> implements ServiceMasterDataDao {

	
	@Override
	public List<ServiceMasterdata> findAll() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ServiceOffered> searchServiceNameList(Integer organisationId) {
		
		
		Query query = entityManager.createQuery("SELECT so FROM ServiceOffered so WHERE so.serviceMasterdata.id IN (select sm.id from ServiceMasterdata sm where sm.organisation.id=:organisationId )");
		
		query.setParameter("organisationId", organisationId);
		return (List<ServiceOffered>) query.getResultList();
	}

	
	@Override
	public List<ServiceMasterdata> listAllServicesByOrganisationId(Integer organisationId) 
	{
		Query query = entityManager.createQuery("SELECT s FROM ServiceMasterdata s where s.organisation.id=:organisationId");
		query.setParameter("organisationId", organisationId);
		return (List<ServiceMasterdata>)query.getResultList();
	}

	@Override
	public List<ApplicationUser> searchStaffNameList(Integer organisationId) 
	{
		Query query = entityManager.createQuery("SELECT au FROM ApplicationUser au WHERE au.organisation.id=:organisationId");
		query.setParameter("organisationId", organisationId);
		return (List<ApplicationUser>)query.getResultList();
	}

	@Override
	public List<ServiceMasterdata> getAllServiceDetails(Integer organisationId) {
		Query query = entityManager.createQuery("SELECT sm FROM ServiceMasterdata sm where sm.organisation.id =:organisationId");
		query.setParameter("organisationId", organisationId);
	    return (List<ServiceMasterdata>) query.getResultList();
	}

	@Override
	public List<BranchSchedule> getBranchScheduleDetails(Integer id) {
		Query query = entityManager.createQuery("SELECT bs FROM BranchSchedule bs where bs.branch.id =:id");
		query.setParameter("id", id);
	    return (List<BranchSchedule>) query.getResultList();
	}

	@Override
	public ClientOrder searchLastBookingDetails(Integer clientId) {
		Query query =entityManager.createQuery("SELECT o FROM ClientOrder o where o.client.id =:clientId ORDER BY o.lastUpdated DESC");
		query.setParameter("clientId", clientId);		
		query.setMaxResults(1);
		return (ClientOrder) query.getSingleResult();
	}

	@Override
	public List<StaffSchedule> getStaffScheduleDetails(Integer staffId) {
		Query query = entityManager.createQuery("SELECT ss FROM StaffSchedule ss where ss.applicationUser.id =:staffId");
		query.setParameter("staffId", staffId);
	    return (List<StaffSchedule>) query.getResultList();
	}

	

	
	
}
