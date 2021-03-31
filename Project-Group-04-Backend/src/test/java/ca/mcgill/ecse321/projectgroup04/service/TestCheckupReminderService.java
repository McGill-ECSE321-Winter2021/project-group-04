package ca.mcgill.ecse321.projectgroup04.service;

import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup04.dao.CheckupReminderRepository;
import ca.mcgill.ecse321.projectgroup04.model.CheckupReminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class TestCheckupReminderService {

    @Mock
    private CheckupReminderRepository checkupReminderRepository;

    @InjectMocks
    private CheckupReminderService service;

    private static final String CHECKUPREMINDER_DATE = "2021-03-18";
    private static final String CHECKUPREMINDER_TIME = "11:00:00";
    private static final String CHECKUPREMINDER_MESSAGE = "TestMessage";
    private static final Long CHECKUPREMINDER_ID = 123l;

    @BeforeEach
    public void setMockOutput() {
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(checkupReminderRepository.save(any(CheckupReminder.class))).thenAnswer(returnParameterAsAnswer);

        lenient().when(checkupReminderRepository.findByReminderId(anyLong()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (invocation.getArgument(0).equals(CHECKUPREMINDER_ID)) {
                        CheckupReminder checkupReminder = new CheckupReminder();
                        checkupReminder.setDate(Date.valueOf(LocalDate.parse(CHECKUPREMINDER_DATE)));
                        checkupReminder.setTime(Time.valueOf(LocalTime.parse(CHECKUPREMINDER_TIME)));
                        checkupReminder.setMessage(CHECKUPREMINDER_MESSAGE);

                        return checkupReminder;
                    }
                    return null;

                });

    }

    @Test
    public void TestcreateCheckupReminder() {

        Date date = Date.valueOf(LocalDate.parse("2021-03-20"));
        Time time = Time.valueOf(LocalTime.parse("10:00:00"));
        String message = "TestMessage";

        CheckupReminder checkupReminder = null;
        try {
            checkupReminder = service.createCheckupReminder(date, time, message);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(checkupReminder);
        assertEquals(date, checkupReminder.getDate());
        assertEquals(time, checkupReminder.getTime());
        assertEquals(message, checkupReminder.getMessage());

    }

    @Test
    public void TestcreateCheckupReminderNoDate() {
        Date date = null;

        Time time = Time.valueOf(LocalTime.parse("10:00:00"));
        String message = "TestMessage";

        String error = null;

        CheckupReminder checkupReminder = null;
        try {
            checkupReminder = service.createCheckupReminder(date, time, message);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(checkupReminder);
        assertEquals(error, "Date cannot be null");

    }

    @Test
    public void TestCreateAppointmentNoTime() {
        Date date = Date.valueOf(LocalDate.parse("2021-03-20"));
        Time time = null;
        String message = "TestMessage";

        String error = null;

        CheckupReminder checkupReminder = null;
        try {
            checkupReminder = service.createCheckupReminder(date, time, message);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(checkupReminder);
        assertEquals(error, "Time cannot be null");

    }

    @Test
    public void TestCreateAdministrativeAssistantNoMessage() {
        Date date = Date.valueOf(LocalDate.parse("2021-03-20"));
        Time time = Time.valueOf(LocalTime.parse("10:00:00"));
        String message = "";

        String error = null;

        CheckupReminder checkupReminder = null;
        try {
            checkupReminder = service.createCheckupReminder(date, time, message);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(checkupReminder);
        assertEquals(error, "Message cannot be empty");

    }

    @Test
    public void TestEditCheckupReminder() {
        Date date = Date.valueOf(LocalDate.parse("2021-05-20"));
        Time time = Time.valueOf(LocalTime.parse("14:00:00"));
        String newMessage = "Hello";

        CheckupReminder checkupReminder = null;
        try {
            checkupReminder = service.editCheckupReminder(CHECKUPREMINDER_ID, date, time, newMessage);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(checkupReminder);
        assertEquals(date, checkupReminder.getDate());
        assertEquals(time, checkupReminder.getTime());
        assertEquals(newMessage, checkupReminder.getMessage());

    }

    @Test
    public void TestEditCheckupReminderNoDate() {
        Date date = null;
        Time time = Time.valueOf(LocalTime.parse("14:00:00"));
        String newMessage = "Hello";

        CheckupReminder checkupReminder = null;
        try {
            checkupReminder = service.editCheckupReminder(CHECKUPREMINDER_ID, date, time, newMessage);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(checkupReminder);
        assertEquals(CHECKUPREMINDER_DATE, checkupReminder.getDate().toString());
        assertEquals(time, checkupReminder.getTime());
        assertEquals(newMessage, checkupReminder.getMessage());

    }

    @Test
    public void TestEditCheckupReminderNoTime() {
        Date date = Date.valueOf(LocalDate.parse("2021-05-20"));
        Time time = null;
        String newMessage = "Hello";

        CheckupReminder checkupReminder = null;
        try {
            checkupReminder = service.editCheckupReminder(CHECKUPREMINDER_ID, date, time, newMessage);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(checkupReminder);
        assertEquals(date, checkupReminder.getDate());
        assertEquals(CHECKUPREMINDER_TIME, checkupReminder.getTime().toString());
        assertEquals(newMessage, checkupReminder.getMessage());

    }

    @Test
    public void TestEditCheckupReminderNoMessage() {
        Date date = Date.valueOf(LocalDate.parse("2021-05-20"));
        Time time = Time.valueOf(LocalTime.parse("14:00:00"));
        String newMessage = "";

        CheckupReminder checkupReminder = null;
        try {
            checkupReminder = service.editCheckupReminder(CHECKUPREMINDER_ID, date, time, newMessage);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(checkupReminder);
        assertEquals(date, checkupReminder.getDate());
        assertEquals(time, checkupReminder.getTime());
        assertEquals(CHECKUPREMINDER_MESSAGE, checkupReminder.getMessage());

    }

}