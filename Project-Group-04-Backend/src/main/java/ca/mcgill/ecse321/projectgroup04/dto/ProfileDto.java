package ca.mcgill.ecse321.projectgroup04.dto;

public class ProfileDto {

	private String addressLine;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	private String zipCode;
	private String emailAddress;
	private Long profileId;

	public ProfileDto() {

	}

	public ProfileDto(String aAddressLine, String aPhoneNumber, String aFirstName, String aLastName, String aZipCode,
			String aEmailAddress) {
		addressLine = aAddressLine;
		phoneNumber = aPhoneNumber;
		firstName = aFirstName;
		lastName = aLastName;
		zipCode = aZipCode;
		emailAddress = aEmailAddress;

	}

	public void setAddressLine(String aAddressLine) {
		addressLine = aAddressLine;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setPhoneNumber(String aPhoneNumber) {

		phoneNumber = aPhoneNumber;

	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setFirstName(String aFirstName) {
		firstName = aFirstName;

	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String aLastName) {
		lastName = aLastName;

	}

	public String getLastName() {
		return lastName;
	}

	public void setZipCode(String aZipCode) {

		zipCode = aZipCode;

	}

	public String getZipCode() {
		return zipCode;
	}

	public void setEmailAddress(String aEmailAddress) {

		emailAddress = aEmailAddress;

	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long id) {
		this.profileId = id;
	}

}