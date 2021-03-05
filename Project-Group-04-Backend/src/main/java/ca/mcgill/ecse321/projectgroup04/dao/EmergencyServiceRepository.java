package ca.mcgill.ecse321.projectgroup04.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup04.model.*;

public interface EmergencyServiceRepository extends CrudRepository <EmergencyService, Long> {
    EmergencyService findEmergencyServiceByServiceId(Long serviceId);
    EmergencyService findByAppointments(Appointment appointments); //Emergency service not linked with appointments


}