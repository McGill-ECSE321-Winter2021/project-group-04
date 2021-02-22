/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.sql.Time;
import java.sql.Date;
import javax.persistence.*;

@Entity
public class TimeSlot {

  private Time startTime;
  private Date startDate;
  private Time endTime;
  private Date endDate;

  private GarageSpot garageSpot;
  private AutoRepairShop autoRepairShop;
  private String timeSlotID;

  public void setStartTime(Time aStartTime) { this.startTime = aStartTime; }
  public void setEndtTime(Time aEndtTime) { this.endTime = aEndtTime; }
  public void setStartDate(Date aStartDate) { this.startDate = aStartDate; }
  public void setEndDate(Date aEndDate) { this.endDate = aEndDate; }

  public void setGarageSpot(GarageSpot aGarageSpot) { this.garageSpot = aGarageSpot; }
  public void setAutoRepairShop(AutoRepairShop aAutoRepairShop) { this.autoRepairShop = aAutoRepairShop; }
  public void setTimeSlotID(String aTimeSlotID){this.timeSlotID = aTimeSlotID;}

  public Time getStartTime()
  {
    return this.startTime;
  }

  public Date getStartDate()
  {
    return this.startDate;
  }

  public Time getEndTime()
  {
    return this.endTime;
  }

  public Date getEndDate() { return this.endDate; }

  @Id
  public String getTimeSlotID() { return this.timeSlotID; }

  @ManyToOne
  public GarageSpot getGarageSpot()
  {
    return this.garageSpot;
  }

  @ManyToOne
  public AutoRepairShop getAutoRepairShop()
  {
    return this.autoRepairShop;
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