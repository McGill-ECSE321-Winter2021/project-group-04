/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.*;

@Entity
public class Receipt {

  private String receiptID;
  private double totalPrice;

  //Receipt Associations
  private Appointment appointment;

  public void setReceiptID(String aReceiptID) { this.receiptID = aReceiptID; }

  public void setTotalPrice(double aTotalPrice) { this.totalPrice = aTotalPrice; }

  @Id
  public String getReceiptID()
  {
    return this.receiptID;
  }

  public double getTotalPrice()
  {
    return this.totalPrice;
  }

  @OneToMany(cascade = {CascadeType.ALL})
  public Appointment getAppointment()
  {
    return appointment;
  }

  public String toString()
  {
    return super.toString() + "["+
            "totalPrice" + ":" + getTotalPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "receiptID" + "=" + (getReceiptID() != null ? !getReceiptID().equals(this)  ? getReceiptID().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "appointment = "+(getAppointment()!=null?Integer.toHexString(System.identityHashCode(getAppointment())):"null");
  }
}