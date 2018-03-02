package in.adcast.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import in.adcast.common.utils.AppConstant;

import in.adcast.dto.StaffScheduleDto;

import in.adcast.model.StaffSchedule;


@Component
public class StaffScheduleMapper 
{

	public List<StaffSchedule> prepareEntity(StaffScheduleDto staffScheduleDto) {

		List<StaffSchedule> staffScheduleList = new ArrayList<>();
      
		String s1="Open Time", s2="Closed Time";
		
		if(!s1.equals(staffScheduleDto.getOpenTimeSun()) && !s2.equals(staffScheduleDto.getCloseTimeSun())){
			StaffSchedule staffSchedule= new StaffSchedule();
			staffSchedule.setWeekDay(AppConstant.WeekDay.SUNDAY.getValue());
	        if (null!=staffScheduleDto.getOpenTimeSun())
	        	staffSchedule.setDutyStartTime(staffScheduleDto.getOpenTimeSun());
	        
	        if (null!=staffScheduleDto.getCloseTimeSun())
	        	staffSchedule.setDutyEndTime(staffScheduleDto.getCloseTimeSun());
	        staffScheduleList.add(staffSchedule);
		}else{
			StaffSchedule staffSchedule= new StaffSchedule();
			staffSchedule.setWeekDay(AppConstant.WeekDay.SUNDAY.getValue());
			staffSchedule.setDutyEndTime("closed");
			staffSchedule.setDutyStartTime("closed");
			staffScheduleList.add(staffSchedule);
		}
        
		if(!s1.equals(staffScheduleDto.getOpenTimeMon()) && !s2.equals(staffScheduleDto.getCloseTimeMon())){
	        StaffSchedule staffSchedule1= new StaffSchedule();
	        staffSchedule1.setWeekDay(AppConstant.WeekDay.MONDAY.getValue());
	        if (null!=staffScheduleDto.getOpenTimeMon())
	        	staffSchedule1.setDutyStartTime(staffScheduleDto.getOpenTimeMon());
	        
	        if (null!=staffScheduleDto.getCloseTimeMon())
	        	staffSchedule1.setDutyEndTime(staffScheduleDto.getCloseTimeMon());
	        staffScheduleList.add(staffSchedule1);
		}else{
			StaffSchedule staffSchedule= new StaffSchedule();
			staffSchedule.setWeekDay(AppConstant.WeekDay.MONDAY.getValue());
	        staffSchedule.setDutyEndTime("closed");
	        staffSchedule.setDutyStartTime("closed");
	        staffScheduleList.add(staffSchedule);
		}   
        
		if(!s1.equals(staffScheduleDto.getOpenTimeTue()) && !s2.equals(staffScheduleDto.getCloseTimeTue())){
	        StaffSchedule staffSchedule2 =new StaffSchedule();
	        staffSchedule2.setWeekDay(AppConstant.WeekDay.TUESDAY.getValue());
	        if (null!=staffScheduleDto.getOpenTimeTue())
	        	staffSchedule2.setDutyStartTime(staffScheduleDto.getOpenTimeTue());
	        
	        if (null!=staffScheduleDto.getCloseTimeTue())
	        	staffSchedule2.setDutyEndTime(staffScheduleDto.getCloseTimeTue());
	        staffScheduleList.add(staffSchedule2);
		}else{
			StaffSchedule staffSchedule= new StaffSchedule();
			staffSchedule.setWeekDay(AppConstant.WeekDay.TUESDAY.getValue());
	        staffSchedule.setDutyEndTime("closed");
	        staffSchedule.setDutyStartTime("closed");
	        staffScheduleList.add(staffSchedule);
		}   
        
		if(!s1.equals(staffScheduleDto.getOpenTimeWed()) && !s2.equals(staffScheduleDto.getCloseTimeWed())){
	        StaffSchedule staffSchedule3=new StaffSchedule();
	        staffSchedule3.setWeekDay(AppConstant.WeekDay.WEDNESDAY.getValue());
	        if (null!=staffScheduleDto.getOpenTimeWed())
	        	staffSchedule3.setDutyStartTime(staffScheduleDto.getOpenTimeWed());
	        
	        if (null!=staffScheduleDto.getCloseTimeWed())
	        	staffSchedule3.setDutyEndTime(staffScheduleDto.getCloseTimeWed());
	        staffScheduleList.add(staffSchedule3);
		}else{
			StaffSchedule staffSchedule= new StaffSchedule();
			staffSchedule.setWeekDay(AppConstant.WeekDay.WEDNESDAY.getValue());
	        staffSchedule.setDutyEndTime("closed");
	        staffSchedule.setDutyStartTime("closed");
	        staffScheduleList.add(staffSchedule);
		}   
        
		if(!s1.equals(staffScheduleDto.getOpenTimeThr()) && !s2.equals(staffScheduleDto.getCloseTimeThr())){
		    StaffSchedule staffSchedule4=new StaffSchedule();
		    staffSchedule4.setWeekDay(AppConstant.WeekDay.THURSDAY.getValue());
	        if (null!=staffScheduleDto.getOpenTimeThr())
	        	staffSchedule4.setDutyStartTime(staffScheduleDto.getOpenTimeThr());
	        
	        if (null!=staffScheduleDto.getCloseTimeThr())
	        	staffSchedule4.setDutyEndTime(staffScheduleDto.getCloseTimeThr());
	        staffScheduleList.add(staffSchedule4);
		}else{
			StaffSchedule staffSchedule= new StaffSchedule();
			staffSchedule.setWeekDay(AppConstant.WeekDay.THURSDAY.getValue());
			staffSchedule.setDutyEndTime("closed");
			staffSchedule.setDutyStartTime("closed");
			staffScheduleList.add(staffSchedule);
		}   
       
		
		if(!s1.equals(staffScheduleDto.getOpenTimeFri()) && !s2.equals(staffScheduleDto.getCloseTimeFri())){
			StaffSchedule staffSchedule5=new StaffSchedule();
			staffSchedule5.setWeekDay(AppConstant.WeekDay.FRIDAY.getValue());
	        if (null!=staffScheduleDto.getOpenTimeFri())
	        	staffSchedule5.setDutyStartTime(staffScheduleDto.getOpenTimeFri());
	        
	        if (null!=staffScheduleDto.getCloseTimeFri())
	        	staffSchedule5.setDutyEndTime(staffScheduleDto.getCloseTimeFri());
	        staffScheduleList.add(staffSchedule5);
		}else{
			StaffSchedule staffSchedule= new StaffSchedule();
			staffSchedule.setWeekDay(AppConstant.WeekDay.FRIDAY.getValue());
			staffSchedule.setDutyEndTime("closed");
			staffSchedule.setDutyStartTime("closed");
			staffScheduleList.add(staffSchedule);
		}   
        
		if(!s1.equals(staffScheduleDto.getOpenTimeSat()) && !s2.equals(staffScheduleDto.getCloseTimeSat())){
			StaffSchedule staffSchedule6=new StaffSchedule();
			staffSchedule6.setWeekDay(AppConstant.WeekDay.SATURDAY.getValue());
	        if (null!=staffScheduleDto.getOpenTimeSat())

	        	staffSchedule6.setDutyStartTime(staffScheduleDto.getOpenTimeSat());
	        
	        if (null!=staffScheduleDto.getCloseTimeSat())
	        	staffSchedule6.setDutyEndTime(staffScheduleDto.getCloseTimeSat());
	        staffScheduleList.add(staffSchedule6);
		}else{
			StaffSchedule staffSchedule= new StaffSchedule();
			staffSchedule.setWeekDay(AppConstant.WeekDay.SATURDAY.getValue());
			staffSchedule.setDutyEndTime("closed");
			staffSchedule.setDutyStartTime("closed");
			staffScheduleList.add(staffSchedule);
		}   
        
		return staffScheduleList;
	}

