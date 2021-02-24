package ca.mcgill.ecse321.projectgroup04.dao;

import org.springframework.data.repository.CrudRepository; 
import ca.mcgill.ecse321.projectgroup04.model.*;


public interface GarageSpotRepository extends CrudRepository<GarageSpot, Long> {
    GarageSpot findGarageSpotById(Long id);
    GarageSpot findByTimeSlot(TimeSlot timeslot);
}
