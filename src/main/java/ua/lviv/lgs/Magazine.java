package ua.lviv.lgs;

import java.time.LocalDate;

public class Magazine {
	private int id;
	private String title;
	private String description;
	private LocalDate publishingDate;
	private int price;

	public Magazine(int id, String title, String description, LocalDate publishingDate, int price) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.publishingDate = publishingDate;
		this.price = price;
	}

	public Magazine(String title, String description, LocalDate publishingDate, int price) {
		this.title = title;
		this.description = description;
		this.publishingDate = publishingDate;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(LocalDate publishingDate) {
		this.publishingDate = publishingDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		if (id == 0)
			return "Журнал \"" + title + "\", " + description + ", publishingDate " + publishingDate + ", price " + price / 100 + "." + String.format("%02d", price % 100) + " грн.";
		else
			return "Magazine ID#" + id + ": Журнал \"" + title + "\", " + description + ", publishingDate: " + publishingDate + ", price: " + price / 100 + "." + String.format("%02d", price % 100) + " грн.";
	}

}
