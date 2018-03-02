package in.adcast.dao.impl;

import java.util.List;


import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;

import in.adcast.dao.FabagoDao;

import in.adcast.model.BusinessTypeMasterdata;


@SuppressWarnings("serial")
@Repository
public class FabagoDaoImpl extends GenericDAOImpl<BusinessTypeMasterdata,Integer>  implements FabagoDao{

	  
	
	@Override
	public List<BusinessTypeMasterdata> getBusinessTypeName(Integer organizationId,String queri) {
		
		List<BusinessTypeMasterdata> BusinessTypeMasterdataList =null;
		
		
		return BusinessTypeMasterdataList;
		
	}

	

}
