/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.util.*;

// line 45 "model.ump"
// line 245 "model.ump"
public class BookableService extends Service
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BookableService Attributes
  private int duration;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BookableService(String aServiceID, int aPrice, String aName, AutoRepairShop aAutoRepairShop, int aDuration)
  {
    super(aServiceID, aPrice, aName, aAutoRepairShop);
    duration = aDuration;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDuration(int aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
    wasSet = true;
    return wasSet;
  }

  public int getDuration()
  {
    return duration;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "duration" + ":" + getDuration()+ "]";
  }
}