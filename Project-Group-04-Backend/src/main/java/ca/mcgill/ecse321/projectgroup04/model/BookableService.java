package ca.mcgill.ecse321.projectgroup04.model;

import java.util.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

@Entity
@Table(name = "bookableService")
public class BookableService extends Service
{

  //BookableService Attributes
  private int duration;

  public void setDuration(int aDuration) { this.duration = aDuration; }

  public int getDuration()
  {
    return this.duration;
  }

  @Override
  public String getName() {
    return super.getName();
  }

  @Override
  public void setName(String aName) {
    super.setName(aName);
  }

  
  @Override
  public Long getServiceId() {
    return super.getServiceId();
  }

  @Override
  public void setServiceId(Long aServiceId) {
    super.setServiceId(aServiceId);
  }

  @Override
  public void setPrice(int aPrice) {
    super.setPrice(aPrice);
  }

  @Override
  public int getPrice() {
    return super.getPrice();
  }

  
 

  @Override
  public AutoRepairShop getAutoRepairShop() {
    return super.getAutoRepairShop();
  }

  @Override
  public void setAutoRepairShop(AutoRepairShop aAutoRepairShop) {
    super.setAutoRepairShop(aAutoRepairShop);
  }

  public String toString()
  {
    return super.toString() + "["+
            "duration" + ":" + getDuration()+ "]";
  }
}