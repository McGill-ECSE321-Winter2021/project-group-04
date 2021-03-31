package ca.mcgill.ecse321.projectgroup04.service;

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

import ca.mcgill.ecse321.projectgroup04.model.Profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import ca.mcgill.ecse321.projectgroup04.dao.ProfileRepository;

@ExtendWith(MockitoExtension.class)
public class TestProfileService {

	@Mock
	private ProfileRepository profileRepository;

	@InjectMocks
	private ProfileService service;

	private static final String FIRST_NAME1 = "Harry";
	private static final String LAST_NAME1 = "Potter";
	private static final String ADDRESS_LINE1 = "Hogwarts, London";
	private static final String EMAIL_ADDRESS1 = "harry@potter";
	private static final String PHONE_NUMBER1 = "8889995555";
	private static final String ZIP_CODE1 = "H9P5F0";
	private static final Long PROFILE_ID1 = 5653l;

	private static final String FIRST_NAME2 = "Ron";
	private static final String LAST_NAME2 = "Weasley";
	private static final String ADDRESS_LINE2 = "Hogwarts, London";
	private static final String EMAIL_ADDRESS2 = "ron@weasley";
	private static final String PHONE_NUMBER2 = "5554442222";
	private static final String ZIP_CODE2 = "K9F6B2";
	private static final Long PROFILE_ID2 = 7623l;

