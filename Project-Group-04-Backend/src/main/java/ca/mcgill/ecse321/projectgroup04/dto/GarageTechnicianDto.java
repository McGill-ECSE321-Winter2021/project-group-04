package ca.mcgill.ecse321.projectgroup04.dto;

public class GarageTechnicianDto {
	private String name;
	private Long technicianId;
	public GarageTechnicianDto() {
	}

	public GarageTechnicianDto(String name, Long id) {
		this.name = name;
		technicianId=id;
	}

	public String getName() {
		return name;
	}
	public Long getTechnicianId() {
		return technicianId;
	}

}