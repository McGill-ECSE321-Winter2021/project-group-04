/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.util.*;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "customer")
public class Customer extends User {

  // Customer Associations
  private List<Reminder> reminders;
  private Profile customerProfile;
  private Car car;

  @Override
  public String getUserId() {
    return super.getUserId();
  }

  @Override
  public Long getId() {
    return super.getId();
  }

  @Override
  public void setId(Long id) {
    super.setId(id);
  }

  @Override
  public void setUserId(String aUserId) {
    super.setUserId(aUserId);
  }

  @Override
  public void setPassword(String aPassword) {
    super.setPassword(aPassword);
  }

  @Override
  public String getPassword() {
    return super.getPassword();
  }

  public void setReminders(List<Reminder> aReminders) {
    this.reminders = aReminders;
  }

  @OneToMany // (mappedBy = "customer", cascade = {CascadeType.ALL})
  public List<Reminder> getReminders() {
    return this.reminders;
  }

  public void setCustomerProfile(Profile aCustomerProfile) {
    this.customerProfile = aCustomerProfile;
  }

  @OneToOne
  public Profile getCustomerProfile() {
    return this.customerProfile;
  }

  public void setCar(Car aCar) {
    this.car = aCar;
  }

  @OneToOne
  public Car getCar() {
    return this.car;
  }

}