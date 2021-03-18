package ca.mcgill.ecse321.projectgroup04.dto;

import java.sql.Date;
import java.sql.Time;

public class CheckupReminderDto {
    private Date date;
    private Time time;
    private String message;
    private Long checkupReminderId;

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

    public Long getId() {
        return checkupReminderId;
    }

    public void setId(Long id) {
        this.checkupReminderId = id;
    }

}