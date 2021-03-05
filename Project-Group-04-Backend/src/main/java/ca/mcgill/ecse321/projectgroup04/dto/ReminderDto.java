package ca.mcgill.ecse321.projectgroup04.dto;

import java.sql.Date;
import java.sql.Time;

public class ReminderDto {
	private Long reminderId;
	private String message;
	private Date date;
	private Time time;
	private CustomerDto customer;
	
	public ReminderDto(String aMessage,Date aDate,Time aTime) {
		message=aMessage;
		date=aDate;
		time=aTime;
	}

}
