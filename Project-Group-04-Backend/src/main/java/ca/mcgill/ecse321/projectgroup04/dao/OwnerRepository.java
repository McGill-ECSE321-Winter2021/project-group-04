package ca.mcgill.ecse321.projectgroup04.dao;

import org.springframework.data.repository.CrudRepository; 
import ca.mcgill.ecse321.projectgroup04.model.*;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findOwnerById(Long userId);
    Owner findOwnerByUserId(String userId);
}