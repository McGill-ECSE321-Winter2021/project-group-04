package ca.mcgill.ecse321.projectgroup04.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;
import java.time.LocalTime;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup04.model.BusinessHour;
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour.DayOfWeek;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistAndLoadBusninessHour {

    @Autowired
    private BusinessHourRepository businessHourRepository;

    @AfterEach
    public void clearDatabase() {
        businessHourRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testPersistAndLoadBusinessHour() {
        BusinessHour businessHour = new BusinessHour();
        Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
        Time endTime = java.sql.Time.valueOf(LocalTime.of(12, 35));
        DayOfWeek dow = DayOfWeek.Saturday;

        businessHour.setDayOfWeek(dow);
        businessHour.setEndTime(startTime);
        businessHour.setEndTime(endTime);
        businessHourRepository.save(businessHour);
        Long businessHourId = businessHour.getHourId();

        businessHour = null;
        businessHour = businessHourRepository.findBusinessHourByHourId(businessHourId);

        assertNotNull(businessHour);
        assertEquals(dow, businessHour.getDayOfWeek());
        assertEquals(businessHourId, businessHour.getHourId());

    }

}
