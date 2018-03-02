package in.adcast.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import in.adcast.common.Constants;
import in.adcast.common.ImageToThumbnail;

import in.adcast.dao.MediaDao;
import in.adcast.dao.OrganisationDao;
import in.adcast.dao.UserDao;

import in.adcast.dto.MediaDto;

import in.adcast.exception.CustomRuntimeException;
import in.adcast.exception.TechnicalException;

import in.adcast.mapper.MediaMapper;

import in.adcast.model.ApplicationUser;
import in.adcast.model.Media;
import in.adcast.model.Organisation;

import in.adcast.services.MediaService;

@Service
@Transactional
public class MediaServiceImpl implements MediaService{
	
    private static final Logger LOGGER = Logger.getLogger(MediaServiceImpl.class);
	
	@Autowired
	private MediaDao mediaDao;
	
	@Autowired
	private OrganisationDao organisationDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MediaMapper mediaMapper;

	public String upload(MultipartHttpServletRequest request, HttpServletResponse response) throws TechnicalException,Exception{
		LOGGER.info("upload() ..... Start");
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		Media media = null;
		String id = null;
		String userId = null;
		
		String type=Constants.IMAGES.toString();
		
		userId = request.getParameter("userId").toString();
		
		ApplicationUser applicationUser = userDao.findByUUID(userId);
		
        if(request.getParameter("imageType")!=null)
        {
        	type=request.getParameter("imageType").toString();
        }
        
		 while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			try{
				 byte[] originalMedia=null;
				 byte[] thumbnailByteArray=null;
				if(type.equals(Constants.IMAGES.toString())){
					originalMedia=mpf.getBytes();
					thumbnailByteArray= ImageToThumbnail.getThumbnailFromImage(originalMedia, 100, 100,"png");  
				}else{
					originalMedia=mpf.getBytes();
					thumbnailByteArray= ImageToThumbnail.getThumbnailFromImage(originalMedia, 100, 100,"png");
				}
					media = new Media();
					media.setImageType(type.charAt(0));
					media.setFile(thumbnailByteArray);
					media.setOriginalMedia(originalMedia);
					media.setFileName(mpf.getOriginalFilename());
					media.setFileType(mpf.getContentType());
					Organisation organisation=organisationDao.findById(applicationUser.getOrganisation().getId());
					media.setOrganisation(organisation);
					id = mediaDao.upload(media);
          }catch (RuntimeException re) {
  			re.printStackTrace();
        	  throw re;
  		 }
		}
		 LOGGER.info("upload() ..... End");
		 return id;
	}
    
	public List<MediaDto> getImageForGallery(String userId) {
        LOGGER.info("getImageForGallery(String userId) -------------Start ");
		
        List<MediaDto> mediaDtos=new ArrayList<MediaDto>();
    	try {
    		ApplicationUser applicationUser = userDao.findByUUID(userId);
    		List<Media> mediaList= mediaDao.getImageForGallery(applicationUser.getOrganisation().getId());
       
	    		if(null!=mediaList && mediaList.size()>0){					
						for(Media media:mediaList){
							MediaDto mediaDto = mediaMapper.prepareDto(media);
							//BeanUtils.copyProperties(media, mediaDto);
							mediaDtos.add(mediaDto);
						}
	    		}
	    		else{
	    			MediaDto defaultDto = new MediaDto();
	    			defaultDto.setImageId(0);	   //change after change mediaDto 16-08-2017 	
	    			mediaDtos.add(defaultDto);
	    		}
	    	
		} catch (HibernateException e) {
			LOGGER.error("getImageForGallery(String userId)", e);
			e.printStackTrace();
			 throw new CustomRuntimeException("HibernateException EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("getImageForGallery(String userId) -------------  End ");
		return mediaDtos;
	}

	@Override
	public byte[] getMediaById(int imageId, boolean isOriginal) {
		LOGGER.info("getMediaById(int imageId, boolean isOriginal) ..... start");
		try {
			Media media = mediaDao.findById(imageId);

			if (null != media) {
				if(!isOriginal){
		
					return media.getFile();
				}else{
					return media.getOriginalMedia();
				}
			} else {
				return null;
			}
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		}finally{
			LOGGER.info("getMediaById(int imageId, boolean isOriginal) ..... end");
		}
		
	}

	@Override
	public Boolean deleteImage(Integer imageId) {
	
		LOGGER.info("deleteImage(Integer imageId) -----------start");
		
		boolean success = false;
		
		System.out.println("in Mediaservice implementation "+imageId);
		
		Media media = mediaDao.findById(imageId);
		
		try{
			
			mediaDao.delete(media);
			
			success = true;
			
		}catch(HibernateException e){
			
	      LOGGER.error("deleteImage(Integer imageId)"+e);
	      
	      throw new CustomRuntimeException("Hibernate Exeception", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	      
		}
		
		LOGGER.info("deleteImage(Integer imageId) -----------end");
		
		return success;
	}
	
}