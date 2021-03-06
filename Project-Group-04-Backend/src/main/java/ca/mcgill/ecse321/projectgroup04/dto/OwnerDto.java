package ca.mcgill.ecse321.projectgroup04.dto;

public class OwnerDto {
	private Long id;
	private String userId;
	private String password;
	
	public OwnerDto() {
		
	}
	
	public OwnerDto(String aName, String aPassword) {
		this.userId = aName;
		this.password = aPassword;
	}
	
	public String getName() {
		return this.userId;
	}
	
	public String getPassword() {
		return this.password;
	}
}
