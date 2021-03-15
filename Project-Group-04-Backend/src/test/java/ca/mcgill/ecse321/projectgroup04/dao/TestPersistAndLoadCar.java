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

import ca.mcgill.ecse321.projectgroup04.model.Car;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistAndLoadCar {

    @Autowired
    private CarRepository carRepository;

    @AfterEach
    public void clearDataBase() {
        carRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testPersistAndLoadCar() {
        Car car = new Car();
        String model = "testModel";
        String year = "2021";
        String color = "blue";

        car.setColor(color);
        car.setModel(model);
        car.setYear(year);

        carRepository.save(car);
        Long carId = car.getCarId();

        car = null;
        car = carRepository.findByCarId(carId);

        assertNotNull(car);
        assertEquals(carId, car.getCarId());
        assertEquals(color, car.getColor());
        assertEquals(model, car.getModel());
        assertEquals(year, car.getYear());

    }
}
