package ca.mcgill.ecse321.projectgroup04.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup04.model.*;

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
    

    @AfterEach
	public void clearDatabase() {
        appointmentRepository.deleteAll();
        appointmentReminderRepository.deleteAll();
        administrativeAssistantRepository.deleteAll();
        bookableServiceRepository.deleteAll();
        technicianRepository.deleteAll();
        timeSlotRepository.deleteAll();
        garageTechnicianRepository.deleteAll();

    }
    @Test
    public void testPersistAndLoadAdministrativeAssistant(){
        String  id = "TestAdministrativeAssistance";
        String password = "TestAdministrativePassword";
        AdministrativeAssistant ad = new AdministrativeAssistant();
        ad.setUserID(id);
        ad.setPassword(password);
        administrativeAssistantRepository.save(ad);

        ad = null;
        ad = administrativeAssistantRepository.findAdminstrativeAssistantByUserID(id);
        assertNotNull(ad);
        assertEquals(id, ad.getUserID());
    }

    @Test
    public void testPersistAndLoadBookableService(){
        String id = "1234";
        int duration = 30;
        String name = "wash";
        int price = 10;
        BookableService bs = new BookableService();
        bs.setDuration(duration);
        bs.setName(name);
        bs.setServiceID(id);
        bs.setPrice(price);
        bookableServiceRepository.save(bs);

        bs = null;
        bs = bookableServiceRepository.findBookableServiceByServiceID(id);
        assertNotNull(bs);
        assertEquals(name, bs.getName());
        assertEquals(id, bs.getServiceID());
        assertEquals(duration, bs.getDuration());
        assertEquals(price, bs.getPrice());
    }
    @Test
    public void testPersistAndLoadGarageTechnician(){
        String name = "TestGarageTechnician";
        String id = "9876";
        GarageTechnician gt = new GarageTechnician();
        gt.setName(name);
        gt.setTechnicianID(id);
        garageTechnicianRepository.save(gt);

        gt = null;
        gt = garageTechnicianRepository.findGarageTechnician(id);
        assertNotNull(gt);
        assertEquals(name, gt.getTechnicianID());
        assertEquals(id, gt.getTechnicianID());
    }


    // @Test
    // public void testPersistAndLoadTimeSlot(){
    //     Time startTime = Time.valueOf("10:00");
    //     Time endTime = Time.valueOf("11:00");
    //     Date startDate = Date.valueOf("2021-03-19");
    //     Date endDate = Date.valueOf("2021-03-19");
    //     TimeSlot ts = new TimeSlot();
    //     ts.setStartTime(startTime);
    //     ts.setEndtTime(endTime);
    //     ts.setStartDate(startDate);
    //     ts.setEndDate(endDate);
    //     ts.setGarageSpot();
    // }

     @Test   
    public void testPersistAndLoadAppointment(){
        String appointmentID = "a12";

        String serviceID = "12345";
        Service service = new BookableService();
        service.setServiceID(serviceID);
        
        String technicianID = "987";
        GarageTechnician technician = new GarageTechnician();
        technician.setTechnicianID(technicianID);

        String customerID = "234";
        Customer customer = new Customer();
        customer.setUserID(customerID);
        
        Time startTime = Time.valueOf("10:00");
        Time endTime = Time.valueOf("11:00");
        Date startDate = Date.valueOf("2021-03-19");
        Date endDate = Date.valueOf("2021-03-19");
        TimeSlot ts = new TimeSlot();
        ts.setStartTime(startTime);
        ts.setEndtTime(endTime);
        ts.setStartDate(startDate);
        ts.setEndDate(endDate);

        String receiptID = "r12";
        double totalPrice = 90;
        Receipt receipt = new Receipt();
        receipt.setReceiptID(receiptID);
        receipt.setTotalPrice(totalPrice);


        String reminderID = "000";
        Date date = Date.valueOf("20-03-18");
        Time time = Time.valueOf("12:00");
        String message = "It is tomorrow";
        AppointmentReminder ar =  new AppointmentReminder();
        ar.setCustomer(customer);
        ar.setReminderID(reminderID);
        ar.setDate(date);
        ar.setTime(time);
        ar.setMessage(message);


        Appointment appointment = new Appointment();
        appointment.setCustomer(customer);
        appointment.setServices(service);
        appointment.setTechnician(technician);
        appointment.setTimeSlot(ts);
        appointment.setAppointmentID(appointmentID);
        appointment.setReceipt(receipt);
        appointment.setReminder(ar);
        appointmentRepository.save(appointment);

        appointment = null;
        appointment = appointmentRepository.findByAppointmentID(appointmentID);
        assertNotNull(appointment);
        assertEquals(appointmentID, appointment.getAppointmentID());
        assertEquals(customer, appointment.getCustomer());
        assertEquals(service, appointment.getServices());
        assertEquals(technician, appointment.getTechnician());
        assertEquals(ts, appointment.getTimeSlot());
        assertEquals(receipt, appointment.getReceipt());
        assertEquals(ar, appointment.getReminder());

    }
    public void testPersistAndLoadAppointmentReminder(){
        String customerID = "234";
        Customer customer = new Customer();
        customer.setUserID(customerID);

        String reminderID = "re12";

        Time time = Time.valueOf("12:00");
        Date date = Date.valueOf("20-03-18");
        String message = "It is tomorrow";

       
        Service service = new BookableService();
        service.setName("oil change");
        service.setPrice(20);
        service.setServiceID("9876");
        
        String receiptID = "r12";
        double totalPrice = 90;
        Receipt receipt = new Receipt();
        receipt.setReceiptID(receiptID);
        receipt.setTotalPrice(totalPrice);

        String technicianID = "987";
        GarageTechnician technician = new GarageTechnician();
        technician.setTechnicianID(technicianID);

        Time startTime = Time.valueOf("10:00");
        Time endTime = Time.valueOf("11:00");
        Date startDate = Date.valueOf("2021-03-19");
        Date endDate = Date.valueOf("2021-03-19");
        TimeSlot ts = new TimeSlot();
        ts.setStartTime(startTime);
        ts.setEndtTime(endTime);
        ts.setStartDate(startDate);
        ts.setEndDate(endDate);

        Appointment appointment = new Appointment();
        appointment.setCustomer(customer);
        appointment.setServices(service);
        appointment.setReceipt(receipt);
        appointment.setTechnician(technician);
        appointment.setTimeSlot(ts);
        appointment.setAppointmentID("ap88");

        AppointmentReminder appointmentReminder = new AppointmentReminder();
        appointmentReminder.setCustomer(customer);
        appointmentReminder.setDate(date);
        appointmentReminder.setMessage(message);
        appointmentReminder.setReminderID(reminderID);
        appointmentReminder.setTime(time);
        appointmentReminder.setAppointment(appointment);
    }

}
