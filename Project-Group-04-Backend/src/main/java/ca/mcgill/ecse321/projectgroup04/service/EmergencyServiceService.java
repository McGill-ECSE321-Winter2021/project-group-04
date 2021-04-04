package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*;
import ca.mcgill.ecse321.projectgroup04.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmergencyServiceService {
    @Autowired
    private EmergencyServiceRepository emergencyServiceRepository;
    
    
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public EmergencyService createEmergencyService(String name, int price) {

        EmergencyService existingService = getEmergencyServiceByServiceName(name);

        if (existingService != null) {
            throw new IllegalArgumentException("Emergency Service with this name already exists");
        }

        if (name == "" || name.equals("undefined")) {
            throw new IllegalArgumentException("Name can't be null");
        }
        if (price == 0) {
            throw new IllegalArgumentException("Price can't be 0");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be negative");
        }

        EmergencyService emergencyService = new EmergencyService();

        emergencyService.setName(name);
        emergencyService.setPrice(price);
        emergencyServiceRepository.save(emergencyService);
        return emergencyService;
    }

    public EmergencyService bookEmergencyService(String bookingName, String serviceName, String aLocation,
            String userId, FieldTechnician aFieldTechnician) {

        EmergencyService bookableEmergencyService = new EmergencyService();
        EmergencyService emergencyService = getEmergencyServiceByServiceName(serviceName);

        if (aLocation == null || aLocation.equals("undefined")){
            throw new IllegalArgumentException("Location can't be null");
        }
        if (aFieldTechnician == null ) {
            throw new IllegalArgumentException("Field Technician can't be null");
        }
        if (bookingName == null) {
            throw new IllegalArgumentException("Service Name can't be null");
        }

        if (aFieldTechnician.getIsAvailable() == false) { // if field technician is unavailable
            throw new IllegalArgumentException("Field Technician is currently unavailable");
        }

        if (emergencyService == null) {
            throw new IllegalArgumentException("No Emergency Service with such name!");
        }

        int price = emergencyService.getPrice();

        Receipt aReceipt = createReceipt(price);
        Customer customer = getCustomerByUserId(userId);

        if (customer == null) {
            throw new IllegalArgumentException("Customer can't be null");
        }

        bookableEmergencyService.setName(bookingName);

        bookableEmergencyService.setPrice(price);
        bookableEmergencyService.setLocation(aLocation);
        bookableEmergencyService.setTechnician(aFieldTechnician);
        aFieldTechnician.setIsAvailable(false);
        // System.out.println(aFieldTechnician.getIsAvailable());
        bookableEmergencyService.setCustomer(customer);
        bookableEmergencyService.setReceipt(aReceipt);
        emergencyServiceRepository.save(bookableEmergencyService);
        return bookableEmergencyService;
    }

    @Transactional
    public EmergencyService getEmergencyServiceByServiceName(String name) {
        for (EmergencyService emergencyService : emergencyServiceRepository.findAll()) {
            if (name.contains(emergencyService.getName()) || name.equals(emergencyService.getName())) {
                return emergencyService;
            }
        }
        return null;
    }

    @Transactional
    public EmergencyService getEmergencyServiceByServiceId(Long serviceId) {
        EmergencyService emergencyService = emergencyServiceRepository.findEmergencyServiceByServiceId(serviceId);

        if (emergencyService == null) {
            throw new IllegalArgumentException("No Emergency Service with such ID exist!");
        }

        else {
            return emergencyService;
        }
    }

    @Transactional
    public List<EmergencyService> getAllEmergencyServices() {
        return (List<EmergencyService>) emergencyServiceRepository.findAll();
    }

    @Transactional
    public List<EmergencyService> getCustomerEmergencyServices(Customer customer) {
        return (List<EmergencyService>) emergencyServiceRepository.findByCustomer(customer);
    }

    @Transactional
    public EmergencyService getEmergencyServiceByReceipt(Receipt receipt) {
        return emergencyServiceRepository.findByReceipt(receipt);
    }

    @Transactional
    public EmergencyService editEmergencyService(EmergencyService emergencyService, int price) {

        if (price == emergencyService.getPrice()) {
            throw new IllegalArgumentException("You have to edit one of the fields");
        }

        emergencyService.setPrice(price);
        emergencyServiceRepository.save(emergencyService);

        return emergencyService;
    }

    @Transactional
    public EmergencyService deleteEmergencyService(EmergencyService emergencyService) {
        emergencyServiceRepository.delete(emergencyService);
        emergencyService = null;
        return emergencyService;
    }

    @Transactional
    public EmergencyService endEmergencyService(EmergencyService emergencyService) {
        FieldTechnician fieldTechnician = emergencyService.getTechnician();
        fieldTechnician.setIsAvailable(true);
        emergencyService.setTechnician(null);
        return emergencyService;
    }
    
    @Transactional
    public List<EmergencyService> getCurrentEmergencyServices(){
    	List<EmergencyService> currentEmergencyServices = new ArrayList<>();
    	for(EmergencyService emergencyService : emergencyServiceRepository.findAll()) {
    		if(emergencyService.getTechnician() != null){
            if(!emergencyService.getTechnician().getIsAvailable()) {
    			currentEmergencyServices.add(emergencyService);
    		}
        }
    	}
    	return currentEmergencyServices;
    }
    @Transactional
    public Receipt createReceipt(double aTotalPrice) {
        if (aTotalPrice == 0) {
            throw new IllegalArgumentException("Total Price can't be 0");
        }
        if (aTotalPrice < 0) {
            throw new IllegalArgumentException("Total Price can't be negative");
        }
        Receipt receipt = new Receipt();
        receipt.setTotalPrice(aTotalPrice);
        receiptRepository.save(receipt);
        return receipt;
    }

    @Transactional
    public Customer getCustomerByUserId(String userId) {
        Customer customer = customerRepository.findCustomerByUserId(userId);
        if (customer == null) {
            throw new IllegalArgumentException("No customer with such userId!");
        }
        return customer;
    }
}
