package ca.mcgill.ecse321.projectgroup04.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup04.model.Business;
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour;

public interface BusinessHourRepository extends CrudRepository<BusinessHour, Long> {
    BusinessHour findBusinessHourByHourId(Long hourId);
}