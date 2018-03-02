package in.adcast.dao;


import java.util.List;
import in.adcast.common.GenericDAO;
import in.adcast.exception.AuthenticationFailedException;
import in.adcast.model.ApplicationUser;


public interface UserDao extends GenericDAO<ApplicationUser, Integer> {

	List<ApplicationUser> findAll();
	
	ApplicationUser authenticateUser(String email,String password) throws AuthenticationFailedException;

	boolean alreadyExists(ApplicationUser user);

	boolean confirmUserRegistration(ApplicationUser user);

	ApplicationUser checkAccountExistance(String email);

	ApplicationUser findByUUID(String userId);
	
	public List<ApplicationUser> findAllbyAccountRole(int acc_type,boolean isIndividual);

	boolean confirmResetpassword(ApplicationUser user);

	List<ApplicationUser> findAllStaffDetails(int id);
	
}
