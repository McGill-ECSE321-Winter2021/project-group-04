package ca.mcgill.ecse321.projectgroup04.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup04.model.Business;

public interface BusinessRepository extends CrudRepository<Business, Long> {
    Business findBusinessById(Long Id);

    Business findBusinessByName(String name);
}
