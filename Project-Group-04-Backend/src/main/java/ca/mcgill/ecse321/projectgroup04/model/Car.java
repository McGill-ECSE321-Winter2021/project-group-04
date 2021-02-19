/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.util.*;

// line 27 "model.ump"
// line 234 "model.ump"
public class Car
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Car Attributes
  private String carID;
  private String model;
  private String color;
  private String year;

  //Car Associations
  private Customer carOwner;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Car(String aCarID, String aModel, String aColor, String aYear, Customer aCarOwner)
  {
    carID = aCarID;
    model = aModel;
    color = aColor;
    year = aYear;
    if (aCarOwner == null || aCarOwner.getCar() != null)
    {
      throw new RuntimeException("Unable to create Car due to aCarOwner. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    carOwner = aCarOwner;
  }

  public Car(String aCarID, String aModel, String aColor, String aYear, String aUserIDForCarOwner, String aPasswordForCarOwner, AutoRepairShop aAutoRepairShopForCarOwner, String aCarIDForCarOwner, Profile aCustomerProfileForCarOwner)
  {
    carID = aCarID;
    model = aModel;
    color = aColor;
    year = aYear;
    carOwner = new Customer(aUserIDForCarOwner, aPasswordForCarOwner, aAutoRepairShopForCarOwner, aCarIDForCarOwner, aCustomerProfileForCarOwner, this);
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

  public boolean setModel(String aModel)
  {
    boolean wasSet = false;
    model = aModel;
    wasSet = true;
    return wasSet;
  }

  public boolean setColor(String aColor)
  {
    boolean wasSet = false;
    color = aColor;
    wasSet = true;
    return wasSet;
  }

  public boolean setYear(String aYear)
  {
    boolean wasSet = false;
    year = aYear;
    wasSet = true;
    return wasSet;
  }

  public String getCarID()
  {
    return carID;
  }

  public String getModel()
  {
    return model;
  }

  public String getColor()
  {
    return color;
  }

  public String getYear()
  {
    return year;
  }
  /* Code from template association_GetOne */
  public Customer getCarOwner()
  {
    return carOwner;
  }

  public void delete()
  {
    Customer existingCarOwner = carOwner;
    carOwner = null;
    if (existingCarOwner != null)
    {
      existingCarOwner.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "carID" + ":" + getCarID()+ "," +
            "model" + ":" + getModel()+ "," +
            "color" + ":" + getColor()+ "," +
            "year" + ":" + getYear()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "carOwner = "+(getCarOwner()!=null?Integer.toHexString(System.identityHashCode(getCarOwner())):"null");
  }
}