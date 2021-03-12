package ca.mcgill.ecse321.projectgroup04.dao;


import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup04.model.*;

public interface BookableServiceRepository extends CrudRepository <BookableService, Long> {
    BookableService findBookableServiceByServiceId(Long serviceId);
    BookableService findBookableServiceByName(String serviceName);
//    BookableService findByAppointments(Appointment appointments);


}
