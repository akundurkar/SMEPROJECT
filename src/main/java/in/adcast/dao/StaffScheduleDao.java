package in.adcast.dao;


import in.adcast.common.GenericDAO;
import in.adcast.dto.StaffScheduleDto;
import in.adcast.model.StaffSchedule;


public interface StaffScheduleDao extends GenericDAO<StaffSchedule, Integer> {

	void saveStaffScheduleDetails(StaffScheduleDto staffScheduleDto);
		
}
