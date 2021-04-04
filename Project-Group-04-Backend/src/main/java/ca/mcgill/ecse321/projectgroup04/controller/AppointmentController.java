package ca.mcgill.ecse321.projectgroup04.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup04.dto.*;
import ca.mcgill.ecse321.projectgroup04.dto.BusinessHourDto.DayOfWeek;
import ca.mcgill.ecse321.projectgroup04.model.*;

import ca.mcgill.ecse321.projectgroup04.service.AppointmentService;


@CrossOrigin(origins = "*")
@RestController
public class AppointmentController {

	@Autowired
	private AppointmentService appService;

    
    private ProfileDto convertToDto(Profile profile) {
		if (profile == null) {
			throw new IllegalArgumentException("There is no such Profile!");
		}
		ProfileDto profileDto = new ProfileDto(profile.getAddressLine(), profile.getPhoneNumber(),
				profile.getFirstName(), profile.getLastName(), profile.getZipCode(), profile.getEmailAddress());
		profileDto.setProfileId(profile.getProfileId());
		return profileDto;

	}

	public AdministrativeAssistantDto convertToDto(AdministrativeAssistant administrativeAssistant) {
		if (administrativeAssistant == null) {
			throw new IllegalArgumentException("There is no such AdministrativeAssistant!");
		}
		AdministrativeAssistantDto administrativeAssistantDto = new AdministrativeAssistantDto(
				administrativeAssistant.getUserId(), administrativeAssistant.getPassword());
		administrativeAssistantDto.setId(administrativeAssistant.getId());
		return administrativeAssistantDto;
	}

	public OwnerDto convertToDto(Owner owner) {
		if (owner == null) {
			throw new IllegalArgumentException("There is no such Owner!");
		}
		OwnerDto ownerDto = new OwnerDto(owner.getUserId(), owner.getPassword());
		ownerDto.setId(owner.getId());
		return ownerDto;
	}

	public AppointmentReminderDto convertToDto(AppointmentReminder appointmentReminder) {
		if (appointmentReminder == null) {
			throw new IllegalArgumentException("There is no such AppointmentReminder!");
		}
		AppointmentReminderDto appointmentReminderDto = new AppointmentReminderDto(appointmentReminder.getDate(),
				appointmentReminder.getTime(), appointmentReminder.getMessage());
		appointmentReminderDto.setId(appointmentReminder.getReminderId());
		return appointmentReminderDto;

	}

