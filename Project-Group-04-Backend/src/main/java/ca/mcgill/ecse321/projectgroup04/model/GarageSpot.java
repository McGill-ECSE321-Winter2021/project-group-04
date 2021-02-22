package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.*;

@Entity
public class GarageSpot
{
  private int spotNumber;

  public void setSpotNumber(int aSpotNumber)
  {
   this.spotNumber = aSpotNumber;
  }

  @Id
  public int getSpotNumber()
  {
    return this.spotNumber;
  }

  private List<TimeSlot> timeSlot;

  @OneToMany(cascade = {CascadeType.ALL})
  public List<TimeSlot> getTimeSlot()
  {
    return this.timeSlot;
  }

  public void setTimeSlot(List<TimeSlot> timeSlot) {
    this.timeSlot = timeSlot;
  }

}
