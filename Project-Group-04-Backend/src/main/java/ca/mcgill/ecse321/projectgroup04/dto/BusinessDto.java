package ca.mcgill.ecse321.projectgroup04.dto;

import java.util.List;

public class BusinessDto {
    private String name;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private Long businessId;

    private List<BusinessHourDto> businessHours;
    private List<TimeSlotDto> regular;

    public BusinessDto() {
    }

    public BusinessDto(String aName, String aAddress, String aPhoneNumber, String aEmailAddress,
            List<BusinessHourDto> aBusinessHours, List<TimeSlotDto> regular) {
        this.name = aName;
        this.address = aAddress;
        this.phoneNumber = aPhoneNumber;
        this.emailAddress = aEmailAddress;
        this.businessHours = aBusinessHours;
        this.regular = regular;

    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return String return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return String return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return String return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @return List<BusinessHour> return the businessHours
     */
    public List<BusinessHourDto> getBusinessHours() {
        return businessHours;
    }

    /**
     * @return List<TimeSlot> return the regular
     */
    public List<TimeSlotDto> getRegular() {
        return regular;
    }

    public void setBusinessHours(List<BusinessHourDto> aBusinessHours) {
        businessHours = aBusinessHours;
    }

    public void setTimeSlot(List<TimeSlotDto> aTimeSlot) {
        regular = aTimeSlot;
    }

    public Long getId() {
        return businessId;
    }

    public void setId(Long id) {
        this.businessId = id;
    }

}