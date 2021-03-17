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

import ca.mcgill.ecse321.projectgroup04.service.AutoRepairShopSystemService;
import ca.mcgill.ecse321.projectgroup04.dao.AppointmentReminderRepository;
import ca.mcgill.ecse321.projectgroup04.dao.AppointmentRepository;
import ca.mcgill.ecse321.projectgroup04.dao.BookableServiceRepository;
import ca.mcgill.ecse321.projectgroup04.dao.CustomerRepository;
import ca.mcgill.ecse321.projectgroup04.dao.GarageTechnicianRepository;
import ca.mcgill.ecse321.projectgroup04.dao.ReceiptRepository;
import ca.mcgill.ecse321.projectgroup04.dao.TimeSlotRepository;
import ca.mcgill.ecse321.projectgroup04.model.Appointment;
import ca.mcgill.ecse321.projectgroup04.model.AppointmentReminder;
import ca.mcgill.ecse321.projectgroup04.model.BookableService;
import ca.mcgill.ecse321.projectgroup04.model.Customer;
import ca.mcgill.ecse321.projectgroup04.model.GarageTechnician;
import ca.mcgill.ecse321.projectgroup04.model.Receipt;
import ca.mcgill.ecse321.projectgroup04.model.Service;
import ca.mcgill.ecse321.projectgroup04.model.TimeSlot;

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
	
	@InjectMocks
	private AutoRepairShopSystemService service;
	
	private static final String CUSTOMER_USERID = "TestUserId";
	private static final String CUSTOMER_PASSWORD ="TestPassword0";
	
	
	private static final String SERVICE_NAME="ServiceName1";
	private static final int SERVICE_PRICE=50;
	private static final int SERVICE_DURATION=30;
	
	private static final String SERVICE_NAME2="ServiceName2";
	private static final int SERVICE_PRICE2=75;
	private static final int SERVICE_DURATION2=45;
	
	private static final String TODAY_DATE ="2021-03-15";
	private static final String OLD_APPOINTMENT_DATE = "2021-03-18";
	private static final Integer OLD_APPOINTMENT_GARAGE_SPOT = 1;
	private static final String OLD_APPOINTMENT_START_TIME = "13:00:00";
	private static final String OLD_APPOINTMENT_END_TIME = "13:30:00";
	
	private static final Long GARAGE_TECHNICIAN_ID = 4587l;
	private static final String GARAGE_TECHNICIAN_NAME="Harry Potter";
	
	private static final Long APPOINTMENT_TO_DELETE_ID=3423l;



	
	@BeforeEach
	public void setMockOutput() {
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(appointmentRepository.save(any(Appointment.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(timeSlotRepository.save(any(TimeSlot.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(customerRepository.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(bookableServiceRepository.save(any(BookableService.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(garageTechnicianRepository.save(any(GarageTechnician.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(receiptRepository.save(any(Receipt.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(appointmentReminderRepository.save(any(AppointmentReminder.class))).thenAnswer(returnParameterAsAnswer);
		
		lenient().when(customerRepository.findCustomerByUserId(anyString())).thenAnswer((InvocationOnMock invocation)->{
			if(invocation.getArgument(0).equals(CUSTOMER_USERID)) {
				Customer customer = new Customer();
				customer.setPassword(CUSTOMER_PASSWORD);
				customer.setUserId(CUSTOMER_USERID);
				
				return customer;
			}
			else {
				return null;
			}
		});
		lenient().when(bookableServiceRepository.findBookableServiceByName(anyString())).thenAnswer((InvocationOnMock invocation)->{
			if(invocation.getArgument(0).equals(SERVICE_NAME)) {
				BookableService bookableService =new BookableService();
				bookableService.setDuration(SERVICE_DURATION);
				bookableService.setName(SERVICE_NAME);
				bookableService.setPrice(SERVICE_PRICE);
				
				return bookableService;
			}
			if(invocation.getArgument(0).equals(SERVICE_NAME2)) {
				BookableService bookableService =new BookableService();
				bookableService.setDuration(SERVICE_DURATION2);
				bookableService.setName(SERVICE_NAME2);
				bookableService.setPrice(SERVICE_PRICE2);
				
				return bookableService;
			}
			return null;
		});
		lenient().when(appointmentRepository.findAll()).thenAnswer((InvocationOnMock invocation)->{
			List<Appointment> appointments = new ArrayList<>();
			TimeSlot timeSlot = new TimeSlot();
			timeSlot.setEndTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_END_TIME)));
			timeSlot.setStartTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_START_TIME)));
			timeSlot.setEndDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE)));
			timeSlot.setStartDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE)));
			timeSlot.setGarageSpot(OLD_APPOINTMENT_GARAGE_SPOT);
			
			Appointment appointment =new Appointment();
			appointment.setTimeSlot(timeSlot);
			
			appointments.add(appointment);

			return appointments;
		});
		lenient().when(garageTechnicianRepository.findGarageTechnicianByTechnicianId(anyLong())).thenAnswer((InvocationOnMock invocation)->{
			if(invocation.getArgument(0).equals(GARAGE_TECHNICIAN_ID)) {
				GarageTechnician garageTechnician = new GarageTechnician();
				garageTechnician.setName(GARAGE_TECHNICIAN_NAME);
				garageTechnician.setTechnicianId(GARAGE_TECHNICIAN_ID);
				
				return garageTechnician;
			}
			return null;
		});
		
		lenient().when(appointmentRepository.findById(anyLong())).thenAnswer((InvocationOnMock invocation)->{
			if(invocation.getArgument(0).equals(APPOINTMENT_TO_DELETE_ID)) {
				
				Appointment appointment = new Appointment();
				appointment.setAppointmentId(APPOINTMENT_TO_DELETE_ID);
				
				Customer customer= new Customer();
				customer.setPassword(CUSTOMER_PASSWORD);
				customer.setUserId(CUSTOMER_USERID);
				
				BookableService bookableService = new BookableService();
				bookableService.setDuration(SERVICE_DURATION);
				bookableService.setName(SERVICE_NAME);
				bookableService.setPrice(SERVICE_PRICE);
				
				GarageTechnician garageTechnician = new GarageTechnician();
				garageTechnician.setName(GARAGE_TECHNICIAN_NAME);
				
				Receipt receipt = new Receipt();
				receipt.setTotalPrice(SERVICE_PRICE);
				
				TimeSlot timeSlot = new TimeSlot();
				timeSlot.setEndDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE)));
				timeSlot.setStartDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE)));
				timeSlot.setStartTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_START_TIME)));
				timeSlot.setEndTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_END_TIME)));

				return appointment;
			}
			return null;
		});
		
		
	}
	
	@Test
	public void TestBookAppointment() {
		String serviceName= "ServiceName1";
		String userId = "TestUserId";
		Time startTime = Time.valueOf(LocalTime.parse("10:00:00"));
		Date date = Date.valueOf(LocalDate.parse("2021-03-20"));
		Long garageTechnicianId=4587l;
		Integer garageSpot=2;
		
		Appointment appointment=null;
		try {
			appointment=service.bookAppointment(userId, serviceName, date, garageSpot, startTime, garageTechnicianId);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(appointment);
		assertEquals(serviceName,appointment.getBookableServices().getName());
		assertEquals(userId,appointment.getCustomer().getUserId());
		assertEquals(startTime,appointment.getTimeSlot().getStartTime());
		assertEquals(date,appointment.getTimeSlot().getStartDate());
		assertNotNull(appointment.getReceipt());
		assertNotNull(appointment.getReminder());
		assertEquals(appointment.getReceipt().getTotalPrice(),appointment.getBookableServices().getPrice());
	}
	
	@Test
	public void TestBookAppointmentNoCustomer() {
		String serviceName= "ServiceName1";
		String userId = "wrongUserId";
		Time startTime = Time.valueOf(LocalTime.parse("10:00:00"));
		Date date = Date.valueOf(LocalDate.parse("2021-03-20"));
		Long garageTechnicianId=4587l;
		Integer garageSpot=2;
		
		String error=null;
		
		Appointment appointment=null;
		try {
			appointment=service.bookAppointment(userId, serviceName, date, garageSpot, startTime, garageTechnicianId);
		} catch(IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(appointment);
		assertEquals(error,"No customer with such userId!");
	}
	
	@Test
	public void TestBookAppointmentNoService() {
		String serviceName= "wrongServiceName";
		String userId = "TestUserId";
		Time startTime = Time.valueOf(LocalTime.parse("10:00:00"));
		Date date = Date.valueOf(LocalDate.parse("2021-03-20"));
		Long garageTechnicianId=4587l;
		Integer garageSpot=2;
		
		String error=null;
		
		Appointment appointment=null;
		try {
			appointment=service.bookAppointment(userId, serviceName, date, garageSpot, startTime, garageTechnicianId);
		} catch(IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(appointment);
		assertEquals(error,"No Bookable Service with such name!");
	}
	
	@Test
	public void TestBookAppointmentOverlapDifferentSpot() {
		String serviceName= "ServiceName1";
		String userId = "TestUserId";
		Time startTime = Time.valueOf(LocalTime.parse("12:45:00"));
		Date date = Date.valueOf(LocalDate.parse("2021-03-18"));
		Long garageTechnicianId=4587l;
		Integer garageSpot=2;
		
		Appointment appointment=null;
		try {
			appointment=service.bookAppointment(userId, serviceName, date, garageSpot, startTime, garageTechnicianId);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(appointment);
		assertEquals(serviceName,appointment.getBookableServices().getName());
		assertEquals(userId,appointment.getCustomer().getUserId());
		assertEquals(startTime,appointment.getTimeSlot().getStartTime());
		assertEquals(date,appointment.getTimeSlot().getStartDate());
		assertNotNull(appointment.getReceipt());
		assertNotNull(appointment.getReminder());
		assertEquals(appointment.getReceipt().getTotalPrice(),appointment.getBookableServices().getPrice());
	}
	
	@Test
	public void TestBookAppointmentOverlapSameSpot() {
		String serviceName= "ServiceName1";
		String userId = "TestUserId";
		Time startTime = Time.valueOf(LocalTime.parse("12:45:00"));
		Date date = Date.valueOf(LocalDate.parse("2021-03-18"));
		Long garageTechnicianId=4587l;
		Integer garageSpot=1;
		String error=null;
		
		Appointment appointment=null;
		try {
			appointment=service.bookAppointment(userId, serviceName, date, garageSpot, startTime, garageTechnicianId);
		} catch(IllegalArgumentException e) {
			error=e.getMessage();
		}
		
		assertNull(appointment);
		assertEquals(error,"This attempted booking overlaps with another!");
		
	}
	
	@Test
	public void TestCreateAppointment() {
		Customer customer= new Customer();
		BookableService bookableService = new BookableService();
		GarageTechnician garageTechnician = new GarageTechnician();
		TimeSlot timeSlot = new TimeSlot();
		AppointmentReminder appointmentReminder =new AppointmentReminder();
		Receipt receipt = new Receipt();
		
		Appointment appointment = null;
		
		try {
			appointment=service.createAppointment(customer, bookableService, garageTechnician, timeSlot, appointmentReminder, receipt);
		} catch(IllegalArgumentException e) {
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
		Customer customer= null;
		BookableService bookableService = new BookableService();
		GarageTechnician garageTechnician = new GarageTechnician();
		TimeSlot timeSlot = new TimeSlot();
		AppointmentReminder appointmentReminder =new AppointmentReminder();
		Receipt receipt = new Receipt();
		
		String error=null;
		
		Appointment appointment = null;
		
		try {
			appointment=service.createAppointment(customer, bookableService, garageTechnician, timeSlot, appointmentReminder, receipt);
		} catch(IllegalArgumentException e) {
			error=e.getMessage();
		}
		
		assertNull(appointment);
		assertEquals(error,"Customer can't be null");
	}
	
	@Test
	public void TestCreateAppointmentBookableServiceNull() {
		Customer customer= new Customer();
		BookableService bookableService = null;
		GarageTechnician garageTechnician = new GarageTechnician();
		TimeSlot timeSlot = new TimeSlot();
		AppointmentReminder appointmentReminder =new AppointmentReminder();
		Receipt receipt = new Receipt();
		
		String error=null;
		
		Appointment appointment = null;
		
		try {
			appointment=service.createAppointment(customer, bookableService, garageTechnician, timeSlot, appointmentReminder, receipt);
		} catch(IllegalArgumentException e) {
			error=e.getMessage();
		}
		
		assertNull(appointment);
		assertEquals(error,"Bookable Service can't be null");
	}
	
	@Test
	public void TestCreateAppointmentGarageTechnicianNull() {
		Customer customer= new Customer();
		BookableService bookableService = new BookableService();
		GarageTechnician garageTechnician = null;
		TimeSlot timeSlot = new TimeSlot();
		AppointmentReminder appointmentReminder =new AppointmentReminder();
		Receipt receipt = new Receipt();
		
		String error=null;
		
		Appointment appointment = null;
		
		try {
			appointment=service.createAppointment(customer, bookableService, garageTechnician, timeSlot, appointmentReminder, receipt);
		} catch(IllegalArgumentException e) {
			error=e.getMessage();
		}
		
		assertNull(appointment);
		assertEquals(error,"Garage Technician can't be null");
	}
	
	@Test
	public void TestCreateAppointmentTimeSlotNull() {
		Customer customer= new Customer();
		BookableService bookableService = new BookableService();
		GarageTechnician garageTechnician = new GarageTechnician();
		TimeSlot timeSlot = null;
		AppointmentReminder appointmentReminder =new AppointmentReminder();
		Receipt receipt = new Receipt();
		
		String error=null;
		
		Appointment appointment = null;
		
		try {
			appointment=service.createAppointment(customer, bookableService, garageTechnician, timeSlot, appointmentReminder, receipt);
		} catch(IllegalArgumentException e) {
			error=e.getMessage();
		}
		
		assertNull(appointment);
		assertEquals(error,"Time Slot can't be null");
	}
	
	@Test
	public void TestCreateAppointmentAppointmentReminderNull() {
		Customer customer= new Customer();
		BookableService bookableService = new BookableService();
		GarageTechnician garageTechnician = new GarageTechnician();
		TimeSlot timeSlot = new TimeSlot();
		AppointmentReminder appointmentReminder =null;
		Receipt receipt = new Receipt();
		
		String error=null;
		
		Appointment appointment = null;
		
		try {
			appointment=service.createAppointment(customer, bookableService, garageTechnician, timeSlot, appointmentReminder, receipt);
		} catch(IllegalArgumentException e) {
			error=e.getMessage();
		}
		
		assertNull(appointment);
		assertEquals(error,"Appointment Reminder can't be null");
	}
	
	@Test
	public void TestCreateAppointmentReceiptNull() {
		Customer customer= new Customer();
		BookableService bookableService = new BookableService();
		GarageTechnician garageTechnician = new GarageTechnician();
		TimeSlot timeSlot = new TimeSlot();
		AppointmentReminder appointmentReminder =new AppointmentReminder();
		Receipt receipt = null;
		
		String error=null;
		
		Appointment appointment = null;
		
		try {
			appointment=service.createAppointment(customer, bookableService, garageTechnician, timeSlot, appointmentReminder, receipt);
		} catch(IllegalArgumentException e) {
			error=e.getMessage();
		}
		
		assertNull(appointment);
		assertEquals(error,"Receipt can't be null");
	}

}
