package in.jquery.dao;

import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import in.adcast.dao.OrganisationDao;
import in.adcast.dao.RoleDao;
import in.adcast.dao.UserDao;
import in.adcast.model.ApplicationUser;
import in.adcast.model.Organisation;
import in.adcast.model.Role;
import in.adcast.schedular.SendEmail;


@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:showad-config-test.xml" } )
@Transactional
@Rollback(false)
@TestExecutionListeners({ TransactionalTestExecutionListener.class,DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection={"dataSourceTest"})

public class UserDaoImplTest {
	
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
	
	@Autowired
	private OrganisationDao organisationDao;
	
	@Autowired
    private SendEmail sendEmail;
	
	//@Autowired
	//private OrganisationTypeDao organisationTypeDao;
	
	//@Autowired
	//private UserStatusDao userStatusDao;
	
	
	@Test
	//@Ignore
	//@DatabaseSetup("classpath:Snippet.xml")
	public void testSendEmail() {
		
		//sendEmail.sendImpressionCountEmail();
		sendEmail.offerEmail();
		
	}
	
	@Test
	@Ignore
	public void testInsert() {
		/*
		User user1 = prepareUser();
				
		User user = userDao.create(user1);		
				
		Assert.assertEquals(1L,userDao.findAll().size());
		
		Assert.assertEquals(2L,user.getRoles().size());*/
	}
	

/*	private User prepareUser() {
		
		//RefOrganisationType refOrganisationType = organisationTypeDao.findById(new Integer(1));
		
		//RefUserstatus refUserstatus = userStatusDao.findById(1);;
		
		Role role1 = roleDao.findById(1);
		Role role2 = roleDao.findById(2);
		
		Organisation organisation = new Organisation();
		organisation.setOrganisationName("Orgnisation Name");
		
		organisation.setAdress1("add");
		
		organisation.setPhone("12345");
		
		organisation.setCity("city");
		
		//organisation.setRefOrganisationType(refOrganisationType);
		
		organisationDao.create(organisation);
				
		User user1 = new User();
		user1.setEmailId("anup.mishra@arms.de");
		
		user1.setFirstName("First Name");
		user1.setMobile("123456");
		user1.setPassword("123456");
		
		//user1.setRefUserstatus(refUserstatus);
		user1.setOrganisation(organisation);
		
		user1.getRoles().add(role1);
		user1.getRoles().add(role2);
		return user1;
	}*/

	//@Test
	@Ignore
	public void testGetAll() {
		Assert.assertEquals(0L, userDao.findAll().size());
	}
	
	@Test
	@Ignore
	public void testGetByIdAndRoles() {
	
	/*	User user1 = prepareUser();
		userDao.create(user1);
	
		List<User> pList = userDao.findAll();
		User User = pList.get(0);
		
		User user2 = userDao.findById(User.getId());
		
		Assert.assertEquals(2, user2.getRoles().size());*/
		
		//Assert.assertEquals("Spring in Action Book", user2.getEmail());
	}
	
	//@Test
	@Ignore
	public void testDelete() {
		ApplicationUser user1 = new ApplicationUser();
		user1.setEmailId("anup.mishar@arms.de");
		userDao.create(user1);
		
		ApplicationUser hProduct = new ApplicationUser();
		hProduct.setEmailId("anup.mishara@arms.de");
		userDao.create(hProduct);
	
		Assert.assertEquals(2L, userDao.findAll().size());
		
		userDao.delete(hProduct);
		
		Assert.assertEquals(1L, userDao.findAll().size());
	}
	//@Test
	@Ignore
	public void testUpdate() {
		
		ApplicationUser user1 = new ApplicationUser();
		user1.setEmailId("anup.mishra@arms.de");
		userDao.create(user1);
		
		Assert.assertEquals(1L, userDao.findAll().size());

		List<ApplicationUser> pList = userDao.findAll();
		ApplicationUser User = pList.get(0);
		User.setEmailId("anup.mishra@arms.de");
		
		userDao.update(User);
		
		List<ApplicationUser> pList2 = userDao.findAll();
		ApplicationUser user2 = pList2.get(0);
		//Assert.assertEquals("Head First Design Patterns", user2.getEmailId());
		Assert.assertEquals("Head First Design Patterns", user2.getLoginId());
	}
	@Test
	@Ignore
	public void uniqueId() {
		 UUID uniqueKey = UUID.randomUUID();
		    System.out.println ("unique"+uniqueKey);
	}
}
