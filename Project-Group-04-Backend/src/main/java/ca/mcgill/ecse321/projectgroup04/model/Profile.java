package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "profile")
public class Profile {

  private Long profileId;

  public void setProfileId(Long aProfileId) {
    profileId = aProfileId;
  }

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  public Long getProfileId() {
    return profileId;
  }

  private String addressLine;

  public void setAddressLine(String aAddressLine) {
    addressLine = aAddressLine;
  }

  public String getAddressLine() {
    return addressLine;
  }

  private String phoneNumber;

  public void setPhoneNumber(String aPhoneNumber) {

    phoneNumber = aPhoneNumber;

  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  private String firstName;

  public void setFirstName(String aFirstName) {
    firstName = aFirstName;

  }

  public String getFirstName() {
    return firstName;
  }

  private String lastName;

  public void setLastName(String aLastName) {
    lastName = aLastName;

  }

  public String getLastName() {
    return lastName;
  }

  private String zipCode;

  public void setZipCode(String aZipCode) {

    zipCode = aZipCode;

  }

  public String getZipCode() {
    return zipCode;
  }

  private String emailAddress;

  public void setEmailAddress(String aEmailAddress) {

    emailAddress = aEmailAddress;

  }

  public String getEmailAddress() {
    return emailAddress;
  }

}