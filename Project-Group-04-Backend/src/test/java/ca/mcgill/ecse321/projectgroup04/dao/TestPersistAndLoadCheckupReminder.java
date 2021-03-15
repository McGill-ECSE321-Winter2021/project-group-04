package ca.mcgill.ecse321.projectgroup04.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup04.model.CheckupReminder;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistAndLoadCheckupReminder {

    @Autowired
    private CheckupReminderRepository checkupReminderRepository;

    @AfterEach
    public void clearDataBase() {
        checkupReminderRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testPersistAndLoadCheckupReminder() {
        CheckupReminder checkupReminder = new CheckupReminder();
        Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Time time = java.sql.Time.valueOf(LocalTime.of(11, 35));
        String message = "testMessage";

        checkupReminder.setDate(date);
        checkupReminder.setTime(time);
        checkupReminder.setMessage(message);

        checkupReminderRepository.save(checkupReminder);
        Long checkupReminderId = checkupReminder.getReminderId();

        checkupReminder = null;
        checkupReminder = checkupReminderRepository.findByReminderId(checkupReminderId);

        assertNotNull(checkupReminder);
        assertEquals(checkupReminderId, checkupReminder.getReminderId());
        assertEquals(message, checkupReminder.getMessage());
        assertEquals(date, checkupReminder.getDate());
        assertEquals(time, checkupReminder.getTime());

    }
}
