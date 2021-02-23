package ca.mcgill.ecse321.projectgroup04.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup04.model.*;

public interface ReminderRepository extends CrudRepository <Reminder, Long> {
	Reminder findReminderByReminderId(Long reminderId);
    List<Reminder> findByCustomer(Customer customer);

}
