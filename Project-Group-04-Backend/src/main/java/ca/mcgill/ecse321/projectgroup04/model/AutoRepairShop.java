package ca.mcgill.ecse321.projectgroup04.model;

import java.util.*; 
import javax.persistence.*;

@Entity
@Table(name = "autoRepairShop")
public class AutoRepairShop {

	private Long Id;
  private String name;
  public void setName(String aName) {name=aName;}
  
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
    return Id;
  }
  public void setId(Long Id) {
	  this.Id = Id;
  }
  public String getName() {
	  return name;
  }
	
  private List<User> user;

  @OneToMany(cascade = {CascadeType.ALL})
  public List<User> getUser() { return this.user; }

  public void setUser(List<User> aUser){this.user=aUser; }

  private Business business;

  @OneToOne
  public Business getBusiness() { return this.business; }

  public void setBusiness(Business aBusiness){this.business=aBusiness; }

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