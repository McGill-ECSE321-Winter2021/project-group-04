/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "adminstrativeAssistant")
public class AdministrativeAssistant extends User {


	@Override
	public Long getId() {
		  return super.getId();
	  }
	  @Override
 public void setId(Long Id) {
		  super.setId(Id);
	  }
  @Override
  public String getUserId() {
    return super.getUserId();
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
  public void setUserId(String userId) {
	  super.setUserId(userId);
  }
  @Override
  public void setPassword(String aPassword) {
	  super.setPassword(aPassword);
  }
  
}