package ca.mcgill.ecse321.projectgroup04.dto;

import java.sql.Date;
import java.sql.Time;

public class TimeSlotDto {

	private Time startTime, endTime;
	private Date startDate, endDate;
	private Integer garageSpot;
	private Long timeSlotId;

	public TimeSlotDto() {
	}

	public TimeSlotDto(Time startTime, Time endTime, Date startDate, Date endDate, Integer agarageSpot) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.startDate = startDate;
		this.endDate = endDate;
		this.garageSpot = agarageSpot;
	}

	public Time getStartTime() {
		return startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Integer getGarageSpot() {
		return garageSpot;
	}

	public Long getTimeSlotId() {
		return timeSlotId;
	}

	public void setId(Long id) {
		this.timeSlotId = id;
	}

}