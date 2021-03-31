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

import ca.mcgill.ecse321.projectgroup04.dao.BusinessHourRepository;
import ca.mcgill.ecse321.projectgroup04.dao.BusinessRepository;
import ca.mcgill.ecse321.projectgroup04.dao.TimeSlotRepository;
import ca.mcgill.ecse321.projectgroup04.model.Business;
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour;
import ca.mcgill.ecse321.projectgroup04.model.TimeSlot;
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour.DayOfWeek;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class TestBusinessService {

    @Mock
    private BusinessHourRepository businessHourRepository;

    @Mock
    private TimeSlotRepository timeSlotRepository;

    @Mock
    private BusinessRepository businessRepository;

    @InjectMocks
    private BusinessService service;

    private static final String BUSINESS_NAME = "TestName";
    private static final String BUSINESS_PHONENUMBER = "438123456";
    private static final String BUSINESS_ADDRESS = "1234 MTL, Quebec";
    private static final String BUSINESS_MAIL = "testmail@mail.mcgill.ca";
    private static final Long BUSINESS_ID = 45678l;

    private static final String OLD_APPOINTMENT_DATE = "2021-03-18";
    private static final String OLD_APPOINTMENT_START_TIME = "13:00:00";
    private static final String OLD_APPOINTMENT_END_TIME = "13:30:00";

    private static final DayOfWeek BUSINESSHOUR_DAYOFWEEK = DayOfWeek.Monday;
    private static final String BUSINESSHOUR_STARTTIME = "08:00:00";
    private static final String BUSINESSHOUR_ENDTIME = "23:00:00";

    private static final List<BusinessHour> BUSINESS_BUSINESSHOUR = new ArrayList<BusinessHour>();
    private static final List<TimeSlot> BUSINESS_TIMESLOTS = new ArrayList<TimeSlot>();

    @BeforeEach
    public void setMockOutput() {
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(businessRepository.save(any(Business.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(timeSlotRepository.save(any(TimeSlot.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(businessHourRepository.save(any(BusinessHour.class))).thenAnswer(returnParameterAsAnswer);

        lenient().when(businessRepository.findBusinessById(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(BUSINESS_ID)) {

                Business business = new Business();
                business.setName(BUSINESS_NAME);
                business.setAddress(BUSINESS_ADDRESS);
                business.setEmailAddress(BUSINESS_MAIL);
                business.setPhoneNumber(BUSINESS_PHONENUMBER);
                business.setId(BUSINESS_ID);

                BusinessHour businessHour = new BusinessHour();
                businessHour.setDayOfWeek(BUSINESSHOUR_DAYOFWEEK);
                businessHour.setStartTime(Time.valueOf(LocalTime.parse(BUSINESSHOUR_STARTTIME)));
                businessHour.setEndTime(Time.valueOf(LocalTime.parse(BUSINESSHOUR_ENDTIME)));

                BUSINESS_BUSINESSHOUR.add(businessHour);
                business.setBusinessHours(BUSINESS_BUSINESSHOUR);

                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setEndDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE)));
                timeSlot.setStartDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE)));
                timeSlot.setStartTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_START_TIME)));
                timeSlot.setEndTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_END_TIME)));

                BUSINESS_TIMESLOTS.add(timeSlot);
                business.setRegular(BUSINESS_TIMESLOTS);

                return business;
            }
            return null;
        });
        lenient().when(businessRepository.findBusinessByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(BUSINESS_NAME)) {

                Business business = new Business();
                business.setName(BUSINESS_NAME);
                business.setAddress(BUSINESS_ADDRESS);
                business.setEmailAddress(BUSINESS_MAIL);
                business.setPhoneNumber(BUSINESS_PHONENUMBER);
                business.setId(BUSINESS_ID);

                BusinessHour businessHour = new BusinessHour();
                businessHour.setDayOfWeek(BUSINESSHOUR_DAYOFWEEK);
                businessHour.setStartTime(Time.valueOf(LocalTime.parse(BUSINESSHOUR_STARTTIME)));
                businessHour.setEndTime(Time.valueOf(LocalTime.parse(BUSINESSHOUR_ENDTIME)));

                BUSINESS_BUSINESSHOUR.add(businessHour);
                business.setBusinessHours(BUSINESS_BUSINESSHOUR);

                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setEndDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE)));
                timeSlot.setStartDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE)));
                timeSlot.setStartTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_START_TIME)));
                timeSlot.setEndTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_END_TIME)));

                BUSINESS_TIMESLOTS.add(timeSlot);
                business.setRegular(BUSINESS_TIMESLOTS);

                return business;
            }
            return null;
        });

    }

    @Test
    public void TestCreateBusiness() {
        String name = "Testname";
        String address = "1234 mtl, Quebec";
        String phoneNumber = "123 456 789";
        String emailAddress = "testmail@mail.mcgill.ca";

        Time startTime = Time.valueOf(LocalTime.parse("08:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("23:00:00"));

        List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();

        BusinessHour businessHour = new BusinessHour();
        businessHour.setDayOfWeek(DayOfWeek.Monday);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHours.add(businessHour);

        Date date = Date.valueOf(LocalDate.parse("2020-03-20"));
        Time aStartTime = Time.valueOf(LocalTime.parse("08:00:00"));
        Time aEndTime = Time.valueOf(LocalTime.parse("09:00:00"));

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartDate(date);
        timeSlot.setEndDate(date);
        timeSlot.setStartTime(aStartTime);
        timeSlot.setEndTime(aEndTime);

        timeSlots.add(timeSlot);

        Business business = null;
        try {
            business = service.createBusiness(name, address, phoneNumber, emailAddress, businessHours, timeSlots);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(business);
        assertEquals(name, business.getName());
        assertEquals(phoneNumber, business.getPhoneNumber());
        assertEquals(emailAddress, business.getEmailAddress());
        assertEquals(address, business.getAddress());

        assertEquals(businessHour, business.getBusinessHours().get(0));
        assertEquals(timeSlot, business.getRegular().get(0));

    }

    @Test
    public void TestCreateBusinessNoName() {
        String name = "";
        String address = "1234 mtl, Quebec";
        String phoneNumber = "123 456 789";
        String emailAddress = "testmail@mail.mcgill.ca";

        String error = null;

        Time startTime = Time.valueOf(LocalTime.parse("08:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("23:00:00"));

        List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();

        BusinessHour businessHour = new BusinessHour();
        businessHour.setDayOfWeek(DayOfWeek.Monday);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHours.add(businessHour);

        Date date = Date.valueOf(LocalDate.parse("2020-03-20"));
        Time aStartTime = Time.valueOf(LocalTime.parse("08:00:00"));
        Time aEndTime = Time.valueOf(LocalTime.parse("09:00:00"));

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartDate(date);
        timeSlot.setEndDate(date);
        timeSlot.setStartTime(aStartTime);
        timeSlot.setEndTime(aEndTime);

        timeSlots.add(timeSlot);

        Business business = null;
        try {
            business = service.createBusiness(name, address, phoneNumber, emailAddress, businessHours, timeSlots);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(business);
        assertEquals(error, "Name cannot be empty");

    }

    @Test
    public void TestCreateBusinessThatAlreadyExists() {
        String name = "TestName";
        String address = "1234 mtl, Quebec";
        String phoneNumber = "123 456 789";
        String emailAddress = "testmail@mail.mcgill.ca";

        String error = null;

        Time startTime = Time.valueOf(LocalTime.parse("08:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("23:00:00"));

        List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();

        BusinessHour businessHour = new BusinessHour();
        businessHour.setDayOfWeek(DayOfWeek.Monday);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHours.add(businessHour);

        Date date = Date.valueOf(LocalDate.parse("2020-03-20"));
        Time aStartTime = Time.valueOf(LocalTime.parse("08:00:00"));
        Time aEndTime = Time.valueOf(LocalTime.parse("09:00:00"));

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartDate(date);
        timeSlot.setEndDate(date);
        timeSlot.setStartTime(aStartTime);
        timeSlot.setEndTime(aEndTime);

        timeSlots.add(timeSlot);

        Business business = null;
        try {
            business = service.createBusiness(name, address, phoneNumber, emailAddress, businessHours, timeSlots);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(business);
        assertEquals(error, "Business already exists");

    }

    @Test
    public void TestCreateBusinessNoAddress() {
        String name = "Testname";
        String address = "";
        String phoneNumber = "123 456 789";
        String emailAddress = "testmail@mail.mcgill.ca";

        String error = null;

        Time startTime = Time.valueOf(LocalTime.parse("08:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("23:00:00"));

        List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();

        BusinessHour businessHour = new BusinessHour();
        businessHour.setDayOfWeek(DayOfWeek.Monday);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHours.add(businessHour);

        Date date = Date.valueOf(LocalDate.parse("2020-03-20"));
        Time aStartTime = Time.valueOf(LocalTime.parse("08:00:00"));
        Time aEndTime = Time.valueOf(LocalTime.parse("09:00:00"));

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartDate(date);
        timeSlot.setEndDate(date);
        timeSlot.setStartTime(aStartTime);
        timeSlot.setEndTime(aEndTime);

        timeSlots.add(timeSlot);

        Business business = null;
        try {
            business = service.createBusiness(name, address, phoneNumber, emailAddress, businessHours, timeSlots);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(business);
        assertEquals(error, "Address cannot be empty");

    }

    @Test
    public void TestCreateBusinessNoPhoneNumber() {
        String name = "Testname";
        String address = "1234 mtl, Quebec";
        String phoneNumber = "";
        String emailAddress = "testmail@mail.mcgill.ca";

        String error = null;

        Time startTime = Time.valueOf(LocalTime.parse("08:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("23:00:00"));

        List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();

        BusinessHour businessHour = new BusinessHour();
        businessHour.setDayOfWeek(DayOfWeek.Monday);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHours.add(businessHour);

        Date date = Date.valueOf(LocalDate.parse("2020-03-20"));
        Time aStartTime = Time.valueOf(LocalTime.parse("08:00:00"));
        Time aEndTime = Time.valueOf(LocalTime.parse("09:00:00"));

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartDate(date);
        timeSlot.setEndDate(date);
        timeSlot.setStartTime(aStartTime);
        timeSlot.setEndTime(aEndTime);

        timeSlots.add(timeSlot);

        Business business = null;
        try {
            business = service.createBusiness(name, address, phoneNumber, emailAddress, businessHours, timeSlots);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(business);
        assertEquals(error, "Phone number cannot be empty");

    }

    @Test
    public void TestCreateBusinessNoEmail() {
        String name = "Testname";
        String address = "1234 mtl, Quebec";
        String phoneNumber = "123456789";
        String emailAddress = "";

        String error = null;

        Time startTime = Time.valueOf(LocalTime.parse("08:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("23:00:00"));

        List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();

        BusinessHour businessHour = new BusinessHour();
        businessHour.setDayOfWeek(DayOfWeek.Monday);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHours.add(businessHour);

        Date date = Date.valueOf(LocalDate.parse("2020-03-20"));
        Time aStartTime = Time.valueOf(LocalTime.parse("08:00:00"));
        Time aEndTime = Time.valueOf(LocalTime.parse("09:00:00"));

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartDate(date);
        timeSlot.setEndDate(date);
        timeSlot.setStartTime(aStartTime);
        timeSlot.setEndTime(aEndTime);

        timeSlots.add(timeSlot);

        Business business = null;
        try {
            business = service.createBusiness(name, address, phoneNumber, emailAddress, businessHours, timeSlots);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(business);
        assertEquals(error, "Email address cannot be empty");

    }

    @Test
    public void TestCreateBusinessIncorrectEmail() {
        String name = "Testname";
        String address = "1234 mtl, Quebec";
        String phoneNumber = "123456789";
        String emailAddress = "testmailmcgill.ca";

        String error = null;

        Time startTime = Time.valueOf(LocalTime.parse("08:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("23:00:00"));

        List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();

        BusinessHour businessHour = new BusinessHour();
        businessHour.setDayOfWeek(DayOfWeek.Monday);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHours.add(businessHour);

        Date date = Date.valueOf(LocalDate.parse("2020-03-20"));
        Time aStartTime = Time.valueOf(LocalTime.parse("08:00:00"));
        Time aEndTime = Time.valueOf(LocalTime.parse("09:00:00"));

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartDate(date);
        timeSlot.setEndDate(date);
        timeSlot.setStartTime(aStartTime);
        timeSlot.setEndTime(aEndTime);

        timeSlots.add(timeSlot);

        Business business = null;
        try {
            business = service.createBusiness(name, address, phoneNumber, emailAddress, businessHours, timeSlots);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(business);
        assertEquals(error, "Email Address must contain @ character");

    }

    @Test
    public void TestCreateBusinessNoBusinessHours() {
        String name = "Testname";
        String address = "1234 mtl, Quebec";
        String phoneNumber = "123456789";
        String emailAddress = "testmail@mail.mcgill.ca";

        String error = null;

        List<BusinessHour> businessHours = null;
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();

        Date date = Date.valueOf(LocalDate.parse("2020-03-20"));
        Time aStartTime = Time.valueOf(LocalTime.parse("08:00:00"));
        Time aEndTime = Time.valueOf(LocalTime.parse("09:00:00"));

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartDate(date);
        timeSlot.setEndDate(date);
        timeSlot.setStartTime(aStartTime);
        timeSlot.setEndTime(aEndTime);

        timeSlots.add(timeSlot);

        Business business = null;
        try {
            business = service.createBusiness(name, address, phoneNumber, emailAddress, businessHours, timeSlots);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(business);
        assertEquals(error, "Business Hours cannot be null");

    }

    @Test
    public void TestCreateBusinessNoTimeSlots() {
        String name = "Testname";
        String address = "1234 mtl, Quebec";
        String phoneNumber = "123456789";
        String emailAddress = "testmail@mail.mcgill.ca";

        String error = null;

        Time startTime = Time.valueOf(LocalTime.parse("08:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("23:00:00"));

        List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
        List<TimeSlot> timeSlots = null;

        BusinessHour businessHour = new BusinessHour();
        businessHour.setDayOfWeek(DayOfWeek.Monday);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHours.add(businessHour);

        Business business = null;
        try {
            business = service.createBusiness(name, address, phoneNumber, emailAddress, businessHours, timeSlots);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(business);
        assertEquals(error, "Timeslots cannot be null");

    }

    @Test
    public void TestUpdateBusinessInfo() {
        String name = "TestName";
        String address = "4567 laval, Quebec";
        String phoneNumber = "122 567 335";
        String emailAddress = "updatedmail@mail.mcgill.ca";

        Long businessId = 45678l;

        Time startTime = Time.valueOf(LocalTime.parse("09:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("22:00:00"));

        List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();

        BusinessHour businessHour = new BusinessHour();
        businessHour.setDayOfWeek(DayOfWeek.Sunday);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHours.add(businessHour);

        Date date = Date.valueOf(LocalDate.parse("2020-06-30"));
        Time aStartTime = Time.valueOf(LocalTime.parse("10:00:00"));
        Time aEndTime = Time.valueOf(LocalTime.parse("11:00:00"));

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartDate(date);
        timeSlot.setEndDate(date);
        timeSlot.setStartTime(aStartTime);
        timeSlot.setEndTime(aEndTime);

        timeSlots.add(timeSlot);

        Business business = null;
        // Business businesstemp = businessRepository.findBusinessById(businessId);

        try {
            business = service.updateBusinessInformation(businessId, name, address, phoneNumber, emailAddress,
                    businessHours, timeSlots);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            fail();
        }
        // businessRepository.save(business);
        // Business businesst = businessRepository.findBusinessByName(name);

        assertNotNull(business);
        assertEquals(name, business.getName());
        assertEquals(phoneNumber, business.getPhoneNumber());
        assertEquals(emailAddress, business.getEmailAddress());
        assertEquals(address, business.getAddress());

        assertEquals(businessHour, business.getBusinessHours().get(0));
        assertEquals(timeSlot, business.getRegular().get(0));

        // assertNotEquals(businesstemp.getName(), business.getName());
        // assertNotEquals(businesstemp.getPhoneNumber(), business.getPhoneNumber());
        // assertNotEquals(businesstemp.getEmailAddress(), business.getEmailAddress());
        // assertNotEquals(businesstemp.getAddress(), business.getAddress());

        // assertNotEquals(businesstemp.getBusinessHours().get(0),
        // business.getBusinessHours().get(0));
        // assertNotEquals(businesstemp.getRegular().get(0),
        // business.getRegular().get(0));
    }

    @Test
    public void TestUpdateBusinessInfoBusinessDoesntExist() {
        String name = "NonExistentBusiness";
        String address = "4567 laval, Quebec";
        String phoneNumber = "122 567 335";
        String emailAddress = "updatedmail@mail.mcgill.ca";

        Long businessId = 4567l;

        Time startTime = Time.valueOf(LocalTime.parse("09:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("22:00:00"));

        List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();

        BusinessHour businessHour = new BusinessHour();
        businessHour.setDayOfWeek(DayOfWeek.Sunday);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHours.add(businessHour);

        Date date = Date.valueOf(LocalDate.parse("2020-06-30"));
        Time aStartTime = Time.valueOf(LocalTime.parse("10:00:00"));
        Time aEndTime = Time.valueOf(LocalTime.parse("11:00:00"));

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartDate(date);
        timeSlot.setEndDate(date);
        timeSlot.setStartTime(aStartTime);
        timeSlot.setEndTime(aEndTime);

        timeSlots.add(timeSlot);

        Business business = null;

        String error = "";

        try {
            business = service.updateBusinessInformation(businessId, name, address, phoneNumber, emailAddress,
                    businessHours, timeSlots);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(business);
        assertEquals(error, "The business with this Id doesn't exist");

    }

    @Test
    public void TestUpdateBusinessInfoNoAddress() {
        String name = "TestName";
        String address = "";
        String phoneNumber = "123 456 789";
        String emailAddress = "updatedmail@mail.mcgill.ca";

        Long businessId = 45678l;

        Time startTime = Time.valueOf(LocalTime.parse("09:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("22:00:00"));

        List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();

        BusinessHour businessHour = new BusinessHour();
        businessHour.setDayOfWeek(DayOfWeek.Sunday);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHours.add(businessHour);

        Date date = Date.valueOf(LocalDate.parse("2020-06-30"));
        Time aStartTime = Time.valueOf(LocalTime.parse("10:00:00"));
        Time aEndTime = Time.valueOf(LocalTime.parse("11:00:00"));

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartDate(date);
        timeSlot.setEndDate(date);
        timeSlot.setStartTime(aStartTime);
        timeSlot.setEndTime(aEndTime);

        timeSlots.add(timeSlot);

        Business business = null;
        // Business businesstemp = businessRepository.findBusinessByName(name);

        // String error = "";

        try {
            business = service.updateBusinessInformation(businessId, name, address, phoneNumber, emailAddress,
                    businessHours, timeSlots);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            fail();
        }

        assertNotNull(business);
        assertEquals(name, business.getName());
        assertEquals(phoneNumber, business.getPhoneNumber());
        assertEquals(emailAddress, business.getEmailAddress());
        assertEquals(BUSINESS_ADDRESS, business.getAddress());

        // assertEquals(businessHour, business.getBusinessHours().get(0));
        // assertEquals(timeSlot, business.getRegular().get(0));

        // assertNotEquals(businesstemp.getPhoneNumber(), business.getPhoneNumber());
        // assertNotEquals(businesstemp.getEmailAddress(), business.getEmailAddress());
        // // assertNotEquals(businesstemp.getAddress(), business.getAddress());

        // assertNotEquals(businesstemp.getBusinessHours().get(0),
        // business.getBusinessHours().get(0));
        // assertNotEquals(businesstemp.getRegular().get(0),
        // business.getRegular().get(0));

    }

    @Test
    public void TestUpdateBusinessInfoNoPhoneNumber() {
        String name = "TestName";
        String address = "456 laval, Quebec";
        String phoneNumber = "";
        String emailAddress = "updatedmail@mail.mcgill.ca";

        Long businessId = 45678l;

        Time startTime = Time.valueOf(LocalTime.parse("09:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("22:00:00"));

        List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();

        BusinessHour businessHour = new BusinessHour();
        businessHour.setDayOfWeek(DayOfWeek.Sunday);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHours.add(businessHour);

        Date date = Date.valueOf(LocalDate.parse("2020-06-30"));
        Time aStartTime = Time.valueOf(LocalTime.parse("10:00:00"));
        Time aEndTime = Time.valueOf(LocalTime.parse("11:00:00"));

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartDate(date);
        timeSlot.setEndDate(date);
        timeSlot.setStartTime(aStartTime);
        timeSlot.setEndTime(aEndTime);

        timeSlots.add(timeSlot);

        Business business = null;
        Business businesstemp = businessRepository.findBusinessByName(name);

        // String error = "";

        try {
            business = service.updateBusinessInformation(businessId, name, address, phoneNumber, emailAddress,
                    businessHours, timeSlots);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(business);
        assertEquals(name, business.getName());
        assertEquals(businesstemp.getPhoneNumber(), business.getPhoneNumber());
        assertEquals(emailAddress, business.getEmailAddress());
        assertEquals(address, business.getAddress());

        assertEquals(businessHour, business.getBusinessHours().get(0));
        assertEquals(timeSlot, business.getRegular().get(0));

        // assertNotEquals(businesstemp.getPhoneNumber(), business.getPhoneNumber());
        assertNotEquals(businesstemp.getEmailAddress(), business.getEmailAddress());
        assertNotEquals(businesstemp.getAddress(), business.getAddress());

        assertNotEquals(businesstemp.getBusinessHours().get(0), business.getBusinessHours().get(0));
        assertNotEquals(businesstemp.getRegular().get(0), business.getRegular().get(0));

    }

    @Test
    public void TestUpdateBusinessInfoNoEmail() {
        String name = "TestName";
        String address = "456 laval, Quebec";
        String phoneNumber = "123 456 789";
        String emailAddress = "";

        Long businessId = 45678l;

        Time startTime = Time.valueOf(LocalTime.parse("09:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("22:00:00"));

        List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();

        BusinessHour businessHour = new BusinessHour();
        businessHour.setDayOfWeek(DayOfWeek.Sunday);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHours.add(businessHour);

        Date date = Date.valueOf(LocalDate.parse("2020-06-30"));
        Time aStartTime = Time.valueOf(LocalTime.parse("10:00:00"));
        Time aEndTime = Time.valueOf(LocalTime.parse("11:00:00"));

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartDate(date);
        timeSlot.setEndDate(date);
        timeSlot.setStartTime(aStartTime);
        timeSlot.setEndTime(aEndTime);

        timeSlots.add(timeSlot);

        Business business = null;
        Business businesstemp = businessRepository.findBusinessByName(name);

        // String error = "";

        try {
            business = service.updateBusinessInformation(businessId, name, address, phoneNumber, emailAddress,
                    businessHours, timeSlots);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(business);
        assertEquals(name, business.getName());
        assertEquals(phoneNumber, business.getPhoneNumber());
        assertEquals(businesstemp.getEmailAddress(), business.getEmailAddress());
        assertEquals(address, business.getAddress());

        assertEquals(businessHour, business.getBusinessHours().get(0));
        assertEquals(timeSlot, business.getRegular().get(0));

        assertNotEquals(businesstemp.getPhoneNumber(), business.getPhoneNumber());
        // assertNotEquals(businesstemp.getEmailAddress(), business.getEmailAddress());
        assertNotEquals(businesstemp.getAddress(), business.getAddress());

        assertNotEquals(businesstemp.getBusinessHours().get(0), business.getBusinessHours().get(0));
        assertNotEquals(businesstemp.getRegular().get(0), business.getRegular().get(0));

    }

    @Test
    public void TestUpdateBusinessInfoWrongEmail() {
        String name = "TestName";
        String address = "456 laval, Quebec";
        String phoneNumber = "123 456 789";
        String emailAddress = "testmailmail.mcgill.ca";

        Long businessId = 45678l;

        Time startTime = Time.valueOf(LocalTime.parse("09:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("22:00:00"));

        List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();

        BusinessHour businessHour = new BusinessHour();
        businessHour.setDayOfWeek(DayOfWeek.Sunday);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHours.add(businessHour);

        Date date = Date.valueOf(LocalDate.parse("2020-06-30"));
        Time aStartTime = Time.valueOf(LocalTime.parse("10:00:00"));
        Time aEndTime = Time.valueOf(LocalTime.parse("11:00:00"));

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartDate(date);
        timeSlot.setEndDate(date);
        timeSlot.setStartTime(aStartTime);
        timeSlot.setEndTime(aEndTime);

        timeSlots.add(timeSlot);

        Business business = null;

        String error = "";

        try {
            business = service.updateBusinessInformation(businessId, name, address, phoneNumber, emailAddress,
                    businessHours, timeSlots);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(business);
        assertEquals(error, "Email Address must contain @ character");

    }

    @Test
    public void TestUpdateBusinessInfoNoBusinessHours() {
        String name = "TestName";
        String address = "456 laval, Quebec";
        String phoneNumber = "123 456 789";
        String emailAddress = "updatedmail@mail.mcgill.ca";

        Long businessId = 45678l;

        List<BusinessHour> businessHours = null;
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();

        Date date = Date.valueOf(LocalDate.parse("2020-06-30"));
        Time aStartTime = Time.valueOf(LocalTime.parse("10:00:00"));
        Time aEndTime = Time.valueOf(LocalTime.parse("11:00:00"));

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartDate(date);
        timeSlot.setEndDate(date);
        timeSlot.setStartTime(aStartTime);
        timeSlot.setEndTime(aEndTime);

        timeSlots.add(timeSlot);

        Business business = null;
        Business businesstemp = businessRepository.findBusinessByName(name);

        // String error = "";

        try {
            business = service.updateBusinessInformation(businessId, name, address, phoneNumber, emailAddress,
                    businessHours, timeSlots);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(business);
        assertEquals(name, business.getName());
        assertEquals(phoneNumber, business.getPhoneNumber());
        assertEquals(emailAddress, business.getEmailAddress());
        assertEquals(address, business.getAddress());

        assertEquals(businesstemp.getBusinessHours().get(0), business.getBusinessHours().get(0));
        assertEquals(timeSlot, business.getRegular().get(0));

        assertNotEquals(businesstemp.getPhoneNumber(), business.getPhoneNumber());
        assertNotEquals(businesstemp.getEmailAddress(), business.getEmailAddress());
        assertNotEquals(businesstemp.getAddress(), business.getAddress());

        // assertNotEquals(businesstemp.getBusinessHours().get(0),
        // business.getBusinessHours().get(0));
        assertNotEquals(businesstemp.getRegular().get(0), business.getRegular().get(0));

    }

    @Test
    public void TestUpdateBusinessInfoNoTimeSlots() {
        String name = "TestName";
        String address = "456 laval, Quebec";
        String phoneNumber = "123 456 789";
        String emailAddress = "updatedmail@mail.mcgill.ca";

        Long businessId = 45678l;

        Time startTime = Time.valueOf(LocalTime.parse("09:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("22:00:00"));

        List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
        List<TimeSlot> timeSlots = null;

        BusinessHour businessHour = new BusinessHour();
        businessHour.setDayOfWeek(DayOfWeek.Sunday);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHours.add(businessHour);

        Business business = null;
        Business businesstemp = businessRepository.findBusinessByName(name);

        // String error = "";

        try {
            business = service.updateBusinessInformation(businessId, name, address, phoneNumber, emailAddress,
                    businessHours, timeSlots);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(business);
        assertEquals(name, business.getName());
        assertEquals(phoneNumber, business.getPhoneNumber());
        assertEquals(emailAddress, business.getEmailAddress());
        assertEquals(address, business.getAddress());

        assertEquals(businessHour, business.getBusinessHours().get(0));
        assertEquals(businesstemp.getRegular().get(0), business.getRegular().get(0));

        assertNotEquals(businesstemp.getPhoneNumber(), business.getPhoneNumber());
        assertNotEquals(businesstemp.getEmailAddress(), business.getEmailAddress());
        assertNotEquals(businesstemp.getAddress(), business.getAddress());

        assertNotEquals(businesstemp.getBusinessHours().get(0), business.getBusinessHours().get(0));
        // assertNotEquals(businesstemp.getRegular().get(0),
        // business.getRegular().get(0));

    }
}
