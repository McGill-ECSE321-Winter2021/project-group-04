package ca.mcgill.ecse321.projectgroup04.dto;

public class FieldTechnicianDto {
	private String name;
	private Long technicianId;
	private boolean isAvailable;

	public FieldTechnicianDto() {

	}

	public FieldTechnicianDto(String aName, boolean bool) {
		this.name = aName;
		this.isAvailable = bool;
	}

	public String getName() {
		return name;
	}

	public Long getTechnicianId() {
		return technicianId;
	}

	public boolean getIsAvailable() {
		return isAvailable;
	}
	
	public void setIsAvailable(boolean bool) {
		this.isAvailable = bool;
	}

	public void setId(Long id) {
		this.technicianId = id;
	}

}