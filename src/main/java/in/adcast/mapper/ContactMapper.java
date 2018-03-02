package in.adcast.mapper;

import org.springframework.stereotype.Component;

import in.adcast.dto.ContactDto;

import in.adcast.model.Contact;

@Component
public class ContactMapper
{

	public Contact prepareEntity(ContactDto contactDto)
	{
	
		 Contact contact = new Contact();
		
		if(null!=contactDto.getName())
			contact.setName(contactDto.getName());
		if(null!=contactDto.getEmail())
			contact.setEmailId(contactDto.getEmail());
		if(null!=contactDto.getMessage())
			contact.setMessage(contactDto.getMessage());
		
		return contact;
	}

	
	
	
}
