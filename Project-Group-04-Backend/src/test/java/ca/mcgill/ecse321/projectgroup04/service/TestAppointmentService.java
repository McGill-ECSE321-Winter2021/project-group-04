package ca.mcgill.ecse321.projectgroup04.service;

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

import ca.mcgill.ecse321.projectgroup04.dao.AppointmentReminderRepository;
import ca.mcgill.ecse321.projectgroup04.dao.AppointmentRepository;
import ca.mcgill.ecse321.projectgroup04.dao.BookableServiceRepository;
import ca.mcgill.ecse321.projectgroup04.dao.BusinessHourRepository;
import ca.mcgill.ecse321.projectgroup04.dao.CustomerRepository;
import ca.mcgill.ecse321.projectgroup04.dao.GarageTechnicianRepository;
import ca.mcgill.ecse321.projectgroup04.dao.ReceiptRepository;
import ca.mcgill.ecse321.projectgroup04.dao.TimeSlotRepository;
import ca.mcgill.ecse321.projectgroup04.model.Appointment;
import ca.mcgill.ecse321.projectgroup04.model.AppointmentReminder;
import ca.mcgill.ecse321.projectgroup04.model.BookableService;
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour;
import ca.mcgill.ecse321.projectgroup04.model.Customer;
import ca.mcgill.ecse321.projectgroup04.model.GarageTechnician;
import ca.mcgill.ecse321.projectgroup04.model.Receipt;
import ca.mcgill.ecse321.projectgroup04.model.TimeSlot;
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour.DayOfWeek;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class TestAppointmentService {

	@Mock
	private AppointmentRepository appointmentRepository;

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private TimeSlotRepository timeSlotRepository;

	@Mock
	private BookableServiceRepository bookableServiceRepository;

	@Mock
	private ReceiptRepository receiptRepository;

	@Mock
	private GarageTechnicianRepository garageTechnicianRepository;

	@Mock
	private AppointmentReminderRepository appointmentReminderRepository;

	@Mock
	private BusinessHourRepository businessHourRepository;

	@InjectMocks
	private AppointmentService service;

	private static final String CUSTOMER_USERID = "TestUserId";
	private static final String CUSTOMER_PASSWORD = "TestPassword0";

	private static final String SERVICE_NAME = "ServiceName1";
	private static final int SERVICE_PRICE = 50;
	private static final int SERVICE_DURATION = 30;

	private static final String OLD_APPOINTMENT_DATE = "2021-04-20";
	private static final Integer OLD_APPOINTMENT_GARAGE_SPOT = 1;
	private static final String OLD_APPOINTMENT_START_TIME = "13:00:00";
	private static final String OLD_APPOINTMENT_END_TIME = "13:30:00";

	private static final String OLD_APPOINTMENT_REMINDER_DATE = "2021-04-19";
	private static final String OLD_APPOINTMENT_REMINDER_TIME = "13:00:00";
	private static final String OLD_APPOINTMENT_REMINDER_MESSAGE = "You have an appointment in 24 hours";

	private static final Long GARAGE_TECHNICIAN_ID = 4587l;
	private static final String GARAGE_TECHNICIAN_NAME = "Harry Potter";

	private static final Long BUSINESSHOUR_ID = 123l;
	private static final DayOfWeek BUSINESSHOUR_DAY = DayOfWeek.Tuesday;
	private static final String BUSINESSHOUR_STARTTIME = "09:00:00";
	private static final String BUSINESSHOUR_ENDTIME = "17:00:00";

	private static final Long APPOINTMENT_TO_DELETE_ID = 3423l;

	@BeforeEach
	public void setMockOutput() {
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(businessHourRepository.save(any(BusinessHour.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(appointmentRepository.save(any(Appointment.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(timeSlotRepository.save(any(TimeSlot.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(customerRepository.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(bookableServiceRepository.save(any(BookableService.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(garageTechnicianRepository.save(any(GarageTechnician.class)))
				.thenAnswer(returnParameterAsAnswer);
		lenient().when(receiptRepository.save(any(Receipt.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(appointmentReminderRepository.save(any(AppointmentReminder.class)))
				.thenAnswer(returnParameterAsAnswer);

		lenient().when(customerRepository.findCustomerByUserId(anyString()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(CUSTOMER_USERID)) {
						Customer customer = new Customer();
						customer.setPassword(CUSTOMER_PASSWORD);
						customer.setUserId(CUSTOMER_USERID);

						return customer;
					} else {
						return null;
					}
				});
		lenient().when(bookableServiceRepository.findBookableServiceByName(anyString()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(SERVICE_NAME)) {
						BookableService bookableService = new BookableService();
						bookableService.setDuration(SERVICE_DURATION);
						bookableService.setName(SERVICE_NAME);
						bookableService.setPrice(SERVICE_PRICE);

						return bookableService;
					}

					return null;
				});
		lenient().when(bookableServiceRepository.findAll()).thenAnswer((InvocationOnMock invocation)->{
			List<BookableService> bookableServices = new ArrayList<>();
			BookableService bookableService = new BookableService();
			bookableService.setName(SERVICE_NAME);
			bookableService.setPrice(SERVICE_PRICE);
			bookableService.setDuration(SERVICE_DURATION);
			bookableServices.add(bookableService);
			return bookableServices;
		});
		lenient().when(appointmentRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			List<Appointment> appointments = new ArrayList<>();
			TimeSlot timeSlot = new TimeSlot();
			timeSlot.setEndTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_END_TIME)));
			timeSlot.setStartTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_START_TIME)));
			timeSlot.setEndDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE)));
			timeSlot.setStartDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE)));
			timeSlot.setGarageSpot(OLD_APPOINTMENT_GARAGE_SPOT);

			Appointment appointment = new Appointment();
			appointment.setTimeSlot(timeSlot);

			appointments.add(appointment);

			return appointments;
		});
		lenient().when(garageTechnicianRepository.findGarageTechnicianByTechnicianId(anyLong()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(GARAGE_TECHNICIAN_ID)) {
						GarageTechnician garageTechnician = new GarageTechnician();
						garageTechnician.setName(GARAGE_TECHNICIAN_NAME);
						garageTechnician.setTechnicianId(GARAGE_TECHNICIAN_ID);

						return garageTechnician;
					}
					return null;
				});

		lenient().when(appointmentRepository.findByAppointmentId(anyLong()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(APPOINTMENT_TO_DELETE_ID)) {

						Appointment appointment = new Appointment();
						appointment.setAppointmentId(APPOINTMENT_TO_DELETE_ID);

						Customer customer = new Customer();
						customer.setPassword(CUSTOMER_PASSWORD);
						customer.setUserId(CUSTOMER_USERID);

						BookableService bookableService = new BookableService();
						bookableService.setDuration(SERVICE_DURATION);
						bookableService.setName(SERVICE_NAME);
						bookableService.setPrice(SERVICE_PRICE);

						GarageTechnician garageTechnician = new GarageTechnician();
						garageTechnician.setName(GARAGE_TECHNICIAN_NAME);
						garageTechnician.setTechnicianId(GARAGE_TECHNICIAN_ID);

						Receipt receipt = new Receipt();
						receipt.setTotalPrice(SERVICE_PRICE);

						TimeSlot timeSlot = new TimeSlot();
						timeSlot.setEndDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE)));
						timeSlot.setStartDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE)));
						timeSlot.setStartTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_START_TIME)));
						timeSlot.setEndTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_END_TIME)));
						timeSlot.setGarageSpot(OLD_APPOINTMENT_GARAGE_SPOT);

						AppointmentReminder appointmentReminder = new AppointmentReminder();
						appointmentReminder.setMessage(OLD_APPOINTMENT_REMINDER_MESSAGE);
						appointmentReminder.setDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_REMINDER_DATE)));
						appointmentReminder.setTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_REMINDER_TIME)));

						appointment.setCustomer(customer);
						appointment.setBookableServices(bookableService);
						appointment.setReceipt(receipt);
						appointment.setReminder(appointmentReminder);
						appointment.setTechnician(garageTechnician);
						appointment.setTimeSlot(timeSlot);

						return appointment;
					}
					return null;
				});
		lenient().when(businessHourRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {

			List<BusinessHour> businessHours = new ArrayList<BusinessHour>();

			BusinessHour businessHour = new BusinessHour();
			businessHour.setDayOfWeek(BUSINESSHOUR_DAY);
			businessHour.setStartTime(Time.valueOf(LocalTime.parse(BUSINESSHOUR_STARTTIME)));
			businessHour.setEndTime(Time.valueOf(LocalTime.parse(BUSINESSHOUR_ENDTIME)));
			businessHour.setHourId(BUSINESSHOUR_ID);

			businessHours.add(businessHour);

			return businessHours;
		});

	}

	@Test
	public void TestBookAppointment() {
		String serviceName = "ServiceName1";
		String userId = "TestUserId";
		Time startTime = Time.valueOf(LocalTime.parse("10:00:00"));
		Date date = Date.valueOf(LocalDate.parse("2021-04-20"));
		Long garageTechnicianId = 4587l;
		Integer garageSpot = 2;

		Appointment appointment = null;
		try {
			appointment = service.bookAppointment(userId, serviceName, date, garageSpot, startTime, garageTechnicianId);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(appointment);
		assertEquals(serviceName, appointment.getBookableServices().getName());
		assertEquals(userId, appointment.getCustomer().getUserId());
		assertEquals(startTime, appointment.getTimeSlot().getStartTime());
		assertEquals(date, appointment.getTimeSlot().getStartDate());
		assertNotNull(appointment.getReceipt());
		assertNotNull(appointment.getReminder());
		assertEquals(appointment.getReceipt().getTotalPrice(), appointment.getBookableServices().getPrice());
	}

	@Test
	public void TestBookAppointmentNotWithinBusinessHours1() {
		String serviceName = "ServiceName1";
		String userId = "TestUserId";
		Time startTime = Time.valueOf(LocalTime.parse("08:00:00"));
		Date date = Date.valueOf(LocalDate.parse("2021-04-20"));
		Long garageTechnicianId = 4587l;
		Integer garageSpot = 2;

		String error = null;

		Appointment appointment = null;
		try {
			appointment = service.bookAppointment(userId, serviceName, date, garageSpot, startTime, garageTechnicianId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(appointment);
		assertEquals(error, "This time doesn't fall within business hours!");
	}

	@Test
	public void TestBookAppointmentNotWithinBusinessHours2() {
		String serviceName = "ServiceName1";
		String userId = "TestUserId";
		Time startTime = Time.valueOf(LocalTime.parse("16:45:00"));
		Date date = Date.valueOf(LocalDate.parse("2021-04-20"));
		Long garageTechnicianId = 4587l;
		Integer garageSpot = 2;

		String error = null;

		Appointment appointment = null;
		try {
			appointment = service.bookAppointment(userId, serviceName, date, garageSpot, startTime, garageTechnicianId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(appointment);
		assertEquals(error, "This time doesn't fall within business hours!");
	}

	@Test
	public void TestBookAppointmentNoCustomer() {
		String serviceName = "ServiceName1";
		String userId = "wrongUserId";
		Time startTime = Time.valueOf(LocalTime.parse("10:00:00"));
		Date date = Date.valueOf(LocalDate.parse("2021-03-20"));
		Long garageTechnicianId = 4587l;
		Integer garageSpot = 2;

		String error = null;

		Appointment appointment = null;
		try {
			appointment = service.bookAppointment(userId, serviceName, date, garageSpot, startTime, garageTechnicianId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(appointment);
		assertEquals(error, "No customer with such userId!");
	}

	@Test
	public void TestBookAppointmentNoService() {
		String serviceName = "wrongServiceName";
		String userId = "TestUserId";
		Time startTime = Time.valueOf(LocalTime.parse("10:00:00"));
		Date date = Date.valueOf(LocalDate.parse("2021-03-20"));
		Long garageTechnicianId = 4587l;
		Integer garageSpot = 2;

		String error = null;

		Appointment appointment = null;
		try {
			appointment = service.bookAppointment(userId, serviceName, date, garageSpot, startTime, garageTechnicianId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(appointment);
		assertEquals(error, "No Bookable Service with such name!");
	}

	@Test
	public void TestBookAppointmentOverlapDifferentSpot() {
		String serviceName = "ServiceName1";
		String userId = "TestUserId";
		Time startTime = Time.valueOf(LocalTime.parse("12:45:00"));
		Date date = Date.valueOf(LocalDate.parse("2021-04-20"));
		Long garageTechnicianId = 4587l;
		Integer garageSpot = 2;

		Appointment appointment = null;
		try {
			appointment = service.bookAppointment(userId, serviceName, date, garageSpot, startTime, garageTechnicianId);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(appointment);
		assertEquals(serviceName, appointment.getBookableServices().getName());
		assertEquals(userId, appointment.getCustomer().getUserId());
		assertEquals(startTime, appointment.getTimeSlot().getStartTime());
		assertEquals(date, appointment.getTimeSlot().getStartDate());
		assertNotNull(appointment.getReceipt());
		assertNotNull(appointment.getReminder());
		assertEquals(appointment.getReceipt().getTotalPrice(), appointment.getBookableServices().getPrice());
	}

	@Test
	public void TestBookAppointmentOverlapSameSpot() {
		String serviceName = "ServiceName1";
		String userId = "TestUserId";
		Time startTime = Time.valueOf(LocalTime.parse("12:45:00"));
		Date date = Date.valueOf(LocalDate.parse("2021-04-20"));
		Long garageTechnicianId = 4587l;
		Integer garageSpot = 1;
		String error = null;

		Appointment appointment = null;
		try {
			appointment = service.bookAppointment(userId, serviceName, date, garageSpot, startTime, garageTechnicianId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(appointment);
		assertEquals(error, "This attempted booking overlaps with another!");

	}

	@Test
	public void TestCreateAppointment() {
		Customer customer = new Customer();
		BookableService bookableService = new BookableService();
		GarageTechnician garageTechnician = new GarageTechnician();
		TimeSlot timeSlot = new TimeSlot();
		AppointmentReminder appointmentReminder = new AppointmentReminder();
		Receipt receipt = new Receipt();

		Appointment appointment = null;

		try {
			appointment = service.createAppointment(customer, bookableService, garageTechnician, timeSlot,
					appointmentReminder, receipt);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(appointment);
		assertNotNull(appointment.getBookableServices());
		assertNotNull(appointment.getCustomer());
		assertNotNull(appointment.getReceipt());
		assertNotNull(appointment.getReminder());
		assertNotNull(appointment.getTimeSlot());
		assertNotNull(appointment.getTechnician());
	}

	@Test
	public void TestCreateAppointmentCustomerNull() {
		Customer customer = null;
		BookableService bookableService = new BookableService();
		GarageTechnician garageTechnician = new GarageTechnician();
		TimeSlot timeSlot = new TimeSlot();
		AppointmentReminder appointmentReminder = new AppointmentReminder();
		Receipt receipt = new Receipt();

		String error = null;

		Appointment appointment = null;

		try {
			appointment = service.createAppointment(customer, bookableService, garageTechnician, timeSlot,
					appointmentReminder, receipt);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(appointment);
		assertEquals(error, "Customer can't be null");
	}

	@Test
	public void TestCreateAppointmentBookableServiceNull() {
		Customer customer = new Customer();
		BookableService bookableService = null;
		GarageTechnician garageTechnician = new GarageTechnician();
		TimeSlot timeSlot = new TimeSlot();
		AppointmentReminder appointmentReminder = new AppointmentReminder();
		Receipt receipt = new Receipt();

		String error = null;

		Appointment appointment = null;

		try {
			appointment = service.createAppointment(customer, bookableService, garageTechnician, timeSlot,
					appointmentReminder, receipt);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(appointment);
		assertEquals(error, "Bookable Service can't be null");
	}

	@Test
	public void TestCreateAppointmentGarageTechnicianNull() {
		Customer customer = new Customer();
		BookableService bookableService = new BookableService();
		GarageTechnician garageTechnician = null;
		TimeSlot timeSlot = new TimeSlot();
		AppointmentReminder appointmentReminder = new AppointmentReminder();
		Receipt receipt = new Receipt();

		String error = null;

		Appointment appointment = null;

		try {
			appointment = service.createAppointment(customer, bookableService, garageTechnician, timeSlot,
					appointmentReminder, receipt);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(appointment);
		assertEquals(error, "Garage Technician can't be null");
	}

	@Test
	public void TestCreateAppointmentTimeSlotNull() {
		Customer customer = new Customer();
		BookableService bookableService = new BookableService();
		GarageTechnician garageTechnician = new GarageTechnician();
		TimeSlot timeSlot = null;
		AppointmentReminder appointmentReminder = new AppointmentReminder();
		Receipt receipt = new Receipt();

		String error = null;

		Appointment appointment = null;

		try {
			appointment = service.createAppointment(customer, bookableService, garageTechnician, timeSlot,
					appointmentReminder, receipt);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(appointment);
		assertEquals(error, "Time Slot can't be null");
	}

	@Test
	public void TestCreateAppointmentAppointmentReminderNull() {
		Customer customer = new Customer();
		BookableService bookableService = new BookableService();
		GarageTechnician garageTechnician = new GarageTechnician();
		TimeSlot timeSlot = new TimeSlot();
		AppointmentReminder appointmentReminder = null;
		Receipt receipt = new Receipt();

		String error = null;

		Appointment appointment = null;

		try {
			appointment = service.createAppointment(customer, bookableService, garageTechnician, timeSlot,
					appointmentReminder, receipt);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(appointment);
		assertEquals(error, "Appointment Reminder can't be null");
	}

	@Test
	public void TestCreateAppointmentReceiptNull() {
		Customer customer = new Customer();
		BookableService bookableService = new BookableService();
		GarageTechnician garageTechnician = new GarageTechnician();
		TimeSlot timeSlot = new TimeSlot();
		AppointmentReminder appointmentReminder = new AppointmentReminder();
		Receipt receipt = null;

		String error = null;

		Appointment appointment = null;

		try {
			appointment = service.createAppointment(customer, bookableService, garageTechnician, timeSlot,
					appointmentReminder, receipt);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(appointment);
		assertEquals(error, "Receipt can't be null");
	}

	@Test
	public void TestGetAppointment() {
		Appointment appointment = null;
		try {
			appointment = service.getAppointment(3423l);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(appointment);
		assertNotNull(appointment.getBookableServices());
		assertNotNull(appointment.getCustomer());
		assertNotNull(appointment.getReceipt());
		assertNotNull(appointment.getTechnician());
		assertNotNull(appointment.getTimeSlot());
		assertNotNull(appointment.getReminder());
		assertEquals(CUSTOMER_USERID, appointment.getCustomer().getUserId());
		assertEquals(GARAGE_TECHNICIAN_ID, appointment.getTechnician().getTechnicianId());
		assertEquals(GARAGE_TECHNICIAN_NAME, appointment.getTechnician().getName());
		assertEquals(SERVICE_NAME, appointment.getBookableServices().getName());
		assertEquals(SERVICE_DURATION, appointment.getBookableServices().getDuration());
		assertEquals(SERVICE_PRICE, appointment.getBookableServices().getPrice());
		assertEquals(CUSTOMER_PASSWORD, appointment.getCustomer().getPassword());
		assertEquals(OLD_APPOINTMENT_REMINDER_MESSAGE, appointment.getReminder().getMessage());
		assertEquals(OLD_APPOINTMENT_REMINDER_DATE, appointment.getReminder().getDate().toString());
		assertEquals(OLD_APPOINTMENT_REMINDER_TIME, appointment.getReminder().getTime().toString());
		assertEquals(SERVICE_PRICE, appointment.getReceipt().getTotalPrice());
		assertEquals(OLD_APPOINTMENT_START_TIME, appointment.getTimeSlot().getStartTime().toString());
		assertEquals(OLD_APPOINTMENT_END_TIME, appointment.getTimeSlot().getEndTime().toString());
		assertEquals(OLD_APPOINTMENT_DATE, appointment.getTimeSlot().getStartDate().toString());
		assertEquals(OLD_APPOINTMENT_DATE, appointment.getTimeSlot().getEndDate().toString());
		assertEquals(OLD_APPOINTMENT_GARAGE_SPOT, appointment.getTimeSlot().getGarageSpot());
	}

	@Test
	public void TestGetAppointmentInvalidId() {
		Appointment appointment = null;
		String error = null;
		try {
			appointment = service.getAppointment(343l);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(appointment);
		assertEquals(error, "No appointment with such ID exist!");
	}

	@Test
	public void TestDeleteAppointment() {
		Appointment appointment = new Appointment();
		appointment.setAppointmentId(APPOINTMENT_TO_DELETE_ID);

		Customer customer = new Customer();
		customer.setPassword(CUSTOMER_PASSWORD);
		customer.setUserId(CUSTOMER_USERID);

		BookableService bookableService = new BookableService();
		bookableService.setDuration(SERVICE_DURATION);
		bookableService.setName(SERVICE_NAME);
		bookableService.setPrice(SERVICE_PRICE);

		GarageTechnician garageTechnician = new GarageTechnician();
		garageTechnician.setName(GARAGE_TECHNICIAN_NAME);
		garageTechnician.setTechnicianId(GARAGE_TECHNICIAN_ID);

		Receipt receipt = new Receipt();
		receipt.setTotalPrice(SERVICE_PRICE);

		LocalDate testDate = LocalDate.parse("2021-03-17");
		LocalTime testTime = LocalTime.parse("16:45:00");

		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setEndDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE).plusDays(1)));
		timeSlot.setStartDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE).plusDays(1)));
		timeSlot.setStartTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_START_TIME)));
		timeSlot.setEndTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_END_TIME)));
		timeSlot.setGarageSpot(OLD_APPOINTMENT_GARAGE_SPOT);

		AppointmentReminder appointmentReminder = new AppointmentReminder();
		appointmentReminder.setMessage(OLD_APPOINTMENT_REMINDER_MESSAGE);
		appointmentReminder.setDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_REMINDER_DATE)));
		appointmentReminder.setTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_REMINDER_TIME)));

		appointment.setCustomer(customer);
		appointment.setBookableServices(bookableService);
		appointment.setReceipt(receipt);
		appointment.setReminder(appointmentReminder);
		appointment.setTechnician(garageTechnician);
		appointment.setTimeSlot(timeSlot);

		try {
			appointment = service.deleteAppointment(appointment, testTime, testDate);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			fail();
		}

		assertNull(appointment);

	}

	@Test
	public void TestDeleteAppointmentSameDay() {
		Appointment appointment = new Appointment();
		appointment.setAppointmentId(APPOINTMENT_TO_DELETE_ID);

		Customer customer = new Customer();
		customer.setPassword(CUSTOMER_PASSWORD);
		customer.setUserId(CUSTOMER_USERID);

		BookableService bookableService = new BookableService();
		bookableService.setDuration(SERVICE_DURATION);
		bookableService.setName(SERVICE_NAME);
		bookableService.setPrice(SERVICE_PRICE);

		GarageTechnician garageTechnician = new GarageTechnician();
		garageTechnician.setName(GARAGE_TECHNICIAN_NAME);
		garageTechnician.setTechnicianId(GARAGE_TECHNICIAN_ID);

		Receipt receipt = new Receipt();
		receipt.setTotalPrice(SERVICE_PRICE);

		LocalDate testDate = LocalDate.parse("2021-04-19");
		LocalTime testTime = LocalTime.parse("16:45:00");

		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setEndDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE).minusDays(1)));
		timeSlot.setStartDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE).minusDays(1)));
		timeSlot.setStartTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_START_TIME)));
		timeSlot.setEndTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_END_TIME)));
		timeSlot.setGarageSpot(OLD_APPOINTMENT_GARAGE_SPOT);

		AppointmentReminder appointmentReminder = new AppointmentReminder();
		appointmentReminder.setMessage(OLD_APPOINTMENT_REMINDER_MESSAGE);
		appointmentReminder.setDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_REMINDER_DATE)));
		appointmentReminder.setTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_REMINDER_TIME)));

		appointment.setCustomer(customer);
		appointment.setBookableServices(bookableService);
		appointment.setReceipt(receipt);
		appointment.setReminder(appointmentReminder);
		appointment.setTechnician(garageTechnician);
		appointment.setTimeSlot(timeSlot);

		String error = null;

		try {
			appointment = service.deleteAppointment(appointment, testTime, testDate);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNotNull(appointment);
		assertEquals(error, "Cannot cancel appointment on same day!");

	}

	@Test
	public void TestDeleteAppointmentLessThan24() {
		Appointment appointment = new Appointment();
		appointment.setAppointmentId(APPOINTMENT_TO_DELETE_ID);

		Customer customer = new Customer();
		customer.setPassword(CUSTOMER_PASSWORD);
		customer.setUserId(CUSTOMER_USERID);

		BookableService bookableService = new BookableService();
		bookableService.setDuration(SERVICE_DURATION);
		bookableService.setName(SERVICE_NAME);
		bookableService.setPrice(SERVICE_PRICE);

		GarageTechnician garageTechnician = new GarageTechnician();
		garageTechnician.setName(GARAGE_TECHNICIAN_NAME);
		garageTechnician.setTechnicianId(GARAGE_TECHNICIAN_ID);

		Receipt receipt = new Receipt();
		receipt.setTotalPrice(SERVICE_PRICE);

		LocalDate testDate = LocalDate.parse("2021-04-19");
		LocalTime testTime = LocalTime.parse("16:45:00");

		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setEndDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE)));
		timeSlot.setStartDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE)));
		timeSlot.setStartTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_START_TIME)));
		timeSlot.setEndTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_END_TIME)));
		timeSlot.setGarageSpot(OLD_APPOINTMENT_GARAGE_SPOT);

		AppointmentReminder appointmentReminder = new AppointmentReminder();
		appointmentReminder.setMessage(OLD_APPOINTMENT_REMINDER_MESSAGE);
		appointmentReminder.setDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_REMINDER_DATE)));
		appointmentReminder.setTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_REMINDER_TIME)));

		appointment.setCustomer(customer);
		appointment.setBookableServices(bookableService);
		appointment.setReceipt(receipt);
		appointment.setReminder(appointmentReminder);
		appointment.setTechnician(garageTechnician);
		appointment.setTimeSlot(timeSlot);

		String error = null;

		try {
			appointment = service.deleteAppointment(appointment, testTime, testDate);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNotNull(appointment);
		assertEquals(error, "Cannot cancel appointment less than 24 hours!");

	}

}
