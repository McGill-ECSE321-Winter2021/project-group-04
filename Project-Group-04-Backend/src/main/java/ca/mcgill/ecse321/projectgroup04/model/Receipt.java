/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


package ca.mcgill.ecse321.projectgroup04.model;

// line 153 "model.ump"
// line 207 "model.ump"
public class Receipt
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Receipt Attributes
  private String receiptID;
  private double totalPrice;

  //Receipt Associations
  private Appointment appointment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Receipt(String aReceiptID, double aTotalPrice, Appointment aAppointment)
  {
    receiptID = aReceiptID;
    totalPrice = aTotalPrice;
    if (aAppointment == null || aAppointment.getReceipt() != null)
    {
      throw new RuntimeException("Unable to create Receipt due to aAppointment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    appointment = aAppointment;
  }

  public Receipt(String aReceiptID, double aTotalPrice, String aAppointmentIDForAppointment, Customer aCustomerForAppointment, Service aServicesForAppointment, GarageTechnician aTechnicianForAppointment, TimeSlot aTimeSlotForAppointment, AppointmentReminder aReminderForAppointment, AutoRepairShop aAutoRepairShopForAppointment)
  {
    receiptID = aReceiptID;
    totalPrice = aTotalPrice;
    appointment = new Appointment(aAppointmentIDForAppointment, aCustomerForAppointment, aServicesForAppointment, aTechnicianForAppointment, aTimeSlotForAppointment, aReminderForAppointment, this, aAutoRepairShopForAppointment);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setReceiptID(String aReceiptID)
  {
    boolean wasSet = false;
    receiptID = aReceiptID;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalPrice(double aTotalPrice)
  {
    boolean wasSet = false;
    totalPrice = aTotalPrice;
    wasSet = true;
    return wasSet;
  }

  public String getReceiptID()
  {
    return receiptID;
  }

  public double getTotalPrice()
  {
    return totalPrice;
  }
  /* Code from template association_GetOne */
  public Appointment getAppointment()
  {
    return appointment;
  }

  public void delete()
  {
    Appointment existingAppointment = appointment;
    appointment = null;
    if (existingAppointment != null)
    {
      existingAppointment.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "totalPrice" + ":" + getTotalPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "receiptID" + "=" + (getReceiptID() != null ? !getReceiptID().equals(this)  ? getReceiptID().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "appointment = "+(getAppointment()!=null?Integer.toHexString(System.identityHashCode(getAppointment())):"null");
  }
}