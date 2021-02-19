/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.sql.Date;
import java.sql.Time;

// line 148 "model.ump"
// line 202 "model.ump"
public class CheckupReminder extends Reminder
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

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