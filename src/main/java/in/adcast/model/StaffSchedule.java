package in.adcast.model;
// Generated 3 Feb, 2018 4:50:11 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * StaffSchedule generated by hbm2java
 */
@Entity
@Table(name = "staff_schedule", catalog = "sme_development")
public class StaffSchedule implements java.io.Serializable {

	private Integer id;
	private ApplicationUser applicationUser;
	private Integer weekDay;
	private String dutyStartTime;
	private String dutyEndTime;

	public StaffSchedule() {
	}

	public StaffSchedule(ApplicationUser applicationUser, Integer weekDay, String dutyStartTime, String dutyEndTime) {
		this.applicationUser = applicationUser;
		this.weekDay = weekDay;
		this.dutyStartTime = dutyStartTime;
		this.dutyEndTime = dutyEndTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staff_id")
	public ApplicationUser getApplicationUser() {
		return this.applicationUser;
	}

	public void setApplicationUser(ApplicationUser applicationUser) {
		this.applicationUser = applicationUser;
	}

	@Column(name = "week_day")
	public Integer getWeekDay() {
		return this.weekDay;
	}

	public void setWeekDay(Integer weekDay) {
		this.weekDay = weekDay;
	}

	@Column(name = "duty_start_time", length = 45)
	public String getDutyStartTime() {
		return this.dutyStartTime;
	}

	public void setDutyStartTime(String dutyStartTime) {
		this.dutyStartTime = dutyStartTime;
	}

	@Column(name = "duty_end_time", length = 45)
	public String getDutyEndTime() {
		return this.dutyEndTime;
	}

	public void setDutyEndTime(String dutyEndTime) {
		this.dutyEndTime = dutyEndTime;
	}

}