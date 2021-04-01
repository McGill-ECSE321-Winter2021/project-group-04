package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.AdministrativeAssistantRepository;
import ca.mcgill.ecse321.projectgroup04.dao.CustomerRepository;
import ca.mcgill.ecse321.projectgroup04.dao.OwnerRepository;
import ca.mcgill.ecse321.projectgroup04.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class LogInLogOutService {
	@Autowired
    private OwnerRepository ownerRepository;
	
	@Autowired
    private AdministrativeAssistantRepository administrativeAssistantRepository;
	
	@Autowired
    private CustomerRepository customerRepository;

    public static User currentUser = null;
    
    @Transactional
    public List<Owner> getOwner() {
        return (List<Owner>) ownerRepository.findAll();
    }
    
    @Transactional
    public List<AdministrativeAssistant> getAllAdministrativeAssistants() {
        return (List<AdministrativeAssistant>) administrativeAssistantRepository.findAll();
    }
    
    @Transactional
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Transactional
    public Customer loginAsCustomer(String userId, String password) {
        if (userId == null || userId.trim().length() == 0) {
            throw new IllegalArgumentException("Please enter a valid UserId");
        }
        if (password == null || password.trim().length() == 0) {
            throw new IllegalArgumentException("Please enter a valid Password");
        }

        List<Customer> customers = getAllCustomers();
        // System.out.println(customers);
        Customer foundCustomer = null;

        for (Customer customer : customers) {
            /*
             * System.out.println(customer.getUserId());
             * System.out.println(customer.getPassword());
             * 
             * System.out.println(userId); System.out.println(password);
             */

            if (customer.getUserId().equals(userId) && customer.getPassword().equals(password)) {
                // System.out.println(customer.getUserId());
                currentUser = customer;
                foundCustomer = customer;
                break;
            }
        }

        if (foundCustomer == null) {
            throw new IllegalArgumentException("User does not exist, please register a new account or try again.");
        }

        return foundCustomer;

    }
    
    

    @Transactional
    public Owner loginAsOwner(String userId, String password) {
        if (userId == null || userId.trim().length() == 0) {
            throw new IllegalArgumentException("Please enter a valid UserId");
        }
        if (password == null || password.trim().length() == 0) {
            throw new IllegalArgumentException("Please enter a valid Password");
        }

        List<Owner> owners = getOwner();
        Owner foundOwner = null;

        for (Owner owner : owners) {
            if (owner.getUserId().equals(userId) && owner.getPassword().equals(password)) {
                currentUser = owner;
                foundOwner = owner;
                break;
            }
        }

        if (foundOwner == null) {
            throw new IllegalArgumentException("User does not exist, please register a new account or try again.");
        }

        return foundOwner;

    }

    @Transactional
    public AdministrativeAssistant loginAsAdmin(String userId, String password) {
        if (userId == null || userId.trim().length() == 0) {
            throw new IllegalArgumentException("Please enter a valid UserId");
        }
        if (password == null || password.trim().length() == 0) {
            throw new IllegalArgumentException("Please enter a valid Password");
        }

        List<AdministrativeAssistant> admins = getAllAdministrativeAssistants();
        AdministrativeAssistant foundAdmin = null;

        for (AdministrativeAssistant admin : admins) {
            if (admin.getUserId().equals(userId) && admin.getPassword().equals(password)) {
                currentUser = admin;
                foundAdmin = admin;
                break;
            }
        }

        if (foundAdmin == null) {
            throw new IllegalArgumentException("User does not exist, please register a new account or try again.");
        }

        return foundAdmin;

    }

    @Transactional
    public void logout() {
        currentUser = null;
    }

    @Transactional
    public User getLoggedUser() {

        return currentUser;
    }
}
