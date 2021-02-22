/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;


import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Entity;
import java.sql.Time;
import java.sql.Date;

@Entity
@Table(name = "checkupReminder")
public class CheckupReminder extends Reminder
{
	@Override
  public void setReminderID(Long aReminderID)
  {
    super.setReminderID(aReminderID);
  }
	@Override
  public void setDate(Date aDate)
  {
    super.setDate(aDate);
  }
	@Override
  public void setTime(Time aTime)
  {
    super.setTime(aTime);
  }
	@Override
  public void setMessage(String aMessage)
  {
    super.setMessage(aMessage);
  }
	@Override
  public Long getReminderID()
  {
    return super.getReminderID();
  }
	@Override
  public Date getDate()
  {
    return super.getDate();
  }
	@Override
  public Time getTime()
  {
    return super.getTime();
  }
	@Override
  public String getMessage()
  {
    return super.getMessage();
  }
	@Override
  @ManyToOne
  public Customer getCustomer()
  {
    return super.getCustomer();
  }
	@Override
  public void setCustomer(Customer aCustomer)
  {
    super.setCustomer(aCustomer);
  }


}