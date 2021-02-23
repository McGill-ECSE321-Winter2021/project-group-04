package ca.mcgill.ecse321.projectgroup04.dao;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import ca.mcgill.ecse321.projectgroup04.model.*;

public interface AppointmentRepository extends CrudRepository<Appointment, Long>{
    List<Appointment> findByCustomer(Customer customer);
    List<Appointment> findByTimeSlot(TimeSlot timeSlot);
    List<Appointment> findByTechnician(GarageTechnician technician);
    List<Appointment> findByBookableServices(BookableService services);
    Appointment findByAppointmentId(Long appointmentId);
    Appointment findByReminder(Reminder reminder);
    Appointment findByBookableServicesAndCustomer(BookableService services, Customer customer);
    Appointment findByTimeSlotAndCustomer(TimeSlot timeSlot, Customer customer);
}
