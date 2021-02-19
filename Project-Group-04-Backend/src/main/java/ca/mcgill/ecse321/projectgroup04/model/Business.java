/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.util.*;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;


@Entity
public class Business
{

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
  // INTERFACE
  //------------------------

  public void setName(String aName)
  {
    this.name=aName;
  }

  public void setAddress(String aAddress)
  {
    this.address=aAddress;
  }

  public void setPhoneNumber(String aPhoneNumber)
  {
    this.phoneNumber=aPhoneNumber;
  }

  public void setEmailAddress(String aEmailAddress)
  {
    this.emailAddress=aEmailAddress;
  }

  @Id
  public String getName()
  {
    return this.name;
  }

  public String getAddress()
  {
    return this.address;
  }

  public String getPhoneNumber()
  {
    return this.phoneNumber;
  }

  public String getEmailAddress()
  {
    return this.emailAddress;
  }

  public void setBusinessHours(List<BusinessHour> aBusinessHours){
    this.businessHours=aBusinessHours;
  }

  @OneToMany(cascade = {CascadeType.ALL})
  public List<BusinessHour> getBusinessHours()
  {
    return this.businessHours;
  }

  public void setReguar(List<TimeSlot> aRegular){
    this.regular=aRegular;
  }

  @OneToMany(cascade = {CascadeType.ALL})
  public List<TimeSlot> getRegular()
  {
    return this.regular;
  }

 
  public AutoRepairShop getAutoRepairShop()
  {
    return this.autoRepairShop;
  }

  public void setAutoRepairShop(AutoRepairShop aAutoRepairShop)
  {
    this.autoRepairShop=aAutoRepairShop;
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