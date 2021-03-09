/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.sql.Time;
import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name = "timeSlot")
public class TimeSlot {

  private Time startTime;
  private Date startDate;
  private Time endTime;
  private Date endDate;

 private Integer garageSpot;
  private Long timeSlotId;

  public void setStartTime(Time aStartTime) { this.startTime = aStartTime; }
  public void setEndTime(Time aEndtTime) { this.endTime = aEndtTime; }
  public void setStartDate(Date aStartDate) { this.startDate = aStartDate; }
  public void setEndDate(Date aEndDate) { this.endDate = aEndDate; }

  public void setGarageSpot(int garageSpot2) { this.garageSpot = garageSpot2; }
  public void setTimeSlotId(Long aTimeSlotId){this.timeSlotId = aTimeSlotId;}

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
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getTimeSlotId() { return this.timeSlotId; }


  public Integer getGarageSpot()
  {
    return this.garageSpot;
  }




}