package ca.mcgill.ecse321.projectgroup04.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup04.model.CheckupReminder;
import ca.mcgill.ecse321.projectgroup04.model.Customer;

public interface CheckupReminderRepository extends CrudRepository<CheckupReminder, Long> {
    CheckupReminder findCheckupReminderByReminderId(Long reminderId);

    List<CheckupReminder> findCheckupReminderByCustomer(Customer customer);

    CheckupReminder findCheckupReminderByDate(Date date);

    CheckupReminder findCheckupReminderByMessage(String message);

    CheckupReminder findCheckupReminderByTime(Time time);
}
