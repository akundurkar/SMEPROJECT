package in.adcast.mapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Component;

import in.adcast.common.utils.AppConstant;
import in.adcast.dto.BookingDto;
import in.adcast.dto.ClientDto;
import in.adcast.model.Client;

@Component
public class ClientMapper 
{

	public Client prepareEntity(ClientDto clientDto)
	{
	
		Client client=new Client();
		
		if(null!=clientDto.getFname())
			client.setFirstName(clientDto.getFname());
		
		if(null!=clientDto.getLname())
			client.setLastName(clientDto.getLname());
		
		if(null!=clientDto.getOwnermobile())
			client.setMobile(clientDto.getOwnermobile());
		
		if(null!=clientDto.getOwnertelephone())
			client.setTelephone(clientDto.getOwnertelephone());
		
		if(null!=clientDto.getEmail())
			client.setEmailId(clientDto.getEmail());
		
		if(null!=clientDto.getNotificationSendBy()){
			switch(clientDto.getNotificationSendBy()){
			
				case "BOTH":
					client.setNotificationSubscriptionType(AppConstant.NotificationSubscriptionType.BOTH.getValue());
					break;
					
				case "EMAIL":
					client.setNotificationSubscriptionType(AppConstant.NotificationSubscriptionType.EMAIL.getValue());
					break;
					
				case "SMS":
					client.setNotificationSubscriptionType(AppConstant.NotificationSubscriptionType.SMS.getValue());
					break;
					
				case "DND":
					client.setNotificationSubscriptionType(AppConstant.NotificationSubscriptionType.DND.getValue());
					break;
					
			}
		}
		if(clientDto.getGender() != null)
			client.setGender(clientDto.getGender());
		
			client.setMaritalStatus(clientDto.getMaritalStatus());
		
		if(null!=clientDto.getClientAddress())
			client.setAdress1(clientDto.getClientAddress());
		
		if(null!=clientDto.getOwnercity())
			client.setCity(clientDto.getOwnercity().toUpperCase());
		
		if(null!=clientDto.getOwnerstate())
			client.setState(clientDto.getOwnerstate().toUpperCase());
		
		if(null!=clientDto.getOwnercode())
			client.setPincode(clientDto.getOwnercode());
		
		
/*		if(null!=clientDto.getDate() && null!=clientDto.getYear() && null!=clientDto.getMonth())
		{
			Date date=null;
			String dobDateString = clientDto.getDate()+"/"+clientDto.getMonth()+"/"+clientDto.getYear();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
			try 
			{
				 date = df.parse(dobDateString);
			} catch (ParseException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}*/
			
		if(null!=clientDto.getDob()){
			client.setDob(clientDto.getDob());
		}
			
		return client;
	}

	public ClientDto prepareDto(Client client) {
	
		ClientDto clientDto=new ClientDto();
		
		if(null != client.getId())
			clientDto.setClientId(client.getId().toString());
		
		if(null!=client.getFirstName())
			clientDto.setFname(client.getFirstName());
		
		if(null!=client.getLastName())
			clientDto.setLname(client.getLastName());
		
		if(null!=client.getMobile())
			clientDto.setOwnermobile(client.getMobile());
		
		if(null!=client.getTelephone())
			clientDto.setOwnertelephone(client.getTelephone());
		
		if(null!=client.getEmailId())
			clientDto.setEmail(client.getEmailId());
	
		if(null!=client.getAdress1())
			clientDto.setClientAddress(client.getAdress1());
		
		if(null!=client.getCity())
			clientDto.setOwnercity(client.getCity());
		
		if(null!=client.getState())
			clientDto.setOwnerstate(client.getState());
		
		if(null!=client.getPincode())
			clientDto.setOwnercode(client.getPincode());
		
		if(null!=client.getDob())
			clientDto.setDob(client.getDob());
		 
		if(client.getGender() != null)
			clientDto.setGender(client.getGender());
		
			clientDto.setMaritalStatus(client.getMaritalStatus());
		
		if(client.getNotificationSubscriptionType()!=null){
			
			switch(AppConstant.NotificationSubscriptionType.values()[client.getNotificationSubscriptionType()].toString()){
			
				case "SMS":
					clientDto.setNotificationSendBy(AppConstant.NotificationSubscriptionType.SMS.toString());
					break;
				
				case "EMAIL":
					clientDto.setNotificationSendBy(AppConstant.NotificationSubscriptionType.EMAIL.toString());
					break;
				
				case "BOTH":
					clientDto.setNotificationSendBy(AppConstant.NotificationSubscriptionType.BOTH.toString());
					break;
					
				case "DND":
					clientDto.setNotificationSendBy(AppConstant.NotificationSubscriptionType.DND.toString());
					break;
					
			}
		}
	    if(null!=client.getDob()){
	    	
	    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    	Date today = client.getDob();
	    	String reportDate = df.format(today);
	    	clientDto.setYear(reportDate);
	    }
		return clientDto;
	}

	public Client prepareEntity(BookingDto bookingDtoReq) {

		
		Client client=new Client();
		
		if(null!=bookingDtoReq.getClientFirstName())
			client.setFirstName(bookingDtoReq.getClientFirstName());
		
		if(null!=bookingDtoReq.getClientLastName())
			client.setLastName(bookingDtoReq.getClientLastName());
		
		if(null!=bookingDtoReq.getMobile())
			client.setMobile(bookingDtoReq.getMobile());
		else if(null != bookingDtoReq.getNewClientMobile())
			client.setMobile(bookingDtoReq.getNewClientMobile());
		
		if(null!=bookingDtoReq.getClientEmail())
			client.setEmailId(bookingDtoReq.getClientEmail());			
		
		if(null != bookingDtoReq.getClientGender())
			client.setGender(bookingDtoReq.getClientGender());
		
		if(null != bookingDtoReq.getMaritalStatus())
			client.setMaritalStatus(bookingDtoReq.getMaritalStatus());
		
		if(null!=bookingDtoReq.getClientAddress())
			client.setAdress1(bookingDtoReq.getClientAddress());
		
		if(null!=bookingDtoReq.getClientCity())
			client.setCity(bookingDtoReq.getClientCity().toUpperCase());
		
		if(null!=bookingDtoReq.getClientState())
			client.setState(bookingDtoReq.getClientState().toUpperCase());
		
		if(null!=bookingDtoReq.getClientPincode())
			client.setPincode(bookingDtoReq.getClientPincode());			
			
		if(null!=bookingDtoReq.getClientDOB()){
			client.setDob(bookingDtoReq.getClientDOB());
		}
		client.setNotificationSubscriptionType(AppConstant.NotificationSubscriptionType.SMS.getValue());
		
		return client;
	
	}
	
	
}
