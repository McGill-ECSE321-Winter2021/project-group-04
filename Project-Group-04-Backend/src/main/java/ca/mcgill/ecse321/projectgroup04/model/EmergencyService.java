/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.Entity;

import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "emergencyService")
public class EmergencyService extends Service {
    private String location;

    public void setLocation(String aLocation) {
        this.location = aLocation;
    }

    public String getLocation() {
        return this.location;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String aName) {
        super.setName(aName);
    }

    @Override
    public Long getServiceId() {
        return super.getServiceId();
    }

    @Override
    public void setServiceId(Long aServiceId) {
        super.setServiceId(aServiceId);
    }

    @Override
    public void setPrice(int aPrice) {
        super.setPrice(aPrice);
    }

    @Override
    public int getPrice() {
        return super.getPrice();
    }

    private Customer customer;

    @OneToOne
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer aCustomer) {
        customer = aCustomer;
    }

    private Receipt receipt;

    @OneToOne
    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt aReceipt) {
        receipt = aReceipt;
    }

    private FieldTechnician technician;

    @OneToOne
    public FieldTechnician getTechnician() {
        return this.technician;
    }

    public void setTechnician(FieldTechnician fieldTechnician) {
        this.technician = fieldTechnician;
    }
}