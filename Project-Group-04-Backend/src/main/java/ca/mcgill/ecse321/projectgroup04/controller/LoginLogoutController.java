package ca.mcgill.ecse321.projectgroup04.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup04.dto.*;
import ca.mcgill.ecse321.projectgroup04.model.*;
import ca.mcgill.ecse321.projectgroup04.service.LogInLogOutService;


@CrossOrigin(origins = "*")
@RestController
public class LoginLogoutController {


	@Autowired
	private LogInLogOutService logService;


    private CustomerDto convertToDto(Customer customer) {
		if (customer == null) {
			throw new IllegalArgumentException("There is no such Customer!");
		}
		CustomerDto customerDto = new CustomerDto(customer.getUserId(), customer.getPassword());
		customerDto.setProfile(convertToDto(customer.getCustomerProfile()));
		customerDto.setCar(convertToDto(customer.getCar()));
		customerDto.setId(customer.getId());
		return customerDto;
	}

    private CarDto convertToDto(Car car) {
		if (car == null) {
			throw new IllegalArgumentException("There is no such Car!");
		}
		CarDto carDto = new CarDto(car.getModel(), car.getColor(), car.getYear());
		carDto.setCarId(car.getCarId());
		return carDto;
	}

    private ProfileDto convertToDto(Profile profile) {
		if (profile == null) {
			throw new IllegalArgumentException("There is no such Profile!");
		}
		ProfileDto profileDto = new ProfileDto(profile.getAddressLine(), profile.getPhoneNumber(),
				profile.getFirstName(), profile.getLastName(), profile.getZipCode(), profile.getEmailAddress());
		profileDto.setProfileId(profile.getProfileId());
		return profileDto;

	}

    public OwnerDto convertToDto(Owner owner) {
		if (owner == null) {
			throw new IllegalArgumentException("There is no such Owner!");
		}
		OwnerDto ownerDto = new OwnerDto(owner.getUserId(), owner.getPassword());
		ownerDto.setId(owner.getId());
		return ownerDto;
	}

    public AdministrativeAssistantDto convertToDto(AdministrativeAssistant administrativeAssistant) {
		if (administrativeAssistant == null) {
			throw new IllegalArgumentException("There is no such AdministrativeAssistant!");
		}
		AdministrativeAssistantDto administrativeAssistantDto = new AdministrativeAssistantDto(
				administrativeAssistant.getUserId(), administrativeAssistant.getPassword());
		administrativeAssistantDto.setId(administrativeAssistant.getId());
		return administrativeAssistantDto;
	}
    

    @PostMapping(value = { "/login/{userId}", "/login/{userId}/" })
	public ResponseEntity<?> userLogin(@PathVariable(value = "userId") String userId,
			@RequestParam(value = "password") String password) throws IllegalArgumentException {
		// System.out.println(userId);
		if (userId.equalsIgnoreCase("owner")) {
			try {
				logService.loginAsOwner(userId, password);
			} catch (IllegalArgumentException e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		else if (userId.equalsIgnoreCase("admin")) {
			try {
				logService.loginAsAdmin(userId, password);
			} catch (IllegalArgumentException e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		else {
			try {
				logService.loginAsCustomer(userId, password);
			} catch (IllegalArgumentException e) {
				System.out.println(e);
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value = { "/logout", "/logout/" })
	public ResponseEntity<?> logout() {
		try {
		logService.logout();
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = { "/login/currentCustomer", "/login/currentCustomer/" })
	public CustomerDto getCurrentUser() {
		User currentUser = logService.getLoggedUser();
		if (!currentUser.getUserId().equalsIgnoreCase("owner") && !currentUser.getUserId().equalsIgnoreCase("admin")) {
			return (convertToDto((Customer) currentUser));
		} else {
			return null;
		}

	}

	@GetMapping(value = { "/login/currentOwner", "/login/currentOwner/" })
	public OwnerDto getCurrentOwner() {
		User currentUser = logService.getLoggedUser();
		if (currentUser.getUserId().equalsIgnoreCase("owner")) {
			return (convertToDto((Owner) currentUser));
		} else {
			return null;
		}
	}

	@GetMapping(value = { "/login/currentAdmin", "/login/currentAdmin/" })
	public AdministrativeAssistantDto getCurrentAdmin() {
		User currentUser = logService.getLoggedUser();
		if (currentUser.getUserId().equalsIgnoreCase("admin")) {
			return (convertToDto((AdministrativeAssistant) currentUser));
		} else {
			return null;
		}
	}

}
