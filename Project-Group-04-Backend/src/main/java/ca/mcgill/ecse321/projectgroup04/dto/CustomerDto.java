package ca.mcgill.ecse321.projectgroup04.dto;

public class CustomerDto {
	private String userID;
	private String password;
	private String id;
	
	
	public CustomerDto() {
	}

	
	public CustomerDto(String userID, String password, String id) {
		super();
		this.userID = userID;
		this.password = password;
		this.id = id;
	}

	public String getUserID() {
		return userID;
	}
	
	public String getPassword() {
		return password;
	}


}
