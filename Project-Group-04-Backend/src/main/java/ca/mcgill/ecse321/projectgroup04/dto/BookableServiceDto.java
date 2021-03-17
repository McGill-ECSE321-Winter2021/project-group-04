package ca.mcgill.ecse321.projectgroup04.dto;

public class BookableServiceDto {
	private int duration;
	private int price;
	private String name;
	private Long bookableServiceId;

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

	public Long getId() {
		return this.bookableServiceId;
	}

	public void setId(Long id) {
		this.bookableServiceId = id;
	}

}