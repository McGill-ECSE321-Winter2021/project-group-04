// package ca.mcgill.ecse321.projectgroup04.controller;

// import java.sql.Date;
// import java.sql.Time;
// import java.time.LocalDate;
// import java.time.LocalTime;
// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.format.annotation.DateTimeFormat;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PatchMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RestController;

// import ca.mcgill.ecse321.projectgroup04.dto.*;
// import ca.mcgill.ecse321.projectgroup04.dto.BusinessHourDto.DayOfWeek;
// import ca.mcgill.ecse321.projectgroup04.model.*;
// import ca.mcgill.ecse321.projectgroup04.service.AdministrativeAssistantService;
// import ca.mcgill.ecse321.projectgroup04.service.AppointmentReminderService;
// import ca.mcgill.ecse321.projectgroup04.service.AppointmentService;
// import ca.mcgill.ecse321.projectgroup04.service.BookableServiceService;
// import ca.mcgill.ecse321.projectgroup04.service.BusinessHourService;
// import ca.mcgill.ecse321.projectgroup04.service.BusinessService;
// import ca.mcgill.ecse321.projectgroup04.service.CarService;
// import ca.mcgill.ecse321.projectgroup04.service.CheckupReminderService;
// import ca.mcgill.ecse321.projectgroup04.service.CustomerService;
// import ca.mcgill.ecse321.projectgroup04.service.EmergencyServiceService;
// import ca.mcgill.ecse321.projectgroup04.service.FieldTechnicianService;
// import ca.mcgill.ecse321.projectgroup04.service.GarageTechnicianService;
// import ca.mcgill.ecse321.projectgroup04.service.LogInLogOutService;
// import ca.mcgill.ecse321.projectgroup04.service.OwnerService;
// import ca.mcgill.ecse321.projectgroup04.service.ProfileService;
// import ca.mcgill.ecse321.projectgroup04.service.ReceiptService;
// import ca.mcgill.ecse321.projectgroup04.service.ReminderService;
// import ca.mcgill.ecse321.projectgroup04.service.TimeSlotService;

// @CrossOrigin(origins = "*")
// @RestController
// public class AutoRepairShopSystemRestController {

// 	@Autowired
// 	private AdministrativeAssistantService adminService;
// 	@Autowired
// 	private AppointmentReminderService appReminderService;
// 	@Autowired
// 	private AppointmentService appService;
// 	@Autowired
// 	private BookableServiceService bookService;
// 	@Autowired
// 	private BusinessHourService businessHourService;
// 	@Autowired
// 	private BusinessService businessService;
// 	@Autowired
// 	private CarService carService;
// 	@Autowired
// 	private CheckupReminderService checkupReminderService;
// 	@Autowired
// 	private CustomerService customerService;
// 	@Autowired
// 	private EmergencyServiceService emergencyServiceService;
// 	@Autowired
// 	private FieldTechnicianService fieldService;
// 	@Autowired
// 	private GarageTechnicianService garageService;
// 	@Autowired
// 	private LogInLogOutService logService;
// 	@Autowired
// 	private OwnerService ownerService;
// 	@Autowired
// 	private ProfileService profileService;
// 	@Autowired
// 	private ReceiptService receiptService;
// 	@Autowired
// 	private TimeSlotService timeService;
// 	@Autowired
// 	private ReminderService reminderService;

// 	/**
// 	 * 
// 	 * @param p is a Profile -> ProfileDto
// 	 * @return ProfileDto that we will convert
// 	 */
// 	private ProfileDto convertToDto(Profile profile) {
// 		if (profile == null) {
// 			throw new IllegalArgumentException("There is no such Profile!");
// 		}
// 		ProfileDto profileDto = new ProfileDto(profile.getAddressLine(), profile.getPhoneNumber(),
// 				profile.getFirstName(), profile.getLastName(), profile.getZipCode(), profile.getEmailAddress());
// 		profileDto.setProfileId(profile.getProfileId());
// 		return profileDto;

// 	}

// 	public AdministrativeAssistantDto convertToDto(AdministrativeAssistant administrativeAssistant) {
// 		if (administrativeAssistant == null) {
// 			throw new IllegalArgumentException("There is no such AdministrativeAssistant!");
// 		}
// 		AdministrativeAssistantDto administrativeAssistantDto = new AdministrativeAssistantDto(
// 				administrativeAssistant.getUserId(), administrativeAssistant.getPassword());
// 		administrativeAssistantDto.setId(administrativeAssistant.getId());
// 		return administrativeAssistantDto;
// 	}

// 	public OwnerDto convertToDto(Owner owner) {
// 		if (owner == null) {
// 			throw new IllegalArgumentException("There is no such Owner!");
// 		}
// 		OwnerDto ownerDto = new OwnerDto(owner.getUserId(), owner.getPassword());
// 		ownerDto.setId(owner.getId());
// 		return ownerDto;
// 	}

// 	public AppointmentReminderDto convertToDto(AppointmentReminder appointmentReminder) {
// 		if (appointmentReminder == null) {
// 			throw new IllegalArgumentException("There is no such AppointmentReminder!");
// 		}
// 		AppointmentReminderDto appointmentReminderDto = new AppointmentReminderDto(appointmentReminder.getDate(),
// 				appointmentReminder.getTime(), appointmentReminder.getMessage());
// 		appointmentReminderDto.setId(appointmentReminder.getReminderId());
// 		return appointmentReminderDto;

// 	}

// 	public ReceiptDto convertToDto(Receipt receipt) {
// 		if (receipt == null) {
// 			throw new IllegalArgumentException("There is no such Receipt!");
// 		}
// 		ReceiptDto receiptDto = new ReceiptDto(receipt.getTotalPrice());
// 		receiptDto.setId(receipt.getReceiptId());
// 		return receiptDto;
// 	}

// 	public TimeSlotDto convertToDto(TimeSlot timeSlot) {
// 		if (timeSlot == null) {
// 			throw new IllegalArgumentException("There is no such TimeSlot!");
// 		}
// 		TimeSlotDto timeSlotDto = new TimeSlotDto(timeSlot.getStartTime(), timeSlot.getEndTime(),
// 				timeSlot.getStartDate(), timeSlot.getEndDate(), timeSlot.getGarageSpot());
// 		timeSlotDto.setId(timeSlot.getTimeSlotId());
// 		return timeSlotDto;
// 	}

// 	public GarageTechnicianDto convertToDto(GarageTechnician garageTechnician) {
// 		if (garageTechnician == null) {
// 			throw new IllegalArgumentException("There is no such GarageTechnician!");
// 		}
// 		GarageTechnicianDto garageTechnicianDto = new GarageTechnicianDto(garageTechnician.getName());
// 		garageTechnicianDto.setTechnicianId(garageTechnician.getTechnicianId());
// 		return garageTechnicianDto;

// 	}

// 	public BookableServiceDto convertToDto(BookableService bookableService) {
// 		if (bookableService == null) {
// 			throw new IllegalArgumentException("There is no such BookableService!");
// 		}
// 		BookableServiceDto bookableServiceDto = new BookableServiceDto(bookableService.getDuration(),
// 				bookableService.getPrice(), bookableService.getName());
// 		bookableServiceDto.setId(bookableService.getServiceId());
// 		return bookableServiceDto;
// 	}

// 	/**
// 	 * 
// 	 * @param c is a Car -> CarDto
// 	 * @return carDto that we will convert
// 	 */
// 	private CarDto convertToDto(Car car) {
// 		if (car == null) {
// 			throw new IllegalArgumentException("There is no such Car!");
// 		}
// 		CarDto carDto = new CarDto(car.getModel(), car.getColor(), car.getYear());
// 		carDto.setCarId(car.getCarId());
// 		return carDto;
// 	}

