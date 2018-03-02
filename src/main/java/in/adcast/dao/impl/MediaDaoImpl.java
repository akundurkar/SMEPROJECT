package in.adcast.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;
import in.adcast.dao.MediaDao;
import in.adcast.dao.ClientOrderDao;
import in.adcast.model.Branch;
import in.adcast.model.Media;


@Repository
public class MediaDaoImpl  extends GenericDAOImpl<Media,Integer> implements MediaDao{

	private static final Log log = LogFactory.getLog(MediaDao.class);
	
	@Autowired
	ClientOrderDao orderDao;

	public MediaDaoImpl() {	
		
		
	}
	
	
	public String upload(Media media) {
	
		log.debug("upload() ..... Start");
		
		try {
			
			create(media);
			log.debug("upload() ..... End");
			return ""+media.getId();
			
		} catch (RuntimeException re) {
			throw re;
		}
	}


	@Override
	public List<Media> getImageForGallery(Integer organizationId) {
		
		   Query query = entityManager.createQuery("SELECT b FROM Media b where b.organisation.id = " + organizationId);
		   
		   return (List<Media>) query.getResultList();
		
	}


	
}	