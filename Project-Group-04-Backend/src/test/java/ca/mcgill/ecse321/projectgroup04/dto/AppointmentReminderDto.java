package ca.mcgill.ecse321.projectgroup04.dto;

import java.sql.Date;
import java.sql.Time;


public class AppointmentReminderDto {
	
	private Long reminderId;
	private CustomerDto customer;
	private Date date;
	private Time time;
	private String message;
	private AppointmentDto appointment;

	public AppointmentReminderDto() {}
	
	public AppointmentReminderDto (Date date, Time time,String message) {
		this.date = date;
		this.time = time;
		this.message = message;
	}

	public Long getReminderId() {
		return reminderId;
	}

	public CustomerDto getCustomer() {
		return customer;
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
	public AppointmentDto getAppointment() {
		return appointment;
	}
	public void setAppointment(AppointmentDto aAppointment) {
		appointment=aAppointment;
	}
	public void setCustomer(CustomerDto aCustomer) {
		customer=aCustomer;
	}

	
}
