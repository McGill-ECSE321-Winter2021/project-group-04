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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup04.dto.*;
import ca.mcgill.ecse321.projectgroup04.model.*;
import ca.mcgill.ecse321.projectgroup04.service.CheckupReminderService;


@CrossOrigin(origins = "*")
@RestController
public class CheckupReminderController {

	@Autowired
	private CheckupReminderService checkupReminderService;

    

    private CheckupReminderDto convertToDto(CheckupReminder checkupReminder) {
		if (checkupReminder == null) {
			throw new IllegalArgumentException("There is no such checkup reminder!");
		}

		CheckupReminderDto checkupReminderDto = new CheckupReminderDto(checkupReminder.getDate(),
				checkupReminder.getTime(), checkupReminder.getMessage());
		checkupReminderDto.setId(checkupReminder.getReminderId());
		return checkupReminderDto;
	}

    @GetMapping(value = { "/checkupReminders", "/checkupReminders/" })
	public List<CheckupReminderDto> getAllCheckupReminders() {
		List<CheckupReminderDto> checkupReminderDtos = new ArrayList<>();
		for (CheckupReminder checkupReminder : checkupReminderService.getAllCheckupReminder()) {
			checkupReminderDtos.add(convertToDto(checkupReminder));
		}
		return checkupReminderDtos;
	}

	@GetMapping(value = { "/checkupReminder/{Id}", "/checkupReminder/{Id}/" })
	public CheckupReminderDto getCheckupRemindertById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
		return convertToDto(checkupReminderService.getCheckupReminderById(Id));
	}

	@PostMapping(value = { "/create/checkupReminder/{message}", "/create/checkupReminder/{message}/" })
	public CheckupReminderDto createCheckupReminder(@PathVariable("message") String message,
			@RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "yyyy-MM-dd") String date,
			@RequestParam(name = "time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "hh:mm:ss") String time)
			throws IllegalArgumentException {
		CheckupReminder checkupReminder = checkupReminderService.createCheckupReminder(
				Date.valueOf(LocalDate.parse(date)), Time.valueOf(LocalTime.parse(time)), message);
		CheckupReminderDto checkupReminderDto = convertToDto(checkupReminder);
		return checkupReminderDto;
	}

	@PatchMapping(value = { "/edit/checkupReminder/{reminderId}", "/edit/checkupReminder/{reminderId}/" })
	public void editCheckupReminder(@PathVariable("reminderId") Long reminderId,
			@RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "yyyy-MM-dd") String date,
			@RequestParam(name = "time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "hh:mm:ss") String time,
			@RequestParam String message) throws IllegalArgumentException {
		// CheckupReminder checkupReminder = service.getCheckupReminderById(reminderId);
		checkupReminderService.editCheckupReminder(reminderId, Date.valueOf(LocalDate.parse(date)),
				Time.valueOf(LocalTime.parse(time)), message);

	}

}
