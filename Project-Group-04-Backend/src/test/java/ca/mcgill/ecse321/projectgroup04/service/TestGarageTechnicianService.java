package ca.mcgill.ecse321.projectgroup04.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup04.dao.AdministrativeAssistantRepository;
import ca.mcgill.ecse321.projectgroup04.dao.BookableServiceRepository;
import ca.mcgill.ecse321.projectgroup04.dao.GarageTechnicianRepository;
import ca.mcgill.ecse321.projectgroup04.model.AdministrativeAssistant;
import ca.mcgill.ecse321.projectgroup04.model.BookableService;
import ca.mcgill.ecse321.projectgroup04.model.GarageTechnician;

@ExtendWith(MockitoExtension.class)
public class TestGarageTechnicianService {

	@Mock
	private GarageTechnicianRepository garageTechnicianRepository;
	
	@InjectMocks
	private AutoRepairShopSystemService service;
	
	 private static final String GARAGETECHNICIAN_NAME = "TestName";
	
	
	@BeforeEach
	public void setMockOutput() {
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
		lenient().when(garageTechnicianRepository.save(any(GarageTechnician.class))).thenAnswer(returnParameterAsAnswer);
        
        lenient().when(garageTechnicianRepository.findGarageTechnicianByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(GARAGETECHNICIAN_NAME)) {

                GarageTechnician garageTechnician = new GarageTechnician();
                garageTechnician.setName(GARAGETECHNICIAN_NAME);
                
               
                return garageTechnician;
            }
            return null;
        });

    }
	
	@Test
    public void TestCreateGarageTechnician() {
	
		String name = "TestName";
   
        GarageTechnician garageTechnician = null;
        try {
        	garageTechnician = service.createGarageTechnician(name);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(garageTechnician);
        assertEquals(name, garageTechnician.getName());
        
	}
	
	 @Test
	 public void TestCreateGarageTechnicianNoName() {
		 	String name = "";	      

	        String error = null;

	      
	        GarageTechnician garageTechnician = null;
	        try {
	        	garageTechnician = service.createGarageTechnician(name);
	        } catch (IllegalArgumentException e) {
	            error = e.getMessage();
	        }

	        assertNull(garageTechnician);
	        assertEquals(error, "Name can't be empty");

	    }
	 
	 @Test
	 public void TestCreateGarageTechnicianAlreadyExists() {
		 	String name = "TestName";	      

	        String error = null;

	      
	        GarageTechnician garageTechnician = null;
	        try {
	        	garageTechnician = service.createGarageTechnician(name);
	        } catch (IllegalArgumentException e) {
	            error = e.getMessage();
	        }

	        assertNull(garageTechnician);
	        assertEquals(error, "Garage Technician with this name already exists");

	    }
	 
	 
}
