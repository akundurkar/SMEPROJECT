package in.adcast.dao.impl;

import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;

import in.adcast.dao.StaffScheduleDao;
import in.adcast.dto.StaffScheduleDto;
import in.adcast.model.StaffSchedule;

@SuppressWarnings("serial")
@Repository
public class StaffScheduleDaoImpl  extends GenericDAOImpl<StaffSchedule,Integer>  implements StaffScheduleDao {

	@Override
	public void saveStaffScheduleDetails(StaffScheduleDto staffScheduleDto) {
		// TODO Auto-generated method stub
		
	}

	
	

	

	
}
