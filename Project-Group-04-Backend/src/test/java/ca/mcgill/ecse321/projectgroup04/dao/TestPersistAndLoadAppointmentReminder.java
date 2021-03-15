package ca.mcgill.ecse321.projectgroup04.dao;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup04.model.AppointmentReminder;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistAndLoadAppointmentReminder {

    @Autowired
    private AppointmentReminderRepository appointmentReminderRepository;

    @AfterEach
    public void clearDataBase() {
        appointmentReminderRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testPersistAndLoadAppointmentReminder() {

        Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Time time = java.sql.Time.valueOf(LocalTime.of(11, 35));
        String message = "It is tomorrow";
        AppointmentReminder appointmentReminder = new AppointmentReminder();

        appointmentReminder.setDate(date);
        appointmentReminder.setMessage(message);
        appointmentReminder.setTime(time);

        appointmentReminderRepository.save(appointmentReminder);
        Long arID = appointmentReminder.getReminderId();

        appointmentReminder = null;

        appointmentReminder = appointmentReminderRepository.findAppointmentReminderByReminderId(arID);

        assertNotNull(appointmentReminder);
        assertEquals(arID, appointmentReminder.getReminderId());
    }

}
