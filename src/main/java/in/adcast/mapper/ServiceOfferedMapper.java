package in.adcast.mapper;

import org.springframework.stereotype.Component;

import in.adcast.dto.ServiceDto;
import in.adcast.model.ServiceOffered;



@Component
public class ServiceOfferedMapper {
	
	public ServiceOffered prepareEntity(ServiceDto serviceDto){
		
		ServiceOffered serviceOffered = new ServiceOffered();
		
		if(null !=serviceDto.getServiceName())
			serviceOffered.setServiceName(serviceDto.getServiceName());
		
		if(null !=serviceDto.getPricingTime())
			serviceOffered.setPricingTime(serviceDto.getPricingTime());
		
		if(null !=serviceDto.getDurationTime())
			serviceOffered.setDurationInHrs(serviceDto.getDurationTime());
		
		if(null !=serviceDto.getTreatmentType())
			serviceOffered.setTreatmentType(serviceDto.getTreatmentType());
		
		if(null !=serviceDto.getRetailPrice())
			serviceOffered.setPrice(serviceDto.getRetailPrice());
		
		if(null !=serviceDto.getSpecialPrice())
			serviceOffered.setSpecialPrice(serviceDto.getSpecialPrice());
		
		if(null !=serviceDto.getAvailableFor())
			serviceOffered.setAvailableFor(serviceDto.getAvailableFor());
		
		if(null !=serviceDto.getExtraTimeType())
			serviceOffered.setExtraTimeType(serviceDto.getExtraTimeType());
		
		if(null !=serviceDto.getTax())
			serviceOffered.setTax(serviceDto.getTax());
		
		return serviceOffered;
	}

	public ServiceDto prepareDto(ServiceOffered serviceOffered) {
		
		ServiceDto serviceDto = new ServiceDto();
		
		if(null != serviceOffered.getId())
			serviceDto.setServiceId(serviceOffered.getId());
		
		if(null!=serviceOffered.getBranch())
			serviceDto.setBranchId(serviceOffered.getBranch().getId());
	
		if(null != serviceOffered.getServiceName())
			serviceDto.setServiceName(serviceOffered.getServiceName());
	
		if(null != serviceOffered.getPricingTime())
			serviceDto.setPricingTime(serviceOffered.getPricingTime());
	
		if(null != serviceOffered.getDurationInHrs())
			serviceDto.setDurationTime(serviceOffered.getDurationInHrs());
	
		if(null != serviceOffered.getTreatmentType())
			serviceDto.setTreatmentType(serviceOffered.getTreatmentType());
	
		if(null != serviceOffered.getPrice())
			serviceDto.setRetailPrice(serviceOffered.getPrice());
	
		if(null != serviceOffered.getSpecialPrice())
			serviceDto.setSpecialPrice(serviceOffered.getSpecialPrice());
	
		if(null != serviceOffered.getAvailableFor())
			serviceDto.setAvailableFor(serviceOffered.getAvailableFor());
	
		if(null != serviceOffered.getExtraTimeType())
			serviceDto.setExtraTimeType(serviceOffered.getExtraTimeType());
	
		if(null != serviceOffered.getTax())
			serviceDto.setTax(serviceOffered.getTax());
	
		if(null != serviceOffered.getDiscount())
			serviceDto.setDiscount(serviceOffered.getDiscount());
		
		return serviceDto;
	}
}
