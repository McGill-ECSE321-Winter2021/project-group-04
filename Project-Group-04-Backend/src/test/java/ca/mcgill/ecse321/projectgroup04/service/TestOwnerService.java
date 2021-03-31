package ca.mcgill.ecse321.projectgroup04.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import static org.mockito.Mockito.lenient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import ca.mcgill.ecse321.projectgroup04.dao.OwnerRepository;
import ca.mcgill.ecse321.projectgroup04.model.Owner;

@ExtendWith(MockitoExtension.class)
public class TestOwnerService {

	@Mock
	private OwnerRepository ownerRepository;

	@InjectMocks
	private OwnerService service;

	private static final String OWNER_NAME = "ownerTestName";
	private static final String OWNER_PASSWORD = "ownerTestPassword";

	@BeforeEach
	public void setMockOutput() {
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(ownerRepository.save(any(Owner.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(ownerRepository.findOwnerByUserId(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(OWNER_NAME)) {
				Owner owner = new Owner();
				owner.setUserId(OWNER_NAME);
				owner.setPassword(OWNER_PASSWORD);

				return owner;
			}
			return null;

		});

	}

	@Test
	public void TestCreateOwner() {

		String userId = "ownerTestname";
		String password = "ownerTestPassword";

		Owner owner = null;
		try {
			owner = service.createOwner(userId, password);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(owner);
		assertEquals(userId, owner.getUserId());
		assertEquals(password, owner.getPassword());

	}

	@Test
	public void TestCreateOwnertNoUserId() {
		String userId = "";
		String password = "ownerTestPassword";

		String error = null;
		Owner owner = null;
		try {
			owner = service.createOwner(userId, password);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(owner);
		assertEquals(error, "Username can't be empty");

	}

	@Test
	public void TestDeleteOwner() {
		String userId = "ownerHello";
		String password = "ownerWorld";
		Owner owner = new Owner();
		owner.setUserId(userId);
		owner.setPassword(password);

		try {
			owner = service.deleteOwner(owner);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNull(owner);

	}

	@Test
	public void TestEditUserIdOwner() {
		String userId = "ownerfirst";
		String password = "ownerpassword";
		String newUserId = "ownersecond";
		Owner owner = new Owner();
		owner.setUserId(userId);
		owner.setPassword(password);

		try {
			owner = service.editOwner(owner, newUserId, password);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(owner);
		assertEquals(newUserId, owner.getUserId());
		assertEquals(password, owner.getPassword());

	}

	@Test
	public void TestEditPasswordOwner() {
		String userId = "ownerUserId";
		String password = "ownerFirst";
		String newPassword = "ownerSecond";
		Owner owner = new Owner();
		owner.setUserId(userId);
		owner.setPassword(password);

		try {
			owner = service.editOwner(owner, userId, newPassword);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(owner);
		assertEquals(userId, owner.getUserId());
		assertEquals(newPassword, owner.getPassword());

	}

	@Test
	public void TestEditUserIdAndPasswordOwner() {
		String userId = "ownerUserId";
		String newUserId = "ownerSecondUserId";
		String password = "ownerFirst";
		String newPassword = "ownerSecond";
		Owner owner = new Owner();
		owner.setUserId(userId);
		owner.setPassword(password);

		try {
			owner = service.editOwner(owner, newUserId, newPassword);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(owner);
		assertEquals(newUserId, owner.getUserId());
		assertEquals(newPassword, owner.getPassword());

	}

	@Test
	public void TestEditWithSameParametersOwner() {
		String userId = "ownerUserId";
		String newUserId = "ownerUserId";
		String password = "ownerFirst";
		String newPassword = "ownerFirst";
		String error = null;
		Owner owner = new Owner();
		owner.setUserId(userId);
		owner.setPassword(password);

		try {
			owner = service.editOwner(owner, newUserId, newPassword);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(owner);
		assertEquals(userId, owner.getUserId());
		assertEquals(password, owner.getPassword());
		assertEquals(error, "You have to change the username or password or both");

	}

}