// 	/**
// 	 * 
// 	 * @param appointment is an Appointment -> AppointmentDto
// 	 * @return Converted to AppointmentDto
// 	 */

// 	private AppointmentDto convertToDto(Appointment appointment) {
// 		if (appointment == null) {
// 			throw new IllegalArgumentException("There is no such Appointment!");
// 		}
// 		AppointmentDto appointmentDto = new AppointmentDto();
// 		appointmentDto.setCustomer(convertToDto(appointment.getCustomer()));
// 		appointmentDto.setGarageTechnician(convertToDto(appointment.getTechnician()));
// 		appointmentDto.setReceipt(convertToDto(appointment.getReceipt()));
// 		appointmentDto.setBookableService(convertToDto(appointment.getBookableServices()));
// 		appointmentDto.setReminder(convertToDto(appointment.getReminder()));
// 		appointmentDto.setTimeSlot(convertToDto(appointment.getTimeSlot()));
// 		appointmentDto.setId(appointment.getAppointmentId());
// 		return appointmentDto;
// 	}

// 	/**
// 	 * 
// 	 * @param c is a Customer -> CustomerDto
// 	 * @return CustomerDto that we will convert
// 	 */
// 	private CustomerDto convertToDto(Customer customer) {
// 		if (customer == null) {
// 			throw new IllegalArgumentException("There is no such Customer!");
// 		}
// 		CustomerDto customerDto = new CustomerDto(customer.getUserId(), customer.getPassword());
// 		customerDto.setProfile(convertToDto(customer.getCustomerProfile()));
// 		customerDto.setCar(convertToDto(customer.getCar()));
// 		customerDto.setId(customer.getId());
// 		return customerDto;
// 	}

// 	/**
// 	 * @param fieldTechnician is an FieldTechnician -> FieldTechnicianDto
// 	 * @return Converted FieldTechnicianDto
// 	 */

// 	private FieldTechnicianDto convertToDto(FieldTechnician fieldTechnician) {
// 		if (fieldTechnician == null) {
// 			throw new IllegalArgumentException("There is no such Field Technician!");
// 		}

// 		FieldTechnicianDto fieldTechnicianDto = new FieldTechnicianDto(fieldTechnician.getName(),
// 				fieldTechnician.getIsAvailable());
// 		fieldTechnicianDto.setId(fieldTechnician.getTechnicianId());
// 		return fieldTechnicianDto;

// 	}

// 	/**
// 	 * @param es is an EmergencyService -> EmergencyServiceDto
// 	 * @return Converted EmergencyServiceDto
// 	 */

// 	private EmergencyServiceDto convertToDto(EmergencyService emergencyService) {
// 		if (emergencyService == null) {
// 			throw new IllegalArgumentException("There is no such Emergency Service!");
// 		}

// 		EmergencyServiceDto emergencyServiceDto = new EmergencyServiceDto();
// 		emergencyServiceDto.setName(emergencyService.getName());
// 		emergencyServiceDto.setPrice(emergencyService.getPrice());
// 		emergencyServiceDto.setLocation(emergencyService.getLocation());

// 		if (emergencyService.getTechnician() != null) {
// 			emergencyServiceDto.setFieldTechnician(convertToDto(emergencyService.getTechnician()));
// 		}

// 		if (emergencyService.getCustomer() != null) {
// 			emergencyServiceDto.setCustomer(convertToDto(emergencyService.getCustomer()));
// 		}

// 		if (emergencyService.getReceipt() != null) {
// 			emergencyServiceDto.setReceipt(convertToDto(emergencyService.getReceipt()));
// 		}

// 		emergencyServiceDto.setId(emergencyService.getServiceId());

// 		return emergencyServiceDto;

// 	}

// 	private DayOfWeek convertToDto(String day) {
// 		if (day == null) {
// 			throw new IllegalArgumentException("There is no such day of the week!");
// 		}

// 		DayOfWeek dayOfWeek = null;

// 		if (day.equals("Monday")) {
// 			dayOfWeek = DayOfWeek.Monday;
// 		} else if (day.equals("Tuesday")) {
// 			dayOfWeek = DayOfWeek.Tuesday;
// 		} else if (day.equals("Wednesday")) {
// 			dayOfWeek = DayOfWeek.Wednesday;
// 		} else if (day.equals("Thursday")) {
// 			dayOfWeek = DayOfWeek.Thursday;
// 		} else if (day.equals("Friday")) {
// 			dayOfWeek = DayOfWeek.Friday;
// 		} else if (day.equals("Saturday")) {
// 			dayOfWeek = DayOfWeek.Saturday;
// 		} else if (day.equals("Sunday")) {
// 			dayOfWeek = DayOfWeek.Sunday;
// 		}

// 		return dayOfWeek;
// 	}

// 	public BusinessHourDto convertToDto(BusinessHour businessHour) {
// 		if (businessHour == null) {
// 			throw new IllegalArgumentException("There is no such BusinessHour!");
// 		}
// 		BusinessHourDto businessHourDto = new BusinessHourDto(convertToDto(businessHour.getDayOfWeek().toString()),
// 				businessHour.getStartTime(), businessHour.getEndTime());
// 		businessHourDto.setId(businessHour.getHourId());

// 		return businessHourDto;
// 	}

// 	/**
// 	 * 
// 	 * @param b
// 	 * @return
// 	 */

// 	private List<TimeSlotDto> createTimeSlotsDtosForBusiness(Business business) {
// 		List<TimeSlot> allTimeSlots = business.getRegular();
// 		List<TimeSlotDto> TimeSlots = new ArrayList<>();
// 		for (TimeSlot timeSlot : allTimeSlots) {
// 			TimeSlots.add(convertToDto(timeSlot));
// 		}
// 		return TimeSlots;
// 	}

// 	/**
// 	 * 
// 	 * @param bh
// 	 * @return
// 	 */

// 	private List<BusinessHourDto> createBusinessHoursDtosForBusiness(Business business) {
// 		List<BusinessHour> allBusinessHours = business.getBusinessHours();
// 		List<BusinessHourDto> BusinessHours = new ArrayList<>();
// 		for (BusinessHour businessHour : allBusinessHours) {
// 			BusinessHours.add(convertToDto(businessHour));
// 		}
// 		return BusinessHours;
// 	}

// 	/**
// 	 * 
// 	 * @param b
// 	 * @return
// 	 */
// 	private BusinessDto convertToDto(Business business) {
// 		if (business == null) {
// 			throw new IllegalArgumentException("There is no such business!");
// 		}

// 		BusinessDto businessDto = new BusinessDto(business.getName(), business.getAddress(), business.getPhoneNumber(),
// 				business.getEmailAddress(), createBusinessHoursDtosForBusiness(business),
// 				createTimeSlotsDtosForBusiness(business));
// 		businessDto.setId(business.getId());
// 		return businessDto;
// 	}

// 	/**
// 	 * 
// 	 * @param cr
// 	 * @return
// 	 */

// 	private CheckupReminderDto convertToDto(CheckupReminder checkupReminder) {
// 		if (checkupReminder == null) {
// 			throw new IllegalArgumentException("There is no such checkup reminder!");
// 		}

