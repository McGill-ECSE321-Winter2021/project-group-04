package ca.mcgill.ecse321.projectgroup04.service;

import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup04.model.Car;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import ca.mcgill.ecse321.projectgroup04.dao.CarRepository;

@ExtendWith(MockitoExtension.class)
public class TestCarService {

	@Mock
	private CarRepository carRepository;

	@InjectMocks
	private CarService service;

	private static final String model = "civic";
	private static final String year = "2001";
	private static final String color = "orange";
	private static final Long carId = 1234l;

	@BeforeEach
	public void setMockOutput() {
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(carRepository.save(any(Car.class))).thenAnswer(returnParameterAsAnswer);

		lenient().when(carRepository.findByCarId(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(model)) {

				Car car = new Car();
				car.setCarId(carId);
				car.setColor(color);
				car.setModel(model);
				car.setYear(year);

				return car;
			}
			return null;
		});

	}

	@Test
	public void testCreateCar() {
		String model = "civic";
		String year = "2001";
		String color = "orange";

		Car car = null;
		try {
			car = service.createCar(model, year, color);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(car);
		assertEquals(color, car.getColor());
		assertEquals(model, car.getModel());
		assertEquals(year, car.getYear());
	}

	@Test
	public void testCreateCarWithNoModel() {
		String model = null;
		String year = "2001";
		String color = "orange";

		String error = null;

		Car car = null;
		try {
			car = service.createCar(model, year, color);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(car);
		assertEquals(error, "Model can't be empty");
	}

	@Test
	public void testCreateCarWithNoYear() {
		String model = "civic";
		String year = null;
		String color = "orange";

		String error = null;

		Car car = null;
		try {
			car = service.createCar(model, year, color);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(car);
		assertEquals(error, "Year can't be empty");
	}

	@Test
	public void testCreateCarWithNoColor() {
		String model = "civic";
		String year = "2001";
		String color = null;

		String error = null;

		Car car = null;
		try {
			car = service.createCar(model, year, color);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(car);
		assertEquals(error, "Color can't be empty");
	}

	@Test
	public void testDeleteCar() {
		String model = "civic";
		String year = "2001";
		String color = "orange";
		Long carId = 1234l;

		Car car = new Car();
		car.setColor(color);
		car.setModel(model);
		car.setYear(year);
		car.setCarId(carId);
		try {
			car = service.deleteCar(car);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNull(car);
	}
}
