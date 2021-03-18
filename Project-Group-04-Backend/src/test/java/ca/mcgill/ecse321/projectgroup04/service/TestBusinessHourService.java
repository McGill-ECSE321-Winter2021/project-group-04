package ca.mcgill.ecse321.projectgroup04.service;

import static org.mockito.Mockito.lenient;

import java.sql.Time;
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
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour;
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour.DayOfWeek;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class TestBusinessHourService {

    @Mock
    private BusinessHourRepository businessHourRepository;

    @InjectMocks
    private AutoRepairShopSystemService service;

    private static final Long BUSINESSHOUR_ID = 123l;
    private static final DayOfWeek BUSINESSHOUR_DAY = DayOfWeek.Monday;
    private static final String BUSINESSHOUR_STARTTIME = "09:00:00";
    private static final String BUSINESSHOUR_ENDTIME = "17:00:00";

    private static final Long BUSINESSHOUR_ID_2 = 456l;
    private static final DayOfWeek BUSINESSHOUR_DAY_2 = DayOfWeek.Tuesday;
    private static final String BUSINESSHOUR_STARTTIME_2 = "08:00:00";
    private static final String BUSINESSHOUR_ENDTIME_2 = "19:00:00";

    @BeforeEach
    public void setMockOutput() {
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(businessHourRepository.save(any(BusinessHour.class))).thenAnswer(returnParameterAsAnswer);
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
                    return null;
                });
        lenient().when(businessHourRepository.findBusinessHourByHourId(anyLong()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (invocation.getArgument(0).equals(BUSINESSHOUR_ID)) {

                        BusinessHour businessHour = new BusinessHour();
                        businessHour.setDayOfWeek(BUSINESSHOUR_DAY_2);
                        businessHour.setStartTime(Time.valueOf(LocalTime.parse(BUSINESSHOUR_STARTTIME_2)));
                        businessHour.setEndTime(Time.valueOf(LocalTime.parse(BUSINESSHOUR_ENDTIME_2)));
                        businessHour.setHourId(BUSINESSHOUR_ID_2);

                        return businessHour;
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
        assertEquals(BUSINESSHOUR_DAY_2, businessHour.getDayOfWeek());
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
        assertEquals(BUSINESSHOUR_STARTTIME_2, businessHour.getStartTime().toString());
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
        assertEquals(BUSINESSHOUR_ENDTIME_2, businessHour.getEndTime().toString());
    }

    @Test
    public void deleteBusinessHour() {
        boolean bool = false;
        BusinessHour businessHour = service.getBusinessHourById(BUSINESSHOUR_ID);
        try {
            bool = service.deleteBusinessHour(businessHour);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertTrue(bool);
    }

}
