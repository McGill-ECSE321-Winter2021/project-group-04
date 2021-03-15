package ca.mcgill.ecse321.projectgroup04.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup04.model.TimeSlot;
import ca.mcgill.ecse321.projectgroup04.model.Business;
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistAndLoadBusiness {

    @Autowired
    private TimeSlotRepository timeSlotRepository;
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private BusinessHourRepository businessHourRepository;

    @AfterEach
    public void clearDataBase() {
        businessRepository.deleteAll();
        businessHourRepository.deleteAll();
        timeSlotRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testPersistAndLoadBusiness() {
        String name = "cizo";
        String adress = "mohammadStreet";
        String phoneNumber = "0602010201";
        String emailAdress = "yasmineMatta";
        Business business = new Business();
        business.setAddress(adress);
        business.setEmailAddress(emailAdress);
        business.setName(name);
        business.setPhoneNumber(phoneNumber);

        BusinessHour businessHour = new BusinessHour();
        ArrayList<BusinessHour> businessHours = new ArrayList<BusinessHour>();
        businessHours.add(businessHour);
        businessHourRepository.save(businessHour);
        Long bushId = businessHour.getHourId();

        TimeSlot timeSlot = new TimeSlot();
        ArrayList<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
        timeSlots.add(timeSlot);
        timeSlotRepository.save(timeSlot);
        Long tsId = timeSlot.getTimeSlotId();

        business.setBusinessHours(businessHours);
        business.setRegular(timeSlots);

        businessRepository.save(business);
        Long businessId = business.getId();

        business = null;

        business = businessRepository.findBusinessById(businessId);

        assertNotNull(business);
        assertEquals(name, business.getName());
        assertEquals(adress, business.getAddress());
        assertEquals(phoneNumber, business.getPhoneNumber());
        assertEquals(emailAdress, business.getEmailAddress());
        assertEquals(businessId, business.getId());
        assertEquals(bushId, business.getBusinessHours().get(0).getHourId());
        assertEquals(tsId, business.getRegular().get(0).getTimeSlotId());

    }
}
