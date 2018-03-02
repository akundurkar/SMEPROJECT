package in.adcast.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import in.adcast.common.utils.AppConstant;
import in.adcast.dto.BranchScheduleDto;
import in.adcast.model.BranchSchedule;


@Component
public class BranchScheduleMapper 
{

	public List<BranchSchedule> prpareEntity(BranchScheduleDto branchScheduleDto) {

		List<BranchSchedule> branchScheduleList = new ArrayList<>();
      
		String s1="Open Time", s2="Closed Time";
		
		if(!s1.equals(branchScheduleDto.getOpenTimeSun()) && !s2.equals(branchScheduleDto.getCloseTimeSun())){
			BranchSchedule branchSchedule= new BranchSchedule();
			branchSchedule.setWeekDay(AppConstant.WeekDay.SUNDAY.getValue());
	        if (null!=branchScheduleDto.getOpenTimeSun())
	        	branchSchedule.setOpenTime(branchScheduleDto.getOpenTimeSun());
	        
	        if (null!=branchScheduleDto.getCloseTimeSun())
	        	branchSchedule.setCloseTime(branchScheduleDto.getCloseTimeSun());
	        branchScheduleList.add(branchSchedule);
		}else{
			BranchSchedule branchSchedule= new BranchSchedule();
			branchSchedule.setWeekDay(AppConstant.WeekDay.SUNDAY.getValue());
	        branchSchedule.setCloseTime("closed");
	        branchSchedule.setOpenTime("closed");
	        branchScheduleList.add(branchSchedule);
		}
        
		if(!s1.equals(branchScheduleDto.getOpenTimeMon()) && !s2.equals(branchScheduleDto.getCloseTimeMon())){
	        BranchSchedule branchSchedule1= new BranchSchedule();
	        branchSchedule1.setWeekDay(AppConstant.WeekDay.MONDAY.getValue());
	        if (null!=branchScheduleDto.getOpenTimeMon())
	        	branchSchedule1.setOpenTime(branchScheduleDto.getOpenTimeMon());
	        
	        if (null!=branchScheduleDto.getCloseTimeMon())
	        	branchSchedule1.setCloseTime(branchScheduleDto.getCloseTimeMon());
	        branchScheduleList.add(branchSchedule1);
		}else{
			BranchSchedule branchSchedule= new BranchSchedule();
			branchSchedule.setWeekDay(AppConstant.WeekDay.MONDAY.getValue());
	        branchSchedule.setCloseTime("closed");
	        branchSchedule.setOpenTime("closed");
	        branchScheduleList.add(branchSchedule);
		}   
        
		if(!s1.equals(branchScheduleDto.getOpenTimeTue()) && !s2.equals(branchScheduleDto.getCloseTimeTue())){
	        BranchSchedule branchSchedule2 =new BranchSchedule();
	        branchSchedule2.setWeekDay(AppConstant.WeekDay.TUESDAY.getValue());
	        if (null!=branchScheduleDto.getOpenTimeTue())
	        	branchSchedule2.setOpenTime(branchScheduleDto.getOpenTimeTue());
	        
	        if (null!=branchScheduleDto.getCloseTimeTue())
	        	branchSchedule2.setCloseTime(branchScheduleDto.getCloseTimeTue());
	        branchScheduleList.add(branchSchedule2);
		}else{
			BranchSchedule branchSchedule= new BranchSchedule();
			branchSchedule.setWeekDay(AppConstant.WeekDay.TUESDAY.getValue());
	        branchSchedule.setCloseTime("closed");
	        branchSchedule.setOpenTime("closed");
	        branchScheduleList.add(branchSchedule);
		}   
        
		if(!s1.equals(branchScheduleDto.getOpenTimeWed()) && !s2.equals(branchScheduleDto.getCloseTimeWed())){
	        BranchSchedule branchSchedule3=new BranchSchedule();
	        branchSchedule3.setWeekDay(AppConstant.WeekDay.WEDNESDAY.getValue());
	        if (null!=branchScheduleDto.getOpenTimeWed())
	        	branchSchedule3.setOpenTime(branchScheduleDto.getOpenTimeWed());
	        
	        if (null!=branchScheduleDto.getCloseTimeWed())
	        	branchSchedule3.setCloseTime(branchScheduleDto.getCloseTimeWed());
	        branchScheduleList.add(branchSchedule3);
		}else{
			BranchSchedule branchSchedule= new BranchSchedule();
			branchSchedule.setWeekDay(AppConstant.WeekDay.WEDNESDAY.getValue());
	        branchSchedule.setCloseTime("closed");
	        branchSchedule.setOpenTime("closed");
	        branchScheduleList.add(branchSchedule);
		}   
        
		if(!s1.equals(branchScheduleDto.getOpenTimeThr()) && !s2.equals(branchScheduleDto.getCloseTimeThr())){
		    BranchSchedule branchSchedule4=new BranchSchedule();
		    branchSchedule4.setWeekDay(AppConstant.WeekDay.THURSDAY.getValue());
	        if (null!=branchScheduleDto.getOpenTimeThr())
	        	branchSchedule4.setOpenTime(branchScheduleDto.getOpenTimeThr());
	        
	        if (null!=branchScheduleDto.getCloseTimeThr())
	        	branchSchedule4.setCloseTime(branchScheduleDto.getCloseTimeThr());
	        branchScheduleList.add(branchSchedule4);
		}else{
			BranchSchedule branchSchedule= new BranchSchedule();
			branchSchedule.setWeekDay(AppConstant.WeekDay.THURSDAY.getValue());
	        branchSchedule.setCloseTime("closed");
	        branchSchedule.setOpenTime("closed");
	        branchScheduleList.add(branchSchedule);
		}   
       
		
		if(!s1.equals(branchScheduleDto.getOpenTimeFri()) && !s2.equals(branchScheduleDto.getCloseTimeFri())){
	        BranchSchedule branchSchedule5=new BranchSchedule();
	        branchSchedule5.setWeekDay(AppConstant.WeekDay.FRIDAY.getValue());
	        if (null!=branchScheduleDto.getOpenTimeFri())
	        	branchSchedule5.setOpenTime(branchScheduleDto.getOpenTimeFri());
	        
	        if (null!=branchScheduleDto.getCloseTimeFri())
	        	branchSchedule5.setCloseTime(branchScheduleDto.getCloseTimeFri());
	        branchScheduleList.add(branchSchedule5);
		}else{
			BranchSchedule branchSchedule= new BranchSchedule();
			branchSchedule.setWeekDay(AppConstant.WeekDay.FRIDAY.getValue());
	        branchSchedule.setCloseTime("closed");
	        branchSchedule.setOpenTime("closed");
	        branchScheduleList.add(branchSchedule);
		}   
        
		if(!s1.equals(branchScheduleDto.getOpenTimeSat()) && !s2.equals(branchScheduleDto.getCloseTimeSat())){
	        BranchSchedule branchSchedule6=new BranchSchedule();
	        branchSchedule6.setWeekDay(AppConstant.WeekDay.SATURDAY.getValue());
	        if (null!=branchScheduleDto.getOpenTimeSat())

	        	branchSchedule6.setOpenTime(branchScheduleDto.getOpenTimeSat());
	        
	        if (null!=branchScheduleDto.getCloseTimeSat())
	        	branchSchedule6.setCloseTime(branchScheduleDto.getCloseTimeSat());
	        branchScheduleList.add(branchSchedule6);
		}else{
			BranchSchedule branchSchedule= new BranchSchedule();
			branchSchedule.setWeekDay(AppConstant.WeekDay.SATURDAY.getValue());
	        branchSchedule.setCloseTime("closed");
	        branchSchedule.setOpenTime("closed");
	        branchScheduleList.add(branchSchedule);
		}   
        
		return branchScheduleList;
	}

