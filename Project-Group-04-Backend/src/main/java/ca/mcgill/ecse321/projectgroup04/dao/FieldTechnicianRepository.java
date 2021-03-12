package ca.mcgill.ecse321.projectgroup04.dao;

import org.springframework.data.repository.CrudRepository; 
import ca.mcgill.ecse321.projectgroup04.model.*;

public interface FieldTechnicianRepository extends CrudRepository<FieldTechnician, Long> {
    FieldTechnician findFieldTechnicianByTechnicianId(Long technicianId);
    FieldTechnician findFieldTechnicianByName(String name);
}