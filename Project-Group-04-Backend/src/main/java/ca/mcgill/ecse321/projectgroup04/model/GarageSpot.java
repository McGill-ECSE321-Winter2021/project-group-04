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

  private List<TimeSlot> spot;

  @OneToMany(cascade = {CascadeType.ALL})
  public List<TimeSlot> getSpot()
  {
    return this.spot;
  }

  public void setSpot(List<TimeSlot> spot) {
    this.spot = spot;
  }

}