	public BranchScheduleDto prepareDto(List<BranchSchedule> branchScheduleList) {
		
		BranchScheduleDto branchScheduleDto=new BranchScheduleDto();
		
		for(BranchSchedule branchSchedule : branchScheduleList){
			
			if(branchSchedule.getWeekDay() == 0){
				branchScheduleDto.setOpenTimeSun(branchSchedule.getOpenTime());
				branchScheduleDto.setCloseTimeSun(branchSchedule.getCloseTime());
			}
			if(branchSchedule.getWeekDay() == 1){
				branchScheduleDto.setOpenTimeMon(branchSchedule.getOpenTime());
				branchScheduleDto.setCloseTimeMon(branchSchedule.getCloseTime());
			}
			if(branchSchedule.getWeekDay() == 2){
				branchScheduleDto.setOpenTimeTue(branchSchedule.getOpenTime());
				branchScheduleDto.setCloseTimeTue(branchSchedule.getCloseTime());
			}
			if(branchSchedule.getWeekDay() == 3){
				branchScheduleDto.setOpenTimeWed(branchSchedule.getOpenTime());
				branchScheduleDto.setCloseTimeWed(branchSchedule.getCloseTime());
			}
			if(branchSchedule.getWeekDay() == 4){
				branchScheduleDto.setOpenTimeThr(branchSchedule.getOpenTime());
				branchScheduleDto.setCloseTimeThr(branchSchedule.getCloseTime());
			}
			if(branchSchedule.getWeekDay() == 5){
				branchScheduleDto.setOpenTimeFri(branchSchedule.getOpenTime());
				branchScheduleDto.setCloseTimeFri(branchSchedule.getCloseTime());
			}
			if(branchSchedule.getWeekDay() == 6){
				branchScheduleDto.setOpenTimeSat(branchSchedule.getOpenTime());
				branchScheduleDto.setCloseTimeSat(branchSchedule.getCloseTime());
			}
		
		}
		return branchScheduleDto;
	}

	private Object Integer(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
