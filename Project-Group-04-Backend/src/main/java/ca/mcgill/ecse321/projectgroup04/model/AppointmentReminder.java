/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.sql.Date; 
import java.sql.Time;
import javax.persistence.*;

@Entity
@Table(name = "appointmentReminder")
public class AppointmentReminder extends Reminder
{

  //AppointmentReminder Associations
  private Appointment appointment;
  @Override
  public void setReminderID(Long aReminderID)
  {
    super.setReminderID(aReminderID);
  }
  @Override
  public void setDate(Date aDate)
  {
    super.setDate(aDate);
  }
  @Override
  public void setTime(Time aTime)
  {
    super.setTime(aTime);
  }
  @Override
  public void setMessage(String aMessage)
  {
    super.setMessage(aMessage);
  }

  public void setAppointment(Appointment aAppointment){ this.appointment = aAppointment;}

 
  @Override
  public Long getReminderID()
  {
    return super.getReminderID();
  }

  @Override
  public Date getDate()
  {
    return super.getDate();
  }
  @Override
  public Time getTime()
  {
    return super.getTime();
  }
  @Override
  public String getMessage()
  {
    return super.getMessage();
  }
  @Override
  @ManyToOne
  public Customer getCustomer()
  {
    return super.getCustomer();
  }

  @OneToOne
  public Appointment getAppointment() { return this.appointment; }

  @Override
  public void setCustomer(Customer aCustomer)
  {
    super.setCustomer(aCustomer);
  }

}