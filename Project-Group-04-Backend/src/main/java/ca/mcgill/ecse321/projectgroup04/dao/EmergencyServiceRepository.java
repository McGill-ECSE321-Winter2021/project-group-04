package ca.mcgill.ecse321.projectgroup04.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup04.model.*;

public interface EmergencyServiceRepository extends CrudRepository <EmergencyService, Long> {
    EmergencyService findEmergencyServiceByServiceId(Long serviceId);
    List<EmergencyService> findByCustomer(Customer customer);
    EmergencyService findByReceipt(Receipt receipt);
    EmergencyService findEmergencyServiceByName(String serviceName);


}