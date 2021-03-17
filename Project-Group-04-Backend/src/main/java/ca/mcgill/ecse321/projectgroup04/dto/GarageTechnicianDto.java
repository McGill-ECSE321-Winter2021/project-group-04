package ca.mcgill.ecse321.projectgroup04.dto;

public class GarageTechnicianDto {
	private String name;
	private Long technicianId;

	public GarageTechnicianDto() {
	}

	public GarageTechnicianDto(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Long getTechnicianId() {
		return technicianId;
	}

	public void setTechnicianId(Long id) {
		this.technicianId = id;
	}

}