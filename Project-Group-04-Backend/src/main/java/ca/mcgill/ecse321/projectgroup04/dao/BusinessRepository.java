package ca.mcgill.ecse321.projectgroup04.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup04.model.AutoRepairShop;
import ca.mcgill.ecse321.projectgroup04.model.Business;
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour;
import ca.mcgill.ecse321.projectgroup04.model.TimeSlot;

public interface BusinessRepository extends CrudRepository<Business, Long> {

    Business findBusinessById(Long Id);

    Business findBusinessByName(String name);

    Business findBusinessByEmailAddress(String emailAddress);

    Business findBusinessByPhoneNumber(String phoneNumber);

    Business findBusinessByAutoRepairShop(AutoRepairShop autoRepairShop);

    Business findBusinessByBusinessHours(List<BusinessHour> businessHours);

    Business findBusinessByTimeSlot(List<TimeSlot> timeSlot);
}
