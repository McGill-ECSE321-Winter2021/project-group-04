package ca.mcgill.ecse321.projectgroup04.dto;

public class OwnerDto {
	private Long id;
	private String name;
	private String password;
	
	public OwnerDto() {
		
	}
	
	public OwnerDto(String aName, String aPassword) {
		this.name = aName;
		this.password = aPassword;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPassword() {
		return this.password;
	}
}
