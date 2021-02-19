/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.Entity;
import java.sql.Time;

@Entity
public class BusinessHour
{

  public enum DayOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

  //BusinessHour Attributes
  private DayOfWeek dayOfWeek;
  private Time startTime;
  private Time endTime;

  public void setDayOfWeek(DayOfWeek aDayOfWeek)
  {
    this.dayOfWeek=aDayOfWeek;
  }

  public void setStartTime(Time aStartTime)
  {
    this.startTime=aStartTime;
  }

  public void setEndTime(Time aEndTime)
  {
    this.endTime=aEndTime;
  }

  public DayOfWeek getDayOfWeek()
  {
    return dayOfWeek;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "dayOfWeek" + "=" + (getDayOfWeek() != null ? !getDayOfWeek().equals(this)  ? getDayOfWeek().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null");
  }
}