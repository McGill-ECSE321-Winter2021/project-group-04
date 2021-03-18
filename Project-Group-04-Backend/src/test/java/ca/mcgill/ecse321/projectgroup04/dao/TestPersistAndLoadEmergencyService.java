package ca.mcgill.ecse321.projectgroup04.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup04.model.EmergencyService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistAndLoadEmergencyService {

    @Autowired
    private EmergencyServiceRepository emergencyServiceRepository;

    @AfterEach
    public void clearDataBase() {
        emergencyServiceRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testPersistAndLoadEmergencyService() {
        String location = "Montreal";
        String name = "Towing";
        int price = 10;
        EmergencyService emergencyService = new EmergencyService();
        emergencyService.setLocation(location);
        emergencyService.setName(name);
        emergencyService.setPrice(price);
        emergencyServiceRepository.save(emergencyService);
        Long emergencyServiceId = emergencyService.getServiceId();

        emergencyService = null;
        emergencyService = emergencyServiceRepository.findEmergencyServiceByServiceId(emergencyServiceId);
        assertNotNull(emergencyService);
        assertEquals(name, emergencyService.getName());
        assertEquals(emergencyServiceId, emergencyService.getServiceId());
        assertEquals(location, emergencyService.getLocation());
        assertEquals(price, emergencyService.getPrice());
    }
}
