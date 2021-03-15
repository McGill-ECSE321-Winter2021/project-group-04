package ca.mcgill.ecse321.projectgroup04.dao;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup04.model.AppointmentReminder;
import ca.mcgill.ecse321.projectgroup04.model.Car;
import ca.mcgill.ecse321.projectgroup04.model.CheckupReminder;
import ca.mcgill.ecse321.projectgroup04.model.Customer;
import ca.mcgill.ecse321.projectgroup04.model.Profile;
import ca.mcgill.ecse321.projectgroup04.model.Reminder;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistAndLoadCustomer {

    @Autowired
    private AppointmentReminderRepository appointmentReminderRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CheckupReminderRepository checkupReminderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @AfterEach
    public void clearDatabase() {
        profileRepository.deleteAll();
        appointmentReminderRepository.deleteAll();
        carRepository.deleteAll();
        customerRepository.deleteAll();
        checkupReminderRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testPersistAndLoadCustomer() {

        String userId = "test";
        String password = "TestPassword";
        Customer customer = new Customer();
        customer.setPassword(password);
        customer.setUserId(userId);

        Profile profile = new Profile();
        String addressLine = "TestAddressLine";
        String phoneNumber = "0123456789";
        String firstName = "TestFirstName";
        String lastName = "TestLastName";
        String zipCode = "zipCode";
        String emailAddress = "testEmailAddress";
        profile.setAddressLine(addressLine);
        profile.setPhoneNumber(phoneNumber);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setZipCode(zipCode);
        profile.setEmailAddress(emailAddress);
        profileRepository.save(profile);
        Long profileId = profile.getProfileId();

        Car car = new Car();
        String model = "testModel";
        String color = "testColor";
        String year = "testYear";
        car.setColor(color);
        car.setModel(model);
        car.setYear(year);
        carRepository.save(car);
        Long carId = car.getCarId();

        CheckupReminder reminder = new CheckupReminder();
        String message = "testMessage";
        Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Time time = java.sql.Time.valueOf(LocalTime.of(11, 35));

        reminder.setMessage(message);
        reminder.setDate(date);
        reminder.setTime(time);
        checkupReminderRepository.save(reminder);
        Long reminderId = reminder.getReminderId();
        ArrayList<Reminder> reminders = new ArrayList<Reminder>();
        reminders.add(reminder);

        Date ardate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Time artime = java.sql.Time.valueOf(LocalTime.of(11, 35));
        String appReminderMessage = "It is tomorrow";
        AppointmentReminder appointmentReminder = new AppointmentReminder();
        appointmentReminder.setDate(ardate);
        appointmentReminder.setTime(artime);
        appointmentReminder.setMessage(appReminderMessage);
        appointmentReminderRepository.save(appointmentReminder);
        Long arId = appointmentReminder.getReminderId();
        reminders.add(appointmentReminder);

        customer.setReminders(reminders);
        customer.setCar(car);
        customer.setCustomerProfile(profile);

        customerRepository.save(customer);
        Long id = customer.getId();

        customer = null;

        customer = customerRepository.findCustomerById(id);

        assertNotNull(customer);
        assertEquals(id, customer.getId());
        assertEquals(password, customer.getPassword());
        assertEquals(carId, customer.getCar().getCarId());
        assertEquals(profileId, customer.getCustomerProfile().getProfileId());
        assertEquals(reminderId, customer.getReminders().get(0).getReminderId());
        assertEquals(arId, customer.getReminders().get(1).getReminderId());
        assertEquals(userId, customer.getUserId());

    }

}
