/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adminstrativeAssistant")
public class AdministrativeAssistant extends User {


	@Override
	public Long getId() {
		  return super.getId();
	  }
	  @Override
 public void setId(Long id) {
		  super.setId(id);
	  }
  @Override
  public String getUserID() {
    return super.getUserID();
  }

  @Override
  public String getPassword() {
    return super.getPassword();
  }
  
  @Override
  public void setAutoRepairShop(AutoRepairShop aAutoRepairShop) {
	super.setAutoRepairShop(aAutoRepairShop); 
  }
  
  @Override
  public AutoRepairShop getAutoRepairShop() {
	  return super.getAutoRepairShop();
  }
  @Override
  public void setUserID(String userID) {
	  super.setUserID(userID);
  }
  @Override
  public void setPassword(String aPassword) {
	  super.setPassword(aPassword);
  }
  
}