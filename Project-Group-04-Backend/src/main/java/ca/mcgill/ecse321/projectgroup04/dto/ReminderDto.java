package ca.mcgill.ecse321.projectgroup04.dto;

import java.sql.Date;
import java.sql.Time;

public class ReminderDto {
	private Long reminderId;

	public Long getReminderId() {
		return reminderId;
	}

	public String getMessage() {
		return message;
	}

	public Date getDate() {
		return date;
	}

	public Time getTime() {
		return time;
	}

	public void setReminderId(Long reminderId) {
		this.reminderId = reminderId;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	private String message;
	private Date date;
	private Time time;

	public ReminderDto(String aMessage, Date aDate, Time aTime) {
		message = aMessage;
		date = aDate;
		time = aTime;
	}

}