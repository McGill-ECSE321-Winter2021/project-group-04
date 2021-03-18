package ca.mcgill.ecse321.projectgroup04.service;


import static org.mockito.Mockito.lenient;


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
	private AutoRepairShopSystemService service;

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

						return fieldTechnician;
					}

					if (invocation.getArgument(0).equals(FIELD_TECHNICIAN_ID2)) {
						FieldTechnician fieldTechnician = new FieldTechnician();
						fieldTechnician.setName(FIELD_TECHNICIAN_NAME2);
						fieldTechnician.setTechnicianId(FIELD_TECHNICIAN_ID2);

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

		assertNull(emergencyService);
		assertEquals(error, "Emergency Service with this name already exists");

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
	 public void TestEditNameEmergencyService() {
		 String name = "EmergencyService1";
		 String newName = "EmergencyNewName";
	        
	     int price = 50;
	     
	     EmergencyService emergencyService = new EmergencyService();
	     emergencyService.setName(name);
	     emergencyService.setPrice(price);
	     try {
	    	 emergencyService = service.editEmergencyService(emergencyService, newName, price);
	     } catch (IllegalArgumentException e) {
	    	 fail();
	     }

	     assertNotNull(emergencyService);
	     assertEquals(newName, emergencyService.getName());
	     assertEquals(price, emergencyService.getPrice());
	 }
	 
	 @Test
	 public void TestEditExistingNameEmergencyService() {
		 String name = "EmergencyTestNameOne";
		 String newName = "EmergencyService1";
	     
	     int price = 50;
	     String error = null;
	     
	     EmergencyService emergencyService = new EmergencyService();
	     emergencyService.setName(name);
	     emergencyService.setPrice(price);
	     try {
	    	 emergencyService = service.editEmergencyService(emergencyService, newName, price);
	        } catch (IllegalArgumentException e) {
	          error = e.getMessage();
	        }

	        assertNotNull(emergencyService);
	        assertEquals(name, emergencyService.getName());
	        assertEquals(price, emergencyService.getPrice());
	        assertEquals(error, "An emergency service with this name already exists");
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
	    	 emergencyService = service.editEmergencyService(emergencyService, name, newPrice);
	     } catch (IllegalArgumentException e) {
	    	 fail();
	     }

	     assertNotNull(emergencyService);
	     assertEquals(name, emergencyService.getName());
	     assertEquals(newPrice, emergencyService.getPrice());
	 }
	 	 
	 //TESTS FOR EMERGENCY SERVICE BOOKINGS ----------------------------------
	
	 @Test
	 public void TestBookEmergencyService() {
		 String serviceName = "EmergencyService1";
		 
		 String userId = "UserTestId";
		 
		 String bookingName = serviceName + " for " + userId;
		 
		 Long fieldTechnicianId = (long) 55999;
		 FieldTechnician fieldTechnician = service.getFieldTechnicianById(fieldTechnicianId);
		 
		 EmergencyService emergencyService = service.getEmergencyServiceByServiceName(serviceName);
		 
		 int price = emergencyService.getPrice();
		 
		 EmergencyService booking = null;
		 
		 
		 
		 try {
			 booking = service.bookEmergencyService(bookingName, price, "Montreal", userId, 
					fieldTechnician);
		 } catch (IllegalArgumentException e) {
			 System.out.println(e);
			 fail();
		 }
		 
		 assertNotNull(booking);
		 assertEquals(bookingName, booking.getName());
		 assertEquals(userId, booking.getCustomer().getUserId());
		 assertEquals(booking.getReceipt().getTotalPrice(), booking.getPrice());
		 assertEquals(fieldTechnician.getName(), booking.getTechnician().getName());
		 
	 }
	 
	 @Test
	 public void TestBookEmergencyServiceNoCustomer() {
		 String serviceName = "EmergencyService1";
		 
		 String userId = "wrongUserId";
		 
		 String bookingName = serviceName + " for " + userId;
		 
		 Long fieldTechnicianId = (long) 51999;
		 FieldTechnician fieldTechnician = service.getFieldTechnicianById(fieldTechnicianId);
		 
		 EmergencyService emergencyService = service.getEmergencyServiceByServiceName(serviceName);
		 
		 int price = emergencyService.getPrice();
		 
		 EmergencyService booking = null;
		 String error = null;
		 
		 
		 try {
			 booking = service.bookEmergencyService(bookingName, price, "Montreal", userId,
					fieldTechnician);
		 } catch (IllegalArgumentException e) {
			 error = e.getMessage();
		 }
		 
		 //System.out.println(fieldTechnician.getName());
		 //System.out.println(fieldTechnician.getIsAvailable());
		 
		 assertNull(booking);
		 assertEquals(error, "No customer with such userId");
		 
		 
	 }
	
	
}
