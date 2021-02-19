/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.util.*;

// line 51 "model.ump"
// line 250 "model.ump"
public class EmergencyService extends Service
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //EmergencyService Attributes
  private String location;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public EmergencyService(String aServiceID, int aPrice, String aName, AutoRepairShop aAutoRepairShop, String aLocation)
  {
    super(aServiceID, aPrice, aName, aAutoRepairShop);
    location = aLocation;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLocation(String aLocation)
  {
    boolean wasSet = false;
    location = aLocation;
    wasSet = true;
    return wasSet;
  }

  public String getLocation()
  {
    return location;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "location" + "=" + (getLocation() != null ? !getLocation().equals(this)  ? getLocation().toString().replaceAll("  ","    ") : "this" : "null");
  }
}