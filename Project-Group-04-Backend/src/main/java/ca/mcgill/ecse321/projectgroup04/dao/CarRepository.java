package ca.mcgill.ecse321.projectgroup04.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup04.model.Car;


public interface CarRepository extends CrudRepository<Car, String>{
    Car findByCarID(String carID);
}
