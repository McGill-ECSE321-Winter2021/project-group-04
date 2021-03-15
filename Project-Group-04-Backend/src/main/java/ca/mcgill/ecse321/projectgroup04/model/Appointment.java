
package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.*;

@Entity
@Table(name = "appointment")
public class Appointment {

  private Long appointmentId;

  // Appointment Associations
  private Customer customer;
  private BookableService bookableServices;
  private GarageTechnician technician;
  private TimeSlot timeSlot;
  private AppointmentReminder reminder;
  private Receipt receipt;

  public void setAppointmentId(Long aAppointmentId) {
    this.appointmentId = aAppointmentId;
  }

  public void setReminder(AppointmentReminder aReminder) {
    this.reminder = aReminder;
  }

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  public Long getAppointmentId() {
    return this.appointmentId;
  }

  @ManyToOne
  public Customer getCustomer() {
    return this.customer;
  }

  @ManyToOne
  public BookableService getBookableServices() {
    return this.bookableServices;
  }

  @ManyToOne
  public GarageTechnician getTechnician() {
    return this.technician;
  }

  @ManyToOne
  public TimeSlot getTimeSlot() {
    return this.timeSlot;
  }

  @OneToOne // (mappedBy = "appointment", cascade = {CascadeType.ALL})
  public AppointmentReminder getReminder() {
    return this.reminder;
  }

  public void setReceipt(Receipt aReceipt) {
    this.receipt = aReceipt;
  }

  @OneToOne
  public Receipt getReceipt() {
    return this.receipt;
  }

  public void setCustomer(Customer aCustomer) {
    this.customer = aCustomer;
  }

  public void setBookableServices(BookableService aServices) {
    this.bookableServices = aServices;
  }

  public void setTechnician(GarageTechnician aTechnician) {
    this.technician = aTechnician;
  }

  public void setTimeSlot(TimeSlot aNewTimeSlot) {
    this.timeSlot = aNewTimeSlot;
  }

  public String toString() {
    return super.toString() + "[" + "]" + System.getProperties().getProperty("line.separator") + "  " + "appointmentId"
        + "="
        + (getAppointmentId() != null
            ? !getAppointmentId().equals(this) ? getAppointmentId().toString().replaceAll("  ", "    ") : "this"
            : "null")
        + System.getProperties().getProperty("line.separator") + "  " + "customer = "
        + (getCustomer() != null ? Integer.toHexString(System.identityHashCode(getCustomer())) : "null")
        + System.getProperties().getProperty("line.separator") + "  " + "services = "
        + (getBookableServices() != null ? Integer.toHexString(System.identityHashCode(getBookableServices())) : "null")
        + System.getProperties().getProperty("line.separator") + "  " + "technician = "
        + (getTechnician() != null ? Integer.toHexString(System.identityHashCode(getTechnician())) : "null")
        + System.getProperties().getProperty("line.separator") + "  " + "timeSlot = "
        + (getTimeSlot() != null ? Integer.toHexString(System.identityHashCode(getTimeSlot())) : "null")
        + System.getProperties().getProperty("line.separator") + "  " + "reminder = "
        + (getReminder() != null ? Integer.toHexString(System.identityHashCode(getReminder())) : "null")
        + System.getProperties().getProperty("line.separator") + "  " + "receipt = "
        + (getReceipt() != null ? Integer.toHexString(System.identityHashCode(getReceipt())) : "null")
        + System.getProperties().getProperty("line.separator");
  }
}