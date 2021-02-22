/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Entity;

@Entity
public class Car
{

  //Car Attributes
  private String carID;
  private String model;
  private String color;
  private String year;


  //Car Associations
  private Customer owner;

  public void setCarID(String aCarID)
  {
    this.carID=aCarID;
  }

  public void setModel(String aModel)
  {
    this.model=aModel;
  }

  public void setColor(String aColor)
  {
    this.color=aColor;
  }

  public void setYear(String aYear)
  {
    this.year=aYear;
  }
  
  public void setOwner(Customer aOwner) {
	  owner=aOwner;
  }

  @Id
  public String getCarID()
  {
    return carID;
  }

  public String getModel()
  {
    return model;
  }

  public String getColor()
  {
    return color;
  }

  public String getYear()
  {
    return year;
  }
  @OneToOne
  public Customer getOwner() {
	  return owner;
  }

}