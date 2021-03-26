/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/
package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fieldTechnician")
public class FieldTechnician extends Technician {
	
	private boolean isAvailable = true;

    @Override
    public Long getTechnicianId() {
        return super.getTechnicianId();
    }

    @Override
    public void setTechnicianId(Long aTechnicianId) {
        super.setTechnicianId(aTechnicianId);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String aName) {
        super.setName(aName);
    }
    
    public boolean getIsAvailable() {
    	return this.isAvailable;
    }
    
    public void setIsAvailable(boolean availability) {
    	this.isAvailable = availability;
    }

}