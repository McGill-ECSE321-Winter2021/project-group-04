/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmergencyService extends Service
{
  private String location;

  public void setLocation(String aLocation)
  {
   this.location = aLocation;
  }
  public String getLocation()
  {
    return this.location;
  }

  @Override
  public String getName() {
    return super.getName();
  }

  @Override
  public void setName(String aName) {
    super.setName(aName);
  }

  @Id
  @Override
  public String getServiceID() {
    return super.getServiceID();
  }

  @Override
  public void setServiceID(String aServiceID) {
    super.setServiceID(aServiceID);
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
  public List<Appointment> getAppointments() {
    return super.getAppointments();
  }

  @Override
  public void setAppointments(List<Appointment> appointments) {
    super.setAppointments(appointments);
  }

  @Override
  public AutoRepairShop getAutoRepairShop() {
    return super.getAutoRepairShop();
  }

  @Override
  public void setAutoRepairShop(AutoRepairShop aAutoRepairShop) {
    super.setAutoRepairShop(aAutoRepairShop);
  }
}