package in.adcast.services;

import java.util.List;

import in.adcast.dto.BusinessTypeNameDto;


public interface FabagoService 
{

	public List<BusinessTypeNameDto> searchBusinessTypeName(Integer organizationId,String queri);

}
