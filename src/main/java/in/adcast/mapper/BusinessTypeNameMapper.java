package in.adcast.mapper;


import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import in.adcast.dto.BusinessTypeNameDto;
import in.adcast.model.BusinessTypeMasterdata;

@Component
public class BusinessTypeNameMapper
{

	public List<BusinessTypeNameDto> prepareDto(List<BusinessTypeMasterdata> BusinessTypeMasterdatalist) {
		
		List<BusinessTypeNameDto> businessTypeNameDtoList = new ArrayList<>();
		
		for(BusinessTypeMasterdata businessTypeMasterdata : BusinessTypeMasterdatalist){
			
			BusinessTypeNameDto businessTypeNameDto = new BusinessTypeNameDto();
			
			businessTypeNameDto.setName(businessTypeMasterdata.getBusinessType());
			
				businessTypeNameDtoList.add(businessTypeNameDto);
				
		}
		
		return businessTypeNameDtoList;
	}

}
