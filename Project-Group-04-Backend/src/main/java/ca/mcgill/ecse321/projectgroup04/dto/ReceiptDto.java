package ca.mcgill.ecse321.projectgroup04.dto;

public class ReceiptDto {
	private Long receiptId;
	private double totalPrice;
	private AppointmentDto appointment;
	
	public ReceiptDto() {
		
	}
	public ReceiptDto(double totalPrice) {
		this.totalPrice=totalPrice;
	}

	
	public double getTotalPrice() {
		return totalPrice;
	}
	public AppointmentDto getAppointment() {
		return appointment;
	}
	public void setTotalPrice(double aTotalPrice) {
		totalPrice=aTotalPrice;
	}
	public void setAppointment(AppointmentDto aAppointment) {
		appointment=aAppointment;
	}

}
