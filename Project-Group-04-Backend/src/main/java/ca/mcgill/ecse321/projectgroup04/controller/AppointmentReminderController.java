package ca.mcgill.ecse321.projectgroup04.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup04.dto.*;
import ca.mcgill.ecse321.projectgroup04.model.*;
import ca.mcgill.ecse321.projectgroup04.service.AppointmentReminderService;

@CrossOrigin(origins = "*")
@RestController
public class AppointmentReminderController {

	@Autowired
	private AppointmentReminderService appReminderService;


    
    public AppointmentReminderDto convertToDto(AppointmentReminder appointmentReminder) {
		if (appointmentReminder == null) {
			throw new IllegalArgumentException("There is no such AppointmentReminder!");
		}
		AppointmentReminderDto appointmentReminderDto = new AppointmentReminderDto(appointmentReminder.getDate(),
				appointmentReminder.getTime(), appointmentReminder.getMessage());
		appointmentReminderDto.setId(appointmentReminder.getReminderId());
		return appointmentReminderDto;

	}

    @GetMapping(value = { "/appointmentReminders", "/appointmentReminders/" })
	public List<AppointmentReminderDto> getAppointmentReminders() {
		List<AppointmentReminderDto> appointmentReminderDtos = new ArrayList<>();
		for (AppointmentReminder appointmentReminder : appReminderService.getAllAppointmentReminders()) {
			appointmentReminderDtos.add(convertToDto(appointmentReminder));
		}
		return appointmentReminderDtos;
	}

	@GetMapping(value = { "/appointmentReminder/{Id}", "/appointmentReminder/{Id}/" })
	public AppointmentReminderDto getAppointmentRemindertById(@PathVariable("Id") Long Id)
			throws IllegalArgumentException {
		AppointmentReminder appointmentReminder = appReminderService.getAppointmentReminderById(Id);
		if (appointmentReminder == null) {
			throw new IllegalArgumentException("No appointment reminder with such id!");
		}
		return convertToDto(appointmentReminder);
	}

	@PostMapping(value = { "/create/appointmentReminder/{message}", "/create/appointmentReminder/{message}/" })
	public AppointmentReminderDto createAppointmentReminder(@PathVariable("message") String message,
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-mm-dd") String date,
			@RequestParam("time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "hh:mm:ss") String time)
			throws IllegalArgumentException {
		AppointmentReminder appointmentReminder = appReminderService.createAppointmentReminder(
				Date.valueOf(LocalDate.parse(date)), Time.valueOf(LocalTime.parse(time)), message);
		AppointmentReminderDto appointmentReminderDto = convertToDto(appointmentReminder);
		return appointmentReminderDto;
	}

    @DeleteMapping(value = { "/delete/appointmentReminder/{reminderId}", "/delete/appointmentReminder/{reminderId}/" })
	public void deleteAppointmentReminder(@PathVariable("reminderId") Long reminderId) throws IllegalArgumentException {

		AppointmentReminder appointmentReminder = appReminderService.getAppointmentReminderById(reminderId);
		appReminderService.deleteAppointmentReminder(appointmentReminder);
	}

	@PatchMapping(value = { "/edit/appointmentReminder/{reminderId}", "/edit/appointmentReminder/{reminderId}/" })
	public void editAppointmentReminder(@PathVariable("reminderId") Long reminderId, @RequestParam String message)
			throws IllegalArgumentException {
		AppointmentReminder appointmentReminder = appReminderService.getAppointmentReminderById(reminderId);
		appReminderService.editAppointmentReminder(appointmentReminder, message);

	}
}
