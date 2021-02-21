/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.*;
import java.util.*;

@Entity
public abstract class Service {

    private String serviceID;
    @Id
    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String aServiceID) {
        this.serviceID = aServiceID;
    }

    private int price;

    public void setPrice(int aPrice) {
        this.price = aPrice;
    }

    public int getPrice() {
        return this.price;
    }

    private String name;
    public void setName(String aName) {
        this.name = aName;
    }

    public String getName() {
        return this.name;
    }

    //Service Associations
    private List<Appointment> appointments;

    @OneToMany(cascade = {CascadeType.ALL})
    public List<Appointment> getAppointments() {
        return this.appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    private AutoRepairShop autoRepairShop;

    @ManyToOne
    public AutoRepairShop getAutoRepairShop() {
        return this.autoRepairShop;
    }

    /* Code from template association_SetOneToMany */
    public void setAutoRepairShop(AutoRepairShop aAutoRepairShop) {
      this.autoRepairShop = aAutoRepairShop;
    }

    public String toString() {
        return super.toString() + "[" +
                "price" + ":" + getPrice() + "," +
                "name" + ":" + getName() + "]" + System.getProperties().getProperty("line.separator") +
                "  " + "serviceID" + "=" + (getServiceID() != null ? !getServiceID().equals(this) ? getServiceID().toString().replaceAll("  ", "    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "autoRepairShop = " + (getAutoRepairShop() != null ? Integer.toHexString(System.identityHashCode(getAutoRepairShop())) : "null");
    }
}