package ca.mcgill.ecse321.projectgroup04.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup04.dto.*;
import ca.mcgill.ecse321.projectgroup04.model.*;
import ca.mcgill.ecse321.projectgroup04.service.AutoRepairShopSystemService;

@CrossOrigin(origins = "*")
@RestController
public class AutoRepairShopSystemRestController {
	
	@Autowired
	private AutoRepairShopSystemService service;
	
	/**
	 * 
	 * @param p is a Profile -> ProfileDto
	 * @return ProfileDto that we will convert
	 */
	private ProfileDto convertToDto(Profile p) {
		if(p==null) {
			throw new IllegalArgumentException("There is no such Profile!");
		}
		ProfileDto profileDto= new ProfileDto(p.getAddressLine(),p.getPhoneNumber(),
				p.getFirstName(),p.getLastName(),p.getZipCode(),
				p.getEmailAddress());
		return profileDto;
		
	}
	
	/**
	 * 
	 * @param c is a Car -> CarDto
	 * @return carDto that we will convert
	 */
	private CarDto convertToDto(Car c) {
		if(c==null) {
			throw new IllegalArgumentException("There is no such Car!");
		}
		CarDto carDto=new CarDto(c.getModel(),c.getColor(),c.getYear());
		return carDto;
	}
	
	private List<ReminderDto> createRemindersDtosForCustomer(Customer c) {
		List<Reminder> allReminders =service.getCustomerReminders(c);
		List<ReminderDto> reminders = new ArrayList<>();
		for(Reminder r : allReminders) {
			reminders.add(convertToDto(r));
		}
		return reminders;
	}
	
	private ReminderDto convertToDto(Reminder r) {
		if(r==null) {
			throw new IllegalArgumentException("There is no such Car!");
		}
		ReminderDto reminderDto = new ReminderDto(r.getMessage(),r.getDate(),r.getTime());
		return reminderDto;
	}
	
	private List<AppointmentDto> createAppointmentsDtosForCustomer(Customer c) {
		List<Appointment> allAppointments =service.getAppointmentsByCustomer(c);
		List<AppointmentDto> appointments = new ArrayList<>();
		for(Appointment a : allAppointments) {
			appointments.add(convertToDto(a));
		}
		return appointments;
	}
	
	private AppointmentDto convertToDto(Appointment a) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param c is a Customer -> CustomerDto
	 * @return CustomerDto that we will convert
	 */
	private CustomerDto convertToDto(Customer c) {
		if(c==null) {
			throw new IllegalArgumentException("There is no such Customer!");
		}
		CustomerDto customerDto =  new CustomerDto(c.getUserId(),c.getUserId());
		customerDto.setProfile(convertToDto(c.getCustomerProfile()));	
		customerDto.setCar(convertToDto(c.getCar()));
		customerDto.setAppointments(createAppointmentsDtosForCustomer(c));
		customerDto.setReminders(createRemindersDtosForCustomer(c));
		return customerDto;
	}
	
	
	

}
