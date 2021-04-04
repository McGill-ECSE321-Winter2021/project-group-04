package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*;
import ca.mcgill.ecse321.projectgroup04.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Profile createProfile(String aAddressLine, String aPhoneNumber, String aFirstName, String aLastName,
            String aZipCode, String aEmailAddress) {
        if (aAddressLine == null || aAddressLine == "" || aAddressLine.equals("undefined")) {
            throw new IllegalArgumentException("Address Line can't be null or empty");
        }
        if (aPhoneNumber == null || aPhoneNumber == ""|| aPhoneNumber.equals("undefined")) {
            throw new IllegalArgumentException("Phone Number can't be null or empty");
        }
        if (aPhoneNumber.length() < 10 || aPhoneNumber.length() > 10) {
            throw new IllegalArgumentException("Phone Number must be 10 characters long");
        }
        if (aFirstName == null || aFirstName == ""|| aFirstName.equals("undefined")) {
            throw new IllegalArgumentException("First Name can't be null or empty");
        }
        if (aLastName == null || aLastName == ""|| aLastName.equals("undefined")) {
            throw new IllegalArgumentException("Last Name can't be null or empty");
        }
        if (aZipCode == null || aZipCode == ""|| aZipCode.equals("undefined")) {
            throw new IllegalArgumentException("Zip Code can't be null or empty");
        }
        if (aZipCode.length() < 6 || aZipCode.length() > 6) {
            throw new IllegalArgumentException("Zip Code must be 6 characters long");
        }
        if (aEmailAddress == null || aEmailAddress == ""|| aEmailAddress.equals("undefined")) {
            throw new IllegalArgumentException("Email Address can't be null or empty");
        }
        if (!aEmailAddress.contains("@")) {
            throw new IllegalArgumentException("Email Address must contain @ character");
        }
        Profile profile = new Profile();
        profile.setAddressLine(aAddressLine);
        profile.setEmailAddress(aEmailAddress);
        profile.setFirstName(aFirstName);
        profile.setLastName(aLastName);
        profile.setPhoneNumber(aPhoneNumber);
        profile.setZipCode(aZipCode);
        profileRepository.save(profile);
        return profile;
    }

    @Transactional
    public Profile getProfile(Long aProfileId) {
        Profile profile = profileRepository.findProfileByProfileId(aProfileId);
        if (profile != null) {
            return profile;
        } else {
            throw new IllegalArgumentException("No profile with such ID exist!");
        }
    }

    @Transactional
    public List<Profile> getAllProfiles() {
        return (List<Profile>) profileRepository.findAll();
    }

    @Transactional
    public Profile getProfileByFirstAndLast(String firstName, String lastName) {

        for (Profile profile : profileRepository.findAll()) {
            if (profile.getFirstName().equals(firstName) && profile.getLastName().equals(lastName)) {
                return profile;
            }
        }
        throw new IllegalArgumentException("No Profile with such First Name and Last Name");
    }

    @Transactional
    public Profile getProfileByUserId(String userId) {
        if (userId == "" || userId == null) {
            throw new IllegalArgumentException("userId can't be null or empty");
        }
        Customer customer = customerRepository.findCustomerByUserId(userId);
        if (customer == null) {
            throw new IllegalArgumentException("No customer with such userId exist");
        }
        Profile profile = customer.getCustomerProfile();
        if (profile == null) {
            throw new IllegalArgumentException("This customer does not have a valid profile");
        }

        return profile;
    }

    public Profile editProfile(Long profileId, String firstName, String lastName, String emailAddress,
            String phoneNumber, String addressLine, String zipCode) {
        Profile profile = getProfile(profileId);
        if (addressLine == null || addressLine == "") {
            throw new IllegalArgumentException("Address Line can't be null or empty");
        }
        if (phoneNumber == null || phoneNumber == "") {
            throw new IllegalArgumentException("Phone Number can't be null or empty");
        }
        if (phoneNumber.length() < 10 || phoneNumber.length() > 10) {
            throw new IllegalArgumentException("Phone Number must be 10 characters long");
        }
        if (firstName == null || firstName == "") {
            throw new IllegalArgumentException("First Name can't be null or empty");
        }
        if (lastName == null || lastName == "") {
            throw new IllegalArgumentException("Last Name can't be null or empty");
        }
        if (zipCode == null || zipCode == "") {
            throw new IllegalArgumentException("Zip Code can't be null or empty");
        }
        if (zipCode.length() < 6 || zipCode.length() > 6) {
            throw new IllegalArgumentException("Zip Code must be 6 characters long");
        }
        if (emailAddress == null || emailAddress == "") {
            throw new IllegalArgumentException("Email Address can't be null or empty");
        }
        if (!emailAddress.contains("@")) {
            throw new IllegalArgumentException("Email Address must contain @ character");
        }
        profile.setAddressLine(addressLine);
        profile.setEmailAddress(emailAddress);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setPhoneNumber(phoneNumber);
        profile.setZipCode(zipCode);
        profileRepository.save(profile);
        return profile;
    }
}
