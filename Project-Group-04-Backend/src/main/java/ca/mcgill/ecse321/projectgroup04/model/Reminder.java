/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.*;

@Entity
@Table(name = "reminder")
public abstract class Reminder
{

  //Reminder Attributes
  private Long reminderId;
  private Date date;
  private Time time;
  private String message;

  //Reminder Associations
  

  
  public void setReminderId(Long aReminderId)
  {
    this.reminderId=aReminderId;
  }

  public void setDate(Date aDate)
  {
    this.date=aDate;
  }

  public void setTime(Time aTime)
  {
    this.time=aTime;
  }

  public void setMessage(String aMessage)
  {
    this.message=aMessage;
  }
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  public Long getReminderId()
  {
    return this.reminderId;
  }

  public Date getDate()
  {
    return this.date;
  }

  public Time getTime()
  {
    return this.time;
  }

  public String getMessage()
  {
    return this.message;
  }
  



}