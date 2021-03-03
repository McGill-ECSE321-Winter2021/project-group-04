package ca.mcgill.ecse321.projectgroup04.dto;

import java.sql.Date;
import java.sql.Time;

import ca.mcgill.ecse321.projectgroup04.model.Customer;

public class CheckupReminderDto {
    private Date date;
    private Time time;
    private String message;
    private Customer customer;

    public CheckupReminderDto() {
    }

    public CheckupReminderDto(Date aDate, Time aTime, String aMessage, Customer aCustomer) {
        this.date = aDate;
        this.time = aTime;
        this.message = aMessage;
        this.customer = aCustomer;
    }

    /**
     * @return Date return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return Time return the time
     */
    public Time getTime() {
        return time;
    }

    /**
     * @return String return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return Customer return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

}
