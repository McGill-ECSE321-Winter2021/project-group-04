package ca.mcgill.ecse321.projectgroup04.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.projectgroup04.dao.ProfileRepository;

@ExtendWith(MockitoExtension.class)
public class TestProfileService {
	
	@Mock
	private ProfileRepository profileRepository;

}
