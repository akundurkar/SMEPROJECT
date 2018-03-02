package in.adcast.mapper;

import org.springframework.stereotype.Component;

import in.adcast.common.utils.AppConstant;
import in.adcast.dto.BranchDto;
import in.adcast.model.Branch;



@Component
public class BranchMapper {
	
	public Branch prepareEntity(BranchDto branchDto){
		
		Branch branch = new Branch();
		
		if(null !=branchDto.getLocationName())
			branch.setLocationName(branchDto.getLocationName());
		
		if(null !=branchDto.getPhone())
			branch.setPhone(branchDto.getPhone());
		
		if(null !=branchDto.getContactNo())
			branch.setContactNo(branchDto.getContactNo());
		
		if(null !=branchDto.getArea())
			branch.setArea(branchDto.getArea());
		
		if(null !=branchDto.getCity())
			branch.setCity(branchDto.getCity().toUpperCase());
		
		if(null !=branchDto.getState())
			branch.setState(branchDto.getState().toUpperCase());
		
		if(null !=branchDto.getLocationPin())
			branch.setPincode(branchDto.getLocationPin());
	
		if(null !=branchDto.getCountry())
			branch.setCountry(branchDto.getCountry());
		
		if(null !=branchDto.getLongitude())
			branch.setLangitude(branchDto.getLongitude());
		
		if(null !=branchDto.getLatitude())
			branch.setLatitude(branchDto.getLatitude());
		
		if(null !=branchDto.getGender())
		{
			
			switch(branchDto.getGender())
			{
			case "UNISEX":
				
				branch.setGender(AppConstant.GenderType.UNISEX.getValue());
				
				break;
				
			case "FEMALE":
				
				branch.setGender(AppConstant.GenderType.FEMALE.getValue());
				
				break;
			
			case "MALE":
				
				branch.setGender(AppConstant.GenderType.MALE.getValue());
				
				break;
			}
			
		}
		
		branch.setAcAvailable(branchDto.getShopType());
			
		branch.setOnlineBookingStatus(branchDto.getOnline_bookng_status());
		
		branch.setWifiAvailable(branchDto.getWiFiAvailable());
			
		return branch;
	}

	public BranchDto prepareDto(Branch branch) 
	{
		BranchDto branchDto = new BranchDto();
	
		if(null!=branch.getId())
			branchDto.setBranchId(branch.getId());
		
		if(null!=branch.getLocationName())
			branchDto.setLocationName(branch.getLocationName());
		
		if(null!=branch.getContactNo())
			branchDto.setContactNo(branch.getContactNo());
		
		if(null!=branch.getState())
			branchDto.setState(branch.getState());
		
		if(null!=branch.getCity())
			branchDto.setCity(branch.getCity());
		
		if(null!=branch.getArea())
			branchDto.setArea(branch.getArea());
		
		if(null!=branch.getPhone())
			branchDto.setPhone(branch.getPhone());
		
		if(null!=branch.getLatitude())
			branchDto.setLatitude(branch.getLatitude());
		
		if(null!=branch.getLangitude())
			branchDto.setLongitude(branch.getLangitude());
		
		if(null!=branch.getPincode())
			branchDto.setLocationPin(branch.getPincode());
		
		branchDto.setShopType(branch.getAcAvailable());
		
		branchDto.setOnline_bookng_status(branch.getOnlineBookingStatus());
		
		branchDto.setWiFiAvailable(branch.getWifiAvailable());
		
		if((Integer)branch.getGender()!= null)
		{
			switch(AppConstant.GenderType.values()[branch.getGender()].toString())
			{
			
			case "FEMALE":
				branchDto.setGender(AppConstant.GenderType.FEMALE.toString());
				break;
				
			case "MALE":
				branchDto.setGender(AppConstant.GenderType.MALE.toString());
				break;
				
			case "UNISEX":
				branchDto.setGender(AppConstant.GenderType.UNISEX.toString());
				break;
			
			}
		}
		
		return branchDto;
	}
}
