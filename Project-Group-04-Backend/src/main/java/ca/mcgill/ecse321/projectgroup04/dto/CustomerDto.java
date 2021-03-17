package ca.mcgill.ecse321.projectgroup04.dto;

import java.util.List;

public class CustomerDto {
	private String userID;
	private String password;
	private CarDto car;
	private ProfileDto profile;
	private Long customerId;

	private List<ReminderDto> reminders;

	public CustomerDto() {
	}

	public CustomerDto(String userID, String password) {
		super();
		this.userID = userID;
		this.password = password;
	}

	public String getUserID() {
		return userID;
	}

	public String getPassword() {
		return password;
	}

	public CarDto getCar() {
		return car;
	}

	public ProfileDto getProfile() {
		return profile;
	}

	public List<ReminderDto> getReminders() {
		return reminders;
	}

	public void setCar(CarDto aCar) {
		car = aCar;
	}

	public void setProfile(ProfileDto aProfile) {
		profile = aProfile;
	}

	public void setReminders(List<ReminderDto> aReminders) {
		reminders = aReminders;
	}

	public Long getId() {
		return customerId;
	}

	public void setId(Long id) {
		this.customerId = id;
	}

}