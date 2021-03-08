package ca.mcgill.ecse321.projectgroup04.dto;

public class CarDto {
	private String model;
	private String color;
	private String year;
	private CustomerDto customer;

	public CarDto() {

	}

	public CarDto(String aModel, String aColor, String aYear) {
		model = aModel;
		color = aColor;
		year = aYear;
	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto aCustomer) {
		customer = aCustomer;
	}

	/**
	 * @return String return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @return String return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @return String return the year
	 */
	public String getYear() {
		return year;
	}

}