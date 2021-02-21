package ca.mcgill.ecse321.projectgroup04.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ca.mcgill.ecse321.projectgroup04.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AutoRepairShopSystemRepository {

   @Autowired
   EntityManager entityManager;

   @Transactional
   public User createUser(String name) {
       User p = new Customer();
       p.setUserID(name);
       entityManager.persist(p);
       return p;
   }

   @Transactional
   public User getUser(String name) {
       User p = entityManager.find(User.class, name);
       return p;
   }

   @Transactional
   public Appointment createAppointment(Customer name, Service date, GarageTechnician startTime, TimeSlot endTime) {
       Appointment e = new Appointment();
       e.setCustomer(name);
       e.setServices(date);
       e.setTechnician(startTime);
       e.setTimeSlot(endTime);
       entityManager.persist(e);
       return e;
   }

   @Transactional
   public Appointment getAppointment(String name) {
       Appointment e = entityManager.find(Appointment.class, name);
       return e;
   }

}

