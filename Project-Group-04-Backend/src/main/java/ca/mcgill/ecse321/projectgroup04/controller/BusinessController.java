package ca.mcgill.ecse321.projectgroup04.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import ca.mcgill.ecse321.projectgroup04.service.TimeSlotService;

@CrossOrigin(origins = "*")
@RestController
public class BusinessController {

	@Autowired
	private BusinessHourService businessHourService;
	@Autowired
	private BusinessService businessService;
	@Autowired
	private TimeSlotService timeService;


	private BusinessDto convertToDto(Business business) {
		if (business == null) {
			throw new IllegalArgumentException("There is no such business!");
		}

		BusinessDto businessDto = new BusinessDto(business.getName(), business.getAddress(), business.getPhoneNumber(),
				business.getEmailAddress(), createBusinessHoursDtosForBusiness(business),
				createTimeSlotsDtosForBusiness(business));
		businessDto.setId(business.getId());
		return businessDto;
	}

    private List<BusinessHourDto> createBusinessHoursDtosForBusiness(Business business) {
		List<BusinessHour> allBusinessHours = business.getBusinessHours();
		List<BusinessHourDto> BusinessHours = new ArrayList<>();
		for (BusinessHour businessHour : allBusinessHours) {
			BusinessHours.add(convertToDto(businessHour));
		}
		return BusinessHours;
	}
    private List<TimeSlotDto> createTimeSlotsDtosForBusiness(Business business) {
		List<TimeSlot> allTimeSlots = business.getRegular();
		List<TimeSlotDto> TimeSlots = new ArrayList<>();
		for (TimeSlot timeSlot : allTimeSlots) {
			TimeSlots.add(convertToDto(timeSlot));
		}
		return TimeSlots;
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

    public TimeSlotDto convertToDto(TimeSlot timeSlot) {
		if (timeSlot == null) {
			throw new IllegalArgumentException("There is no such TimeSlot!");
		}
		TimeSlotDto timeSlotDto = new TimeSlotDto(timeSlot.getStartTime(), timeSlot.getEndTime(),
				timeSlot.getStartDate(), timeSlot.getEndDate(), timeSlot.getGarageSpot());
		timeSlotDto.setId(timeSlot.getTimeSlotId());
		return timeSlotDto;
	}

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

    @GetMapping(value = { "/business", "/business/" })
	public List<BusinessDto> getBusiness() {
		List<BusinessDto> businessDtos = new ArrayList<>();
		for (Business business : businessService.getBusiness()) {
			businessDtos.add(convertToDto(business));
		}

		return businessDtos;
	}

	@GetMapping(value = { "/business/{Id}", "/business/{Id}/" })
	public BusinessDto getBusinessById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
		return convertToDto(businessService.getBusinessById(Id));
	}

	@PostMapping(value = { "/register/business/{name}", "/register/business/{name}/" }) // VERIFY PATH
	public ResponseEntity<?> registerBusiness(@PathVariable("name") String name, @RequestParam String address,
			@RequestParam String phoneNumber, @RequestParam String emailAddress) throws IllegalArgumentException {
		List<BusinessHour> businessHour=null;
		List<TimeSlot> timeSlots=null;
		Business business =null;
		try {
			businessHour = businessHourService.getAllBusinessHours();
			timeSlots = timeService.getAllTimeSlots();
			business = businessService.createBusiness(name, address, phoneNumber, emailAddress, businessHour,
					timeSlots);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(convertToDto(business),HttpStatus.CREATED);
	}

	@PatchMapping(value = { "/edit/businessInformation/{Id}", "/edit/businessInformation/{Id}/" })
	public ResponseEntity<?> updateBusinessInfo(@PathVariable("Id") Long Id, @RequestParam String name,
			@RequestParam String address, @RequestParam String phoneNumber, @RequestParam String emailAddress) {
		Business business = null;
		try {
			business = businessService.updateBusinessInformation(Id, name, address, phoneNumber, emailAddress,
					null, null);
		} catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(convertToDto(business),HttpStatus.CREATED);
	}

	@DeleteMapping(value = { "/delete/business/{Id}", "/delete/business/{Id}/" })
	public void deleteBusiness(@PathVariable("Id") Long Id) {
		Business business = businessService.getBusinessById(Id);
		businessService.deleteBusiness(business);
	}


}
