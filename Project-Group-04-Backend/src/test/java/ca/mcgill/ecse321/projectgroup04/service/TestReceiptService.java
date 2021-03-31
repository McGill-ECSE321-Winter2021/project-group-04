package ca.mcgill.ecse321.projectgroup04.service;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup04.dao.ReceiptRepository;
import ca.mcgill.ecse321.projectgroup04.model.Receipt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class TestReceiptService {

	@Mock
	private ReceiptRepository receiptRepository;

	@InjectMocks
	private ReceiptService service;

	private static final Long RECEIPT_ID1 = 2345l;
	private static final double TOTAL_PRICE1 = 50;

	private static final Long RECEIPT_ID2 = 5312l;
	private static final double TOTAL_PRICE2 = 30;

	@BeforeEach
	public void setMockOutput() {
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(receiptRepository.save(any(Receipt.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(receiptRepository.findReceiptByReceiptId(anyLong()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(RECEIPT_ID1)) {
						Receipt receipt = new Receipt();
						receipt.setReceiptId(RECEIPT_ID1);
						receipt.setTotalPrice(TOTAL_PRICE1);

						return receipt;
					}
					if (invocation.getArgument(0).equals(RECEIPT_ID2)) {
						Receipt receipt = new Receipt();
						receipt.setReceiptId(RECEIPT_ID2);
						receipt.setTotalPrice(TOTAL_PRICE2);

						return receipt;
					}
					return null;
				});
		lenient().when(receiptRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			List<Receipt> receipts = new ArrayList<>();
			Receipt receipt1 = new Receipt();
			receipt1.setReceiptId(RECEIPT_ID1);
			receipt1.setTotalPrice(TOTAL_PRICE1);

			Receipt receipt2 = new Receipt();
			receipt2.setReceiptId(RECEIPT_ID2);
			receipt2.setTotalPrice(TOTAL_PRICE2);

			receipts.add(receipt1);
			receipts.add(receipt2);

			return receipts;
		});
	}

	@Test
	public void TestGetReceipt1() {
		Long receiptId = 2345l;
		Receipt receipt = null;

		try {
			receipt = service.getReceipt(receiptId);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(receipt);
		assertEquals(TOTAL_PRICE1, receipt.getTotalPrice());

	}

	@Test
	public void TestGetReceipt2() {
		Long receiptId = 5312l;
		Receipt receipt = null;

		try {
			receipt = service.getReceipt(receiptId);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(receipt);
		assertEquals(TOTAL_PRICE2, receipt.getTotalPrice());

	}

	@Test
	public void TestGetReceiptInvalidId() {
		Long invalidId = 1959l;
		Receipt receipt = null;

		String error = null;

		try {
			receipt = service.getReceipt(invalidId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(receipt);
		assertEquals(error, "No receipt with such ID exist!");
	}

	@Test
	public void TestCreateReceipt() {
		double totalPrice = 40;

		Receipt receipt = null;
		try {
			receipt = service.createReceipt(totalPrice);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(receipt);
		assertEquals(totalPrice, receipt.getTotalPrice());
	}

	@Test
	public void TestCreateReceiptTotalPrice0() {
		double totalPrice = 0;

		Receipt receipt = null;

		String error = null;
		try {
			receipt = service.createReceipt(totalPrice);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(receipt);
		assertEquals(error, "Total Price can't be 0");
	}

	@Test
	public void TestCreateReceiptTotalPriceNegative() {
		double totalPrice = -10;

		Receipt receipt = null;

		String error = null;
		try {
			receipt = service.createReceipt(totalPrice);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(receipt);
		assertEquals(error, "Total Price can't be negative");
	}

}
