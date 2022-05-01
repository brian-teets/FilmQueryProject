package com.skilldistillery.filmquery.entities;

public class Actor {
/*
 * Define an Actor class with id, first name, and last name fields. 
 * Include the usual getters, setters, toString, etc (of course!), and appropriate constructor(s).
 */
	private int id;
	private String firstName;
	private String lastName;
	
	public Actor() {
		
	}
	
	public Actor(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return " | Actor's name: " + firstName + " " + lastName + "\n";
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
		if (!(obj instanceof Actor))
			return false;
		Actor other = (Actor) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
