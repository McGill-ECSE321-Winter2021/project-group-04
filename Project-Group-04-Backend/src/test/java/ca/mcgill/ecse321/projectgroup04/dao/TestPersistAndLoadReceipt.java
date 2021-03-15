package ca.mcgill.ecse321.projectgroup04.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup04.model.Receipt;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistAndLoadReceipt {

    @Autowired
    private ReceiptRepository receiptRepository;

    @AfterEach
    public void clearDataBase() {
        receiptRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testPersistAndLoadReceipt() {

        Double totalPrice = 100.0;
        Receipt receipt = new Receipt();
        receipt.setTotalPrice(totalPrice);

        receiptRepository.save(receipt);
        Long receiptId = receipt.getReceiptId();

        receipt = null;
        receipt = receiptRepository.findReceiptByReceiptId(receiptId);

        assertNotNull(receipt);
        assertEquals(totalPrice, receipt.getTotalPrice());
        assertEquals(receiptId, receipt.getReceiptId());

    }
}
