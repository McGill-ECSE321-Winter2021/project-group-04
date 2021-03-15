package ca.mcgill.ecse321.projectgroup04.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
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
	private ProfileDto convertToDto(Profile profile) {
		if (profile == null) {
			throw new IllegalArgumentException("There is no such Profile!");
		}
		ProfileDto profileDto = new ProfileDto(profile.getAddressLine(), profile.getPhoneNumber(),
				profile.getFirstName(), profile.getLastName(), profile.getZipCode(), profile.getEmailAddress(),
				profile.getProfileId());
		return profileDto;

	}

	public AdministrativeAssistantDto convertToDto(AdministrativeAssistant administrativeAssistant) {
		if (administrativeAssistant == null) {
			throw new IllegalArgumentException("There is no such AdministrativeAssistant!");
		}
		AdministrativeAssistantDto administrativeAssistantDto = new AdministrativeAssistantDto(
				administrativeAssistant.getId(),administrativeAssistant.getUserId(), administrativeAssistant.getPassword());
		return administrativeAssistantDto;
	}

	public OwnerDto convertToDto(Owner owner) {
		if (owner == null) {
			throw new IllegalArgumentException("There is no such Owner!");
		}
		OwnerDto ownerDto = new OwnerDto(owner.getUserId(), owner.getPassword());
		return ownerDto;
	}

	public AppointmentReminderDto convertToDto(AppointmentReminder appointmentReminder) {
		if (appointmentReminder == null) {
			throw new IllegalArgumentException("There is no such AppointmentReminder!");
		}
		AppointmentReminderDto appointmentReminderDto = new AppointmentReminderDto(appointmentReminder.getReminderId(),appointmentReminder.getDate(),
				appointmentReminder.getTime(), appointmentReminder.getMessage());
		return appointmentReminderDto;

	}

	public ReceiptDto convertToDto(Receipt receipt) {
		if (receipt == null) {
			throw new IllegalArgumentException("There is no such Receipt!");
		}
		ReceiptDto receiptDto = new ReceiptDto(receipt.getTotalPrice(), receipt.getReceiptId());
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
		GarageTechnicianDto garageTechnicianDto = new GarageTechnicianDto(garageTechnician.getName(),
				garageTechnician.getTechnicianId());
		return garageTechnicianDto;

	}

	public BookableServiceDto convertToDto(BookableService bookableService) {
		if (bookableService == null) {
			throw new IllegalArgumentException("There is no such BookableService!");
		}
		BookableServiceDto bookableServiceDto = new BookableServiceDto(bookableService.getDuration(),
				bookableService.getPrice(), bookableService.getName(), bookableService.getServiceId());
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
		return carDto;
	}

	/**
	 * 
	 * @param c to get the reminders as Reminder, convert them to REminderDto
	 * @return List of ReminderDto
	 */

	private List<ReminderDto> createRemindersDtosForCustomer(Customer customer) {
		List<Reminder> allReminders = customer.getReminders();
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

	private ReminderDto convertToDto(Reminder reminder) {
		if (reminder == null) {
			throw new IllegalArgumentException("There is no such Reminder!");
		}
		ReminderDto reminderDto = new ReminderDto(reminder.getMessage(), reminder.getDate(), reminder.getTime());
		return reminderDto;
	}

	/**
	 * 
	 * @param c    to get the reminders as Reminder
	 * @param cDto
	 * @return
	 */

	private List<AppointmentDto> createAppointmentsDtosForCustomer(Customer customer, CustomerDto cDto) {
		List<Appointment> allAppointments = service.getAppointmentsByCustomer(customer);
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
	private CustomerDto convertToDto(Customer customer) {
		if (customer == null) {
			throw new IllegalArgumentException("There is no such Customer!");
		}
		CustomerDto customerDto = new CustomerDto(customer.getUserId(), customer.getUserId());
		customerDto.setProfile(convertToDto(customer.getCustomerProfile()));
		customerDto.setCar(convertToDto(customer.getCar()));
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

	private EmergencyServiceDto convertToDto(EmergencyService emergencyService) {
		if (emergencyService == null) {
			throw new IllegalArgumentException("There is no such Emergency Service!");
		}

		EmergencyServiceDto emergencyServiceDto = new EmergencyServiceDto();
		emergencyServiceDto.setName(emergencyService.getName());
		emergencyServiceDto.setPrice(emergencyService.getPrice());
		emergencyServiceDto.setLocation(emergencyService.getLocation());
		emergencyServiceDto.setFieldTechnician(convertToDto(emergencyService.getTechnician()));

		return emergencyServiceDto;

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
		} else if (day.equals("Wednseday")) {
			dayOfWeek = DayOfWeek.Wednesday;
		} else if (day.equals("Thursday")) {
			dayOfWeek = DayOfWeek.Thursday;
		} else if (day.equals("Friday")) {
			dayOfWeek = DayOfWeek.Friday;
		} else if (day.equals("Saturday")) {
			dayOfWeek = DayOfWeek.Saturday;
		} else if (day.equals("Sunday")) {
			dayOfWeek = DayOfWeek.Saturday;
		}

		return dayOfWeek;
	}

	public BusinessHourDto convertToDto(BusinessHour businessHour) {
		if (businessHour == null) {
			throw new IllegalArgumentException("There is no such BusinessHour!");
		}
		BusinessHourDto businessHourDto = new BusinessHourDto(convertToDto(businessHour.getDayOfWeek().toString()),
				businessHour.getStartTime(), businessHour.getEndTime(), businessHour.getHourId());

		return businessHourDto;
	}

	/**
	 * 
	 * @param b
	 * @return
	 */

	private List<TimeSlotDto> createTimeSlotsDtosForBusiness(Business business) {
		List<TimeSlot> allTimeSlots = business.getRegular();
		List<TimeSlotDto> TimeSlots = new ArrayList<>();
		for (TimeSlot timeSlot : allTimeSlots) {
			TimeSlots.add(convertToDto(timeSlot));
		}
		return TimeSlots;
	}

	/**
	 * 
	 * @param bh
	 * @return
	 */

	private List<BusinessHourDto> createBusinessHoursDtosForBusiness(Business business) {
		List<BusinessHour> allBusinessHours = business.getBusinessHours();
		List<BusinessHourDto> BusinessHours = new ArrayList<>();
		for (BusinessHour businessHour : allBusinessHours) {
			BusinessHours.add(convertToDto(businessHour));
		}
		return BusinessHours;
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	private BusinessDto convertToDto(Business business) {
		if (business == null) {
			throw new IllegalArgumentException("There is no such business!");
		}

		BusinessDto businessDto = new BusinessDto(business.getName(), business.getAddress(), business.getPhoneNumber(),
				business.getEmailAddress(), createBusinessHoursDtosForBusiness(business),
				createTimeSlotsDtosForBusiness(business), business.getId());
		return businessDto;
	}

	/**
	 * 
	 * @param cr
	 * @return
	 */

	private CheckupReminderDto convertToDto(CheckupReminder checkupReminder) {
		if (checkupReminder == null) {
			throw new IllegalArgumentException("There is no such checkup reminder!");
		}

		CheckupReminderDto checkupReminderDto = new CheckupReminderDto(checkupReminder.getDate(),
				checkupReminder.getTime(), checkupReminder.getMessage(), checkupReminder.getReminderId());

		return checkupReminderDto;
	}

	@GetMapping(value = { "/profiles", "/profiles/" })
	public List<ProfileDto> getAllProfiles() {
		List<ProfileDto> profileDtos = new ArrayList<>();
		for (Profile profile : service.getAllProfiles()) {
			profileDtos.add(convertToDto(profile));
		}
		return profileDtos;
	}

	@GetMapping(value = { "/profiles/{Id}", "/profiles/{Id}/" })
	public ProfileDto getProfileById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
		return convertToDto(service.getProfile(Id));
	}

	@GetMapping(value = { "/appointments", "/appointments/" })
	public List<AppointmentDto> getAllAppointments() {
		List<AppointmentDto> appointmentDtos = new ArrayList<>();
		for (Appointment appointment : service.getAllAppointments()) {
			appointmentDtos.add(convertToDto(appointment));
		}
		return appointmentDtos;
	}

	@GetMapping(value = { "/appointments/{Id}", "/appointments/{Id}/" })
	public AppointmentDto getAppointmentById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
		return convertToDto(service.getAppointment(Id));
	}

	@GetMapping(value = { "/receipts", "/receipts/" })
	public List<ReceiptDto> getAllReceipts() {
		List<ReceiptDto> receiptDtos = new ArrayList<>();
		for (Receipt receipt : service.getAllReceipts()) {
			receiptDtos.add(convertToDto(receipt));
		}
		return receiptDtos;
	}

	@GetMapping(value = { "/receipts/{Id}", "/receipts/{Id}/" })
	public ReceiptDto getReceiptById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
		return convertToDto(service.getReceipt(Id));
	}

	///////////////////////////////// OWNER//////////////////////////////////////

	@GetMapping(value = { "/owner", "/owner/" })
	public List<OwnerDto> getOwner() { // will return only one owner either ways
		List<OwnerDto> ownerDtos = new ArrayList<>();
		for (Owner owner : service.getOwner()) {
			ownerDtos.add(convertToDto(owner));
		}

		return ownerDtos;
	}

	@GetMapping(value = { "/owner/{Id}", "/owner/{Id}/" })
	public OwnerDto getOwnerById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
		return convertToDto(service.getOwnerByUserId(Id));
	}

	@PostMapping(value = { "register/owner/{userId}", "register/owner/{userId}/" })
	public OwnerDto createOwner(@PathVariable("userId") String userId, @RequestParam String password)
			throws IllegalArgumentException {
		Owner owner = service.createOwner(userId, password);
		OwnerDto ownerDto = convertToDto(owner);
		return ownerDto;
	}

	///////////////////////////////// EMERGENCY
	///////////////////////////////// SERVICE//////////////////////////////////////

	@GetMapping(value = { "/emergencyservice", "/emergencyservice/" })
	public List<EmergencyServiceDto> getAllEmergencyServices() {
		List<EmergencyServiceDto> emergencyServiceDtos = new ArrayList<>();
		for (EmergencyService emergencyService : service.getAllEmergencyServices()) {
			emergencyServiceDtos.add(convertToDto(emergencyService));
		}

		return emergencyServiceDtos;
	}

	@GetMapping(value = { "/emergencyservice/{Id}", "/emergencyservice/{Id}/" })
	public EmergencyServiceDto getEmergencyServiceById(@PathVariable("Id") Long Id) {
		return convertToDto(service.getEmergencyServiceByServiceId(Id));
	}

	@PostMapping(value = { "/emergencyservice/{userId}/{serviceName}", "/emergencyservice/{userId}/{serviceName}/" })
	public EmergencyServiceDto createEmergencyService(@PathVariable("userId") String userId,
			@PathVariable("serviceName") String serviceName, @RequestParam(name = "Price of service") int price,
			@RequestParam(name = "Location") String location,
			@RequestParam(name = "Field Technician") FieldTechnicianDto fieldTechnicianDto)
			throws IllegalArgumentException {
		Customer customer = service.getCustomerByUserId(userId);
		Receipt receipt = service.createReceipt(price);
		FieldTechnician fieldTechnician = service.getFieldTechnicianById(fieldTechnicianDto.getTechnicianId());
		EmergencyService emergencyService = service.createEmergencyService(serviceName, price, location,
				fieldTechnician, customer, receipt);
		return convertToDto(emergencyService);
	}

	//////////////////////////////// FIELD TECHNICIAN
	//////////////////////////////// ///////////////////////////////////

	@GetMapping(value = { "/fieldtechnician", "/fieldtechnician/" })
	public List<FieldTechnicianDto> getFieldTechnicians() {
		List<FieldTechnicianDto> fieldTechnicianDtos = new ArrayList<>();
		for (FieldTechnician fieldTechnician : service.getAllFieldTechnicians()) {
			fieldTechnicianDtos.add(convertToDto(fieldTechnician));
		}

		return fieldTechnicianDtos;
	}

	@GetMapping(value = { "/fieldtechnician/{Id}", "/fieldtechnician/{Id}/" })
	public FieldTechnicianDto getFieldTechnicianById(@PathVariable("Id") Long Id) {
		return convertToDto(service.getFieldTechnicianById(Id));
	}

	@PostMapping(value = { "/register/fieldtechnician/{name}", "/register/fieldtechnician/{name}/" })
	public FieldTechnicianDto createFieldTechnician(@PathVariable("name") String name) throws IllegalArgumentException {
		FieldTechnician fieldTechnician = service.createFieldTechnician(name);
		FieldTechnicianDto fieldTechnicianDto = convertToDto(fieldTechnician);
		return fieldTechnicianDto;
	}

	private FieldTechnician convertToDomainObject(FieldTechnicianDto fieldTechnicianDto) {
		List<FieldTechnician> fieldTechnicians = service.getAllFieldTechnicians();
		for (FieldTechnician fieldTechnician : fieldTechnicians) {
			if (fieldTechnician.getTechnicianId().equals(fieldTechnicianDto.getTechnicianId())) {
				return fieldTechnician;
			}
		}
		return null;
	}

	/////////////////////////////////////////////////////////////////////////////

	@GetMapping(value = { "/business", "/business/" })
	public List<BusinessDto> getBusiness() {
		List<BusinessDto> businessDtos = new ArrayList<>();
		for (Business business : service.getBusiness()) {
			businessDtos.add(convertToDto(business));
		}

		return businessDtos;
	}

	@GetMapping(value = { "/business/{Id}", "/business/{Id}/" })
	public BusinessDto getBusinessById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
		return convertToDto(service.getBusinessById(Id));
	}

	@PostMapping(value = { "/register/business/{name}", "/register/business/{name}/" }) // VERIFY PATH
	public BusinessDto registerBusiness(@PathVariable("name") String name, @RequestParam String address,
			@RequestParam String phoneNumber, @RequestParam String emailAddress,
			@RequestParam List<BusinessHour> businessHours, @RequestParam List<TimeSlot> timeSlots)
			throws IllegalArgumentException {
		Business business = service.createBusiness(name, address, phoneNumber, emailAddress, businessHours, timeSlots);
		BusinessDto businessDto = convertToDto(business);
		return businessDto;
	}

	////////////////////////////////////////////////////////////////////////

	@GetMapping(value = { "/chekupReminder", "/checkupReminder/" })
	public List<CheckupReminderDto> getAllCheckupReminders() {
		List<CheckupReminderDto> checkupReminderDtos = new ArrayList<>();
		for (CheckupReminder checkupReminder : service.getAllCheckupReminder()) {
			checkupReminderDtos.add(convertToDto(checkupReminder));
		}
		return checkupReminderDtos;
	}

	@GetMapping(value = { "/checkupReminder/{Id}", "/checkupReminder/{Id}/" })
	public CheckupReminderDto getCheckupRemindertById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
		return convertToDto(service.getCheckupReminderById(Id));
	}

	@GetMapping(value = { "/checkupReminder/{message}", "/checkupReminder/{message}/" })
	public CheckupReminderDto getCheckupReminderByMessage(@PathVariable("message") String message)
			throws IllegalArgumentException {
		return convertToDto(service.getCheckupReminderByMessage(message));
	}

	@PostMapping(value = { "/create/checkupReminder/{message}", "/create/checkupReminder/{message}/" })
	public CheckupReminderDto createCheckupReminder(@PathVariable("message") String message,
			@RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "DD/MM/YY") Date date,
			@RequestParam(name = "time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") Time time)
			throws IllegalArgumentException {
		CheckupReminder checkupReminder = service.createCheckupReminder(date, time, message);
		CheckupReminderDto checkupReminderDto = convertToDto(checkupReminder);
		return checkupReminderDto;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	@GetMapping(value = { "/businessHours", "/businessHours/" })
	public List<BusinessHourDto> getBusinessHours() {
		List<BusinessHourDto> businessHourDtos = new ArrayList<>();
		for (BusinessHour businessHour : service.getAllBusinessHours()) {
			businessHourDtos.add(convertToDto(businessHour));
		}

		return businessHourDtos;
	}

	@GetMapping(value = { "/businessHour/{Id}", "/businessHour/{Id}/" })
	public BusinessHourDto getBusinessHourById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
		return convertToDto(service.getBusinessHourById(Id));
	}

	// TODO check the DayOfWeek problem
	@PostMapping(value = { "/add/businessHour/{dayOfWeek}", "/add/businessHour/{dayOfWeek}/" }) // VERIFY PATH
	public BusinessHourDto createBusinessHour(@PathVariable("dayOfWeek") String dayOfWeek,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") Time startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") Time endTime)
			throws IllegalArgumentException {
		BusinessHour businessHour = service.createBusinessHour(dayOfWeek, startTime, endTime);
		BusinessHourDto businessHourDto = convertToDto(businessHour);
		return businessHourDto;
	}

	/////////////////////////////////////// ADMINISTRATIVE
	/////////////////////////////////////// ASSISTANT///////////////////////////

	@GetMapping(value = { "/administrativeAssistants", "/administrativeAssistants/" })
	public List<AdministrativeAssistantDto> getAdministrativeAssistants() {
		List<AdministrativeAssistantDto> administrativeAssistantDtos = new ArrayList<>();
		for (AdministrativeAssistant admnistrativeAssistant : service.getAllAdministrativeAssistants()) {
			administrativeAssistantDtos.add(convertToDto(admnistrativeAssistant));
		}
		return administrativeAssistantDtos;
	}

	@GetMapping(value = { "/register/administrativeAsisstant/{Id}", "/register/administrativeAsisstant/{Id}/" })
	public AdministrativeAssistantDto getAdministrativeAssistantById(@PathVariable("Id") Long Id)
			throws IllegalArgumentException {
		return convertToDto(service.getAdministrativeAssistantById(Id));
	}

	@PostMapping(value = { "/register/administrativeAssistant/{userId}", "/register/administrativeAssistant/{userId}/" })
	public AdministrativeAssistantDto createAdministrativeAssistant(@PathVariable("userId") String userId,
			@RequestParam String password) throws IllegalArgumentException {
		AdministrativeAssistant administrativeAssistant = service.createAdministrativeAssistant(userId, password);
		AdministrativeAssistantDto administrativeAssistantDto = convertToDto(administrativeAssistant);
		return administrativeAssistantDto;
	}

	/////////////////////////////////////// APPOINTMENT
	/////////////////////////////////////// REMINDER///////////////////////////
	@GetMapping(value = { "/appointmentReminders", "/appointmentReminders/" })
	public List<AppointmentReminderDto> getAppointmentReminders() {
		List<AppointmentReminderDto> appointmentReminderDtos = new ArrayList<>();
		for (AppointmentReminder appointmentReminder : service.getAllAppointmentReminders()) {
			appointmentReminderDtos.add(convertToDto(appointmentReminder));
		}
		return appointmentReminderDtos;
	}

	@GetMapping(value = { "/appointmentReminder/{Id}", "/appointmentReminder/{Id}/" })
	public AppointmentReminderDto getAppointmentRemindertById(@PathVariable("Id") Long Id)
			throws IllegalArgumentException {
		return convertToDto(service.getAppointmentReminderById(Id));
	}

