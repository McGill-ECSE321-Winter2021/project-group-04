package ca.mcgill.ecse321.projectgroup04.dto;

import java.util.List;

import ca.mcgill.ecse321.projectgroup04.model.Appointment;

public class GarageTechnicianDto {
	private String name;
	private Long technicianId;
	private List<Appointment> appointments;
	
	
	
	public GarageTechnicianDto() {
	}
	
	public GarageTechnicianDto(String name, List<Appointment> appointments) {
		this.name = name;
		this.appointments = appointments;
		
	}

	public String getName() {
		return name;
	}
	public List<Appointment> getAppointments() {
		return appointments;
	}
	

}
