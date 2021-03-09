package ca.mcgill.ecse321.projectgroup04.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import ca.mcgill.ecse321.projectgroup04.model.*;

public interface AppointmentReminderRepository extends CrudRepository<AppointmentReminder, Long>{
   AppointmentReminder findAppointmentReminderByReminderId (Long reminderId);
}