package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*;
import ca.mcgill.ecse321.projectgroup04.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Transactional
    public Car createCar(String model, String year, String color) {

        if (model == null || model == "") {
            throw new IllegalArgumentException("Model can't be empty");
        }

        if (color == null || color == "") {
            throw new IllegalArgumentException("Color can't be empty");
        }

        if (year == null || year == "") {
            throw new IllegalArgumentException("Year can't be empty");
        }
        Car car = new Car();
        car.setColor(color);
        car.setModel(model);
        car.setYear(year);
        carRepository.save(car);
        return car;
    }

    @Transactional
    public List<Car> getCarByModelAndYearAndColor(String model, String year, String color) {
        return carRepository.findCarByModelAndYearAndColor(model, year, color);
    }

    @Transactional
    public List<Car> getAllCars() {
        return (List<Car>) carRepository.findAll();
    }

    @Transactional
    public Car deleteCar(Car car) {
        carRepository.delete(car);
        car = null;
        return car;
    }

    @Transactional
    public Car getCarByCarId(Long carId) {
        Car car = carRepository.findByCarId(carId);
        if (car == null) {
            System.out.println("No car with such id exist");
            throw new IllegalArgumentException("No car with such id exist");
        }
        return car;
    }

    public Car editCar(Car car, String model, String year, String color) {
        if (model == null || model == "" || model.equals("undefined"))  {
           
            throw new IllegalArgumentException("Model can't be empty");
        }

        if (color == null || color == ""|| color.equals("undefined")) {
           

            throw new IllegalArgumentException("Color can't be empty");
        }

        if (year == null || year == "" || year.equals("undefined")) {
            

            throw new IllegalArgumentException("Year can't be empty");
        }
        car.setColor(color);
        car.setModel(model);
        car.setYear(year);
        carRepository.save(car);
        return car;

    }
}