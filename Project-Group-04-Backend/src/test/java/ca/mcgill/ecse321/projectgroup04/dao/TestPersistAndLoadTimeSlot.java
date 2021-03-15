package ca.mcgill.ecse321.projectgroup04.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup04.model.TimeSlot;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistAndLoadTimeSlot {

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @AfterEach
    public void clearDatabase() {
        timeSlotRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testPersistAndLoadTimeSlot() {
        Date startDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Date endDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
        Time endTime = java.sql.Time.valueOf(LocalTime.of(12, 35));
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartTime(startTime);
        timeSlot.setEndTime(endTime);
        timeSlot.setStartDate(startDate);
        timeSlot.setEndDate(endDate);

        ArrayList<TimeSlot> times = new ArrayList<TimeSlot>();
        times.add(timeSlot);
        int garageSpot = 1;
        timeSlot.setGarageSpot(garageSpot);
        timeSlotRepository.save(timeSlot);

        Long timeSlotID = timeSlot.getTimeSlotId();

        timeSlot = null;

        timeSlot = timeSlotRepository.findTimeSlotByTimeSlotId(timeSlotID);

        assertNotNull(timeSlot);
        assertEquals(startTime, timeSlot.getStartTime());
        assertEquals(endTime, timeSlot.getEndTime());
        assertEquals(startDate, timeSlot.getStartDate());
        assertEquals(endDate, timeSlot.getEndDate());
        assertEquals(timeSlotID, timeSlot.getTimeSlotId());
        assertEquals(garageSpot, timeSlot.getGarageSpot());

    }
}
