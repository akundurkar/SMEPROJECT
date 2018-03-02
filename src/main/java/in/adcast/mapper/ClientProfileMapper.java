package in.adcast.mapper;

import org.springframework.stereotype.Component;

import in.adcast.common.utils.AppConstant;
import in.adcast.dto.ClientProfileDto;
import in.adcast.model.Client;


@Component
public class ClientProfileMapper
{

	public ClientProfileDto prepareDto(Client client) {
		ClientProfileDto clientProfileDtoList = new ClientProfileDto();
		
		if(null!=client.getId())
			clientProfileDtoList.setClientId(String.valueOf(client.getId()));
		if(null!=client.getFirstName())
			clientProfileDtoList.setFname(client.getFirstName());
		if(null!=client.getLastName())
			clientProfileDtoList.setLname(client.getLastName());
		if(null!=client.getMobile())
			clientProfileDtoList.setOwnermobile(client.getMobile());
		if(null!=client.getEmailId())
			clientProfileDtoList.setEmail(client.getEmailId());
		if(client.getGender() != null)
		{
			if(client.getGender().equals("M"))
				clientProfileDtoList.setGender("Male");
			else
				clientProfileDtoList.setGender("Female");
		}
		if(client.getNotificationSubscriptionType()!=null){
			
			switch(AppConstant.NotificationSubscriptionType.values()[client.getNotificationSubscriptionType()].toString()){
			
				case "SMS":
					clientProfileDtoList.setNotificationSendBy(AppConstant.NotificationSubscriptionType.SMS.toString());
					break;
				
				case "EMAIL":
					clientProfileDtoList.setNotificationSendBy(AppConstant.NotificationSubscriptionType.EMAIL.toString());
					break;
				
				case "BOTH":
					clientProfileDtoList.setNotificationSendBy(AppConstant.NotificationSubscriptionType.BOTH.toString());
					break;
					
				case "DND":
					clientProfileDtoList.setNotificationSendBy(AppConstant.NotificationSubscriptionType.DND.toString());
					break;
					
			}
		}
			
		if(null!=client.getDob())
			clientProfileDtoList.setDob(client.getDob());
		
		
		return clientProfileDtoList;
	}
	

	
    

}
