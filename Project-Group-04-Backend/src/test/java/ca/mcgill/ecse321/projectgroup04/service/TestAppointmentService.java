package ca.mcgill.ecse321.projectgroup04.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.projectgroup04.dao.AppointmentRepository;
import ca.mcgill.ecse321.projectgroup04.dao.BookableServiceRepository;
import ca.mcgill.ecse321.projectgroup04.dao.CustomerRepository;
import ca.mcgill.ecse321.projectgroup04.dao.GarageTechnicianRepository;
import ca.mcgill.ecse321.projectgroup04.dao.ReceiptRepository;
import ca.mcgill.ecse321.projectgroup04.dao.TimeSlotRepository;

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
	
	private static final String CUSTOMER_USERID = "TestUserId";
	private static final String CUSTOMER_PASSWORD ="TestPassword0";
	
	private static final String PROFILE_ADDRESS_EMAIL ="harry@potter.com";
	private static final String PROFILE_FIRST_NAME ="Harry";
	private static final String PROFILE_LAST_NAME= "Potter";
	private static final String PROFILE_ADDRESS_LINE="Hogwarts,London";
	private static final String PROFILE_PHONE_NUMBER="8889993333";
	private static final String PROFILE_ZIP_CODE="H3K4L6";
	
	private static final String CAR_MODEL="Mercedes C";
	private static final String CAR_COLOR="Black";
	private static final String CAR_YEAR="2007";
	
	private static final String SERVICE_NAME="ServiceName1";
	private static final int SERVICE_PRICE=50;
	private static final int SERVICE_DURATION=30;
	
	private static final String SERVICE_NAME2="ServiceName2";
	private static final int SERVICE_PRICE2=75;
	private static final int SERVICE_DURATION2=45;
	
	private static final String TODAY_DATE ="2021-03-15";

}
