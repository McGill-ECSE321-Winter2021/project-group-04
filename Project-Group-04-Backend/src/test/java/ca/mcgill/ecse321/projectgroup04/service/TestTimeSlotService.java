package ca.mcgill.ecse321.projectgroup04.service;

import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;


import ca.mcgill.ecse321.projectgroup04.dao.TimeSlotRepository;
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
	private TimeSlotRepository timeSlotRepository;
	
	 @InjectMocks
	 private AutoRepairShopSystemService service;
	
    private static final String OLD_APPOINTMENT_DATE = "2021-03-18";
    private static final String OLD_APPOINTMENT_START_TIME = "13:00:00";
    private static final String OLD_APPOINTMENT_END_TIME = "13:30:00";
    private static final Long timeSlotId = 1234l;
    private static final Integer garageSpot = 123;
    
    
    @BeforeEach
    public void setMockOutput() {
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(timeSlotRepository.save(any(TimeSlot.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(timeSlotRepository.findTimeSlotByTimeSlotId(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
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
        }catch (IllegalArgumentException e){
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
        }catch (IllegalArgumentException e){
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
        }catch (IllegalArgumentException e){
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
        }catch (IllegalArgumentException e){
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
        }catch (IllegalArgumentException e){
        	error = e.getMessage();
        }
        
        assertNull(timeSlot);
        assertEquals(error, "garageSpot cannot be null");
    }
    
//    @Test
//    public void TestCreateTimeSlotThatAlreadyExists() {
//        Date date = Date.valueOf(LocalDate.parse("2020-03-20"));
//        Time aStartTime = Time.valueOf(LocalTime.parse("08:00:00"));
//        Time aEndTime = Time.valueOf(LocalTime.parse("09:00:00"));
//        Integer garageSpot = 123;
//        
//        TimeSlot timeSlot = null;
//        
//        String error = "";
//        
//        try {
//        	timeSlot = service.createTimeSlot(aStartTime, aEndTime, date, date, garageSpot);
//        }catch (IllegalArgumentException e){
//        	error = e.getMessage();
//        }
//        
//        assertNull(timeSlot);
//        assertEquals(error, "This TimeSlot already exists");
//    }
    
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
        }catch (IllegalArgumentException e){
        	error = e.getMessage();
        }
        
        assertNull(timeSlot);
        assertEquals(error, "StartTime cannot be after endTime");
    }
}


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    