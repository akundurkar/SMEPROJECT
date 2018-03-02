package in.adcast.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import in.adcast.common.utils.AppConstant;
import in.adcast.dto.MediaDto;

import in.adcast.exception.CustomRuntimeException;
import in.adcast.exception.ImageNotFoundException;
import in.adcast.exception.TechnicalException;

import in.adcast.services.MediaService;

@RestController
public class MediaController {
	
	private static final Logger LOGGER = Logger.getLogger(MediaController.class);
	
	@Autowired
	private MediaService mediaService;
	
	
	@RequestMapping(value="/rest/media/upload", method = RequestMethod.POST,headers = "content-type=multipart/*" )
	public @ResponseBody
	String upload(MultipartHttpServletRequest request, HttpServletResponse response) throws TechnicalException ,Exception{

		 LOGGER.info("upload() ..... Start");
		 
		 String id = null;
		 try{
		      id = mediaService.upload(request, response);
		      
		 }catch (Exception e){
			 
			 LOGGER.error("Error in uploading image ", e);
			 e.printStackTrace();
			 throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		LOGGER.info("upload() ..... End");
		return id;

	}
	
	@RequestMapping(value = "/rest/media/getImageForGallery", method =RequestMethod.GET)
	public @ResponseBody
	List<MediaDto> getImageForGallery(@RequestParam(required = false) String userId)
	{
		List<MediaDto> mediaDtoList = null;
		mediaDtoList = mediaService.getImageForGallery(userId);
		return mediaDtoList;
	}
	
	
	@RequestMapping(value = "/rest/media/get/{id}", method = RequestMethod.GET)
	public @ResponseBody 
	byte[] get(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) throws IOException,ImageNotFoundException {
		LOGGER.info("get() ..... Start");
		   try{	
			   
				 return mediaService.getMediaById(id,true);
				
	       }catch (Exception e) {
	    	   
				 LOGGER.error("Error in getting thumbnail ", e);
				 e.printStackTrace();
				 throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		   }
	}
	
	
	@RequestMapping(value= "/rest/media/deleteImage", method=RequestMethod.POST)
	public String deleteImage(@RequestBody MediaDto mediaDto) {
		
		Boolean success = mediaService.deleteImage(mediaDto.getImageId());
		
		if(success){
			return AppConstant.SUCCESS;
			}
			else{
				return AppConstant.ERROR;
			}
	}
	
}
