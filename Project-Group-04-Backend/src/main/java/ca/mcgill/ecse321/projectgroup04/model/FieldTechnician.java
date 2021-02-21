/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/
package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class FieldTechnician extends Technician {
    @Id
    @Override
    public String getTechnicianID() {
        return super.getTechnicianID();
    }

    @Override
    public void setTechnicianID(String aTechnicianID) {
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

    private EmergencyService service;

    @OneToOne
    public EmergencyService getService() {
        return this.service;
    }

    public void setService(EmergencyService emergencyService) {
        this.service = emergencyService;
    }
}