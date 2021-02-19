/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.util.*;

// line 77 "model.ump"
// line 260 "model.ump"
public class Business
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Business Attributes
  private String name;
  private String address;
  private String phoneNumber;
  private String emailAddress;

  //Business Associations
  private List<BusinessHour> businessHours;
  private List<TimeSlot> regular;
  private AutoRepairShop autoRepairShop;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Business(String aName, String aAddress, String aPhoneNumber, String aEmailAddress, AutoRepairShop aAutoRepairShop)
  {
    name = aName;
    address = aAddress;
    phoneNumber = aPhoneNumber;
    emailAddress = aEmailAddress;
    businessHours = new ArrayList<BusinessHour>();
    regular = new ArrayList<TimeSlot>();
    boolean didAddAutoRepairShop = setAutoRepairShop(aAutoRepairShop);
    if (!didAddAutoRepairShop)
    {
      throw new RuntimeException("Unable to create business due to autoRepairShop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmailAddress(String aEmailAddress)
  {
    boolean wasSet = false;
    emailAddress = aEmailAddress;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getAddress()
  {
    return address;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }
  /* Code from template association_GetMany */
  public BusinessHour getBusinessHour(int index)
  {
    BusinessHour aBusinessHour = businessHours.get(index);
    return aBusinessHour;
  }

  public List<BusinessHour> getBusinessHours()
  {
    List<BusinessHour> newBusinessHours = Collections.unmodifiableList(businessHours);
    return newBusinessHours;
  }

  public int numberOfBusinessHours()
  {
    int number = businessHours.size();
    return number;
  }

  public boolean hasBusinessHours()
  {
    boolean has = businessHours.size() > 0;
    return has;
  }

  public int indexOfBusinessHour(BusinessHour aBusinessHour)
  {
    int index = businessHours.indexOf(aBusinessHour);
    return index;
  }
  /* Code from template association_GetMany */
  public TimeSlot getRegular(int index)
  {
    TimeSlot aRegular = regular.get(index);
    return aRegular;
  }

  public List<TimeSlot> getRegular()
  {
    List<TimeSlot> newRegular = Collections.unmodifiableList(regular);
    return newRegular;
  }

  public int numberOfRegular()
  {
    int number = regular.size();
    return number;
  }

  public boolean hasRegular()
  {
    boolean has = regular.size() > 0;
    return has;
  }

  public int indexOfRegular(TimeSlot aRegular)
  {
    int index = regular.indexOf(aRegular);
    return index;
  }
  /* Code from template association_GetOne */
  public AutoRepairShop getAutoRepairShop()
  {
    return autoRepairShop;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBusinessHours()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addBusinessHour(BusinessHour aBusinessHour)
  {
    boolean wasAdded = false;
    if (businessHours.contains(aBusinessHour)) { return false; }
    businessHours.add(aBusinessHour);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBusinessHour(BusinessHour aBusinessHour)
  {
    boolean wasRemoved = false;
    if (businessHours.contains(aBusinessHour))
    {
      businessHours.remove(aBusinessHour);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBusinessHourAt(BusinessHour aBusinessHour, int index)
  {  
    boolean wasAdded = false;
    if(addBusinessHour(aBusinessHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBusinessHours()) { index = numberOfBusinessHours() - 1; }
      businessHours.remove(aBusinessHour);
      businessHours.add(index, aBusinessHour);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBusinessHourAt(BusinessHour aBusinessHour, int index)
  {
    boolean wasAdded = false;
    if(businessHours.contains(aBusinessHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBusinessHours()) { index = numberOfBusinessHours() - 1; }
      businessHours.remove(aBusinessHour);
      businessHours.add(index, aBusinessHour);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBusinessHourAt(aBusinessHour, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRegular()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addRegular(TimeSlot aRegular)
  {
    boolean wasAdded = false;
    if (regular.contains(aRegular)) { return false; }
    regular.add(aRegular);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRegular(TimeSlot aRegular)
  {
    boolean wasRemoved = false;
    if (regular.contains(aRegular))
    {
      regular.remove(aRegular);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRegularAt(TimeSlot aRegular, int index)
  {  
    boolean wasAdded = false;
    if(addRegular(aRegular))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRegular()) { index = numberOfRegular() - 1; }
      regular.remove(aRegular);
      regular.add(index, aRegular);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRegularAt(TimeSlot aRegular, int index)
  {
    boolean wasAdded = false;
    if(regular.contains(aRegular))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRegular()) { index = numberOfRegular() - 1; }
      regular.remove(aRegular);
      regular.add(index, aRegular);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRegularAt(aRegular, index);
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
      existingAutoRepairShop.removeBusiness(this);
    }
    autoRepairShop.addBusiness(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    businessHours.clear();
    regular.clear();
    AutoRepairShop placeholderAutoRepairShop = autoRepairShop;
    this.autoRepairShop = null;
    if(placeholderAutoRepairShop != null)
    {
      placeholderAutoRepairShop.removeBusiness(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "address" + ":" + getAddress()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "emailAddress" + ":" + getEmailAddress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "autoRepairShop = "+(getAutoRepairShop()!=null?Integer.toHexString(System.identityHashCode(getAutoRepairShop())):"null");
  }
}