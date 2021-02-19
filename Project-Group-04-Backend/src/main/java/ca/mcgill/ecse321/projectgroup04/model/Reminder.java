/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.sql.Date;
import java.sql.Time;

// line 134 "model.ump"
// line 192 "model.ump"
public abstract class Reminder
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reminder Attributes
  private String reminderID;
  private Date date;
  private Time time;
  private String message;

  //Reminder Associations
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reminder(String aReminderID, Date aDate, Time aTime, String aMessage, Customer aCustomer)
  {
    reminderID = aReminderID;
    date = aDate;
    time = aTime;
    message = aMessage;
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create reminder due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setReminderID(String aReminderID)
  {
    boolean wasSet = false;
    reminderID = aReminderID;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setTime(Time aTime)
  {
    boolean wasSet = false;
    time = aTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setMessage(String aMessage)
  {
    boolean wasSet = false;
    message = aMessage;
    wasSet = true;
    return wasSet;
  }

  public String getReminderID()
  {
    return reminderID;
  }

  public Date getDate()
  {
    return date;
  }

  public Time getTime()
  {
    return time;
  }

  public String getMessage()
  {
    return message;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCustomer(Customer aCustomer)
  {
    boolean wasSet = false;
    if (aCustomer == null)
    {
      return wasSet;
    }

    Customer existingCustomer = customer;
    customer = aCustomer;
    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
    {
      existingCustomer.removeReminder(this);
    }
    customer.addReminder(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeReminder(this);
    }
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