//	@PostMapping(value = { "/appointmentReminder/{message}", "/appointmentReminder/{message}/" })
//	public AppointmentReminderDto createAppointmentReminder(@PathVariable("message") String message,
//			@RequestParam Date date, @RequestParam Time time) throws IllegalArgumentException {
//		AppointmentReminder appointmentReminder = service.createAppointmentReminder(date, time, message);
//		AppointmentReminderDto appointmentReminderDto = convertToDto(appointmentReminder);
//		return appointmentReminderDto;
//	}

	/////////////////////////////////////// BOOKABLE
	/////////////////////////////////////// SERVICE//////////////////////////////

	@GetMapping(value = { "/bookableServices", "/bookableServices/" })
	public List<BookableServiceDto> getAllBookabableServices() {
		List<BookableServiceDto> bookableServiceDtos = new ArrayList<>();
		for (BookableService bookableService : service.getAllBookableServices()) {
			bookableServiceDtos.add(convertToDto(bookableService));
		}

		return bookableServiceDtos;
	}

	@GetMapping(value = { "/bookableService/{Id}", "/bookableService/{Id}/" })
	public BookableServiceDto getBookableServiceById(@PathVariable("Id") Long Id) {
		return convertToDto(service.getBookableServiceById(Id));
	}

	@PostMapping(value = { "/create/bookableService/{name}", "/create/bookableService/{name}/" })
	public BookableServiceDto createBookableService(@PathVariable("name") String name, @RequestParam int duration,
			@RequestParam int price) {
		BookableService bookableService = service.createBookableService(name, price, duration);
		BookableServiceDto bookableServiceDto = convertToDto(bookableService);
		return bookableServiceDto;
	}

	/////////////////////////////////////// GARAGE
	/////////////////////////////////////// TECHNICIAN////////////////////////////
	@GetMapping(value = { "/garageTechnicians", "/garageTechnicians/" })
	public List<GarageTechnicianDto> getGarageTechnicians() {
		List<GarageTechnicianDto> garageTechnicianDtos = new ArrayList<>();
		for (GarageTechnician garageTechnician : service.getAllGarageTechnicians()) {
			garageTechnicianDtos.add(convertToDto(garageTechnician));
		}

		return garageTechnicianDtos;
	}

	@GetMapping(value = { "/garageTechnician/{Id}", "/garageTechnician/{Id}/" })
	public GarageTechnicianDto getGarageTechnicianById(@PathVariable("Id") Long Id) {
		return convertToDto(service.getGarageTechnicianById(Id));
	}

	@PostMapping(value = { "/garageTechnician/{name}", "/garageTechnician/{name}/" })
	public GarageTechnicianDto createGarageTechnician(@PathVariable("name") String name)
			throws IllegalArgumentException {
		GarageTechnician garageTechnician = service.createGarageTechnician(name);
		GarageTechnicianDto garageTechnicianDto = convertToDto(garageTechnician);
		return garageTechnicianDto;
	}

	@PostMapping(value = { "/appointments/{userId}/{serviceName}", "/appointments/{userId}/{serviceName}/" })
	public AppointmentDto bookAppointment(@PathVariable("userId") String userId,
			@PathVariable("serviceName") String serviceName, @RequestParam Date date, @RequestParam Integer garageSpot,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") Time startTime,
			@RequestParam(name = "Garage Technician") GarageTechnicianDto garageTechnicianDto,
			@RequestParam Long timeSlotId)
			throws IllegalArgumentException {
		Customer customer = service.getCustomerByUserId(userId);
		BookableService bookableService = service.getBookableServiceByServiceName(serviceName);

		java.sql.Time myTimeEnd = startTime;
		LocalTime localTimeEnd = myTimeEnd.toLocalTime();
		localTimeEnd = localTimeEnd.plusMinutes(bookableService.getDuration());
		java.sql.Time endTime = java.sql.Time.valueOf(localTimeEnd);
		for (Appointment appointment : service.getAppointmentsByDate(date)) {
			if (service.isOverlap(appointment.getTimeSlot(), startTime, endTime, garageSpot)) {
				throw new IllegalArgumentException("This attempted booking overlaps with another!");
			}
		}
		TimeSlot timeSlot = service.createTimeSlot(timeSlotId, startTime, endTime, date, date, garageSpot);
		Receipt receipt = service.createReceipt(bookableService.getPrice());
		GarageTechnician garageTechnician = service.getGarageTechnicianById(garageTechnicianDto.getTechnicianId());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date); // convert your date to Calendar object
		int daysToDecrement = -1;
		cal.add(Calendar.DATE, daysToDecrement);
		date = (Date) cal.getTime(); // again get back your date object
		AppointmentReminder appReminder = service.createAppointmentReminder(date, startTime,
				"You have an appointment in 24hours");
		Appointment appointment = service.createAppointment(customer, bookableService, garageTechnician, timeSlot,
				appReminder, receipt, null);
		return convertToDto(appointment);
	}

	private GarageTechnician convertToDomainObject(GarageTechnicianDto garageTechnicianDto) {
		List<GarageTechnician> garageTechnicians = service.getAllGarageTechnicians();
		for (GarageTechnician garageTechnician : garageTechnicians) {
			if (garageTechnician.getTechnicianId().equals(garageTechnicianDto.getTechnicianId())) {
				return garageTechnician;
			}
		}
		return null;
	}

	@PostMapping(value = { "profiles/{first}/{last}", "profiles/{first}/{last}/" })
	public ProfileDto createProfile(@PathVariable("first") String firstName, @PathVariable("last") String lastName,
			@RequestParam(name = "Email Address") String emailAddress,
			@RequestParam(name = "Phone Number") String phoneNumber,
			@RequestParam(name = "Address Line") String addressLine, @RequestParam(name = "Zip Code") String zipCode)
			throws IllegalArgumentException {
		Profile profile = service.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode, emailAddress);
		return convertToDto(profile);
	}

	@GetMapping(value = { "profiles/{first}/{last}", "profiles/{first}/{last}/" })
	public ProfileDto getProfileByFirstAndLast(@PathVariable("first") String firstName,
			@PathVariable("last") String lastName) throws IllegalArgumentException {
		return convertToDto(service.getProfileByFirstAndLast(firstName, lastName));
	}
	
	/////////////////////////////////////// CUSTOMER///////////////////////////

	@GetMapping(value = { "/customers", "/customers/" })
	public List<CustomerDto> getCustomers() {
		List<CustomerDto> customerDtos = new ArrayList<>();
		for (Customer customer : service.getAllCustomers()) {
			customerDtos.add(convertToDto(customer));
		}
		return customerDtos;
	}

	@GetMapping(value = { "/register/customer/{Id}", "/register/customer/{Id}/" })
	public CustomerDto getCustomerById(@PathVariable("Id") Long Id)
			throws IllegalArgumentException {
		return convertToDto(service.getCustomerById(Id));
	}

	@PostMapping(value = { "/customer/{userId}", "/customer/{userId}/" })
	public CustomerDto createCustomer(@PathVariable("userId") String userId,
			@RequestParam String password,
			@RequestParam List<Reminder> reminders,
			@RequestParam Car car,
			@RequestParam Profile profile) throws IllegalArgumentException {
		Customer customer = service.createCustomer(userId, password, reminders, car, profile);
		CustomerDto customerDto = convertToDto(customer);
		return customerDto;
	}

	/////////////////////////////////////// CAR///////////////////////////

	@GetMapping(value = { "/cars", "/cars/" })
	public List<CarDto> getCars() {
		List<CarDto> carDtos = new ArrayList<>();
		for (Car car : service.getAllCars()) {
			carDtos.add(convertToDto(car));
		}
		return carDtos;
	}

	@GetMapping(value = { "/register/car/{Id}", "/register/car/{Id}/" })
	public CarDto getCarByCarId(@PathVariable("Id") Long Id)
			throws IllegalArgumentException {
		return convertToDto(service.getCarByCarId(Id));
	}

	@PostMapping(value = { "/car/{carId}", "/car/{carId}/" })
	public CarDto createCar(@PathVariable("carId") Long carId,
			@RequestParam String model,
			@RequestParam String year,
			@RequestParam String color) throws IllegalArgumentException {
		Car car1 = service.createCar(carId, model, year, color);
		CarDto carDto = convertToDto(car1);
		return carDto;
	}

	/////////////////////////////////////// TIMESLOT///////////////////////////

	@GetMapping(value = { "/timeSlots", "/timeSlots/" })
	public List<TimeSlotDto> getTimeSlot() {
		List<TimeSlotDto> timeSlotDtos = new ArrayList<>();
		for (TimeSlot timeSlot : service.getAllTimeSlots()) {
			timeSlotDtos.add(convertToDto(timeSlot));
		}
		return timeSlotDtos;
	}

	@GetMapping(value = { "/register/timeSlot/{Id}", "/register/timeSlot/{Id}/" })
	public TimeSlotDto getTimeSlotByTimeSlotId(@PathVariable("Id") Long Id)
			throws IllegalArgumentException {
		return convertToDto(service.getTimeSlotByTimeSlotId(Id));
	}

	@PostMapping(value = { "/timeSlot/{timeSlotId}", "/timeSlot/{timeSlotId}/" })
	public TimeSlotDto createTimeSlot(@PathVariable("timeSlotId") Long timeSlotId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "YYYY/MM/DD") Date startDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "YYYY/MM/DD") Date endDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") Time startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") Time endTime,
			@RequestParam Integer garageSpot) throws IllegalArgumentException {
		TimeSlot timeSlot = service.createTimeSlot(timeSlotId, startTime, endTime, startDate, endDate, garageSpot);
		TimeSlotDto timeSlotDtos = convertToDto(timeSlot);
		return timeSlotDtos;
	}
	
	@PostMapping(value = {"/appointments/{appointmentId}/cancel","/appointments/{appointmentId}/cancel/"})
	public void cancelAppointmemt(@PathVariable("appointmentId") Long appointmentId) throws IllegalArgumentException{
		Date today = new Date(0);
		long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;
		Appointment appointment =service.getAppointment(appointmentId);
		if((appointment.getTimeSlot().getStartDate().getTime()-today.getTime())>MILLIS_PER_DAY) {
			service.deleteAppointment(appointment);
		}
        

	}

}