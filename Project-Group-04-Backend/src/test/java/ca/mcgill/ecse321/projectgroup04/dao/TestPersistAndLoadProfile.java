package ca.mcgill.ecse321.projectgroup04.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup04.model.Profile;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistAndLoadProfile {

    @Autowired
    private ProfileRepository profileRepository;

    @AfterEach
    public void clearDataBase() {
        profileRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testPersistAndLoadProfile() {

        String address = "this is the test adress";
        String phoneNumber = "438-978-6824";
        String firstName = "FirstName";
        String lastName = "LastName";
        String zipCode = "H2X2B5";
        String email = "thisis@test.com";
        Profile profile = new Profile();
        profile.setAddressLine(address);
        profile.setEmailAddress(email);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setPhoneNumber(phoneNumber);
        profile.setZipCode(zipCode);

        profileRepository.save(profile);
        Long profileId = profile.getProfileId();

        profile = null;
        profile = profileRepository.findProfileByProfileId(profileId);

        assertNotNull(profile);
        assertEquals(profileId, profile.getProfileId());
        assertEquals(address, profile.getAddressLine());
        assertEquals(phoneNumber, profile.getPhoneNumber());
        assertEquals(firstName, profile.getFirstName());
        assertEquals(lastName, profile.getLastName());
        assertEquals(zipCode, profile.getZipCode());
        assertEquals(email, profile.getEmailAddress());

    }
}
