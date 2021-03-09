package ca.mcgill.ecse321.projectgroup04.dto;

public class FieldTechnicianDto {
	private String name;
	private EmergencyServiceDto emergencyService;
	
	public FieldTechnicianDto() {
		
	}
	
	public FieldTechnicianDto(String aName) {
		name = aName;
	}
	
	public String getName() {
		return name;
	}
	
	public EmergencyServiceDto getEmergencyService() {
		return emergencyService;
	}
	
	public void setName(String myName) {
		name = myName;
	}
	
	public void setEmergencyService(EmergencyServiceDto myEmergencyService) {
		emergencyService = myEmergencyService;
	}
}