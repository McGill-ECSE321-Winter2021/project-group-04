/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.sql.Date; 
import java.sql.Time;

// line 143 "model.ump"
// line 197 "model.ump"
public class AppointmentReminder extends Reminder
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AppointmentReminder Associations
  private Appointment appointment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public AppointmentReminder(String aReminderID, Date aDate, Time aTime, String aMessage, Customer aCustomer, Appointment aAppointment)
  {
    super(aReminderID, aDate, aTime, aMessage, aCustomer);
    if (aAppointment == null || aAppointment.getReminder() != null)
    {
      throw new RuntimeException("Unable to create AppointmentReminder due to aAppointment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    appointment = aAppointment;
  }

  public AppointmentReminder(String aReminderID, Date aDate, Time aTime, String aMessage, Customer aCustomer, String aAppointmentIDForAppointment, Customer aCustomerForAppointment, Service aServicesForAppointment, GarageTechnician aTechnicianForAppointment, TimeSlot aTimeSlotForAppointment, Receipt aReceiptForAppointment, AutoRepairShop aAutoRepairShopForAppointment)
  {
    super(aReminderID, aDate, aTime, aMessage, aCustomer);
    appointment = new Appointment(aAppointmentIDForAppointment, aCustomerForAppointment, aServicesForAppointment, aTechnicianForAppointment, aTimeSlotForAppointment, this, aReceiptForAppointment, aAutoRepairShopForAppointment);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Appointment getAppointment()
  {
    return appointment;
  }

  public void delete()
  {
    Appointment existingAppointment = appointment;
    appointment = null;
    if (existingAppointment != null)
    {
      existingAppointment.delete();
    }
    super.delete();
  }

}