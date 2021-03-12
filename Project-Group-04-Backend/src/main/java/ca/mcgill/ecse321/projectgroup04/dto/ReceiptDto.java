package ca.mcgill.ecse321.projectgroup04.dto;

public class ReceiptDto {
	private double totalPrice;
	private Long receiptId;

	public ReceiptDto() {

	}

	public ReceiptDto(double totalPrice,Long aReceiptId) {
		this.totalPrice = totalPrice;
		receiptId=aReceiptId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double aTotalPrice) {
		totalPrice = aTotalPrice;
	}
	
	public Long getReceiptId() {
		return receiptId;
	}

}