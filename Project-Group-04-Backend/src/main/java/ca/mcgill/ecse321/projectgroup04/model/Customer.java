/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 9 "model.ump"
// line 227 "model.ump"
public class Customer extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Attributes
  private String carID;

  //Customer Associations
  private List<Reminder> reminders;
  private Profile customerProfile;
  private Car car;
  private List<Appointment> appointments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(String aUserID, String aPassword, AutoRepairShop aAutoRepairShop, String aCarID, Profile aCustomerProfile, Car aCar)
  {
    super(aUserID, aPassword, aAutoRepairShop);
    carID = aCarID;
    reminders = new ArrayList<Reminder>();
    if (aCustomerProfile == null || aCustomerProfile.getCustomer() != null)
    {
      throw new RuntimeException("Unable to create Customer due to aCustomerProfile. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    customerProfile = aCustomerProfile;
    if (aCar == null || aCar.getCarOwner() != null)
    {
      throw new RuntimeException("Unable to create Customer due to aCar. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    car = aCar;
    appointments = new ArrayList<Appointment>();
  }

  public Customer(String aUserID, String aPassword, AutoRepairShop aAutoRepairShop, String aCarID, String aAddressLineForCustomerProfile, String aPhoneNumberForCustomerProfile, String aFirstNameForCustomerProfile, String aLastNameForCustomerProfile, String aZipCodeForCustomerProfile, String aEmailAddressForCustomerProfile, String aCarIDForCar, String aModelForCar, String aColorForCar, String aYearForCar)
  {
    super(aUserID, aPassword, aAutoRepairShop);
    carID = aCarID;
    reminders = new ArrayList<Reminder>();
    customerProfile = new Profile(aAddressLineForCustomerProfile, aPhoneNumberForCustomerProfile, aFirstNameForCustomerProfile, aLastNameForCustomerProfile, aZipCodeForCustomerProfile, aEmailAddressForCustomerProfile, this);
    car = new Car(aCarIDForCar, aModelForCar, aColorForCar, aYearForCar, this);
    appointments = new ArrayList<Appointment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCarID(String aCarID)
  {
    boolean wasSet = false;
    carID = aCarID;
    wasSet = true;
    return wasSet;
  }

  public String getCarID()
  {
    return carID;
  }
  /* Code from template association_GetMany */
  public Reminder getReminder(int index)
  {
    Reminder aReminder = reminders.get(index);
    return aReminder;
  }

  public List<Reminder> getReminders()
  {
    List<Reminder> newReminders = Collections.unmodifiableList(reminders);
    return newReminders;
  }

  public int numberOfReminders()
  {
    int number = reminders.size();
    return number;
  }

  public boolean hasReminders()
  {
    boolean has = reminders.size() > 0;
    return has;
  }

  public int indexOfReminder(Reminder aReminder)
  {
    int index = reminders.indexOf(aReminder);
    return index;
  }
  /* Code from template association_GetOne */
  public Profile getCustomerProfile()
  {
    return customerProfile;
  }
  /* Code from template association_GetOne */
  public Car getCar()
  {
    return car;
  }
  /* Code from template association_GetMany */
  public Appointment getAppointment(int index)
  {
    Appointment aAppointment = appointments.get(index);
    return aAppointment;
  }

  public List<Appointment> getAppointments()
  {
    List<Appointment> newAppointments = Collections.unmodifiableList(appointments);
    return newAppointments;
  }

  public int numberOfAppointments()
  {
    int number = appointments.size();
    return number;
  }

  public boolean hasAppointments()
  {
    boolean has = appointments.size() > 0;
    return has;
  }

  public int indexOfAppointment(Appointment aAppointment)
  {
    int index = appointments.indexOf(aAppointment);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReminders()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addReminder(Reminder aReminder)
  {
    boolean wasAdded = false;
    if (reminders.contains(aReminder)) { return false; }
    Customer existingCustomer = aReminder.getCustomer();
    boolean isNewCustomer = existingCustomer != null && !this.equals(existingCustomer);
    if (isNewCustomer)
    {
      aReminder.setCustomer(this);
    }
    else
    {
      reminders.add(aReminder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReminder(Reminder aReminder)
  {
    boolean wasRemoved = false;
    //Unable to remove aReminder, as it must always have a customer
    if (!this.equals(aReminder.getCustomer()))
    {
      reminders.remove(aReminder);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addReminderAt(Reminder aReminder, int index)
  {  
    boolean wasAdded = false;
    if(addReminder(aReminder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReminders()) { index = numberOfReminders() - 1; }
      reminders.remove(aReminder);
      reminders.add(index, aReminder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReminderAt(Reminder aReminder, int index)
  {
    boolean wasAdded = false;
    if(reminders.contains(aReminder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReminders()) { index = numberOfReminders() - 1; }
      reminders.remove(aReminder);
      reminders.add(index, aReminder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReminderAt(aReminder, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAppointments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Appointment addAppointment(String aAppointmentID, Service aServices, GarageTechnician aTechnician, TimeSlot aTimeSlot, AppointmentReminder aReminder, Receipt aReceipt, AutoRepairShop aAutoRepairShop)
  {
    return new Appointment(aAppointmentID, this, aServices, aTechnician, aTimeSlot, aReminder, aReceipt, aAutoRepairShop);
  }

  public boolean addAppointment(Appointment aAppointment)
  {
    boolean wasAdded = false;
    if (appointments.contains(aAppointment)) { return false; }
    Customer existingCustomer = aAppointment.getCustomer();
    boolean isNewCustomer = existingCustomer != null && !this.equals(existingCustomer);
    if (isNewCustomer)
    {
      aAppointment.setCustomer(this);
    }
    else
    {
      appointments.add(aAppointment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAppointment(Appointment aAppointment)
  {
    boolean wasRemoved = false;
    //Unable to remove aAppointment, as it must always have a customer
    if (!this.equals(aAppointment.getCustomer()))
    {
      appointments.remove(aAppointment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAppointmentAt(Appointment aAppointment, int index)
  {  
    boolean wasAdded = false;
    if(addAppointment(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointments()) { index = numberOfAppointments() - 1; }
      appointments.remove(aAppointment);
      appointments.add(index, aAppointment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAppointmentAt(Appointment aAppointment, int index)
  {
    boolean wasAdded = false;
    if(appointments.contains(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointments()) { index = numberOfAppointments() - 1; }
      appointments.remove(aAppointment);
      appointments.add(index, aAppointment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAppointmentAt(aAppointment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=reminders.size(); i > 0; i--)
    {
      Reminder aReminder = reminders.get(i - 1);
      aReminder.delete();
    }
    Profile existingCustomerProfile = customerProfile;
    customerProfile = null;
    if (existingCustomerProfile != null)
    {
      existingCustomerProfile.delete();
    }
    Car existingCar = car;
    car = null;
    if (existingCar != null)
    {
      existingCar.delete();
    }
    for(int i=appointments.size(); i > 0; i--)
    {
      Appointment aAppointment = appointments.get(i - 1);
      aAppointment.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "carID" + ":" + getCarID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "customerProfile = "+(getCustomerProfile()!=null?Integer.toHexString(System.identityHashCode(getCustomerProfile())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "car = "+(getCar()!=null?Integer.toHexString(System.identityHashCode(getCar())):"null");
  }
}