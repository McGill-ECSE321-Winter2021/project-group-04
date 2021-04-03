package ca.mcgill.ecse321.projectgroup04.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup04.dao.BookableServiceRepository;
import ca.mcgill.ecse321.projectgroup04.model.BookableService;

@ExtendWith(MockitoExtension.class)
public class TestBookableService {

	@Mock
	private BookableServiceRepository bookableServiceRepository;

	@InjectMocks
	private BookableServiceService service;

	private static final String BOOKABLESERVICE_NAME = "TestName";
	private static final int BOOKABLESERVICE_DURATION = 30;
	private static final int BOOKABLESERVICE_PRICE = 50;

	@BeforeEach
	public void setMockOutput() {
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(bookableServiceRepository.save(any(BookableService.class))).thenAnswer(returnParameterAsAnswer);

		lenient().when(bookableServiceRepository.findBookableServiceByName(anyString()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(BOOKABLESERVICE_NAME)) {

						BookableService bookableService = new BookableService();
						bookableService.setDuration(BOOKABLESERVICE_DURATION);
						;
						bookableService.setPrice(BOOKABLESERVICE_PRICE);

						return bookableService;
					}
					return null;
				});
		lenient().when(bookableServiceRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {

			List<BookableService> bookableServices = new ArrayList<BookableService>();

			BookableService bookableService = new BookableService();
			bookableService.setName(BOOKABLESERVICE_NAME);
			bookableService.setDuration(BOOKABLESERVICE_DURATION);

			bookableService.setPrice(BOOKABLESERVICE_PRICE);

			bookableServices.add(bookableService);

			return bookableServices;
		});

	}

	@Test
	public void TestCreateBookableService() {

		String name = "Testname";
		int duration = 30;
		int price = 50;

		BookableService bookableService = null;
		try {
			bookableService = service.createBookableService(name, price, duration);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(bookableService);
		assertEquals(name, bookableService.getName());
		assertEquals(duration, bookableService.getDuration());
		assertEquals(price, bookableService.getPrice());

	}

	@Test
	public void TestCreateBookableServiceNoName() {
		String name = "";
		int duration = 30;
		int price = 50;

		String error = null;

		BookableService bookableService = null;
		try {
			bookableService = service.createBookableService(name, price, duration);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(bookableService);
		assertEquals(error, "Name can't be empty");

	}

	@Test
	public void TestCreateBookableServiceNoDuration() {
		String name = "NewNameDuration";
		int duration = 0;
		int price = 50;

		String error = null;

		BookableService bookableService = null;
		try {
			bookableService = service.createBookableService(name, price, duration);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(bookableService);
		assertEquals(error, "Duration can't be equal to 0");

	}

	@Test
	public void TestCreateBookableServiceNoPrice() {
		String name = "NewNamePrice";
		int duration = 30;
		int price = 0;

		String error = null;

		BookableService bookableService = null;
		try {
			bookableService = service.createBookableService(name, price, duration);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(bookableService);
		assertEquals(error, "Price can't be 0");

	}

	@Test
	public void TestCreateBookableServiceAlreadyExists() {
		String name = "TestName";
		int duration = 30;
		int price = 50;

		String error = null;

		BookableService bookableService = null;
		try {
			bookableService = service.createBookableService(name, price, duration);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(bookableService);
		assertEquals(error, "Bookable Service with this name already exists");

	}

	@Test
	public void TestDeleteBookableService() {
		String name = "TestName";
		int duration = 30;
		int price = 50;

		BookableService bookableService = new BookableService();
		bookableService.setName(name);
		bookableService.setDuration(duration);
		bookableService.setPrice(price);
		try {
			bookableService = service.deleteBookableService(bookableService);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNull(bookableService);

	}


  @Test
	public void TestEditDurationBookableService() {
		String name = "TestNameEditDuration";
		int newDuration = 40;
		int duration = 30;
		int price = 50;

		BookableService bookableService = new BookableService();
		bookableService.setName(name);
		bookableService.setDuration(duration);
		bookableService.setPrice(price);
		try {
			bookableService = service.editBookableService(bookableService, newDuration, price);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(bookableService);
		assertEquals(name, bookableService.getName());
		assertEquals(newDuration, bookableService.getDuration());
		assertEquals(price, bookableService.getPrice());
	}

	@Test
	public void TestEditPriceBookableService() {
		String name = "TestNameEditPrice";
		int newPrice = 40;
		int duration = 30;
		int price = 50;

		BookableService bookableService = new BookableService();
		bookableService.setName(name);
		bookableService.setDuration(duration);
		bookableService.setPrice(price);
		try {
			bookableService = service.editBookableService(bookableService, duration, newPrice);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(bookableService);
		assertEquals(name, bookableService.getName());
		assertEquals(duration, bookableService.getDuration());
		assertEquals(newPrice, bookableService.getPrice());
	}

}
