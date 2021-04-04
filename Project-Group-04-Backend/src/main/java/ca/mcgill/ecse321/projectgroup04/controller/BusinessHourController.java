package ca.mcgill.ecse321.projectgroup04.controller;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup04.dto.*;
import ca.mcgill.ecse321.projectgroup04.dto.BusinessHourDto.DayOfWeek;
import ca.mcgill.ecse321.projectgroup04.model.*;
import ca.mcgill.ecse321.projectgroup04.service.BusinessHourService;
import ca.mcgill.ecse321.projectgroup04.service.BusinessService;


@CrossOrigin(origins = "*")
@RestController
public class BusinessHourController {


	@Autowired
	private BusinessHourService businessHourService;
	@Autowired
	private BusinessService businessService;


    private DayOfWeek convertToDto(String day) {
		if (day == null) {
			throw new IllegalArgumentException("There is no such day of the week!");
		}

		DayOfWeek dayOfWeek = null;

		if (day.equals("Monday")) {
			dayOfWeek = DayOfWeek.Monday;
		} else if (day.equals("Tuesday")) {
			dayOfWeek = DayOfWeek.Tuesday;
		} else if (day.equals("Wednesday")) {
			dayOfWeek = DayOfWeek.Wednesday;
		} else if (day.equals("Thursday")) {
			dayOfWeek = DayOfWeek.Thursday;
		} else if (day.equals("Friday")) {
			dayOfWeek = DayOfWeek.Friday;
		} else if (day.equals("Saturday")) {
			dayOfWeek = DayOfWeek.Saturday;
		} else if (day.equals("Sunday")) {
			dayOfWeek = DayOfWeek.Sunday;
		}

		return dayOfWeek;
	}

	public BusinessHourDto convertToDto(BusinessHour businessHour) {
		if (businessHour == null) {
			throw new IllegalArgumentException("There is no such BusinessHour!");
		}
		BusinessHourDto businessHourDto = new BusinessHourDto(convertToDto(businessHour.getDayOfWeek().toString()),
				businessHour.getStartTime(), businessHour.getEndTime());
		businessHourDto.setId(businessHour.getHourId());

		return businessHourDto;
	}

    @GetMapping(value = { "/businessHours", "/businessHours/" })
	public List<BusinessHourDto> getBusinessHours() {
		List<BusinessHourDto> businessHourDtos = new ArrayList<>();
		for (BusinessHour businessHour : businessHourService.getAllBusinessHours()) {
			businessHourDtos.add(convertToDto(businessHour));
		}

		return businessHourDtos;
	}

	@GetMapping(value = { "/businessHour/{Id}", "/businessHour/{Id}/" })
	public BusinessHourDto getBusinessHourById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
		return convertToDto(businessHourService.getBusinessHourById(Id));
	}

	@PostMapping(value = { "/add/businessHour/{dayOfWeek}", "/add/businessHour/{dayOfWeek}/" }) // VERIFY PATH
	public ResponseEntity<?> createBusinessHour(@PathVariable("dayOfWeek") String dayOfWeek,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "hh:mm") String startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "hh:mm") String endTime)
			throws IllegalArgumentException {
		BusinessHour businessHour = null;
		try {
			 businessHour = businessHourService.createBusinessHour(dayOfWeek,
						Time.valueOf(LocalTime.parse(startTime)), Time.valueOf(LocalTime.parse(endTime)));
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(convertToDto(businessHour),HttpStatus.CREATED);
	}

	@PatchMapping(value = { "/edit/businessHour/{Id}", "/edit/businessHour/{Id}/" })
	public ResponseEntity<?> editBusinessHour(@PathVariable("Id") Long Id, @RequestParam String dayOfWeek,
	@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "hh:mm") String startTime,
	@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "hh:mm") String endTime) {
		BusinessHour businessHour = null;
		try {
			 businessHour = businessHourService.updateBusinessHour(Id, dayOfWeek,
					Time.valueOf(LocalTime.parse(startTime)), Time.valueOf(LocalTime.parse(endTime)));
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(convertToDto(businessHour),HttpStatus.CREATED);

	}

	@DeleteMapping(value = { "/delete/businessHour/{Id}", "/delete/businessHour/{Id}/" })
	public void deleteBusinessHourById(@PathVariable("Id") Long Id, @RequestParam Long businessId) {
		BusinessHour businessHour = businessHourService.getBusinessHourById(Id);
		Business business = businessService.getBusinessById(businessId);
		businessHourService.deleteBusinessHour(businessHour, business);
	}

	@DeleteMapping(value = { "/delete/allBusinessHours/{Id}", "/delete/allBusinessHour/{Id}/" })
	public void deleteAllBusinessHours(@PathVariable("Id") Long Id) {
		Business business = businessService.getBusinessById(Id);
		businessHourService.deleteAllBusinessHours(business);
	}

}
