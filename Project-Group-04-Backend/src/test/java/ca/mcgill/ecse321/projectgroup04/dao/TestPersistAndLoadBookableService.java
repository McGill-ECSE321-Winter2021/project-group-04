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

import ca.mcgill.ecse321.projectgroup04.model.BookableService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistAndLoadBookableService {

    @Autowired
    private BookableServiceRepository bookableServiceRepository;

    @AfterEach
    public void clearDatabase() {
        bookableServiceRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testPersistAndLoadBookableService() {
        int duration = 30;
        String name = "wash";
        int price = 10;
        BookableService bookableService = new BookableService();
        bookableService.setDuration(duration);
        bookableService.setName(name);
        bookableService.setPrice(price);
        bookableServiceRepository.save(bookableService);
        Long id = bookableService.getServiceId();

        bookableService = null;
        bookableService = bookableServiceRepository.findBookableServiceByServiceId(id);
        assertNotNull(bookableService);
        assertEquals(name, bookableService.getName());
        assertEquals(id, bookableService.getServiceId());
        assertEquals(duration, bookableService.getDuration());
        assertEquals(price, bookableService.getPrice());

    }
}
