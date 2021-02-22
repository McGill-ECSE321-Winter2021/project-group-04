/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/
package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fieldTechnician")
public class FieldTechnician extends Technician {
    
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

    private EmergencyService emergencyService;

    @OneToOne
    public EmergencyService getEmergencyService() {
        return this.emergencyService;
    }

    public void setEmergencyService(EmergencyService emergencyService) {
        this.emergencyService = emergencyService;
    }
}