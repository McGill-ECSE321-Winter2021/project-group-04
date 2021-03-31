package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*;
import ca.mcgill.ecse321.projectgroup04.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class CheckupReminderService {
    @Autowired
    private CheckupReminderRepository checkupReminderRepository;

    @Transactional
    public CheckupReminder createCheckupReminder(Date aDate, Time aTime, String aMessage) {
        CheckupReminder checkupReminder = new CheckupReminder();

        if (aDate == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (aTime == null) {
            throw new IllegalArgumentException("Time cannot be null");
        }
        if (aMessage == null || aMessage.equals("")) {
            throw new IllegalArgumentException("Message cannot be empty");
        }

        checkupReminder.setDate(aDate);
        checkupReminder.setMessage(aMessage);
        checkupReminder.setTime(aTime);
        checkupReminderRepository.save(checkupReminder);
        return checkupReminder;
    }

    @Transactional
    public CheckupReminder editCheckupReminder(Long id, Date aDate, Time aTime, String aMessage) {
        CheckupReminder checkupReminder = checkupReminderRepository.findByReminderId(id);

        Boolean dateBool = true;
        Boolean timeBool = true;
        Boolean messageBool = true;

        if (aDate == null) {
            dateBool = false;
        }
        if (aTime == null) {
            timeBool = false;
        }
        if (aMessage == null || aMessage.equals("")) {
            messageBool = false;
        }

        if (dateBool)
            checkupReminder.setDate(aDate);
        if (messageBool)
            checkupReminder.setMessage(aMessage);
        if (timeBool)
            checkupReminder.setTime(aTime);
        checkupReminderRepository.save(checkupReminder);
        return checkupReminder;
    }

    @Transactional
    public CheckupReminder getCheckupReminderById(Long checkupReminderId) {
        return checkupReminderRepository.findByReminderId(checkupReminderId);
    }

    @Transactional
    public CheckupReminder getCheckupReminderByMessage(String message) {
        return checkupReminderRepository.findByMessage(message);
    }

    @Transactional
    public List<CheckupReminder> getAllCheckupReminder() {
        return (List<CheckupReminder>) checkupReminderRepository.findAll();
    }
}
