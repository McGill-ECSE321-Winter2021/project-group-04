package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*;
import ca.mcgill.ecse321.projectgroup04.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReminderService {

    @Autowired
    ReminderRepository reminderRepository;

    @Transactional
    public List<Reminder> getAllReminder() {
        return (List<Reminder>) reminderRepository.findAll();
    }

    @Transactional
    public List<Reminder> getReminderByReminderId(Long reminderId) {
        Reminder reminder = reminderRepository.findReminderByReminderId(reminderId);
        List<Reminder> reminders = new ArrayList<Reminder>();
        reminders.add(reminder);
        return reminders;
    }

}
