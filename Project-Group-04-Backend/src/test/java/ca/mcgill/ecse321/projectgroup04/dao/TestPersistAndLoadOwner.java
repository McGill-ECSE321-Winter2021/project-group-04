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

import ca.mcgill.ecse321.projectgroup04.model.Owner;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistAndLoadOwner {
    @Autowired
    private OwnerRepository ownerRepository;

    @AfterEach
    public void clearDataBase() {
        ownerRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testPersistAndLoadOwner() {
        String userId = "ooo";
        String password = "TestOwnerPassword";
        Owner owner = new Owner();
        owner.setUserId(userId);
        owner.setPassword(password);
        ownerRepository.save(owner);
        Long owId = owner.getId();

        owner = null;
        owner = ownerRepository.findOwnerById(owId);
        assertNotNull(owner);
        assertEquals(owId, owner.getId());
        assertEquals(userId, owner.getUserId());
    }
}
