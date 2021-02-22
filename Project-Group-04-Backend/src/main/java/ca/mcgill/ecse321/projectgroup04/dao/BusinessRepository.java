package ca.mcgill.ecse321.projectgroup04.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup04.model.Business;


public interface BusinessRepository extends CrudRepository<Business, String>{
    Business findBusinessByName(String name);
}
