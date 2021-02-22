package ca.mcgill.ecse321.projectgroup04.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ca.mcgill.ecse321.projectgroup04.model.*;

import org.hibernate.EntityNameResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AutoRepairShopSystemRepository {

    @Autowired
    EntityManager entityManager;

    // ! User (1)
    @Transactional
    public User createUser(String name, String password) {
        User user = new Customer();
        user.setUserID(name);
        user.setPassword(password);
        entityManager.persist(user);
        return user;
    }

    @Transactional
    public User getUser(String name) {
        User user = entityManager.find(User.class, name);
        return user;
    }

    // ! Appointment (2)
    @Transactional
    public Appointment createAppointment(Customer customer, Service service, GarageTechnician garageTechnician,
            TimeSlot timeSlot, AppointmentReminder reminder, Receipt receipt) {
        Appointment app = new Appointment();
        app.setCustomer(customer);
        app.setServices(service);
        app.setTechnician(garageTechnician);
        app.setTimeSlot(timeSlot);
        app.setReminder(reminder);
        app.setReceipt(receipt);
        entityManager.persist(app);
        return app;
    }

    @Transactional
    public Appointment getAppointment(String name) {
        Appointment app = entityManager.find(Appointment.class, name);
        return app;
    }

    // ! Business (3)
    @Transactional
    public Business createBusiness(String name, String address, String phoneNumber, String emailAddress,
            List<BusinessHour> businessHours, List<TimeSlot> regular) {
        Business business = new Business();
        business.setName(name);
        business.setAddress(address);
        business.setPhoneNumber(phoneNumber);
        business.setEmailAddress(emailAddress);
        business.setBusinessHours(businessHours);
        business.setRegular(regular);
        entityManager.persist(business);
        return business;
    }

    @Transactional
    public Business getBusiness(String name) {
        Business app = entityManager.find(Business.class, name);
        return app;
    }

    // ! Car (4)
    @Transactional
    public Car createCar(String carID, String model, String color, String year) {
        Car car = new Car();
        car.setCarID(carID);
        car.setColor(color);
        car.setModel(model);
        car.setYear(year);
        entityManager.persist(car);
        return car;
    }

    @Transactional
    public Car getCar(String carID) {
        Car car = entityManager.find(Car.class, carID);
        return car;
    }

    // ! AdministrativeAssistant (5)
    @Transactional
    public AdministrativeAssistant createAdministrativeAssistant(String userID, String password) {
        AdministrativeAssistant admin = new AdministrativeAssistant();
        admin.setUserID(userID);
        admin.setPassword(password);
        entityManager.persist(admin);
        return admin;
    }

    public AdministrativeAssistant getAdministrativeAssistant(String userID) {
        AdministrativeAssistant admin = entityManager.find(AdministrativeAssistant.class, userID);
        return admin;
    }

    // ! AppointmentReminder (6)
    @Transactional // ?check if u add customer (its the association) and checkk if need to add
                   // appointment
    public AppointmentReminder createAppointmentReminder(String reminderID, Date date, Time time, String message) {
        AppointmentReminder app = new AppointmentReminder();
        app.setReminderID(reminderID);
        app.setDate(date);
        app.setMessage(message);
        app.setTime(time);
        entityManager.persist(app);
        return app;
    }

    public AppointmentReminder getAppointmentReminder(String id) {
        AppointmentReminder app = entityManager.find(AppointmentReminder.class, id);
        return app;
    }

    // !

}
