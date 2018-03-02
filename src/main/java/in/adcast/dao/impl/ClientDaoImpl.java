package in.adcast.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;
import in.adcast.dao.ClientDao;
import in.adcast.model.Branch;
import in.adcast.model.Client;

@SuppressWarnings("serial")
@Repository
public class ClientDaoImpl extends GenericDAOImpl<Client,Integer>  implements ClientDao{

	@Override
	public List<Branch> getAllClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> findAll(Integer organizationId) {
		Query query = entityManager.createQuery("SELECT c FROM Client c where c.organisation.id = " + organizationId);
    return (List<Client>) query.getResultList();
	}

	
	public boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}  
	
	@Override
	public List<Client> getClientByMobileOrName(Integer organizationId,String queri) {
		
		List<Client> clientList =null;
		
		if (isNumeric(queri)){
			clientList = entityManager.createQuery(
				    "select c " +
				    "from Client c " +
				    "where c.organisation.id = "+organizationId+" AND c.mobile like '"+queri+"%'", Client.class )
				.getResultList();
		}
		else {
			clientList = entityManager.createQuery(
				    "select c " +
				    "from Client c " +
				    "where c.organisation.id = "+organizationId+" AND c.firstName like '"+queri+"%'", Client.class )
				.getResultList();
			
		}
		
		return clientList;
		
	}

	@Override
	public List<Client> getClientHaveingDOB(Integer organizationId,String date) {
      
		 List<Client> clientList =null;
		
			clientList = entityManager.createQuery(
				    "select c " +
				    "from Client c " +
				    "where c.organisation.id = "+organizationId+" AND c.dob like '"+date+"%'", Client.class )
				.getResultList();
	
		return clientList;
	}

	@Override
	public List<Client> getClientByGender(Integer organizationId,char gender) {
		
		List<Client> clientList =null;
				
			clientList = entityManager.createQuery(
				    "select c " +
				    "from Client c " +
				    "where c.organisation.id = "+organizationId+" AND c.gender like '"+gender+"%'", Client.class )
				.getResultList();
	
		return clientList;
	}

	@Override
	public List<Client> getClientByAge(Integer organizationId,int age) {
		List<Client> clientList =null;
		
		clientList = entityManager.createQuery(
			    "select c " +
			    "from Client c " +
			    "where c.age like '"+age+"%'", Client.class )
			.getResultList();

	return clientList;
	}

	@Override
	public Client getClientByMobileAndName(Integer organizationId,String mobile, String clientFirstName, String clientLastName) {
		
		Client client =null;
		
		
			client = entityManager.createQuery(
				    "select c " +
				    "from Client c " +
				    "where c.organisation.id = "+organizationId+" AND c.mobile like '"+mobile.trim()+"%'"+
				    " and c.firstName ='"+clientFirstName+
				    "' and c.lastName ='"+clientLastName+"'", Client.class )
				.getSingleResult();		
		return client;		
	}

	@Override
	public List<Client> getClientNoShow(Integer organizationId) {
		// TODO Auto-generated method stub
		return null;
	}

}
