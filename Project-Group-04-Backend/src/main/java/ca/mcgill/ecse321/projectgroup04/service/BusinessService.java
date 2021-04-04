package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*;
import ca.mcgill.ecse321.projectgroup04.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BusinessService {
    @Autowired
    private BusinessRepository businessRepository;

    @Transactional
    public Business createBusiness(String aName, String aAddress, String aPhoneNumber, String aEmailAddress,
            List<BusinessHour> aBusinessHours, List<TimeSlot> regular) {

        boolean businessExist = false;
        List<Business> businessList = getBusiness();
        if (businessList.size() == 1) {
            throw new IllegalArgumentException("Only one business can exist");
        }

        Business tempBusiness = getBusinessByName(aName);

        if (tempBusiness != null) {
            businessExist = true;
        }

        if (businessExist) {
            throw new IllegalArgumentException("Business already exists");
        }

        if (aName == null || aName == "") {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (aAddress == null || aAddress == "") {
            throw new IllegalArgumentException("Address cannot be empty");
        }

        if (aPhoneNumber == null || aPhoneNumber == "") {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        if (aEmailAddress == null || aEmailAddress == "") {
            throw new IllegalArgumentException("Email address cannot be empty");
        } else if (!aEmailAddress.contains("@")) {
            throw new IllegalArgumentException("Email Address must contain @ character");
        }
        if (aBusinessHours == null) {
            throw new IllegalArgumentException("Business Hours cannot be null");
        }
        if (regular == null) {
            throw new IllegalArgumentException("Timeslots cannot be null");
        }

        Business business = new Business();
        business.setName(aName);
        business.setAddress(aAddress);
        business.setPhoneNumber(aPhoneNumber);
        business.setEmailAddress(aEmailAddress);
        business.setBusinessHours(aBusinessHours);
        business.setRegular(regular);
        businessRepository.save(business);
        return business;
    }

    // ?? do we have to save again if we are changing the fields?
    public Business updateBusinessInformation(Long Id, String aName, String aAddress, String aPhoneNumber,
            String aEmailAddress, List<BusinessHour> aBusinessHours, List<TimeSlot> regular) {

        Business business = getBusinessById(Id);

        if (business == null) {
            throw new IllegalArgumentException("The business with this Id doesn't exist");
        }
        boolean addressBool = true;
        boolean phoneBool = true;
        boolean emailBool = true;
        boolean businessHourBool = true;
        boolean regularBool = true;

        if (aAddress == null || aAddress == "" || aAddress.equals("undefined")) {
            addressBool = false;
        }
        if (aPhoneNumber == null || aPhoneNumber == ""|| aPhoneNumber.equals("undefined")) {
            phoneBool = false;
        }
        if (aEmailAddress == null || aEmailAddress == ""|| aEmailAddress.equals("undefined")) {
            emailBool = false;
        }

        if (aBusinessHours == null) {
            businessHourBool = false;
        }
        if (regular == null) {
            regularBool = false;
        }

        if (addressBool) {
            business.setAddress(aAddress);
        }
        if (phoneBool) {
            business.setPhoneNumber(aPhoneNumber);
        }
        if (emailBool) {
            if (!aEmailAddress.contains("@")) {
                throw new IllegalArgumentException("Email Address must contain @ character");
            }
            business.setEmailAddress(aEmailAddress);
        }
        if (businessHourBool) {
            business.setBusinessHours(aBusinessHours);
        }
        if (regularBool) {
            business.setRegular(regular);
        }
        businessRepository.save(business); // ????
        return business;
    }

    @Transactional
    public Business getBusinessById(Long businessId) {
        return businessRepository.findBusinessById(businessId);
    }

    @Transactional
    public List<Business> getBusiness() {
        if (businessRepository.findAll() != null) {
            return (List<Business>) businessRepository.findAll();
        } else {
            return null;
        }
    }

    @Transactional
    public Business getBusinessByName(String name) {
        return businessRepository.findBusinessByName(name);
    }

    public Boolean deleteBusiness(Business business) {
        businessRepository.delete(business);
        return true;
    }
}