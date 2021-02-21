/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Owner extends User
{

  @Id
  @Override
  public String getUserID() {
    return super.getUserID();
  }

  @Override
  public String getPassword() {
    return super.getPassword();
  }
}