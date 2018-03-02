package in.adcast.mapper;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import in.adcast.dto.OfferTypeDto;

import in.adcast.model.OfferType;


@Component
public class OfferTypeMapper {
	

	public List<OfferTypeDto> prepareDto(List<OfferType> offerTypesList) {
		
		List<OfferTypeDto> offerTypeDtoList = new ArrayList<>();
		
		for (OfferType offerType : offerTypesList)
		{
			
			OfferTypeDto offerTypeDto = new OfferTypeDto();
			
				offerTypeDto.setOfferType(offerType.getOfferType());
				offerTypeDto.setId(offerType.getId());
			
			offerTypeDtoList.add(offerTypeDto);
			
		}
		
		return offerTypeDtoList;
	}


	public OfferType prepareEntity(OfferTypeDto offerTypeDto) {
		
		OfferType offerType = new OfferType();
		
		if (null!=offerTypeDto.getId())
			offerType.setId(offerTypeDto.getId());
		if (null!=offerTypeDto.getOfferTypeName())
			offerType.setOfferType(offerTypeDto.getOfferTypeName());
		if (null!=offerTypeDto.getOfferDate())
			offerType.setOfferDate(offerTypeDto.getOfferDate());
		if(offerTypeDto.getOfferType().equals("1")){
			offerType.setEvent(true);
		}else{
			offerType.setEvent(false);
		}
		if(offerTypeDto.getOfferType().equals("2")){
			offerType.setFestival(true);
		}else{
			offerType.setFestival(false);
		}
		
		return offerType;
	}

}
