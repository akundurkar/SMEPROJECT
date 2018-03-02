package in.adcast.mapper;

import org.springframework.stereotype.Component;
import in.adcast.dto.MediaDto;
import in.adcast.model.Media;

@Component
public class MediaMapper 
{

	public MediaDto prepareDto(Media media) {
	
		MediaDto mediaDto=new MediaDto();
		
		if(null != media.getId())
			mediaDto.setImageId(media.getId());
		if(null != media.getFile())
			mediaDto.setFileName(media.getFileName());
		if(null !=media.getImageType())
			mediaDto.setImageType(media.getImageType());
	
		return mediaDto;
	}

}
