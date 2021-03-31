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

import ca.mcgill.ecse321.projectgroup04.dao.BusinessRepository;
import ca.mcgill.ecse321.projectgroup04.dao.TimeSlotRepository;
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse321.projectgroup04.model.Business;
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour;
import ca.mcgill.ecse321.projectgroup04.model.TimeSlot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class TestTimeSlotService {

    @Mock
    private BusinessRepository businessRepository;

    @Mock
    private TimeSlotRepository timeSlotRepository;

    @InjectMocks
    private TimeSlotService service;

    @InjectMocks
    private BusinessService businessService;

    private static final String OLD_APPOINTMENT_DATE = "2021-03-18";
    private static final String OLD_APPOINTMENT_START_TIME = "13:00:00";
    private static final String OLD_APPOINTMENT_END_TIME = "13:30:00";
    private static final Long timeSlotId = 1234l;
    private static final Integer garageSpot = 123;

    private static final String BUSINESS_NAME = "TestName";
    private static final String BUSINESS_PHONENUMBER = "438123456";
    private static final String BUSINESS_ADDRESS = "1234 MTL, Quebec";
    private static final String BUSINESS_MAIL = "testmail@mail.mcgill.ca";
    private static final Long BUSINESS_ID = 45678l;
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
        lenient().when(timeSlotRepository.findTimeSlotByTimeSlotId(anyLong()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (invocation.getArgument(0).equals(timeSlotId)) {

                        TimeSlot timeSlot = new TimeSlot();
                        timeSlot.setEndDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE)));
                        timeSlot.setStartDate(Date.valueOf(LocalDate.parse(OLD_APPOINTMENT_DATE)));
                        timeSlot.setStartTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_START_TIME)));
                        timeSlot.setEndTime(Time.valueOf(LocalTime.parse(OLD_APPOINTMENT_END_TIME)));
                        timeSlot.setGarageSpot(garageSpot);

                        return timeSlot;
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
    }

    @Test
    public void TestCreateTimeSlot() {
        Date date = Date.valueOf(LocalDate.parse("2020-03-20"));
        Time aStartTime = Time.valueOf(LocalTime.parse("08:00:00"));
        Time aEndTime = Time.valueOf(LocalTime.parse("09:00:00"));
        Integer garageSpot = 123;

        TimeSlot timeSlot = null;

        try {
            timeSlot = service.createTimeSlot(aStartTime, aEndTime, date, date, garageSpot);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(timeSlot);
        assertEquals(date, timeSlot.getStartDate());
        assertEquals(date, timeSlot.getEndDate());
        assertEquals(aStartTime, timeSlot.getStartTime());
        assertEquals(aEndTime, timeSlot.getEndTime());
        assertEquals(garageSpot, timeSlot.getGarageSpot());
    }

    @Test
    public void TestCreateTimeSlotWithoutDate() {
        Date date = null;
        Time aStartTime = Time.valueOf(LocalTime.parse("08:00:00"));
        Time aEndTime = Time.valueOf(LocalTime.parse("09:00:00"));
        Integer garageSpot = 123;

        TimeSlot timeSlot = null;

        String error = "";

        try {
            timeSlot = service.createTimeSlot(aStartTime, aEndTime, date, date, garageSpot);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(timeSlot);
        assertEquals(error, "Date cannot be null");
    }

    @Test
    public void TestCreateTimeSlotWithStartTimeNull() {
        Date date = Date.valueOf(LocalDate.parse("2020-03-20"));
        Time aStartTime = null;
        Time aEndTime = Time.valueOf(LocalTime.parse("09:00:00"));
        Integer garageSpot = 123;

        TimeSlot timeSlot = null;

        String error = "";

        try {
            timeSlot = service.createTimeSlot(aStartTime, aEndTime, date, date, garageSpot);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(timeSlot);
        assertEquals(error, "startTime cannot be null");
    }

    @Test
    public void TestCreateTimeSlotWithEndTimeNull() {
        Date date = Date.valueOf(LocalDate.parse("2020-03-20"));
        Time aStartTime = Time.valueOf(LocalTime.parse("09:00:00"));
        Time aEndTime = null;
        Integer garageSpot = 123;

        TimeSlot timeSlot = null;

        String error = "";

        try {
            timeSlot = service.createTimeSlot(aStartTime, aEndTime, date, date, garageSpot);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(timeSlot);
        assertEquals(error, "endTime cannot be null");
    }

    @Test
    public void TestCreateTimeSlotWithGarageSpotNull() {
        Date date = Date.valueOf(LocalDate.parse("2020-03-20"));
        Time aStartTime = Time.valueOf(LocalTime.parse("09:00:00"));
        Time aEndTime = Time.valueOf(LocalTime.parse("10:00:00"));
        Integer garageSpot = null;

        TimeSlot timeSlot = null;

        String error = "";

        try {
            timeSlot = service.createTimeSlot(aStartTime, aEndTime, date, date, garageSpot);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(timeSlot);
        assertEquals(error, "garageSpot cannot be null");
    }

    @Test
    public void TestCreateTimeSlotWithWrongTime() {
        Date date = Date.valueOf(LocalDate.parse("2020-03-20"));
        Time aStartTime = Time.valueOf(LocalTime.parse("09:00:00"));
        Time aEndTime = Time.valueOf(LocalTime.parse("08:00:00"));
        Integer garageSpot = 123;

        TimeSlot timeSlot = null;

        String error = "";

        try {
            timeSlot = service.createTimeSlot(aStartTime, aEndTime, date, date, garageSpot);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(timeSlot);
        assertEquals(error, "StartTime cannot be after endTime");
    }

    @Test
    public void TestDeleteTimeSlot() {
        Date date = Date.valueOf(LocalDate.parse("2020-03-20"));
        Time aStartTime = Time.valueOf(LocalTime.parse("08:00:00"));
        Time aEndTime = Time.valueOf(LocalTime.parse("09:00:00"));
        Integer garageSpot = 123;

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setEndDate(date);
        timeSlot.setEndTime(aEndTime);
        timeSlot.setStartDate(date);
        timeSlot.setStartTime(aStartTime);
        timeSlot.setGarageSpot(garageSpot);
        Business business = businessService.getBusinessById(BUSINESS_ID);

        try {
            timeSlot = service.deleteTimeSlot(timeSlot, business);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNull(timeSlot);
    }
}
