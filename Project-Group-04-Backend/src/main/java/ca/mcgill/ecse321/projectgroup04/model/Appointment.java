/*PLEASE DO NOT EDIT THIS CODE*/ 
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/
package ca.mcgill.ecse321.projectgroup04.model;

import java.sql.Date; 
import java.sql.Time;
import java.util.*;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;

// line 57 "model.ump"
// line 173 "model.ump"
// line 187 "model.ump"
public class Appointment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Appointment Attributes
  private String appointmentID;

  //Appointment Associations
  private Customer customer;
  private Service services;
  private GarageTechnician technician;
  private TimeSlot timeSlot;
  private AppointmentReminder reminder;
  private Receipt receipt;
  private AutoRepairShop autoRepairShop;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Appointment(String aAppointmentID, Customer aCustomer, Service aServices, GarageTechnician aTechnician, TimeSlot aTimeSlot, AppointmentReminder aReminder, Receipt aReceipt, AutoRepairShop aAutoRepairShop)
  {
    appointmentID = aAppointmentID;
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create appointment due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddServices = setServices(aServices);
    if (!didAddServices)
    {
      throw new RuntimeException("Unable to create appointment due to services. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddTechnician = setTechnician(aTechnician);
    if (!didAddTechnician)
    {
      throw new RuntimeException("Unable to create appointment due to technician. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setTimeSlot(aTimeSlot))
    {
      throw new RuntimeException("Unable to create Appointment due to aTimeSlot. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aReminder == null || aReminder.getAppointment() != null)
    {
      throw new RuntimeException("Unable to create Appointment due to aReminder. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    reminder = aReminder;
    if (aReceipt == null || aReceipt.getAppointment() != null)
    {
      throw new RuntimeException("Unable to create Appointment due to aReceipt. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    receipt = aReceipt;
    boolean didAddAutoRepairShop = setAutoRepairShop(aAutoRepairShop);
    if (!didAddAutoRepairShop)
    {
      throw new RuntimeException("Unable to create appointment due to autoRepairShop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Appointment(String aAppointmentID, Customer aCustomer, Service aServices, GarageTechnician aTechnician, TimeSlot aTimeSlot, String aReminderIDForReminder, Date aDateForReminder, Time aTimeForReminder, String aMessageForReminder, Customer aCustomerForReminder, String aReceiptIDForReceipt, double aTotalPriceForReceipt, AutoRepairShop aAutoRepairShop)
  {
    appointmentID = aAppointmentID;
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create appointment due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddServices = setServices(aServices);
    if (!didAddServices)
    {
      throw new RuntimeException("Unable to create appointment due to services. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddTechnician = setTechnician(aTechnician);
    if (!didAddTechnician)
    {
      throw new RuntimeException("Unable to create appointment due to technician. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddTimeSlot = setTimeSlot(aTimeSlot);
    if (!didAddTimeSlot)
    {
      throw new RuntimeException("Unable to create appointment due to timeSlot. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    reminder = new AppointmentReminder(aReminderIDForReminder, aDateForReminder, aTimeForReminder, aMessageForReminder, aCustomerForReminder, this);
    receipt = new Receipt(aReceiptIDForReceipt, aTotalPriceForReceipt, this);
    boolean didAddAutoRepairShop = setAutoRepairShop(aAutoRepairShop);
    if (!didAddAutoRepairShop)
    {
      throw new RuntimeException("Unable to create appointment due to autoRepairShop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAppointmentID(String aAppointmentID)
  {
    boolean wasSet = false;
    appointmentID = aAppointmentID;
    wasSet = true;
    return wasSet;
  }

  public String getAppointmentID()
  {
    return appointmentID;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_GetOne */
  public Service getServices()
  {
    return services;
  }
  /* Code from template association_GetOne */
  public GarageTechnician getTechnician()
  {
    return technician;
  }
  /* Code from template association_GetOne */
  public TimeSlot getTimeSlot()
  {
    return timeSlot;
  }
  /* Code from template association_GetOne */
  public AppointmentReminder getReminder()
  {
    return reminder;
  }
  /* Code from template association_GetOne */
  public Receipt getReceipt()
  {
    return receipt;
  }
  /* Code from template association_GetOne */
  public AutoRepairShop getAutoRepairShop()
  {
    return autoRepairShop;
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
      existingCustomer.removeAppointment(this);
    }
    customer.addAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setServices(Service aServices)
  {
    boolean wasSet = false;
    if (aServices == null)
    {
      return wasSet;
    }

    Service existingServices = services;
    services = aServices;
    if (existingServices != null && !existingServices.equals(aServices))
    {
      existingServices.removeAppointment(this);
    }
    services.addAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setTechnician(GarageTechnician aTechnician)
  {
    boolean wasSet = false;
    if (aTechnician == null)
    {
      return wasSet;
    }

    GarageTechnician existingTechnician = technician;
    technician = aTechnician;
    if (existingTechnician != null && !existingTechnician.equals(aTechnician))
    {
      existingTechnician.removeAppointment(this);
    }
    technician.addAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setTimeSlot(TimeSlot aNewTimeSlot)
  {
    boolean wasSet = false;
    if (aNewTimeSlot != null)
    {
      timeSlot = aNewTimeSlot;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAutoRepairShop(AutoRepairShop aAutoRepairShop)
  {
    boolean wasSet = false;
    if (aAutoRepairShop == null)
    {
      return wasSet;
    }

    AutoRepairShop existingAutoRepairShop = autoRepairShop;
    autoRepairShop = aAutoRepairShop;
    if (existingAutoRepairShop != null && !existingAutoRepairShop.equals(aAutoRepairShop))
    {
      existingAutoRepairShop.removeAppointment(this);
    }
    autoRepairShop.addAppointment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeAppointment(this);
    }
    Service placeholderServices = services;
    this.services = null;
    if(placeholderServices != null)
    {
      placeholderServices.removeAppointment(this);
    }
    GarageTechnician placeholderTechnician = technician;
    this.technician = null;
    if(placeholderTechnician != null)
    {
      placeholderTechnician.removeAppointment(this);
    }
    timeSlot = null;
    AppointmentReminder existingReminder = reminder;
    reminder = null;
    if (existingReminder != null)
    {
      existingReminder.delete();
    }
    Receipt existingReceipt = receipt;
    receipt = null;
    if (existingReceipt != null)
    {
      existingReceipt.delete();
    }
    AutoRepairShop placeholderAutoRepairShop = autoRepairShop;
    this.autoRepairShop = null;
    if(placeholderAutoRepairShop != null)
    {
      placeholderAutoRepairShop.removeAppointment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "appointmentID" + "=" + (getAppointmentID() != null ? !getAppointmentID().equals(this)  ? getAppointmentID().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "services = "+(getServices()!=null?Integer.toHexString(System.identityHashCode(getServices())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "technician = "+(getTechnician()!=null?Integer.toHexString(System.identityHashCode(getTechnician())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "timeSlot = "+(getTimeSlot()!=null?Integer.toHexString(System.identityHashCode(getTimeSlot())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "reminder = "+(getReminder()!=null?Integer.toHexString(System.identityHashCode(getReminder())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "receipt = "+(getReceipt()!=null?Integer.toHexString(System.identityHashCode(getReceipt())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "autoRepairShop = "+(getAutoRepairShop()!=null?Integer.toHexString(System.identityHashCode(getAutoRepairShop())):"null");
  }
}