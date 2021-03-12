package ca.mcgill.ecse321.projectgroup04.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup04.model.Customer;
import ca.mcgill.ecse321.projectgroup04.model.Profile;
import ca.mcgill.ecse321.projectgroup04.model.Car;


public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findCustomerById(Long userId);
    Customer findByCustomerProfile(Profile customerProfile);
    Customer findByCar(Car car);
    Customer findCustomerByUserId(String userId);
}
