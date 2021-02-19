/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.util.*;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import java.sql.Time;
import java.sql.Date;

@Entity
public class Customer extends User
{

  //Customer Associations
  private List<Reminder> reminders;
  private Profile customerProfile;
  private Car car;
  private List<Appointment> appointments;

  @Override
  public String getUserID(){
    return super.getUserID();
  }

  @Override
  public void setUserID(String aUserID){
    super.setUserID(aUserID);
  }

  @Override
  public void setPassword(String aPassword){
    super.setPassword(aPassword);
  }

  @Override
  public String getPassword(){
    return super.getPassword();
  }

  public void setReminders(List<Reminder> aReminders){
    this.reminders=aReminders;
  }
  public List<Reminder> getReminders()
  {
    return this.reminders;
  }

  public void setCustomerProfile(Profile aCustomerProfile){
    this.customerProfile=aCustomerProfile;
  }

  public Profile getCustomerProfile()
  {
    return this.customerProfile;
  }

  public void setCar(Car aCar){
    this.car=aCar;
  }
  public Car getCar()
  {
    return this.car;
  }

  public void setappointments (List<Appointment> aAppointments){
    this.appointments=aAppointments;
  }

  public List<Appointment> getAppointments()
  {
    return this.appointments;
  }

}