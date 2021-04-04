package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*;
import ca.mcgill.ecse321.projectgroup04.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class GarageTechnicianService {

    @Autowired
    private GarageTechnicianRepository garageTechnicianRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Transactional
    public GarageTechnician createGarageTechnician(String name) {

        if (name == "" || name == null|| name.equals("undefined")) {
            throw new IllegalArgumentException("Name can't be empty");
        }
        GarageTechnician existingGarageTechnician = getGarageTechnicianByName(name);
        if (existingGarageTechnician != null) {
            throw new IllegalArgumentException("Garage Technician with this name already exists");
        }

        GarageTechnician garageTechnician = new GarageTechnician();
        garageTechnician.setName(name);
        garageTechnicianRepository.save(garageTechnician);
        return garageTechnician;
    }

    @Transactional
    public List<GarageTechnician> getAllGarageTechnicians() {
        return (List<GarageTechnician>) garageTechnicianRepository.findAll();
    }

    @Transactional
    public GarageTechnician getGarageTechnicianById(Long technicianId) {
        GarageTechnician garageTechnician = garageTechnicianRepository.findGarageTechnicianByTechnicianId(technicianId);
        if (garageTechnician == null) {
            throw new IllegalArgumentException("No garage technician with such id!");
        }
        return garageTechnician;
    }

    @Transactional
    public GarageTechnician getGarageTechnicianByName(String name) {
        GarageTechnician garageTech = null;
        for (GarageTechnician garageTechnician : garageTechnicianRepository.findAll()) {
            if (name.contains(garageTechnician.getName())) {
                garageTech = garageTechnician;
                break;
            }
        }

        return garageTech;
    }

    public GarageTechnician deleteGarageTechnician(GarageTechnician garageTechnician) {

        List<Appointment> appointmentsList = (List<Appointment>) appointmentRepository.findAll();
        

        for (Appointment appointment : appointmentsList) {
            if (appointment.getTechnician().equals(garageTechnician)) {
                throw new IllegalArgumentException("This garage technician still has appointments");
            }

        }
        garageTechnicianRepository.delete(garageTechnician);
        garageTechnician = null;
        return garageTechnician;

    }
}
