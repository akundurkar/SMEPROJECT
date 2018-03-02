package in.adcast.dao;


import java.util.List;
import in.adcast.common.GenericDAO;
import in.adcast.model.ApplicationUser;
import in.adcast.model.BranchSchedule;
import in.adcast.model.ClientOrder;
import in.adcast.model.ServiceMasterdata;
import in.adcast.model.ServiceOffered;
import in.adcast.model.StaffSchedule;


public interface ServiceMasterDataDao extends GenericDAO<ServiceMasterdata, Integer>
{
	public List<ServiceMasterdata> findAll();

	public List<ServiceOffered> searchServiceNameList(Integer organisationId);
	
	public List<ServiceMasterdata> listAllServicesByOrganisationId(Integer organisationId);

	public List<ApplicationUser> searchStaffNameList(Integer organisationId);

	public List<ServiceMasterdata> getAllServiceDetails(Integer organisationId);

	public List<BranchSchedule> getBranchScheduleDetails(Integer id);

	public ClientOrder searchLastBookingDetails(Integer clientId);

	public List<StaffSchedule> getStaffScheduleDetails(Integer staffId);
	
}