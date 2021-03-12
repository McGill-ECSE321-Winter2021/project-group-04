package ca.mcgill.ecse321.projectgroup04.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup04.dto.*;
import ca.mcgill.ecse321.projectgroup04.dto.BusinessHourDto.DayOfWeek;
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
		if (p == null) {
			throw new IllegalArgumentException("There is no such Profile!");
		}
		ProfileDto profileDto = new ProfileDto(p.getAddressLine(), p.getPhoneNumber(), p.getFirstName(),
				p.getLastName(), p.getZipCode(), p.getEmailAddress());
		return profileDto;

	}

	public AdministrativeAssistantDto convertToDto(AdministrativeAssistant a) {
		if (a == null) {
			throw new IllegalArgumentException("There is no such AdministrativeAssistant!");
		}
		AdministrativeAssistantDto administrativeAssistantDto = new AdministrativeAssistantDto(a.getUserId(),
				a.getPassword());
		return administrativeAssistantDto;
	}

	public OwnerDto convertToDto(Owner o) {
		if (o == null) {
			throw new IllegalArgumentException("There is no such Owner!");
		}
		OwnerDto ownerDto = new OwnerDto(o.getUserId(), o.getPassword());
		return ownerDto;
	}

	public AppointmentReminderDto convertToDto(AppointmentReminder appointmentReminder) {
		if (appointmentReminder == null) {
			throw new IllegalArgumentException("There is no such AppointmentReminder!");
		}
		AppointmentReminderDto appointmentReminderDto = new AppointmentReminderDto(appointmentReminder.getDate(),
				appointmentReminder.getTime(), appointmentReminder.getMessage());
		return appointmentReminderDto;

	}

	public ReceiptDto convertToDto(Receipt r) {
		if (r == null) {
			throw new IllegalArgumentException("There is no such Receipt!");
		}
		ReceiptDto receiptDto = new ReceiptDto(r.getTotalPrice());
		return receiptDto;
	}

	public TimeSlotDto convertToDto(TimeSlot timeSlot) {
		if (timeSlot == null) {
			throw new IllegalArgumentException("There is no such TimeSlot!");
		}
		TimeSlotDto timeSlotDto = new TimeSlotDto(timeSlot.getStartTime(), timeSlot.getEndTime(),
				timeSlot.getStartDate(), timeSlot.getEndDate(), timeSlot.getGarageSpot());
		return timeSlotDto;
	}

	public GarageTechnicianDto convertToDto(GarageTechnician garageTechnician) {
		if (garageTechnician == null) {
			throw new IllegalArgumentException("There is no such GarageTechnician!");
		}
		GarageTechnicianDto garageTechnicianDto = new GarageTechnicianDto(garageTechnician.getName());
		return garageTechnicianDto;

	}

	public BookableServiceDto convertToDto(BookableService bookableService) {
		if (bookableService == null) {
			throw new IllegalArgumentException("There is no such BookableService!");
		}
		BookableServiceDto bookableServiceDto = new BookableServiceDto(bookableService.getDuration(),
				bookableService.getPrice(), bookableService.getName());
		return bookableServiceDto;
	}

	/**
	 * 
	 * @param c is a Car -> CarDto
	 * @return carDto that we will convert
	 */
	private CarDto convertToDto(Car c) {
		if (c == null) {
			throw new IllegalArgumentException("There is no such Car!");
		}
		CarDto carDto = new CarDto(c.getModel(), c.getColor(), c.getYear());
		return carDto;
	}

	/**
	 * 
	 * @param c to get the reminders as Reminder, convert them to REminderDto
	 * @return List of ReminderDto
	 */

	private List<ReminderDto> createRemindersDtosForCustomer(Customer c) {
		List<Reminder> allReminders = c.getReminders();
		List<ReminderDto> reminders = new ArrayList<>();
		for (Reminder r : allReminders) {
			reminders.add(convertToDto(r));
		}
		return reminders;
	}

	/**
	 * 
	 * @param r is a Reminder -> ReminderDto
	 * @return Converted to ReminderDto
	 */

	private ReminderDto convertToDto(Reminder r) {
		if (r == null) {
			throw new IllegalArgumentException("There is no such Reminder!");
		}
		ReminderDto reminderDto = new ReminderDto(r.getMessage(), r.getDate(), r.getTime());
		return reminderDto;
	}

	/**
	 * 
	 * @param c    to get the reminders as Reminder
	 * @param cDto
	 * @return
	 */

	private List<AppointmentDto> createAppointmentsDtosForCustomer(Customer c, CustomerDto cDto) {
		List<Appointment> allAppointments = service.getAppointmentsByCustomer(c);
		List<AppointmentDto> appointments = new ArrayList<>();
		for (Appointment a : allAppointments) {
			AppointmentDto appointmentDto = new AppointmentDto();
			appointmentDto.setCustomer(cDto);
			appointmentDto.setGarageTechnician(convertToDto(a.getTechnician()));
			appointmentDto.setReceipt(convertToDto(a.getReceipt()));
			appointmentDto.setBookableService(convertToDto(a.getBookableServices()));
			appointmentDto.setReminder(convertToDto(a.getReminder()));
			appointmentDto.setTimeSlot(convertToDto(a.getTimeSlot()));
			appointments.add(appointmentDto);
		}
		return appointments;
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
		return appointmentDto;
	}

	/**
	 * 
	 * @param c is a Customer -> CustomerDto
	 * @return CustomerDto that we will convert
	 */
	private CustomerDto convertToDto(Customer c) {
		if (c == null) {
			throw new IllegalArgumentException("There is no such Customer!");
		}
		CustomerDto customerDto = new CustomerDto(c.getUserId(), c.getUserId());
		customerDto.setProfile(convertToDto(c.getCustomerProfile()));
		customerDto.setCar(convertToDto(c.getCar()));
		// customerDto.setReminders(createRemindersDtosForCustomer(c));

		return customerDto;
	}

	/**
	 * @param fieldTechnician is an FieldTechnician -> FieldTechnicianDto
	 * @return Converted FieldTechnicianDto
	 */

	private FieldTechnicianDto convertToDto(FieldTechnician fieldTechnician) {
		if (fieldTechnician == null) {
			throw new IllegalArgumentException("There is no such Field Technician!");
		}

		FieldTechnicianDto fieldTechnicianDto = new FieldTechnicianDto(fieldTechnician.getName());
		return fieldTechnicianDto;

	}

	/**
	 * @param es is an EmergencyService -> EmergencyServiceDto
	 * @return Converted EmergencyServiceDto
	 */

	private EmergencyServiceDto convertToDto(EmergencyService es) {
		if (es == null) {
			throw new IllegalArgumentException("There is no such Emergency Service!");
		}

		EmergencyServiceDto emergencyServiceDto = new EmergencyServiceDto();
		emergencyServiceDto.setName(es.getName());
		emergencyServiceDto.setPrice(es.getPrice());
		emergencyServiceDto.setLocation(es.getLocation());
		emergencyServiceDto.setFieldTechnician(convertToDto(es.getTechnician()));

		return emergencyServiceDto;

	}

	private DayOfWeek convertToDto(String dow) {
		if (dow == null) {
			throw new IllegalArgumentException("There is no such day of the week!");
		}

		DayOfWeek dayOfWeek = null;

		if (dow.equals("Monday")) {
			dayOfWeek = DayOfWeek.Monday;
		} else if (dow.equals("Tuesday")) {
			dayOfWeek = DayOfWeek.Tuesday;
		} else if (dow.equals("Wednseday")) {
			dayOfWeek = DayOfWeek.Wednesday;
		} else if (dow.equals("Thursday")) {
			dayOfWeek = DayOfWeek.Thursday;
		} else if (dow.equals("Friday")) {
			dayOfWeek = DayOfWeek.Friday;
		} else if (dow.equals("Saturday")) {
			dayOfWeek = DayOfWeek.Saturday;
		} else if (dow.equals("Sunday")) {
			dayOfWeek = DayOfWeek.Saturday;
		}

		return dayOfWeek;
	}

	public BusinessHourDto convertToDto(BusinessHour b) {
		if (b == null) {
			throw new IllegalArgumentException("There is no such BusinessHour!");
		}
		BusinessHourDto businessHourDto = new BusinessHourDto(convertToDto(b.getDayOfWeek().toString()),
				b.getStartTime(), b.getEndTime());

		return businessHourDto;
	}

	/**
	 * 
	 * @param b
	 * @return
	 */

	private List<TimeSlotDto> createTimeSlotsDtosForBusiness(Business b) {
		List<TimeSlot> allTimeSlots = b.getRegular();
		List<TimeSlotDto> TimeSlots = new ArrayList<>();
		for (TimeSlot t : allTimeSlots) {
			TimeSlots.add(convertToDto(t));
		}
		return TimeSlots;
	}

	/**
	 * 
	 * @param bh
	 * @return
	 */

	private List<BusinessHourDto> createBusinessHoursDtosForBusiness(Business b) {
		List<BusinessHour> allBusinessHours = b.getBusinessHours();
		List<BusinessHourDto> BusinessHours = new ArrayList<>();
		for (BusinessHour bh : allBusinessHours) {
			BusinessHours.add(convertToDto(bh));
		}
		return BusinessHours;
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	private BusinessDto convertToDto(Business b) {
		if (b == null) {
			throw new IllegalArgumentException("There is no such business!");
		}

		BusinessDto businessDto = new BusinessDto(b.getName(), b.getAddress(), b.getPhoneNumber(), b.getEmailAddress(),
				createBusinessHoursDtosForBusiness(b), createTimeSlotsDtosForBusiness(b));
		return businessDto;
	}

	/**
	 * 
	 * @param cr
	 * @return
	 */

	private CheckupReminderDto convertToDto(CheckupReminder cr) {
		if (cr == null) {
			throw new IllegalArgumentException("There is no such checkup reminder!");
		}

		CheckupReminderDto checkupReminderDto = new CheckupReminderDto(cr.getDate(), cr.getTime(), cr.getMessage());

		return checkupReminderDto;
	}

}