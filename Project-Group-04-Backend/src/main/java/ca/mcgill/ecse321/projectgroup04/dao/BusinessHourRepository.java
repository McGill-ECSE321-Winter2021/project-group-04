package ca.mcgill.ecse321.projectgroup04.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup04.model.BusinessHour;
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour.DayOfWeek;

public interface BusinessHourRepository extends CrudRepository<BusinessHour, Long> {
    BusinessHour findBusinessHourByHourId(Long hourId);

    BusinessHour findBusinessHourByDayOfWeek(DayOfWeek dayOfWeek);
}