	@BeforeEach
	public void setMockOutput() {
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(profileRepository.save(any(Profile.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(profileRepository.findProfileByProfileId(anyLong()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(PROFILE_ID1)) {
						Profile profile = new Profile();
						profile.setProfileId(PROFILE_ID1);
						profile.setAddressLine(ADDRESS_LINE1);
						profile.setEmailAddress(EMAIL_ADDRESS1);
						profile.setFirstName(FIRST_NAME1);
						profile.setLastName(LAST_NAME1);
						profile.setPhoneNumber(PHONE_NUMBER1);
						profile.setZipCode(ZIP_CODE1);

						return profile;
					}

					if (invocation.getArgument(0).equals(PROFILE_ID2)) {
						Profile profile = new Profile();
						profile.setProfileId(PROFILE_ID2);
						profile.setAddressLine(ADDRESS_LINE2);
						profile.setEmailAddress(EMAIL_ADDRESS2);
						profile.setFirstName(FIRST_NAME2);
						profile.setLastName(LAST_NAME2);
						profile.setPhoneNumber(PHONE_NUMBER2);
						profile.setZipCode(ZIP_CODE2);

						return profile;
					}
					return null;
				});

		lenient().when(profileRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			List<Profile> profiles = new ArrayList<>();

			Profile profile1 = new Profile();
			profile1.setProfileId(PROFILE_ID1);
			profile1.setAddressLine(ADDRESS_LINE1);
			profile1.setEmailAddress(EMAIL_ADDRESS1);
			profile1.setFirstName(FIRST_NAME1);
			profile1.setLastName(LAST_NAME1);
			profile1.setPhoneNumber(PHONE_NUMBER1);
			profile1.setZipCode(ZIP_CODE1);

			Profile profile2 = new Profile();
			profile2.setProfileId(PROFILE_ID2);
			profile2.setAddressLine(ADDRESS_LINE2);
			profile2.setEmailAddress(EMAIL_ADDRESS2);
			profile2.setFirstName(FIRST_NAME2);
			profile2.setLastName(LAST_NAME2);
			profile2.setPhoneNumber(PHONE_NUMBER2);
			profile2.setZipCode(ZIP_CODE2);

			profiles.add(profile1);
			profiles.add(profile2);

			return profiles;

		});

	}

	@Test
	public void TestGetProfile1() {
		Long profileId = 5653l;
		Profile profile = null;
		try {
			profile = service.getProfile(profileId);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(profile);
		assertEquals(PROFILE_ID1, profile.getProfileId());
		assertEquals(FIRST_NAME1, profile.getFirstName());
		assertEquals(LAST_NAME1, profile.getLastName());
		assertEquals(EMAIL_ADDRESS1, profile.getEmailAddress());
		assertEquals(ADDRESS_LINE1, profile.getAddressLine());
		assertEquals(ZIP_CODE1, profile.getZipCode());
		assertEquals(PHONE_NUMBER1, profile.getPhoneNumber());

	}

	@Test
	public void TestGetProfile2() {
		Long profileId = 7623l;
		Profile profile = null;
		try {
			profile = service.getProfile(profileId);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(profile);
		assertEquals(PROFILE_ID2, profile.getProfileId());
		assertEquals(FIRST_NAME2, profile.getFirstName());
		assertEquals(LAST_NAME2, profile.getLastName());
		assertEquals(EMAIL_ADDRESS2, profile.getEmailAddress());
		assertEquals(ADDRESS_LINE2, profile.getAddressLine());
		assertEquals(ZIP_CODE2, profile.getZipCode());
		assertEquals(PHONE_NUMBER2, profile.getPhoneNumber());

	}

	@Test
	public void TestGetProfileInvalidId() {
		Long invalidId = 4312l;
		Profile profile = null;

		String error = null;
		try {
			profile = service.getProfile(invalidId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(profile);
		assertEquals(error, "No profile with such ID exist!");

	}

	@Test
	public void TestGetProfile1ByFirstAndLastName() {
		String firstName = "Harry";
		String lastName = "Potter";
		Profile profile = null;

		try {
			profile = service.getProfileByFirstAndLast(firstName, lastName);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(profile);
		assertEquals(firstName, profile.getFirstName());
		assertEquals(lastName, profile.getLastName());
		assertEquals(EMAIL_ADDRESS1, profile.getEmailAddress());
		assertEquals(PHONE_NUMBER1, profile.getPhoneNumber());
		assertEquals(ADDRESS_LINE1, profile.getAddressLine());
		assertEquals(ZIP_CODE1, profile.getZipCode());
	}

	@Test
	public void TestGetProfile2ByFirstAndLastName() {
		String firstName = "Ron";
		String lastName = "Weasley";
		Profile profile = null;

		try {
			profile = service.getProfileByFirstAndLast(firstName, lastName);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(profile);
		assertEquals(firstName, profile.getFirstName());
		assertEquals(lastName, profile.getLastName());
		assertEquals(EMAIL_ADDRESS2, profile.getEmailAddress());
		assertEquals(PHONE_NUMBER2, profile.getPhoneNumber());
		assertEquals(ADDRESS_LINE2, profile.getAddressLine());
		assertEquals(ZIP_CODE2, profile.getZipCode());
	}

	@Test
	public void TestGetProfileByInvalidFirstAndLastName() {
		String firstName = "Hermione";
		String lastName = "Granger";
		Profile profile = null;

		String error = null;

		try {
			profile = service.getProfileByFirstAndLast(firstName, lastName);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(profile);
		assertEquals(error, "No Profile with such First Name and Last Name");

	}

	@Test
	public void TestCreateProfile() {
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "4440007777";
		String zipCode = "F6M7Z1";

		Profile profile = null;
		try {
			profile = service.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode, emailAddress);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(profile);
		assertEquals(firstName, profile.getFirstName());
		assertEquals(lastName, profile.getLastName());
		assertEquals(emailAddress, profile.getEmailAddress());
		assertEquals(addressLine, profile.getAddressLine());
		assertEquals(phoneNumber, profile.getPhoneNumber());
		assertEquals(zipCode, profile.getZipCode());
	}

	@Test
	public void TestCreateProfileFirstNameEmpty() {
		String firstName = "";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "4440007777";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode, emailAddress);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "First Name can't be null or empty");

	}

	@Test
	public void TestCreateProfileFirstNameNull() {
		String firstName = null;
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "4440007777";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode, emailAddress);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "First Name can't be null or empty");

	}

	@Test
	public void TestCreateProfileLastNameEmpty() {
		String firstName = "Hermoine";
		String lastName = "";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "4440007777";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode, emailAddress);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Last Name can't be null or empty");

	}

	@Test
	public void TestCreateProfileLastNameNull() {
		String firstName = "Hermoine";
		String lastName = null;
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "4440007777";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode, emailAddress);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Last Name can't be null or empty");

	}

	@Test
	public void TestCreateProfileEmailAddressEmpty() {
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "";
		String addressLine = "Central London";
		String phoneNumber = "4440007777";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode, emailAddress);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Email Address can't be null or empty");

	}

	@Test
	public void TestCreateProfileEmailAddressNull() {
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = null;
		String addressLine = "Central London";
		String phoneNumber = "4440007777";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode, emailAddress);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Email Address can't be null or empty");

	}

