package ca.mcgill.ecse321.projectgroup04.dao;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import ca.mcgill.ecse321.projectgroup04.model.*;


public interface GarageSpotRepository extends CrudRepository<GarageSpot, Long> {
    GarageSpot findGarageSpotById(Long id);
    GarageSpot findGarageSpotBySpotNumber(int aSpotNumber);
    GarageSpot findGarageSpotByTimeSlot(List<TimeSlot> timeslot);
}
