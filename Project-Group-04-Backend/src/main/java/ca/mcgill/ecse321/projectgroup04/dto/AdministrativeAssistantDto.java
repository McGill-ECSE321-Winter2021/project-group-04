package ca.mcgill.ecse321.projectgroup04.dto;

public class AdministrativeAssistantDto {
	private Long id;
	private String userId;
	private String password;
	
	public AdministrativeAssistantDto()
	{}
	
	public AdministrativeAssistantDto(String userId, String password) {
		this.userId = userId;
		this.password = password;
		
	}
	
	public String getUserId () {
		return this.userId;
	}
	public String getPassword() {
		return this.password;
	}

}