	@Test
	public void TestCreateProfileEmailAddressNoSymbol() {
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine.granger";
		String addressLine = "Central London";
		String phoneNumber = "4440007777";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode, emailAddress);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Email Address must contain @ character");

	}

	@Test
	public void TestCreateProfileAddressLineEmpty() {
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "";
		String phoneNumber = "4440007777";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode, emailAddress);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Address Line can't be null or empty");

	}

	@Test
	public void TestCreateProfileAddressLineNull() {
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = null;
		String phoneNumber = "4440007777";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode, emailAddress);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Address Line can't be null or empty");

	}

	@Test
	public void TestCreateProfilePhoneNumberEmpty() {
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode, emailAddress);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Phone Number can't be null or empty");

	}

	@Test
	public void TestCreateProfilePhoneNumberNull() {
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = null;
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode, emailAddress);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Phone Number can't be null or empty");

	}

	@Test
	public void TestCreateProfilePhoneNumberLongerThan10() {
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "44488855550";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode, emailAddress);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Phone Number must be 10 characters long");

	}

	@Test
	public void TestCreateProfilePhoneNumberLessThan10() {
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "444888555";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode, emailAddress);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Phone Number must be 10 characters long");

	}

	@Test
	public void TestCreateProfileZipCodeEmpty() {
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "4448885555";
		String zipCode = "";

		String error = null;

		Profile profile = null;
		try {
			profile = service.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode, emailAddress);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Zip Code can't be null or empty");

	}

	@Test
	public void TestCreateProfileZipCodeNull() {
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "4448885555";
		String zipCode = null;

		String error = null;

		Profile profile = null;
		try {
			profile = service.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode, emailAddress);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Zip Code can't be null or empty");

	}

	@Test
	public void TestCreateProfileZipCodeMoreThan6() {
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "4448885555";
		String zipCode = "H3G0E45";

		String error = null;

		Profile profile = null;
		try {
			profile = service.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode, emailAddress);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Zip Code must be 6 characters long");

	}

	@Test
	public void TestCreateProfileZipCodeLessThan6() {
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "4448885555";
		String zipCode = "H3G0E";

		String error = null;

		Profile profile = null;
		try {
			profile = service.createProfile(addressLine, phoneNumber, firstName, lastName, zipCode, emailAddress);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Zip Code must be 6 characters long");

	}

	@Test
	public void TestEditProfile() {
		Long profileId = 5653l;
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "4448885555";
		String zipCode = "H3G0E6";

		Profile profile = null;
		try {
			profile = service.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
					zipCode);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(profile);
		assertEquals(firstName, profile.getFirstName());
		assertEquals(lastName, profile.getLastName());
		assertEquals(emailAddress, profile.getEmailAddress());
		assertEquals(addressLine, profile.getAddressLine());
		assertEquals(phoneNumber, profile.getPhoneNumber());
		assertEquals(zipCode, profile.getZipCode());
	}

	@Test
	public void TestEditProfileInvalidId() {
		Long profileId = 5343l;
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "4440007777";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
					zipCode);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "No profile with such ID exist!");

	}

	@Test
	public void TestEditProfileFirstNameEmpty() {
		Long profileId = 5653l;
		String firstName = "";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "4440007777";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
					zipCode);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "First Name can't be null or empty");

	}

	@Test
	public void TestEditProfileFirstNameNull() {
		Long profileId = 5653l;
		String firstName = null;
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "4440007777";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
					zipCode);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "First Name can't be null or empty");

	}

	@Test
	public void TestEditProfileLastNameEmpty() {
		Long profileId = 5653l;
		String firstName = "Hermoine";
		String lastName = "";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "4440007777";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
					zipCode);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Last Name can't be null or empty");

	}

	@Test
	public void TestEditProfileLastNameNull() {
		Long profileId = 5653l;
		String firstName = "Hermoine";
		String lastName = null;
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "4440007777";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
					zipCode);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Last Name can't be null or empty");

	}

	@Test
	public void TestEditProfileEmailAddressEmpty() {
		Long profileId = 5653l;
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "";
		String addressLine = "Central London";
		String phoneNumber = "4440007777";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
					zipCode);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Email Address can't be null or empty");

	}

	@Test
	public void TestEditProfileEmailAddressNull() {
		Long profileId = 5653l;
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = null;
		String addressLine = "Central London";
		String phoneNumber = "4440007777";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
					zipCode);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Email Address can't be null or empty");

	}

	@Test
	public void TestEditProfileEmailAddressNoSymbol() {
		Long profileId = 5653l;
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine.granger";
		String addressLine = "Central London";
		String phoneNumber = "4440007777";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
					zipCode);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Email Address must contain @ character");

	}

	@Test
	public void TestEditProfileAddressLineEmpty() {
		Long profileId = 5653l;
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "";
		String phoneNumber = "4440007777";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
					zipCode);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Address Line can't be null or empty");

	}

	@Test
	public void TestEditProfileAddressLineNull() {
		Long profileId = 5653l;
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = null;
		String phoneNumber = "4440007777";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
					zipCode);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Address Line can't be null or empty");

	}

	@Test
	public void TestEditProfilePhoneNumberEmpty() {
		Long profileId = 5653l;
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
					zipCode);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Phone Number can't be null or empty");

	}

	@Test
	public void TestEditProfilePhoneNumberNull() {
		Long profileId = 5653l;
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = null;
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
					zipCode);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Phone Number can't be null or empty");

	}

	@Test
	public void TestEditProfilePhoneNumberLongerThan10() {
		Long profileId = 5653l;
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "44488855550";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
					zipCode);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Phone Number must be 10 characters long");

	}

	@Test
	public void TestEditProfilePhoneNumberLessThan10() {
		Long profileId = 5653l;
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "444888555";
		String zipCode = "F6M7Z1";

		String error = null;

		Profile profile = null;
		try {
			profile = service.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
					zipCode);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Phone Number must be 10 characters long");

	}

	@Test
	public void TestEditProfileZipCodeEmpty() {
		Long profileId = 5653l;
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "4448885555";
		String zipCode = "";

		String error = null;

		Profile profile = null;
		try {
			profile = service.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
					zipCode);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Zip Code can't be null or empty");

	}

	@Test
	public void TestEditProfileZipCodeNull() {
		Long profileId = 5653l;
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "4448885555";
		String zipCode = null;

		String error = null;

		Profile profile = null;
		try {
			profile = service.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
					zipCode);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Zip Code can't be null or empty");

	}

	@Test
	public void TestEditProfileZipCodeMoreThan6() {
		Long profileId = 5653l;
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "4448885555";
		String zipCode = "H3G0E45";

		String error = null;

		Profile profile = null;
		try {
			profile = service.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
					zipCode);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Zip Code must be 6 characters long");

	}

	@Test
	public void TestEditProfileZipCodeLessThan6() {
		Long profileId = 5653l;
		String firstName = "Hermoine";
		String lastName = "Granger";
		String emailAddress = "hermoine@granger";
		String addressLine = "Central London";
		String phoneNumber = "4448885555";
		String zipCode = "H3G0E";

		String error = null;

		Profile profile = null;
		try {
			profile = service.editProfile(profileId, firstName, lastName, emailAddress, phoneNumber, addressLine,
					zipCode);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(profile);
		assertEquals(error, "Zip Code must be 6 characters long");

	}
}
