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
import ca.mcgill.ecse321.projectgroup04.service.CustomerService;
import ca.mcgill.ecse321.projectgroup04.service.EmergencyServiceService;
import ca.mcgill.ecse321.projectgroup04.service.FieldTechnicianService;


@CrossOrigin(origins = "*")
@RestController
public class EmergencyServiceController {

	@Autowired
	private EmergencyServiceService emergencyServiceService;
	@Autowired
	private FieldTechnicianService fieldService;
	@Autowired
	private CustomerService customerService;

    private EmergencyServiceDto convertToDto(EmergencyService emergencyService) {
		if (emergencyService == null) {
			throw new IllegalArgumentException("There is no such Emergency Service!");
		}

		EmergencyServiceDto emergencyServiceDto = new EmergencyServiceDto();
		emergencyServiceDto.setName(emergencyService.getName());
		emergencyServiceDto.setPrice(emergencyService.getPrice());
		emergencyServiceDto.setLocation(emergencyService.getLocation());

		if (emergencyService.getTechnician() != null) {
			emergencyServiceDto.setFieldTechnician(convertToDto(emergencyService.getTechnician()));
		}

		if (emergencyService.getCustomer() != null) {
			emergencyServiceDto.setCustomer(convertToDto(emergencyService.getCustomer()));
		}

		if (emergencyService.getReceipt() != null) {
			emergencyServiceDto.setReceipt(convertToDto(emergencyService.getReceipt()));
		}

		emergencyServiceDto.setId(emergencyService.getServiceId());

		return emergencyServiceDto;

	}

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

    public ReceiptDto convertToDto(Receipt receipt) {
		if (receipt == null) {
			throw new IllegalArgumentException("There is no such Receipt!");
		}
		ReceiptDto receiptDto = new ReceiptDto(receipt.getTotalPrice());
		receiptDto.setId(receipt.getReceiptId());
		return receiptDto;
	}

    private FieldTechnicianDto convertToDto(FieldTechnician fieldTechnician) {
		if (fieldTechnician == null) {
			throw new IllegalArgumentException("There is no such Field Technician!");
		}

		FieldTechnicianDto fieldTechnicianDto = new FieldTechnicianDto(fieldTechnician.getName(),
				fieldTechnician.getIsAvailable());
		fieldTechnicianDto.setId(fieldTechnician.getTechnicianId());
		return fieldTechnicianDto;

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

    @GetMapping(value = { "/emergencyServices", "/emergencyServices/" })
	public List<EmergencyServiceDto> getAllEmergencyServices() {
		
		
		List<EmergencyServiceDto> emergencyServiceDtos = new ArrayList<>();
		for (EmergencyService emergencyService : emergencyServiceService.getAllEmergencyServices()) {
			if (!emergencyService.getName().contains("for")) {
				emergencyServiceDtos.add(convertToDto(emergencyService));			
			}
			
		}

		return emergencyServiceDtos;
	}

	@GetMapping(value = { "/emergencyService/{Id}", "/emergencyService/{Id}/" })
	public EmergencyServiceDto getEmergencyServiceById(@PathVariable("Id") Long Id) {
		return convertToDto(emergencyServiceService.getEmergencyServiceByServiceId(Id));
	}

	@PostMapping(value = { "/create/emergencyService/{serviceName}", "/create/emergencyService/{serviceName}/" })
	public ResponseEntity<?> createEmergencyService(@PathVariable("serviceName") String serviceName,
			@RequestParam int price) {
		EmergencyService emergencyService = null;
		try {
			emergencyService = emergencyServiceService.createEmergencyService(serviceName, price);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(convertToDto(emergencyService), HttpStatus.CREATED);

	}

	@PostMapping(value = { "/book/emergencyService/{userId}",
			"/book/emergencyService/{userId}/" })
	public ResponseEntity<?> bookEmergencyService(@PathVariable("userId") String userId,
			@RequestParam(name ="serviceName") String serviceName, @RequestParam(name = "Location") String location,
			@RequestParam(name = "fieldTechnicianId") Long fieldTechnicianId) throws IllegalArgumentException {

		FieldTechnician fieldTechnician=null;
		String nameOfBooking = null;
		EmergencyService bookableEmergencyService=null;
		try {
			
			fieldTechnician = fieldService.getFieldTechnicianById(fieldTechnicianId); // get
			nameOfBooking = serviceName + " for " + userId; // service for that user
			bookableEmergencyService = emergencyServiceService.bookEmergencyService(nameOfBooking,
					serviceName, location, userId, fieldTechnician);
		} catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(convertToDto(bookableEmergencyService),HttpStatus.CREATED);
	}

	// Will not allow updating emergency service as it is spontaneous

	@PatchMapping(value = { "/edit/emergencyService/{serviceId}", "/edit/emergencyService/{serviceId}/" })
	public ResponseEntity<?> editEmergencyService(@PathVariable("serviceId") Long serviceId, @RequestParam int price)
			throws IllegalArgumentException {
		EmergencyService emergencyService = null;
		try {
			emergencyService = emergencyServiceService.getEmergencyServiceByServiceId(serviceId);
			emergencyServiceService.editEmergencyService(emergencyService, price);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(convertToDto(emergencyService), HttpStatus.CREATED);

	}

	@DeleteMapping(value = { "/delete/emergencyService/{serviceId}", "/delete/emergencyServices/{serviceId}/" })
	public ResponseEntity<?> deleteEmergencyService(@PathVariable("serviceId") Long serviceId)
			throws IllegalArgumentException {
		EmergencyService emergencyService = null;
		try {
			emergencyService = emergencyServiceService.getEmergencyServiceByServiceId(serviceId);
			emergencyServiceService.deleteEmergencyService(emergencyService);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(convertToDto(emergencyService), HttpStatus.CREATED);

	}

	@PostMapping(value = { "/end/emergencyService/{serviceId}", "/end/emergencyService/{serviceId}/" })
	public ResponseEntity<?> endEmergencyServiceBooking(@PathVariable("serviceId") Long serviceId) throws IllegalArgumentException {
		EmergencyService emergencyServiceBooking = null;
		try {
			emergencyServiceBooking = emergencyServiceService.getEmergencyServiceByServiceId(serviceId);
			emergencyServiceService.endEmergencyService(emergencyServiceBooking);
		} catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	} // only admin/owner can end an emergency service


	@GetMapping(value = { "/onGoingEmergencies", "/onGoingEmergencies/" })
	public List<EmergencyService> getOnGoingEmergencies() {
		return emergencyServiceService.getCurrentEmergencyServices();
	}
	@GetMapping(value= { "/booked/emergencies/{userId}", "/booked/emergencies/{userId}/"})
	public List<EmergencyServiceDto> getReceiptsOfEmergencies(@PathVariable("userId") String userId){
		List<EmergencyServiceDto> emergencyServiceDto = new ArrayList<EmergencyServiceDto>();
		for (EmergencyService emergencies : customerService.getEmergencyServiceOfCustomer(userId) ) {
			emergencyServiceDto.add(convertToDto(emergencies));
		}
		return emergencyServiceDto;
	}
}
