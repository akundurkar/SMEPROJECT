package in.adcast.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class StaffScheduleDto implements Serializable
{
	
	private Integer id;
	private Integer staffId;
	
	
	private String userId;
	private String sunday;
	private String monday;
	private String tuesday;
	private String wednesday;
	private String thrusday;
	private String friday;
	private String saturday;
	private String openTimeSun;
	private String closeTimeSun;
	private String openTimeMon;
	private String closeTimeMon;
	private String openTimeTue;
	private String closeTimeTue;
	private String openTimeWed;
	private String closeTimeWed;
	private String openTimeThr;
	private String closeTimeThr;
	private String openTimeFri;
	private String closeTimeFri;
	private String openTimeSat;
	private String closeTimeSat;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId.trim();
	}
	public String getOpenTimeSun() {
		return openTimeSun;
	}
	public void setOpenTimeSun(String openTimeSun) {
		this.openTimeSun = openTimeSun.trim();
	}
	public String getCloseTimeSun() {
		return closeTimeSun;
	}
	public void setCloseTimeSun(String closeTimeSun) {
		this.closeTimeSun = closeTimeSun.trim();
	}
	public String getOpenTimeMon() {
		return openTimeMon;
	}
	public void setOpenTimeMon(String openTimeMon) {
		this.openTimeMon = openTimeMon.trim();
	}
	public String getCloseTimeMon() {
		return closeTimeMon;
	}
	public void setCloseTimeMon(String closeTimeMon) {
		this.closeTimeMon = closeTimeMon.trim();
	}
	public String getOpenTimeTue() {
		return openTimeTue;
	}
	public void setOpenTimeTue(String openTimeTue) {
		this.openTimeTue = openTimeTue.trim();
	}
	public String getCloseTimeTue() {
		return closeTimeTue;
	}
	public void setCloseTimeTue(String closeTimeTue) {
		this.closeTimeTue = closeTimeTue.trim();
	}
	public String getOpenTimeWed() {
		return openTimeWed;
	}
	public void setOpenTimeWed(String openTimeWed) {
		this.openTimeWed = openTimeWed.trim();
	}
	public String getCloseTimeWed() {
		return closeTimeWed;
	}
	public void setCloseTimeWed(String closeTimeWed) {
		this.closeTimeWed = closeTimeWed.trim();
	}
	public String getOpenTimeThr() {
		return openTimeThr;
	}
	public void setOpenTimeThr(String openTimeThr) {
		this.openTimeThr = openTimeThr.trim();
	}
	public String getCloseTimeThr() {
		return closeTimeThr;
	}
	public void setCloseTimeThr(String closeTimeThr) {
		this.closeTimeThr = closeTimeThr.trim();
	}
	public String getOpenTimeFri() {
		return openTimeFri;
	}
	public void setOpenTimeFri(String openTimeFri) {
		this.openTimeFri = openTimeFri.trim();
	}
	public String getCloseTimeFri() {
		return closeTimeFri;
	}
	public void setCloseTimeFri(String closeTimeFri) {
		this.closeTimeFri = closeTimeFri.trim();
	}
	public String getOpenTimeSat() {
		return openTimeSat;
	}
	public void setOpenTimeSat(String openTimeSat) {
		this.openTimeSat = openTimeSat.trim();
	}
	public String getCloseTimeSat() {
		return closeTimeSat;
	}
	public void setCloseTimeSat(String closeTimeSat) {
		this.closeTimeSat = closeTimeSat.trim();
	}
	public String getSunday() {
		return sunday;
	}
	public void setSunday(String sunday) {
		this.sunday = sunday;
	}
	public String getMonday() {
		return monday;
	}
	public void setMonday(String monday) {
		this.monday = monday;
	}
	public String getTuesday() {
		return tuesday;
	}
	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}
	public String getWednesday() {
		return wednesday;
	}
	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}
	public String getThrusday() {
		return thrusday;
	}
	public void setThrusday(String thrusday) {
		this.thrusday = thrusday;
	}
	public String getFriday() {
		return friday;
	}
	public void setFriday(String friday) {
		this.friday = friday;
	}
	public String getSaturday() {
		return saturday;
	}
	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}
	
	
}
