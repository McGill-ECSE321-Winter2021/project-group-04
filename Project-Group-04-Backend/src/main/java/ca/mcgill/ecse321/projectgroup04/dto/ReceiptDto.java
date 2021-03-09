package ca.mcgill.ecse321.projectgroup04.dto;

public class ReceiptDto {
	private Long receiptId;
	private double totalPrice;

	
	public ReceiptDto() {
		
	}
	public ReceiptDto(double totalPrice) {
		this.totalPrice=totalPrice;
	}

	
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double aTotalPrice) {
		totalPrice=aTotalPrice;
	}


}