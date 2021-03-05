package ca.mcgill.ecse321.projectgroup04.dto;

public class CarDto {
	private Long carId;
	private String model;
	private String color;
	private String year;
	private CustomerDto customer;
	
	public CarDto(){
		
	}
	
	public CarDto(String aModel,String aColor,String aYear) {
		model=aModel;
		color=aColor;
		year=aYear;
	}
	public CustomerDto getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDto aCustomer) {
		customer=aCustomer;
	}

}
