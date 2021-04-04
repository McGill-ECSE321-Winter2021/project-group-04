package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*;
import ca.mcgill.ecse321.projectgroup04.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private EmergencyServiceRepository emergencyServiceRepository;

    @Transactional
    public List<Receipt> getCustomerReceipts(String userId) {
        Customer customer = getCustomerByUserId(userId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer can't be null!");
        }
        List<Receipt> customerReceipts = new ArrayList<>();
        for (Appointment a : appointmentRepository.findByCustomer(customer)) {
            customerReceipts.add(a.getReceipt());
        }
        
       for (EmergencyService emergencyService : emergencyServiceRepository.findAll()){
           if(emergencyService.getCustomer() != null){
               if(emergencyService.getCustomer().getUserId().equals(userId)){
                   customerReceipts.add(emergencyService.getReceipt());
               }
           }
       }

        return customerReceipts;
    }

    @Transactional
    public Customer createCustomer(String userId, String password, List<Reminder> reminder, Car car, Profile profile) {

        if (userId == null || userId == "") {
            throw new IllegalArgumentException("userId can't be empty");
        }
        if (password == null || password == "") {
            throw new IllegalArgumentException("password can't be empty");
        }
        if (reminder == null) {
            throw new IllegalArgumentException("reminders can't be empty");
        }
        if (car == null) {
            throw new IllegalArgumentException("Car can't be empty");
        }
        if (profile == null) {
            throw new IllegalArgumentException("Profile can't be empty");
        }

        Customer test = customerRepository.findCustomerByUserId(userId);

        if (test != null) {
            throw new IllegalArgumentException("This customer already exists");
        }
        Customer customer = new Customer();
        customer.setPassword(password);
        customer.setUserId(userId);
        customer.setReminders(reminder);
        customer.setCar(car);
        customer.setCustomerProfile(profile);
        LogInLogOutService.currentUser = customer;
        customerRepository.save(customer);
        return customer;
    }

    @Transactional
    public Customer getCustomerById(Long id) {
        return customerRepository.findCustomerById(id);
    }

    @Transactional
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Transactional
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    public Customer editCustomer(Customer customer, String Id, String password, List<Reminder> reminders, Car car,
            Profile profile) {

        if (customer == null) {
            throw new IllegalArgumentException("This customer does not exist");
        }

        customer.setUserId(Id);
        customer.setPassword(password);
        customer.setCar(car);
        customer.setCustomerProfile(profile);
        customer.setReminders(reminders);
        LogInLogOutService.currentUser = customer;
        customerRepository.save(customer);
        return customer;
    }

    @Transactional
    public List<EmergencyService> getCustomerEmergencyServices(Customer customer) {
        return (List<EmergencyService>) emergencyServiceRepository.findByCustomer(customer);
    }

    @Transactional
    public Customer getCustomerByUserId(String userId) {
        Customer customer = customerRepository.findCustomerByUserId(userId);
        if (customer == null) {
            throw new IllegalArgumentException("No customer with such userId!");
        }
        return customer;
    }
    @Transactional
    public Car getCustomerCar(String userId) {
        Customer customer = getCustomerByUserId(userId);
        Car car = customer.getCar();
        if (car == null) {
            throw new IllegalArgumentException("This customer does not have a car yet");
        }
        return car;
    }

    @Transactional
    public List<EmergencyService> getEmergencyServiceOfCustomer(String userId){
        Customer customer = getCustomerByUserId(userId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer can't be null!");
        }
        List<EmergencyService> emergencyServices = new ArrayList<>();
        // for (Appointment a : appointmentRepository.findByCustomer(customer)) {
        //     customerReceipts.add(a.getReceipt());
        // }
        
       for (EmergencyService emergencyService : emergencyServiceRepository.findAll()){
           if(emergencyService.getCustomer() != null){
               if(emergencyService.getCustomer().getUserId().equals(userId)){
                emergencyServices.add(emergencyService);
               }
           }
       }
       return emergencyServices;
    }
}
