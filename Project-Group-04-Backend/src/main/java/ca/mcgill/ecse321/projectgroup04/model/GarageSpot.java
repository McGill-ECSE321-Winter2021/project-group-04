/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.util.*;
import java.sql.Time;
import java.sql.Date;

// line 114 "model.ump"
// line 287 "model.ump"
public class GarageSpot
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GarageSpot Attributes
  private int spotNumber;

  //GarageSpot Associations
  private List<TimeSlot> spot;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GarageSpot(int aSpotNumber)
  {
    spotNumber = aSpotNumber;
    spot = new ArrayList<TimeSlot>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSpotNumber(int aSpotNumber)
  {
    boolean wasSet = false;
    spotNumber = aSpotNumber;
    wasSet = true;
    return wasSet;
  }

  public int getSpotNumber()
  {
    return spotNumber;
  }
  /* Code from template association_GetMany */
  public TimeSlot getSpot(int index)
  {
    TimeSlot aSpot = spot.get(index);
    return aSpot;
  }

  public List<TimeSlot> getSpot()
  {
    List<TimeSlot> newSpot = Collections.unmodifiableList(spot);
    return newSpot;
  }

  public int numberOfSpot()
  {
    int number = spot.size();
    return number;
  }

  public boolean hasSpot()
  {
    boolean has = spot.size() > 0;
    return has;
  }

  public int indexOfSpot(TimeSlot aSpot)
  {
    int index = spot.indexOf(aSpot);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpot()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TimeSlot addSpot(Time aStartTime, Date aStartDate, Time aEndTime, Date aEndDate, AutoRepairShop aAutoRepairShop)
  {
    return new TimeSlot(aStartTime, aStartDate, aEndTime, aEndDate, this, aAutoRepairShop);
  }

  public boolean addSpot(TimeSlot aSpot)
  {
    boolean wasAdded = false;
    if (spot.contains(aSpot)) { return false; }
    GarageSpot existingGarageSpot = aSpot.getGarageSpot();
    boolean isNewGarageSpot = existingGarageSpot != null && !this.equals(existingGarageSpot);
    if (isNewGarageSpot)
    {
      aSpot.setGarageSpot(this);
    }
    else
    {
      spot.add(aSpot);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpot(TimeSlot aSpot)
  {
    boolean wasRemoved = false;
    //Unable to remove aSpot, as it must always have a garageSpot
    if (!this.equals(aSpot.getGarageSpot()))
    {
      spot.remove(aSpot);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSpotAt(TimeSlot aSpot, int index)
  {  
    boolean wasAdded = false;
    if(addSpot(aSpot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpot()) { index = numberOfSpot() - 1; }
      spot.remove(aSpot);
      spot.add(index, aSpot);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSpotAt(TimeSlot aSpot, int index)
  {
    boolean wasAdded = false;
    if(spot.contains(aSpot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpot()) { index = numberOfSpot() - 1; }
      spot.remove(aSpot);
      spot.add(index, aSpot);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSpotAt(aSpot, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=spot.size(); i > 0; i--)
    {
      TimeSlot aSpot = spot.get(i - 1);
      aSpot.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "spotNumber" + ":" + getSpotNumber()+ "]";
  }
}