/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.util.*; 
import java.sql.Time;
import java.sql.Date;

// line 120 "model.ump"
// line 293 "model.ump"
public class AutoRepairShop
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AutoRepairShop Associations
  private List<User> user;
  private List<Business> business;
  private List<Appointment> appointments;
  private List<TimeSlot> timeSlots;
  private List<Service> services;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public AutoRepairShop()
  {
    user = new ArrayList<User>();
    business = new ArrayList<Business>();
    appointments = new ArrayList<Appointment>();
    timeSlots = new ArrayList<TimeSlot>();
    services = new ArrayList<Service>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = user.get(index);
    return aUser;
  }

  public List<User> getUser()
  {
    List<User> newUser = Collections.unmodifiableList(user);
    return newUser;
  }

  public int numberOfUser()
  {
    int number = user.size();
    return number;
  }

  public boolean hasUser()
  {
    boolean has = user.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = user.indexOf(aUser);
    return index;
  }
  /* Code from template association_GetMany */
  public Business getBusiness(int index)
  {
    Business aBusiness = business.get(index);
    return aBusiness;
  }

  public List<Business> getBusiness()
  {
    List<Business> newBusiness = Collections.unmodifiableList(business);
    return newBusiness;
  }

  public int numberOfBusiness()
  {
    int number = business.size();
    return number;
  }

  public boolean hasBusiness()
  {
    boolean has = business.size() > 0;
    return has;
  }

  public int indexOfBusiness(Business aBusiness)
  {
    int index = business.indexOf(aBusiness);
    return index;
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
  /* Code from template association_GetMany */
  public TimeSlot getTimeSlot(int index)
  {
    TimeSlot aTimeSlot = timeSlots.get(index);
    return aTimeSlot;
  }

  public List<TimeSlot> getTimeSlots()
  {
    List<TimeSlot> newTimeSlots = Collections.unmodifiableList(timeSlots);
    return newTimeSlots;
  }

  public int numberOfTimeSlots()
  {
    int number = timeSlots.size();
    return number;
  }

  public boolean hasTimeSlots()
  {
    boolean has = timeSlots.size() > 0;
    return has;
  }

  public int indexOfTimeSlot(TimeSlot aTimeSlot)
  {
    int index = timeSlots.indexOf(aTimeSlot);
    return index;
  }
  /* Code from template association_GetMany */
  public Service getService(int index)
  {
    Service aService = services.get(index);
    return aService;
  }

  public List<Service> getServices()
  {
    List<Service> newServices = Collections.unmodifiableList(services);
    return newServices;
  }

  public int numberOfServices()
  {
    int number = services.size();
    return number;
  }

  public boolean hasServices()
  {
    boolean has = services.size() > 0;
    return has;
  }

  public int indexOfService(Service aService)
  {
    int index = services.indexOf(aService);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUser()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (user.contains(aUser)) { return false; }
    AutoRepairShop existingAutoRepairShop = aUser.getAutoRepairShop();
    boolean isNewAutoRepairShop = existingAutoRepairShop != null && !this.equals(existingAutoRepairShop);
    if (isNewAutoRepairShop)
    {
      aUser.setAutoRepairShop(this);
    }
    else
    {
      user.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a autoRepairShop
    if (!this.equals(aUser.getAutoRepairShop()))
    {
      user.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUser()) { index = numberOfUser() - 1; }
      user.remove(aUser);
      user.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(user.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUser()) { index = numberOfUser() - 1; }
      user.remove(aUser);
      user.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBusiness()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Business addBusiness(String aName, String aAddress, String aPhoneNumber, String aEmailAddress)
  {
    return new Business(aName, aAddress, aPhoneNumber, aEmailAddress, this);
  }

  public boolean addBusiness(Business aBusiness)
  {
    boolean wasAdded = false;
    if (business.contains(aBusiness)) { return false; }
    AutoRepairShop existingAutoRepairShop = aBusiness.getAutoRepairShop();
    boolean isNewAutoRepairShop = existingAutoRepairShop != null && !this.equals(existingAutoRepairShop);
    if (isNewAutoRepairShop)
    {
      aBusiness.setAutoRepairShop(this);
    }
    else
    {
      business.add(aBusiness);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBusiness(Business aBusiness)
  {
    boolean wasRemoved = false;
    //Unable to remove aBusiness, as it must always have a autoRepairShop
    if (!this.equals(aBusiness.getAutoRepairShop()))
    {
      business.remove(aBusiness);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBusinessAt(Business aBusiness, int index)
  {  
    boolean wasAdded = false;
    if(addBusiness(aBusiness))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBusiness()) { index = numberOfBusiness() - 1; }
      business.remove(aBusiness);
      business.add(index, aBusiness);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBusinessAt(Business aBusiness, int index)
  {
    boolean wasAdded = false;
    if(business.contains(aBusiness))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBusiness()) { index = numberOfBusiness() - 1; }
      business.remove(aBusiness);
      business.add(index, aBusiness);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBusinessAt(aBusiness, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAppointments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Appointment addAppointment(String aAppointmentID, Customer aCustomer, Service aServices, GarageTechnician aTechnician, TimeSlot aTimeSlot, AppointmentReminder aReminder, Receipt aReceipt)
  {
    return new Appointment(aAppointmentID, aCustomer, aServices, aTechnician, aTimeSlot, aReminder, aReceipt, this);
  }

  public boolean addAppointment(Appointment aAppointment)
  {
    boolean wasAdded = false;
    if (appointments.contains(aAppointment)) { return false; }
    AutoRepairShop existingAutoRepairShop = aAppointment.getAutoRepairShop();
    boolean isNewAutoRepairShop = existingAutoRepairShop != null && !this.equals(existingAutoRepairShop);
    if (isNewAutoRepairShop)
    {
      aAppointment.setAutoRepairShop(this);
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
    //Unable to remove aAppointment, as it must always have a autoRepairShop
    if (!this.equals(aAppointment.getAutoRepairShop()))
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTimeSlots()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TimeSlot addTimeSlot(Time aStartTime, Date aStartDate, Time aEndTime, Date aEndDate, GarageSpot aGarageSpot)
  {
    return new TimeSlot(aStartTime, aStartDate, aEndTime, aEndDate, aGarageSpot, this);
  }

  public boolean addTimeSlot(TimeSlot aTimeSlot)
  {
    boolean wasAdded = false;
    if (timeSlots.contains(aTimeSlot)) { return false; }
    AutoRepairShop existingAutoRepairShop = aTimeSlot.getAutoRepairShop();
    boolean isNewAutoRepairShop = existingAutoRepairShop != null && !this.equals(existingAutoRepairShop);
    if (isNewAutoRepairShop)
    {
      aTimeSlot.setAutoRepairShop(this);
    }
    else
    {
      timeSlots.add(aTimeSlot);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTimeSlot(TimeSlot aTimeSlot)
  {
    boolean wasRemoved = false;
    //Unable to remove aTimeSlot, as it must always have a autoRepairShop
    if (!this.equals(aTimeSlot.getAutoRepairShop()))
    {
      timeSlots.remove(aTimeSlot);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTimeSlotAt(TimeSlot aTimeSlot, int index)
  {  
    boolean wasAdded = false;
    if(addTimeSlot(aTimeSlot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeSlots()) { index = numberOfTimeSlots() - 1; }
      timeSlots.remove(aTimeSlot);
      timeSlots.add(index, aTimeSlot);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTimeSlotAt(TimeSlot aTimeSlot, int index)
  {
    boolean wasAdded = false;
    if(timeSlots.contains(aTimeSlot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeSlots()) { index = numberOfTimeSlots() - 1; }
      timeSlots.remove(aTimeSlot);
      timeSlots.add(index, aTimeSlot);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTimeSlotAt(aTimeSlot, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServices()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addService(Service aService)
  {
    boolean wasAdded = false;
    if (services.contains(aService)) { return false; }
    AutoRepairShop existingAutoRepairShop = aService.getAutoRepairShop();
    boolean isNewAutoRepairShop = existingAutoRepairShop != null && !this.equals(existingAutoRepairShop);
    if (isNewAutoRepairShop)
    {
      aService.setAutoRepairShop(this);
    }
    else
    {
      services.add(aService);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeService(Service aService)
  {
    boolean wasRemoved = false;
    //Unable to remove aService, as it must always have a autoRepairShop
    if (!this.equals(aService.getAutoRepairShop()))
    {
      services.remove(aService);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addServiceAt(Service aService, int index)
  {  
    boolean wasAdded = false;
    if(addService(aService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServices()) { index = numberOfServices() - 1; }
      services.remove(aService);
      services.add(index, aService);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveServiceAt(Service aService, int index)
  {
    boolean wasAdded = false;
    if(services.contains(aService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServices()) { index = numberOfServices() - 1; }
      services.remove(aService);
      services.add(index, aService);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addServiceAt(aService, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (user.size() > 0)
    {
      User aUser = user.get(user.size() - 1);
      aUser.delete();
      user.remove(aUser);
    }
    
    while (business.size() > 0)
    {
      Business aBusiness = business.get(business.size() - 1);
      aBusiness.delete();
      business.remove(aBusiness);
    }
    
    while (appointments.size() > 0)
    {
      Appointment aAppointment = appointments.get(appointments.size() - 1);
      aAppointment.delete();
      appointments.remove(aAppointment);
    }
    
    while (timeSlots.size() > 0)
    {
      TimeSlot aTimeSlot = timeSlots.get(timeSlots.size() - 1);
      aTimeSlot.delete();
      timeSlots.remove(aTimeSlot);
    }
    
    while (services.size() > 0)
    {
      Service aService = services.get(services.size() - 1);
      aService.delete();
      services.remove(aService);
    }
    
  }

}