package ca.mcgill.ecse321.projectgroup04.dto;

import java.sql.Date;
import java.sql.Time;

import ca.mcgill.ecse321.projectgroup04.model.Customer;

public class CheckupReminderDto {
    private Date date;
    private Time time;
    private String message;


    public CheckupReminderDto() {
    }

    public CheckupReminderDto(Date aDate, Time aTime, String aMessage) {
        this.date = aDate;
        this.time = aTime;
        this.message = aMessage;
     
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



}