package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*;
import ca.mcgill.ecse321.projectgroup04.model.*;

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
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private AppointmentReminderRepository appointmentReminderRepository;
    @Autowired
    private TimeSlotRepository timeSlotRepository;

    private CustomerService customerService;
    private BookableServiceService bookableServiceService;
    private BusinessHourService businessHourService;
    private TimeSlotService timeSlotService;
    private ReceiptService receiptService;
    private AppointmentReminderService appointmentReminderService;
    private GarageTechnicianService garageTechnicianService;

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
        Customer customer = customerService.getCustomerByUserId(userId);
        if (customer == null) {
            throw new IllegalArgumentException("No customer with such userId!");
        }
        BookableService bookableService = bookableServiceService.getBookableServiceByServiceName(serviceName);
        if (bookableService == null) {
            throw new IllegalArgumentException("No Bookable Service with such name!");
        }

        java.sql.Time myTimeEnd = startTime;
        LocalTime localTimeEnd = myTimeEnd.toLocalTime();
        localTimeEnd = localTimeEnd.plusMinutes(bookableService.getDuration());
        java.sql.Time endTime = java.sql.Time.valueOf(localTimeEnd);
        if (garageSpot < 1 || garageSpot > 4) {
            throw new IllegalArgumentException("Please chose a garage sport between 1 and 4");
        }
        if (date.before(Date.valueOf(LocalDate.now()))) {
            throw new IllegalArgumentException("Please book an appointment in the future");
        }
        for (BusinessHour businessHour : businessHourService.getAllBusinessHours()) {
            if (businessHour.getStartTime().after(startTime) || businessHour.getEndTime().before(endTime)) {
                throw new IllegalArgumentException("This time doesn't fall within business hours!");
            }
        }
        for (Appointment appointment : getAppointmentsByDate(date)) {
            if (isOverlap(appointment.getTimeSlot(), startTime, endTime, garageSpot)) {
                throw new IllegalArgumentException("This attempted booking overlaps with another!");
            }
        }
        TimeSlot timeSlot = timeSlotService.createTimeSlot(startTime, endTime, date, date, garageSpot);
        Receipt receipt = receiptService.createReceipt(bookableService.getPrice());
        GarageTechnician garageTechnician = garageTechnicianService.getGarageTechnicianById(garageTechnicianId);
        date = Date.valueOf(date.toLocalDate().minusDays(1)); // again get back your date object
        AppointmentReminder appReminder = appointmentReminderService.createAppointmentReminder(date, startTime,
                "You have an appointment in 24hours");
        appointmentReminderService.addAppointmentReminderToCustomer(customer, appReminder);
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
    public List<Appointment> getAppointmentsByCustomer(String userId) {
        Customer customer = customerService.getCustomerByUserId(userId);
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

    public List<Appointment> getNextAppointments(String userId) {
        Date today = Date.valueOf(LocalDate.now());
        List<Appointment> nextAppointments = new ArrayList<Appointment>();
        for (Appointment appointment : getAppointmentsByCustomer(userId)) {
            if (appointment.getTimeSlot().getStartDate().after(today)
                    || appointment.getTimeSlot().getStartDate().compareTo(today) == 0) {
                nextAppointments.add(appointment);
            }
        }
        return nextAppointments;
    }

}
