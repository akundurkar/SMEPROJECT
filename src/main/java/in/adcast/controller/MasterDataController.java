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

import in.adcast.dto.BranchScheduleDto;
import in.adcast.dto.CancelOrderDto;
import in.adcast.dto.ClosedDatesDto;
import in.adcast.dto.ReferralSourceDto;
import in.adcast.dto.StaffDto;
import in.adcast.dto.StaffScheduleDto;
import in.adcast.dto.TaxDto;

import in.adcast.exception.CustomRuntimeException;

import in.adcast.services.MasterDataService;


@RestController
public class MasterDataController 
{

	@Autowired
	private MasterDataService masterDataService;
	
	@RequestMapping(value="/rest/masterdata/saveCancelOrderMasterData", method=RequestMethod.POST)	
	public String bookServiceAppoitment(@RequestBody CancelOrderDto cancelOrderDto ,HttpServletResponse response){
		
		boolean success = false ;
		success = masterDataService.saveCancelOrderReason(cancelOrderDto);
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	@RequestMapping(value="/rest/masterdata/saveReferralSourceMasterData", method=RequestMethod.POST)	
	public String saveReferralSource(@RequestBody ReferralSourceDto referralSourceDto ,HttpServletResponse response){
		
		
		boolean success = false ;
		success = masterDataService.saveReferralSource(referralSourceDto);
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	
	
	@RequestMapping(value="/rest/masterdata/saveTaxMasterData", method=RequestMethod.POST)	
	public String saveTaxDetails(@RequestBody TaxDto taxDto ,HttpServletResponse response){
	
		boolean success = false ;
		success = masterDataService.saveTaxDetails(taxDto);
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	@RequestMapping(value= "/rest/masterdata/listTaxMasterData", method=RequestMethod.GET)		
	public 	List<TaxDto> getAllTaxes(@RequestParam(required = false) String userId) {

		List<TaxDto> taxDtoList = null;
		
		try{
			taxDtoList = masterDataService.findAllTaxDetails(userId);
			
		}catch (Exception e){
			
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return taxDtoList;
	}
	
	@RequestMapping(value= "/rest/masterdata/deleteTaxDetails", method=RequestMethod.POST)
	public String  deleteTaxDetailsById(@RequestBody TaxDto taxDto) {
		
		System.out.println("in controller "+taxDto.getTaxId());
		Boolean success = masterDataService.deleteTaxDetails(taxDto.getTaxId());
		
		System.out.println("in after  controller "+taxDto.getTaxId());
		if(success){
			return AppConstant.SUCCESS;
			}
			else{
				return AppConstant.ERROR;
			}
	}
	
	
	
	@RequestMapping(value="/rest/masterdata/getBranchScheduleDetails", method=RequestMethod.GET)		
	public 	BranchScheduleDto getBranchScheduleDetails(@RequestParam(required = false) Integer branchId) 
	{
		BranchScheduleDto scheduleDto = null;
		
		try{
			scheduleDto = masterDataService.getBranchScheduleDetails(branchId);
			
		} catch (Exception e){
			
            e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return scheduleDto;
	}
	
	@RequestMapping(value="/rest/masterdata/getStaffScheduleDetails", method=RequestMethod.GET)
	public StaffScheduleDto getStaffScheduleDetails(@RequestParam(required = false) Integer staffId)
	{
		StaffScheduleDto staffScheduleDto = null;
		
		try{
			staffScheduleDto = masterDataService.getStaffScheduleDetails(staffId);
			
		} catch (Exception e){
			
            e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return staffScheduleDto;
	}
	
	
	@RequestMapping(value="/rest/masterdata/setStaffSchedule", method=RequestMethod.POST)	
	public String setStaffSchedule(@RequestBody StaffScheduleDto staffScheduleDto ,HttpServletResponse response){
	
		boolean success = false ;
		success = masterDataService.setStaffSchedule(staffScheduleDto);
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}	
	
	

	@RequestMapping(value="/rest/masterdata/setClosedDates", method=RequestMethod.POST)	
	public String closedDates(@RequestBody ClosedDatesDto closedDatesDto ,HttpServletResponse response){
	
		boolean success = false ;
		success = masterDataService.setClosedDates(closedDatesDto);
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	
	
	@RequestMapping(value= "/rest/masterdata/getAllClosedDates", method=RequestMethod.GET)		
	public 	List<ClosedDatesDto> getAllClosedDates(@RequestParam(required = false) Integer branchId) {

		List<ClosedDatesDto> closedDatesDto = null;
		
		try{
			closedDatesDto = masterDataService.getAllClosedDates(branchId);
			
		} catch (Exception e){
			
	        e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return closedDatesDto;
	}
	
	@RequestMapping(value= "/rest/masterdata/listCancellationReason", method=RequestMethod.GET)		
	public 	List<CancelOrderDto> getAllCancellationReason(@RequestParam(required = false) String userId) {

		List<CancelOrderDto> cancelOrderDtoList = null;
		
		try{
			cancelOrderDtoList = masterDataService.findAllCancellationReason(userId);
			
		}catch (Exception e){
			
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return cancelOrderDtoList;
	}

	
	
	@RequestMapping(value= "/rest/masterdata/listReferralSourcesData", method=RequestMethod.GET)
	public List<ReferralSourceDto> getAllReferralSources(@RequestParam(required = false) String userId){
     
		List<ReferralSourceDto> referralSourceDtoList = null;
		
		try{
		    referralSourceDtoList =masterDataService.findAllReferralSourceDetails(userId);
			 
		}catch(Exception e){
			
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

	return referralSourceDtoList;
	}
	
	@RequestMapping(value="/rest/masterdata/saveStaffDetails", method=RequestMethod.POST)
	public String saveStaffDetails(@RequestBody StaffDto staffDto, HttpServletResponse response){
		
		boolean success = false ;
		success = masterDataService.saveStaffDetails(staffDto);
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	@RequestMapping(value="/rest/masterdata/staffEditDetails", method=RequestMethod.POST)
	public StaffDto getStaffEditDetails(@RequestBody StaffDto staffDtoReq){
		StaffDto staffDto = null;
		
		try{
			staffDto = masterDataService.getStaffEditDetails(Integer.parseInt(staffDtoReq.getStaffId()));
			
		}catch(Exception e){
			
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return staffDto;
	}
	
	@RequestMapping(value="/rest/masterdata/updateStaffEditDetails", method=RequestMethod.POST)
	public StaffDto updateStaffEditDetails(@RequestBody StaffDto staffDtoReq ,HttpServletResponse response){
	
		StaffDto staffDto = null;
		
		try{
			
			staffDto = masterDataService.updateStaffEditDetails(staffDtoReq);
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
			return staffDto;
		}
	

}
