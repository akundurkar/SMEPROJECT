package in.adcast.mapper;

import org.springframework.stereotype.Component;

import in.adcast.dto.OrganisationDto;
import in.adcast.model.Organisation;



@Component
public class OrganisationMapper {
	
	public Organisation prepareEntity(OrganisationDto organisationDto){
		Organisation organisation = new Organisation();
		
		if(null != organisationDto.getAdress1()) 
			organisation.setAdress1(organisationDto.getAdress1());
		if(null != organisationDto.getAdress2()) 
			organisation.setAdress2(organisationDto.getAdress2());
		if(null != organisationDto.getCity()) 
			organisation.setCity(organisationDto.getCity());
		if(null != organisationDto.getBusinessName()) 
			organisation.setOrganisationName(organisationDto.getBusinessName());
		if(null != organisationDto.getOfficephone()) 
			organisation.setPhone(organisationDto.getOfficephone());
		if(null != organisationDto.getPincode()) 
			organisation.setPincode(organisationDto.getPincode());
		if(null != organisationDto.getState()) 
			organisation.setState(organisationDto.getState());
		if(null != organisationDto.getWebsite()) 
			organisation.setWebsite(organisationDto.getWebsite());
		if(null != organisationDto.getInfo())
			organisation.setDetailInfo(organisationDto.getInfo());
		
		return organisation;
	}

	public OrganisationDto prepareDto(Organisation organisation) {
		OrganisationDto organisationDto = new OrganisationDto();
		
		if(null != organisation.getAdress1()) 
			organisationDto.setAdress1(organisation.getAdress1());
		if(null != organisation.getAdress2()) 
			organisationDto.setAdress2(organisation.getAdress2());
		if(null != organisation.getCity()) 
			organisationDto.setCity(organisation.getCity());
		if(null != organisation.getOrganisationName()) 
			organisationDto.setBusinessName(organisation.getOrganisationName());
		if(null != organisation.getPhone()) 
			organisationDto.setOfficephone(organisation.getPhone());
		if(null != organisation.getPincode()) 
			organisationDto.setPincode(organisation.getPincode());
		if(null != organisation.getState()) 
			organisationDto.setState(organisation.getState());
		if(null != organisation.getWebsite()) 
			organisationDto.setWebsite(organisation.getWebsite());
		if(null != organisation.getDetailInfo())
			organisationDto.setInfo(organisation.getDetailInfo());
		 
		
		if(null != organisation.getBusinessTypeMasterdata())
		{	
			organisationDto.setBusinesssCategory(organisation.getBusinessTypeMasterdata().getId());
			
		}
		
		return organisationDto;
	}
}
