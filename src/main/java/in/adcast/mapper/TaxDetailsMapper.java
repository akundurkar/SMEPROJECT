package in.adcast.mapper;

import org.springframework.stereotype.Component;
import in.adcast.dto.TaxDto;
import in.adcast.model.TaxMasterdata;


@Component
public class TaxDetailsMapper 
{

	public TaxMasterdata prpareEntity(TaxDto taxDto){
		
		TaxMasterdata taxMasterdata =new TaxMasterdata();
			
		if(null!=taxDto.getTaxName())
			taxMasterdata.setTaxType(taxDto.getTaxName());
		if(null!=taxDto.getTaxRate())
			taxMasterdata.setTaxRate(taxDto.getTaxRate());
			
		return taxMasterdata;
	}

	public TaxDto prepareDto(TaxMasterdata taxMasterdata) {
		
		TaxDto taxDto =new TaxDto();
		
		if(null!=taxMasterdata.getId())
			taxDto.setTaxId(taxMasterdata.getId());
		if(null!=taxMasterdata.getTaxType())
			taxDto.setTaxName(taxMasterdata.getTaxType());
		if(null!=taxMasterdata.getTaxRate())
			taxDto.setTaxRate(taxMasterdata.getTaxRate());
			
		return taxDto;
	}
	
	
	
}
