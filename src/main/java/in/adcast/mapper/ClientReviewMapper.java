package in.adcast.mapper;

import org.springframework.stereotype.Component;

import in.adcast.dto.ClientReviewDto;

import in.adcast.model.ClientReview;

@Component
public class ClientReviewMapper
{

	public ClientReview prepareEntity(ClientReviewDto  clientReviewDto)
	{
	
		 ClientReview clientReview = new ClientReview();
		
		if(null!=clientReviewDto.getName())
			clientReview.setName(clientReviewDto.getName());
		if(null!=clientReviewDto.getPhone())
			clientReview.setPhone(clientReviewDto.getPhone());
		if(null!=clientReviewDto.getEmail())
			clientReview.setEmailId(clientReviewDto.getEmail());
		if(null!=clientReviewDto.getRating())
		    clientReview.setRating(clientReviewDto.getRating());
		if(null!=clientReviewDto.getComment())
			clientReview.setComment(clientReviewDto.getComment());
		
		return clientReview;
	}

	
	
	
}