	public ReceiptDto convertToDto(Receipt receipt) {
		if (receipt == null) {
			throw new IllegalArgumentException("There is no such Receipt!");
		}
		ReceiptDto receiptDto = new ReceiptDto(receipt.getTotalPrice());
		receiptDto.setId(receipt.getReceiptId());
		return receiptDto;
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

	public GarageTechnicianDto convertToDto(GarageTechnician garageTechnician) {
		if (garageTechnician == null) {
			throw new IllegalArgumentException("There is no such GarageTechnician!");
		}
		GarageTechnicianDto garageTechnicianDto = new GarageTechnicianDto(garageTechnician.getName());
		garageTechnicianDto.setTechnicianId(garageTechnician.getTechnicianId());
		return garageTechnicianDto;

	}

	public BookableServiceDto convertToDto(BookableService bookableService) {
		if (bookableService == null) {
			throw new IllegalArgumentException("There is no such BookableService!");
		}
		BookableServiceDto bookableServiceDto = new BookableServiceDto(bookableService.getDuration(),
				bookableService.getPrice(), bookableService.getName());
		bookableServiceDto.setId(bookableService.getServiceId());
		return bookableServiceDto;
	}

	/**
	 * 
	 * @param c is a Car -> CarDto
	 * @return carDto that we will convert
	 */
	private CarDto convertToDto(Car car) {
		if (car == null) {
			throw new IllegalArgumentException("There is no such Car!");
		}
		CarDto carDto = new CarDto(car.getModel(), car.getColor(), car.getYear());
		carDto.setCarId(car.getCarId());
		return carDto;
	}

	/**
	 * 
	 * @param appointment is an Appointment -> AppointmentDto
	 * @return Converted to AppointmentDto
	 */

	private AppointmentDto convertToDto(Appointment appointment) {
		if (appointment == null) {
			throw new IllegalArgumentException("There is no such Appointment!");
		}
		AppointmentDto appointmentDto = new AppointmentDto();
		appointmentDto.setCustomer(convertToDto(appointment.getCustomer()));
		appointmentDto.setGarageTechnician(convertToDto(appointment.getTechnician()));
		appointmentDto.setReceipt(convertToDto(appointment.getReceipt()));
		appointmentDto.setBookableService(convertToDto(appointment.getBookableServices()));
		appointmentDto.setReminder(convertToDto(appointment.getReminder()));
		appointmentDto.setTimeSlot(convertToDto(appointment.getTimeSlot()));
		appointmentDto.setId(appointment.getAppointmentId());
		return appointmentDto;
	}

	/**
	 * 
	 * @param c is a Customer -> CustomerDto
	 * @return CustomerDto that we will convert
	 */
	private CustomerDto convertToDto(Customer customer) {
		if (customer == null) {
			throw new IllegalArgumentException("There is no such Customer!");
		}
		CustomerDto customerDto = new CustomerDto(customer.getUserId(), customer.getPassword());
		customerDto.setProfile(convertToDto(customer.getCustomerProfile()));
		customerDto.setCar(convertToDto(customer.getCar()));
		customerDto.setId(customer.getId());
		return customerDto;
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

	public BusinessHourDto convertToDto(BusinessHour businessHour) {
		if (businessHour == null) {
			throw new IllegalArgumentException("There is no such BusinessHour!");
		}
		BusinessHourDto businessHourDto = new BusinessHourDto(convertToDto(businessHour.getDayOfWeek().toString()),
				businessHour.getStartTime(), businessHour.getEndTime());
		businessHourDto.setId(businessHour.getHourId());

		return businessHourDto;
	}

    @GetMapping(value = { "/appointments", "/appointments/" })
	public List<AppointmentDto> getAllAppointments() {
		List<AppointmentDto> appointmentDtos = new ArrayList<>();
		for (Appointment appointment : appService.getAllAppointments()) {
			appointmentDtos.add(convertToDto(appointment));
		}
		return appointmentDtos;
	}

	@GetMapping(value = { "/appointments/{userId}", "/appointments/{userId}/" })
	public List<AppointmentDto> getCustomerAppointments(@PathVariable("userId") String userId)
			throws IllegalArgumentException {
		List<AppointmentDto> appointmentDtos = new ArrayList<>();
		for (Appointment appointment : appService.getAppointmentsByCustomer(userId)) {
			appointmentDtos.add(convertToDto(appointment));
		}
		return appointmentDtos;
	}

	@GetMapping(value = { "/appointment/{Id}", "/appointment/{Id}/" })
	public AppointmentDto getAppointmentById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
		return convertToDto(appService.getAppointment(Id));
	}

    @PostMapping(value = { "/book/appointment/{userId}", "/book/appointment/{userId}/" })
	public ResponseEntity<?> bookAppointment(@PathVariable("userId") String userId,
			@RequestParam(name ="serviceName") String serviceName,
			@RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") String date,
			@RequestParam(name = "garageSpot") Integer garageSpot,
			@RequestParam(name = "startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") String startTime,
			@RequestParam(name = "garageTechnicianId") Long garageTechnicianId) throws IllegalArgumentException {
	Appointment appointment = null;
				try{
					appointment = appService.bookAppointment(userId, serviceName.trim(), Date.valueOf(LocalDate.parse(date)),
					garageSpot, Time.valueOf(LocalTime.parse(startTime)), garageTechnicianId);
	
	}
	catch(IllegalArgumentException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

	}
	return new ResponseEntity<>(convertToDto(appointment),HttpStatus.CREATED);
				
	}

    @DeleteMapping(value = { "/cancel/appointment/{appointmentId}", "/cancel/appointment/{appointmentId}/" })
	public ResponseEntity<?> cancelAppointmemt(@PathVariable("appointmentId") Long appointmentId)
			throws IllegalArgumentException {
		Appointment appointment = null;
		try {
			appointment = appService.getAppointment(appointmentId);
			appService.deleteAppointment(appointment, null, null);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);

	}


    @GetMapping(value = { "/appointments/date/{date}", "/appointments/date/{date}/" })
	public List<AppointmentDto> getAppointmentsByDate(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-mm-dd") String date) {
		List<AppointmentDto> appointmentsDto = new ArrayList<>();
		for (Appointment appointment : appService.getAppointmentsByDate(Date.valueOf(date))) {
			appointmentsDto.add(convertToDto(appointment));
		}
		return appointmentsDto;
	}

	@GetMapping(value = { "/appointments/next/{userId}", "/appointments/next/{userId}/" })
	public List<AppointmentDto> getFuturAppointments(@PathVariable("userId") String userId) {
		List<AppointmentDto> appointmentsDto = new ArrayList<AppointmentDto>();
		for (Appointment appointment : appService.getNextAppointments(userId)) {
			appointmentsDto.add(convertToDto(appointment));
		}
		return appointmentsDto;
	}

	@GetMapping(value = { "/appointments/next/24Hours/{userId}", "/appointments/next/24Hours/{userId}/" })
	public List<AppointmentDto> get24HoursAppointments(@PathVariable("userId") String userId) {
		List<AppointmentDto> appointmentsDto = new ArrayList<AppointmentDto>();
		for (Appointment appointment : appService.get24hoursAppointment(userId)) {
			appointmentsDto.add(convertToDto(appointment));
		}
		return appointmentsDto;
	}
}
