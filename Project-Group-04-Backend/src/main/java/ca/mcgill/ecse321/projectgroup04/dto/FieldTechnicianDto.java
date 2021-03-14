package ca.mcgill.ecse321.projectgroup04.dto;

public class FieldTechnicianDto {
	private String name;
	private Long technicianId;
	public FieldTechnicianDto() {
		
	}
	
	public FieldTechnicianDto(String aName) {
		name = aName;
	}
	
	public String getName() {
		return name;
	}
	
	public Long getTechnicianId() {
		return technicianId;
	}
	

}