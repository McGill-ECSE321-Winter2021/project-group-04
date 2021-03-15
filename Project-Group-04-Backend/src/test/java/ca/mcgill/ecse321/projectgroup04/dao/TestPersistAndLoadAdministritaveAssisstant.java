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

import ca.mcgill.ecse321.projectgroup04.model.AdministrativeAssistant;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistAndLoadAdministritaveAssisstant {

    @Autowired
    private AdministrativeAssistantRepository administrativeAssistantRepository;

    @AfterEach
    public void clearDatabase() {
        administrativeAssistantRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testPersistAndLoadAdministrativeAssistant() {
        String userId = "qq";
        String password = "TestAdministrativePassword";
        AdministrativeAssistant admin = new AdministrativeAssistant();
        admin.setUserId(userId);
        admin.setPassword(password);
        administrativeAssistantRepository.save(admin);
        Long id = admin.getId();
        System.out.print(admin.getId());
        System.out.println(admin.getId().getClass());
        admin = null;
        admin = administrativeAssistantRepository.findAdministrativeAssistantById(id);
        assertNotNull(admin);
        assertEquals(id, admin.getId());

    }

}
