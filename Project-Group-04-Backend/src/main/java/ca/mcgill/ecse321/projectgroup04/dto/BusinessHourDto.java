package ca.mcgill.ecse321.projectgroup04.dto;

import java.sql.Time;

public class BusinessHourDto {
    public enum DayOfWeek {
        Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
    }

    private DayOfWeek dayOfWeek;
    private Time startTime;
    private Time endTime;
    private Long hourId;

    public BusinessHourDto() {
    }

    public BusinessHourDto(DayOfWeek aDayOfWeek, Time aStartTime, Time aEndTime) {

        this.dayOfWeek = aDayOfWeek;
        this.startTime = aStartTime;
        this.endTime = aEndTime;

    }

    /**
     * @return DayOfWeek return the dayOfWeek
     */
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * @return Time return the startTime
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * @return Time return the endTime
     */
    public Time getEndTime() {
        return endTime;
    }

    public Long getId() {
        return hourId;
    }

    public void setId(Long id) {
        this.hourId = id;
    }

//    public void setDayOfWeek(DayOfWeek dow) {
//        if (dow.toString().equals("Monday")) {
//            dayOfWeek = dow;
//        } else if (dow.toString().equals("Tuesday")) {
//            dayOfWeek = dow;
//        } else if (dow.toString().equals("Wednseday")) {
//            dayOfWeek = dow;
//        } else if (dow.toString().equals("Thursday")) {
//            dayOfWeek = dow;
//        } else if (dow.toString().equals("Friday")) {
//            dayOfWeek = dow;
//        } else if (dow.toString().equals("Saturday")) {
//            dayOfWeek = dow;
//        } else if (dow.toString().equals("Sunday")) {
//            dayOfWeek = dow;
//        }
//
//    }
}
