/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.util.*;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


// line 158 "model.ump"
// line 299 "model.ump"
@Entity
public class Profile
{

  private String profileID;
  
  public void setProfileID(String aProfileID)
  {
	  profileID=aProfileID;
  }
  @Id
  public String getProfileID()
  {
	  return profileID;
  }
  private String addressLine;
  
  public void setAddressLine(String aAddressLine)
  {    
    addressLine = aAddressLine;
  }
  
  public String getAddressLine()
  {
    return addressLine;
  }

  private String phoneNumber;
  
  public void setPhoneNumber(String aPhoneNumber)
  {

    phoneNumber = aPhoneNumber;

  }
  
  public String getPhoneNumber()
  {
    return phoneNumber;
  }
  private String firstName;
  
  public void setFirstName(String aFirstName)
  {
    firstName = aFirstName;

  }
  
  public String getFirstName()
  {
    return firstName;
  }

  private String lastName;
  
  public void setLastName(String aLastName)
  {
    lastName = aLastName;

  }
  
  public String getLastName()
  {
    return lastName;
  }

  private String zipCode;
  
  public void setZipCode(String aZipCode)
  {

    zipCode = aZipCode;

  }
  public String getZipCode()
  {
    return zipCode;
  }

  private String emailAddress;
  
  public void setEmailAddress(String aEmailAddress)
  {

    emailAddress = aEmailAddress;

  }
  public String getEmailAddress()
  {
    return emailAddress;
  }


  private Customer customer;
  
  public void setCustomer(Customer aCustomer)
  {
	  customer=aCustomer;
	  
  }
  @OneToOne
  public Customer getCustomer()
  {
    return customer;
  }





 

  
  /* Code from template association_GetOne */
  




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