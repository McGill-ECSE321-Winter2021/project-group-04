package ca.mcgill.ecse321.projectgroup04.dao;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup04.model.GarageTechnician;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistAndLoadGarageTechnician {

    @Autowired
    private GarageTechnicianRepository garageTechnicianRepository;

    @AfterEach
    public void clearDataBase() {
        garageTechnicianRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testPersistAndLoadGarageTechnician() {
        String techName = "cizo";
        GarageTechnician garageTechnician = new GarageTechnician();
        garageTechnician.setName(techName);
        garageTechnicianRepository.save(garageTechnician);
        Long garageTechnicianId = garageTechnician.getTechnicianId();
        garageTechnician = null;
        garageTechnician = garageTechnicianRepository.findGarageTechnicianByTechnicianId(garageTechnicianId);
        assertNotNull(garageTechnician);
        assertEquals(garageTechnicianId, garageTechnician.getTechnicianId());
        assertEquals(techName, garageTechnician.getName());

    }
}
