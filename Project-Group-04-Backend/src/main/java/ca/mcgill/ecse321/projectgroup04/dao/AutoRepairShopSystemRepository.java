package ca.mcgill.ecse321.projectgroup04.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup04.model.AutoRepairShop;

public interface AutoRepairShopSystemRepository extends CrudRepository<AutoRepairShop, String> {

    AutoRepairShop findAutoRepairShopByName(String name);

}
