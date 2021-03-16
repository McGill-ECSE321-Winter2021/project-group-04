package ca.mcgill.ecse321.projectgroup04.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.projectgroup04.dao.ReceiptRepository;

@ExtendWith(MockitoExtension.class)
public class TestReceiptService {

	@Mock
	private ReceiptRepository receiptRepository;
	
}
