package ca.mcgill.ecse321.projectgroup04.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup04.dto.*;
import ca.mcgill.ecse321.projectgroup04.model.*;
import ca.mcgill.ecse321.projectgroup04.service.CarService;
import ca.mcgill.ecse321.projectgroup04.service.CustomerService;


@CrossOrigin(origins = "*")
@RestController
public class CarController {

	@Autowired
	private CarService carService;

    @Autowired
	private CustomerService customerService;

    private CarDto convertToDto(Car car) {
		if (car == null) {
			throw new IllegalArgumentException("There is no such Car!");
		}
		CarDto carDto = new CarDto(car.getModel(), car.getColor(), car.getYear());
		carDto.setCarId(car.getCarId());
		return carDto;
	}

    /////////////////////////////////////// CAR///////////////////////////

	@GetMapping(value = { "/cars", "/cars/" })
	public List<CarDto> getCars() {
		List<CarDto> carDtos = new ArrayList<>();
		for (Car car : carService.getAllCars()) {
			carDtos.add(convertToDto(car));
		}
		return carDtos;
	}

	@GetMapping(value = { "/cars/{model}/{year}/{color}", "/cars/{model}/{year}/{color}/" })
	public List<CarDto> getCarByModelAndYearAndColor(@PathVariable String model, @PathVariable String year,

			@PathVariable String color) throws IllegalArgumentException {
		List<Car> car = carService.getCarByModelAndYearAndColor(model, year, color);

		if (model == null || year == null || color == null) {
			throw new IllegalArgumentException("No car with such model, year or color!");
		}
		return convertListToDto(car);
	}

	private List<CarDto> convertListToDto(List<Car> car) {
		List<CarDto> cardto = new ArrayList<CarDto>();
		for (Car car1 : car) {
			cardto.add(convertToDto(car1));
		}
		return cardto;
	}

	@PostMapping(value = { "/add/car", "/add/car/" })
	public CarDto createCar(@RequestParam String model, @RequestParam String year, @RequestParam String color)
			throws IllegalArgumentException {
		Car car1 = carService.createCar(model, year, color);
		CarDto carDto = convertToDto(car1);
		return carDto;
	}

	@PatchMapping(value = { "/edit/car/{carId}", "/edit/car/{carId}/" })
	public ResponseEntity<?> editCar(@PathVariable("carId") Long carId, @RequestParam String model,
			@RequestParam String year, @RequestParam String color) throws IllegalArgumentException {
		Car car = null;
		try {
			car = carService.getCarByCarId(carId);
			car = carService.editCar(car, model, year, color);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(convertToDto(car), HttpStatus.CREATED);
	}

	@GetMapping(value = { "/car/{userId}", "/car/{userId}/" })
	public CarDto getCustomerCar(@PathVariable("userId") String userId) throws IllegalArgumentException {
		Car car = customerService.getCustomerCar(userId);
		return convertToDto(car);
	}
}