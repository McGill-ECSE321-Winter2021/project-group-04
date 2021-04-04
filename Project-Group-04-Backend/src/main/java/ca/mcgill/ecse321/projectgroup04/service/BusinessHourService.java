package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*;
import ca.mcgill.ecse321.projectgroup04.model.*;
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour.DayOfWeek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessHourService {
    @Autowired
    private BusinessHourRepository businessHourRepository;
    @Autowired
    private BusinessRepository businessRepository;

    @Transactional
    public BusinessHour createBusinessHour(String aDayOfWeek, Time aStartTime, Time aEndTime) {

        for (BusinessHour businessHour : getAllBusinessHours()) {
            if (businessHour.getDayOfWeek().equals(convertStringToDayOfWeek(aDayOfWeek))) {
                if (businessHour.getStartTime().equals(aStartTime)) {
                    if (businessHour.getEndTime().equals(aEndTime)) {
                        throw new IllegalArgumentException("These business hours already exist!");
                    }
                    throw new IllegalArgumentException(
                            "Business hours with this start time already exist, update end time instead");
                }
            }
        }

        if (aStartTime == null) {
            throw new IllegalArgumentException("Start time cannot be null");
        }

        if (aEndTime == null) {
            throw new IllegalArgumentException("End time cannot be null");
        }

        if (aStartTime.after(aEndTime)) {
            throw new IllegalArgumentException("Start time has to be before end Time");
        }

        if (aDayOfWeek == null || aDayOfWeek.equals("")) {
            throw new IllegalArgumentException("Day of the week cannot be null");
        }

        if (aStartTime.equals(aEndTime)) {
            throw new IllegalArgumentException("Business hour start time cannot equal to end time");
        }

        BusinessHour businessHour = new BusinessHour();
        businessHour.setDayOfWeek(convertStringToDayOfWeek(aDayOfWeek));
        businessHour.setEndTime(aEndTime);
        businessHour.setStartTime(aStartTime);
        businessHourRepository.save(businessHour);

        // // TODO check this!!!!
        // Business business = businessService.getBusiness().get(0);
        // Business business = businessService.getBusinessByName("AutoRepairShop");
        // business.setBusinessHours(getAllBusinessHours());
        // businessRepository.save(business);

        return businessHour;
    }

    public BusinessHour updateBusinessHour(Long id, String dayOfWeek, Time startTime, Time endTime) {

        BusinessHour tempBusinessHour = getBusinessHourById(id);
        for (BusinessHour businessHour : getAllBusinessHours()) {
            if (businessHour.getDayOfWeek().equals(convertStringToDayOfWeek(dayOfWeek))) {
                if (businessHour.getStartTime().equals(startTime)) {
                    if (businessHour.getEndTime().equals(endTime)) {
                        throw new IllegalArgumentException("These business hours already exist!");
                    }
                }
            }
        }

        Boolean dayBool = true;
        Boolean startTimeBool = true;
        Boolean endTimeBool = true;

        if (startTime == null) {
            startTimeBool = false;

        }

        if (endTime == null) {
            endTimeBool = false;
        }

        if (startTimeBool && endTimeBool) {
            if (startTime.after(endTime)) {
                throw new IllegalArgumentException("Start time has to be before end Time");
            }
        }

        if (dayOfWeek == null || dayOfWeek.equals("")) {
            dayBool = false;
        }

        if (dayBool)
            tempBusinessHour.setDayOfWeek(convertStringToDayOfWeek(dayOfWeek));
        if (startTimeBool)
            tempBusinessHour.setStartTime(startTime);
        if (endTimeBool)
            tempBusinessHour.setEndTime(endTime);
        businessHourRepository.save(tempBusinessHour);
        return tempBusinessHour;
    }

    public Boolean deleteBusinessHour(BusinessHour businessHour, Business business) {
        // List<Business> businesses = getBusiness();
        // Business business = getBusinessById(businessId);
        List<BusinessHour> businessHours = business.getBusinessHours();
        businessHours.remove(businessHour);
        business.setBusinessHours(businessHours);
        businessRepository.save(business);
        businessHourRepository.delete(businessHour);
        return true;
    }

    public Boolean deleteAllBusinessHours(Business business) {
        List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
        business.setBusinessHours(businessHours);
        businessRepository.save(business);
        businessHourRepository.deleteAll();
        return true;
    }

    @Transactional
    public BusinessHour getBusinessHourById(Long businessHourId) {
        return businessHourRepository.findBusinessHourByHourId(businessHourId);
    }

    @Transactional
    public List<BusinessHour> getAllBusinessHours() {
        return (List<BusinessHour>) businessHourRepository.findAll();
    }

    public DayOfWeek convertStringToDayOfWeek(String day) {
        if (day == null) {
            throw new IllegalArgumentException("There is no such day of the week!");
        }

        DayOfWeek dayOfWeek = null;

        if (day.equals("Monday")) {
            dayOfWeek = DayOfWeek.Monday;
        } else if (day.equals("Tuesday")) {
            dayOfWeek = DayOfWeek.Tuesday;
        } else if (day.equals("Wednesday")) {
            dayOfWeek = DayOfWeek.Wednesday;
        } else if (day.equals("Thursday")) {
            dayOfWeek = DayOfWeek.Thursday;
        } else if (day.equals("Friday")) {
            dayOfWeek = DayOfWeek.Friday;
        } else if (day.equals("Saturday")) {
            dayOfWeek = DayOfWeek.Saturday;
        } else if (day.equals("Sunday")) {
            dayOfWeek = DayOfWeek.Sunday;
        }
        return dayOfWeek;
    }
}
