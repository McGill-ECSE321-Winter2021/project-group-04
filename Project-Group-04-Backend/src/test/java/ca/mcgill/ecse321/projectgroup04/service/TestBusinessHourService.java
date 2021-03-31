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
import ca.mcgill.ecse321.projectgroup04.model.Business;
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour;
import ca.mcgill.ecse321.projectgroup04.model.TimeSlot;
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour.DayOfWeek;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class TestBusinessHourService {

    @Mock
    private BusinessHourRepository businessHourRepository;

    @Mock
    private BusinessRepository businessRepository;

    @InjectMocks
    private BusinessHourService service;

    @InjectMocks
    private BusinessService businessService;

    private static final Long BUSINESSHOUR_ID = 123l;
    private static final DayOfWeek BUSINESSHOUR_DAY = DayOfWeek.Monday;
    private static final String BUSINESSHOUR_STARTTIME = "09:00:00";
    private static final String BUSINESSHOUR_ENDTIME = "17:00:00";

    private static final Long BUSINESSHOUR_ID_2 = 456l;
    private static final DayOfWeek BUSINESSHOUR_DAY_2 = DayOfWeek.Tuesday;
    private static final String BUSINESSHOUR_STARTTIME_2 = "08:00:00";
    private static final String BUSINESSHOUR_ENDTIME_2 = "19:00:00";

    private static final String BUSINESS_NAME = "AutoRepairShop";
    private static final String BUSINESS_PHONENUMBER = "438123456";
    private static final String BUSINESS_ADDRESS = "1234 MTL, Quebec";
    private static final String BUSINESS_MAIL = "testmail@mail.mcgill.ca";
    private static final Long BUSINESS_ID = 45678l;

    private static final String OLD_APPOINTMENT_DATE = "2021-03-18";
    private static final String OLD_APPOINTMENT_START_TIME = "13:00:00";
    private static final String OLD_APPOINTMENT_END_TIME = "13:30:00";

    private static final DayOfWeek BUSINESSHOUR_DAYOFWEEK = DayOfWeek.Monday;
    // private static final String BUSINESSHOUR_STARTTIME2 = "08:00:00";
    // private static final String BUSINESSHOUR_ENDTIME = "23:00:00";

    private static final List<BusinessHour> BUSINESS_BUSINESSHOUR = new ArrayList<BusinessHour>();
    private static final List<TimeSlot> BUSINESS_TIMESLOTS = new ArrayList<TimeSlot>();

    @BeforeEach
    public void setMockOutput() {
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(businessRepository.save(any(Business.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(businessHourRepository.save(any(BusinessHour.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(businessHourRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {

            List<BusinessHour> businessHours = new ArrayList<BusinessHour>();

            BusinessHour businessHour = new BusinessHour();
            businessHour.setDayOfWeek(BUSINESSHOUR_DAY);
            businessHour.setStartTime(Time.valueOf(LocalTime.parse(BUSINESSHOUR_STARTTIME)));
            businessHour.setEndTime(Time.valueOf(LocalTime.parse(BUSINESSHOUR_ENDTIME)));
            businessHour.setHourId(BUSINESSHOUR_ID);

            BusinessHour businessHour2 = new BusinessHour();
            businessHour2.setDayOfWeek(BUSINESSHOUR_DAY_2);
            businessHour2.setStartTime(Time.valueOf(LocalTime.parse(BUSINESSHOUR_STARTTIME_2)));
            businessHour2.setEndTime(Time.valueOf(LocalTime.parse(BUSINESSHOUR_ENDTIME_2)));
            businessHour2.setHourId(BUSINESSHOUR_ID_2);

            businessHours.add(businessHour2);
            businessHours.add(businessHour);

            return businessHours;
        });
        lenient().when(businessHourRepository.findBusinessHourByHourId(anyLong()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (invocation.getArgument(0).equals(BUSINESSHOUR_ID)) {

                        BusinessHour businessHour = new BusinessHour();
                        businessHour.setDayOfWeek(BUSINESSHOUR_DAY);
                        businessHour.setStartTime(Time.valueOf(LocalTime.parse(BUSINESSHOUR_STARTTIME)));
                        businessHour.setEndTime(Time.valueOf(LocalTime.parse(BUSINESSHOUR_ENDTIME)));
                        businessHour.setHourId(BUSINESSHOUR_ID);

                        return businessHour;
                    }
                    if (invocation.getArgument(0).equals(BUSINESSHOUR_ID_2)) {

                        BusinessHour businessHour = new BusinessHour();
                        businessHour.setDayOfWeek(BUSINESSHOUR_DAY_2);
                        businessHour.setStartTime(Time.valueOf(LocalTime.parse(BUSINESSHOUR_STARTTIME_2)));
                        businessHour.setEndTime(Time.valueOf(LocalTime.parse(BUSINESSHOUR_ENDTIME_2)));
                        businessHour.setHourId(BUSINESSHOUR_ID_2);

                        return businessHour;
                    }
                    return null;
                });
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
    public void createBusinessHour() {

        String dayOfWeek = "Thursday";
        Time startTime = Time.valueOf(LocalTime.parse("09:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("23:00:00"));

        BusinessHour businessHour = null;
        try {
            businessHour = service.createBusinessHour(dayOfWeek, startTime, endTime);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            fail();
        }

        assertNotNull(businessHour);
        assertEquals(dayOfWeek, businessHour.getDayOfWeek().toString());
        assertEquals(startTime, businessHour.getStartTime());
        assertEquals(endTime, businessHour.getEndTime());
    }

    @Test
    public void createBusinessHourNoDayOfWeek() {

        String dayOfWeek = "";
        Time startTime = Time.valueOf(LocalTime.parse("09:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("23:00:00"));

        BusinessHour businessHour = null;

        String error = "";

        try {
            businessHour = service.createBusinessHour(dayOfWeek, startTime, endTime);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(businessHour);
        assertEquals(error, "Day of the week cannot be null");
    }

    @Test
    public void createBusinessHourNoStartTime() {

        String dayOfWeek = "Monday";
        Time startTime = null;
        Time endTime = Time.valueOf(LocalTime.parse("23:00:00"));

        BusinessHour businessHour = null;

        String error = "";

        try {
            businessHour = service.createBusinessHour(dayOfWeek, startTime, endTime);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(businessHour);
        assertEquals(error, "Start time cannot be null");
    }

    @Test
    public void createBusinessHourNoEndTime() {

        String dayOfWeek = "Monday";
        Time endTime = null;
        Time startTime = Time.valueOf(LocalTime.parse("23:00:00"));

        BusinessHour businessHour = null;

        String error = "";

        try {
            businessHour = service.createBusinessHour(dayOfWeek, startTime, endTime);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(businessHour);
        assertEquals(error, "End time cannot be null");
    }

    @Test
    public void createBusinessHourStartTimeEqualEndTime() {

        String dayOfWeek = "Monday";
        Time startTime = Time.valueOf(LocalTime.parse("23:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("23:00:00"));

        BusinessHour businessHour = null;

        String error = "";

        try {
            businessHour = service.createBusinessHour(dayOfWeek, startTime, endTime);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(businessHour);
        assertEquals(error, "Business hour start time cannot equal to end time");
    }

    @Test
    public void createBusinessHourStartTimeLaterEndTime() {

        String dayOfWeek = "Monday";
        Time startTime = Time.valueOf(LocalTime.parse("23:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("10:00:00"));

        BusinessHour businessHour = null;

        String error = "";

        try {
            businessHour = service.createBusinessHour(dayOfWeek, startTime, endTime);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(businessHour);
        assertEquals(error, "Start time has to be before end Time");
    }

    @Test
    public void createBusinessHourExist() {

        String dayOfWeek = "Monday";
        Time startTime = Time.valueOf(LocalTime.parse(BUSINESSHOUR_STARTTIME));
        Time endTime = Time.valueOf(LocalTime.parse(BUSINESSHOUR_ENDTIME));

        BusinessHour businessHour = null;

        String error = "";

        try {
            businessHour = service.createBusinessHour(dayOfWeek, startTime, endTime);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(businessHour);
        assertEquals(error, "These business hours already exist!");
    }

    @Test
    public void createBusinessHourWithStartTimeExist() {

        String dayOfWeek = "Monday";
        Time startTime = Time.valueOf(LocalTime.parse(BUSINESSHOUR_STARTTIME));
        Time endTime = Time.valueOf(LocalTime.parse("23:00:00"));

        BusinessHour businessHour = null;

        String error = "";

        try {
            businessHour = service.createBusinessHour(dayOfWeek, startTime, endTime);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(businessHour);
        assertEquals(error, "Business hours with this start time already exist, update end time instead");
    }

    @Test
    public void updateBusinessHourButBusinessHourExist() {

        String dayOfWeek = "Monday";
        Time startTime = Time.valueOf(LocalTime.parse(BUSINESSHOUR_STARTTIME));
        Time endTime = Time.valueOf(LocalTime.parse(BUSINESSHOUR_ENDTIME));

        // BusinessHour businessHour = new BusinessHour();
        // businessHour.setDayOfWeek(service.convertStringToDayOfWeek(dayOfWeek));
        // businessHour.setStartTime(startTime);
        // businessHour.setEndTime(endTime);

        String error = "";

        BusinessHour businessHour = null;

        try {
            businessHour = service.updateBusinessHour(BUSINESSHOUR_ID_2, dayOfWeek, startTime, endTime);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(businessHour);
        assertEquals(error, "These business hours already exist!");
    }

    @Test
    public void updateBusinessHourStartTimeAfterEndTime() {

        String dayOfWeek = "Tuesday";
        Time startTime = Time.valueOf(LocalTime.parse("10:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("09:00:00"));

        // BusinessHour businessHour = new BusinessHour();
        // businessHour.setDayOfWeek(service.convertStringToDayOfWeek(dayOfWeek));
        // businessHour.setStartTime(startTime);
        // businessHour.setEndTime(endTime);

        String error = "";

        BusinessHour businessHour = null;

        try {
            businessHour = service.updateBusinessHour(BUSINESSHOUR_ID_2, dayOfWeek, startTime, endTime);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(businessHour);
        assertEquals(error, "Start time has to be before end Time");
    }

    @Test
    public void updateBusinessHour() {

        String dayOfWeek = "Tuesday";
        Time startTime = Time.valueOf(LocalTime.parse("10:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("11:00:00"));

        // BusinessHour businessHour = new BusinessHour();
        // businessHour.setDayOfWeek(service.convertStringToDayOfWeek(dayOfWeek));
        // businessHour.setStartTime(startTime);
        // businessHour.setEndTime(endTime);

        // String error = "";

        BusinessHour businessHour = null;

        try {
            businessHour = service.updateBusinessHour(BUSINESSHOUR_ID, dayOfWeek, startTime, endTime);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(businessHour);
        assertEquals(dayOfWeek, businessHour.getDayOfWeek().toString());
        assertEquals(startTime, businessHour.getStartTime());
        assertEquals(endTime, businessHour.getEndTime());
    }

    @Test
    public void updateBusinessHourNoDay() {

        String dayOfWeek = "";
        Time startTime = Time.valueOf(LocalTime.parse("10:00:00"));
        Time endTime = Time.valueOf(LocalTime.parse("11:00:00"));

        BusinessHour businessHour = null;

        try {
            businessHour = service.updateBusinessHour(BUSINESSHOUR_ID, dayOfWeek, startTime, endTime);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(businessHour);
        assertEquals(BUSINESSHOUR_DAY, businessHour.getDayOfWeek());
        assertEquals(startTime, businessHour.getStartTime());
        assertEquals(endTime, businessHour.getEndTime());
    }

    @Test
    public void updateBusinessHourStartTime() {

        String dayOfWeek = "Thursday";
        Time startTime = null;
        Time endTime = Time.valueOf(LocalTime.parse("11:00:00"));
        Long id = 123l;

        BusinessHour businessHour = null;

        try {
            businessHour = service.updateBusinessHour(id, dayOfWeek, startTime, endTime);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(businessHour);
        assertEquals(dayOfWeek, businessHour.getDayOfWeek().toString());
        assertEquals(BUSINESSHOUR_STARTTIME, businessHour.getStartTime().toString());
        assertEquals(endTime, businessHour.getEndTime());
    }

    @Test
    public void updateBusinessHourNoEndTime() {

        String dayOfWeek = "Thursday";
        Time startTime = Time.valueOf(LocalTime.parse("11:00:00"));
        Time endTime = null;
        Long id = 123l;

        BusinessHour businessHour = null;

        try {
            businessHour = service.updateBusinessHour(id, dayOfWeek, startTime, endTime);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(businessHour);
        assertEquals(dayOfWeek, businessHour.getDayOfWeek().toString());
        assertEquals(startTime, businessHour.getStartTime());
        assertEquals(BUSINESSHOUR_ENDTIME, businessHour.getEndTime().toString());
    }

    @Test
    public void deleteBusinessHour() {
        boolean bool = false;
        boolean bool2 = false;
        Business business = businessService.getBusinessById(BUSINESS_ID);
        BusinessHour businessHour = service.getBusinessHourById(BUSINESSHOUR_ID);
        try {
            bool = service.deleteBusinessHour(businessHour, business);
        } catch (IllegalArgumentException e) {
            fail();
        }

        for (BusinessHour businessHour2 : business.getBusinessHours()) {
            if (businessHour2.equals(businessHour)) {
                bool2 = true;
            }
        }
        assertFalse(bool2);
        assertTrue(bool);
    }

    @Test
    public void getBusinessHour() {
        boolean bool = false;
        // Business business = service.getBusinessById(BUSINESS_ID);

        List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
        List<BusinessHour> businessHours2 = (List<BusinessHour>) businessHourRepository.findAll();
        try {
            businessHours = service.getAllBusinessHours();
        } catch (IllegalArgumentException e) {
            fail();
        }

        if (businessHours.size() > 0 && businessHours2.size() > 0) {
            bool = true;
        }

        assertTrue(bool);
    }

}
