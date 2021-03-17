package ca.mcgill.ecse321.projectgroup04.dto;

public class AdministrativeAssistantDto {
	private Long adminId;
	private String userId;
	private String password;

	public AdministrativeAssistantDto() {
	}

	public AdministrativeAssistantDto(String userId, String password) {
		this.userId = userId;
		this.password = password;

	}

	public String getUserId() {
		return this.userId;
	}

	public String getPassword() {
		return this.password;
	}

	public Long getId() {
		return this.adminId;
	}

	public void setId(Long id) {
		this.adminId = id;
	}
}