package ca.mcgill.ecse321.projectgroup04.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup04.model.*;

public interface ReceiptRepository extends CrudRepository <Receipt, String> {
	Receipt findReceiptByReceiptID(String receiptID);
    Receipt findByAppointment(Appointment appointment);

}
