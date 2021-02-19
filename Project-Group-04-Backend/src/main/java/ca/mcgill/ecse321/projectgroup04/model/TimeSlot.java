/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.sql.Time;
import java.sql.Date;

// line 106 "model.ump"
// line 282 "model.ump"
public class TimeSlot
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TimeSlot Attributes
  private Time startTime;
  private Date startDate;
  private Time endTime;
  private Date endDate;

  //TimeSlot Associations
  private GarageSpot garageSpot;
  private AutoRepairShop autoRepairShop;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TimeSlot(Time aStartTime, Date aStartDate, Time aEndTime, Date aEndDate, GarageSpot aGarageSpot, AutoRepairShop aAutoRepairShop)
  {
    startTime = aStartTime;
    startDate = aStartDate;
    endTime = aEndTime;
    endDate = aEndDate;
    boolean didAddGarageSpot = setGarageSpot(aGarageSpot);
    if (!didAddGarageSpot)
    {
      throw new RuntimeException("Unable to create spot due to garageSpot. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAutoRepairShop = setAutoRepairShop(aAutoRepairShop);
    if (!didAddAutoRepairShop)
    {
      throw new RuntimeException("Unable to create timeSlot due to autoRepairShop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(Date aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public Date getEndDate()
  {
    return endDate;
  }
  /* Code from template association_GetOne */
  public GarageSpot getGarageSpot()
  {
    return garageSpot;
  }
  /* Code from template association_GetOne */
  public AutoRepairShop getAutoRepairShop()
  {
    return autoRepairShop;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGarageSpot(GarageSpot aGarageSpot)
  {
    boolean wasSet = false;
    if (aGarageSpot == null)
    {
      return wasSet;
    }

    GarageSpot existingGarageSpot = garageSpot;
    garageSpot = aGarageSpot;
    if (existingGarageSpot != null && !existingGarageSpot.equals(aGarageSpot))
    {
      existingGarageSpot.removeSpot(this);
    }
    garageSpot.addSpot(this);
    wasSet = true;
    return wasSet;
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
      existingAutoRepairShop.removeTimeSlot(this);
    }
    autoRepairShop.addTimeSlot(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    GarageSpot placeholderGarageSpot = garageSpot;
    this.garageSpot = null;
    if(placeholderGarageSpot != null)
    {
      placeholderGarageSpot.removeSpot(this);
    }
    AutoRepairShop placeholderAutoRepairShop = autoRepairShop;
    this.autoRepairShop = null;
    if(placeholderAutoRepairShop != null)
    {
      placeholderAutoRepairShop.removeTimeSlot(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "garageSpot = "+(getGarageSpot()!=null?Integer.toHexString(System.identityHashCode(getGarageSpot())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "autoRepairShop = "+(getAutoRepairShop()!=null?Integer.toHexString(System.identityHashCode(getAutoRepairShop())):"null");
  }
}