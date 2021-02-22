package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.*;

@Entity
@Table(name = "receipt")
public class Receipt {

  private Long receiptID;
  private double totalPrice;

  
  private Appointment appointment;

  public void setReceiptID(Long aReceiptID) { this.receiptID = aReceiptID; }

  public void setTotalPrice(double aTotalPrice) { this.totalPrice = aTotalPrice; }
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  public Long getReceiptID()
  {
    return this.receiptID;
  }

  public double getTotalPrice()
  {
    return this.totalPrice;
  }

  @OneToOne
  public Appointment getAppointment()
  {
    return appointment;
  }
  public void setAppointment(Appointment aAppointment) {this.appointment=aAppointment;}
  

  public String toString()
  {
    return super.toString() + "["+
            "totalPrice" + ":" + getTotalPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "receiptID" + "=" + (getReceiptID() != null ? !getReceiptID().equals(this)  ? getReceiptID().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "appointment = "+(getAppointment()!=null?Integer.toHexString(System.identityHashCode(getAppointment())):"null");
  }
}