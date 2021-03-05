package ca.mcgill.ecse321.projectgroup04.dto;

public class EmergencyServiceDto {
	private Long serviceId;
	private int price;
	private String name;
	private String location;
	private FieldTechnicianDto fieldTechnician;
	
	public EmergencyServiceDto() {
		
	}
	
	public EmergencyServiceDto(int aPrice, String aName, String aLocation, FieldTechnicianDto aFieldTechnician) {
		price = aPrice;
		name = aName;
		location = aLocation;
		fieldTechnician = aFieldTechnician;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public FieldTechnicianDto getFieldTechnician() {
		return fieldTechnician;
	}
	
	public void setPrice(int myPrice) {
		price = myPrice;
	}
	
	public void setName(String myName) {
		name = myName;
	}
	
	public void setLocation(String myLocation) {
		location = myLocation;
	}
	
	public void setFieldTechnician(FieldTechnicianDto myFieldTech) {
		fieldTechnician = myFieldTech;
	}
	
}
