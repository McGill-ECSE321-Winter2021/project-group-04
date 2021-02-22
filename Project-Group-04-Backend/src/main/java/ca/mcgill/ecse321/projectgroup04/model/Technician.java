/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


package ca.mcgill.ecse321.projectgroup04.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Technician
{

  private String name;

  public void setName(String aName)
  {
    this.name = aName;
  }
  public String getName()
  {
    return this.name;
  }

  private String technicianID;

  public void setTechnicianID(String aTechnicianID)
  {
    this.technicianID = aTechnicianID;
  }

  @Id
  public String getTechnicianID()
  {
    return this.technicianID;
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "name" + "=" + (getName() != null ? !getName().equals(this)  ? getName().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "technicianID" + "=" + (getTechnicianID() != null ? !getTechnicianID().equals(this)  ? getTechnicianID().toString().replaceAll("  ","    ") : "this" : "null");
  }
}