package in.adcast.mapper;

import org.springframework.stereotype.Component;

import in.adcast.dto.CampaignDto;
import in.adcast.model.OfferTemplate;

@Component
public class OfferTemplateMapper {

	public OfferTemplate prpareEntity(CampaignDto campaignDto){
		
		OfferTemplate offerTemplate=new OfferTemplate();
		
		if(null!=campaignDto.getEmailSubject())
			offerTemplate.setEmailSubject(campaignDto.getEmailSubject());
		
		if(null!=campaignDto.getEmailTemplate())
			offerTemplate.setEmailTemplate(campaignDto.getEmailTemplate());
		
		if(null!=campaignDto.getSmsTemplate())
			offerTemplate.setSmsTemplate(campaignDto.getSmsTemplate());
		
		
		return offerTemplate;
		
	}
	
	
}
