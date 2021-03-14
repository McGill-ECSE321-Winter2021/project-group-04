package ca.mcgill.ecse321.projectgroup04.dto;

public class BookableServiceDto {
	private int duration;
	private int price;
	private String name;
	private Long id;

	public BookableServiceDto() {

	}

	public BookableServiceDto(int duration, int price, String name, Long id) {
		this.duration = duration;
		this.price = price;
		this.name = name;
		this.id = id;
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
		return this.id;
	}
}