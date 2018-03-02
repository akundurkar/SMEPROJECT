package in.adcast.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import in.adcast.dto.ServiceMasterdto;
import in.adcast.dto.ServiceNamesDto;
import in.adcast.model.ServiceMasterdata;
import in.adcast.model.ServiceOffered;



@Component
public class ServiceMasterMapper {
	
	

	public ServiceMasterdata prepareEntity(ServiceMasterdto serviceMasterdto){
		
		ServiceMasterdata masterdata=new ServiceMasterdata();
		
		if(null!=serviceMasterdto.getGroupName())
			masterdata.setServiceCategoty(serviceMasterdto.getGroupName());
		
		return masterdata;
	}
	
	public List<ServiceNamesDto> prepareDto(List<ServiceOffered> servicesOfferedList){
		
		List<ServiceNamesDto> serviceNameList = new ArrayList<>();
		
		for(ServiceOffered ServiceOffered : servicesOfferedList)
		{
			ServiceNamesDto serviceNamesDto = new ServiceNamesDto(); 
			
				serviceNamesDto.setServiceName(ServiceOffered.getServiceName());
				serviceNamesDto.setServiceId(ServiceOffered.getId().toString());
				serviceNamesDto.setPrice(ServiceOffered.getPrice());
			
			serviceNameList.add(serviceNamesDto);
		}
		
		return serviceNameList;
	}
	
	public ServiceMasterdto prpareDto( ServiceMasterdata services){
		
		ServiceMasterdto serviceDetail = new ServiceMasterdto();
		
		if(null!=services.getId())
			serviceDetail.setServiceId(services.getId());
		if(null!=services.getServiceCategoty())
			serviceDetail.setGroupName(services.getServiceCategoty());
		
		return serviceDetail;
		
	}
	

}
