package ca.mcgill.ecse321.projectgroup04.dto;

public class AppointmentDto {
	private CustomerDto customer;
	private BookableServiceDto bookableService;
	private GarageTechnicianDto technician;
	private TimeSlotDto timeSlot;
	private AppointmentReminderDto reminder;
	private ReceiptDto receipt;
	private Long appointmentId;

	public AppointmentDto() {

	}

	public AppointmentDto(CustomerDto aCustomer, BookableServiceDto aBookableService,
			GarageTechnicianDto aGarageTechnician, TimeSlotDto aTimeSlot, AppointmentReminderDto aReminder,
			ReceiptDto aReceipt) {
		customer = aCustomer;
		bookableService = aBookableService;
		technician = aGarageTechnician;
		timeSlot = aTimeSlot;
		reminder = aReminder;
		receipt = aReceipt;

	}

	public CustomerDto getCustomer() {
		return customer;

	}

	public BookableServiceDto getBookableService() {
		return bookableService;
	}

	public GarageTechnicianDto getTechnician() {
		return technician;

	}

	public ReceiptDto getReceipt() {
		return receipt;
	}

	public AppointmentReminderDto getReminder() {
		return reminder;
	}

	public void setReminder(AppointmentReminderDto aReminder) {
		reminder = aReminder;
	}

	public void setReceipt(ReceiptDto aReceipt) {
		receipt = aReceipt;
	}

	public void setCustomer(CustomerDto aCustomer) {
		customer = aCustomer;
	}

	public void setBookableService(BookableServiceDto aService) {
		bookableService = aService;
	}

	public void setTimeSlot(TimeSlotDto aTimeSlot) {
		timeSlot = aTimeSlot;
	}

	public void setGarageTechnician(GarageTechnicianDto aTechnician) {
		technician = aTechnician;
	}

	public TimeSlotDto getTimeSlot() {
		return timeSlot;
	}

	public void setId(Long id) {
		this.appointmentId = id;
	}

	public Long getId() {
		return this.appointmentId;
	}
}