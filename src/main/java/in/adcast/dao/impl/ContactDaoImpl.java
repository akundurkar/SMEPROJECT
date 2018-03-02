package in.adcast.dao.impl;

import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;
import in.adcast.dao.ContactDao;
import in.adcast.model.Contact;

@SuppressWarnings("serial")
@Repository
public class ContactDaoImpl extends GenericDAOImpl<Contact,Integer>  implements ContactDao{

	
}
