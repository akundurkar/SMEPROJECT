package in.adcast.mapper;

import org.springframework.stereotype.Component;
import in.adcast.dto.ReferralSourceDto;
import in.adcast.model.ReferralMasterdata;

@Component
public class ReferralSourceMapper 
{

	public ReferralMasterdata prpareEntity(ReferralSourceDto referralSourceDto){
		
		ReferralMasterdata referralMasterdata =new ReferralMasterdata();
			
		if(null!=referralSourceDto.getReferralName())
			referralMasterdata.setReferralType(referralSourceDto.getReferralName());
		
		if(null!=referralSourceDto.getReferralCode())
			referralMasterdata.setReferralCode(referralSourceDto.getReferralCode());
		
		if(null!=referralSourceDto.getReferralPoint())
			referralMasterdata.setReferralPoint(referralSourceDto.getReferralPoint());
		
		
		return referralMasterdata;
	}
	
	public ReferralSourceDto prepareDto(ReferralMasterdata referralMasterdata){
		
		ReferralSourceDto referralSourceDto=new ReferralSourceDto();
		
		if(null!=referralMasterdata.getReferralType())
			referralSourceDto.setReferralName(referralMasterdata.getReferralType());
		
		if(null!=referralMasterdata.getReferralCode())
		   referralSourceDto.setReferralCode(referralMasterdata.getReferralCode());
		
		if (null!=referralMasterdata.getReferralPoint())
			referralSourceDto.setReferralPoint(referralMasterdata.getReferralPoint());
		
		return referralSourceDto;
	}
	


}
