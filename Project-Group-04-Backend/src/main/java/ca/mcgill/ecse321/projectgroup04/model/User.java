/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.util.*;

// line 2 "model.ump"
// line 222 "model.ump"
public abstract class User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByUserID = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String userID;
  private String password;

  //User Associations
  private AutoRepairShop autoRepairShop;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aUserID, String aPassword, AutoRepairShop aAutoRepairShop)
  {
    password = aPassword;
    if (!setUserID(aUserID))
    {
      throw new RuntimeException("Cannot create due to duplicate userID. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddAutoRepairShop = setAutoRepairShop(aAutoRepairShop);
    if (!didAddAutoRepairShop)
    {
      throw new RuntimeException("Unable to create user due to autoRepairShop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUserID(String aUserID)
  {
    boolean wasSet = false;
    String anOldUserID = getUserID();
    if (anOldUserID != null && anOldUserID.equals(aUserID)) {
      return true;
    }
    if (hasWithUserID(aUserID)) {
      return wasSet;
    }
    userID = aUserID;
    wasSet = true;
    if (anOldUserID != null) {
      usersByUserID.remove(anOldUserID);
    }
    usersByUserID.put(aUserID, this);
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public String getUserID()
  {
    return userID;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithUserID(String aUserID)
  {
    return usersByUserID.get(aUserID);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithUserID(String aUserID)
  {
    return getWithUserID(aUserID) != null;
  }

  public String getPassword()
  {
    return password;
  }
  /* Code from template association_GetOne */
  public AutoRepairShop getAutoRepairShop()
  {
    return autoRepairShop;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAutoRepairShop(AutoRepairShop aAutoRepairShop)
  {
    boolean wasSet = false;
    if (aAutoRepairShop == null)
    {
      return wasSet;
    }

    AutoRepairShop existingAutoRepairShop = autoRepairShop;
    autoRepairShop = aAutoRepairShop;
    if (existingAutoRepairShop != null && !existingAutoRepairShop.equals(aAutoRepairShop))
    {
      existingAutoRepairShop.removeUser(this);
    }
    autoRepairShop.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    usersByUserID.remove(getUserID());
    AutoRepairShop placeholderAutoRepairShop = autoRepairShop;
    this.autoRepairShop = null;
    if(placeholderAutoRepairShop != null)
    {
      placeholderAutoRepairShop.removeUser(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "userID" + ":" + getUserID()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "autoRepairShop = "+(getAutoRepairShop()!=null?Integer.toHexString(System.identityHashCode(getAutoRepairShop())):"null");
  }
}