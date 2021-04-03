package ca.mcgill.ecse321.projectgroup04.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup04.dto.*;
import ca.mcgill.ecse321.projectgroup04.model.*;
import ca.mcgill.ecse321.projectgroup04.service.CarService;
import ca.mcgill.ecse321.projectgroup04.service.CustomerService;
import ca.mcgill.ecse321.projectgroup04.service.ProfileService;
import ca.mcgill.ecse321.projectgroup04.service.ReminderService;

@CrossOrigin(origins = "*")
@RestController
public class CustomerController {

	@Autowired
	private CarService carService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProfileService profileService;
	@Autowired
	private ReminderService reminderService;

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

    @GetMapping(value = { "/customers", "/customers/" })
	public List<CustomerDto> getCustomers() {
		List<CustomerDto> customerDtos = new ArrayList<>();
		for (Customer customer : customerService.getAllCustomers()) {
			customerDtos.add(convertToDto(customer));
		}
		return customerDtos;
	}

	@GetMapping(value = { "/customer/{Id}", "/customer/{Id}/" })
	public CustomerDto getCustomerById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
		Customer customer = customerService.getCustomerById(Id);
		if (customer == null) {
			throw new IllegalArgumentException("No customer with such id!");
		}
		return convertToDto(customer);
	}

	@PostMapping(value = { "/register/customer/{userId}", "/register/customer/{userId}/" })
	public ResponseEntity<?> registerCustomer(@PathVariable("userId") String userId, @RequestParam String password,
			@RequestParam String firstName, @RequestParam String lastName, @RequestParam String address,
			@RequestParam String phoneNumber, @RequestParam String zipCode, @RequestParam String emailAddress,
			@RequestParam String modelNumber, @RequestParam String year, @RequestParam String color)
			{
		Customer customer = null;
		
		try {
			Car car1 = carService.createCar(modelNumber, year, color);
			Profile profile1 = profileService.createProfile(address, phoneNumber, firstName, lastName, zipCode,
				emailAddress);

			List<Reminder> reminder = new ArrayList<>();
			customer = customerService.createCustomer(userId, password, reminder, car1, profile1);
		} catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(convertToDto(customer), HttpStatus.CREATED);
	}

	@PatchMapping(value = { "/edit/customer/{userId}", "/edit/customer/{userId}/" })
	public CustomerDto editCustomer(@PathVariable("userId") String userId, @RequestParam String password,
			@RequestParam Long reminders, @RequestParam Long car, @RequestParam Long profile)
			throws IllegalArgumentException {
		Car car1 = carService.getCarByCarId(car);
		Profile profile1 = profileService.getProfile(profile);
		List<Reminder> reminder = reminderService.getReminderByReminderId(reminders);
		Customer customer = customerService.getCustomerByUserId(userId);
		customer = customerService.editCustomer(customer, userId, password, reminder, car1, profile1);
		CustomerDto customerDto = convertToDto(customer);
		return customerDto;
	}

    @DeleteMapping(value = { "/delete/customer/{Id}", "/delete/customer/{Id}/" })
	public void deleteCustomer(@PathVariable("Id") Long Id) throws IllegalArgumentException {
		Customer customer = customerService.getCustomerById(Id);
		customerService.deleteCustomer(customer);
	}

}