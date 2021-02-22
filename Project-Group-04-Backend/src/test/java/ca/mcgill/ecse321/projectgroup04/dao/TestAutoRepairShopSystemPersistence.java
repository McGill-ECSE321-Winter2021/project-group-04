package ca.mcgill.ecse321.projectgroup04.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup04.model.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestAutoRepairShopSystemPersistence {
    
    @Autowired
    private AdministrativeAssistantRepository administrativeAssistantRepository;
    private AppointmentReminderRepository appointmentReminderRepository;
    private AppointmentRepository appointmentRepository;
    private BookableServiceRepository bookableServiceRepository;
    private TimeSlotRepository timeSlotRepository;
    private TechnicianRepository technicianRepository;
    private GarageTechnicianRepository garageTechnicianRepository;
    

    @AfterEach
	public void clearDatabase() {
        appointmentRepository.deleteAll();
        appointmentReminderRepository.deleteAll();
        administrativeAssistantRepository.deleteAll();
        bookableServiceRepository.deleteAll();
        technicianRepository.deleteAll();
        timeSlotRepository.deleteAll();

    }
    @Test
    public void testPersistAndLoadAdministrativeAssistant(){
        String  id = "TestAdministrativeAssistance";
        String password = "TestAdministrativePassword";
        AdministrativeAssistant ad = new AdministrativeAssistant();
        ad.setUserID(id);
        ad.setPassword(password);
        administrativeAssistantRepository.save(ad);

        ad = null;
        ad = administrativeAssistantRepository.findAdminstrativeAssistantByUserID(id);
        assertNotNull(ad);
        assertEquals(id, ad.getUserID());
    }

    @Test
    public void testPersistAndLoadBookableService(){
        String id = "1234";
        int duration = 30;
        String name = "wash";
        int price = 10;
        BookableService bs = new BookableService();
        bs.setDuration(duration);
        bs.setName(name);
        bs.setServiceID(id);
        bs.setPrice(price);
        bookableServiceRepository.save(bs);

        bs = null;
        bs = bookableServiceRepository.findBookableServiceByServiceID(id);
        assertNotNull(bs);
        assertEquals(name, bs.getName());
        assertEquals(id, bs.getServiceID());
        assertEquals(duration, bs.getDuration());
        assertEquals(price, bs.getPrice());
    }
    @Test
    public void testPersistAndLoadGarageTechnician(){
        String name = "TestGarageTechnician";
        String id = "9876";
        GarageTechnician gt = new GarageTechnician();
        gt.setName(name);
        gt.setTechnicianID(id);
        garageTechnicianRepository.save(gt);

        gt = null;
        gt = garageTechnicianRepository.findGarageTechnician(id);
        assertNotNull(gt);
        assertEquals(name, gt.getTechnicianID());
        assertEquals(id, gt.getTechnicianID());
    }


    // @Test
    // public void testPersistAndLoadTimeSlot(){
    //     Time startTime = Time.valueOf("10:00");
    //     Time endTime = Time.valueOf("11:00");
    //     Date startDate = Date.valueOf("2021-03-19");
    //     Date endDate = Date.valueOf("2021-03-19");
    //     String 
    //     TimeSlot ts = new TimeSlot();
    //     ts.setStartTime(startTime);
    //     ts.setEndtTime(endTime);
    //     ts.setStartDate(startDate);
    //     ts.setEndDate(endDate);
    //     ts.setGarageSpot();
    // }
}
