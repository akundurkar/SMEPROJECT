package in.adcast.mapper;


import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.adcast.dao.ServiceOfferedDao;
import in.adcast.dao.UserDao;
import in.adcast.dto.ServiceStaffDto;
import in.adcast.model.ServiceOffered;
import in.adcast.model.SubOrder;


@Component
public class SubOrderMapper {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ServiceOfferedDao serviceOfferedDao;

	public SubOrder prepareEntity(ServiceStaffDto serviceStaffDto) {

		SubOrder subOrder = null;
		
		try{

			ServiceOffered serviceOffered = serviceOfferedDao.findById(serviceStaffDto.getServiceId());
			
			if(null != serviceOffered){
				
				subOrder = new SubOrder();
				subOrder.setServiceCost(serviceOffered.getPrice());
				subOrder.setServiceOffered(serviceOffered);		
				subOrder.setApplicationUser(userDao.findById(serviceStaffDto.getStaffId()));
				
			}
			
		}catch(HibernateException he){
			
			he.printStackTrace();
			
		}
		
		return subOrder;

	}

	public List<ServiceStaffDto> prepareServiceStaffDto(List<SubOrder> subOrdersList) {
		
		List<ServiceStaffDto> serviceStaffDtoList = new LinkedList<>();
		
		for(SubOrder subOrder : subOrdersList){
			
			ServiceStaffDto serviceStaffDto = new ServiceStaffDto();
		
			serviceStaffDto.setServiceId(subOrder.getServiceOffered().getId());
			
			serviceStaffDtoList.add(serviceStaffDto);		
		}
		
		return serviceStaffDtoList;
	}

}
