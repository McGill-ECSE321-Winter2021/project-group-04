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

import ca.mcgill.ecse321.projectgroup04.model.FieldTechnician;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistAndLoadFieldTechnician {

    @Autowired
    private FieldTechnicianRepository fieldTechnicianRepository;

    @AfterEach
    public void clearDataBase() {
        fieldTechnicianRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testPersistAndLoadFieldTechnician() {
        String fieldTechName = "cizor";
        FieldTechnician fieldTech = new FieldTechnician();
        fieldTech.setName(fieldTechName);
        fieldTechnicianRepository.save(fieldTech);
        Long fieldTechId = fieldTech.getTechnicianId();

        fieldTech = null;

        fieldTech = fieldTechnicianRepository.findFieldTechnicianByTechnicianId(fieldTechId);

        assertNotNull(fieldTech);
        assertEquals(fieldTechName, fieldTech.getName());

    }
}
