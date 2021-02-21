/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

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

  private String garageID;

  @Id
  public String getGarageID() {
    return garageID;
  }

  public void setGarageID(String garageID) {
    this.garageID = garageID;
  }
}
