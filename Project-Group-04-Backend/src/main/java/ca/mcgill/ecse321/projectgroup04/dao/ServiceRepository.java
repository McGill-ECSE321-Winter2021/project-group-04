package ca.mcgill.ecse321.projectgroup04.dao;


import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup04.model.*;

public interface ServiceRepository extends CrudRepository <Service, Long> {
	Service findServiceByServiceId(Long serviceId);
//	Service findServiceByAppointments(Appointment appointments);

}
