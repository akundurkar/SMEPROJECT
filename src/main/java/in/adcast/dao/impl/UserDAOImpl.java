/**
 * 
 */
package in.adcast.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;
import in.adcast.common.utils.AppConstant.Account;
import in.adcast.dao.UserDao;
import in.adcast.exception.AuthenticationFailedException;
import in.adcast.model.ApplicationUser;



/**
 * @author Mahendra
 *
 */
@SuppressWarnings("serial")
@Repository
public class UserDAOImpl extends GenericDAOImpl<ApplicationUser,Integer> implements UserDao {

	
	@Override
	public List<ApplicationUser> findAll() {
		//get the query here
		
		Query query = entityManager.createQuery("SELECT e FROM ApplicationUser e");
	    return (List<ApplicationUser>) query.getResultList();
		
	}

	@Override
	public ApplicationUser authenticateUser(String loginId,String password) throws AuthenticationFailedException{
		ApplicationUser user = null;
		try{
		Query query = entityManager.createQuery("SELECT e FROM ApplicationUser e where e.loginId  = :loginId AND e.password  = :password AND e.userStatus = :userStatus");
		query.setParameter("loginId", loginId);
		query.setParameter("password", password);
		query.setParameter("userStatus", Account.ACTIVE.getValue());
		user = (ApplicationUser) query.getSingleResult();
		}
		catch(Exception e){
			e.printStackTrace();
			throw new AuthenticationFailedException("Authentication faileds");
		}
		
		return user;
	}

	@Override
	public boolean alreadyExists(ApplicationUser user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean confirmUserRegistration(ApplicationUser user) {
		// TODO Auto-generated method stub
		boolean emailconfirmed = false;
		//Query query = entityManager.createQuery("SELECT e FROM User e where e.emailId = '"+ user.getEmailId()+"' AND e.confirmationToken = '"+user.getConfirmationToken()+"'");
		
		Query query = entityManager.createQuery("SELECT e FROM ApplicationUser e where e.confirmationToken = '"+user.getConfirmationToken()+"'");
		ApplicationUser userFromDB = (ApplicationUser)query.getSingleResult();
		if(userFromDB!=null)
		{
			userFromDB.setConfirmationToken("");
			userFromDB.setUserStatus(Account.ACTIVE.getValue());
			update(userFromDB);
			emailconfirmed = true;
		}
		return emailconfirmed;
	}

	@Override
	public ApplicationUser checkAccountExistance(String loginId) {
		ApplicationUser userFromDB = null;
		Query query = entityManager.createQuery("SELECT e FROM ApplicationUser e where e.loginId  = :loginId");
		query.setParameter("loginId", loginId);
		try{
			userFromDB = (ApplicationUser) query.getSingleResult();
		}
		catch(Exception e)
		{
			System.out.println("Account does not exists with this "+loginId);
			e.printStackTrace();
		}
		return userFromDB; 
	}

	@Override
	public ApplicationUser findByUUID(String userId) {
		ApplicationUser userFromDB = null;
		Query query = entityManager.createQuery("SELECT e FROM ApplicationUser e where e.uniqueId  = :uniqueId");
		query.setParameter("uniqueId", userId);
		userFromDB = (ApplicationUser) query.getSingleResult();
		
		return userFromDB;
	}


	@Override
	public List<ApplicationUser> findAllbyAccountRole(int acc_type,boolean isIndividual) {
		return null;/*
		String queryString = null;
		if(acc_type == AccountRole.ADMIN_ADVTERSISER.getValue()){
			if(isIndividual)
				queryString = "SELECT * FROM application_user a,user_has_role u WHERE a.id = u.user_id AND u.role_id = :types AND a.individual = 1";
			else
				queryString = "SELECT * FROM application_user a,user_has_role u WHERE a.id = u.user_id AND u.role_id = :types AND a.individual = 0";
		}
		else
		{
			queryString = "SELECT * FROM application_user a,user_has_role u WHERE a.id = u.user_id AND u.role_id = :types";
		}
		Query query = entityManager.createNativeQuery(queryString,ApplicationUser.class);
    query.setParameter("types",acc_type);
    List<ApplicationUser> appUserList = (List<ApplicationUser>)query.getResultList();
    List<Object[]> objList=query.getResultList();
	   for(Object[] obj:objList){
		   ApplicationUser appUser =new ApplicationUser();
		  
		   appUser.setId();
		   documents.setDocumentType((Integer)obj[2]);
		  documentsList.add(documents);
	   }
    
    
    
		return appUserList ; 
	*/}

	@Override
	public boolean confirmResetpassword(ApplicationUser user) {
		boolean resetPasswordConfirmed = false;
		//Query query = entityManager.createQuery("SELECT e FROM User e where e.emailId = '"+ user.getEmailId()+"' AND e.confirmationToken = '"+user.getConfirmationToken()+"'");
		
		Query query = entityManager.createQuery("SELECT e FROM ApplicationUser e where e.resetPassword = '"+user.getResetPassword()+"'");
		ApplicationUser userFromDB = (ApplicationUser)query.getSingleResult();
		if(userFromDB!=null)
		{
			//userFromDB.setResetPassword("");			
			//update(userFromDB);
			resetPasswordConfirmed = true;
		}
		return resetPasswordConfirmed;

	}

	@Override
	public List<ApplicationUser> findAllStaffDetails(int orgid) {
		String queryString = null;
			queryString = "SELECT * FROM application_user a,user_has_role u WHERE a.organisation.id = :orgid";
		
			Query query = entityManager.createNativeQuery(queryString,ApplicationUser.class);
			query.setParameter("orgid",orgid);
    List<ApplicationUser> appUserList = (List<ApplicationUser>)query.getResultList();
    return appUserList ;
		
	}
	
}
