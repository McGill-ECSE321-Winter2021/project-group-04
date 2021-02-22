/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.*;

@Entity
@Table(name = "reminder")
public abstract class Reminder
{

  //Reminder Attributes
  private Long reminderID;
  private Date date;
  private Time time;
  private String message;

  //Reminder Associations
  private Customer customer;

  
  public void setReminderID(Long aReminderID)
  {
    this.reminderID=aReminderID;
  }

  public void setDate(Date aDate)
  {
    this.date=aDate;
  }

  public void setTime(Time aTime)
  {
    this.time=aTime;
  }

  public void setMessage(String aMessage)
  {
    this.message=aMessage;
  }
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  public Long getReminderID()
  {
    return this.reminderID;
  }

  public Date getDate()
  {
    return this.date;
  }

  public Time getTime()
  {
    return this.time;
  }

  public String getMessage()
  {
    return this.message;
  }
  
  @ManyToOne
  public Customer getCustomer()
  {
    return customer;
  }

  public void setCustomer(Customer aCustomer)
  {
    this.customer=aCustomer;
  }

  public String toString()
  {
    return super.toString() + "["+
            "reminderID" + ":" + getReminderID()+ "," +
            "message" + ":" + getMessage()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "time" + "=" + (getTime() != null ? !getTime().equals(this)  ? getTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}