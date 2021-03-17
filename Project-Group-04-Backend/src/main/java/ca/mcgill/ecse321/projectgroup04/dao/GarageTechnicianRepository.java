package ca.mcgill.ecse321.projectgroup04.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup04.model.*;

public interface GarageTechnicianRepository extends CrudRepository<GarageTechnician, Long> {
    GarageTechnician findGarageTechnicianByTechnicianId(Long technicianId);
    GarageTechnician findGarageTechnicianByName(String name);
}