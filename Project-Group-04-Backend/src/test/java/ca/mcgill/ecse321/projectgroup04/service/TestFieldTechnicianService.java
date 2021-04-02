package ca.mcgill.ecse321.projectgroup04.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import ca.mcgill.ecse321.projectgroup04.dao.EmergencyServiceRepository;
import ca.mcgill.ecse321.projectgroup04.dao.FieldTechnicianRepository;
import ca.mcgill.ecse321.projectgroup04.model.EmergencyService;
import ca.mcgill.ecse321.projectgroup04.model.FieldTechnician;

@ExtendWith(MockitoExtension.class)
public class TestFieldTechnicianService {

	@Mock
	private FieldTechnicianRepository fieldTechnicianRepository;

	@Mock
	private EmergencyServiceRepository emergencyServiceRepository;

	@InjectMocks
	private FieldTechnicianService service;

	private static final String FIELDTECHNICIAN_NAME = "TestName";

	@BeforeEach
	public void setMockOutput() {
		lenient().when(fieldTechnicianRepository.findFieldTechnicianByName(anyString()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(FIELDTECHNICIAN_NAME)) {

						FieldTechnician fieldTechnician = new FieldTechnician();
						fieldTechnician.setName(FIELDTECHNICIAN_NAME);

						return fieldTechnician;
					}
					return null;
				});
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(fieldTechnicianRepository.save(any(FieldTechnician.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(emergencyServiceRepository.save(any(EmergencyService.class)))
				.thenAnswer(returnParameterAsAnswer);
		lenient().when(emergencyServiceRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {

			List<EmergencyService> emergencyServices = new ArrayList<>();

			EmergencyService emergencyService = new EmergencyService();

			FieldTechnician fieldTechTest = new FieldTechnician();
			fieldTechTest.setName("FieldTechTest");
			fieldTechTest.setIsAvailable(true);

			emergencyService.setTechnician(fieldTechTest);
			emergencyServices.add(emergencyService);

			return emergencyServices;

		});

	}

	@Test
	public void TestCreateFieldTechnician() {
		String name = "Testname";

		FieldTechnician fieldTechnician = null;
		try {
			fieldTechnician = service.createFieldTechnician(name);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(fieldTechnician);
		assertEquals(name, fieldTechnician.getName());
		assertTrue(fieldTechnician.getIsAvailable());
	}

	@Test
	public void TestCreateFieldTechnicianNoName() {
		String name = "";
		String error = null;

		FieldTechnician fieldTechnician = null;
		try {
			fieldTechnician = service.createFieldTechnician(name);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(fieldTechnician);
		assertEquals(error, "Name can't be empty");
	}

	@Test
	public void TestDeleteFieldTechnician() {
		String name = "TestNameDelete";
		FieldTechnician fieldTechnician = new FieldTechnician();
		fieldTechnician.setName(name);

		try {
			fieldTechnician = service.deleteFieldTechnician(fieldTechnician);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNull(fieldTechnician);
	}

}
