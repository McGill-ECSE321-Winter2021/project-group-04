package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*;
import ca.mcgill.ecse321.projectgroup04.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ReceiptService {
    @Autowired
    private ReceiptRepository receiptRepository;
    
    
    @Transactional
    public Receipt createReceipt(double aTotalPrice) {
        if (aTotalPrice == 0) {
            throw new IllegalArgumentException("Total Price can't be 0");
        }
        if (aTotalPrice < 0) {
            throw new IllegalArgumentException("Total Price can't be negative");
        }
        Receipt receipt = new Receipt();
        receipt.setTotalPrice(aTotalPrice);
        receiptRepository.save(receipt);
        return receipt;
    }

    @Transactional
    public Receipt getReceipt(Long aReceiptId) {
        Receipt receipt = receiptRepository.findReceiptByReceiptId(aReceiptId);
        if (receipt != null) {
            return receipt;
        } else {
            throw new IllegalArgumentException("No receipt with such ID exist!");
        }
    }

    @Transactional
    public List<Receipt> getAllReceipts() {
        return (List<Receipt>) receiptRepository.findAll();
    }
}