// 		CheckupReminderDto checkupReminderDto = new CheckupReminderDto(checkupReminder.getDate(),
// 				checkupReminder.getTime(), checkupReminder.getMessage());
// 		checkupReminderDto.setId(checkupReminder.getReminderId());
// 		return checkupReminderDto;
// 	}

	/////////////////////////////////// LOGIN
	/////////////////////////////////// LOGOUT///////////////////////////////////////

	// @PostMapping(value = { "/login/{userId}", "/login/{userId}/" })
	// public ResponseEntity<?> userLogin(@PathVariable(value = "userId") String userId,
	// 		@RequestParam(value = "password") String password) throws IllegalArgumentException {
	// 	// System.out.println(userId);
	// 	if (userId.equalsIgnoreCase("owner")) {
	// 		try {
	// 			logService.loginAsOwner(userId, password);
	// 		} catch (IllegalArgumentException e) {
	// 			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	// 		}
	// 	}

	// 	else if (userId.equalsIgnoreCase("admin")) {
	// 		try {
	// 			logService.loginAsAdmin(userId, password);
	// 		} catch (IllegalArgumentException e) {
	// 			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	// 		}
	// 	}

	// 	else {
	// 		try {
	// 			logService.loginAsCustomer(userId, password);
	// 		} catch (IllegalArgumentException e) {
	// 			System.out.println(e);
	// 			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	// 		}
	// 	}
		
	// 	return new ResponseEntity<>(HttpStatus.OK);
	// }

	// @PostMapping(value = { "/logout", "/logout/" })
	// public ResponseEntity<?> logout() {
	// 	try {
	// 	logService.logout();
	// 	} catch (IllegalArgumentException e) {
	// 		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
		
	// 	return new ResponseEntity<>(HttpStatus.OK);
	// }

	// @GetMapping(value = { "/login/currentCustomer", "/login/currentCustomer/" })
	// public CustomerDto getCurrentUser() {
	// 	User currentUser = logService.getLoggedUser();
	// 	if (!currentUser.getUserId().equalsIgnoreCase("owner") && !currentUser.getUserId().equalsIgnoreCase("admin")) {
	// 		return (convertToDto((Customer) currentUser));
	// 	} else {
	// 		return null;
	// 	}

	// }

	// @GetMapping(value = { "/login/currentOwner", "/login/currentOwner/" })
	// public OwnerDto getCurrentOwner() {
	// 	User currentUser = logService.getLoggedUser();
	// 	if (currentUser.getUserId().equalsIgnoreCase("owner")) {
	// 		return (convertToDto((Owner) currentUser));
	// 	} else {
	// 		return null;
	// 	}
	// }

	// @GetMapping(value = { "/login/currentAdmin", "/login/currentAdmin/" })
	// public AdministrativeAssistantDto getCurrentAdmin() {
	// 	User currentUser = logService.getLoggedUser();
	// 	if (currentUser.getUserId().equalsIgnoreCase("admin")) {
	// 		return (convertToDto((AdministrativeAssistant) currentUser));
	// 	} else {
	// 		return null;
	// 	}
	// }

	//////////////////////////////////////////////////////////////////////////////////////

	// @GetMapping(value = { "/profiles", "/profiles/" })
	// public List<ProfileDto> getAllProfiles() {
	// 	List<ProfileDto> profileDtos = new ArrayList<>();
	// 	for (Profile profile : profileService.getAllProfiles()) {
	// 		profileDtos.add(convertToDto(profile));
	// 	}
	// 	return profileDtos;
	// }

	// @GetMapping(value = { "/profile/{Id}", "/profile/{Id}/" })
	// public ProfileDto getProfileById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
	// 	return convertToDto(profileService.getProfile(Id));
	// }

	// @GetMapping(value = { "/profiles/{userId}", "/profiles/{userId}/" })
	// public ProfileDto getProfileByUserId(@PathVariable("userId") String userId) throws IllegalArgumentException {
	// 	return convertToDto(profileService.getProfileByUserId(userId));
	// }

	// @GetMapping(value = { "/appointments", "/appointments/" })
	// public List<AppointmentDto> getAllAppointments() {
	// 	List<AppointmentDto> appointmentDtos = new ArrayList<>();
	// 	for (Appointment appointment : appService.getAllAppointments()) {
	// 		appointmentDtos.add(convertToDto(appointment));
	// 	}
	// 	return appointmentDtos;
	// }

	// @GetMapping(value = { "/appointments/{userId}", "/appointments/{userId}/" })
	// public List<AppointmentDto> getCustomerAppointments(@PathVariable("userId") String userId)
	// 		throws IllegalArgumentException {
	// 	List<AppointmentDto> appointmentDtos = new ArrayList<>();
	// 	for (Appointment appointment : appService.getAppointmentsByCustomer(userId)) {
	// 		appointmentDtos.add(convertToDto(appointment));
	// 	}
	// 	return appointmentDtos;
	// }

	// @GetMapping(value = { "/appointment/{Id}", "/appointment/{Id}/" })
	// public AppointmentDto getAppointmentById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
	// 	return convertToDto(appService.getAppointment(Id));
	// }

	// @GetMapping(value = { "/receipts", "/receipts/" })
	// public List<ReceiptDto> getAllReceipts() {
	// 	List<ReceiptDto> receiptDtos = new ArrayList<>();
	// 	for (Receipt receipt : receiptService.getAllReceipts()) {
	// 		receiptDtos.add(convertToDto(receipt));
	// 	}
	// 	return receiptDtos;
	// }

	// @GetMapping(value = { "/receipts/{userId}", "/receipts/{userId}/" })
	// public List<ReceiptDto> getCustomerReceipts(@PathVariable("userId") String userId) {
	// 	List<ReceiptDto> receiptDtos = new ArrayList<>();
	// 	for (Receipt receipt : customerService.getCustomerReceipts(userId)) {
	// 		receiptDtos.add(convertToDto(receipt));
	// 	}
	// 	return receiptDtos;
	// }

	// @GetMapping(value = { "/receipt/{Id}", "/receipt/{Id}/" })
	// public ReceiptDto getReceiptById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
	// 	return convertToDto(receiptService.getReceipt(Id));
	// }

	///////////////////////////////// OWNER//////////////////////////////////////

	// @GetMapping(value = { "/owner", "/owner/" })
	// public List<OwnerDto> getOwner() { // will return only one owner either ways
	// 	List<OwnerDto> ownerDtos = new ArrayList<>();
	// 	for (Owner owner : ownerService.getOwner()) {
	// 		ownerDtos.add(convertToDto(owner));
	// 	}

	// 	return ownerDtos;
	// }

	// @GetMapping(value = { "/owner/{Id}", "/owner/{Id}/" })
	// public OwnerDto getOwnerById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
	// 	return convertToDto(ownerService.getOwnerByUserId(Id));
	// }

	// @PostMapping(value = { "register/owner/{userId}", "register/owner/{userId}/" })
	// public ResponseEntity<?> createOwner(@PathVariable("userId") String userId, @RequestParam String password)
	// 		{
	// 	Owner owner = null;
	// 	try {
	// 		owner = ownerService.createOwner(userId, password);
	// 	} catch(IllegalArgumentException e) {
	// 		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// 	return new ResponseEntity<>(convertToDto(owner), HttpStatus.CREATED);
	// }

	// @DeleteMapping(value = { "/delete/owner/{Id}", "/delete/owner/{Id}/" })
	// public void deleteOwner(@PathVariable("Id") Long Id) throws IllegalArgumentException {
	// 	Owner owner = ownerService.getOwnerByUserId(Id);
	// 	ownerService.deleteOwner(owner);
	// }

	// @PatchMapping(value = { "/edit/owner/{Id}", "/edit/owner/{Id}/" })
	// public void editOwner(@PathVariable("Id") Long Id, @RequestParam String userId, @RequestParam String password)
	// 		throws IllegalArgumentException {
	// 	Owner owner = ownerService.getOwnerByUserId(Id);
	// 	ownerService.editOwner(owner, userId, password);
	// }

	///////////////////////////////// EMERGENCY
	///////////////////////////////// SERVICE//////////////////////////////////////

	// @GetMapping(value = { "/emergencyServices", "/emergencyServices/" })
	// public List<EmergencyServiceDto> getAllEmergencyServices() {
		
		
	// 	List<EmergencyServiceDto> emergencyServiceDtos = new ArrayList<>();
	// 	for (EmergencyService emergencyService : emergencyServiceService.getAllEmergencyServices()) {
	// 		if (!emergencyService.getName().contains("for")) {
	// 			emergencyServiceDtos.add(convertToDto(emergencyService));			
	// 		}
			
	// 	}

	// 	return emergencyServiceDtos;
	// }

	// @GetMapping(value = { "/emergencyService/{Id}", "/emergencyService/{Id}/" })
	// public EmergencyServiceDto getEmergencyServiceById(@PathVariable("Id") Long Id) {
	// 	return convertToDto(emergencyServiceService.getEmergencyServiceByServiceId(Id));
	// }

	// @PostMapping(value = { "/create/emergencyService/{serviceName}", "/create/emergencyService/{serviceName}/" })
	// public ResponseEntity<?> createEmergencyService(@PathVariable("serviceName") String serviceName,
	// 		@RequestParam int price) {
	// 	EmergencyService emergencyService = null;
	// 	try {
	// 		emergencyService = emergencyServiceService.createEmergencyService(serviceName, price);
	// 	} catch (IllegalArgumentException e) {
	// 		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// 	return new ResponseEntity<>(convertToDto(emergencyService), HttpStatus.CREATED);

	// }

	// @PostMapping(value = { "book/emergencyService/{userId}",
	// 		"book/emergencyService/{userId}/" })
	// public ResponseEntity<?> bookEmergencyService(@PathVariable("userId") String userId,
	// 		@RequestParam(name ="serviceName") String serviceName, @RequestParam(name = "Location") String location,
	// 		@RequestParam(name = "fieldTechnicianId") Long fieldTechnicianId) throws IllegalArgumentException {

	// 	FieldTechnician fieldTechnician=null;
	// 	String nameOfBooking = null;
	// 	EmergencyService bookableEmergencyService=null;
	// 	try {
	// 		fieldTechnician = fieldService.getFieldTechnicianById(fieldTechnicianId); // get
	// 		nameOfBooking = serviceName + " for " + userId; // service for that user
	// 		bookableEmergencyService = emergencyServiceService.bookEmergencyService(nameOfBooking,
	// 				serviceName, location, userId, fieldTechnician);
	// 	} catch(IllegalArgumentException e) {
	// 		return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
		
	// 	return new ResponseEntity<>(convertToDto(bookableEmergencyService),HttpStatus.CREATED);
	// }

	// // Will not allow updating emergency service as it is spontaneous

	// @PatchMapping(value = { "/edit/emergencyService/{serviceId}", "/edit/emergencyService/{serviceId}/" })
	// public ResponseEntity<?> editEmergencyService(@PathVariable("serviceId") Long serviceId, @RequestParam int price)
	// 		throws IllegalArgumentException {
	// 	EmergencyService emergencyService = null;
	// 	try {
	// 		emergencyService = emergencyServiceService.getEmergencyServiceByServiceId(serviceId);
	// 		emergencyServiceService.editEmergencyService(emergencyService, price);
	// 	} catch (IllegalArgumentException e) {
	// 		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// 	return new ResponseEntity<>(convertToDto(emergencyService), HttpStatus.CREATED);

	// }

	// @DeleteMapping(value = { "/delete/emergencyService/{serviceId}", "/delete/emergencyServices/{serviceId}/" })
	// public ResponseEntity<?> deleteEmergencyService(@PathVariable("serviceId") Long serviceId)
	// 		throws IllegalArgumentException {
	// 	EmergencyService emergencyService = null;
	// 	try {
	// 		emergencyService = emergencyServiceService.getEmergencyServiceByServiceId(serviceId);
	// 		emergencyServiceService.deleteEmergencyService(emergencyService);
	// 	} catch (IllegalArgumentException e) {
	// 		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// 	return new ResponseEntity<>(convertToDto(emergencyService), HttpStatus.CREATED);

	// }

	// @PostMapping(value = { "/end/emergencyService/{serviceId}", "/end/emergencyService/{serviceId}/" })
	// public ResponseEntity<?> endEmergencyServiceBooking(@PathVariable("serviceId") Long serviceId) throws IllegalArgumentException {
	// 	EmergencyService emergencyServiceBooking = null;
	// 	try {
	// 		emergencyServiceBooking = emergencyServiceService.getEmergencyServiceByServiceId(serviceId);
	// 		emergencyServiceService.endEmergencyService(emergencyServiceBooking);
	// 	} catch(IllegalArgumentException e) {
	// 		return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// 	return new ResponseEntity<>(HttpStatus.CREATED);
	// } // only admin/owner can end an emergency service

	// //////////////////////////////// FIELD TECHNICIAN
	// ////////////////////////////////////////////////////////////////////

	// @GetMapping(value = { "/onGoingEmergencies", "/onGoingEmergencies/" })
	// public List<EmergencyService> getOnGoingEmergencies() {
	// 	return emergencyServiceService.getCurrentEmergencyServices();
	// }

	// @GetMapping(value = { "/fieldTechnician", "/fieldTechnician/" })
	// public List<FieldTechnicianDto> getFieldTechnicians() {
	// 	List<FieldTechnicianDto> fieldTechnicianDtos = new ArrayList<>();
	// 	for (FieldTechnician fieldTechnician : fieldService.getAllFieldTechnicians()) {
	// 		fieldTechnicianDtos.add(convertToDto(fieldTechnician));
	// 	}

	// 	return fieldTechnicianDtos;
	// }

	// @GetMapping(value = { "/fieldTechnician/{Id}", "/fieldTechnician/{Id}/" })
	// public FieldTechnicianDto getFieldTechnicianById(@PathVariable("Id") Long Id) {
	// 	return convertToDto(fieldService.getFieldTechnicianById(Id));
	// }

	// @PostMapping(value = { "/register/fieldTechnician/{name}", "/register/fieldTechnician/{name}/" })
	// public FieldTechnicianDto createFieldTechnician(@PathVariable("name") String name) throws IllegalArgumentException {
	// 	FieldTechnician fieldTechnician = fieldService.createFieldTechnician(name);
	// 	FieldTechnicianDto fieldTechnicianDto = convertToDto(fieldTechnician);
	// 	return fieldTechnicianDto;
	// }

	// @DeleteMapping(value = { "/delete/fieldTechnician/{Id}", "/delete/fieldTechnician/{Id}/" })
	// public void deleteFieldTechnician(@PathVariable("Id") Long id) throws IllegalArgumentException {
	// 	FieldTechnician fieldTechnician = fieldService.getFieldTechnicianById(id);
	// 	if (!fieldTechnician.getIsAvailable()) {
	// 		throw new IllegalArgumentException("The field technician is assigned to an emergency service!");
	// 	}
	// 	fieldService.deleteFieldTechnician(fieldTechnician);
	// }

	// @PatchMapping(value = { "/edit/fieldTechnician/{Id}", "/edit/fieldTechnician/{Id}/" })
	// public void editFieldTechnician(@PathVariable("Id") Long id, @RequestParam String name)
	// 		throws IllegalArgumentException {
	// 	FieldTechnician fieldTechnician = fieldService.getFieldTechnicianById(id);
	// 	fieldService.editFieldTechnician(fieldTechnician, name);
	// }

	/////////////////////////////////////////////////////////////////////////////

	// @GetMapping(value = { "/business", "/business/" })
	// public List<BusinessDto> getBusiness() {
	// 	List<BusinessDto> businessDtos = new ArrayList<>();
	// 	for (Business business : businessService.getBusiness()) {
	// 		businessDtos.add(convertToDto(business));
	// 	}

	// 	return businessDtos;
	// }

	// @GetMapping(value = { "/business/{Id}", "/business/{Id}/" })
	// public BusinessDto getBusinessById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
	// 	return convertToDto(businessService.getBusinessById(Id));
	// }

	// @PostMapping(value = { "/register/business/{name}", "/register/business/{name}/" }) // VERIFY PATH
	// public ResponseEntity<?> registerBusiness(@PathVariable("name") String name, @RequestParam String address,
	// 		@RequestParam String phoneNumber, @RequestParam String emailAddress) throws IllegalArgumentException {
	// 	List<BusinessHour> businessHour=null;
	// 	List<TimeSlot> timeSlots=null;
	// 	Business business =null;
	// 	try {
	// 		businessHour = businessHourService.getAllBusinessHours();
	// 		timeSlots = timeService.getAllTimeSlots();
	// 		business = businessService.createBusiness(name, address, phoneNumber, emailAddress, businessHour,
	// 				timeSlots);
	// 	}catch(IllegalArgumentException e) {
	// 		return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// 	return new ResponseEntity<>(convertToDto(business),HttpStatus.CREATED);
	// }

	// @PatchMapping(value = { "/edit/businessInformation/{Id}", "/edit/businessInformation/{Id}/" })
	// public ResponseEntity<?> updateBusinessInfo(@PathVariable("Id") Long Id, @RequestParam String name,
	// 		@RequestParam String address, @RequestParam String phoneNumber, @RequestParam String emailAddress) {
	// 	Business business = null;
	// 	try {
	// 		business = businessService.updateBusinessInformation(Id, name, address, phoneNumber, emailAddress,
	// 				null, null);
	// 	} catch(IllegalArgumentException e) {
	// 		return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// 	return new ResponseEntity<>(convertToDto(business),HttpStatus.CREATED);
	// }

	// @DeleteMapping(value = { "/delete/business/{Id}", "/delete/business/{Id}/" })
	// public void deleteBusiness(@PathVariable("Id") Long Id) {
	// 	Business business = businessService.getBusinessById(Id);
	// 	businessService.deleteBusiness(business);
	// }

	////////////////////////////////////////////////////////////////////////

	// @GetMapping(value = { "/checkupReminders", "/checkupReminders/" })
	// public List<CheckupReminderDto> getAllCheckupReminders() {
	// 	List<CheckupReminderDto> checkupReminderDtos = new ArrayList<>();
	// 	for (CheckupReminder checkupReminder : checkupReminderService.getAllCheckupReminder()) {
	// 		checkupReminderDtos.add(convertToDto(checkupReminder));
	// 	}
	// 	return checkupReminderDtos;
	// }

	// @GetMapping(value = { "/checkupReminder/{Id}", "/checkupReminder/{Id}/" })
	// public CheckupReminderDto getCheckupRemindertById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
	// 	return convertToDto(checkupReminderService.getCheckupReminderById(Id));
	// }

	// @PostMapping(value = { "/create/checkupReminder/{message}", "/create/checkupReminder/{message}/" })
	// public CheckupReminderDto createCheckupReminder(@PathVariable("message") String message,
	// 		@RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "yyyy-MM-dd") String date,
	// 		@RequestParam(name = "time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "hh:mm:ss") String time)
	// 		throws IllegalArgumentException {
	// 	CheckupReminder checkupReminder = checkupReminderService.createCheckupReminder(
	// 			Date.valueOf(LocalDate.parse(date)), Time.valueOf(LocalTime.parse(time)), message);
	// 	CheckupReminderDto checkupReminderDto = convertToDto(checkupReminder);
	// 	return checkupReminderDto;
	// }

	// @PatchMapping(value = { "/edit/checkupReminder/{reminderId}", "/edit/checkupReminder/{reminderId}/" })
	// public void editCheckupReminder(@PathVariable("reminderId") Long reminderId,
	// 		@RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "yyyy-MM-dd") String date,
	// 		@RequestParam(name = "time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "hh:mm:ss") String time,
	// 		@RequestParam String message) throws IllegalArgumentException {
	// 	// CheckupReminder checkupReminder = service.getCheckupReminderById(reminderId);
	// 	checkupReminderService.editCheckupReminder(reminderId, Date.valueOf(LocalDate.parse(date)),
	// 			Time.valueOf(LocalTime.parse(time)), message);

	// }

	//////////////////////////////////////////////////////////////////////////////////////////////

	// @GetMapping(value = { "/businessHours", "/businessHours/" })
	// public List<BusinessHourDto> getBusinessHours() {
	// 	List<BusinessHourDto> businessHourDtos = new ArrayList<>();
	// 	for (BusinessHour businessHour : businessHourService.getAllBusinessHours()) {
	// 		businessHourDtos.add(convertToDto(businessHour));
	// 	}

	// 	return businessHourDtos;
	// }

	// @GetMapping(value = { "/businessHour/{Id}", "/businessHour/{Id}/" })
	// public BusinessHourDto getBusinessHourById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
	// 	return convertToDto(businessHourService.getBusinessHourById(Id));
	// }

	// @PostMapping(value = { "/add/businessHour/{dayOfWeek}", "/add/businessHour/{dayOfWeek}/" }) // VERIFY PATH
	// public ResponseEntity<?> createBusinessHour(@PathVariable("dayOfWeek") String dayOfWeek,
	// 		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "hh:mm:ss") String startTime,
	// 		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "hh:mm:ss") String endTime)
	// 		throws IllegalArgumentException {
	// 	BusinessHour businessHour = null;
	// 	try {
	// 		 businessHour = businessHourService.createBusinessHour(dayOfWeek,
	// 					Time.valueOf(LocalTime.parse(startTime)), Time.valueOf(LocalTime.parse(endTime)));
	// 	}catch(IllegalArgumentException e) {
	// 		return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
		
	// 	return new ResponseEntity<>(convertToDto(businessHour),HttpStatus.CREATED);
	// }

	// @PatchMapping(value = { "/edit/businessHour/{Id}", "/edit/businessHour/{Id}/" })
	// public ResponseEntity<?> editBusinessHour(@PathVariable("Id") Long Id, @RequestParam String dayOfWeek,
	// 		@RequestParam String startTime, @RequestParam String endTime) {
	// 	BusinessHour businessHour = null;
	// 	try {
	// 		 businessHour = businessHourService.updateBusinessHour(Id, dayOfWeek,
	// 				Time.valueOf(LocalTime.parse(startTime)), Time.valueOf(LocalTime.parse(endTime)));
	// 	}catch(IllegalArgumentException e) {
	// 		return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
		
	// 	return new ResponseEntity<>(convertToDto(businessHour),HttpStatus.CREATED);

	// }

	// @DeleteMapping(value = { "/delete/businessHour/{Id}", "/delete/businessHour/{Id}/" })
	// public void deleteBusinessHourById(@PathVariable("Id") Long Id, @RequestParam Long businessId) {
	// 	BusinessHour businessHour = businessHourService.getBusinessHourById(Id);
	// 	Business business = businessService.getBusinessById(businessId);
	// 	businessHourService.deleteBusinessHour(businessHour, business);
	// }

	// @DeleteMapping(value = { "/delete/allBusinessHours/{Id}", "/delete/allBusinessHour/{Id}/" })
	// public void deleteAllBusinessHours(@PathVariable("Id") Long Id) {
	// 	Business business = businessService.getBusinessById(Id);
	// 	businessHourService.deleteAllBusinessHours(business);
	// }

	/////////////////////////////////////// ADMINISTRATIVE
	/////////////////////////////////////// ASSISTANT///////////////////////////

	// @GetMapping(value = { "/administrativeAssistants", "/administrativeAssistants/" })
	// public List<AdministrativeAssistantDto> getAdministrativeAssistants() {
	// 	List<AdministrativeAssistantDto> administrativeAssistantDtos = new ArrayList<>();
	// 	for (AdministrativeAssistant admnistrativeAssistant : adminService.getAllAdministrativeAssistants()) {
	// 		administrativeAssistantDtos.add(convertToDto(admnistrativeAssistant));
	// 	}
	// 	return administrativeAssistantDtos;
	// }

	// @GetMapping(value = { "/administrativeAssistant/{Id}", "/administrativeAssistant/{Id}/" })
	// public AdministrativeAssistantDto getAdministrativeAssistantById(@PathVariable("Id") Long Id)
	// 		throws IllegalArgumentException {
	// 	return convertToDto(adminService.getAdministrativeAssistantById(Id));
	// }

	// @PostMapping(value = { "/register/administrativeAssistant/{userId}",
	// 		"/register/administrativeAssistant/{userId}/" })
	// public ResponseEntity<?> createAdministrativeAssistant(@PathVariable("userId") String userId,
	// 		@RequestParam String password) {
	// 	AdministrativeAssistant administrativeAssistant = null;
	// 	try {
	// 		administrativeAssistant = adminService.createAdministrativeAssistant(userId, password);
	// 	} catch(IllegalArgumentException e) {
	// 		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// 	return new ResponseEntity<>(convertToDto(administrativeAssistant), HttpStatus.CREATED);
	// }

	/////////////////////////////////////// APPOINTMENT
	/////////////////////////////////////// REMINDER///////////////////////////
	// @GetMapping(value = { "/appointmentReminders", "/appointmentReminders/" })
	// public List<AppointmentReminderDto> getAppointmentReminders() {
	// 	List<AppointmentReminderDto> appointmentReminderDtos = new ArrayList<>();
	// 	for (AppointmentReminder appointmentReminder : appReminderService.getAllAppointmentReminders()) {
	// 		appointmentReminderDtos.add(convertToDto(appointmentReminder));
	// 	}
	// 	return appointmentReminderDtos;
	// }

	// @GetMapping(value = { "/appointmentReminder/{Id}", "/appointmentReminder/{Id}/" })
	// public AppointmentReminderDto getAppointmentRemindertById(@PathVariable("Id") Long Id)
	// 		throws IllegalArgumentException {
	// 	AppointmentReminder appointmentReminder = appReminderService.getAppointmentReminderById(Id);
	// 	if (appointmentReminder == null) {
	// 		throw new IllegalArgumentException("No appointment reminder with such id!");
	// 	}
	// 	return convertToDto(appointmentReminder);
	// }

	// @PostMapping(value = { "/create/appointmentReminder/{message}", "/create/appointmentReminder/{message}/" })
	// public AppointmentReminderDto createAppointmentReminder(@PathVariable("message") String message,
	// 		@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-mm-dd") String date,
	// 		@RequestParam("time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "hh:mm:ss") String time)
	// 		throws IllegalArgumentException {
	// 	AppointmentReminder appointmentReminder = appReminderService.createAppointmentReminder(
	// 			Date.valueOf(LocalDate.parse(date)), Time.valueOf(LocalTime.parse(time)), message);
	// 	AppointmentReminderDto appointmentReminderDto = convertToDto(appointmentReminder);
	// 	return appointmentReminderDto;
	// }

	/////////////////////////////////////// BOOKABLE
	/////////////////////////////////////// SERVICE//////////////////////////////

	// @GetMapping(value = { "/bookableServices", "/bookableServices/" })
	// public List<BookableServiceDto> getAllBookableServices() {
	// 	List<BookableServiceDto> bookableServiceDtos = new ArrayList<>();
	// 	for (BookableService bookableService : bookService.getAllBookableServices()) {
	// 		bookableServiceDtos.add(convertToDto(bookableService));
	// 	}

	// 	return bookableServiceDtos;
	// }

	// @GetMapping(value = { "/bookableService/{Id}", "/bookableService/{Id}/" })
	// public BookableServiceDto getBookableServiceById(@PathVariable("Id") Long Id) {
	// 	BookableService bookableService = bookService.getBookableServiceById(Id);
	// 	if (bookableService == null) {
	// 		throw new IllegalArgumentException("No service with such id!");
	// 	}
	// 	return convertToDto(bookableService);
	// }

	// @PostMapping(value = { "/create/bookableService/{serviceName}", "/create/bookableService/{serviceName}/" })
	// public ResponseEntity<?> createBookableService(@PathVariable("serviceName") String name, @RequestParam int duration,
	// 		@RequestParam int price) {
	// 	BookableService bookableService = null;
	// 	try {
	// 		bookableService = bookService.createBookableService(name, price, duration);
	// 	} catch (IllegalArgumentException e) {
	// 		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// 	return new ResponseEntity<>(convertToDto(bookableService), HttpStatus.CREATED);
	// }

	/////////////////////////////////////// GARAGE
	/////////////////////////////////////// TECHNICIAN////////////////////////////
	// @GetMapping(value = { "/garageTechnicians", "/garageTechnicians/" })
	// public List<GarageTechnicianDto> getGarageTechnicians() {
	// 	List<GarageTechnicianDto> garageTechnicianDtos = new ArrayList<>();
	// 	for (GarageTechnician garageTechnician : garageService.getAllGarageTechnicians()) {
	// 		garageTechnicianDtos.add(convertToDto(garageTechnician));
	// 	}

	// 	return garageTechnicianDtos;
	// }

	// @GetMapping(value = { "/garageTechnician/{Id}", "/garageTechnician/{Id}/" })
	// public GarageTechnicianDto getGarageTechnicianById(@PathVariable("Id") Long Id) {
	// 	GarageTechnician garageTechnician = garageService.getGarageTechnicianById(Id);
	// 	return convertToDto(garageTechnician);
	// }

	// @GetMapping(value = { "/garageTechnicians/{name}", "/garageTechnicians/{name}/" })
	// public GarageTechnicianDto getGarageTechnicianIdByName(@PathVariable("name") String name) {

	// 	GarageTechnician garageTechnician = garageService.getGarageTechnicianByName(name);
	// 	if (garageTechnician == null) {
	// 		throw new IllegalArgumentException("No such garage technician exists!");
	// 	}
	// 	return convertToDto(garageTechnician);
	// }

	// @GetMapping(value = { "/fieldTechnicians/{name}", "/fieldTechnicians/{name}/" })
	// public FieldTechnicianDto getFieldTechnicianIdByName(@PathVariable("name") String name) {

	// 	FieldTechnician fieldTechnician = fieldService.getFieldTechnicianByName(name);
	// 	if (fieldTechnician == null) {
	// 		throw new IllegalArgumentException("No such field technician exists!");
	// 	}
	// 	return convertToDto(fieldTechnician);
	// }

	// @PostMapping(value = { "/register/garageTechnician/{name}", "/register/garageTechnician/{name}/" })
	// public GarageTechnicianDto createGarageTechnician(@PathVariable("name") String name)
	// 		throws IllegalArgumentException {
	// 	GarageTechnician garageTechnician = garageService.createGarageTechnician(name);
	// 	GarageTechnicianDto garageTechnicianDto = convertToDto(garageTechnician);
	// 	return garageTechnicianDto;
	// }

	// @PostMapping(value = { "/book/appointment/{userId}", "/book/appointment/{userId}/" })
	// public AppointmentDto bookAppointment(@PathVariable("userId") String userId,
	// 		@RequestParam(name ="serviceName") String serviceName,
	// 		@RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") String date,
	// 		@RequestParam(name = "garageSpot") Integer garageSpot,
	// 		@RequestParam(name = "startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") String startTime,
	// 		@RequestParam(name = "garageTechnicianId") Long garageTechnicianId) throws IllegalArgumentException {
	// 	return convertToDto(appService.bookAppointment(userId, serviceName.trim(), Date.valueOf(LocalDate.parse(date)),
	// 			garageSpot, Time.valueOf(LocalTime.parse(startTime)), garageTechnicianId));
	// }

	// @PostMapping(value = { "/create/profile/{first}/{last}", "/create/profile/{first}/{last}/" })
	// public ProfileDto createProfile(@PathVariable("first") String firstName, @PathVariable("last") String lastName,
	// 		@RequestParam(name = "Email Address") String emailAddress,
	// 		@RequestParam(name = "Phone Number") String phoneNumber,
	// 		@RequestParam(name = "Address Line") String addressLine, @RequestParam(name = "Zip Code") String zipCode)
	// 		throws IllegalArgumentException {
	// 	Profile profile = profileService.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode,
	// 			emailAddress);
	// 	return convertToDto(profile);
	// }

	// @GetMapping(value = { "/profile/{first}/{last}", "/profile/{first}/{last}/" })
	// public ProfileDto getProfileByFirstAndLast(@PathVariable("first") String firstName,
	// 		@PathVariable("last") String lastName) throws IllegalArgumentException {
	// 	Profile profile = profileService.getProfileByFirstAndLast(firstName, lastName);
	// 	if (profile == null) {
	// 		throw new IllegalArgumentException("No profile with such first and last name!");
	// 	}
	// 	return convertToDto(profile);
	// }

	// @PatchMapping(value = { "/edit/profile/{profileId}", "/edit/profile/{profileId}/" })
	// public ResponseEntity<?> editProfile(@PathVariable("profileId") Long profileId,
	// 		@RequestParam(name = "Email Address") String emailAddress,
	// 		@RequestParam(name = "Phone Number") String phoneNumber,
	// 		@RequestParam(name = "Address Line") String addressLine, @RequestParam(name = "Zip Code") String zipCode,
	// 		@RequestParam(name = "First Name") String firstName, @RequestParam(name = "Last Name") String lastName) {
	// 	Profile profile = null;
	// 	try {
	// 		profile = profileService.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
	// 				zipCode);
	// 	} catch (IllegalArgumentException e) {
	// 		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// 	return new ResponseEntity<>(convertToDto(profile), HttpStatus.CREATED);
	// }

	/////////////////////////////////////// CUSTOMER///////////////////////////

	// @GetMapping(value = { "/customers", "/customers/" })
	// public List<CustomerDto> getCustomers() {
	// 	List<CustomerDto> customerDtos = new ArrayList<>();
	// 	for (Customer customer : customerService.getAllCustomers()) {
	// 		customerDtos.add(convertToDto(customer));
	// 	}
	// 	return customerDtos;
	// }

	// @GetMapping(value = { "/customer/{Id}", "/customer/{Id}/" })
	// public CustomerDto getCustomerById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
	// 	Customer customer = customerService.getCustomerById(Id);
	// 	if (customer == null) {
	// 		throw new IllegalArgumentException("No customer with such id!");
	// 	}
	// 	return convertToDto(customer);
	// }

	// @PostMapping(value = { "/register/customer/{userId}", "/register/customer/{userId}/" })
	// public ResponseEntity<?> registerCustomer(@PathVariable("userId") String userId, @RequestParam String password,
	// 		@RequestParam String firstName, @RequestParam String lastName, @RequestParam String address,
	// 		@RequestParam String phoneNumber, @RequestParam String zipCode, @RequestParam String emailAddress,
	// 		@RequestParam String modelNumber, @RequestParam String year, @RequestParam String color)
	// 		{
	// 	Customer customer = null;
		
	// 	try {
	// 		Car car1 = carService.createCar(modelNumber, year, color);
	// 		Profile profile1 = profileService.createProfile(address, phoneNumber, firstName, lastName, zipCode,
	// 			emailAddress);

	// 		List<Reminder> reminder = new ArrayList<>();
	// 		customer = customerService.createCustomer(userId, password, reminder, car1, profile1);
	// 	} catch(IllegalArgumentException e) {
	// 		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
		
	// 	return new ResponseEntity<>(convertToDto(customer), HttpStatus.CREATED);
	// }

	// @PatchMapping(value = { "/edit/customer/{userId}", "/edit/customer/{userId}/" })
	// public CustomerDto editCustomer(@PathVariable("userId") String userId, @RequestParam String password,
	// 		@RequestParam Long reminders, @RequestParam Long car, @RequestParam Long profile)
	// 		throws IllegalArgumentException {
	// 	Car car1 = carService.getCarByCarId(car);
	// 	Profile profile1 = profileService.getProfile(profile);
	// 	List<Reminder> reminder = reminderService.getReminderByReminderId(reminders);
	// 	Customer customer = customerService.getCustomerByUserId(userId);
	// 	customer = customerService.editCustomer(customer, userId, password, reminder, car1, profile1);
	// 	CustomerDto customerDto = convertToDto(customer);
	// 	return customerDto;
	// }

	/////////////////////////////////////// CAR///////////////////////////

	// @GetMapping(value = { "/cars", "/cars/" })
	// public List<CarDto> getCars() {
	// 	List<CarDto> carDtos = new ArrayList<>();
	// 	for (Car car : carService.getAllCars()) {
	// 		carDtos.add(convertToDto(car));
	// 	}
	// 	return carDtos;
	// }

	// @GetMapping(value = { "/cars/{model}/{year}/{color}", "/cars/{model}/{year}/{color}/" })
	// public List<CarDto> getCarByModelAndYearAndColor(@PathVariable String model, @PathVariable String year,

	// 		@PathVariable String color) throws IllegalArgumentException {
	// 	List<Car> car = carService.getCarByModelAndYearAndColor(model, year, color);

	// 	if (model == null || year == null || color == null) {
	// 		throw new IllegalArgumentException("No car with such model, year or color!");
	// 	}
	// 	return convertListToDto(car);
	// }

	// private List<CarDto> convertListToDto(List<Car> car) {
	// 	List<CarDto> cardto = new ArrayList<CarDto>();
	// 	for (Car car1 : car) {
	// 		cardto.add(convertToDto(car1));
	// 	}
	// 	return cardto;
	// }

	// @PostMapping(value = { "/add/car", "/add/car/" })
	// public CarDto createCar(@RequestParam String model, @RequestParam String year, @RequestParam String color)
	// 		throws IllegalArgumentException {
	// 	Car car1 = carService.createCar(model, year, color);
	// 	CarDto carDto = convertToDto(car1);
	// 	return carDto;
	// }

	/////////////////////////////////////// TIMESLOT///////////////////////////

	// @GetMapping(value = { "/timeSlots", "/timeSlots/" })
	// public List<TimeSlotDto> getTimeSlot() {
	// 	List<TimeSlotDto> timeSlotDtos = new ArrayList<>();
	// 	for (TimeSlot timeSlot : timeService.getAllTimeSlots()) {
	// 		timeSlotDtos.add(convertToDto(timeSlot));
	// 	}
	// 	return timeSlotDtos;
	// }

	// @GetMapping(value = { "/timeSlot/{startDate}/{startTime}", "/timeSlot/{startDate}/{startTime}/" })
	// public TimeSlotDto getTimeSlotByStartDateAndStartTime(
	// 		@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "c") String startDate,
	// 		@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") String startTime)
	// 		throws IllegalArgumentException {
	// 	TimeSlot timeSlot = timeService.getTimeSlotByStartDateAndStartTime(Date.valueOf(LocalDate.parse(startDate)),
	// 			Time.valueOf(LocalTime.parse(startTime)));
	// 	if (startDate == null || startTime == null) {
	// 		throw new IllegalArgumentException("No time slot with such start date or start time!");
	// 	}
	// 	return convertToDto(timeSlot);
	// }

	// @PostMapping(value = { "/add/timeSlot/{startDate}/{startTime}", "/add/timeSlot/{startDate}/{startTime}/" })
	// public TimeSlotDto createTimeSlot(
	// 		@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-mm-dd") String startDate,
	// 		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-mm-dd") String endDate,
	// 		@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") String startTime,
	// 		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") String endTime,
	// 		@RequestParam Integer garageSpot) throws IllegalArgumentException {
	// 	TimeSlot timeSlot = timeService.createTimeSlot(Time.valueOf(LocalTime.parse(startTime)),
	// 			Time.valueOf(LocalTime.parse(endTime)), Date.valueOf(LocalDate.parse(startDate)),
	// 			Date.valueOf(LocalDate.parse(endDate)), garageSpot);
	// 	TimeSlotDto timeSlotDtos = convertToDto(timeSlot);
	// 	return timeSlotDtos;
	// }

	// @DeleteMapping(value = { "/cancel/appointment/{appointmentId}", "/cancel/appointment/{appointmentId}/" })
	// public ResponseEntity<?> cancelAppointmemt(@PathVariable("appointmentId") Long appointmentId)
	// 		throws IllegalArgumentException {
	// 	Appointment appointment = null;
	// 	try {
	// 		appointment = appService.getAppointment(appointmentId);
	// 		appService.deleteAppointment(appointment, null, null);
	// 	} catch (IllegalArgumentException e) {
	// 		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// 	return new ResponseEntity<>(HttpStatus.CREATED);

	// }

	// @DeleteMapping(value = { "/delete/appointmentReminder/{reminderId}", "/delete/appointmentReminder/{reminderId}/" })
	// public void deleteAppointmentReminder(@PathVariable("reminderId") Long reminderId) throws IllegalArgumentException {

	// 	AppointmentReminder appointmentReminder = appReminderService.getAppointmentReminderById(reminderId);
	// 	appReminderService.deleteAppointmentReminder(appointmentReminder);
	// }

	// @PatchMapping(value = { "/edit/appointmentReminder/{reminderId}", "/edit/appointmentReminder/{reminderId}/" })
	// public void editAppointmentReminder(@PathVariable("reminderId") Long reminderId, @RequestParam String message)
	// 		throws IllegalArgumentException {
	// 	AppointmentReminder appointmentReminder = appReminderService.getAppointmentReminderById(reminderId);
	// 	appReminderService.editAppointmentReminder(appointmentReminder, message);

	// }

	// @DeleteMapping(value = { "/delete/bookableService/{serviceId}", "/delete/bookableService/{serviceId}/" })
	// public ResponseEntity<?> deleteBookableService(@PathVariable("serviceId") Long serviceId)
	// 		throws IllegalArgumentException {
	// 	BookableService bookableService = null;
	// 	try {
	// 		bookableService = bookService.getBookableServiceById(serviceId);
	// 		bookService.deleteBookableService(bookableService);
	// 	} catch (IllegalArgumentException e) {
	// 		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// 	return new ResponseEntity<>(convertToDto(bookableService), HttpStatus.CREATED);
	// }

	// @PatchMapping(value = { "/edit/bookableService/{serviceId}", "/edit/bookableService/{serviceId}/" })
	// public ResponseEntity<?> editBookableService(@PathVariable("serviceId") Long serviceId, @RequestParam int duration,
	// 		@RequestParam int price) throws IllegalArgumentException {
	// 	BookableService bookableService = null;
	// 	try {
	// 		bookableService = bookService.getBookableServiceById(serviceId);
	// 		bookService.editBookableService(bookableService, duration, price);
	// 	} catch (IllegalArgumentException e) {
	// 		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// 	return new ResponseEntity<>(convertToDto(bookableService), HttpStatus.CREATED);
	// }

	// @DeleteMapping(value = { "/delete/administrativeAssistant/{Id}", "/delete/administrativeAssistant/{Id}/" })
	// public void deleteAdministrativeAssistant(@PathVariable("Id") Long Id) throws IllegalArgumentException {
	// 	AdministrativeAssistant administrativeAssistant = adminService.getAdministrativeAssistantById(Id);
	// 	adminService.deleteAdministrativeAssistant(administrativeAssistant);
	// }

	// @PatchMapping(value = { "/edit/administrativeAssistant/{Id}", "/edit/administrativeAssistants/{Id}/" })
	// public void editAdministariveAssistant(@PathVariable("Id") Long Id, @RequestParam String userId,
	// 		@RequestParam String password) throws IllegalArgumentException {
	// 	AdministrativeAssistant administrativeAssistant = adminService.getAdministrativeAssistantById(Id);
	// 	adminService.editAdministrativeAssistant(administrativeAssistant, userId, password);
	// }

	// @DeleteMapping(value = { "/delete/garageTechnician/{technicianId}", "/delete/garageTechnician/{technicianId}/" })

	// public void deleteGarageTechnician(@PathVariable("technicianId") Long technicianId)
	// 		throws IllegalArgumentException {
	// 	GarageTechnician garageTechnician = garageService.getGarageTechnicianById(technicianId);
	// 	garageService.deleteGarageTechnician(garageTechnician);
	// }

	// @DeleteMapping(value = { "/delete/customer/{Id}", "/delete/customer/{Id}/" })
	// public void deleteCustomer(@PathVariable("Id") Long Id) throws IllegalArgumentException {
	// 	Customer customer = customerService.getCustomerById(Id);
	// 	customerService.deleteCustomer(customer);
	// }

	// @PatchMapping(value = { "/edit/car/{carId}", "/edit/car/{carId}/" })
	// public ResponseEntity<?> editCar(@PathVariable("carId") Long carId, @RequestParam String model,
	// 		@RequestParam String year, @RequestParam String color) throws IllegalArgumentException {
	// 	Car car = null;
	// 	try {
	// 		car = carService.getCarByCarId(carId);
	// 		car = carService.editCar(car, model, year, color);
	// 	} catch (IllegalArgumentException e) {
	// 		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// 	return new ResponseEntity<>(convertToDto(car), HttpStatus.CREATED);
	// }

	// @GetMapping(value = { "/car/{userId}", "/car/{userId}/" })
	// public CarDto getCustomerCar(@PathVariable("userId") String userId) throws IllegalArgumentException {
	// 	Car car = customerService.getCustomerCar(userId);
	// 	return convertToDto(car);
	// }

	// @GetMapping(value = { "/appointments/date/{date}", "/appointments/date/{date}/" })
	// public List<AppointmentDto> getAppointmentsByDate(
	// 		@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-mm-dd") String date) {
	// 	List<AppointmentDto> appointmentsDto = new ArrayList<>();
	// 	for (Appointment appointment : appService.getAppointmentsByDate(Date.valueOf(date))) {
	// 		appointmentsDto.add(convertToDto(appointment));
	// 	}
	// 	return appointmentsDto;
	// }

	// @GetMapping(value = { "/appointments/next/{userId}", "/appointments/next/{userId}/" })
	// public List<AppointmentDto> getFuturAppointments(@PathVariable("userId") String userId) {
	// 	List<AppointmentDto> appointmentsDto = new ArrayList<AppointmentDto>();
	// 	for (Appointment appointment : appService.getNextAppointments(userId)) {
	// 		appointmentsDto.add(convertToDto(appointment));
	// 	}
	// 	return appointmentsDto;
	// }

	// @GetMapping(value = { "/appointments/next/24Hours/{userId}", "/appointments/next/24Hours/{userId}/" })
	// public List<AppointmentDto> get24HoursAppointments(@PathVariable("userId") String userId) {
	// 	List<AppointmentDto> appointmentsDto = new ArrayList<AppointmentDto>();
	// 	for (Appointment appointment : appService.get24hoursAppointment(userId)) {
	// 		appointmentsDto.add(convertToDto(appointment));
	// 	}
	// 	return appointmentsDto;
	// }

// }
