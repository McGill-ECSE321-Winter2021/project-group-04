package ca.mcgill.ecse321.projectgroup04.dto;

import java.util.List;

public class GarageSpotDto {
	private Long garageSpotId;
	private int spotNumber;
	private List<TimeSlotDto> timeSlot;
	
	public GarageSpotDto() {
		
	}
	
	public GarageSpotDto(int aSpotNumber, List<TimeSlotDto> aTimeSlot) {
		this.spotNumber = aSpotNumber;
		timeSlot = aTimeSlot;
	}
	
	public int getSpotNumber() {
		return spotNumber;
	}
	
	public List<TimeSlotDto> getTimeSlot(){
		return timeSlot;
	}
	
	public void setSpotNumber(int mySpotNumber) {
		spotNumber = mySpotNumber;
	}
	
	public void setTimeSlot(List<TimeSlotDto> myTimeSlot){
		timeSlot = myTimeSlot;
	}
}
