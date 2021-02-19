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

public class CheckupReminder extends Reminder
{

  public CheckupReminder(String aReminderID, Date aDate, Time aTime, String aMessage, Customer aCustomer)
  {
    super(aReminderID, aDate, aTime, aMessage, aCustomer);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}