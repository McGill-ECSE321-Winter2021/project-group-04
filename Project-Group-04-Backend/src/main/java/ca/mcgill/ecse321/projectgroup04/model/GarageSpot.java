package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.*;

@Entity
@Table(name = "garageSpot")
public class GarageSpot
{
  private int spotNumber;
  private Long Id;

  public void setSpotNumber(int aSpotNumber)
  {
   this.spotNumber = aSpotNumber;
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
	    return Id;
	  }
  public void setId(Long Id) {
	  this.Id = Id;
  }
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
