package in.adcast.services;

import java.util.List;

import in.adcast.dto.CampaignDto;
import in.adcast.dto.ClientDto;
import in.adcast.dto.OfferDto;
import in.adcast.dto.OfferTypeDto;

public interface CampaignService {
	
	public boolean saveNewOffer(CampaignDto campaignDto);

	public List<CampaignDto> getCampaignDetails(Integer branchId);

	public List<OfferTypeDto> searcEventNameList(String userId);

	public List<OfferTypeDto> searchFestivalNameList(String userId);

	public boolean saveOfferTypeDetails(OfferTypeDto offerTypeDto);

	public List<CampaignDto> getEditCampaignDetails(Integer branchId);

	public List<ClientDto> getAllCampaignTemplate();

	public boolean changeOfferStatus(OfferDto offerDto);

	CampaignDto getCampaignTemplate(Integer branchId);
	
}
