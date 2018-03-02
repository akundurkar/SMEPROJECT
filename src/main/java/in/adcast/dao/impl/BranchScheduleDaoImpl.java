package in.adcast.dao.impl;

import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;

import in.adcast.dao.BranchScheduleDao;
import in.adcast.dto.BranchScheduleDto;
import in.adcast.model.BranchSchedule;

@SuppressWarnings("serial")
@Repository
public class BranchScheduleDaoImpl  extends GenericDAOImpl<BranchSchedule,Integer>  implements BranchScheduleDao {

	@Override
	public void saveBranchScheduleDetails(BranchScheduleDto branchScheduleDto) {
		// TODO Auto-generated method stub
		
	}

	

	

	
}
