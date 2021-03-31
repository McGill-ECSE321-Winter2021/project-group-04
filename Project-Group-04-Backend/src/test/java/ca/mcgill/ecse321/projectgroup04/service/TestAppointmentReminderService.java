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

import ca.mcgill.ecse321.projectgroup04.dao.AppointmentReminderRepository;
import ca.mcgill.ecse321.projectgroup04.model.AppointmentReminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class TestAppointmentReminderService {

	@Mock
	private AppointmentReminderRepository appointmentReminderRepository;

	@InjectMocks
	private AppointmentReminderService service;

	private static final String APPOINTMENTREMINDER_DATE = "2021-03-18";
	private static final String APPOINTMENTREMINDER_TIME = "11:00:00";
	private static final String APPOINTMENTREMINDER_MESSAGE = "TestMessage";
	private static final Long APPOINTMENTREMINDER_ID = 123l;

	@BeforeEach
	public void setMockOutput() {
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(appointmentReminderRepository.save(any(AppointmentReminder.class)))
				.thenAnswer(returnParameterAsAnswer);

		lenient().when(appointmentReminderRepository.findById(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(APPOINTMENTREMINDER_ID)) {
				AppointmentReminder appointmentReminder = new AppointmentReminder();
				appointmentReminder.setDate(Date.valueOf(LocalDate.parse(APPOINTMENTREMINDER_DATE)));
				appointmentReminder.setTime(Time.valueOf(LocalTime.parse(APPOINTMENTREMINDER_TIME)));
				appointmentReminder.setMessage(APPOINTMENTREMINDER_MESSAGE);

				return appointmentReminder;
			}
			return null;

		});

	}

	@Test
	public void TestCreateAppointmentReminder() {

		Date date = Date.valueOf(LocalDate.parse("2021-03-20"));
		Time time = Time.valueOf(LocalTime.parse("10:00:00"));
		String message = "TestMessage";

		AppointmentReminder appointmentReminder = null;
		try {
			appointmentReminder = service.createAppointmentReminder(date, time, message);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(appointmentReminder);
		assertEquals(date, appointmentReminder.getDate());
		assertEquals(time, appointmentReminder.getTime());
		assertEquals(message, appointmentReminder.getMessage());

	}

	@Test
	public void TestCreateAppointmentReminderNoDate() {
		Date date = null;

		Time time = Time.valueOf(LocalTime.parse("10:00:00"));
		String message = "TestMessage";

		String error = null;

		AppointmentReminder appointmentReminder = null;
		try {
			appointmentReminder = service.createAppointmentReminder(date, time, message);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(appointmentReminder);
		assertEquals(error, "Date can't be null");

	}

	@Test
	public void TestCreateAppointmentNoTime() {
		Date date = Date.valueOf(LocalDate.parse("2021-03-20"));
		Time time = null;
		String message = "TestMessage";

		String error = null;

		AppointmentReminder appointmentReminder = null;
		try {
			appointmentReminder = service.createAppointmentReminder(date, time, message);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(appointmentReminder);
		assertEquals(error, "Time can't be null");

	}

	@Test
	public void TestCreateAdministrativeAssistantNoMessage() {
		Date date = Date.valueOf(LocalDate.parse("2021-03-20"));
		Time time = Time.valueOf(LocalTime.parse("10:00:00"));
		String message = "";

		String error = null;

		AppointmentReminder appointmentReminder = null;
		try {
			appointmentReminder = service.createAppointmentReminder(date, time, message);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(appointmentReminder);
		assertEquals(error, "Message can't be empty");

	}

	@Test
	public void TestDeleteAppointmentReminder() {
		Date date = Date.valueOf(LocalDate.parse("2021-03-20"));
		Time time = Time.valueOf(LocalTime.parse("10:00:00"));
		String message = "TestMessage";

		AppointmentReminder appointmentReminder = new AppointmentReminder();
		appointmentReminder.setDate(date);
		appointmentReminder.setTime(time);
		appointmentReminder.setMessage(message);
		try {
			appointmentReminder = service.deleteAppointmentReminder(appointmentReminder);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNull(appointmentReminder);

	}

	@Test
	public void TestEditMessageAppointmentReminder() {
		Date date = Date.valueOf(LocalDate.parse("2021-03-20"));
		Time time = Time.valueOf(LocalTime.parse("10:00:00"));
		String message = "TestMessage";
		String newMessage = "Hello";

		AppointmentReminder appointmentReminder = new AppointmentReminder();
		appointmentReminder.setDate(date);
		appointmentReminder.setTime(time);
		appointmentReminder.setMessage(message);
		try {
			appointmentReminder = service.editAppointmentReminder(appointmentReminder, newMessage);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(appointmentReminder);
		assertEquals(date, appointmentReminder.getDate());
		assertEquals(time, appointmentReminder.getTime());
		assertEquals(newMessage, appointmentReminder.getMessage());

	}

	@Test
	public void TestEditSameMessageAppointmentReminder() {
		Date date = Date.valueOf(LocalDate.parse("2021-03-20"));
		Time time = Time.valueOf(LocalTime.parse("10:00:00"));
		String message = "TestMessage";
		String newMessage = "TestMessage";
		String error = null;

		AppointmentReminder appointmentReminder = new AppointmentReminder();
		appointmentReminder.setDate(date);
		appointmentReminder.setTime(time);
		appointmentReminder.setMessage(message);
		try {
			appointmentReminder = service.editAppointmentReminder(appointmentReminder, newMessage);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNotNull(appointmentReminder);
		assertEquals(date, appointmentReminder.getDate());
		assertEquals(time, appointmentReminder.getTime());
		assertEquals(message, appointmentReminder.getMessage());
		assertEquals(error, "You have to change the message");

	}

}