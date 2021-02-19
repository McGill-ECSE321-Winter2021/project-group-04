/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.util.*;

// line 158 "model.ump"
// line 299 "model.ump"
public class Profile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Profile Attributes
  private String addressLine;
  private String phoneNumber;
  private String firstName;
  private String lastName;
  private String zipCode;
  private String emailAddress;

  //Profile Associations
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Profile(String aAddressLine, String aPhoneNumber, String aFirstName, String aLastName, String aZipCode, String aEmailAddress, Customer aCustomer)
  {
    addressLine = aAddressLine;
    phoneNumber = aPhoneNumber;
    firstName = aFirstName;
    lastName = aLastName;
    zipCode = aZipCode;
    emailAddress = aEmailAddress;
    if (aCustomer == null || aCustomer.getCustomerProfile() != null)
    {
      throw new RuntimeException("Unable to create Profile due to aCustomer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    customer = aCustomer;
  }

  public Profile(String aAddressLine, String aPhoneNumber, String aFirstName, String aLastName, String aZipCode, String aEmailAddress, String aUserIDForCustomer, String aPasswordForCustomer, AutoRepairShop aAutoRepairShopForCustomer, String aCarIDForCustomer, Car aCarForCustomer)
  {
    addressLine = aAddressLine;
    phoneNumber = aPhoneNumber;
    firstName = aFirstName;
    lastName = aLastName;
    zipCode = aZipCode;
    emailAddress = aEmailAddress;
    customer = new Customer(aUserIDForCustomer, aPasswordForCustomer, aAutoRepairShopForCustomer, aCarIDForCustomer, this, aCarForCustomer);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAddressLine(String aAddressLine)
  {
    boolean wasSet = false;
    addressLine = aAddressLine;
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

  public boolean setFirstName(String aFirstName)
  {
    boolean wasSet = false;
    firstName = aFirstName;
    wasSet = true;
    return wasSet;
  }

  public boolean setLastName(String aLastName)
  {
    boolean wasSet = false;
    lastName = aLastName;
    wasSet = true;
    return wasSet;
  }

  public boolean setZipCode(String aZipCode)
  {
    boolean wasSet = false;
    zipCode = aZipCode;
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

  public String getAddressLine()
  {
    return addressLine;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getZipCode()
  {
    return zipCode;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }

  public void delete()
  {
    Customer existingCustomer = customer;
    customer = null;
    if (existingCustomer != null)
    {
      existingCustomer.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "addressLine" + ":" + getAddressLine()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "firstName" + ":" + getFirstName()+ "," +
            "lastName" + ":" + getLastName()+ "," +
            "zipCode" + ":" + getZipCode()+ "," +
            "emailAddress" + ":" + getEmailAddress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}