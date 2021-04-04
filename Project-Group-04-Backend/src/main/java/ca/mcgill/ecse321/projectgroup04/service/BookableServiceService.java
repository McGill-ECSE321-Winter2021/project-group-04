package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*;
import ca.mcgill.ecse321.projectgroup04.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BookableServiceService {

    @Autowired
    private BookableServiceRepository bookableServiceRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Transactional
    public Appointment getAppointmentsByBookableServiceAndCustomer(BookableService service, Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer can't be null");
        }
        if (service == null) {
            throw new IllegalArgumentException("Service can't be null");
        }
        return appointmentRepository.findByBookableServicesAndCustomer(service, customer);
    }

    @Transactional
    public BookableService createBookableService(String name, int price, int duration) {

        BookableService existingService = getBookableServiceByServiceName(name);

        if (existingService != null) {
            throw new IllegalArgumentException("Bookable Service with this name already exists");
        }

        if (price == 0) {
            throw new IllegalArgumentException("Price can't be 0");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be negative");
        }
        if (duration < 0) {
            throw new IllegalArgumentException("Duration can't be negative");
        }
        if (duration == 0) {
            throw new IllegalArgumentException("Duration can't be equal to 0");
        }
        if (name == "" || name.equals("undefined")) {
            throw new IllegalArgumentException("Name can't be empty");
        }

        BookableService bookableService = new BookableService();
        bookableService.setDuration(duration);
        bookableService.setName(name);
        bookableService.setPrice(price);
        bookableServiceRepository.save(bookableService);
        return bookableService;
    }

    @Transactional
    public BookableService getBookableServiceById(Long serviceId) {
        return bookableServiceRepository.findBookableServiceByServiceId(serviceId);
    }

    @Transactional
    public List<BookableService> getAllBookableServices() {
        return (List<BookableService>) bookableServiceRepository.findAll();
    }

    @Transactional
    public BookableService getBookableServiceByServiceName(String name) {

        for (BookableService bookableService : bookableServiceRepository.findAll()) {
            if (name.contains(bookableService.getName()) || name.equals(bookableService.getName())) {
                return bookableService;
            }
        }
        return null;
    }

    public BookableService deleteBookableService(BookableService bookableService) {
        bookableServiceRepository.delete(bookableService);
        bookableService = null;
        return bookableService;
    }

    public BookableService editBookableService(BookableService bookableService, int duration, int price) {
        if (price == 0) {
            throw new IllegalArgumentException("Price can't be 0");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be negative");
        }
        if (duration < 0) {
            throw new IllegalArgumentException("Duration can't be negative");
        }
        if ( duration == bookableService.getDuration()
                && price == bookableService.getPrice()) {
            throw new IllegalArgumentException("You have to edit one of the fields");
        }
        if (duration == 0) {
            throw new IllegalArgumentException("Duration can't be equal to 0");
        }
        
        
        bookableService.setDuration(duration);
        bookableService.setPrice(price);
        bookableServiceRepository.save(bookableService);
        return bookableService;
    }
}
