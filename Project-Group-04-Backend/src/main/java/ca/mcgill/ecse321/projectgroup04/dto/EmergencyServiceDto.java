package ca.mcgill.ecse321.projectgroup04.dto;

public class EmergencyServiceDto {
	private int price;
	private String name;
	private String location;
	private FieldTechnicianDto fieldTechnician;
	private CustomerDto customer;
	private ReceiptDto receipt;
	private Long emergencyId;

	public EmergencyServiceDto() {

	}

	public EmergencyServiceDto(int aPrice, String aName, String aLocation) {
		price = aPrice;
		name = aName;
		location = aLocation;

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

	public void setCustomer(CustomerDto aCustomer) {
		customer = aCustomer;
	}

	public void setReceipt(ReceiptDto aReceipt) {
		receipt = aReceipt;
	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public ReceiptDto getReceipt() {
		return receipt;
	}

	public void setId(Long id) {
		this.emergencyId = id;
	}

	public Long getId() {
		return emergencyId;
	}

}