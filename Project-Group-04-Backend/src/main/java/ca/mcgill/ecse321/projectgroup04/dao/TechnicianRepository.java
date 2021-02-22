package ca.mcgill.ecse321.projectgroup04.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup04.model.*;

public interface TechnicianRepository extends CrudRepository<Technician, String> {
    Technician findTechnicianByName(String technicianID);
}