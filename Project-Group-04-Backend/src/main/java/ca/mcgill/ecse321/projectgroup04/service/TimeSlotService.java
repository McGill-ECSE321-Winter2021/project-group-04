package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*;
import ca.mcgill.ecse321.projectgroup04.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class TimeSlotService {

    @Autowired
    private TimeSlotRepository timeSlotRepository;
    @Autowired
    private BusinessRepository businessRepository;

    @Transactional
    public TimeSlot createTimeSlot(Time startTime, Time endTime, Date startDate, Date endDate, Integer garageSpot) {

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (startTime == null) {
            throw new IllegalArgumentException("startTime cannot be null");
        }
        if (endTime == null) {
            throw new IllegalArgumentException("endTime cannot be null");
        }
        if (garageSpot == null) {
            throw new IllegalArgumentException("garageSpot cannot be null");
        }

        if (startTime.after(endTime)) {
            throw new IllegalArgumentException("StartTime cannot be after endTime");
        }
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartDate(startDate);
        timeSlot.setStartTime(startTime);
        timeSlot.setEndDate(endDate);
        timeSlot.setEndTime(endTime);
        timeSlot.setGarageSpot(garageSpot);
        timeSlotRepository.save(timeSlot);
        return timeSlot;
    }

    @Transactional
    public TimeSlot getTimeSlotByStartDateAndStartTime(Date startDate, Time startTime) {
        return timeSlotRepository.findTimeSlotByStartDateAndStartTime(startDate, startTime);
    }

    @Transactional
    public List<TimeSlot> getAllTimeSlots() {
        return (List<TimeSlot>) timeSlotRepository.findAll();
    }

    @Transactional
    public TimeSlot deleteTimeSlot(TimeSlot timeSlot, Business business) {

        List<TimeSlot> timeSlots = business.getRegular();
        timeSlots.remove(timeSlot);
        business.setRegular(timeSlots);
        businessRepository.save(business);
        timeSlotRepository.delete(timeSlot);
        timeSlot = null;
        return timeSlot;
    }
}
