/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.sql.Date; 
import java.sql.Time;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;

@Entity
public class AppointmentReminder extends Reminder
{

  //AppointmentReminder Associations
  private Appointment appointment;

  public void setReminderID(String aReminderID)
  {
    super.setReminderID(aReminderID);
  }

  public void setDate(Date aDate)
  {
    super.setDate(aDate);
  }

  public void setTime(Time aTime)
  {
    super.setTime(aTime);
  }

  public void setMessage(String aMessage)
  {
    super.setMessage(aMessage);
  }

  public void setAppointment(Appointment aAppointment){ this.appointment = aAppointment;}

  @Id
  public String getReminderID()
  {
    return super.getReminderID();
  }

  public Date getDate()
  {
    return super.getDate();
  }

  public Time getTime()
  {
    return super.getTime();
  }

  public String getMessage()
  {
    return super.getMessage();
  }

  @ManyToOne
  public Customer getCustomer()
  {
    return super.getCustomer();
  }

  @OneToMany
  public Appointment getAppointment() { return this.appointment; }

  public void setCustomer(Customer aCustomer)
  {
    super.setCustomer(aCustomer);
  }

}