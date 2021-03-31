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
public class AppointmentReminderService {

    @Autowired
    private AppointmentReminderRepository appointmentReminderRepository;

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

    public AppointmentReminder deleteAppointmentReminder(AppointmentReminder appointmentReminder) {
        appointmentReminderRepository.delete(appointmentReminder);
        appointmentReminder = null;
        return appointmentReminder;
    }

    public AppointmentReminder editAppointmentReminder(AppointmentReminder appointmentReminder, String message) {
        if (message == appointmentReminder.getMessage()) {
            throw new IllegalArgumentException("You have to change the message");
        }
        appointmentReminder.setMessage(message);
        appointmentReminderRepository.save(appointmentReminder);
        return appointmentReminder;
    }

}