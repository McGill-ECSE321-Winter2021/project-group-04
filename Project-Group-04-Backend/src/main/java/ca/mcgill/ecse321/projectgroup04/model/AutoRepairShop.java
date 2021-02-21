/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.util.*; 
import java.sql.Time;
import java.sql.Date;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;

public class AutoRepairShop {

  private List<User> user;

  @OneToMany(cascade = {CascadeType.ALL})
  public List<User> getUser() { return this.user; }

  public void setUser(List<User> aUser){this.user=aUser; }

  private List<Business> business;

  @OneToMany(cascade = {CascadeType.ALL})
  public List<Business> getBusiness() { return this.business; }

  public void setBusiness(List<Business> aBusiness){this.business=aBusiness; }

  private List<Appointment> appointments;

  @OneToMany(cascade = {CascadeType.ALL})
  public List<Appointment> getAppointments() { return this.appointments; }

  public void setAppointments(List<Appointment> aAppointment){this.appointments=aAppointment; }

  private List<TimeSlot> timeSlots;

  @OneToMany(cascade = {CascadeType.ALL})
  public List<TimeSlot> getTimeSlots() { return this.timeSlots; }

  public void setTimeSlots(List<TimeSlot> aTimeSlot){this.timeSlots=aTimeSlot; }

  private List<Service> services;

  @OneToMany(cascade = {CascadeType.ALL})
  public List<Service> getServices() { return this.services; }

  public void setServices(List<Service> aServices){this.services=aServices; }
}