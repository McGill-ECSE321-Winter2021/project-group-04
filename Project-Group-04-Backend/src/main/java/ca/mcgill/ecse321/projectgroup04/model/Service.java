/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.util.*;

// line 36 "model.ump"
// line 240 "model.ump"
public abstract class Service
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Service> servicesByName = new HashMap<String, Service>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Service Attributes
  private String serviceID;
  private int price;
  private String name;

  //Service Associations
  private List<Appointment> appointments;
  private AutoRepairShop autoRepairShop;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Service(String aServiceID, int aPrice, String aName, AutoRepairShop aAutoRepairShop)
  {
    serviceID = aServiceID;
    price = aPrice;
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    appointments = new ArrayList<Appointment>();
    boolean didAddAutoRepairShop = setAutoRepairShop(aAutoRepairShop);
    if (!didAddAutoRepairShop)
    {
      throw new RuntimeException("Unable to create service due to autoRepairShop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setServiceID(String aServiceID)
  {
    boolean wasSet = false;
    serviceID = aServiceID;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    String anOldName = getName();
    if (anOldName != null && anOldName.equals(aName)) {
      return true;
    }
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      servicesByName.remove(anOldName);
    }
    servicesByName.put(aName, this);
    return wasSet;
  }

  public String getServiceID()
  {
    return serviceID;
  }

  public int getPrice()
  {
    return price;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static Service getWithName(String aName)
  {
    return servicesByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
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
  /* Code from template association_GetOne */
  public AutoRepairShop getAutoRepairShop()
  {
    return autoRepairShop;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAppointments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Appointment addAppointment(String aAppointmentID, Customer aCustomer, GarageTechnician aTechnician, TimeSlot aTimeSlot, AppointmentReminder aReminder, Receipt aReceipt, AutoRepairShop aAutoRepairShop)
  {
    return new Appointment(aAppointmentID, aCustomer, this, aTechnician, aTimeSlot, aReminder, aReceipt, aAutoRepairShop);
  }

  public boolean addAppointment(Appointment aAppointment)
  {
    boolean wasAdded = false;
    if (appointments.contains(aAppointment)) { return false; }
    Service existingServices = aAppointment.getServices();
    boolean isNewServices = existingServices != null && !this.equals(existingServices);
    if (isNewServices)
    {
      aAppointment.setServices(this);
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
    //Unable to remove aAppointment, as it must always have a services
    if (!this.equals(aAppointment.getServices()))
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
      existingAutoRepairShop.removeService(this);
    }
    autoRepairShop.addService(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    servicesByName.remove(getName());
    for(int i=appointments.size(); i > 0; i--)
    {
      Appointment aAppointment = appointments.get(i - 1);
      aAppointment.delete();
    }
    AutoRepairShop placeholderAutoRepairShop = autoRepairShop;
    this.autoRepairShop = null;
    if(placeholderAutoRepairShop != null)
    {
      placeholderAutoRepairShop.removeService(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "price" + ":" + getPrice()+ "," +
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "serviceID" + "=" + (getServiceID() != null ? !getServiceID().equals(this)  ? getServiceID().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "autoRepairShop = "+(getAutoRepairShop()!=null?Integer.toHexString(System.identityHashCode(getAutoRepairShop())):"null");
  }
}