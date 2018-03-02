package in.adcast.mapper;


import org.eclipse.paho.client.mqttv3.util.Debug;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import in.adcast.dao.OfferTypeDao;

import in.adcast.dto.CampaignDto;
import in.adcast.model.OfferTemplate;
import in.adcast.model.Offers;


@Component
public class CampaignMapper {
	
	@Autowired
	OfferTypeDao offerTypeDao;
	public Offers prpareEntity(CampaignDto campaignDto) 
	{
		
		Offers offers=new Offers();
		
		if(null!= (Integer)campaignDto.getOfferTypeId())
		{
			offers.setOfferType(offerTypeDao.findById(campaignDto.getOfferTypeId()));
		}
		if(null!=campaignDto.getOfferDate())
			offers.setStartDate(campaignDto.getOfferDate());
		
		if(null!=campaignDto.getEndDate())
			offers.setEndDate(campaignDto.getEndDate());
		
		if(null!=campaignDto.getSendBy())
			offers.setSendBy(campaignDto.getSendBy());
		
		if(null!=campaignDto.getReminderNotice())
			offers.setReminderNotice(campaignDto.getReminderNotice());
		
		if(null != campaignDto.getOfferDiscountPer())
			offers.setOfferDiscountPer(campaignDto.getOfferDiscountPer());
		
		offers.setOfferStatus(campaignDto.isOfferStatus());
		
		offers.setOfferSent(false);
		
		return offers;
	
	}

	public List<CampaignDto> prepareDto(List<Offers> offerList) {
		
		List<CampaignDto> campaignDtoList = new LinkedList<>();
		
		
	
		for(Offers offers : offerList){
			CampaignDto campaignDto = new CampaignDto();	
			if(null!=offers.getOfferType())
				campaignDto.setOfferType(offers.getOfferType().getOfferType());			
			if(null!=offers.getEndDate())
				campaignDto.setEndDate(offers.getEndDate());
			if(null!=offers.getStartDate())
				campaignDto.setOfferDate(offers.getStartDate());
			if(null!=offers.getReminderNotice())
				campaignDto.setReminderNotice(offers.getReminderNotice());
			if(null != offers.getOfferDiscountPer())
				campaignDto.setOfferDiscountPer(offers.getOfferDiscountPer());
			if(null != offers.getBranch())
				campaignDto.setLocationName(offers.getBranch().getLocationName());
			if(null != offers.getBranch())
				campaignDto.setContactNo(offers.getBranch().getContactNo());
			
			campaignDto.setOfferStatus(offers.isOfferStatus());//added by akundurkar
			campaignDto.setOfferId(offers.getId());
			campaignDtoList.add(campaignDto);
			System.out.println("offerstatus - "+campaignDto.isOfferStatus());

		}
		
		return campaignDtoList;
	}

	public CampaignDto prepareDtoOffersTemplate(OfferTemplate offerTemplate) {
		
		CampaignDto campaignDto = new CampaignDto();	
		
		if(null!=offerTemplate.getSmsTemplate())
			campaignDto.setSmsTemplate(offerTemplate.getSmsTemplate());
		if(null!=offerTemplate.getEmailTemplate())
			campaignDto.setEmailTemplate(offerTemplate.getEmailTemplate());
		if(null!=offerTemplate.getEmailSubject())
			campaignDto.setEmailSubject(offerTemplate.getEmailSubject());
		
		return campaignDto;
	}

}
