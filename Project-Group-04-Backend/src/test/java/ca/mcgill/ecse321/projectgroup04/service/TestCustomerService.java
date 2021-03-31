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

import ca.mcgill.ecse321.projectgroup04.dao.CarRepository;
import ca.mcgill.ecse321.projectgroup04.dao.CustomerRepository;
import ca.mcgill.ecse321.projectgroup04.dao.ProfileRepository;
import ca.mcgill.ecse321.projectgroup04.dao.ReminderRepository;
import ca.mcgill.ecse321.projectgroup04.model.AppointmentReminder;
import ca.mcgill.ecse321.projectgroup04.model.Car;
import ca.mcgill.ecse321.projectgroup04.model.Customer;
import ca.mcgill.ecse321.projectgroup04.model.Profile;
import ca.mcgill.ecse321.projectgroup04.model.Reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class TestCustomerService {

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private CarRepository carRepository;

	@Mock
	private ProfileRepository profileRepository;

	@Mock
	private ReminderRepository reminderRepository;

	@InjectMocks
	private CustomerService service;

	private static final String userId = "Henry";
	private static final String password = "charles";
	private static final Long id = 123456l;

	private static final String FIRST_NAME1 = "Harry";
	private static final String LAST_NAME1 = "Potter";
	private static final String ADDRESS_LINE1 = "Hogwarts, London";
	private static final String EMAIL_ADDRESS1 = "harry@potter";
	private static final String PHONE_NUMBER1 = "8889995555";
	private static final String ZIP_CODE1 = "H9P5F0";
	private static final Long PROFILE_ID1 = 5653l;

	private static final String model = "civic";
	private static final String year = "2001";
	private static final String color = "orange";
	private static final Long carId = 1234l;

	private static final String APPOINTMENTREMINDER_DATE = "2021-03-18";
	private static final String APPOINTMENTREMINDER_TIME = "11:00:00";
	private static final String APPOINTMENTREMINDER_MESSAGE = "TestMessage";
	private static final Long APPOINTMENTREMINDER_ID = 123l;

	private static final List<Reminder> reminders = new ArrayList<Reminder>();

	@BeforeEach
	public void setMockOutput() {
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};

		lenient().when(customerRepository.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(carRepository.save(any(Car.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(reminderRepository.save(any(Reminder.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(profileRepository.save(any(Profile.class))).thenAnswer(returnParameterAsAnswer);

		lenient().when(customerRepository.findCustomerByUserId(anyString()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(userId)) {

						Customer customer = new Customer();
						customer.setId(id);
						customer.setPassword(password);
						customer.setUserId(userId);

						Profile profile = new Profile();
						profile.setAddressLine(ADDRESS_LINE1);
						profile.setEmailAddress(EMAIL_ADDRESS1);
						profile.setFirstName(FIRST_NAME1);
						profile.setLastName(LAST_NAME1);
						profile.setPhoneNumber(PHONE_NUMBER1);
						profile.setProfileId(PROFILE_ID1);
						profile.setZipCode(ZIP_CODE1);

						Car car = new Car();
						car.setCarId(carId);
						car.setColor(color);
						car.setModel(model);
						car.setYear(year);

						Reminder reminder = new AppointmentReminder();
						reminder.setDate(Date.valueOf(LocalDate.parse(APPOINTMENTREMINDER_DATE)));
						reminder.setMessage(APPOINTMENTREMINDER_MESSAGE);
						reminder.setReminderId(APPOINTMENTREMINDER_ID);
						reminder.setTime(Time.valueOf(LocalTime.parse(APPOINTMENTREMINDER_TIME)));

						reminders.add(reminder);

						customer.setCar(car);
						customer.setCustomerProfile(profile);
						customer.setReminders(reminders);

						return customer;
					}
					return null;
				});
	}

	@Test
	public void testCreateCustomer() {

		String userId = "Henryy";
		String password = "charlesyy";

		String FIRST_NAME1 = "Harryy";
		String LAST_NAME1 = "Potteyr";
		String ADDRESS_LINE1 = "Hogwarts, Londony";
		String EMAIL_ADDRESS1 = "harry@pottery";
		String PHONE_NUMBER1 = "8889995557";
		String ZIP_CODE1 = "H9P5F1";
		Long PROFILE_ID1 = 5673l;

		String model = "civicy";
		String year = "2002";
		String color = "orangey";
		Long carId = 12347l;

		String APPOINTMENTREMINDER_DATE = "2021-03-19";
		String APPOINTMENTREMINDER_TIME = "11:00:00";
		String APPOINTMENTREMINDER_MESSAGE = "TestMessage";
		Long APPOINTMENTREMINDER_ID = 123l;

		List<Reminder> reminders = new ArrayList<Reminder>();

		Profile profile = new Profile();
		profile.setAddressLine(ADDRESS_LINE1);
		profile.setEmailAddress(EMAIL_ADDRESS1);
		profile.setFirstName(FIRST_NAME1);
		profile.setLastName(LAST_NAME1);
		profile.setPhoneNumber(PHONE_NUMBER1);
		profile.setProfileId(PROFILE_ID1);
		profile.setZipCode(ZIP_CODE1);

		Car car = new Car();
		car.setCarId(carId);
		car.setColor(color);
		car.setModel(model);
		car.setYear(year);

		Reminder reminder = new AppointmentReminder();
		reminder.setDate(Date.valueOf(LocalDate.parse(APPOINTMENTREMINDER_DATE)));
		reminder.setMessage(APPOINTMENTREMINDER_MESSAGE);
		reminder.setReminderId(APPOINTMENTREMINDER_ID);
		reminder.setTime(Time.valueOf(LocalTime.parse(APPOINTMENTREMINDER_TIME)));

		reminders.add(reminder);

		Customer customer = null;

		try {
			customer = service.createCustomer(userId, password, reminders, car, profile);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(customer);
		assertEquals(userId, customer.getUserId());
		assertEquals(password, customer.getPassword());

		assertEquals(car, customer.getCar());
		assertEquals(profile, customer.getCustomerProfile());
		assertEquals(reminders, customer.getReminders());
	}

	@Test
	public void testCreateCustomerWithoutUserId() {

		String userId = null;
		String password = "charles";

		String FIRST_NAME1 = "Harry";
		String LAST_NAME1 = "Potter";
		String ADDRESS_LINE1 = "Hogwarts, London";
		String EMAIL_ADDRESS1 = "harry@potter";
		String PHONE_NUMBER1 = "8889995555";
		String ZIP_CODE1 = "H9P5F0";
		Long PROFILE_ID1 = 5653l;

		String model = "civic";
		String year = "2001";
		String color = "orange";
		Long carId = 1234l;

		String APPOINTMENTREMINDER_DATE = "2021-03-18";
		String APPOINTMENTREMINDER_TIME = "11:00:00";
		String APPOINTMENTREMINDER_MESSAGE = "TestMessage";
		Long APPOINTMENTREMINDER_ID = 123l;

		List<Reminder> reminders = new ArrayList<Reminder>();

		Profile profile = new Profile();
		profile.setAddressLine(ADDRESS_LINE1);
		profile.setEmailAddress(EMAIL_ADDRESS1);
		profile.setFirstName(FIRST_NAME1);
		profile.setLastName(LAST_NAME1);
		profile.setPhoneNumber(PHONE_NUMBER1);
		profile.setProfileId(PROFILE_ID1);
		profile.setZipCode(ZIP_CODE1);

		Car car = new Car();
		car.setCarId(carId);
		car.setColor(color);
		car.setModel(model);
		car.setYear(year);

		Reminder reminder = new AppointmentReminder();
		reminder.setDate(Date.valueOf(LocalDate.parse(APPOINTMENTREMINDER_DATE)));
		reminder.setMessage(APPOINTMENTREMINDER_MESSAGE);
		reminder.setReminderId(APPOINTMENTREMINDER_ID);
		reminder.setTime(Time.valueOf(LocalTime.parse(APPOINTMENTREMINDER_TIME)));

		reminders.add(reminder);

		Customer customer = null;

		String error = null;

		try {
			customer = service.createCustomer(userId, password, reminders, car, profile);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(customer);
		assertEquals(error, "userId can't be empty");
	}

	@Test
	public void testCreateCustomerWithoutPassword() {

		String userId = "Henry";
		String password = null;

		String FIRST_NAME1 = "Harry";
		String LAST_NAME1 = "Potter";
		String ADDRESS_LINE1 = "Hogwarts, London";
		String EMAIL_ADDRESS1 = "harry@potter";
		String PHONE_NUMBER1 = "8889995555";
		String ZIP_CODE1 = "H9P5F0";
		Long PROFILE_ID1 = 5653l;

		String model = "civic";
		String year = "2001";
		String color = "orange";
		Long carId = 1234l;

		String APPOINTMENTREMINDER_DATE = "2021-03-18";
		String APPOINTMENTREMINDER_TIME = "11:00:00";
		String APPOINTMENTREMINDER_MESSAGE = "TestMessage";
		Long APPOINTMENTREMINDER_ID = 123l;

		List<Reminder> reminders = new ArrayList<Reminder>();

		Profile profile = new Profile();
		profile.setAddressLine(ADDRESS_LINE1);
		profile.setEmailAddress(EMAIL_ADDRESS1);
		profile.setFirstName(FIRST_NAME1);
		profile.setLastName(LAST_NAME1);
		profile.setPhoneNumber(PHONE_NUMBER1);
		profile.setProfileId(PROFILE_ID1);
		profile.setZipCode(ZIP_CODE1);

		Car car = new Car();
		car.setCarId(carId);
		car.setColor(color);
		car.setModel(model);
		car.setYear(year);

		Reminder reminder = new AppointmentReminder();
		reminder.setDate(Date.valueOf(LocalDate.parse(APPOINTMENTREMINDER_DATE)));
		reminder.setMessage(APPOINTMENTREMINDER_MESSAGE);
		reminder.setReminderId(APPOINTMENTREMINDER_ID);
		reminder.setTime(Time.valueOf(LocalTime.parse(APPOINTMENTREMINDER_TIME)));

		reminders.add(reminder);

		Customer customer = null;

		String error = null;

		try {
			customer = service.createCustomer(userId, password, reminders, car, profile);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(customer);
		assertEquals(error, "password can't be empty");
	}

	@Test
	public void testCreateCustomerWithoutCar() {

		String userId = "Henry";
		String password = "charles";

		String FIRST_NAME1 = "Harry";
		String LAST_NAME1 = "Potter";
		String ADDRESS_LINE1 = "Hogwarts, London";
		String EMAIL_ADDRESS1 = "harry@potter";
		String PHONE_NUMBER1 = "8889995555";
		String ZIP_CODE1 = "H9P5F0";
		Long PROFILE_ID1 = 5653l;

		String APPOINTMENTREMINDER_DATE = "2021-03-18";
		String APPOINTMENTREMINDER_TIME = "11:00:00";
		String APPOINTMENTREMINDER_MESSAGE = "TestMessage";
		Long APPOINTMENTREMINDER_ID = 123l;

		List<Reminder> reminders = new ArrayList<Reminder>();

		Profile profile = new Profile();
		profile.setAddressLine(ADDRESS_LINE1);
		profile.setEmailAddress(EMAIL_ADDRESS1);
		profile.setFirstName(FIRST_NAME1);
		profile.setLastName(LAST_NAME1);
		profile.setPhoneNumber(PHONE_NUMBER1);
		profile.setProfileId(PROFILE_ID1);
		profile.setZipCode(ZIP_CODE1);

		Car car = null;

		Reminder reminder = new AppointmentReminder();
		reminder.setDate(Date.valueOf(LocalDate.parse(APPOINTMENTREMINDER_DATE)));
		reminder.setMessage(APPOINTMENTREMINDER_MESSAGE);
		reminder.setReminderId(APPOINTMENTREMINDER_ID);
		reminder.setTime(Time.valueOf(LocalTime.parse(APPOINTMENTREMINDER_TIME)));

		reminders.add(reminder);

		Customer customer = null;

		String error = null;

		try {
			customer = service.createCustomer(userId, password, reminders, car, profile);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(customer);
		assertEquals(error, "Car can't be empty");
	}

	@Test
	public void testCreateCustomerWithoutProfile() {

		String userId = "Henry";
		String password = "Charles";

		// String FIRST_NAME1 = "Harry";
		// String LAST_NAME1 = "Potter";
		// String ADDRESS_LINE1 = "Hogwarts, London";
		// String EMAIL_ADDRESS1 = "harry@potter";
		// String PHONE_NUMBER1 = "8889995555";
		// String ZIP_CODE1 = "H9P5F0";
		// Long PROFILE_ID1= 5653l;

		String model = "civic";
		String year = "2001";
		String color = "orange";
		Long carId = 1234l;

		String APPOINTMENTREMINDER_DATE = "2021-03-18";
		String APPOINTMENTREMINDER_TIME = "11:00:00";
		String APPOINTMENTREMINDER_MESSAGE = "TestMessage";
		Long APPOINTMENTREMINDER_ID = 123l;

		List<Reminder> reminders = new ArrayList<Reminder>();

		Profile profile = null;
		// profile.setAddressLine(ADDRESS_LINE1);
		// profile.setEmailAddress(EMAIL_ADDRESS1);
		// profile.setFirstName(FIRST_NAME1);
		// profile.setLastName(LAST_NAME1);
		// profile.setPhoneNumber(PHONE_NUMBER1);
		// profile.setProfileId(PROFILE_ID1);
		// profile.setZipCode(ZIP_CODE1);

		Car car = new Car();
		car.setCarId(carId);
		car.setColor(color);
		car.setModel(model);
		car.setYear(year);

		Reminder reminder = new AppointmentReminder();
		reminder.setDate(Date.valueOf(LocalDate.parse(APPOINTMENTREMINDER_DATE)));
		reminder.setMessage(APPOINTMENTREMINDER_MESSAGE);
		reminder.setReminderId(APPOINTMENTREMINDER_ID);
		reminder.setTime(Time.valueOf(LocalTime.parse(APPOINTMENTREMINDER_TIME)));

		reminders.add(reminder);

		Customer customer = null;

		String error = null;

		try {
			customer = service.createCustomer(userId, password, reminders, car, profile);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(customer);
		assertEquals(error, "Profile can't be empty");
	}

	@Test
	public void testCreateCustomerWithoutReminders() {

		String userId = "Henry";
		String password = "Charles";

		String FIRST_NAME1 = "Harry";
		String LAST_NAME1 = "Potter";
		String ADDRESS_LINE1 = "Hogwarts, London";
		String EMAIL_ADDRESS1 = "harry@potter";
		String PHONE_NUMBER1 = "8889995555";
		String ZIP_CODE1 = "H9P5F0";
		Long PROFILE_ID1 = 5653l;

		String model = "civic";
		String year = "2001";
		String color = "orange";
		Long carId = 1234l;

		List<Reminder> reminders = null;

		Profile profile = new Profile();
		profile.setAddressLine(ADDRESS_LINE1);
		profile.setEmailAddress(EMAIL_ADDRESS1);
		profile.setFirstName(FIRST_NAME1);
		profile.setLastName(LAST_NAME1);
		profile.setPhoneNumber(PHONE_NUMBER1);
		profile.setProfileId(PROFILE_ID1);
		profile.setZipCode(ZIP_CODE1);

		Car car = new Car();
		car.setCarId(carId);
		car.setColor(color);
		car.setModel(model);
		car.setYear(year);

		Customer customer = null;

		String error = null;

		try {
			customer = service.createCustomer(userId, password, reminders, car, profile);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(customer);
		assertEquals(error, "reminders can't be empty");
	}

	@Test
	public void testUpdateCustomerInfo() {

		String userId = "Henryyy";
		String password = "paul";

		String FIRST_NAME1 = "Ron";
		String LAST_NAME1 = "tatai";
		String ADDRESS_LINE1 = "Lyon, France";
		String EMAIL_ADDRESS1 = "lui@meme";
		String PHONE_NUMBER1 = "8889996666";
		String ZIP_CODE1 = "H9P5K7";
		Long PROFILE_ID1 = 5686l;

		String model = "toyo";
		String year = "2011";
		String color = "blue";
		Long carId = 1537l;

		String APPOINTMENTREMINDER_DATE = "2021-03-28";
		String APPOINTMENTREMINDER_TIME = "15:00:00";
		String APPOINTMENTREMINDER_MESSAGE = "hahaMessage";
		Long APPOINTMENTREMINDER_ID = 12345l;

		List<Reminder> reminders = new ArrayList<Reminder>();

		Profile profile = new Profile();
		profile.setAddressLine(ADDRESS_LINE1);
		profile.setEmailAddress(EMAIL_ADDRESS1);
		profile.setFirstName(FIRST_NAME1);
		profile.setLastName(LAST_NAME1);
		profile.setPhoneNumber(PHONE_NUMBER1);
		profile.setProfileId(PROFILE_ID1);
		profile.setZipCode(ZIP_CODE1);

		Car car = new Car();
		car.setCarId(carId);
		car.setColor(color);
		car.setModel(model);
		car.setYear(year);

		Reminder reminder = new AppointmentReminder();
		reminder.setDate(Date.valueOf(LocalDate.parse(APPOINTMENTREMINDER_DATE)));
		reminder.setMessage(APPOINTMENTREMINDER_MESSAGE);
		reminder.setReminderId(APPOINTMENTREMINDER_ID);
		reminder.setTime(Time.valueOf(LocalTime.parse(APPOINTMENTREMINDER_TIME)));

		reminders.add(reminder);

		Customer customer = service.createCustomer(userId, password, reminders, car, profile);
		Customer test = customerRepository.findCustomerByUserId("Henry");

		try {
			customer = service.editCustomer(customer, userId, password, reminders, car, profile);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(customer);
		assertEquals(userId, customer.getUserId());
		assertEquals(password, customer.getPassword());

		assertEquals(car, customer.getCar());
		assertEquals(profile, customer.getCustomerProfile());
		assertEquals(reminders, customer.getReminders());

		assertNotEquals(test.getCar(), customer.getCar());
		assertNotEquals(test.getCustomerProfile(), customer.getCustomerProfile());
		assertNotEquals(test.getId(), customer.getId());
		assertNotEquals(test.getPassword(), customer.getPassword());
		assertNotEquals(test.getReminders(), customer.getReminders());
	}

	@Test
	public void testUpdateCustomerInfoWithFakeCustomer() {

		String userId = "Henry";
		String password = "paul";

		String FIRST_NAME1 = "Ron";
		String LAST_NAME1 = "tatai";
		String ADDRESS_LINE1 = "Lyon, France";
		String EMAIL_ADDRESS1 = "lui@meme";
		String PHONE_NUMBER1 = "8889996666";
		String ZIP_CODE1 = "H9P5K7";
		Long PROFILE_ID1 = 5686l;

		String model = "toyo";
		String year = "2011";
		String color = "blue";
		Long carId = 1537l;

		String APPOINTMENTREMINDER_DATE = "2021-03-28";
		String APPOINTMENTREMINDER_TIME = "15:00:00";
		String APPOINTMENTREMINDER_MESSAGE = "hahaMessage";
		Long APPOINTMENTREMINDER_ID = 12345l;

		List<Reminder> reminders = new ArrayList<Reminder>();

		Profile profile = new Profile();
		profile.setAddressLine(ADDRESS_LINE1);
		profile.setEmailAddress(EMAIL_ADDRESS1);
		profile.setFirstName(FIRST_NAME1);
		profile.setLastName(LAST_NAME1);
		profile.setPhoneNumber(PHONE_NUMBER1);
		profile.setProfileId(PROFILE_ID1);
		profile.setZipCode(ZIP_CODE1);

		Car car = new Car();
		car.setCarId(carId);
		car.setColor(color);
		car.setModel(model);
		car.setYear(year);

		Reminder reminder = new AppointmentReminder();
		reminder.setDate(Date.valueOf(LocalDate.parse(APPOINTMENTREMINDER_DATE)));
		reminder.setMessage(APPOINTMENTREMINDER_MESSAGE);
		reminder.setReminderId(APPOINTMENTREMINDER_ID);
		reminder.setTime(Time.valueOf(LocalTime.parse(APPOINTMENTREMINDER_TIME)));

		reminders.add(reminder);

		Customer customer = null;

		String error = "";

		try {
			customer = service.editCustomer(customer, userId, password, reminders, car, profile);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(customer);
		assertEquals(error, "This customer does not exist");
	}

	// @Test
	// public void testUpdateCustomerInfoWithNoUserId() {
	//
	// String userId = null;
	// String password = "paul";
	//
	// String FIRST_NAME1 = "Ron";
	// String LAST_NAME1 = "tatai";
	// String ADDRESS_LINE1 = "Lyon, France";
	// String EMAIL_ADDRESS1 = "lui@meme";
	// String PHONE_NUMBER1 = "8889996666";
	// String ZIP_CODE1 = "H9P5K7";
	// Long PROFILE_ID1= 5686l;
	//
	// String model = "toyo";
	// String year = "2011";
	// String color = "blue";
	// Long carId = 1537l;
	//
	// String APPOINTMENTREMINDER_DATE = "2021-03-28";
	// String APPOINTMENTREMINDER_TIME = "15:00:00";
	// String APPOINTMENTREMINDER_MESSAGE = "hahaMessage";
	// Long APPOINTMENTREMINDER_ID = 12345l;
	//
	// List<Reminder> reminders = new ArrayList<Reminder>();
	//
	// Profile profile = new Profile();
	// profile.setAddressLine(ADDRESS_LINE1);
	// profile.setEmailAddress(EMAIL_ADDRESS1);
	// profile.setFirstName(FIRST_NAME1);
	// profile.setLastName(LAST_NAME1);
	// profile.setPhoneNumber(PHONE_NUMBER1);
	// profile.setProfileId(PROFILE_ID1);
	// profile.setZipCode(ZIP_CODE1);
	//
	// Car car = new Car();
	// car.setCarId(carId);
	// car.setColor(color);
	// car.setModel(model);
	// car.setYear(year);
	//
	// Reminder reminder = new AppointmentReminder();
	// reminder.setDate(Date.valueOf(LocalDate.parse(APPOINTMENTREMINDER_DATE)));
	// reminder.setMessage(APPOINTMENTREMINDER_MESSAGE);
	// reminder.setReminderId(APPOINTMENTREMINDER_ID);
	// reminder.setTime(Time.valueOf(LocalTime.parse(APPOINTMENTREMINDER_TIME)));
	//
	// reminders.add(reminder);
	//
	// Customer customer = service.createCustomer("ewhiu", password, reminders, car,
	// profile);
	//
	// String error = "";
	//
	// try {
	// customer = service.editCustomer(customer, userId, password, reminders, car,
	// profile);
	// }catch (IllegalArgumentException e){
	// fail();
	// }
	//
	// assertNull(customer);
	// assertEquals(error, "userId can't be empty");
	// }

	@Test
	public void testUpdateCustomerInfoWithNoPassword() {

		String userId = "tour";
		String password = null;

		String FIRST_NAME1 = "Ron";
		String LAST_NAME1 = "tatai";
		String ADDRESS_LINE1 = "Lyon, France";
		String EMAIL_ADDRESS1 = "lui@meme";
		String PHONE_NUMBER1 = "8889996666";
		String ZIP_CODE1 = "H9P5K7";
		Long PROFILE_ID1 = 5686l;

		String model = "toyo";
		String year = "2011";
		String color = "blue";
		Long carId = 1537l;

		String APPOINTMENTREMINDER_DATE = "2021-03-28";
		String APPOINTMENTREMINDER_TIME = "15:00:00";
		String APPOINTMENTREMINDER_MESSAGE = "hahaMessage";
		Long APPOINTMENTREMINDER_ID = 12345l;

		List<Reminder> reminders = new ArrayList<Reminder>();

		Profile profile = new Profile();
		profile.setAddressLine(ADDRESS_LINE1);
		profile.setEmailAddress(EMAIL_ADDRESS1);
		profile.setFirstName(FIRST_NAME1);
		profile.setLastName(LAST_NAME1);
		profile.setPhoneNumber(PHONE_NUMBER1);
		profile.setProfileId(PROFILE_ID1);
		profile.setZipCode(ZIP_CODE1);

		Car car = new Car();
		car.setCarId(carId);
		car.setColor(color);
		car.setModel(model);
		car.setYear(year);

		Reminder reminder = new AppointmentReminder();
		reminder.setDate(Date.valueOf(LocalDate.parse(APPOINTMENTREMINDER_DATE)));
		reminder.setMessage(APPOINTMENTREMINDER_MESSAGE);
		reminder.setReminderId(APPOINTMENTREMINDER_ID);
		reminder.setTime(Time.valueOf(LocalTime.parse(APPOINTMENTREMINDER_TIME)));

		reminders.add(reminder);

		Customer customer = service.createCustomer(userId, "pass", reminders, car, profile);
		Customer test = customerRepository.findCustomerByUserId("Henry");

		try {
			customer = service.editCustomer(customer, userId, password, reminders, car, profile);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(customer);
		assertEquals(userId, customer.getUserId());
		assertEquals(password, customer.getPassword());

		assertEquals(car, customer.getCar());
		assertEquals(profile, customer.getCustomerProfile());
		assertEquals(reminders, customer.getReminders());

		assertNotEquals(test.getCar(), customer.getCar());
		assertNotEquals(test.getCustomerProfile(), customer.getCustomerProfile());
		assertNotEquals(test.getId(), customer.getId());
		// assertNotEquals(test.getPassword(), customer.getPassword());
		assertNotEquals(test.getReminders().get(0), customer.getReminders().get((0)));

	}

	@Test
	public void testUpdateCustomerInfoWithNoCar() {

		String userId = "berr";
		String password = "charlsres";

		String FIRST_NAME1 = "Ron";
		String LAST_NAME1 = "tatai";
		String ADDRESS_LINE1 = "Lyon, France";
		String EMAIL_ADDRESS1 = "lui@meme";
		String PHONE_NUMBER1 = "8889996666";
		String ZIP_CODE1 = "H9P5K7";
		Long PROFILE_ID1 = 5686l;

		String model = "toyo";
		String year = "2011";
		String color = "blue";
		Long carId = 1537l;

		String APPOINTMENTREMINDER_DATE = "2021-03-28";
		String APPOINTMENTREMINDER_TIME = "15:00:00";
		String APPOINTMENTREMINDER_MESSAGE = "hahaMessage";
		Long APPOINTMENTREMINDER_ID = 12345l;

		List<Reminder> reminders = new ArrayList<Reminder>();

		Profile profile = new Profile();
		profile.setAddressLine(ADDRESS_LINE1);
		profile.setEmailAddress(EMAIL_ADDRESS1);
		profile.setFirstName(FIRST_NAME1);
		profile.setLastName(LAST_NAME1);
		profile.setPhoneNumber(PHONE_NUMBER1);
		profile.setProfileId(PROFILE_ID1);
		profile.setZipCode(ZIP_CODE1);

		Car car1 = null;
		Car car = new Car();
		car.setCarId(carId);
		car.setColor(color);
		car.setModel(model);
		car.setYear(year);

		Reminder reminder = new AppointmentReminder();
		reminder.setDate(Date.valueOf(LocalDate.parse(APPOINTMENTREMINDER_DATE)));
		reminder.setMessage(APPOINTMENTREMINDER_MESSAGE);
		reminder.setReminderId(APPOINTMENTREMINDER_ID);
		reminder.setTime(Time.valueOf(LocalTime.parse(APPOINTMENTREMINDER_TIME)));

		reminders.add(reminder);

		Customer customer = service.createCustomer(userId, password, reminders, car, profile);
		Customer test = customerRepository.findCustomerByUserId("Henry");

		try {
			customer = service.editCustomer(customer, userId, password, reminders, car1, profile);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(customer);
		assertEquals(userId, customer.getUserId());
		assertEquals(password, customer.getPassword());

		// assertEquals(car, customer.getCar());
		assertEquals(profile, customer.getCustomerProfile());
		assertEquals(reminders, customer.getReminders());

		// assertNotEquals(test.getCar(), customer.getCar());
		assertNotEquals(test.getCustomerProfile(), customer.getCustomerProfile());
		assertNotEquals(test.getId(), customer.getId());
		assertNotEquals(test.getPassword(), customer.getPassword());
		assertNotEquals(test.getReminders().get(0), customer.getReminders().get(0));

	}

	@Test
	public void testUpdateCustomerInfoWithNoProfile() {

		String userId = "bist";
		String password = "charewles";

		String FIRST_NAME1 = "Ron";
		String LAST_NAME1 = "tatai";
		String ADDRESS_LINE1 = "Lyon, France";
		String EMAIL_ADDRESS1 = "lui@meme";
		String PHONE_NUMBER1 = "8889996666";
		String ZIP_CODE1 = "H9P5K7";
		Long PROFILE_ID1 = 5686l;

		String model = "toyo";
		String year = "2011";
		String color = "blue";
		Long carId = 1537l;

		String APPOINTMENTREMINDER_DATE = "2021-03-28";
		String APPOINTMENTREMINDER_TIME = "15:00:00";
		String APPOINTMENTREMINDER_MESSAGE = "hahaMessage";
		Long APPOINTMENTREMINDER_ID = 12345l;

		List<Reminder> reminders = new ArrayList<Reminder>();

		Profile profile1 = null;
		Profile profile = new Profile();
		profile.setAddressLine(ADDRESS_LINE1);
		profile.setEmailAddress(EMAIL_ADDRESS1);
		profile.setFirstName(FIRST_NAME1);
		profile.setLastName(LAST_NAME1);
		profile.setPhoneNumber(PHONE_NUMBER1);
		profile.setProfileId(PROFILE_ID1);
		profile.setZipCode(ZIP_CODE1);

		Car car = new Car();
		car.setCarId(carId);
		car.setColor(color);
		car.setModel(model);
		car.setYear(year);

		Reminder reminder = new AppointmentReminder();
		reminder.setDate(Date.valueOf(LocalDate.parse(APPOINTMENTREMINDER_DATE)));
		reminder.setMessage(APPOINTMENTREMINDER_MESSAGE);
		reminder.setReminderId(APPOINTMENTREMINDER_ID);
		reminder.setTime(Time.valueOf(LocalTime.parse(APPOINTMENTREMINDER_TIME)));

		reminders.add(reminder);

		Customer customer = service.createCustomer(userId, password, reminders, car, profile);
		Customer test = customerRepository.findCustomerByUserId("Henry");

		try {
			customer = service.editCustomer(customer, userId, password, reminders, car, profile1);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(customer);
		assertEquals(userId, customer.getUserId());
		assertEquals(password, customer.getPassword());

		assertEquals(car, customer.getCar());
		// assertEquals(profile, customer.getCustomerProfile());
		assertEquals(reminders, customer.getReminders());

		assertNotEquals(test.getCar(), customer.getCar());
		// assertNotEquals(test.getCustomerProfile(), customer.getCustomerProfile());
		assertNotEquals(test.getId(), customer.getId());
		assertNotEquals(test.getPassword(), customer.getPassword());
		assertNotEquals(test.getReminders().get(0), customer.getReminders().get(0));

	}

	@Test
	public void testUpdateCustomerInfoWithNoReminder() {

		String userId = "jcob";
		String password = "charlmes";

		String FIRST_NAME1 = "Ron";
		String LAST_NAME1 = "tatai";
		String ADDRESS_LINE1 = "Lyon, France";
		String EMAIL_ADDRESS1 = "lui@meme";
		String PHONE_NUMBER1 = "8889996666";
		String ZIP_CODE1 = "H9P5K7";
		Long PROFILE_ID1 = 5686l;

		String model = "toyo";
		String year = "2011";
		String color = "blue";
		Long carId = 1537l;

		String APPOINTMENTREMINDER_DATE = "2021-03-28";
		String APPOINTMENTREMINDER_TIME = "15:00:00";
		String APPOINTMENTREMINDER_MESSAGE = "hahaMessage";
		Long APPOINTMENTREMINDER_ID = 12345l;

		List<Reminder> reminders1 = null;
		List<Reminder> reminders = new ArrayList<Reminder>();

		Profile profile = new Profile();
		profile.setAddressLine(ADDRESS_LINE1);
		profile.setEmailAddress(EMAIL_ADDRESS1);
		profile.setFirstName(FIRST_NAME1);
		profile.setLastName(LAST_NAME1);
		profile.setPhoneNumber(PHONE_NUMBER1);
		profile.setProfileId(PROFILE_ID1);
		profile.setZipCode(ZIP_CODE1);

		Car car = new Car();
		car.setCarId(carId);
		car.setColor(color);
		car.setModel(model);
		car.setYear(year);

		Reminder reminder = new AppointmentReminder();
		reminder.setDate(Date.valueOf(LocalDate.parse(APPOINTMENTREMINDER_DATE)));
		reminder.setMessage(APPOINTMENTREMINDER_MESSAGE);
		reminder.setReminderId(APPOINTMENTREMINDER_ID);
		reminder.setTime(Time.valueOf(LocalTime.parse(APPOINTMENTREMINDER_TIME)));

		reminders.add(reminder);

		Customer customer = service.createCustomer(userId, password, reminders, car, profile);
		Customer test = customerRepository.findCustomerByUserId("Henry");

		try {
			customer = service.editCustomer(customer, userId, password, reminders1, car, profile);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(customer);
		assertEquals(userId, customer.getUserId());
		assertEquals(password, customer.getPassword());

		assertEquals(car, customer.getCar());
		assertEquals(profile, customer.getCustomerProfile());
		// assertEquals(reminders, customer.getReminders());

		assertNotEquals(test.getCar(), customer.getCar());
		assertNotEquals(test.getCustomerProfile(), customer.getCustomerProfile());
		assertNotEquals(test.getId(), customer.getId());
		assertNotEquals(test.getPassword(), customer.getPassword());
		// assertNotEquals(test.getReminders(), customer.getReminders());
	}
}
