/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


package ca.mcgill.ecse321.projectgroup04.model;

// line 89 "model.ump"
// line 267 "model.ump"
public abstract class Technician
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Technician Attributes
  private String name;
  private String technicianID;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Technician(String aName, String aTechnicianID)
  {
    name = aName;
    technicianID = aTechnicianID;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setTechnicianID(String aTechnicianID)
  {
    boolean wasSet = false;
    technicianID = aTechnicianID;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getTechnicianID()
  {
    return technicianID;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "name" + "=" + (getName() != null ? !getName().equals(this)  ? getName().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "technicianID" + "=" + (getTechnicianID() != null ? !getTechnicianID().equals(this)  ? getTechnicianID().toString().replaceAll("  ","    ") : "this" : "null");
  }
}