package in.adcast.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.adcast.common.utils.AppConstant;
import in.adcast.dto.AppointmentHistoryDto;
import in.adcast.dto.ClientDto;
import in.adcast.dto.ClientNameDto;
import in.adcast.dto.ClientProfileDto;
import in.adcast.dto.ClientReviewDto;
import in.adcast.dto.ContactDto;
import in.adcast.dto.OrderCountDto;
import in.adcast.dto.UpcomingAppointmentDto;
import in.adcast.exception.ClientNotFoundException;
import in.adcast.exception.CustomRuntimeException;

import in.adcast.services.ClientService;


@RestController
public class ClientController 
{

	private static final String COMMON_SERVICE_PATH = "/rest/client/";
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value=COMMON_SERVICE_PATH +"/addNewClient", method=RequestMethod.POST)	
	public String addNewClient(@RequestBody ClientDto clientDto ,HttpServletResponse response){
		
		
		Integer clientId = null;
		clientId = clientService.addNewClient(clientDto);
		if(clientId!=null)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	
	@RequestMapping(value=COMMON_SERVICE_PATH +"/saveClientAddress", method=RequestMethod.POST)	
	public String saveCliendAddress(@RequestBody ClientDto clientDto ,HttpServletResponse response)
	{
		boolean success = false ;
		success = clientService.saveClientAddress(clientDto);
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	@RequestMapping(value=COMMON_SERVICE_PATH +"/list", method=RequestMethod.GET)		
	public 	List<ClientDto> getAllClient(@RequestParam(required = false) String userId) {

		List<ClientDto> clientDtoList = null;
		
		try{
			clientDtoList = clientService.findAll(userId);
			
		} catch (ClientNotFoundException e) {
			
			e.printStackTrace();
			throw e;
			
		} catch (Exception e){
			
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return clientDtoList;
	}

	@RequestMapping(value=COMMON_SERVICE_PATH +"/searchClient/{queri}/{organizationId}", method=RequestMethod.GET)		
	public 	List<ClientNameDto> searchClient(@PathVariable String queri,@PathVariable Integer organizationId) {

		List<ClientNameDto> availableName=null;
	
		try{
			
			availableName = clientService.searchClientByMobileORName(organizationId,queri);
			
		} catch (ClientNotFoundException e) {
			
			e.printStackTrace();
			throw e;
			
		} catch (Exception e){
			
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return availableName;
	}

	

	@RequestMapping(value=COMMON_SERVICE_PATH +"/clientProfilelist", method=RequestMethod.POST)		
	public 	ClientProfileDto getClientProfilelist(@RequestBody ClientProfileDto clientProfileDtoReq){//@RequestParam(required = false) Integer clientId  ) 

		ClientProfileDto clientProfileDto = null;
		
		try{
			clientProfileDto = clientService.findClientProfile(Integer.parseInt(clientProfileDtoReq.getClientId()));
			
		} catch (ClientNotFoundException e) {
			
            e.printStackTrace();
			throw e;
			
		} catch (Exception e){
			
            e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return clientProfileDto;
	}
	
	
	@RequestMapping(value=COMMON_SERVICE_PATH +"/getOrderCountforClient", method=RequestMethod.POST)		
	public 	OrderCountDto getOrderCountforClient(@RequestBody ClientProfileDto clientProfileDtoReq){

		OrderCountDto orderCountDto = null;
		
		try{
			
			orderCountDto = clientService.getOrderCountforClient(Integer.parseInt(clientProfileDtoReq.getClientId()));
			
		} catch (Exception e){
			
            e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return orderCountDto;
	}
	
	@RequestMapping(value=COMMON_SERVICE_PATH +"/getUpcomingAppointmentForClient", method=RequestMethod.GET)		
	public 	List<UpcomingAppointmentDto> getUpcomingAppointmentForClient(@RequestParam(required = false) Integer clientId){

		List<UpcomingAppointmentDto> upcomingAppointmentDtosList = null;
		
		try{
			
			upcomingAppointmentDtosList = clientService.getUpcomingAppointmentForClient(clientId);
			
		} catch (Exception e){
			
            e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return upcomingAppointmentDtosList;
	}
	
	
	@RequestMapping(value=COMMON_SERVICE_PATH +"/getAppointmentHistoryForClient", method=RequestMethod.GET)		
	public 	List<AppointmentHistoryDto> getAppointmentHistoryForClient(@RequestParam(required = false) Integer clientId){

		List<AppointmentHistoryDto> appointmentHistoryDtosList = null;
		
		try{
			
			appointmentHistoryDtosList = clientService.getAppointmentHistoryForClient(clientId);
			
		} catch (Exception e){
			
            e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return appointmentHistoryDtosList;
	}
    
	
	@RequestMapping(value=COMMON_SERVICE_PATH +"/clientEditlist", method=RequestMethod.POST)		
	public 	ClientDto getClientEditDetails(@RequestBody ClientDto clientDtoReq){
		ClientDto clientDto= null;
		
		try{
			clientDto = clientService.getClientEditDetails(Integer.parseInt(clientDtoReq.getClientId()));
			
		} catch (ClientNotFoundException e) {
			
			e.printStackTrace();
			throw e;
			
		} catch (Exception e){
			
			e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return clientDto;
	}
	
	@RequestMapping(value=COMMON_SERVICE_PATH +"/updateClientEditlist", method=RequestMethod.PUT)	
	public ClientDto updateClientEditDetails(@RequestBody ClientDto clientDtoReq ,HttpServletResponse response){	
		ClientDto clientDto= null;
		
		try{
			clientDto = clientService.updateClientEditDetails(clientDtoReq);
			
		} catch (ClientNotFoundException e) {
			
			e.printStackTrace();
			throw e;
			
		} catch (Exception e){
			
            e.printStackTrace();
			throw new CustomRuntimeException("GENERAL EXCEPTION", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return clientDto;
		
	}
	
	@RequestMapping(value=COMMON_SERVICE_PATH +"/saveClientReview",method=RequestMethod.POST)
	public String saveClientReview(@RequestBody ClientReviewDto clientReviewDto ,HttpServletResponse response){	
		
		boolean success = false;
		
		success = clientService.saveClientReview(clientReviewDto);
		
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	@RequestMapping(value=COMMON_SERVICE_PATH +"/saveContact",method=RequestMethod.POST)
	public String saveContact(@RequestBody ContactDto contactDto ,HttpServletResponse response){	
		
		boolean success = false;
		
		success = clientService.saveContact(contactDto);
		
		if(success)
			return AppConstant.SUCCESS;
		else
			return AppConstant.ERROR;
	}
	
	
}
