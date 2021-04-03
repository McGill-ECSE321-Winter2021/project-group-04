package ca.mcgill.ecse321.projectgroup04.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup04.dto.*;
import ca.mcgill.ecse321.projectgroup04.model.*;
import ca.mcgill.ecse321.projectgroup04.service.CustomerService;
import ca.mcgill.ecse321.projectgroup04.service.ReceiptService;


@CrossOrigin(origins = "*")
@RestController
public class ReceiptsController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private ReceiptService receiptService;

    

    public ReceiptDto convertToDto(Receipt receipt) {
		if (receipt == null) {
			throw new IllegalArgumentException("There is no such Receipt!");
		}
		ReceiptDto receiptDto = new ReceiptDto(receipt.getTotalPrice());
		receiptDto.setId(receipt.getReceiptId());
		return receiptDto;
	}

    @GetMapping(value = { "/receipts", "/receipts/" })
	public List<ReceiptDto> getAllReceipts() {
		List<ReceiptDto> receiptDtos = new ArrayList<>();
		for (Receipt receipt : receiptService.getAllReceipts()) {
			receiptDtos.add(convertToDto(receipt));
		}
		return receiptDtos;
	}

	@GetMapping(value = { "/receipts/{userId}", "/receipts/{userId}/" })
	public List<ReceiptDto> getCustomerReceipts(@PathVariable("userId") String userId) {
		List<ReceiptDto> receiptDtos = new ArrayList<>();
		for (Receipt receipt : customerService.getCustomerReceipts(userId)) {
			receiptDtos.add(convertToDto(receipt));
		}
		return receiptDtos;
	}

	@GetMapping(value = { "/receipt/{Id}", "/receipt/{Id}/" })
	public ReceiptDto getReceiptById(@PathVariable("Id") Long Id) throws IllegalArgumentException {
		return convertToDto(receiptService.getReceipt(Id));
	}
}
