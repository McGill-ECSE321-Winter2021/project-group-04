package ca.mcgill.ecse321.projectgroup04.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.List;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import javax.transaction.Transactional;

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
    private GarageSpotRepository garageSpotRepository;
    @Autowired
    private FieldTechnicianRepository fieldTechnicianRepository;
    @Autowired
    private EmergencyServiceRepository emergencyServiceRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @AfterEach
    public void clearDatabase() {
        administrativeAssistantRepository.deleteAll();
        appointmentRepository.deleteAll();
        profileRepository.deleteAll();
        ownerRepository.deleteAll();
        appointmentReminderRepository.deleteAll();
        businessRepository.deleteAll();
        carRepository.deleteAll();
        customerRepository.deleteAll();
        businessHourRepository.deleteAll();
        receiptRepository.deleteAll();
        bookableServiceRepository.deleteAll();
        garageTechnicianRepository.deleteAll();
        timeSlotRepository.deleteAll();

        garageSpotRepository.deleteAll();

        checkupReminderRepository.deleteAll();
        fieldTechnicianRepository.deleteAll();
        emergencyServiceRepository.deleteAll();

    }

    @Test
    @Transactional
    public void testPersistAndLoadAdministrativeAssistant() {
        String userId = "qq";
        String password = "TestAdministrativePassword";
        AdministrativeAssistant ad = new AdministrativeAssistant();
        ad.setUserId(userId);
        ad.setPassword(password);
        administrativeAssistantRepository.save(ad);
        Long id = ad.getId();
        System.out.print(ad.getId());
        System.out.println(ad.getId().getClass());
        ad = null;
        ad = administrativeAssistantRepository.findAdministrativeAssistantById(id);
        assertNotNull(ad);
        assertEquals(id, ad.getId());

    }

    @Test
    @Transactional
    public void testPersistAndLoadBookableService() {
        int duration = 30;
        String name = "wash";
        int price = 10;
        BookableService bs = new BookableService();
        bs.setDuration(duration);
        bs.setName(name);
        bs.setPrice(price);
        bookableServiceRepository.save(bs);
        Long id = bs.getServiceId();

        bs = null;
        bs = bookableServiceRepository.findBookableServiceByServiceId(id);
        assertNotNull(bs);
        assertEquals(name, bs.getName());
        assertEquals(id, bs.getServiceId());
        assertEquals(duration, bs.getDuration());
        assertEquals(price, bs.getPrice());

    }

    @Test
    @Transactional
    public void testPersistAndLoadTimeSlot() {
        Date startDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Date endDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
        Time endTime = java.sql.Time.valueOf(LocalTime.of(12, 35));
        TimeSlot ts = new TimeSlot();
        ts.setStartTime(startTime);
        ts.setEndTime(endTime);
        ts.setStartDate(startDate);
        ts.setEndDate(endDate);

        int GSnum = 123;
        GarageSpot garageSpot = new GarageSpot();
        garageSpot.setSpotNumber(GSnum);

        ArrayList<TimeSlot> times = new ArrayList<TimeSlot>();
        times.add(ts);
        garageSpot.setTimeSlot(times);

        ts.setGarageSpot(garageSpot);

        garageSpotRepository.save(garageSpot);
        Long garageId = garageSpot.getId();
        timeSlotRepository.save(ts);

        Long timeSlotID = ts.getTimeSlotId();

        ts = null;

        ts = timeSlotRepository.findTimeSlotByTimeSlotId(timeSlotID);

        assertNotNull(ts);
        assertEquals(startTime, ts.getStartTime());
        assertEquals(endTime, ts.getEndTime());
        assertEquals(startDate, ts.getStartDate());
        assertEquals(endDate, ts.getEndDate());
        assertEquals(timeSlotID, ts.getTimeSlotId());
        assertEquals(garageId, ts.getGarageSpot().getId());

    }

    @Test
    @Transactional
    public void testPersistAndLoadAppointment() {

        BookableService service = new BookableService();

        GarageTechnician technician = new GarageTechnician();

        String customerID = "TestId";
        Customer customer = new Customer();
        customer.setUserId(customerID);

        Date startDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Date endDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
        Time endTime = java.sql.Time.valueOf(LocalTime.of(12, 35));
        TimeSlot ts = new TimeSlot();
        ts.setStartTime(startTime);
        ts.setEndTime(endTime);
        ts.setStartDate(startDate);
        ts.setEndDate(endDate);

        double totalPrice = 90;
        Receipt receipt = new Receipt();
        receipt.setTotalPrice(totalPrice);

        Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Time time = java.sql.Time.valueOf(LocalTime.of(11, 35));
        String message = "It is tomorrow";
        AppointmentReminder ar = new AppointmentReminder();
        ar.setCustomer(customer);
        ar.setDate(date);
        ar.setTime(time);
        ar.setMessage(message);

        Appointment appointment = new Appointment();
        appointment.setCustomer(customer);
        appointment.setBookableServices(service);
        appointment.setTechnician(technician);
        appointment.setTimeSlot(ts);
        appointment.setReceipt(receipt);
        appointment.setReminder(ar);
        receiptRepository.save(receipt);
        Long receiptID = receipt.getReceiptId();
        customerRepository.save(customer);
        Long customerId = customer.getId();
        appointmentReminderRepository.save(ar);
        Long arID = ar.getReminderId();
        garageTechnicianRepository.save(technician);
        Long techId = technician.getTechnicianId();
        timeSlotRepository.save(ts);
        Long tsId = ts.getTimeSlotId();
        bookableServiceRepository.save(service);
        Long serviceId = service.getServiceId();
        appointmentRepository.save(appointment);
        Long appointmentID = appointment.getAppointmentId();

        appointment = null;
        appointment = appointmentRepository.findByAppointmentId(appointmentID);
        assertNotNull(appointment);
        assertEquals(appointmentID, appointment.getAppointmentId());
        assertEquals(customerId, appointment.getCustomer().getId());
        assertEquals(serviceId, appointment.getBookableServices().getServiceId());
        assertEquals(techId, appointment.getTechnician().getTechnicianId());
        assertEquals(tsId, appointment.getTimeSlot().getTimeSlotId());
        assertEquals(receiptID, appointment.getReceipt().getReceiptId());
        assertEquals(arID, appointment.getReminder().getReminderId());
    }

    @Test
    @Transactional

    public void testPersistAndLoadAppointmentReminder() {
        String customerID = "cizo";
        Customer customer = new Customer();
        customer.setUserId(customerID);

        Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Time time = java.sql.Time.valueOf(LocalTime.of(11, 35));
        String message = "It is tomorrow";

        BookableService service = new BookableService();
        service.setName("oil change");
        service.setPrice(20);

        double totalPrice = 90;
        Receipt receipt = new Receipt();
        receipt.setTotalPrice(totalPrice);

        GarageTechnician technician = new GarageTechnician();

        Date startDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Date endDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
        Time endTime = java.sql.Time.valueOf(LocalTime.of(12, 35));
        TimeSlot ts = new TimeSlot();
        ts.setStartTime(startTime);
        ts.setEndTime(endTime);
        ts.setStartDate(startDate);
        ts.setEndDate(endDate);

        Appointment appointment = new Appointment();
        appointment.setCustomer(customer);
        appointment.setBookableServices(service);
        appointment.setReceipt(receipt);
        appointment.setTechnician(technician);
        appointment.setTimeSlot(ts);

        AppointmentReminder appointmentReminder = new AppointmentReminder();
        appointmentReminder.setCustomer(customer);
        appointmentReminder.setDate(date);
        appointmentReminder.setMessage(message);
        appointmentReminder.setTime(time);
        appointmentReminder.setAppointment(appointment);

        receiptRepository.save(receipt);
        Long receiptID = receipt.getReceiptId();
        customerRepository.save(customer);
        Long customerId = customer.getId();
        garageTechnicianRepository.save(technician);
        Long techId = technician.getTechnicianId();
        timeSlotRepository.save(ts);
        Long tsId = ts.getTimeSlotId();
        bookableServiceRepository.save(service);
        Long serviceId = service.getServiceId();
        appointmentRepository.save(appointment);
        Long appointmentID = appointment.getAppointmentId();
        appointmentReminderRepository.save(appointmentReminder);
        Long arID = appointmentReminder.getReminderId();

        appointmentReminder = null;

        appointmentReminder = appointmentReminderRepository.findAppointmentReminderByReminderId(arID);

        assertNotNull(appointmentReminder);
        assertEquals(appointmentID, appointmentReminder.getAppointment().getAppointmentId());
        assertEquals(customerId, appointmentReminder.getCustomer().getId());
        assertEquals(serviceId, appointmentReminder.getAppointment().getBookableServices().getServiceId());
        assertEquals(techId, appointmentReminder.getAppointment().getTechnician().getTechnicianId());
        assertEquals(tsId, appointmentReminder.getAppointment().getTimeSlot().getTimeSlotId());
        assertEquals(receiptID, appointmentReminder.getAppointment().getReceipt().getReceiptId());
        assertEquals(arID, appointmentReminder.getReminderId());
    }

    @Test
    @Transactional
    public void testPersistAndLoadGarageTechnician() {
        String techName = "cizo";
        GarageTechnician technician = new GarageTechnician();
        technician.setName(techName);

        Appointment appointment = new Appointment();
        appointment.setTechnician(technician);
        appointmentRepository.save(appointment);
        Long apId = appointment.getAppointmentId();
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        appointments.add(appointment);

        technician.setAppointments(appointments);
        garageTechnicianRepository.save(technician);
        Long technicianID = technician.getTechnicianId();

        technician = null;

        technician = garageTechnicianRepository.findGarageTechnicianByTechnicianId(technicianID);

        assertNotNull(technician);
        assertEquals(technicianID, technician.getTechnicianId());
        assertEquals(techName, technician.getName());
        assertEquals(apId, technician.getAppointments().get(0).getAppointmentId());
    }

    @Test
    @Transactional
    public void testPersistAndLoadCustomer() {

        String userId = "test";
        String password = "TestPAssword";
        Customer customer = new Customer();
        customer.setPassword(password);
        customer.setUserId(userId);

        Profile profile = new Profile();
        String addressLine = "TestAddressLine";
        String phoneNumber = "0123456789";
        String firstName = "TestFirstName";
        String lastName = "TestLastName";
        String zipCode = "zipCode";
        String emailAddress = "testEmailAddress";
        profile.setAddressLine(addressLine);
        profile.setPhoneNumber(phoneNumber);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setZipCode(zipCode);
        profile.setEmailAddress(emailAddress);
        profileRepository.save(profile);
        Long profileId = profile.getProfileId();

        Car car = new Car();
        String model = "testModel";
        String color = "testColor";
        String year = "testYear";
        car.setColor(color);
        car.setModel(model);
        car.setYear(year);
        carRepository.save(car);
        Long carId = car.getCarId();

        CheckupReminder reminder = new CheckupReminder();
        String message = "testMessage";
        Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Time time = java.sql.Time.valueOf(LocalTime.of(11, 35));
        reminder.setCustomer(customer);
        reminder.setMessage(message);
        reminder.setDate(date);
        reminder.setTime(time);
        checkupReminderRepository.save(reminder);
        Long reminderId = reminder.getReminderId();
        ArrayList<Reminder> reminders = new ArrayList<Reminder>();
        reminders.add(reminder);

        Date ardate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Time artime = java.sql.Time.valueOf(LocalTime.of(11, 35));
        String appReminderMessage = "It is tomorrow";
        AppointmentReminder ar = new AppointmentReminder();
        ar.setCustomer(customer);
        ar.setDate(ardate);
        ar.setTime(artime);
        ar.setMessage(appReminderMessage);
        appointmentReminderRepository.save(ar);
        Long arId = ar.getReminderId();
        reminders.add(ar);

        Appointment appointment = new Appointment();
        appointment.setCustomer(customer);
        appointment.setReminder(ar);
        appointmentRepository.save(appointment);
        Long appointmentId = appointment.getAppointmentId();
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        appointments.add(appointment);

        customer.setReminders(reminders);
        customer.setAppointments(appointments);
        customer.setCar(car);
        customer.setCustomerProfile(profile);

        customerRepository.save(customer);
        Long id = customer.getId();

        customer = null;

        customer = customerRepository.findCustomerById(id);

        assertNotNull(customer);
        assertEquals(id, customer.getId());
        assertEquals(password, customer.getPassword());
        assertEquals(carId, customer.getCar().getCarId());
        assertEquals(profileId, customer.getCustomerProfile().getProfileId());
        assertEquals(appointmentId, customer.getAppointments().get(0).getAppointmentId());
        assertEquals(reminderId, customer.getReminders().get(0).getReminderId());
        assertEquals(arId, customer.getReminders().get(1).getReminderId());
        assertEquals(userId, customer.getUserId());

    }

    @Test
    @Transactional
    public void testPersistAndLoadProfile() {

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

        String password = "TestPassword";
        Customer customer = new Customer();
        String userId = "TestUser";
        customer.setUserId(userId);
        customer.setPassword(password);

        customer.setCustomerProfile(profile);
        profile.setCustomer(customer);
        customerRepository.save(customer);
        Long id = customer.getId();
        profileRepository.save(profile);
        Long profileId = profile.getProfileId();

        profile = null;
        profile = profileRepository.findProfileByProfileId(profileId);

        assertNotNull(profile);
        assertEquals(profileId, profile.getProfileId());
        assertEquals(address, profile.getAddressLine());
        assertEquals(phoneNumber, profile.getPhoneNumber());
        assertEquals(firstName, profile.getFirstName());
        assertEquals(lastName, profile.getLastName());
        assertEquals(zipCode, profile.getZipCode());
        assertEquals(email, profile.getEmailAddress());
        assertEquals(id, profile.getCustomer().getId());

    }

    @Test
    @Transactional
    public void testPersistAndLoadReceipt() {

        Double totalPrice = 100.0;
        Receipt receipt = new Receipt();
        receipt.setTotalPrice(totalPrice);

        Appointment appointment = new Appointment();

        receipt.setAppointment(appointment);
        appointment.setReceipt(receipt);

        receiptRepository.save(receipt);
        Long receiptId = receipt.getReceiptId();
        appointmentRepository.save(appointment);
        Long appointmentId = appointment.getAppointmentId();

        receipt = null;
        receipt = receiptRepository.findReceiptByReceiptId(receiptId);

        assertNotNull(receipt);
        assertEquals(totalPrice, receipt.getTotalPrice());
        assertEquals(receiptId, receipt.getReceiptId());
        assertEquals(appointmentId, receipt.getAppointment().getAppointmentId());

    }

    @Test
    @Transactional
    public void testPersistAndLoadGarageSpot() {
        int gSpot = 2;
        GarageSpot garageSpot = new GarageSpot();
        garageSpot.setSpotNumber(gSpot);

        Date startDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Date endDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
        Time endTime = java.sql.Time.valueOf(LocalTime.of(12, 35));
        TimeSlot ts = new TimeSlot();
        ts.setStartTime(startTime);
        ts.setEndTime(endTime);
        ts.setStartDate(startDate);
        ts.setEndDate(endDate);
        ts.setGarageSpot(garageSpot);

        ArrayList<TimeSlot> times = new ArrayList<TimeSlot>();
        times.add(ts);
        garageSpot.setTimeSlot(times);

        garageSpotRepository.save(garageSpot);
        Long gsId = garageSpot.getId();
        timeSlotRepository.save(ts);
        Long tsId = ts.getTimeSlotId();

        garageSpot = null;

        garageSpot = garageSpotRepository.findGarageSpotById(gsId);

        assertNotNull(garageSpot);
        assertEquals(tsId, garageSpot.getTimeSlot().get(0).getTimeSlotId());
        assertEquals(gSpot, garageSpot.getSpotNumber());
        assertEquals(gsId, garageSpot.getId());
    }

    @Test
    @Transactional
    public void testPersistAndLoadOwner() {
        String userId = "ooo";
        String password = "TestOwnerPassword";
        Owner ow = new Owner();
        ow.setUserId(userId);
        ow.setPassword(password);
        ownerRepository.save(ow);
        Long owId = ow.getId();

        ow = null;
        ow = ownerRepository.findOwnerById(owId);
        assertNotNull(ow);
        assertEquals(owId, ow.getId());
        assertEquals(userId, ow.getUserId());
    }

    @Test
    @Transactional
    public void testPersistAndLoadEmergencyService() {
        String location = "Montreal";
        String name = "Towing";
        int price = 10;
        EmergencyService es = new EmergencyService();
        // Long id = es.getServiceID();
        es.setLocation(location);
        es.setName(name);
        es.setPrice(price);
        emergencyServiceRepository.save(es);
        Long esId = es.getServiceId();

        es = null;
        es = emergencyServiceRepository.findEmergencyServiceByServiceId(esId);
        assertNotNull(es);
        assertEquals(name, es.getName());
        assertEquals(esId, es.getServiceId());
        assertEquals(location, es.getLocation());
        assertEquals(price, es.getPrice());
    }

    @Test
    @Transactional
    public void testPersistAndLoadFieldTechnician() {
        String fieldTechName = "cizor";
        FieldTechnician fieldTech = new FieldTechnician();
        fieldTech.setName(fieldTechName);
        fieldTechnicianRepository.save(fieldTech);
        Long ftId = fieldTech.getTechnicianId();

        fieldTech = null;

        fieldTech = fieldTechnicianRepository.findFieldTechnicianByTechnicianId(ftId);

    }

    @Test
    @Transactional
    public void testPersistAndLoadBusiness() {
        String name = "cizo";
        String adress = "mohammadStreet";
        String phoneNumber = "0602010201";
        String emailAdress = "yasmineMatta";
        Business bus = new Business();
        bus.setAddress(adress);
        bus.setEmailAddress(emailAdress);
        bus.setName(name);
        bus.setPhoneNumber(phoneNumber);

        BusinessHour bush = new BusinessHour();
        ArrayList<BusinessHour> bh = new ArrayList<BusinessHour>();
        bh.add(bush);
        businessHourRepository.save(bush);
        Long bushId = bush.getHourId();

        TimeSlot ts = new TimeSlot();
        ArrayList<TimeSlot> times = new ArrayList<TimeSlot>();
        times.add(ts);
        timeSlotRepository.save(ts);
        Long tsId = ts.getTimeSlotId();

        bus.setBusinessHours(bh);
        bus.setRegular(times);

        businessRepository.save(bus);
        Long busId = bus.getId();

        bus = null;

        bus = businessRepository.findBusinessById(busId);

        assertNotNull(bus);
        assertEquals(name, bus.getName());
        assertEquals(adress, bus.getAddress());
        assertEquals(phoneNumber, bus.getPhoneNumber());
        assertEquals(emailAdress, bus.getEmailAddress());
        assertEquals(busId, bus.getId());
        assertEquals(bushId, bus.getBusinessHours().get(0).getHourId());
        assertEquals(tsId, bus.getRegular().get(0).getTimeSlotId());

    }

    @Test
    @Transactional
    public void testPersistAndLoadBusinessHour() {
        BusinessHour bh = new BusinessHour();
        Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
        Time endTime = java.sql.Time.valueOf(LocalTime.of(12, 35));
        DayOfWeek dow = DayOfWeek.Saturday;

        bh.setDayOfWeek(dow);
        bh.setEndTime(startTime);
        bh.setEndTime(endTime);
        businessHourRepository.save(bh);
        Long bhId = bh.getHourId();

        bh = null;
        bh = businessHourRepository.findBusinessHourByHourId(bhId);

        assertNotNull(bh);
        assertEquals(dow, bh.getDayOfWeek());
        assertEquals(bhId, bh.getHourId());

    }

    @Test
    @Transactional
    public void testPersistAndLoadCar() {
        Car car = new Car();
        String model = "testModel";
        String year = "2021";
        String color = "blue";

        car.setColor(color);
        car.setModel(model);
        car.setYear(year);

        carRepository.save(car);
        Long carId = car.getCarId();

        car = null;
        car = carRepository.findByCarId(carId);

        assertNotNull(car);
        assertEquals(carId, car.getCarId());
        assertEquals(color, car.getColor());
        assertEquals(model, car.getModel());
        assertEquals(year, car.getYear());

    }

    @Test
    @Transactional
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
        Long crId = cr.getReminderId();

        cr = null;
        cr = checkupReminderRepository.findCheckupReminderByReminderId(crId);

        assertNotNull(cr);
        assertEquals(crId, cr.getReminderId());
        assertEquals(message, cr.getMessage());
        assertEquals(custId, cr.getCustomer().getId());
        assertEquals(date, cr.getDate());
        assertEquals(time, cr.getTime());

    }
}
