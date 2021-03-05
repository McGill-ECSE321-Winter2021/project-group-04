package ca.mcgill.ecse321.projectgroup04.dto;

import java.util.List;

public class GarageSpotDto {
	private Long garageSpotId;
	private int spotNumber;
	private List<TimeSlot> timeSlot;
	
	public GarageSpotDto() {
		
	}
	
	public GarageSpotDto(int aSpotNumber, List<TimeSlot> aTimeSlot) {
		this.spotNumber = aSpotNumber;
		timeSlot = aTimeSlot;
	}
	
	public int getSpotNumber() {
		return spotNumber;
	}
	
	public List<TimeSlot> getTimeSlot(){
		return timeSlot;
	}
	
	public void setSpotNumber(int mySpotNumber) {
		spotNumber = mySpotNumber;
	}
	
	public void setTimeSlot(List<TimeSlot> myTimeSlot){
		timeSlot = myTimeSlot;
	}
}
