package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*;
import ca.mcgill.ecse321.projectgroup04.model.*;
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
	
	@Transactional
	public Profile createProfile(String aAddressLine, String aPhoneNumber,String aFirstName, String aLastName, String aZipCode, String aEmailAddress, Customer aCustomer) {
		Profile profile = new Profile();
		profile.setAddressLine(aAddressLine);
		profile.setCustomer(aCustomer);
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
	public Profile getProfileByCustomer(Customer aCustomer) {
		Profile profile = profileRepository.findByCustomer(aCustomer);
		return profile;
	}
	
	@Transactional
	public List<Profile> getAllProfiles(){
		return (List<Profile>) profileRepository.findAll();
	}
	
	@Transactional
	public Receipt createReceipt(Appointment aAppointment, double aTotalPrice) {
		Receipt receipt = new Receipt();
		receipt.setAppointment(aAppointment);
		receipt.setTotalPrice(aTotalPrice);
		return receipt;
	}
	
	@Transactional
	public Receipt getReceipt(Long aReceiptId) {
		Receipt receipt = receiptRepository.findReceiptByReceiptId(aReceiptId);
		return receipt;
	}
	
	@Transactional
	public Receipt getReceiptByAppointment(Appointment aAppointment) {
		Receipt receipt = receiptRepository.findByAppointment(aAppointment);
		return receipt;
	}
	
	@Transactional
	public List<Receipt> getAllReceipts(){
		return (List<Receipt>) receiptRepository.findAll();
	}
	
	@Transactional
	public List<Receipt> getCustomerReceipts(Customer customer){
		List<Receipt> customerReceipts = new ArrayList<>();
		for (Appointment a: appointmentRepository.findByCustomer(customer)) {
			customerReceipts.add(a.getReceipt());
		}
		return customerReceipts;
	}
	
	@Transactional
	public Appointment createAppointment(Customer aCustomer, BookableService aBookableService, GarageTechnician aGarageTechnician, TimeSlot aTimeSlot, AppointmentReminder aAppointmentReminder, Receipt aReceipt, AutoRepairShop aAutoRepairShop) {
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
	public List<Appointment> getAppointmentsByCustomer(Customer customer){
		return appointmentRepository.findByCustomer(customer);
	}
	
	@Transactional
	public List<Appointment> getAppointmentsByTechnician(GarageTechnician garageTechnician){
		return appointmentRepository.findByTechnician(garageTechnician);
	}
	
	@Transactional
	public List<Appointment> getAppointmentsByBookableService(BookableService service){
		return appointmentRepository.findByBookableService(service);
	}
	
	
	@Transactional
	public List<Appointment> getAllAppointments(){
		return (List<Appointment>) appointmentRepository.findAll();
	}
	
	@Transactional
	public Car createCar(String model, String year, String color, Customer customer) {
		Car car = new Car();
		car.setColor(color);
		car.setModel(model);
		car.setOwner(customer);
		car.setYear(year);
		carRepository.save(car);
		return car;
	}
	
	@Transactional
	public Car getCarByCarId(Long carId) {
		return carRepository.findByCarId(carId);
	}
	
	@Transactional
	public Customer createCustomer(String userId, String password, List<Appointment> appointment, AutoRepairShop auto, List<Reminder> reminder, Car car, Profile profile) {
		Customer customer = new Customer();
		customer.setAppointments(appointment);
		customer.setAutoRepairShop(auto);
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
	public TimeSlot createTimeSlot(Time startTime, Time endTime, Date startDate, Date endDate, GarageSpot garageSpot, AutoRepairShop auto) {
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartDate(startDate);
		timeSlot.setStartTime(startTime);
		timeSlot.setEndDate(endDate);
		timeSlot.setEndTime(endTime);
		timeSlot.setGarageSpot(garageSpot);
		timeSlot.setAutoRepairShop(auto);
		timeSlotRepository.save(timeSlot);
		return timeSlot;
		}
	
	@Transactional
	public TimeSlot getTimeSlotByTimeSlotId(Long timeSlotId) {
		return timeSlotRepository.findTimeSlotByTimeSlotId(timeSlotId);
	}
}
