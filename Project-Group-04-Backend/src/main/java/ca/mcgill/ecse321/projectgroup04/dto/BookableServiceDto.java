package ca.mcgill.ecse321.projectgroup04.dto;

import java.util.List;

public class BookableServiceDto {
	private Long serviceId;
	private int duration;
	private int price;
	private String name;


	public BookableServiceDto() {

	}

	public BookableServiceDto(int duration, int price, String name) {
		this.duration = duration;
		this.price = price;
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}



}