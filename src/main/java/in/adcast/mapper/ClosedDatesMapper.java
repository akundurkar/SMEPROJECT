package in.adcast.mapper;

import org.springframework.stereotype.Component;

import in.adcast.dto.ClosedDatesDto;

import in.adcast.model.ClosedDates;
import in.adcast.model.TaxMasterdata;



@Component
public class ClosedDatesMapper 
{

	public ClosedDates prpareEntity(ClosedDatesDto closedDatesDto) {
		// TODO Auto-generated method stub
		
		ClosedDates closedDates =new ClosedDates();
		
		if(null!=closedDatesDto.getStartDate())
			closedDates.setStartDate(closedDatesDto.getStartDate());
		if(null!=closedDatesDto.getFinishDate())
			closedDates.setEndDate(closedDatesDto.getFinishDate());
		if(null!=closedDatesDto.getDescription())
			closedDates.setDescription(closedDatesDto.getDescription());
		
		
		return closedDates;
	}

	public ClosedDatesDto prepareDto(ClosedDates closedDates) {
		// TODO Auto-generated method stub
		ClosedDatesDto closedDatesDto =new ClosedDatesDto();
		
		if(null!=closedDates.getId())
			closedDatesDto.setId(closedDates.getId());
		
		if(null!=closedDates.getBranch())
			closedDatesDto.setBranchId(closedDates.getBranch().getId());
		
		if(null!=closedDates.getBranch())
			closedDatesDto.setBranchName(closedDates.getBranch().getLocationName());
		
		if(null!=closedDates.getStartDate())
			closedDatesDto.setStartDate(closedDates.getStartDate());
		
		if(null!=closedDates.getEndDate())
			closedDatesDto.setFinishDate(closedDates.getEndDate());
		
		if(null!=closedDates.getDescription())
			closedDatesDto.setDescription(closedDates.getDescription());
		
		return closedDatesDto;
	}

	
	
	
	
	
}
