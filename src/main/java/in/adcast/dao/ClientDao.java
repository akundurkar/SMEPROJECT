package in.adcast.dao;


import java.util.List;
import in.adcast.common.GenericDAO;
import in.adcast.model.Branch;
import in.adcast.model.Client;

public interface ClientDao extends GenericDAO<Client, Integer> 
{

	public List<Branch> getAllClient();

	public List<Client> findAll(Integer organizationId);

	public List<Client> getClientByMobileOrName(Integer organizationId,String queri);

	public List<Client> getClientHaveingDOB(Integer organizationId,String date);

	public List<Client> getClientByGender(Integer organizationId,char gender);

	public List<Client> getClientByAge(Integer organizationId,int age);

	public Client getClientByMobileAndName(Integer organizationId,String mobile, String clientFirstName, String clientLastName);

	public List<Client> getClientNoShow(Integer organizationId);

}
