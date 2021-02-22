
package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.*;

@Entity
public class Appointment {

  private String appointmentID;

  // Appointment Associations
  private Customer customer;
  private Service services;
  private GarageTechnician technician;
  private TimeSlot timeSlot;
  private AppointmentReminder reminder;
  private Receipt receipt;
  private AutoRepairShop autoRepairShop;

  public void setAppointmentID(String aAppointmentID) {
    this.appointmentID = aAppointmentID;
  }

  public void setReminder(AppointmentReminder aReminder) {
    this.reminder = aReminder;
  }

  @Id
  public String getAppointmentID() {
    return this.appointmentID;
  }

  @OneToOne
  public Customer getCustomer() {
    return this.customer;
  }

  @ManyToOne
  public Service getServices() {
    return this.services;
  }

  @ManyToOne
  public GarageTechnician getTechnician() {
    return this.technician;
  }

  @OneToMany(cascade = { CascadeType.ALL })
  public TimeSlot getTimeSlot() {
    return this.timeSlot;
  }

  @ManyToOne
  public AppointmentReminder getReminder() {
    return this.reminder;
  }

  public void setReceipt(Receipt aReceipt) {
    this.receipt = aReceipt;
  }

  @ManyToOne
  public Receipt getReceipt() {
    return this.receipt;
  }

  @OneToOne
  public AutoRepairShop getAutoRepairShop() {
    return this.autoRepairShop;
  }

  public void setCustomer(Customer aCustomer) {
    this.customer = aCustomer;
  }

  public void setServices(Service aServices) {
    this.services = aServices;
  }

  public void setTechnician(GarageTechnician aTechnician) {
    this.technician = aTechnician;
  }

  public void setTimeSlot(TimeSlot aNewTimeSlot) {
    this.timeSlot = aNewTimeSlot;
  }

  public void setAutoRepairShop(AutoRepairShop aAutoRepairShop) {
    this.autoRepairShop = aAutoRepairShop;
  }

  public String toString() {
    return super.toString() + "[" + "]" + System.getProperties().getProperty("line.separator") + "  " + "appointmentID"
        + "="
        + (getAppointmentID() != null
            ? !getAppointmentID().equals(this) ? getAppointmentID().toString().replaceAll("  ", "    ") : "this"
            : "null")
        + System.getProperties().getProperty("line.separator") + "  " + "customer = "
        + (getCustomer() != null ? Integer.toHexString(System.identityHashCode(getCustomer())) : "null")
        + System.getProperties().getProperty("line.separator") + "  " + "services = "
        + (getServices() != null ? Integer.toHexString(System.identityHashCode(getServices())) : "null")
        + System.getProperties().getProperty("line.separator") + "  " + "technician = "
        + (getTechnician() != null ? Integer.toHexString(System.identityHashCode(getTechnician())) : "null")
        + System.getProperties().getProperty("line.separator") + "  " + "timeSlot = "
        + (getTimeSlot() != null ? Integer.toHexString(System.identityHashCode(getTimeSlot())) : "null")
        + System.getProperties().getProperty("line.separator") + "  " + "reminder = "
        + (getReminder() != null ? Integer.toHexString(System.identityHashCode(getReminder())) : "null")
        + System.getProperties().getProperty("line.separator") + "  " + "receipt = "
        + (getReceipt() != null ? Integer.toHexString(System.identityHashCode(getReceipt())) : "null")
        + System.getProperties().getProperty("line.separator") + "  " + "autoRepairShop = "
        + (getAutoRepairShop() != null ? Integer.toHexString(System.identityHashCode(getAutoRepairShop())) : "null");
  }
}