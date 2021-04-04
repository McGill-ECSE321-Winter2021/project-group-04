package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*;
import ca.mcgill.ecse321.projectgroup04.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;
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
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BookableServiceRepository bookableServiceRepository;
    @Autowired
    private BusinessHourRepository businessHourRepository;
    @Autowired
    private GarageTechnicianRepository garageTechnicianRepository;

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
        if (garageSpot < 1 || garageSpot > 4) {
            throw new IllegalArgumentException("Please chose a garage sport between 1 and 4");
        }
        if (date.before(Date.valueOf(LocalDate.now()))) {
            throw new IllegalArgumentException("Please book an appointment in the future");
        }
        if (!fallsWithinBusinessHours(date, startTime, endTime)) {
            throw new IllegalArgumentException("This time doesn't fall within business hours!");
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
                "You have an appointment within 24 hours");
        addAppointmentReminderToCustomer(customer, appReminder);
        Appointment appointment = createAppointment(customer, bookableService, garageTechnician, timeSlot, appReminder,
                receipt);
        return appointment;

    }

    public Boolean fallsWithinBusinessHours(Date date, Time startTime, Time endTime) {
        DayOfWeek dayOfWeekOfAppointment = LocalDate.parse(date.toString()).getDayOfWeek();
        Boolean fallsWithinBusinessHours = false;
        for (BusinessHour businessHour : getAllBusinessHours()) {
            if (businessHour.getDayOfWeek().name().equalsIgnoreCase(dayOfWeekOfAppointment.name())) {
                if (businessHour.getStartTime().before(startTime) && businessHour.getEndTime().after(endTime)) {
                    fallsWithinBusinessHours = true;
                }
            }
        }

        return fallsWithinBusinessHours;
    }

    @Transactional
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
    public GarageTechnician getGarageTechnicianById(Long technicianId) {
        GarageTechnician garageTechnician = garageTechnicianRepository.findGarageTechnicianByTechnicianId(technicianId);
        if (garageTechnician == null) {
            throw new IllegalArgumentException("No garage technician with such id!");
        }
        return garageTechnician;
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
    public List<BusinessHour> getAllBusinessHours() {
        return (List<BusinessHour>) businessHourRepository.findAll();
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

        for (BookableService bookableService : bookableServiceRepository.findAll()) {
            if (name.contains(bookableService.getName()) || bookableService.getName().equals(name)) {
                return bookableService;
            }
        }
        return null;
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
        Customer customer = getCustomerByUserId(userId);
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
            throw new IllegalArgumentException("Cannot cancel appointment less than 24 hours!");
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
        Time now =Time.valueOf(LocalTime.now());
        List<Appointment> nextAppointments = new ArrayList<Appointment>();
        for (Appointment appointment : getAppointmentsByCustomer(userId)) {
            if (appointment.getTimeSlot().getStartDate().after(today)
                    || 
                    (appointment.getTimeSlot().getStartDate().compareTo(today) == 0 &&
                    appointment.getTimeSlot().getEndTime().after(now))) {
                nextAppointments.add(appointment);
            }
        }
        return nextAppointments;
    }

    public List<Appointment> get24hoursAppointment(String userId) {
        Date today = Date.valueOf(LocalDate.now());
        List<Appointment> hoursAppointments = new ArrayList<Appointment>();
        for (Appointment appointment : getAppointmentsByCustomer(userId)) {
            Date reminderDate = appointment.getReminder().getDate();
            Date appointmentDate = appointment.getTimeSlot().getStartDate();
            if (reminderDate.getTime() <= today.getTime() && today.getTime() <= appointmentDate.getTime()) {
                hoursAppointments.add(appointment);
            }
        }
        return hoursAppointments;
    }

}
