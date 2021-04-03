package ca.mcgill.ecse321.projectgroup04.service;

import static org.mockito.Mockito.lenient;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup04.dao.CustomerRepository;
import ca.mcgill.ecse321.projectgroup04.dao.EmergencyServiceRepository;
import ca.mcgill.ecse321.projectgroup04.dao.FieldTechnicianRepository;
import ca.mcgill.ecse321.projectgroup04.dao.ReceiptRepository;

import ca.mcgill.ecse321.projectgroup04.model.Customer;
import ca.mcgill.ecse321.projectgroup04.model.EmergencyService;
import ca.mcgill.ecse321.projectgroup04.model.FieldTechnician;

import ca.mcgill.ecse321.projectgroup04.model.Receipt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class TestEmergencyServiceService {

	@Mock
	private EmergencyServiceRepository emergencyServiceRepository;

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private ReceiptRepository receiptRepository;

	@Mock
	private FieldTechnicianRepository fieldTechnicianRepository;

	@InjectMocks
	private EmergencyServiceService service;

	@InjectMocks
	private FieldTechnicianService fieldTechservice;

	private static final String CUSTOMER_USERID = "UserTestId";
	private static final String CUSTOMER_PASSWORD = "PasswordTest";

	private static final String SERVICE_NAME = "EmergencyService1";
	private static final int SERVICE_PRICE = 50;
	private static final String SERVICE_LOCATION = "Montreal";

	private static final String SERVICE_NAME2 = "EmergencyService2";
	private static final int SERVICE_PRICE2 = 75;

	private static final Long FIELD_TECHNICIAN_ID1 = (long) 55999;
	private static final String FIELD_TECHNICIAN_NAME1 = "Borat";

	private static final Long FIELD_TECHNICIAN_ID2 = (long) 51999;
	private static final String FIELD_TECHNICIAN_NAME2 = "Bieber";

	private static final Long EMERGENCYSERVICE_BOOKING_TO_DELETE_ID = (long) 45321;

	@BeforeEach
	public void setMockOutput() {
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(emergencyServiceRepository.save(any(EmergencyService.class)))
				.thenAnswer(returnParameterAsAnswer);
		lenient().when(customerRepository.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(fieldTechnicianRepository.save(any(FieldTechnician.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(receiptRepository.save(any(Receipt.class))).thenAnswer(returnParameterAsAnswer);

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

		lenient().when(emergencyServiceRepository.findEmergencyServiceByName(anyString()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(SERVICE_NAME)) {
						EmergencyService emergencyServiceBookings = new EmergencyService();

						emergencyServiceBookings.setName(SERVICE_NAME);
						emergencyServiceBookings.setPrice(SERVICE_PRICE);

						return emergencyServiceBookings;
					}
					if (invocation.getArgument(0).equals(SERVICE_NAME2)) {
						EmergencyService emergencyServiceBookings = new EmergencyService();

						emergencyServiceBookings.setName(SERVICE_NAME2);
						emergencyServiceBookings.setPrice(SERVICE_PRICE2);

						return emergencyServiceBookings;
					}
					return null;
				});

		lenient().when(fieldTechnicianRepository.findFieldTechnicianByTechnicianId(anyLong()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(FIELD_TECHNICIAN_ID1)) {
						FieldTechnician fieldTechnician = new FieldTechnician();
						fieldTechnician.setName(FIELD_TECHNICIAN_NAME1);
						fieldTechnician.setTechnicianId(FIELD_TECHNICIAN_ID1);

						// System.out.println(fieldTechnician.getIsAvailable());

						return fieldTechnician;
					}

					if (invocation.getArgument(0).equals(FIELD_TECHNICIAN_ID2)) {
						FieldTechnician fieldTechnician = new FieldTechnician();
						fieldTechnician.setName(FIELD_TECHNICIAN_NAME2);
						fieldTechnician.setTechnicianId(FIELD_TECHNICIAN_ID2);

						// System.out.println(fieldTechnician.getIsAvailable());

						return fieldTechnician;
					}

					return null;
				});

		lenient().when(emergencyServiceRepository.findEmergencyServiceByServiceId(anyLong()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(EMERGENCYSERVICE_BOOKING_TO_DELETE_ID)) {

						EmergencyService emergencyServiceBooking = new EmergencyService();
						emergencyServiceBooking.setServiceId(EMERGENCYSERVICE_BOOKING_TO_DELETE_ID);

						Customer customer = new Customer();
						customer.setPassword(CUSTOMER_PASSWORD);
						customer.setUserId(CUSTOMER_USERID);

						EmergencyService emergencyService = new EmergencyService();

						emergencyService.setName(SERVICE_NAME);
						emergencyService.setPrice(SERVICE_PRICE);

						FieldTechnician fieldTechnician = new FieldTechnician();
						fieldTechnician.setName(FIELD_TECHNICIAN_NAME1);
						fieldTechnician.setTechnicianId(FIELD_TECHNICIAN_ID1);

						Receipt receipt = new Receipt();
						receipt.setTotalPrice(emergencyService.getPrice());

						String bookingName = emergencyService.getName() + " for " + CUSTOMER_USERID;

						emergencyServiceBooking.setName(bookingName);
						emergencyServiceBooking.setPrice(emergencyService.getPrice());
						emergencyServiceBooking.setLocation(SERVICE_LOCATION);

						emergencyServiceBooking.setCustomer(customer);
						emergencyServiceBooking.setReceipt(receipt);
						emergencyServiceBooking.setTechnician(fieldTechnician);

						return emergencyServiceBooking;
					}
					return null;
				});

	}

	// TESTS FOR EMERGENCY SERVICES ---------------------------------------

	@Test
	public void TestCreateEmergencyService() {

		String name = "emergencyTestname";
		int price = 50;

		EmergencyService emergencyService = null;
		try {
			emergencyService = service.createEmergencyService(name, price);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(emergencyService);
		assertEquals(name, emergencyService.getName());
		assertEquals(price, emergencyService.getPrice());

	}

	@Test
	public void TestCreateEmergencyServiceNoName() {
		String name = "";
		int price = 50;

		String error = null;

		EmergencyService emergencyService = null;
		try {
			emergencyService = service.createEmergencyService(name, price);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(emergencyService);
		assertEquals(error, "Name can't be null");

	}

	@Test
	public void TestEmergencyEmergencyServiceNoPrice() {
		String name = "emergencyTestNamePrice";

		int price = 0;

		String error = null;

		EmergencyService emergencyService = null;
		try {
			emergencyService = service.createEmergencyService(name, price);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(emergencyService);
		assertEquals(error, "Price can't be 0");

	}

	@Test
	public void TestCreateEmergencyServiceAlreadyExists() {
		String name = "EmergencyService1";
		int price = 50;

		String error = null;

		EmergencyService emergencyService = null;
		try {
			emergencyService = service.createEmergencyService(name, price);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNotNull(emergencyService);
		assertEquals(error, null);

	}

	@Test
	public void TestDeleteEmergencyService() {
		String name = "EmergencyService1";

		int price = 50;

		EmergencyService emergencyService = new EmergencyService();
		emergencyService.setName(name);

		emergencyService.setPrice(price);
		try {
			emergencyService = service.deleteEmergencyService(emergencyService);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNull(emergencyService);

	}


	@Test
	public void TestEditPriceEmergencyService() {
		String name = "EmergencyTestNameEditPrice";
		int newPrice = 40;
		int price = 50;

		EmergencyService emergencyService = new EmergencyService();
		emergencyService.setName(name);
		emergencyService.setPrice(price);
		try {
			emergencyService = service.editEmergencyService(emergencyService, newPrice);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(emergencyService);
		assertEquals(name, emergencyService.getName());
		assertEquals(newPrice, emergencyService.getPrice());
	}

	// TESTS FOR EMERGENCY SERVICE BOOKINGS ----------------------------------

	@Test
	public void TestBookEmergencyService() {
		String serviceName = "EmergencyService1";

		String userId = "UserTestId";

		String bookingName = serviceName + " for " + userId;

		Long fieldTechnicianId = (long) 55999;
		FieldTechnician fieldTechnician = fieldTechservice.getFieldTechnicianById(fieldTechnicianId);

		EmergencyService booking = null;
		String error = null;

		try {
			booking = service.bookEmergencyService(bookingName, serviceName, "Montreal", userId, fieldTechnician);
		} catch (IllegalArgumentException e) {
			//System.out.println(e);
			error = e.getMessage();
		}

		assertEquals(error, "No Emergency Service with such name!");
		assertNull(booking);
		

	}

	@Test
	public void TestBookEmergencyServiceNoCustomer() {
		String serviceName = "EmergencyService1";

		String userId = "wrongUserId";

		String bookingName = serviceName + " for " + userId;

		Long fieldTechnicianId = (long) 55999;
		FieldTechnician fieldTechnician = fieldTechservice.getFieldTechnicianById(fieldTechnicianId);

		EmergencyService booking = null;
		String error = null;

		try {
			booking = service.bookEmergencyService(bookingName, serviceName, "Montreal", userId, fieldTechnician);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// System.out.println(fieldTechnician.getName());
		// System.out.println(fieldTechnician.getIsAvailable());

		assertNull(booking);
		assertEquals(error, "No Emergency Service with such name!");

	}

	@Test
	public void TestBookEmergencyServiceNoService() {
		String serviceName = "wrongService1";

		String userId = "UserTestId";

		String bookingName = serviceName + " for " + userId;

		Long fieldTechnicianId = (long) 55999;
		FieldTechnician fieldTechnician = fieldTechservice.getFieldTechnicianById(fieldTechnicianId);

		EmergencyService booking = null;
		String error = null;

		try {
			booking = service.bookEmergencyService(bookingName, serviceName, "Montreal", userId, fieldTechnician);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// System.out.println(fieldTechnician.getName());
		// System.out.println(fieldTechnician.getIsAvailable());

		assertNull(booking);
		assertEquals(error, "No Emergency Service with such name!");

	}

	@Test
	public void TestBookEmergencyServiceUnavailableFieldTechnician() {
		String serviceName1 = "EmergencyService1";
		String userId = "UserTestId";
		String bookingName1 = serviceName1 + " for " + userId;

		String serviceName2 = "EmergencyService2";
		String bookingName2 = serviceName2 + " for " + userId;

		Long fieldTechnicianId = (long) 55999;
		FieldTechnician fieldTechnician = fieldTechservice.getFieldTechnicianById(fieldTechnicianId);

		String error1 = null;
		EmergencyService booking1 = null;
		try {
			booking1 = service.bookEmergencyService(bookingName1, serviceName1, "Montreal", userId,
				fieldTechnician);
		} catch (IllegalArgumentException e) {
			error1 = e.getMessage();
		}

		String error = null;
		EmergencyService booking2 = null;

		try {
			booking2 = service.bookEmergencyService(bookingName2, serviceName2, "Montreal", userId, fieldTechnician);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// System.out.println(fieldTechnician.getName());
		// System.out.println(fieldTechnician.getIsAvailable());

		assertNull(booking2);
		//assertFalse(booking1.getTechnician().getIsAvailable());
		assertEquals(error, "No Emergency Service with such name!");

	}

	@Test
	public void TestGetEmergencyService() {
		EmergencyService booking = null;

		try {
			booking = service.getEmergencyServiceByServiceId((long) 45321);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(booking);
		assertNotNull(booking.getCustomer());
		assertNotNull(booking.getReceipt());
		assertNotNull(booking.getTechnician());
		assertEquals(CUSTOMER_USERID, booking.getCustomer().getUserId());
		assertEquals(FIELD_TECHNICIAN_ID1, booking.getTechnician().getTechnicianId());
		assertEquals(FIELD_TECHNICIAN_NAME1, booking.getTechnician().getName());
		assertEquals(SERVICE_NAME + " for " + booking.getCustomer().getUserId(), booking.getName());
		assertEquals(SERVICE_PRICE, booking.getPrice());
		assertEquals(CUSTOMER_PASSWORD, booking.getCustomer().getPassword());
		assertEquals(SERVICE_PRICE, booking.getReceipt().getTotalPrice());
		assertEquals("Montreal", booking.getLocation());

	}

	@Test
	public void TestGetAppointmentInvalidId() {
		EmergencyService booking = null;
		String error = null;
		try {
			booking = service.getEmergencyServiceByServiceId(321l);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(booking);
		assertEquals(error, "No Emergency Service with such ID exist!");

	}

	@Test
	public void TestEndEmergencyService() {
		EmergencyService booking = new EmergencyService();
		booking.setServiceId(EMERGENCYSERVICE_BOOKING_TO_DELETE_ID);

		Customer customer = new Customer();
		customer.setPassword(CUSTOMER_PASSWORD);
		customer.setUserId(CUSTOMER_USERID);

		EmergencyService emergencyService = new EmergencyService();

		emergencyService.setName(SERVICE_NAME);
		emergencyService.setPrice(SERVICE_PRICE);

		FieldTechnician fieldTechnician = new FieldTechnician();
		fieldTechnician.setName(FIELD_TECHNICIAN_NAME1);
		fieldTechnician.setTechnicianId(FIELD_TECHNICIAN_ID1);

		Receipt receipt = new Receipt();
		receipt.setTotalPrice(SERVICE_PRICE);

		booking.setCustomer(customer);
		booking.setName(emergencyService.getName() + " for " + customer.getUserId());
		booking.setReceipt(receipt);
		booking.setLocation(SERVICE_LOCATION);
		booking.setTechnician(fieldTechnician);

		try {
			booking = service.endEmergencyService(booking);
		} catch (IllegalArgumentException e) {
			// System.out.println(e.getMessage());
			fail();
		}

		assertNull(booking.getTechnician());

	}

}
