package in.adcast.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.adcast.common.utils.AppConstant;
import in.adcast.dto.CampaignDto;
import in.adcast.dto.OfferDto;
import in.adcast.dto.OfferTypeDto;

import in.adcast.exception.CustomRuntimeException;

import in.adcast.services.CampaignService;

@RestController
public class CampaignController {
	
	@Autowired
	private CampaignService campaignService; 
	
	@RequestMapping(value="/rest/saveNewCampaign", method=RequestMethod.POST)	
	public String saveNewCampaign(@RequestBody CampaignDto campaignDto ,HttpServletResponse response){
		
		
		//toDo: campaignDto must contain branchId;
		boolean success = false;
		
			success=campaignService.saveNewOffer(campaignDto);	
		
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	@RequestMapping(value="/rest/getCampaignDetails", method=RequestMethod.GET)
	public 	List<CampaignDto> getCampaignDetails(@RequestParam(required = false) Integer branchId){
		
		List<CampaignDto> campaignDtoList = null;
		
		try{
			
			campaignDtoList =campaignService.getCampaignDetails(branchId);	
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return campaignDtoList;
	}
	
	@RequestMapping(value="/rest/getCampaignTemplate", method=RequestMethod.GET)
	public 	CampaignDto getCampaignTemplate(@RequestParam(required = false) Integer branchId){
		
		CampaignDto campaignDto = null;
		
		try{
			
			campaignDto =campaignService.getCampaignTemplate(branchId);	
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return campaignDto;
	}
	
	@RequestMapping(value="/rest/searcEventNameList", method=RequestMethod.GET)		
	public 	List<OfferTypeDto> searcEventNameList(@RequestParam(required = false) String userId) 
	{
		List<OfferTypeDto> eventNameList=null;
	
		try{
			
			eventNameList = campaignService.searcEventNameList(userId);
			
		} catch (Exception e){
			
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return eventNameList;
	}
	
	@RequestMapping(value="/rest/searcFestivalNameList", method=RequestMethod.GET)		
	public 	List<OfferTypeDto> searcFestivalNameList(@RequestParam(required = false) String userId) 
	{
		List<OfferTypeDto> festivalNameList=null;
	
		try{
			
			festivalNameList = campaignService.searchFestivalNameList(userId);
			
		} catch (Exception e)
		{
			
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return festivalNameList;
	}
	
	@RequestMapping(value="/rest/saveOfferTypeDetails", method=RequestMethod.POST)	
	public String saveOfferTypeDetails(@RequestBody OfferTypeDto offerTypeDto ,HttpServletResponse response){
		
		boolean success = false;
		
			success=campaignService.saveOfferTypeDetails(offerTypeDto);	
		
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	@RequestMapping(value="/rest/getCditCampaign", method= RequestMethod.POST)
	public List<CampaignDto> getEditCampaignDetails(@RequestParam(required = false) Integer branchId){
		
		List<CampaignDto> campaignDtoList= null;
		
		try{
			campaignDtoList=campaignService.getEditCampaignDetails(branchId);
		}
		catch (Exception e){
			
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return campaignDtoList;
	}
	
	
	@RequestMapping(value="/rest/changeOfferStatus", method= RequestMethod.POST)
	public String changeOfferStatus(@RequestBody OfferDto offerDto){
		
		List<CampaignDto> campaignDtoList= null;
		boolean success;
		try{
			success = campaignService.changeOfferStatus(offerDto);
		}
		catch (Exception e){
			
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;		
	}

}
