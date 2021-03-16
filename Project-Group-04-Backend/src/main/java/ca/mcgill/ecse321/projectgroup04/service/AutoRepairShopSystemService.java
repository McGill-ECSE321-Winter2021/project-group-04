package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*;
import ca.mcgill.ecse321.projectgroup04.model.*;
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour.DayOfWeek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutoRepairShopSystemService {

	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	ReceiptRepository receiptRepository;

	@Autowired
	AppointmentRepository appointmentRepository;

	@Autowired
	CarRepository carRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	TimeSlotRepository timeSlotRepository;

	@Autowired
	AdministrativeAssistantRepository administrativeAssistantRepository;

	@Autowired
	OwnerRepository ownerRepository;

	@Autowired
	AppointmentReminderRepository appointmentReminderRepository;

	@Autowired
	BookableServiceRepository bookableServiceRepository;

	@Autowired
	EmergencyServiceRepository emergencyServiceRepository;

	@Autowired
	GarageTechnicianRepository garageTechnicianRepository;

	@Autowired
	FieldTechnicianRepository fieldTechnicianRepository;

	@Autowired
	BusinessRepository businessRepository;

	@Autowired
	BusinessHourRepository businessHourRepository;

	@Autowired
	CheckupReminderRepository checkupReminderRepository;

	@Transactional
	public Profile createProfile(String aAddressLine, String aPhoneNumber, String aFirstName, String aLastName,
			String aZipCode, String aEmailAddress) {
		if (aAddressLine == null || aAddressLine == "") {
			throw new IllegalArgumentException("Address Line can't be null or empty");
		}
		if (aPhoneNumber == null || aPhoneNumber == "") {
			throw new IllegalArgumentException("Address Line can't be null or empty");
		}
		if (aPhoneNumber.length() < 7 || aPhoneNumber.length() > 7) {
			throw new IllegalArgumentException("Phone Number must be 7 characters long");
		}
		if (aFirstName == null || aFirstName == "") {
			throw new IllegalArgumentException("First Name can't be null or empty");
		}
		if (aLastName == null || aLastName == "") {
			throw new IllegalArgumentException("Last Name can't be null or empty");
		}
		if (aZipCode == null || aZipCode == "") {
			throw new IllegalArgumentException("Zip Code can't be null or empty");
		}
		if (aZipCode.length() < 6 || aZipCode.length() > 6) {
			throw new IllegalArgumentException("Zip Code must be 6 characters long");
		}
		if (aEmailAddress == null || aEmailAddress == "") {
			throw new IllegalArgumentException("Email Address can't be null or empty");
		}
		if (!aEmailAddress.contains("@")) {
			throw new IllegalArgumentException("Email Address must contain @ character");
		}
		Profile profile = new Profile();
		profile.setAddressLine(aAddressLine);
		profile.setEmailAddress(aEmailAddress);
		profile.setFirstName(aFirstName);
		profile.setLastName(aLastName);
		profile.setPhoneNumber(aPhoneNumber);
		profile.setZipCode(aZipCode);
		profileRepository.save(profile);
		return profile;
	}

	@Transactional
	public Profile getProfile(Long aProfileId) {
		Optional<Profile> p = profileRepository.findById(aProfileId);
		Profile profile = profileRepository.findProfileByProfileId(aProfileId);
		if(p.isPresent()) {
			return profile;
		}
		else {
			throw new IllegalArgumentException("No profile with such ID exist!");
		}
	}

	@Transactional
	public List<Profile> getAllProfiles() {
		return (List<Profile>) profileRepository.findAll();
	}

	@Transactional
	public Receipt createReceipt(double aTotalPrice) {
		if (aTotalPrice == 0) {
			throw new IllegalArgumentException("Total Price can't be 0");
		}
		Receipt receipt = new Receipt();
		receipt.setTotalPrice(aTotalPrice);
		return receipt;
	}

	@Transactional
	public Receipt getReceipt(Long aReceiptId) {
		Receipt receipt = receiptRepository.findReceiptByReceiptId(aReceiptId);
		Optional<Receipt> r=receiptRepository.findById(aReceiptId);
		if(r.isPresent()) {
			return receipt;
		}
		else {
			throw new IllegalArgumentException("No receipt with such ID exist!");
		}
	}

	@Transactional
	public List<Receipt> getAllReceipts() {
		return (List<Receipt>) receiptRepository.findAll();
	}

	@Transactional
	public List<Receipt> getCustomerReceipts(Customer customer) {
		if(customer==null) {
			throw new IllegalArgumentException("Customer can't be null!");
		}
		List<Receipt> customerReceipts = new ArrayList<>();
		for (Appointment a : appointmentRepository.findByCustomer(customer)) {
			customerReceipts.add(a.getReceipt());
		}
		for (EmergencyService emergencyService : emergencyServiceRepository.findByCustomer(customer)) {
			customerReceipts.add(emergencyService.getReceipt());
		}
		return customerReceipts;
	}

	@Transactional
	public Appointment createAppointment(Customer aCustomer, BookableService aBookableService,
			GarageTechnician aGarageTechnician, TimeSlot aTimeSlot, AppointmentReminder aAppointmentReminder,
			Receipt aReceipt) {
		Appointment appointment = new Appointment();
		if (aCustomer == null) {
			throw new IllegalArgumentException("Customer can't be null");
		}
		if (aBookableService == null) {
			throw new IllegalArgumentException("Bookable Service can't be null");
		}
		if (aGarageTechnician == null) {
			throw new IllegalArgumentException("Garage Technician can't be null");
		}
		if (aTimeSlot == null) {
			throw new IllegalArgumentException("Time Slot can't be null");
		}
		if (aAppointmentReminder == null) {
			throw new IllegalArgumentException("Appointment Reminder can't be null");
		}
		if (aReceipt == null) {
			throw new IllegalArgumentException("Receipt can't be null");
		}
		appointment.setBookableServices(aBookableService);
		appointment.setCustomer(aCustomer);
		appointment.setReceipt(aReceipt);
		appointment.setReminder(aAppointmentReminder);
		appointment.setTechnician(aGarageTechnician);
		appointment.setTimeSlot(aTimeSlot);
		appointmentRepository.save(appointment);
		return appointment;
	}

	@Transactional
	public Appointment getAppointmentByReminder(AppointmentReminder reminder) {
		
		return appointmentRepository.findByReminder(reminder);
	}

	@Transactional
	public Appointment getAppointmentByTimeSlotAndCustomer(TimeSlot aTimeSlot, Customer aCustomer) {
		return appointmentRepository.findByTimeSlotAndCustomer(aTimeSlot, aCustomer);
	}

	@Transactional
	public List<Appointment> getAppointmentsByCustomer(Customer customer) {
		if(customer==null) {
			throw new IllegalArgumentException("Customer can't be null");
		}
		return appointmentRepository.findByCustomer(customer);
	}

	@Transactional
	public List<Appointment> getAppointmentsByTechnician(GarageTechnician garageTechnician) {
		if(garageTechnician==null) {
			throw new IllegalArgumentException("Garage Technician can't be null");
		}
		return appointmentRepository.findByTechnician(garageTechnician);
	}

	@Transactional
	public Appointment getAppointmentsByBookableServiceAndCustomer(BookableService service, Customer customer) {
		if(customer==null) {
			throw new IllegalArgumentException("Customer can't be null");
		}
		if(service==null) {
			throw new IllegalArgumentException("Service can't be null");
		}
		return appointmentRepository.findByBookableServicesAndCustomer(service, customer);
	}

	@Transactional
	public List<Appointment> getAllAppointments() {
		return (List<Appointment>) appointmentRepository.findAll();
	}

	@Transactional
	public AdministrativeAssistant createAdministrativeAssistant(String userId, String password) {
		if (userId == null) {
			throw new IllegalArgumentException("Username can't be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("Password can't be null");
		}
		AdministrativeAssistant administrativeAssistant = new AdministrativeAssistant();
		administrativeAssistant.setUserId(userId);
		administrativeAssistant.setPassword(password);
		administrativeAssistantRepository.save(administrativeAssistant);
		return administrativeAssistant;

	}

	@Transactional
	public AdministrativeAssistant getAdministrativeAssistantById(Long userId) {
		return administrativeAssistantRepository.findAdministrativeAssistantById(userId);
	}

	@Transactional
	public List<AdministrativeAssistant> getAllAdministrativeAssistants() {
		return (List<AdministrativeAssistant>) administrativeAssistantRepository.findAll();
	}

	/////////////////////////////////////////////////////////////////////////////

	@Transactional
	public Owner createOwner(String userId, String password) {
		Owner owner = new Owner();
		owner.setUserId(userId);
		owner.setPassword(password);
		ownerRepository.save(owner);
		return owner;
	}

	@Transactional
	public Owner getOwnerByUserId(Long userId) {
		return ownerRepository.findOwnerById(userId);
	}

	@Transactional
	public List<Owner> getOwner() {
		return (List<Owner>) ownerRepository.findAll();
	}

	//////////////////////////////////////////////////////////////////////////////

	@Transactional
	public Car createCar(Long cardId, String model, String year, String color) {
		Car car = new Car();
		car.setCarId(cardId);
		car.setColor(color);
		car.setModel(model);
		car.setYear(year);
		carRepository.save(car);
		return car;
	}

	@Transactional
	public Car getCarByCarId(Long carId) {
		return carRepository.findByCarId(carId);
	}

	@Transactional
	public List<Car> getAllCars() {
		return (List<Car>) carRepository.findAll();
	}

	@Transactional
	public Customer createCustomer(String userId, String password, List<Reminder> reminder, Car car, Profile profile) {
		Customer customer = new Customer();
		customer.setPassword(password);
		customer.setUserId(userId);
		customer.setReminders(reminder);
		customer.setCar(car);
		customer.setCustomerProfile(profile);
		customerRepository.save(customer);
		return customer;
	}

	@Transactional
	public Customer getCustomerById(Long id) {
		return customerRepository.findCustomerById(id);
	}

	@Transactional
	public List<Customer> getAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}

	@Transactional
	public TimeSlot createTimeSlot(Long timeSlotId, Time startTime, Time endTime, Date startDate, Date endDate,
			Integer garageSpot) {
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setTimeSlotId(timeSlotId);
		timeSlot.setStartDate(startDate);
		timeSlot.setStartTime(startTime);
		timeSlot.setEndDate(endDate);
		timeSlot.setEndTime(endTime);
		timeSlot.setGarageSpot(garageSpot); // TODO: change this one
		timeSlotRepository.save(timeSlot);
		return timeSlot;
	}

	@Transactional
	public TimeSlot getTimeSlotByTimeSlotId(Long timeSlotId) {
		return timeSlotRepository.findTimeSlotByTimeSlotId(timeSlotId);
	}

	@Transactional
	public List<TimeSlot> getAllTimeSlots() {
		return (List<TimeSlot>) timeSlotRepository.findAll();
	}

	// public List<TimeSlot> getTimeSlotByGarageSpot(Integer garageSpot) {
	// return timeSlotRepository.findTimeSlotByGarageSpot(garageSpot);
	// }

	public AppointmentReminder createAppointmentReminder(Date date, Time time, String message) {
		if (message == null) {
			throw new IllegalArgumentException("Message can't be null");
		}
		if (date.equals(null)) { // TODO: not sure of this
			throw new IllegalArgumentException("Date can't be null");
		}
		if (time.equals(null)) { // TODO: not sure of this
			throw new IllegalArgumentException("Time can't be null");
		}
		AppointmentReminder appointmentReminder = new AppointmentReminder();

		appointmentReminder.setDate(date);
		appointmentReminder.setTime(time);
		appointmentReminder.setMessage(message);

		appointmentReminderRepository.save(appointmentReminder);
		return appointmentReminder;

	}

	@Transactional
	public AppointmentReminder getAppointmentReminderById(Long id) {
		return appointmentReminderRepository.findAppointmentReminderByReminderId(id);
	}
	// @Transactional
	// public List<AppointmentReminder> getAppointmentReminderByCustomer(Customer
	// customer) {
	// return (List<AppointmentReminder>)
	// appointmentReminderRepository.findByCustomer(customer);
	// }
	//
	// @Transactional
	// public AppointmentReminder
	// getAppointmentReminderByCustomerAndAppointment(Customer customer,
	// Appointment appointment) {
	// return appointmentReminderRepository.findByCustomerAndAppointment(customer,
	// appointment);
	// }

	@Transactional
	public List<AppointmentReminder> getAllAppointmentReminders() {
		return (List<AppointmentReminder>) appointmentReminderRepository.findAll();

	}

	@Transactional
	public BookableService createBookableService(String name, int price, int duration) {
		if (name == null) {
			throw new IllegalArgumentException("Name can't be null");
		}
		if (price == 0) {
			throw new IllegalArgumentException("Price can't be 0");
		}
		if (price < 0) {
			throw new IllegalArgumentException("Price can't be negative");
		}
		if (duration < 0) {
			throw new IllegalArgumentException("Duration can't be negative");
		}
		if (duration == 0) {
			throw new IllegalArgumentException("Duration can't be equal to 0");
		}
		BookableService bookableService = new BookableService();
		bookableService.setDuration(duration);
		bookableService.setName(name);
		bookableService.setPrice(price);
		bookableServiceRepository.save(bookableService);
		return bookableService;
	}

	// @Transactional
	// public BookableService getBookableServiceByAppointment(Appointment
	// appointment) {
	// return
	// bookableServiceRepository.findBookableServiceByAppointments(appointment);
	// }
	@Transactional
	public BookableService getBookableServiceById(Long serviceId) {
		return bookableServiceRepository.findBookableServiceByServiceId(serviceId);
	}

	@Transactional
	public List<BookableService> getAllBookableServices() {
		return (List<BookableService>) bookableServiceRepository.findAll();
	}

	///////////////////////////////////////////////////////////////////////////

	@Transactional
	public EmergencyService createEmergencyService(String name, int price, String aLocation,
			FieldTechnician aFieldTechnician, Customer aCustomer, Receipt aReceipt) {
		EmergencyService emergencyService = new EmergencyService();
		emergencyService.setName(name);
		emergencyService.setPrice(price);
		emergencyService.setLocation(aLocation);
		emergencyService.setTechnician(aFieldTechnician);
		emergencyService.setCustomer(aCustomer);
		emergencyService.setReceipt(aReceipt);
		emergencyServiceRepository.save(emergencyService);
		return emergencyService;
	}

	@Transactional
	public EmergencyService getEmergencyServiceByServiceId(Long serviceId) {
		return emergencyServiceRepository.findEmergencyServiceByServiceId(serviceId);
	}

	@Transactional
	public List<EmergencyService> getAllEmergencyServices() {
		return (List<EmergencyService>) emergencyServiceRepository.findAll();
	}

	@Transactional
	public List<EmergencyService> getCustomerEmergencyServices(Customer customer) {
		return (List<EmergencyService>) emergencyServiceRepository.findByCustomer(customer);
	}

	@Transactional
	public EmergencyService getEmergencyServiceByReceipt(Receipt receipt) {
		return emergencyServiceRepository.findByReceipt(receipt);
	}

	///////////////////////////////////////////////////////////////////////////////

	@Transactional
	public GarageTechnician createGarageTechnician(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Name can't be null");
		}
		GarageTechnician garageTechnician = new GarageTechnician();
		garageTechnician.setName(name);
		garageTechnicianRepository.save(garageTechnician);
		return garageTechnician;
	}

	@Transactional
	public List<GarageTechnician> getAllGarageTechnicians() {
		return (List<GarageTechnician>) garageTechnicianRepository.findAll();
	}

	@Transactional
	public GarageTechnician getGarageTechnicianById(Long technicianId) {
		return garageTechnicianRepository.findGarageTechnicianByTechnicianId(technicianId);
	}
	/////////////////////////////////////////////////////////////////////////////////

	@Transactional
	public FieldTechnician createFieldTechnician(String name) {
		FieldTechnician fieldTechnician = new FieldTechnician();
		fieldTechnician.setName(name);

		fieldTechnicianRepository.save(fieldTechnician);
		return fieldTechnician;
	}

	@Transactional
	public FieldTechnician getFieldTechnicianById(Long technicianId) {
		return fieldTechnicianRepository.findFieldTechnicianByTechnicianId(technicianId);
	}

	@Transactional
	public List<FieldTechnician> getAllFieldTechnicians() {
		return (List<FieldTechnician>) fieldTechnicianRepository.findAll();
	}

	@Transactional
	public FieldTechnician getFieldTechnicianByName(String name) {
		return fieldTechnicianRepository.findFieldTechnicianByName(name);
	}

	//////////////////////////////////////////////////////////////////////////////////

	@Transactional
	public Business createBusiness(String aName, String aAddress, String aPhoneNumber, String aEmailAddress,
			List<BusinessHour> aBusinessHours, List<TimeSlot> regular) {

		boolean businessExist = false;

		Business tempBusiness = getBusinessByName(aName);

		if (tempBusiness != null) {
			businessExist = true;
		}

		if (businessExist) {
			throw new IllegalArgumentException("Business already exists");
		}

		if (aName == null || aName == "") {
			throw new IllegalArgumentException("Name cannot be empty");
		}
		if (aAddress == null || aAddress == "") {
			throw new IllegalArgumentException("Address cannot be empty");
		}
		if (aPhoneNumber == null || aPhoneNumber == "") {
			throw new IllegalArgumentException("Phone number cannot be empty");
		}
		if (aEmailAddress == null || aEmailAddress == "") {
			throw new IllegalArgumentException("Email address cannot be empty");
		}
		if (aBusinessHours == null) {
			throw new IllegalArgumentException("Business Hours cannot be null");
		}
		if (regular == null) {
			throw new IllegalArgumentException("Timeslots cannot be null");
		}

		Business business = new Business();
		business.setName(aName);
		business.setAddress(aAddress);
		business.setPhoneNumber(aPhoneNumber);
		business.setEmailAddress(aEmailAddress);
		business.setBusinessHours(aBusinessHours);
		business.setRegular(regular);
		businessRepository.save(business);
		return business;
	}

	// ?? do we have to save again if we are changing the fields?
	public void updateBusinessInformation(String aName, String aAddress, String aPhoneNumber, String aEmailAddress,
			List<BusinessHour> aBusinessHours, List<TimeSlot> regular) {

		Business business = getBusinessByName(aName);
		boolean addressBool = true;
		boolean phoneBool = true;
		boolean emailBool = true;
		boolean businessHourBool = true;
		boolean regularBool = true;

		if (aAddress == null || aAddress == "") {
			addressBool = false;
		}
		if (aPhoneNumber == null || aPhoneNumber == "") {
			phoneBool = false;
		}
		if (aEmailAddress == null || aEmailAddress == "") {
			emailBool = false;
		}
		if (aBusinessHours == null) {
			businessHourBool = false;
		}
		if (regular == null) {
			regularBool = false;
		}

		if (addressBool) {
			business.setAddress(aAddress);
		}
		if (phoneBool) {
			business.setPhoneNumber(aPhoneNumber);
		}
		if (emailBool) {
			business.setEmailAddress(aEmailAddress);
		}
		if (businessHourBool) {
			business.setBusinessHours(aBusinessHours);
		}
		if (regularBool) {
			business.setRegular(regular);
		}
		businessRepository.save(business); // ????
	}

	@Transactional
	public Business getBusinessById(Long businessId) {
		return businessRepository.findBusinessById(businessId);
	}

	@Transactional
	public List<Business> getBusiness() {
		return (List<Business>) businessRepository.findAll();
	}

	@Transactional
	public Business getBusinessByName(String name) {
		return businessRepository.findBusinessByName(name);
	}

	// @Transactional
	// public Business getBusinessByEmailAddress(String emailAddress) {
	// return businessRepository.findBusinessByEmailAddress(emailAddress);
	// }
	//
	// @Transactional
	// public Business getBusinessByPhoneNumber(String phoneNumber) {
	// return businessRepository.findBusinessByPhoneNumber(phoneNumber);
	// }

	// @Transactional
	// public Business getBusinessByBusinessHours(List<BusinessHour> businessHours)
	// {
	// return businessRepository.findBusinessByBusinessHours(businessHours);
	// }
	//
	// @Transactional
	// public Business getBusinessByTimeSlots(List<TimeSlot> timeSlot) {
	// return businessRepository.findBusinessByTimeSlot(timeSlot);
	// }

	////////////////////////////////////////////////////////////////////

	public DayOfWeek convertStringToDayOfWeek(String day) {
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

	@Transactional
	public BusinessHour createBusinessHour(String aDayOfWeek, Time aStartTime, Time aEndTime) {

		if (aDayOfWeek == null || aDayOfWeek.equals("")) {
			throw new IllegalArgumentException("Day of the week cannot be null");
		}

		if (aStartTime == null) {
			throw new IllegalArgumentException("Start time cannot be null");
		}

		if (aEndTime == null) {
			throw new IllegalArgumentException("Start time cannot be null");
		}

		if (aStartTime.equals(aEndTime) || aStartTime.compareTo(aEndTime) > 0) {
			throw new IllegalArgumentException("Business hour start time cannot be later or equal to end time");
		}

		BusinessHour businessHour = new BusinessHour();
		businessHour.setDayOfWeek(convertStringToDayOfWeek(aDayOfWeek));
		businessHour.setEndTime(aEndTime);
		businessHour.setStartTime(aStartTime);
		businessHourRepository.save(businessHour);
		return businessHour;
	}

	// public void updateBusinessHour(Long id, String dayOfWeek, Time startTime,
	// Time endTime) {
	// BusinessHour tempBusinessHour = getBusinessHourById(id);

	// if(tempBusinessHour!=null)

	// boolean dayOfWeekBool = true;

	// // if(dayOfWeek.equal(""));

	//

	public void deleteBusinessHour(BusinessHour businessHour) {
		// BusinessHour businessHour = getBusinessHourById(businessHourId);
		businessHourRepository.delete(businessHour);
	}

	@Transactional
	public BusinessHour getBusinessHourById(Long businessHourId) {
		return businessHourRepository.findBusinessHourByHourId(businessHourId);
	}

	@Transactional
	public List<BusinessHour> getAllBusinessHours() {
		return (List<BusinessHour>) businessHourRepository.findAll();
	}

	@Transactional
	public BusinessHour getBusinessHourByDayOfWeek(DayOfWeek dayOfWeek) {
		return businessHourRepository.findBusinessHourByDayOfWeek(dayOfWeek);
	}

	@Transactional
	public CheckupReminder createCheckupReminder(Date aDate, Time aTime, String aMessage) {
		CheckupReminder checkupReminder = new CheckupReminder();

		if (aDate == null) {
			throw new IllegalArgumentException("Date cannot be null");
		}
		if (aTime == null) {
			throw new IllegalArgumentException("Time cannot be null");
		}
		if (aMessage == null || aMessage.equals("")) {
			throw new IllegalArgumentException("Message cannot be empty");
		}

		checkupReminder.setDate(aDate);
		checkupReminder.setMessage(aMessage);
		checkupReminder.setTime(aTime);
		checkupReminderRepository.save(checkupReminder);
		return checkupReminder;
	}

	@Transactional
	public CheckupReminder getCheckupReminderById(Long checkupReminderId) {
		return checkupReminderRepository.findByReminderId(checkupReminderId);
	}

	@Transactional
	public CheckupReminder getCheckupReminderByMessage(String message) {
		return checkupReminderRepository.findByMessage(message);
	}

	// @Transactional
	// public List<CheckupReminder> getCheckupReminderByCustomer(Customer customer)
	// {
	// return (List<CheckupReminder>)
	// checkupReminderRepository.findCheckupReminderByCustomer(customer);
	// }

	// @Transactional
	// public CheckupReminder getCheckupReminderByDate(Date date) {
	// return checkupReminderRepository.findCheckupReminderByDate(date);
	// }
	//
	// @Transactional
	// public CheckupReminder getCheckupReminderByMessage(String message) {
	// return checkupReminderRepository.findCheckupReminderByMessage(message);
	// }
	//
	// @Transactional
	// public CheckupReminder getCheckupReminderByTime(Time time) {
	// return checkupReminderRepository.findCheckupReminderByTime(time);
	// }

	@Transactional
	public List<CheckupReminder> getAllCheckupReminder() {
		return (List<CheckupReminder>) checkupReminderRepository.findAll();
	}

	@Autowired
	ReminderRepository reminderRepository;

	@Transactional
	public List<Reminder> getAllReminder() {
		return (List<Reminder>) reminderRepository.findAll();
	}

	// @Transactional
	// public List<Reminder> getCustomerReminders(Customer customer) {
	// return reminderRepository.findByCustomer(customer);
	// }

	@Transactional
	public Appointment getAppointment(Long Id) {
		Optional<Appointment> app = appointmentRepository.findById(Id);
		if(app.isPresent()) {
			return appointmentRepository.findByAppointmentId(Id);
		}
		else {
			throw new IllegalArgumentException("No appointment with such ID exist!");
		}
	}

	@Transactional
	public List<Appointment> getAppointmentsByDate(Date date) {
		List<Appointment> appointments = new ArrayList<>();
		for (Appointment appointment : appointmentRepository.findAll()) {
			if (appointment.getTimeSlot().getStartDate() == date) {
				appointments.add(appointment);
			}
		}
		return appointments;
	}

	@Transactional
	public Customer getCustomerByUserId(String userId) {
		return customerRepository.findCustomerByUserId(userId);
	}

	@Transactional
	public BookableService getBookableServiceByServiceName(String name) {
		return bookableServiceRepository.findBookableServiceByName(name);
	}

	@Transactional
	public Profile getProfileByFirstAndLast(String firstName, String lastName) {
		
		for (Profile profile : profileRepository.findAll()) {
			if (profile.getFirstName().equals(firstName) && profile.getLastName().equals(lastName)) {
				return profile;
			}
		}
		return null;
	}

	public boolean isOverlap(TimeSlot timeSlot1, Time startTime, Time endTime, Integer garageSpot) {
		if (timeSlot1.getGarageSpot().equals(garageSpot)) {
			LocalTime S1 = timeSlot1.getStartTime().toLocalTime();
			LocalTime S2 = startTime.toLocalTime();
			LocalTime E1 = timeSlot1.getEndTime().toLocalTime();
			LocalTime E2 = endTime.toLocalTime();
			return S1.isBefore(E2) && S2.isBefore(E1);
		}
		return false;
	}

	public void deleteAppointmentById(Long appointmentId) {
		Optional<Appointment> app = appointmentRepository.findById(appointmentId);
		Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId);

		if(app.isPresent()) {
			appointmentRepository.deleteById(appointmentId);
		}
		else {
			throw new IllegalArgumentException("No appointment with such ID exist!");
		}
		appointmentReminderRepository.delete(appointment.getReminder());
		receiptRepository.delete(appointment.getReceipt());
		timeSlotRepository.delete(appointment.getTimeSlot());
		appointmentRepository.delete(appointment);
	}

	public void deleteAppointmentReminder(AppointmentReminder appointmentReminder) {
		appointmentReminderRepository.delete(appointmentReminder);
	}

	public void deleteBookableService(BookableService bookableService) {
		bookableServiceRepository.delete(bookableService);
	}

	public void deleteAdministrativeAssistant(AdministrativeAssistant administrativeAssistant) {
		administrativeAssistantRepository.delete(administrativeAssistant);
	}

	public void deleteGarageTechnician(GarageTechnician garageTechnician) {
		List<Appointment> appointments = getAllAppointments();
		for (Appointment appointment : appointments) {
			if (appointment.getTechnician().equals(garageTechnician)) {
				appointmentRepository.delete(appointment);
			}
		}
		garageTechnicianRepository.delete(garageTechnician);

	}

	public void editAppointmentReminder(AppointmentReminder appointmentReminder, String message) {
		appointmentReminder.setMessage(message);
		appointmentReminderRepository.save(appointmentReminder);
	}

	public void editBookableService(BookableService bookableService, String name, int price) {
		bookableService.setName(name);
		bookableService.setPrice(price);
		bookableServiceRepository.save(bookableService);
	}

	public void editAdministrativeAssistant(AdministrativeAssistant administrativeAssistant, String userId,
			String password) {
		administrativeAssistant.setUserId(userId);
		administrativeAssistant.setPassword(password);
	}
}