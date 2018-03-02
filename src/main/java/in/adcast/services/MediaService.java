package in.adcast.services;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import in.adcast.dto.MediaDto;
import in.adcast.exception.TechnicalException;


public interface MediaService {
	/**
	 * upload
	 * @param request
	 * @param response
	 * @return String
	 * @throws TechnicalException
	 * @throws Exception
	 */
	public String upload(MultipartHttpServletRequest request, HttpServletResponse response) throws TechnicalException, Exception;

	public List<MediaDto>  getImageForGallery(String userId);

	public byte[] getMediaById(int id, boolean b);

	public Boolean deleteImage(Integer imageId);

	
	/**
	 * getImageThumbnailById
	 * @param id
	 * @return byte[]
	 
	public byte[] getImageThumbnailById(final int id);*/
	
	/**
	 * getImageById
	 * @param id
	 * @return byte[]
	 */
	
	
	
	//public byte[] getMediaById(final int id,boolean isOriginal);
	
	/**
	 * Get list of media based on the userid
	 * @param id
	 * @return
	 */
	//public List<MediaDto> getList(String userId,String type);
	
	/**
	 * Update status to inactive
	 * @param mediaId
	 */
	
	//public boolean delete (String mediaId);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	//public Media getMediaInfo(final int id);
	
	/*public boolean userHasAprovedMedia(final String userId);*/
	
	//public void cacheMedia();
	
	/**
	 * This returns XYZ. 
	 * @param String id, String type.
	 * @return 	List MediaDto.
	 */

//	public List<MediaDto> getLatestList(String id, String type);
	
	/**
	 * This returns XYZ. 
	 * @param String id, String type.
	 * @return 	List MediaDto.
	 */
	//public List<MediaDto> getUserMediaForCampaign(String id, String type);
	
	/**
	 * This returns XYZ. 
	 * @param String id, String type.
	 * @return 	List GroupMediaDto.
	 */
	
	
}
