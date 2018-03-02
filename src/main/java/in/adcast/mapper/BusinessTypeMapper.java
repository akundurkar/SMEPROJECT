package in.adcast.mapper;

import org.springframework.stereotype.Component;

import in.adcast.dto.BusinessTypeDto;
import in.adcast.model.BusinessTypeMasterdata;


@Component
public class BusinessTypeMapper {

	public BusinessTypeDto prepareDto(BusinessTypeMasterdata businessTypeMasterdata) {
		
		BusinessTypeDto businessTypeDto = new BusinessTypeDto();
		
			businessTypeDto.setBusinessTypeId(businessTypeMasterdata.getId());
		
		if(null!=businessTypeMasterdata.getBusinessType())
			businessTypeDto.setBusinessTypeName(businessTypeMasterdata.getBusinessType());
		
		return businessTypeDto;
	}
	
	
}
