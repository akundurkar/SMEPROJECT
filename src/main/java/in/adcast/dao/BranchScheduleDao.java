package in.adcast.dao;


import in.adcast.common.GenericDAO;
import in.adcast.dto.BranchScheduleDto;
import in.adcast.model.BranchSchedule;


public interface BranchScheduleDao extends GenericDAO<BranchSchedule, Integer> {

	void saveBranchScheduleDetails(BranchScheduleDto branchScheduleDto);
		
}
