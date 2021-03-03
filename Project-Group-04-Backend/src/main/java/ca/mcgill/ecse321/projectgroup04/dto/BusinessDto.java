package ca.mcgill.ecse321.projectgroup04.dto;

import java.util.List;

import ca.mcgill.ecse321.projectgroup04.model.AutoRepairShop;
import ca.mcgill.ecse321.projectgroup04.model.BusinessHour;
import ca.mcgill.ecse321.projectgroup04.model.TimeSlot;

public class BusinessDto {
    private String name;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private Long id;
    private List<BusinessHour> businessHours;
    private List<TimeSlot> regular;
    private AutoRepairShop autoRepairShop;

    public BusinessDto() {
    }

    public BusinessDto(String aName, String aAddress, String aPhoneNumber, String aEmailAddress,
            List<BusinessHour> aBusinessHours, List<TimeSlot> regular, AutoRepairShop aAuto) {
        this.name = aName;
        this.address = aAddress;
        this.phoneNumber = aPhoneNumber;
        this.emailAddress = aEmailAddress;
        this.businessHours = aBusinessHours;
        this.regular = regular;
        this.autoRepairShop = aAuto;
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
    public List<BusinessHour> getBusinessHours() {
        return businessHours;
    }

    /**
     * @return List<TimeSlot> return the regular
     */
    public List<TimeSlot> getRegular() {
        return regular;
    }

}
