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
import ca.mcgill.ecse321.projectgroup04.service.ProfileService;


@CrossOrigin(origins = "*")
@RestController
public class ProfileController {

	@Autowired
	private ProfileService profileService;


    private ProfileDto convertToDto(Profile profile) {
		if (profile == null) {
			throw new IllegalArgumentException("There is no such Profile!");
		}
		ProfileDto profileDto = new ProfileDto(profile.getAddressLine(), profile.getPhoneNumber(),
				profile.getFirstName(), profile.getLastName(), profile.getZipCode(), profile.getEmailAddress());
		profileDto.setProfileId(profile.getProfileId());
		return profileDto;

	}


	@GetMapping(value = { "/profiles", "/profiles/" })
	public List<ProfileDto> getAllProfiles() {
		List<ProfileDto> profileDtos = new ArrayList<>();
		for (Profile profile : profileService.getAllProfiles()) {
			profileDtos.add(convertToDto(profile));
		}
		return profileDtos;
	}

	@GetMapping(value = { "/profile/{Id}", "/profile/{Id}/" })
	public ProfileDto getProfileById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
		return convertToDto(profileService.getProfile(Id));
	}

	@GetMapping(value = { "/profiles/{userId}", "/profiles/{userId}/" })
	public ProfileDto getProfileByUserId(@PathVariable("userId") String userId) throws IllegalArgumentException {
		return convertToDto(profileService.getProfileByUserId(userId));
	}
    @PostMapping(value = { "/create/profile/{first}/{last}", "/create/profile/{first}/{last}/" })
	public ProfileDto createProfile(@PathVariable("first") String firstName, @PathVariable("last") String lastName,
			@RequestParam(name = "Email Address") String emailAddress,
			@RequestParam(name = "Phone Number") String phoneNumber,
			@RequestParam(name = "Address Line") String addressLine, @RequestParam(name = "Zip Code") String zipCode)
			throws IllegalArgumentException {
		Profile profile = profileService.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode,
				emailAddress);
		return convertToDto(profile);
	}

    @GetMapping(value = { "/profile/{first}/{last}", "/profile/{first}/{last}/" })
	public ProfileDto getProfileByFirstAndLast(@PathVariable("first") String firstName,
			@PathVariable("last") String lastName) throws IllegalArgumentException {
		Profile profile = profileService.getProfileByFirstAndLast(firstName, lastName);
		if (profile == null) {
			throw new IllegalArgumentException("No profile with such first and last name!");
		}
		return convertToDto(profile);
	}

	@PatchMapping(value = { "/edit/profile/{profileId}", "/edit/profile/{profileId}/" })
	public ResponseEntity<?> editProfile(@PathVariable("profileId") Long profileId,
			@RequestParam(name = "Email Address") String emailAddress,
			@RequestParam(name = "Phone Number") String phoneNumber,
			@RequestParam(name = "Address Line") String addressLine, @RequestParam(name = "Zip Code") String zipCode,
			@RequestParam(name = "First Name") String firstName, @RequestParam(name = "Last Name") String lastName) {
		Profile profile = null;
		try {
			profile = profileService.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
					zipCode);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(convertToDto(profile), HttpStatus.CREATED);
	}

}