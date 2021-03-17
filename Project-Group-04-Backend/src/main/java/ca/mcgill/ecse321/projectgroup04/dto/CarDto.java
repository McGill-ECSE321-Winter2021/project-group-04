package ca.mcgill.ecse321.projectgroup04.dto;

public class CarDto {
	private String model;
	private String color;
	private String year;
	private Long carId;

	public CarDto() {

	}

	public CarDto(String aModel, String aColor, String aYear) {
		model = aModel;
		color = aColor;
		year = aYear;
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

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

}