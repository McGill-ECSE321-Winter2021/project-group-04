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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup04.dto.*;
import ca.mcgill.ecse321.projectgroup04.model.*;
import ca.mcgill.ecse321.projectgroup04.service.TimeSlotService;

@CrossOrigin(origins = "*")
@RestController
public class TimeSlotController {


	@Autowired
	private TimeSlotService timeService;


    public TimeSlotDto convertToDto(TimeSlot timeSlot) {
		if (timeSlot == null) {
			throw new IllegalArgumentException("There is no such TimeSlot!");
		}
		TimeSlotDto timeSlotDto = new TimeSlotDto(timeSlot.getStartTime(), timeSlot.getEndTime(),
				timeSlot.getStartDate(), timeSlot.getEndDate(), timeSlot.getGarageSpot());
		timeSlotDto.setId(timeSlot.getTimeSlotId());
		return timeSlotDto;
	}

    @GetMapping(value = { "/timeSlots", "/timeSlots/" })
	public List<TimeSlotDto> getTimeSlot() {
		List<TimeSlotDto> timeSlotDtos = new ArrayList<>();
		for (TimeSlot timeSlot : timeService.getAllTimeSlots()) {
			timeSlotDtos.add(convertToDto(timeSlot));
		}
		return timeSlotDtos;
	}

	@GetMapping(value = { "/timeSlot/{startDate}/{startTime}", "/timeSlot/{startDate}/{startTime}/" })
	public TimeSlotDto getTimeSlotByStartDateAndStartTime(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "c") String startDate,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") String startTime)
			throws IllegalArgumentException {
		TimeSlot timeSlot = timeService.getTimeSlotByStartDateAndStartTime(Date.valueOf(LocalDate.parse(startDate)),
				Time.valueOf(LocalTime.parse(startTime)));
		if (startDate == null || startTime == null) {
			throw new IllegalArgumentException("No time slot with such start date or start time!");
		}
		return convertToDto(timeSlot);
	}

	@PostMapping(value = { "/add/timeSlot/{startDate}/{startTime}", "/add/timeSlot/{startDate}/{startTime}/" })
	public TimeSlotDto createTimeSlot(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-mm-dd") String startDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-mm-dd") String endDate,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") String startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") String endTime,
			@RequestParam Integer garageSpot) throws IllegalArgumentException {
		TimeSlot timeSlot = timeService.createTimeSlot(Time.valueOf(LocalTime.parse(startTime)),
				Time.valueOf(LocalTime.parse(endTime)), Date.valueOf(LocalDate.parse(startDate)),
				Date.valueOf(LocalDate.parse(endDate)), garageSpot);
		TimeSlotDto timeSlotDtos = convertToDto(timeSlot);
		return timeSlotDtos;
	}
    
}
