package ca.mcgill.ecse321.projectgroup04.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup04.model.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findCustomerById(Long userId);
}
