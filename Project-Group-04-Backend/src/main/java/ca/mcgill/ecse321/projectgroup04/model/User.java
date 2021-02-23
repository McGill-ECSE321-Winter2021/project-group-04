/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "\"User\"")
public abstract class User
{

  //User Attributes
  private String userID;
  private String password;
  private Long id;

  //User Associations
  private AutoRepairShop autoRepairShop;


  public void setUserID(String aUserID)
  {
    this.userID=aUserID;
  }

  public void setPassword(String aPassword)
  {
    this.password=aPassword;
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
	  this.id = id;
  }
  public String getUserID()
  {
    return userID;
  }

  public String getPassword()
  {
    return password;
  }
  
  @ManyToOne
  public AutoRepairShop getAutoRepairShop()
  {
    return autoRepairShop;
  }
  
  public void setAutoRepairShop(AutoRepairShop aAutoRepairShop)
  {
    this.autoRepairShop=aAutoRepairShop;
  }

  public String toString()
  {
    return super.toString() + "["+
            "userID" + ":" + getUserID()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "autoRepairShop = "+(getAutoRepairShop()!=null?Integer.toHexString(System.identityHashCode(getAutoRepairShop())):"null");
  }
}