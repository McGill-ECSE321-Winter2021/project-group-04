package ca.mcgill.ecse321.projectgroup04.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup04.dao.AppointmentRepository;
import ca.mcgill.ecse321.projectgroup04.dao.GarageTechnicianRepository;
import ca.mcgill.ecse321.projectgroup04.model.Appointment;

import ca.mcgill.ecse321.projectgroup04.model.GarageTechnician;
import ca.mcgill.ecse321.projectgroup04.model.TimeSlot;

@ExtendWith(MockitoExtension.class)
public class TestGarageTechnicianService {

	@Mock
	private GarageTechnicianRepository garageTechnicianRepository;

	@Mock
	private AppointmentRepository appointmentRepository;

	@InjectMocks
	private GarageTechnicianService service;

	private static final String GARAGETECHNICIAN_NAME = "TestName";
	private static final String OLD_APPOINTMENT_DATE = "2021-03-18";
	private static final Integer OLD_APPOINTMENT_GARAGE_SPOT = 1;
	private static final String OLD_APPOINTMENT_START_TIME = "13:00:00";
	private static final String OLD_APPOINTMENT_END_TIME = "13:30:00";

	@BeforeEach
	public void setMockOutput() {
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(garageTechnicianRepository.save(any(GarageTechnician.class)))
				.thenAnswer(returnParameterAsAnswer);
		lenient().when(appointmentRepository.save(any(Appointment.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(appointmentRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			List<Appointment> appointments = new ArrayList<Appointment>();
			TimeSlot timeSlot = new TimeSlot();
			timeSlot.setEndTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_END_TIME)));
			timeSlot.setStartTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_START_TIME)));
			timeSlot.setEndDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE)));
			timeSlot.setStartDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE)));
			timeSlot.setGarageSpot(OLD_APPOINTMENT_GARAGE_SPOT);

			Appointment appointment = new Appointment();
			appointment.setTimeSlot(timeSlot);

			GarageTechnician garageTechTest = new GarageTechnician();
			garageTechTest.setName("GarageTechTest");

			appointment.setTechnician(garageTechTest);
			appointments.add(appointment);

			return appointments;
		});
		lenient().when(garageTechnicianRepository.findGarageTechnicianByName(anyString()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(GARAGETECHNICIAN_NAME)) {

						GarageTechnician garageTechnician = new GarageTechnician();
						garageTechnician.setName(GARAGETECHNICIAN_NAME);

						return garageTechnician;
					}
					return null;
				});
		lenient().when(garageTechnicianRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {

			List<GarageTechnician> techs = new ArrayList<GarageTechnician>();

			GarageTechnician garageTechnician = new GarageTechnician();
			garageTechnician.setName(GARAGETECHNICIAN_NAME);

			techs.add(garageTechnician);

			return techs;
		});

	}

	@Test

	public void TestCreateGarageTechnician() {

		String name = "NewName";

		GarageTechnician garageTechnician = null;
		try {
			garageTechnician = service.createGarageTechnician(name);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(garageTechnician);
		assertEquals(name, garageTechnician.getName());

	}

	@Test
	public void TestCreateGarageTechnicianNoName() {
		String name = "";

		String error = null;

		GarageTechnician garageTechnician = null;
		try {
			garageTechnician = service.createGarageTechnician(name);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(garageTechnician);
		assertEquals(error, "Name can't be empty");

	}

	@Test
	public void TestCreateGarageTechnicianAlreadyExists() {
		String name = "TestName";

		String error = null;

		GarageTechnician garageTechnician = null;
		try {
			garageTechnician = service.createGarageTechnician(name);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(garageTechnician);
		assertEquals(error, "Garage Technician with this name already exists");

	}

	@Test
	public void TestDeleteGarageTechnician() {
		String name = "TestNameDelete";
		GarageTechnician garageTechnician = new GarageTechnician();
		garageTechnician.setName(name);

		try {
			garageTechnician = service.deleteGarageTechnician(garageTechnician);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNull(garageTechnician);

	}

}
