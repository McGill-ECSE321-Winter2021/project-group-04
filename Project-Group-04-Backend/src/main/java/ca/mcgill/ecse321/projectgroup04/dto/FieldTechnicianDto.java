package ca.mcgill.ecse321.projectgroup04.dto;

public class FieldTechnicianDto {
	private Long technicianId;
	private String name;
	
	public FieldTechnicianDto() {
		
	}
	
	public FieldTechnicianDto(String aName) {
		name = aName;
	}
	
	public String getName() {
		return name;
	}

	
	public void setName(String myName) {
		name = myName;
	}
	

}