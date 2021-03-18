package ca.mcgill.ecse321.projectgroup04.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup04.model.Car;


public interface CarRepository extends CrudRepository<Car, Long>{
    Car findByCarId(Long carId);

	List<Car> findCarByModelAndYearAndColor(String model, String year, String color);
}
