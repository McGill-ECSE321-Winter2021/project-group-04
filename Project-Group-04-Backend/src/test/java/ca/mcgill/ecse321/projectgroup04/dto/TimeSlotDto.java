package ca.mcgill.ecse321.projectgroup04.dto;

import java.sql.Date;
import java.sql.Time;

public class TimeSlotDto {

	private String timeSlotId;
	private Time startTime, endTime;
	private Date startDate, endDate;
	private Integer garageSpot;

	public TimeSlotDto() {
	}

	public TimeSlotDto(String timeSlotId, Time startTime, Time endTime, Date startDate, Date endDate,
			Integer agarageSpot) {
		this.timeSlotId = timeSlotId;
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

}