	public StaffScheduleDto prepareDto(List<StaffSchedule> staffScheduleList) {
		
		StaffScheduleDto staffScheduleDto=new StaffScheduleDto();
		
		for(StaffSchedule staffSchedule : staffScheduleList){
			
			if(staffSchedule.getWeekDay() == 0){
				staffScheduleDto.setOpenTimeSun(staffSchedule.getDutyStartTime());
				staffScheduleDto.setCloseTimeSun(staffSchedule.getDutyEndTime());
			}
			if(staffSchedule.getWeekDay() == 1){
				staffScheduleDto.setOpenTimeMon(staffSchedule.getDutyStartTime());
				staffScheduleDto.setCloseTimeMon(staffSchedule.getDutyEndTime());
			}
			if(staffSchedule.getWeekDay() == 2){
				staffScheduleDto.setOpenTimeTue(staffSchedule.getDutyStartTime());
				staffScheduleDto.setCloseTimeTue(staffSchedule.getDutyEndTime());
			}
			if(staffSchedule.getWeekDay() == 3){
				staffScheduleDto.setOpenTimeWed(staffSchedule.getDutyStartTime());
				staffScheduleDto.setCloseTimeWed(staffSchedule.getDutyEndTime());
			}
			if(staffSchedule.getWeekDay() == 4){
				staffScheduleDto.setOpenTimeThr(staffSchedule.getDutyStartTime());
				staffScheduleDto.setCloseTimeThr(staffSchedule.getDutyEndTime());
			}
			if(staffSchedule.getWeekDay() == 5){
				staffScheduleDto.setOpenTimeFri(staffSchedule.getDutyStartTime());
				staffScheduleDto.setCloseTimeFri(staffSchedule.getDutyEndTime());
			}
			if(staffSchedule.getWeekDay() == 6){
				staffScheduleDto.setOpenTimeSat(staffSchedule.getDutyStartTime());
				staffScheduleDto.setCloseTimeSat(staffSchedule.getDutyEndTime());
			}
		
		}
		return staffScheduleDto;
	}

	private Object Integer(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
