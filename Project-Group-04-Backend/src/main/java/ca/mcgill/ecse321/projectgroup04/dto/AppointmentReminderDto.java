package ca.mcgill.ecse321.projectgroup04.dto;

import java.sql.Date;
import java.sql.Time;


public class AppointmentReminderDto {
	
	private Long reminderId;
	private Date date;
	private Time time;
	private String message;
	
	public AppointmentReminderDto() {}
	
	public AppointmentReminderDto (Long reminderId,Date date, Time time,String message) {
		this.date = date;
		this.reminderId = reminderId;
		this.time = time;
		this.message = message;
	}

	public Long getReminderId() {
		return reminderId;
	}



	public Date getDate() {
		return date;
	}

	public Time getTime() {
		return time;
	}

	public String getMessage() {
		return message;
	}


	
}
