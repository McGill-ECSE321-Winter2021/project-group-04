package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*;
import ca.mcgill.ecse321.projectgroup04.model.*;
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour.DayOfWeek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

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
		return profile;
	}

	@Transactional
	public List<Profile> getAllProfiles() {
		return (List<Profile>) profileRepository.findAll();
	}

	@Transactional
	public Receipt createReceipt(double aTotalPrice) {
		Receipt receipt = new Receipt();
		receipt.setTotalPrice(aTotalPrice);
		return receipt;
	}

	@Transactional
	public Receipt getReceipt(Long aReceiptId) {
		Receipt receipt = receiptRepository.findReceiptByReceiptId(aReceiptId);
		return receipt;
	}

	@Transactional
	public List<Receipt> getAllReceipts() {
		return (List<Receipt>) receiptRepository.findAll();
	}

	@Transactional
	public List<Receipt> getCustomerReceipts(Customer customer) {
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
			Receipt aReceipt, AutoRepairShop aAutoRepairShop) {
		Appointment appointment = new Appointment();
		appointment.setAutoRepairShop(aAutoRepairShop);
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
		return appointmentRepository.findByCustomer(customer);
	}

	@Transactional
	public List<Appointment> getAppointmentsByTechnician(GarageTechnician garageTechnician) {
		return appointmentRepository.findByTechnician(garageTechnician);
	}

	@Transactional
	public Appointment getAppointmentsByBookableServiceAndCustomer(BookableService service, Customer customer) {
		return appointmentRepository.findByBookableServicesAndCustomer(service, customer);
	}

	@Transactional
	public List<Appointment> getAllAppointments() {
		return (List<Appointment>) appointmentRepository.findAll();
	}

	@Transactional
	public AdministrativeAssistant createAdministrativeAssistant(String userId, String password) {
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
	public Car createCar(String model, String year, String color) {
		Car car = new Car();
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
	public TimeSlot createTimeSlot(Time startTime, Time endTime, Date startDate, Date endDate, Integer garageSpot) {
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartDate(startDate);
		timeSlot.setStartTime(startTime);
		timeSlot.setEndDate(endDate);
		timeSlot.setEndTime(endTime);
		// timeSlot.setGarageSpot(garageSpot); //TODO: change this one
		timeSlotRepository.save(timeSlot);
		return timeSlot;
	}

	@Transactional
	public TimeSlot getTimeSlotByTimeSlotId(Long timeSlotId) {
		return timeSlotRepository.findTimeSlotByTimeSlotId(timeSlotId);
	}

	// public List<TimeSlot> getTimeSlotByGarageSpot(Integer garageSpot) {
	// return timeSlotRepository.findTimeSlotByGarageSpot(garageSpot);
	// }

	public AppointmentReminder createAppointmentReminder(Date date, Time time, String message) {
		AppointmentReminder appointmentReminder = new AppointmentReminder();

		appointmentReminder.setDate(date);
		appointmentReminder.setTime(time);
		appointmentReminder.setMessage(message);

		appointmentReminderRepository.save(appointmentReminder);
		return appointmentReminder;

	}

	@Transactional 
	public AppointmentReminder getAppointmentReminderById(Long id){
		return appointmentReminderRepository.findAppointmentReminderByReminderId(id)
;	}
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
	public BookableService createBookableService( String name, int price, int duration) {
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

	// @Transactional
	// public Business getBusinessByName(String name) {
	// return businessRepository.findBusinessByName(name);
	// }

	@Transactional
	public Business getBusinessById(Long businessId) {
		return businessRepository.findBusinessById(businessId);
	}

	@Transactional
	public List<Business> getBusiness() {
		return (List<Business>) businessRepository.findAll();
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

	@Transactional
	public BusinessHour createBusinessHours(DayOfWeek aDayOfWeek, Time aStartTime, Time aEndTime) {
		BusinessHour businessHour = new BusinessHour();
		businessHour.setDayOfWeek(aDayOfWeek);
		businessHour.setEndTime(aEndTime);
		businessHour.setStartTime(aStartTime);
		businessHourRepository.save(businessHour);
		return businessHour;
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
		return appointmentRepository.findByAppointmentId(Id);
	}
	@Transactional
	public List<Appointment> getAppointmentsByDate(Date date){
		List<Appointment> appointments = new ArrayList<>();
		for(Appointment appointment : appointmentRepository.findAll()) {
			if(appointment.getTimeSlot().getStartDate()==date) {
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
	
}