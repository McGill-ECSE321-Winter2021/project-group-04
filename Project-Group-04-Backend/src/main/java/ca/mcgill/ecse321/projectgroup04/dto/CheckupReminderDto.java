package ca.mcgill.ecse321.projectgroup04.dto;

import java.sql.Date;
import java.sql.Time;

public class CheckupReminderDto {
    private Date date;
    private Time time;
    private String message;
    private Long id;

    public CheckupReminderDto() {
    }

    public CheckupReminderDto(Date aDate, Time aTime, String aMessage, Long aId) {
        this.date = aDate;
        this.time = aTime;
        this.message = aMessage;
        this.id = aId;

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

    public Long getId() {
        return id;
    }

}