package ca.mcgill.ecse321.projectgroup04.dto;

import java.util.List;

import ca.mcgill.ecse321.projectgroup04.model.Appointment;

public class GarageTechnicianDto {
	private String name;
	private Long technicianId;
	private List<AppointmentDto> appointments;
	
	
	
	public GarageTechnicianDto() {
	}
	
	public GarageTechnicianDto(String name) {
		this.name = name;
		
		
	}

	public String getName() {
		return name;
	}
	public List<AppointmentDto> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<AppointmentDto> aAppointments) {
		appointments=aAppointments;
	}
	

}
