package ca.mcgill.ecse321.projectgroup04.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup04.model.*;

public interface AdministrativeAssistantRepository extends CrudRepository < AdministrativeAssistant, Long>{
    AdministrativeAssistant findAdministrativeAssistantById(Long id);
    AdministrativeAssistant findAdministrativeAssistantByUserId(String userId);
}