/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.util.*;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "business")
public class Business {

  // Business Attributes
  private String name;
  private String address;
  private String phoneNumber;
  private String emailAddress;
  private Long id;
  // Business Associations
  private List<BusinessHour> businessHours;
  private List<TimeSlot> regular;

  // ------------------------
  // INTERFACE
  // ------------------------

  public void setName(String aName) {
    this.name = aName;
  }

  public void setAddress(String aAddress) {
    this.address = aAddress;
  }

  public void setPhoneNumber(String aPhoneNumber) {
    this.phoneNumber = aPhoneNumber;
  }

  public void setEmailAddress(String aEmailAddress) {
    this.emailAddress = aEmailAddress;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
    return id;
  }

  public void setId(Long Id) {
    this.id = Id;
  }

  public String getName() {
    return this.name;
  }

  public String getAddress() {
    return this.address;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public String getEmailAddress() {
    return this.emailAddress;
  }

  public void setBusinessHours(List<BusinessHour> aBusinessHours) {
    this.businessHours = aBusinessHours;
  }

  @OneToMany(cascade = { CascadeType.ALL })
  public List<BusinessHour> getBusinessHours() {
    return this.businessHours;
  }

  public void setRegular(List<TimeSlot> aRegular) {
    this.regular = aRegular;
  }

  @OneToMany(cascade = { CascadeType.ALL })
  public List<TimeSlot> getRegular() {
    return this.regular;
  }

}