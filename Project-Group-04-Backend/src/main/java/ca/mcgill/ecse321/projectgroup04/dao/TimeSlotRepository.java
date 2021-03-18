package ca.mcgill.ecse321.projectgroup04.dao;

import java.sql.Date;
import java.sql.Time;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup04.model.*;

public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long> {
    TimeSlot findTimeSlotByTimeSlotId(Long timeSlotId);
    TimeSlot findTimeSlotByGarageSpotAndStartTimeAndStartDate(Integer garageSpot, Time startTime, Date startDate);
	TimeSlot findTimeSlotByStartDateAndStartTime(Date startDate, Time startTime);
}