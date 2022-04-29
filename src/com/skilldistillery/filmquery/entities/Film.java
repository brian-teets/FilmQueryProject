package com.skilldistillery.filmquery.entities;

import java.time.LocalDate;

public class Film {
/*
* Complete the Film class with attributes that map to the columns of the film table. 
* Use appropriate data types and Java naming conventions. 
* Provide getters and setters, and appropriate constructors. 
* Also add a good toString, and equals and hashCode methods.
*/
	private int id; // will map as primary key of film table 
	private String title;
	private String description;
	private LocalDate releaseYear; // LocalDate is probably enough, probably won't need LocalDateTime
	private int languageID; // will map to foreign key of another table
	private int rentalDuration;
	private double rentalRate;
	private int length;
	private double replacementCost;
	private String rating; // enum in film table DESC, use String here 
	private String specialFeatures; // set in film table DESC, use String here
	
	
	public Film() {
		
	}
	
	public Film(int id, String title, String description, LocalDate releaseYear, 
			int languageID, int rentalDuration,
			double rentalRate, int length, double replacementCost, 
			String rating, String specialFeatures) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageID = languageID;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
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

	public LocalDate getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(LocalDate releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLanguageID() {
		return languageID;
	}

	public void setLanguageID(int languageID) {
		this.languageID = languageID;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	@Override
	public String toString() {
		return "Film ID:" + id + "| Title:" + title + "| Description:" + description + "| Release Year:" + releaseYear
				+ "| Language ID:" + languageID + "| Rental Duration:" + rentalDuration + "| Rental Rate:" + rentalRate
				+ "| Length:" + length + "| Replacement Cost:" + replacementCost + "| Rating:" + rating
				+ "| Special Features:" + specialFeatures;
	}

	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Film))
			return false;
		Film other = (Film) obj;
		if (id != other.id)
			return false;
		return true;
	} 
	
	
}
