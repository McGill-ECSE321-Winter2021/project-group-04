package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*;
import ca.mcgill.ecse321.projectgroup04.model.*;
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour.DayOfWeek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AutoRepairShopSystemService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private ReceiptRepository receiptRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TimeSlotRepository timeSlotRepository;

	@Autowired
	private AdministrativeAssistantRepository administrativeAssistantRepository;

	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private AppointmentReminderRepository appointmentReminderRepository;

	@Autowired
	private BookableServiceRepository bookableServiceRepository;

	@Autowired
	private EmergencyServiceRepository emergencyServiceRepository;

	@Autowired
	private GarageTechnicianRepository garageTechnicianRepository;

	@Autowired
	private FieldTechnicianRepository fieldTechnicianRepository;

	@Autowired
	private BusinessRepository businessRepository;

	@Autowired
	private BusinessHourRepository businessHourRepository;

	@Autowired
	private CheckupReminderRepository checkupReminderRepository;
	
	private static User currentUser = null;
	
	/////////////////////////////////LOGIN LOGOUT///////////////////////////////////////////////
	
	@Transactional
	public Customer loginAsCustomer(String userId, String password) {
		if (userId == null || userId.trim().length() == 0) {
			throw new IllegalArgumentException("Please enter a valid UserId");
		}
		if (password == null || password.trim().length() == 0) {
			throw new IllegalArgumentException("Please enter a valid Password");
		}
		
		List<Customer> customers = getAllCustomers();
		//System.out.println(customers);
		Customer foundCustomer = null;
		
		for (Customer customer : customers) {
			/*System.out.println(customer.getUserId());
			System.out.println(customer.getPassword());
			
			System.out.println(userId);
			System.out.println(password);*/
			
			if (customer.getUserId().equals(userId) && customer.getPassword().equals(password)) {
				//System.out.println(customer.getUserId());
				currentUser = customer;
				foundCustomer = customer;
				break;
			}
		}
		
		if (foundCustomer == null) {
			throw new IllegalArgumentException("User does not exist, please register a new account or try again.");
		}
		
		return foundCustomer;
		
	}
	
	@Transactional
	public Owner loginAsOwner(String userId, String password) {
		if (userId == null || userId.trim().length() == 0) {
			throw new IllegalArgumentException("Please enter a valid UserId");
		}
		if (password == null || password.trim().length() == 0) {
			throw new IllegalArgumentException("Please enter a valid Password");
		}
		
		List<Owner> owners  = getOwner();
		Owner foundOwner = null;
		
		for (Owner owner : owners) {
			if (owner.getUserId().equals(userId) && owner.getPassword().equals(password)) {
				currentUser = owner;
				foundOwner = owner;
				break;
			}
		}
		
		if (foundOwner == null) {
			throw new IllegalArgumentException("User does not exist, please register a new account or try again.");
		}
		
		return foundOwner;
		
	}
	
	@Transactional
	public AdministrativeAssistant loginAsAdmin(String userId, String password) {
		if (userId == null || userId.trim().length() == 0) {
			throw new IllegalArgumentException("Please enter a valid UserId");
		}
		if (password == null || password.trim().length() == 0) {
			throw new IllegalArgumentException("Please enter a valid Password");
		}
		
		List<AdministrativeAssistant> admins  = getAllAdministrativeAssistants();
		AdministrativeAssistant foundAdmin = null;
		
		for (AdministrativeAssistant admin : admins) {
			if (admin.getUserId().equals(userId) && admin.getPassword().equals(password)) {
				currentUser = admin;
				foundAdmin = admin;
				break;
			}
		}
		
		if (foundAdmin == null) {
			throw new IllegalArgumentException("User does not exist, please register a new account or try again.");
		}
		
		return foundAdmin;
		
	}
	
	@Transactional
	public void logout() {
		currentUser = null;
	}
	
	@Transactional
	public User getLoggedUser() {
		return currentUser;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	

	@Transactional
	public Profile createProfile(String aAddressLine, String aPhoneNumber, String aFirstName, String aLastName,
			String aZipCode, String aEmailAddress) {
		if (aAddressLine == null || aAddressLine == "") {
			throw new IllegalArgumentException("Address Line can't be null or empty");
		}
		if (aPhoneNumber == null || aPhoneNumber == "") {
			throw new IllegalArgumentException("Phone Number can't be null or empty");
		}
		if (aPhoneNumber.length() < 10 || aPhoneNumber.length() > 10) {
			throw new IllegalArgumentException("Phone Number must be 10 characters long");
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
		Profile profile = profileRepository.findProfileByProfileId(aProfileId);
		if (profile != null) {
			return profile;
		} else {
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
		if (aTotalPrice < 0) {
			throw new IllegalArgumentException("Total Price can't be negative");
		}
		Receipt receipt = new Receipt();
		receipt.setTotalPrice(aTotalPrice);
		receiptRepository.save(receipt);
		return receipt;
	}

	@Transactional
	public Receipt getReceipt(Long aReceiptId) {
		Receipt receipt = receiptRepository.findReceiptByReceiptId(aReceiptId);
		if (receipt != null) {
			return receipt;
		} else {
			throw new IllegalArgumentException("No receipt with such ID exist!");
		}
	}

	@Transactional
	public List<Receipt> getAllReceipts() {
		return (List<Receipt>) receiptRepository.findAll();
	}

	@Transactional
	public List<Receipt> getCustomerReceipts(Customer customer) {
		if (customer == null) {
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
	public Appointment bookAppointment(String userId, String serviceName, Date date, Integer garageSpot, Time startTime,
			Long garageTechnicianId) {
		Customer customer = getCustomerByUserId(userId);
		if (customer == null) {
			throw new IllegalArgumentException("No customer with such userId!");
		}
		BookableService bookableService = getBookableServiceByServiceName(serviceName);
		if (bookableService == null) {
			throw new IllegalArgumentException("No Bookable Service with such name!");
		}

		java.sql.Time myTimeEnd = startTime;
		LocalTime localTimeEnd = myTimeEnd.toLocalTime();
		localTimeEnd = localTimeEnd.plusMinutes(bookableService.getDuration());
		java.sql.Time endTime = java.sql.Time.valueOf(localTimeEnd);
		if(garageSpot<1 || garageSpot >4) {
			throw new IllegalArgumentException("Please chose a garage sport between 1 and 4");
		}
		if(date.before(Date.valueOf(LocalDate.now()))){
			throw new IllegalArgumentException("Please book an appointment in the future");
		}
		for (BusinessHour businessHour : getAllBusinessHours()) {
			if (businessHour.getStartTime().after(startTime) || businessHour.getEndTime().before(endTime)) {
				throw new IllegalArgumentException("This time doesn't fall within business hours!");
			}
		}
		for (Appointment appointment : getAppointmentsByDate(date)) {
			if (isOverlap(appointment.getTimeSlot(), startTime, endTime, garageSpot)) {
				throw new IllegalArgumentException("This attempted booking overlaps with another!");
			}
		}
		TimeSlot timeSlot = createTimeSlot(startTime, endTime, date, date, garageSpot);
		Receipt receipt = createReceipt(bookableService.getPrice());
		GarageTechnician garageTechnician = getGarageTechnicianById(garageTechnicianId);
		date = Date.valueOf(date.toLocalDate().minusDays(1)); // again get back your date object
		AppointmentReminder appReminder = createAppointmentReminder(date, startTime,
				"You have an appointment in 24hours");
		addAppointmentReminderToCustomer(customer, appReminder);
		Appointment appointment = createAppointment(customer, bookableService, garageTechnician, timeSlot, appReminder,
				receipt);
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
		if (customer == null) {
			throw new IllegalArgumentException("Customer can't be null");
		}
		return appointmentRepository.findByCustomer(customer);
	}

	@Transactional
	public List<Appointment> getAppointmentsByTechnician(GarageTechnician garageTechnician) {
		if (garageTechnician == null) {
			throw new IllegalArgumentException("Garage Technician can't be null");
		}
		return appointmentRepository.findByTechnician(garageTechnician);
	}

	@Transactional
	public Appointment getAppointmentsByBookableServiceAndCustomer(BookableService service, Customer customer) {
		if (customer == null) {
			throw new IllegalArgumentException("Customer can't be null");
		}
		if (service == null) {
			throw new IllegalArgumentException("Service can't be null");
		}
		return appointmentRepository.findByBookableServicesAndCustomer(service, customer);
	}

	@Transactional
	public List<Appointment> getAllAppointments() {
		return (List<Appointment>) appointmentRepository.findAll();
	}

	@Transactional
	public AdministrativeAssistant getAdministrativeAssistantByUserId(String userId) {
		return administrativeAssistantRepository.findAdministrativeAssistantByUserId(userId);
	}

	@Transactional
	public AdministrativeAssistant createAdministrativeAssistant(String userId, String password) {

		AdministrativeAssistant existingAssistant = getAdministrativeAssistantByUserId(userId);

		if (existingAssistant != null) {
			throw new IllegalArgumentException("Administrative Assistant already exists");
		}
		// if (userId == null ) {
		// throw new IllegalArgumentException("Username can't be null");
		// }
		if (userId == "") {
			throw new IllegalArgumentException("Username can't be empty");
		}
		// if (password == null) {
		// throw new IllegalArgumentException("Password can't be null");
		// }
		if (password == "") {
			throw new IllegalArgumentException("Password can't be empty");
		}
		AdministrativeAssistant administrativeAssistant = new AdministrativeAssistant();
		administrativeAssistant.setUserId(userId);
		administrativeAssistant.setPassword(password);
		currentUser = administrativeAssistant;
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

		if (ownerExists()) {
			throw new IllegalArgumentException("Owner already exists");
		}
		if (userId == "") {
			throw new IllegalArgumentException("Username can't be empty");
		}
		if (password == "") {
			throw new IllegalArgumentException("Password can't be empty");
		}

		Owner owner = new Owner();
		owner.setUserId(userId);
		owner.setPassword(password);
		currentUser = owner;
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

	@Transactional
	public Owner deleteOwner(Owner owner) {
		ownerRepository.delete(owner);
		owner = null;
		return owner;
	}

	@Transactional
	public Owner editOwner(Owner owner, String userId, String password) {

		if (userId == owner.getUserId() && password == owner.getPassword()) {
			throw new IllegalArgumentException("You have to change the username or password or both");
		}

		owner.setUserId(userId);
		owner.setPassword(password);
		currentUser = owner;
		ownerRepository.save(owner);

		return owner;
	}

	@Transactional
	public boolean ownerExists() { // does owner exist?
		long ownerCount = ownerRepository.count();
		if (ownerCount > 0) {
			return true; // owner exists
		} else {
			return false; // owner does not exist
		}
	}

	@Transactional
	public Owner getOwnerByUserId(String userId) {
		return ownerRepository.findOwnerByUserId(userId);
	}

	//////////////////////////////////////////////////////////////////////////////

	@Transactional
	public Car createCar(String model, String year, String color) {

		if (model == null || model == "") {
			throw new IllegalArgumentException("Model can't be empty");
		}

		if (color == null || color == "") {
			throw new IllegalArgumentException("Color can't be empty");
		}

		if (year == null || year == "") {
			throw new IllegalArgumentException("Year can't be empty");
		}
		Car car = new Car();
		car.setColor(color);
		car.setModel(model);
		car.setYear(year);
		carRepository.save(car);
		return car;
	}

	@Transactional
	public List<Car> getCarByModelAndYearAndColor(String model, String year, String color) {
		return carRepository.findCarByModelAndYearAndColor(model, year, color);
	}

	@Transactional
	public List<Car> getAllCars() {
		return (List<Car>) carRepository.findAll();
	}

	@Transactional
	public Car deleteCar(Car car) {
		carRepository.delete(car);
		car = null;
		return car;
	}

	@Transactional
	public Car getCarByCarId(Long carId) {
		Car car = carRepository.findByCarId(carId);
		return car;
	}

	@Transactional
	public List<Reminder> getReminderByReminderId(Long reminderId) {
		Reminder reminder = reminderRepository.findReminderByReminderId(reminderId);
		List<Reminder> reminders = new ArrayList<Reminder>();
		reminders.add(reminder);
		return reminders;
	}

	@Transactional
	public Customer createCustomer(String userId, String password, List<Reminder> reminder, Car car, Profile profile) {

		if (userId == null || userId == "") {
			throw new IllegalArgumentException("userId can't be empty");
		}
		if (password == null || password == "") {
			throw new IllegalArgumentException("password can't be empty");
		}
		if (reminder == null) {
			throw new IllegalArgumentException("reminders can't be empty");
		}
		if (car == null) {
			throw new IllegalArgumentException("Car can't be empty");
		}
		if (profile == null) {
			throw new IllegalArgumentException("Profile can't be empty");
		}

		Customer test = customerRepository.findCustomerByUserId(userId);

		if (test != null) {
			throw new IllegalArgumentException("This customer already exists");
		}
		Customer customer = new Customer();
		customer.setPassword(password);
		customer.setUserId(userId);
		customer.setReminders(reminder);
		customer.setCar(car);
		customer.setCustomerProfile(profile);
		currentUser = customer;
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
	public void deleteCustomer(Customer customer) {
		customerRepository.delete(customer);
	}

	public Customer editCustomer(Customer customer, String Id, String password, List<Reminder> reminders, Car car,
			Profile profile) {

		if (customer == null) {
			throw new IllegalArgumentException("This customer does not exist");
		}

		customer.setUserId(Id);
		customer.setPassword(password);
		customer.setCar(car);
		customer.setCustomerProfile(profile);
		customer.setReminders(reminders);
		currentUser = customer;
		customerRepository.save(customer);
		return customer;
	}

	@Transactional
	public void addAppointmentReminderToCustomer(Customer customer, AppointmentReminder appointmentReminder) {
		if (customer == null) {
			throw new IllegalArgumentException("Customer can't be null!");
		}
		if (appointmentReminder == null) {
			throw new IllegalArgumentException("Appointment Reminder can't be null!");
		}
		List<Reminder> newReminders = new ArrayList<>();
		if (customer.getReminders() != null) {
			for (Reminder a : customer.getReminders()) {
				newReminders.add(a);
			}
		}
		newReminders.add(appointmentReminder);
		customer.setReminders(newReminders);
	}

	@Transactional
	public TimeSlot createTimeSlot(Time startTime, Time endTime, Date startDate, Date endDate, Integer garageSpot) {

		if (startDate == null || endDate == null) {
			throw new IllegalArgumentException("Date cannot be null");
		}
		if (startTime == null) {
			throw new IllegalArgumentException("startTime cannot be null");
		}
		if (endTime == null) {
			throw new IllegalArgumentException("endTime cannot be null");
		}
		if (garageSpot == null) {
			throw new IllegalArgumentException("garageSpot cannot be null");
		}

		if (startTime.after(endTime)) {
			throw new IllegalArgumentException("StartTime cannot be after endTime");
		}
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartDate(startDate);
		timeSlot.setStartTime(startTime);
		timeSlot.setEndDate(endDate);
		timeSlot.setEndTime(endTime);
		timeSlot.setGarageSpot(garageSpot);
		timeSlotRepository.save(timeSlot);
		return timeSlot;
	}

	@Transactional
	public TimeSlot getTimeSlotByStartDateAndStartTime(Date startDate, Time startTime) {
		return timeSlotRepository.findTimeSlotByStartDateAndStartTime(startDate, startTime);
	}

	@Transactional
	public List<TimeSlot> getAllTimeSlots() {
		return (List<TimeSlot>) timeSlotRepository.findAll();
	}

	@Transactional
	public TimeSlot deleteTimeSlot(TimeSlot timeSlot, Business business) {

		List<TimeSlot> timeSlots = business.getRegular();
		timeSlots.remove(timeSlot);
		business.setRegular(timeSlots);
		businessRepository.save(business);
		timeSlotRepository.delete(timeSlot);
		timeSlot = null;
		return timeSlot;
	}

	public AppointmentReminder createAppointmentReminder(Date date, Time time, String message) {
		if (message == "") {
			throw new IllegalArgumentException("Message can't be empty");
		}
		if (date == null) {
			throw new IllegalArgumentException("Date can't be null");
		}
		if (time == null) {
			throw new IllegalArgumentException("Time can't be null");
		}
		if (message == "") {
			throw new IllegalArgumentException("Message can't be empty");
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

	@Transactional
	public List<AppointmentReminder> getAllAppointmentReminders() {
		return (List<AppointmentReminder>) appointmentReminderRepository.findAll();

	}

	@Transactional
	public BookableService createBookableService(String name, int price, int duration) {

		BookableService existingService = getBookableServiceByServiceName(name);

		if (existingService != null) {
			throw new IllegalArgumentException("Bookable Service with this name already exists");
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
		if (name == "") {
			throw new IllegalArgumentException("Name can't be empty");
		}

		BookableService bookableService = new BookableService();
		bookableService.setDuration(duration);
		bookableService.setName(name);
		bookableService.setPrice(price);
		bookableServiceRepository.save(bookableService);
		return bookableService;
	}

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
	public EmergencyService createEmergencyService(String name, int price) {

		EmergencyService existingService = getEmergencyServiceByServiceName(name);

		if (existingService != null) {
			throw new IllegalArgumentException("Emergency Service with this name already exists");
		}

		if (name == "") {
			throw new IllegalArgumentException("Name can't be null");
		}
		if (price == 0) {
			throw new IllegalArgumentException("Price can't be 0");
		}
		if (price < 0) {
			throw new IllegalArgumentException("Price can't be negative");
		}

		EmergencyService emergencyService = new EmergencyService();

		emergencyService.setName(name);
		emergencyService.setPrice(price);
		emergencyServiceRepository.save(emergencyService);
		return emergencyService;
	}

	public EmergencyService bookEmergencyService(String bookingName, String serviceName, String aLocation,
			String userId, FieldTechnician aFieldTechnician) {

		EmergencyService bookableEmergencyService = new EmergencyService();
		EmergencyService emergencyService = getEmergencyServiceByServiceName(serviceName);

		if (aLocation == null) {
			throw new IllegalArgumentException("Location can't be null");
		}
		if (aFieldTechnician == null) {
			throw new IllegalArgumentException("Field Technician can't be null");
		}
		if (bookingName == null) {
			throw new IllegalArgumentException("Service Name can't be null");
		}

		if (aFieldTechnician.getIsAvailable() == false) { // if field technician is unavailable
			throw new IllegalArgumentException("Field Technician is currently unavailable");
		}

		if (emergencyService == null) {
			throw new IllegalArgumentException("No Emergency Service with such name!");
		}

		int price = emergencyService.getPrice();

		Receipt aReceipt = createReceipt(price);
		Customer customer = getCustomerByUserId(userId);

		if (customer == null) {
			throw new IllegalArgumentException("Customer can't be null");
		}

		bookableEmergencyService.setName(bookingName);

		bookableEmergencyService.setPrice(price);
		bookableEmergencyService.setLocation(aLocation);
		bookableEmergencyService.setTechnician(aFieldTechnician);
		aFieldTechnician.setIsAvailable(false);
		// System.out.println(aFieldTechnician.getIsAvailable());
		bookableEmergencyService.setCustomer(customer);
		bookableEmergencyService.setReceipt(aReceipt);
		emergencyServiceRepository.save(bookableEmergencyService);
		return bookableEmergencyService;
	}

	@Transactional
	public EmergencyService getEmergencyServiceByServiceName(String name) {
		return emergencyServiceRepository.findEmergencyServiceByName(name);
	}

	@Transactional
	public EmergencyService getEmergencyServiceByServiceId(Long serviceId) {
		EmergencyService emergencyService = emergencyServiceRepository.findEmergencyServiceByServiceId(serviceId);

		if (emergencyService == null) {
			throw new IllegalArgumentException("No Emergency Service with such ID exist!");
		}

		else {
			return emergencyService;
		}
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

	@Transactional
	public EmergencyService editEmergencyService(EmergencyService emergencyService, String name, int price) {

		if (name == emergencyService.getName() && price == emergencyService.getPrice()) {
			throw new IllegalArgumentException("You have to edit one of the fields");
		}
		EmergencyService existingEmergencyService = getEmergencyServiceByServiceName(name);
		if (existingEmergencyService != null) {
			throw new IllegalArgumentException("An emergency service with this name already exists");
		}

		emergencyService.setName(name);
		emergencyService.setPrice(price);
		emergencyServiceRepository.save(emergencyService);

		return emergencyService;
	}

	@Transactional
	public EmergencyService deleteEmergencyService(EmergencyService emergencyService) {
		emergencyServiceRepository.delete(emergencyService);
		emergencyService = null;
		return emergencyService;
	}

	@Transactional
	public EmergencyService endEmergencyService(EmergencyService emergencyService) {
		FieldTechnician fieldTechnician = emergencyService.getTechnician();
		fieldTechnician.setIsAvailable(true);
		return emergencyService;
	}

	///////////////////////////////////////////////////////////////////////////////

	@Transactional
	public GarageTechnician createGarageTechnician(String name) {

		if (name == "") {
			throw new IllegalArgumentException("Name can't be empty");
		}
		GarageTechnician existingGarageTechnician = getGarageTechnicianByName(name);
		if (existingGarageTechnician != null) {
			throw new IllegalArgumentException("Garage Technician with this name already exists");
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

	@Transactional
	public GarageTechnician getGarageTechnicianByName(String name) {
		return garageTechnicianRepository.findGarageTechnicianByName(name);
	}
	/////////////////////////////////////////////////////////////////////////////////

	@Transactional
	public FieldTechnician createFieldTechnician(String name) {

		if (name == "") {
			throw new IllegalArgumentException("Name can't be empty");
		}
		FieldTechnician existingFieldTechnician = getFieldTechnicianByName(name);
		if (existingFieldTechnician != null) {
			throw new IllegalArgumentException("Field Technician with this name already exists");
		}

		FieldTechnician fieldTechnician = new FieldTechnician();
		fieldTechnician.setName(name);
		fieldTechnician.setIsAvailable(true);

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

	@Transactional
	public FieldTechnician deleteFieldTechnician(FieldTechnician fieldTechnician) {
		List<EmergencyService> emergencyServices = (List<EmergencyService>) emergencyServiceRepository.findAll();

		for (EmergencyService emergencyService : emergencyServices) {
			if (emergencyService.getTechnician().equals(fieldTechnician)) {
				throw new IllegalArgumentException("The field technician is assigned to an emergency service!");
			}
		}
		fieldTechnicianRepository.delete(fieldTechnician);
		fieldTechnician = null;
		return fieldTechnician;

	}

	@Transactional
	public void editFieldTechnician(FieldTechnician fieldTechnician, String name) {
		fieldTechnician.setName(name);
		fieldTechnicianRepository.save(fieldTechnician);
	}

	//////////////////////////////////////////////////////////////////////////////////

	@Transactional
	public Business createBusiness(String aName, String aAddress, String aPhoneNumber, String aEmailAddress,
			List<BusinessHour> aBusinessHours, List<TimeSlot> regular) {

		boolean businessExist = false;
		List<Business> businessList = getBusiness();
		if (businessList.size() == 1) {
			throw new IllegalArgumentException("Only one business can exist");
		}

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
		} else if (!aEmailAddress.contains("@")) {
			throw new IllegalArgumentException("Email Address must contain @ character");
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
	public Business updateBusinessInformation(Long Id, String aName, String aAddress, String aPhoneNumber,
			String aEmailAddress, List<BusinessHour> aBusinessHours, List<TimeSlot> regular) {

		Business business = getBusinessById(Id);

		if (business == null) {
			throw new IllegalArgumentException("The business with this Id doesn't exist");
		}
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
			if (!aEmailAddress.contains("@")) {
				throw new IllegalArgumentException("Email Address must contain @ character");
			}
			business.setEmailAddress(aEmailAddress);
		}
		if (businessHourBool) {
			business.setBusinessHours(aBusinessHours);
		}
		if (regularBool) {
			business.setRegular(regular);
		}
		businessRepository.save(business); // ????
		return business;
	}

	@Transactional
	public Business getBusinessById(Long businessId) {
		return businessRepository.findBusinessById(businessId);
	}

	@Transactional
	public List<Business> getBusiness() {
		if (businessRepository.findAll() != null) {
			return (List<Business>) businessRepository.findAll();
		} else {
			return null;
		}
	}

	@Transactional
	public Business getBusinessByName(String name) {
		return businessRepository.findBusinessByName(name);
	}

	public Boolean deleteBusiness(Business business) {
		businessRepository.delete(business);
		return true;
	}

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

	@Transactional
	public BusinessHour createBusinessHour(String aDayOfWeek, Time aStartTime, Time aEndTime) {

		for (BusinessHour businessHour : getAllBusinessHours()) {
			if (businessHour.getDayOfWeek().equals(convertStringToDayOfWeek(aDayOfWeek))) {
				if (businessHour.getStartTime().equals(aStartTime)) {
					if (businessHour.getEndTime().equals(aEndTime)) {
						throw new IllegalArgumentException("These business hours already exist!");
					}
					throw new IllegalArgumentException(
							"Business hours with this start time already exist, update end time instead");
				}
			}
		}

		if (aStartTime == null) {
			throw new IllegalArgumentException("Start time cannot be null");
		}

		if (aEndTime == null) {
			throw new IllegalArgumentException("End time cannot be null");
		}

		if (aStartTime.after(aEndTime)) {
			throw new IllegalArgumentException("Start time has to be before end Time");
		}

		if (aDayOfWeek == null || aDayOfWeek.equals("")) {
			throw new IllegalArgumentException("Day of the week cannot be null");
		}

		if (aStartTime.equals(aEndTime)) {
			throw new IllegalArgumentException("Business hour start time cannot equal to end time");
		}

		BusinessHour businessHour = new BusinessHour();
		businessHour.setDayOfWeek(convertStringToDayOfWeek(aDayOfWeek));
		businessHour.setEndTime(aEndTime);
		businessHour.setStartTime(aStartTime);
		businessHourRepository.save(businessHour);
		return businessHour;
	}

	public BusinessHour updateBusinessHour(Long id, String dayOfWeek, Time startTime, Time endTime) {

		BusinessHour tempBusinessHour = getBusinessHourById(id);
		for (BusinessHour businessHour : getAllBusinessHours()) {
			if (businessHour.getDayOfWeek().equals(convertStringToDayOfWeek(dayOfWeek))) {
				if (businessHour.getStartTime().equals(startTime)) {
					if (businessHour.getEndTime().equals(endTime)) {
						throw new IllegalArgumentException("These business hours already exist!");
					}
				}
			}
		}

		Boolean dayBool = true;
		Boolean startTimeBool = true;
		Boolean endTimeBool = true;

		if (startTime == null) {
			startTimeBool = false;
		}

		if (endTime == null) {
			endTimeBool = false;
		}

		if (startTimeBool && endTimeBool) {
			if (startTime.after(endTime)) {
				throw new IllegalArgumentException("Start time has to be before end Time");
			}
		}

		if (dayOfWeek == null || dayOfWeek.equals("")) {
			dayBool = false;
		}

		if (dayBool)
			tempBusinessHour.setDayOfWeek(convertStringToDayOfWeek(dayOfWeek));
		if (startTimeBool)
			tempBusinessHour.setStartTime(startTime);
		if (endTimeBool)
			tempBusinessHour.setEndTime(endTime);
		businessHourRepository.save(tempBusinessHour);
		return tempBusinessHour;
	}

	public Boolean deleteBusinessHour(BusinessHour businessHour, Business business) {
		// List<Business> businesses = getBusiness();
		// Business business = getBusinessById(businessId);
		List<BusinessHour> businessHours = business.getBusinessHours();
		businessHours.remove(businessHour);
		business.setBusinessHours(businessHours);
		businessRepository.save(business);
		businessHourRepository.delete(businessHour);
		return true;
	}

	public Boolean deleteAllBusinessHours(Business business) {
		List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
		business.setBusinessHours(businessHours);
		businessRepository.save(business);
		businessHourRepository.deleteAll();
		return true;
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
	public CheckupReminder editCheckupReminder(Long id, Date aDate, Time aTime, String aMessage) {
		CheckupReminder checkupReminder = checkupReminderRepository.findByReminderId(id);

		Boolean dateBool = true;
		Boolean timeBool = true;
		Boolean messageBool = true;

		if (aDate == null) {
			dateBool = false;
		}
		if (aTime == null) {
			timeBool = false;
		}
		if (aMessage == null || aMessage.equals("")) {
			messageBool = false;
		}

		if (dateBool)
			checkupReminder.setDate(aDate);
		if (messageBool)
			checkupReminder.setMessage(aMessage);
		if (timeBool)
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

	@Transactional
	public Appointment getAppointment(Long Id) {
		Appointment appointment = appointmentRepository.findByAppointmentId(Id);
		if (appointment != null) {
			return appointment;
		} else {
			throw new IllegalArgumentException("No appointment with such ID exist!");
		}
	}

	@Transactional
	public List<Appointment> getAppointmentsByDate(Date date) {
		List<Appointment> appointments = new ArrayList<>();
		for (Appointment appointment : appointmentRepository.findAll()) {
			if (appointment.getTimeSlot().getStartDate().equals(date)) {
				appointments.add(appointment);
			}
		}
		return appointments;
	}

	@Transactional
	public Customer getCustomerByUserId(String userId) {
		Customer customer = customerRepository.findCustomerByUserId(userId);
		if (customer == null) {
			throw new IllegalArgumentException("No customer with such userId!");
		}
		return customer;
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
		throw new IllegalArgumentException("No Profile with such First Name and Last Name");
	}
	
	@Transactional
	public Profile getProfileByUserId(String userId) {
		System.out.println("enterred the function");
		if(userId=="" || userId==null) {
			throw new IllegalArgumentException("userId can't be null or empty");
		}
		System.out.println("gonna get user");
		Customer customer = customerRepository.findCustomerByUserId(userId);
		if(customer==null) {
			throw new IllegalArgumentException("No customer with such userId exist");
		}
		System.out.println("gonna get profile");
		Profile profile = customer.getCustomerProfile();
		if(profile==null) {
			throw new IllegalArgumentException("This customer does not have a valid profile");
		}
		System.out.println("gonna return");

		return profile;
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

	public Appointment deleteAppointment(Appointment appointment, LocalTime testTime, LocalDate testDate) {
		if (appointment == null) {
			throw new IllegalArgumentException("No appointment with such ID exist!");
		}
		LocalTime now = LocalTime.now();
		if (testTime != null) {
			now = testTime;
		}
		LocalDate today = LocalDate.now();
		if (testDate != null) {
			today = testDate;
		}
		LocalDate appDate = appointment.getTimeSlot().getStartDate().toLocalDate();
		LocalTime appTime = appointment.getTimeSlot().getStartTime().toLocalTime();
		if (today.equals(appDate)) {
			throw new IllegalArgumentException("Cannot cancel appointment on same day!");
		}
		if (today.plusDays(1).equals(appDate) && now.isAfter(appTime)) {
			throw new IllegalArgumentException("Cannot cancel appointment less than 24hours!");
		}
		Customer customer = appointment.getCustomer();
		AppointmentReminder appointmentReminder = appointment.getReminder();
		Receipt receipt = appointment.getReceipt();
		TimeSlot timeSlot = appointment.getTimeSlot();
		appointmentRepository.delete(appointment);
		if (customer.getReminders() != null) {
			customer.getReminders().remove(appointmentReminder);
		}
		appointmentReminderRepository.delete(appointmentReminder);
		receiptRepository.delete(receipt);
		timeSlotRepository.delete(timeSlot);
		appointment.setBookableServices(null);
		appointment.setCustomer(null);
		appointment.setReceipt(null);
		appointment.setReminder(null);
		appointment.setTechnician(null);
		appointment.setTimeSlot(null);
		appointment.setAppointmentId(null);
		appointment = null;
		return appointment;
	}

	public AppointmentReminder deleteAppointmentReminder(AppointmentReminder appointmentReminder) {
		appointmentReminderRepository.delete(appointmentReminder);
		appointmentReminder = null;
		return appointmentReminder;
	}

	public BookableService deleteBookableService(BookableService bookableService) {
		bookableServiceRepository.delete(bookableService);
		bookableService = null;
		return bookableService;
	}

	public AdministrativeAssistant deleteAdministrativeAssistant(AdministrativeAssistant administrativeAssistant) {
		administrativeAssistantRepository.delete(administrativeAssistant);
		administrativeAssistant = null;
		return administrativeAssistant;
	}

	public GarageTechnician deleteGarageTechnician(GarageTechnician garageTechnician) {

		List<Appointment> appointmentsList = getAllAppointments();

		for (Appointment appointment : appointmentsList) {
			if (appointment.getTechnician().equals(garageTechnician)) {
				throw new IllegalArgumentException("This garage technician still has appointments");
			}

		}
		garageTechnicianRepository.delete(garageTechnician);
		garageTechnician = null;
		return garageTechnician;

	}

	public AppointmentReminder editAppointmentReminder(AppointmentReminder appointmentReminder, String message) {
		if (message == appointmentReminder.getMessage()) {
			throw new IllegalArgumentException("You have to change the message");
		}
		appointmentReminder.setMessage(message);
		appointmentReminderRepository.save(appointmentReminder);
		return appointmentReminder;
	}

	public BookableService editBookableService(BookableService bookableService, String name, int duration, int price) {
		if (name == bookableService.getName() && duration == bookableService.getDuration()
				&& price == bookableService.getPrice()) {
			throw new IllegalArgumentException("You have to edit one of the fields");
		}
		BookableService existingBookableService = getBookableServiceByServiceName(name);
		if (existingBookableService != null) {
			throw new IllegalArgumentException("A bookable service with this name already exists");
		}
		bookableService.setName(name);
		bookableService.setDuration(duration);
		bookableService.setPrice(price);
		bookableServiceRepository.save(bookableService);
		return bookableService;
	}

	public AdministrativeAssistant editAdministrativeAssistant(AdministrativeAssistant administrativeAssistant,
			String userId, String password) {
		if (userId == administrativeAssistant.getUserId() && password == administrativeAssistant.getPassword()) {
			throw new IllegalArgumentException("You have to change the username or password or both");
		}
		administrativeAssistant.setUserId(userId);
		administrativeAssistant.setPassword(password);
		currentUser = administrativeAssistant;
		administrativeAssistantRepository.save(administrativeAssistant);
		return administrativeAssistant;
	}

	public Profile editProfile(Long profileId, String firstName, String lastName, String emailAddress,
			String phoneNumber, String addressLine, String zipCode) {
		Profile profile = getProfile(profileId);
		if (addressLine == null || addressLine == "") {
			throw new IllegalArgumentException("Address Line can't be null or empty");
		}
		if (phoneNumber == null || phoneNumber == "") {
			throw new IllegalArgumentException("Phone Number can't be null or empty");
		}
		if (phoneNumber.length() < 10 || phoneNumber.length() > 10) {
			throw new IllegalArgumentException("Phone Number must be 10 characters long");
		}
		if (firstName == null || firstName == "") {
			throw new IllegalArgumentException("First Name can't be null or empty");
		}
		if (lastName == null || lastName == "") {
			throw new IllegalArgumentException("Last Name can't be null or empty");
		}
		if (zipCode == null || zipCode == "") {
			throw new IllegalArgumentException("Zip Code can't be null or empty");
		}
		if (zipCode.length() < 6 || zipCode.length() > 6) {
			throw new IllegalArgumentException("Zip Code must be 6 characters long");
		}
		if (emailAddress == null || emailAddress == "") {
			throw new IllegalArgumentException("Email Address can't be null or empty");
		}
		if (!emailAddress.contains("@")) {
			throw new IllegalArgumentException("Email Address must contain @ character");
		}
		profile.setAddressLine(addressLine);
		profile.setEmailAddress(emailAddress);
		profile.setFirstName(firstName);
		profile.setLastName(lastName);
		profile.setPhoneNumber(phoneNumber);
		profile.setZipCode(zipCode);
		profileRepository.save(profile);
		return profile;
	}
	
		public void editCar(Car car, String model, String year, String color) {

		if ( car == null){
			throw new IllegalArgumentException("This car does not exist");
		}
		if (model == null || model == "") {
			throw new IllegalArgumentException("Model can't be empty");
		}

		if (color == null || color == "") {
			throw new IllegalArgumentException("Color can't be empty");
		}

		if (year == null || year == "") {
			throw new IllegalArgumentException("Year can't be empty");
		}
		car.setColor(color);
		car.setModel(model);
		car.setYear(year);
		carRepository.save(car);

	}


}
