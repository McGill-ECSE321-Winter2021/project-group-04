package ca.mcgill.ecse321.projectgroup04.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup04.model.*;

public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long> {
    TimeSlot findTimeSlotByTimeSlotId(Long timeSlotId);
    List<TimeSlot> findTimeSlotByGarageSpot(Integer garageSpot);
}