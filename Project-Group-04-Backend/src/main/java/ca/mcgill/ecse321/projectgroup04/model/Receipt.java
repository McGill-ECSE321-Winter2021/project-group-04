package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.*;

@Entity
@Table(name = "receipt")
public class Receipt {

  private Long receiptId;
  private double totalPrice;

  


  public void setReceiptId(Long aReceiptId) { this.receiptId = aReceiptId; }

  public void setTotalPrice(double aTotalPrice) { this.totalPrice = aTotalPrice; }
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  public Long getReceiptId()
  {
    return this.receiptId;
  }

  public double getTotalPrice()
  {
    return this.totalPrice;
  }


  


}