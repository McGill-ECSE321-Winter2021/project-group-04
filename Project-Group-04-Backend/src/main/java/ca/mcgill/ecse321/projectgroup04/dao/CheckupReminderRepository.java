package ca.mcgill.ecse321.projectgroup04.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup04.model.CheckupReminder;

public interface CheckupReminderRepository extends CrudRepository<CheckupReminder, Long> {
    CheckupReminder findByReminderId(Long reminderId);

    CheckupReminder findByMessage(String message);
}
