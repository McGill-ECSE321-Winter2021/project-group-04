/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "car")
public class Car {

  // Car Attributes
  private Long carId;
  private String model;
  private String color;
  private String year;

  // Car Associations

  public void setCarId(Long aCarId) {
    this.carId = aCarId;
  }

  public void setModel(String aModel) {
    this.model = aModel;
  }

  public void setColor(String aColor) {
    this.color = aColor;
  }

  public void setYear(String aYear) {
    this.year = aYear;
  }

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  public Long getCarId() {
    return carId;
  }

  public String getModel() {
    return model;
  }

  public String getColor() {
    return color;
  }

  public String getYear() {
    return year;
  }

}