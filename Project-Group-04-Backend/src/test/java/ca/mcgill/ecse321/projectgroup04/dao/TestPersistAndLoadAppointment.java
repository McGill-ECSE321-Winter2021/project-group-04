package ca.mcgill.ecse321.projectgroup04.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup04.model.Appointment;
import ca.mcgill.ecse321.projectgroup04.model.AppointmentReminder;
import ca.mcgill.ecse321.projectgroup04.model.BookableService;
import ca.mcgill.ecse321.projectgroup04.model.Customer;
import ca.mcgill.ecse321.projectgroup04.model.GarageTechnician;
import ca.mcgill.ecse321.projectgroup04.model.Receipt;
import ca.mcgill.ecse321.projectgroup04.model.TimeSlot;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistAndLoadAppointment {

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
    private CustomerRepository customerRepository;
    @Autowired
    private ReceiptRepository receiptRepository;

    @AfterEach
    public void clearDatabase() {
        appointmentRepository.deleteAll();
        appointmentReminderRepository.deleteAll();
        customerRepository.deleteAll();
        receiptRepository.deleteAll();
        bookableServiceRepository.deleteAll();
        garageTechnicianRepository.deleteAll();
        timeSlotRepository.deleteAll();

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
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartTime(startTime);
        timeSlot.setEndTime(endTime);
        timeSlot.setStartDate(startDate);
        timeSlot.setEndDate(endDate);

        double totalPrice = 90;
        Receipt receipt = new Receipt();
        receipt.setTotalPrice(totalPrice);

        Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Time time = java.sql.Time.valueOf(LocalTime.of(11, 35));
        String message = "It is tomorrow";
        AppointmentReminder appointmentReminder = new AppointmentReminder();

        appointmentReminder.setDate(date);
        appointmentReminder.setTime(time);
        appointmentReminder.setMessage(message);

        Appointment appointment = new Appointment();
        appointment.setCustomer(customer);
        appointment.setBookableServices(service);
        appointment.setTechnician(technician);
        appointment.setTimeSlot(timeSlot);
        appointment.setReceipt(receipt);
        appointment.setReminder(appointmentReminder);
        receiptRepository.save(receipt);
        Long receiptID = receipt.getReceiptId();
        customerRepository.save(customer);
        Long customerId = customer.getId();
        appointmentReminderRepository.save(appointmentReminder);
        Long arID = appointmentReminder.getReminderId();
        garageTechnicianRepository.save(technician);
        Long techId = technician.getTechnicianId();
        timeSlotRepository.save(timeSlot);
        Long timeSlotId = timeSlot.getTimeSlotId();
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
        assertEquals(timeSlotId, appointment.getTimeSlot().getTimeSlotId());
        assertEquals(receiptID, appointment.getReceipt().getReceiptId());
        assertEquals(arID, appointment.getReminder().getReminderId());
    }

}
