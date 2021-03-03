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
	AdministrativeAssistantRepository administrativeAssistantRepository;
	
	@Autowired
	AppointmentReminderRepository appointmentReminderRepository;
	
	@Autowired 
	BookableServiceRepository bookableServiceRepository;
	
	@Autowired
	GarageTechnicianRepository garageTechnicianRepository;
	
	
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
	public AdministrativeAssistant createAdministrativeAssistant(AutoRepairShop aAutoRepairShop, String userId, String password) {
		AdministrativeAssistant administrativeAssistant = new AdministrativeAssistant();
		administrativeAssistant.setAutoRepairShop(aAutoRepairShop);
		administrativeAssistant.setUserId(userId);
		administrativeAssistant.setPassword(password);
		administrativeAssistantRepository.save(administrativeAssistant);
		return administrativeAssistant;
		
	}
	
	@Transactional
	public AdministrativeAssistant getAdministrativeAssistantByUserId(String userId) {
		return administrativeAssistantRepository.findAdministrativeAssistantByUserId(userId);
	}
	
	@Transactional
	public List<AdministrativeAssistant> getAllAdministrativeAssistants() {
		return (List<AdministrativeAssistant>) administrativeAssistantRepository.findAll();
	}
	
	
	@Transactional
	public AppointmentReminder createAppointmentReminder(AutoRepairShop aAutoRepairShop,Appointment appointment, Customer customer, Date date, Time time, String message ) {
		AppointmentReminder appointmentReminder = new AppointmentReminder();
		appointmentReminder.setCustomer(customer);
		appointmentReminder.setDate(date);
		appointmentReminder.setTime(time);
		appointmentReminder.setMessage(message);
		appointmentReminder.setAppointment(appointment);
		appointmentReminderRepository.save(appointmentReminder);
		return appointmentReminder;

	}
	
	@Transactional
	public List<AppointmentReminder> getAppointmentReminderByCustomer(Customer customer) {
		return (List<AppointmentReminder>) appointmentReminderRepository.findAppointmentReminderByCustomer(customer);
	}
	
	@Transactional
	public AppointmentReminder getAppointmentReminderByCustomerAndAppointment(Customer customer, Appointment appointment) {
		return appointmentReminderRepository.findByCustomerAndAppointment(customer, appointment);
	}
	
	@Transactional
	public List<AppointmentReminder> getAllAppointmentReminders() {
		return (List<AppointmentReminder>) appointmentReminderRepository.findAll();

	}
	
	@Transactional 
	public BookableService createBookableService(AutoRepairShop autoRepairShop, String name, int price, int duration ) {
		BookableService bookableService = new BookableService();
		bookableService.setAutoRepairShop(autoRepairShop);
		bookableService.setDuration(duration);
		bookableService.setName(name);
		bookableService.setPrice(price);
		bookableServiceRepository.save(bookableService);
		return bookableService;
	}
	
	@Transactional
	public BookableService getBookableServiceByAppointment(Appointment appointment) {
		return bookableServiceRepository.findBookableServiceByAppointments(appointment);
	}
	
	@Transactional
	public List <BookableService> getAllBookableServices(){
		return (List <BookableService>) bookableServiceRepository.findAll();
	}
	
	@Transactional
	public GarageTechnician createGarageTechnician(AutoRepairShop autoRepairShop, String name, List<Appointment> appointments ) {
		GarageTechnician garageTechnician = new GarageTechnician();
		garageTechnician.setName(name);
		garageTechnician.setAppointments(appointments);
		garageTechnicianRepository.save(garageTechnician);
		return garageTechnician;
	}
	
	public List<GarageTechnician> getAllGarageTechnicians(){
		return (List<GarageTechnician>) garageTechnicianRepository.findAll();
	}
	
	

}
