package ca.mcgill.ecse321.projectgroup04.dao;

import java.sql.Date;
import java.sql.Time;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup04.model.*;

public interface TimeSlotRepository entends CrudRepository<TimeSlot, Date, Time>{
    TimeSlot findTimeSlotByDateAndTime(Date startDate, Time startTime);
        }