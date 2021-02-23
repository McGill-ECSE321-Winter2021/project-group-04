package ca.mcgill.ecse321.projectgroup04.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup04.model.*;
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour.DayOfWeek;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestAutoRepairShopSystemPersistence {

    @Autowired
    private AdministrativeAssistantRepository administrativeAssistantRepository;
    @Autowired
    private AppointmentReminderRepository appointmentReminderRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private BookableServiceRepository bookableServiceRepository;
    @Autowired
    private TimeSlotRepository timeSlotRepository;
    @Autowired
    private TechnicianRepository technicianRepository;
    @Autowired
    private GarageTechnicianRepository garageTechnicianRepository;
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private BusinessHourRepository businessHourRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CheckupReminderRepository checkupReminderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private ReminderRepository reminderRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private AutoRepairShopSystemRepository autoRepairShopSystemRepository;
    // @Autowired
    // private EmergencyServiceRepository emergencyServiceRepository;
    // @Autowired
    // private FieldTechRepository fieldTechRepository;
    // @Autowired
    // private GarageSpotRepository garageSpotRepository;
    // @Autowired
    // private OwnerRepository ownerRepository;

    @AfterEach
    public void clearDatabase() {
        businessRepository.deleteAll();
        businessHourRepository.deleteAll();
        appointmentRepository.deleteAll();
        appointmentReminderRepository.deleteAll();
        administrativeAssistantRepository.deleteAll();
        bookableServiceRepository.deleteAll();
        technicianRepository.deleteAll();
        timeSlotRepository.deleteAll();
        carRepository.deleteAll();
        customerRepository.deleteAll();
        checkupReminderRepository.deleteAll();
    }
    // @Test
    // public void testPersistAndLoadAdministrativeAssistant(){
    // Long id = (long) 123456;
    // String userId = "qq";
    // String password = "TestAdministrativePassword";
    // AdministrativeAssistant ad = new AdministrativeAssistant();
    // ad.setId(id);
    // ad.setUserID(userId);
    // ad.setPassword(password);
    // administrativeAssistantRepository.save(ad);
    // System.out.print(ad.getId());
    // System.out.println(ad.getId().getClass());
    // ad = null;
    // ad = administrativeAssistantRepository.findAdminstrativeAssistantById(id);
    // assertNotNull(ad);
    // assertEquals(id, ad.getId());
    // }

    // @Test
    // public void testPersistAndLoadBookableService(){
    // Long id = (long) 1234;
    // int duration = 30;
    // String name = "wash";
    // int price = 10;
    // BookableService bs = new BookableService();
    // Long id = bs.getServiceID();
    // bs.setDuration(duration);
    // bs.setName(name);
    // bs.setServiceID(id);
    // bs.setPrice(price);
    // bookableServiceRepository.save(bs);
    //
    // bs = null;
    // bs = bookableServiceRepository.findBookableServiceByServiceID(id);
    // assertNotNull(bs);
    // assertEquals(name, bs.getName());
    // assertEquals(id, bs.getServiceID());
    // assertEquals(duration, bs.getDuration());
    // assertEquals(price, bs.getPrice());
    // }

    // @Test
    // public void testPersistAndLoadTimeSlot(){
    // Time startTime = Time.valueOf("10:00");
    // Time endTime = Time.valueOf("11:00");
    // Date startDate = Date.valueOf("2021-03-19");
    // Date endDate = Date.valueOf("2021-03-19");
    // Long timeSlotID = (long) 45;
    // TimeSlot ts = new TimeSlot();
    // ts.setStartTime(startTime);
    // ts.setEndTime(endTime);
    // ts.setStartDate(startDate);
    // ts.setEndDate(endDate);
    // ts.setTimeSlotID(timeSlotID);
    //
    // int GSnum = 123;
    // GarageSpot garageSpot = new GarageSpot();
    // garageSpot.setSpotNumber(GSnum);
    //
    // ArrayList<TimeSlot> times = new ArrayList<TimeSlot>();
    // times.add(ts);
    // garageSpot.setTimeSlot(times);
    //
    // ts.setGarageSpot(garageSpot);
    //
    // timeSlotRepository.save(ts);
    //
    // ts = null;
    //
    // ts = timeSlotRepository.findTimeSlotByTimeSlotID(timeSlotID);
    //
    // assertNotNull(ts);
    // assertEquals(startTime, ts.getStartTime());
    // assertEquals(endTime, ts.getEndTime());
    // assertEquals(startDate, ts.getStartDate());
    // assertEquals(endDate, ts.getEndDate());
    // assertEquals(timeSlotID, ts.getTimeSlotID());
    // assertEquals(garageSpot, ts.getGarageSpot());
    // }
    //
    // @Test
    // public void testPersistAndLoadAppointment(){
    // Long appointmentID = (long)12;
    //
    // Long serviceID = (long)12345;
    // BookableService service = new BookableService();
    // service.setServiceID(serviceID);
    //
    // Long technicianID = (long) 987;
    // GarageTechnician technician = new GarageTechnician();
    // technician.setTechnicianID(technicianID);
    //
    // String customerID = "234";
    // Customer customer = new Customer();
    // customer.setUserID(customerID);
    //
    // String timeString = "10:00";
    // Time startTime = Time.valueOf(time);
    // Time endTime = Time.valueOf("11:00");
    // Date startDate = Date.valueOf("2021-03-19");
    // Date endDate = Date.valueOf("2021-03-19");
    // Date startDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY,
    // 31));
    // Date endDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
    // Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
    // Time endTime = java.sql.Time.valueOf(LocalTime.of(12, 35));
    // TimeSlot ts = new TimeSlot();
    // ts.setStartTime(startTime);
    // ts.setEndTime(endTime);
    // ts.setStartDate(startDate);
    // ts.setEndDate(endDate);
    //
    // Long receiptID = (long) 12;
    // double totalPrice = 90;
    // Receipt receipt = new Receipt();
    // receipt.setReceiptID(receiptID);
    // receipt.setTotalPrice(totalPrice);
    //
    //
    // Long reminderID = (long) 000;
    // Date date = Date.valueOf("20-03-18");
    // Time time = Time.valueOf("12:00");
    // String message = "It is tomorrow";
    // AppointmentReminder ar = new AppointmentReminder();
    // ar.setCustomer(customer);
    // ar.setReminderID(reminderID);
    // ar.setDate(date);
    // ar.setTime(time);
    // ar.setMessage(message);
    //
    //
    // Appointment appointment = new Appointment();
    // appointment.setCustomer(customer);
    // appointment.setBookableServices(service);
    // appointment.setTechnician(technician);
    // appointment.setTimeSlot(ts);
    // appointment.setAppointmentID(appointmentID);
    // appointment.setReceipt(receipt);
    // appointment.setReminder(ar);
    // appointmentRepository.save(appointment);
    //
    // appointment = null;
    // appointment = appointmentRepository.findByAppointmentID(appointmentID);
    // assertNotNull(appointment);
    // assertEquals(appointmentID, appointment.getAppointmentID());
    // assertEquals(customer, appointment.getCustomer());
    // assertEquals(service, appointment.getBookableServices());
    // assertEquals(technician, appointment.getTechnician());
    // assertEquals(ts, appointment.getTimeSlot());
    // assertEquals(receipt, appointment.getReceipt());
    // assertEquals(ar, appointment.getReminder());
    //
    // }
    // public void testPersistAndLoadAppointmentReminder(){
    // String customerID = "234";
    // Customer customer = new Customer();
    // customer.setUserID(customerID);
    //
    // Long reminderID = (long)12;
    //
    // Time time = Time.valueOf("12:00");
    // Date date = Date.valueOf("20-03-18");
    // String message = "It is tomorrow";
    //
    // Long serviceId = (long) 45;
    // BookableService service = new BookableService();
    // service.setName("oil change");
    // service.setPrice(20);
    // service.setServiceID(serviceId);
    //
    // Long receiptID = (long)12;
    // double totalPrice = 90;
    // Receipt receipt = new Receipt();
    // receipt.setReceiptID(receiptID);
    // receipt.setTotalPrice(totalPrice);
    //
    // Long technicianID = (long)87;
    // GarageTechnician technician = new GarageTechnician();
    // technician.setTechnicianID(technicianID);
    //
    // Time startTime = Time.valueOf("10:00");
    // Time endTime = Time.valueOf("11:00");
    // Date startDate = Date.valueOf("2021-03-19");
    // Date endDate = Date.valueOf("2021-03-19");
    // TimeSlot ts = new TimeSlot();
    // ts.setStartTime(startTime);
    // ts.setEndTime(endTime);
    // ts.setStartDate(startDate);
    // ts.setEndDate(endDate);
    //
    //
    //
    // Appointment appointment = new Appointment();
    // Long aptId = (long) 34;
    // appointment.setCustomer(customer);
    // appointment.setBookableServices(service);
    // appointment.setReceipt(receipt);
    // appointment.setTechnician(technician);
    // appointment.setTimeSlot(ts);
    // appointment.setAppointmentID(aptId);
    //
    // AppointmentReminder appointmentReminder = new AppointmentReminder();
    // appointmentReminder.setCustomer(customer);
    // appointmentReminder.setDate(date);
    // appointmentReminder.setMessage(message);
    // appointmentReminder.setReminderID(reminderID);
    // appointmentReminder.setTime(time);
    // appointmentReminder.setAppointment(appointment);
    // }

    // @Test
    // public void testPersistAndLoadGarageTechnician(){
    // String technicianID = "987";
    // String techName = "cizo";
    // GarageTechnician technician = new GarageTechnician();
    // technician.setTechnicianID(technicianID);
    // technician.setName(techName);
    // garageTechnicianRepository.save(technician);
    //
    // String id = "TestUser";
    // String password = "TestPAssword";
    // Customer customer = new Customer();
    // customer.setUserID(id);
    // customer.setPassword(password);
    //
    // Profile profile = new Profile();
    // String profileID = "TestProfileID";
    // String addressLine = "TestAddressLine";
    // String phoneNumber = "0123456789";
    // String firstName = "TestFirstName";
    // String lastName = "TestLastName";
    // String zipCode = "zipCode";
    // String emailAddress = "testEmailAddress";
    // profile.setAddressLine(addressLine);
    // profile.setProfileID(profileID);
    // profile.setPhoneNumber(phoneNumber);
    // profile.setFirstName(firstName);
    // profile.setLastName(lastName);
    // profile.setZipCode(zipCode);
    // profile.setEmailAddress(emailAddress);
    // profileRepository.save(profile);
    //
    // Car car = new Car();
    // String carID = "testID";
    // String model = "testModel";
    // String color = "testColor";
    // String year = "testYear";
    // car.setCarID(carID);
    // car.setColor(color);
    // car.setModel(model);
    // car.setYear(year);
    // carRepository.save(car);
    //
    // CheckupReminder reminder = new CheckupReminder();
    // String reminderID = "testReminderID";
    // String message = "testMessage";
    // Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
    // Time time = java.sql.Time.valueOf(LocalTime.of(11, 35));
    // reminder.setCustomer(customer);
    // reminder.setReminderID(reminderID);
    // reminder.setMessage(message);
    // reminder.setDate(date);
    // reminder.setTime(time);
    // checkupReminderRepository.save(reminder);
    // ArrayList<Reminder> reminders = new ArrayList<Reminder>();
    // reminders.add(reminder);
    //
    // String appointmentID = "a12";
    //
    // String serviceID = "12345";
    // BookableService service = new BookableService();
    // service.setServiceID(serviceID);
    // int price = 37;
    // service.setPrice(price);
    // String serviceName = "testServiceName";
    // service.setName(serviceName);
    // bookableServiceRepository.save(service);
    //
    // Time startTime = Time.valueOf("10:00");
    // Time endTime = Time.valueOf("11:00");
    // Date startDate = Date.valueOf("2021-03-19");
    // Date endDate = Date.valueOf("2021-03-19");
    // TimeSlot ts = new TimeSlot();
    // ts.setStartTime(startTime);
    // ts.setEndTime(endTime);
    // ts.setStartDate(startDate);
    // ts.setEndDate(endDate);
    // timeSlotRepository.save(ts);
    //
    // String receiptID = "r12";
    // double totalPrice = 90;
    // Receipt receipt = new Receipt();
    // receipt.setReceiptID(receiptID);
    // receipt.setTotalPrice(totalPrice);
    // receiptRepository.save(receipt);
    //
    // String appReminderID = "000";
    // Date appReminderDate = Date.valueOf("20-03-18");
    // Time appReminderTime = Time.valueOf("12:00");
    // String appReminderMessage = "It is tomorrow";
    // AppointmentReminder ar = new AppointmentReminder();
    // ar.setCustomer(customer);
    // ar.setReminderID(appReminderID);
    // ar.setDate(appReminderDate);
    // ar.setTime(appReminderTime);
    // ar.setMessage(appReminderMessage);
    // appointmentReminderRepository.save(ar);
    //
    // Appointment appointment = new Appointment();
    // appointment.setCustomer(customer);
    // appointment.setBookableServices(service);
    // appointment.setTechnician(technician);
    // appointment.setTimeSlot(ts);
    // appointment.setAppointmentID(appointmentID);
    // appointment.setReceipt(receipt);
    // appointment.setReminder(ar);
    // appointmentRepository.save(appointment);
    // ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    // appointments.add(appointment);
    //
    // technician.setAppointments(appointments);
    //
    // garageTechnicianRepository.save(technician);
    //
    // technician = null;
    //
    // technician =
    // garageTechnicianRepository.findGarageTechnicianByTechnicianID(technicianID);
    //
    // assertNotNull(technician);
    // assertEquals(technicianID, technician.getTechnicianID());
    // assertEquals(techName, technician.getName());
    // assertEquals(appointments, technician.getAppointments());
    // }
    //
    // @Test
    // public void testPersistAndLoadCustomer() {
    //
    // String id = "TestUser";
    // String password = "TestPAssword";
    // Customer customer = new Customer();
    // customer.setUserID(id);
    // customer.setPassword(password);
    //
    // Profile profile = new Profile();
    // String profileID = "TestProfileID";
    // String addressLine = "TestAddressLine";
    // String phoneNumber = "0123456789";
    // String firstName = "TestFirstName";
    // String lastName = "TestLastName";
    // String zipCode = "zipCode";
    // String emailAddress = "testEmailAddress";
    // profile.setAddressLine(addressLine);
    // profile.setProfileID(profileID);
    // profile.setPhoneNumber(phoneNumber);
    // profile.setFirstName(firstName);
    // profile.setLastName(lastName);
    // profile.setZipCode(zipCode);
    // profile.setEmailAddress(emailAddress);
    // profileRepository.save(profile);
    //
    // Car car = new Car();
    // String carID = "testID";
    // String model = "testModel";
    // String color = "testColor";
    // String year = "testYear";
    // car.setCarID(carID);
    // car.setColor(color);
    // car.setModel(model);
    // car.setYear(year);
    // carRepository.save(car);
    //
    // CheckupReminder reminder = new CheckupReminder();
    // String reminderID = "testReminderID";
    // String message = "testMessage";
    // Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
    // Time time = java.sql.Time.valueOf(LocalTime.of(11, 35));
    // reminder.setCustomer(customer);
    // reminder.setReminderID(reminderID);
    // reminder.setMessage(message);
    // reminder.setDate(date);
    // reminder.setTime(time);
    // checkupReminderRepository.save(reminder);
    // ArrayList<Reminder> reminders = new ArrayList<Reminder>();
    // reminders.add(reminder);
    //
    // String appointmentID = "a12";
    //
    // String serviceID = "12345";
    // BookableService service = new BookableService();
    // service.setServiceID(serviceID);
    // int price = 37;
    // service.setPrice(price);
    // String serviceName = "testServiceName";
    // service.setName(serviceName);
    // bookableServiceRepository.save(service);
    //
    // String technicianID = "987";
    // GarageTechnician technician = new GarageTechnician();
    // technician.setTechnicianID(technicianID);
    // garageTechnicianRepository.save(technician);
    //
    // Time startTime = Time.valueOf("10:00");
    // Time endTime = Time.valueOf("11:00");
    // Date startDate = Date.valueOf("2021-03-19");
    // Date endDate = Date.valueOf("2021-03-19");
    // TimeSlot ts = new TimeSlot();
    // ts.setStartTime(startTime);
    // ts.setEndTime(endTime);
    // ts.setStartDate(startDate);
    // ts.setEndDate(endDate);
    // timeSlotRepository.save(ts);
    //
    // String receiptID = "r12";
    // double totalPrice = 90;
    // Receipt receipt = new Receipt();
    // receipt.setReceiptID(receiptID);
    // receipt.setTotalPrice(totalPrice);
    // receiptRepository.save(receipt);
    //
    // String appReminderID = "000";
    // Date appReminderDate = Date.valueOf("20-03-18");
    // Time appReminderTime = Time.valueOf("12:00");
    // String appReminderMessage = "It is tomorrow";
    // AppointmentReminder ar = new AppointmentReminder();
    // ar.setCustomer(customer);
    // ar.setReminderID(appReminderID);
    // ar.setDate(appReminderDate);
    // ar.setTime(appReminderTime);
    // ar.setMessage(appReminderMessage);
    // appointmentReminderRepository.save(ar);
    //
    // Appointment appointment = new Appointment();
    // appointment.setCustomer(customer);
    // appointment.setBookableServices(service);
    // appointment.setTechnician(technician);
    // appointment.setTimeSlot(ts);
    // appointment.setAppointmentID(appointmentID);
    // appointment.setReceipt(receipt);
    // appointment.setReminder(ar);
    // appointmentRepository.save(appointment);
    // ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    // appointments.add(appointment);
    //
    // customer.setReminders(reminders);
    // customer.setAppointments(appointments);
    //
    // customerRepository.save(customer);
    //
    // customer = null;
    //
    // customer = customerRepository.findByUserID(id);
    //
    // assertNotNull(customer);
    // assertEquals(id, customer.getUserID());
    // assertEquals(password, customer.getPassword());
    // assertEquals(car.getCarID(), customer.getCar().getCarID());
    // assertEquals(profile.getProfileID(),
    // customer.getCustomerProfile().getProfileID());
    // assertEquals(appointments, customer.getAppointments());
    // assertEquals(reminders, customer.getReminders());
    // }
    @Test
    public void testPersistAndLoadProfile() {

        Long profileID = (long) 23;
        String address = "this is the test adress";
        String phoneNumber = "438-978-6824";
        String firstName = "FirstName";
        String lastName = "LastName";
        String zipCode = "H2X2B5";
        String email = "thisis@test.com";
        Profile profile = new Profile();
        profile.setAddressLine(address);
        profile.setEmailAddress(email);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setPhoneNumber(phoneNumber);
        profile.setZipCode(zipCode);
        profile.setProfileId(profileID);

        Long id = (long) 233;
        String password = "TestPassword";
        Customer customer = new Customer();
        String userId = "TestUser";
        customer.setId(id);
        customer.setUserID(userId);
        customer.setPassword(password);

        customer.setCustomerProfile(profile);
        profile.setCustomer(customer);

        profileRepository.save(profile);
        customerRepository.save(customer);

        profile = null;
        profile = profileRepository.findProfileByProfileId(profileID);

        assertNotNull(profile);
        assertEquals(profileID, profile.getProfileId());
        assertEquals(address, profile.getAddressLine());
        assertEquals(phoneNumber, profile.getPhoneNumber());
        assertEquals(firstName, profile.getFirstName());
        assertEquals(lastName, profile.getLastName());
        assertEquals(zipCode, profile.getZipCode());
        assertEquals(email, profile.getEmailAddress());
        assertEquals(customer, profile.getCustomer());

    }

    @Test
    public void testPersistAndLoadBusinessHour() {
        BusinessHour bh = new BusinessHour();
        Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
        Time endTime = java.sql.Time.valueOf(LocalTime.of(12, 35));
        DayOfWeek dow = DayOfWeek.Saturday;

        bh.setDayOfWeek(dow);
        bh.setEndTime(startTime);
        bh.setEndTime(endTime);
        businessHourRepository.save(bh);
        Long bhId = bh.getHourID();

        bh = null;
        bh = businessHourRepository.findBusinessHourByHourID(bhId);

        assertNotNull(bh);
        assertEquals(dow, bh.getDayOfWeek());
        assertEquals(bhId, bh.getHourID());

    }

    @Test
    public void testPersistAndLoadCra() {
        Car car = new Car();
        String model = "testModel";
        String year = "2021";
        String color = "blue";

        car.setColor(color);
        car.setModel(model);
        car.setYear(year);

        carRepository.save(car);
        Long carId = car.getCarID();

        car = null;
        car = carRepository.findByCarID(carId);

        assertNotNull(car);
        assertEquals(carId, car.getCarID());
        assertEquals(color, car.getColor());
        assertEquals(model, car.getModel());
        assertEquals(year, car.getYear());

    }

    @Test
    public void testPersistAndLoadCheckupReminder() {
        CheckupReminder cr = new CheckupReminder();
        Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Time time = java.sql.Time.valueOf(LocalTime.of(11, 35));
        String message = "testMessage";

        Customer cust = new Customer();
        customerRepository.save(cust);
        Long custId = cust.getId();

        cr.setCustomer(cust);
        cr.setDate(date);
        cr.setTime(time);
        cr.setMessage(message);

        checkupReminderRepository.save(cr);
        Long crId = cr.getReminderID();

        cr = null;
        cr = checkupReminderRepository.findByReminderID(crId);

        assertNotNull(cr);
        assertEquals(crId, cr.getReminderID());
        assertEquals(message, cr.getMessage());
        assertEquals(custId, cr.getCustomer().getId());
        assertEquals(date, cr.getDate());
        assertEquals(time, cr.getTime());

    }
    // @Test
    // public void testPersistAndLoadReceipt() {
    //
    //
    // }
    // @Test
    // public void testPersistAndLoadReminder() {
    //
    //
    //
    // String receiptID ="r1";
    // Double totalPrice = 100.0;
    // Receipt receipt= new Receipt();
    // receipt.setReceiptID(receiptID);
    // receipt.setTotalPrice(totalPrice);
    //
    // String appointmentID="a1";
    // Appointment appointment= new Appointment();
    // appointment.setAppointmentID(appointmentID);
    //
    // receipt.setAppointment(appointment);
    // appointment.setReceipt(receipt);
    //
    // receiptRepository.save(receipt);
    // appointmentRepository.save(appointment);
    //
    // receipt=null;
    // receipt=receiptRepository.findReceiptByReceiptID(receiptID);
    //
    // assertNotNull(receipt);
    // assertEquals(totalPrice,receipt.getTotalPrice());
    // assertEquals(receiptID,receipt.getReceiptID());
    // assertEquals(appointment,receipt.getAppointment());
    // }
    //
    // @Test
    // public void testPersistAndLoadService() {
    //
    // }
}
