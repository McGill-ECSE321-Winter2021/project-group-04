/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.*;

@Entity
@Table(name = "garageTechnician")
public class GarageTechnician extends Technician {

    private List<Appointment> appointments;

    @OneToMany(cascade = {CascadeType.ALL})
    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    
    @Override
    public Long getTechnicianID() {
        return super.getTechnicianID();
    }

    @Override
    public void setTechnicianID(Long aTechnicianID) {
        super.setTechnicianID(aTechnicianID);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String aName) {
        super.setName(aName);
    }
}