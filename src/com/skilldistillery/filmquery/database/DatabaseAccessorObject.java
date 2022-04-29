package com.skilldistillery.filmquery.database;

import java.sql.*;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

/*
All JDBC code will be in methods of the DatabaseAccessorObject class.

Implement the findFilmById method that takes an int film ID, and returns a Film object 
(or null, if the film ID returns no data.)

Implement findActorById method that takes an int actor ID, and returns an Actor object 
(or null, if the actor ID returns no data.)

Implement findActorsByFilmId with an appropriate List implementation 
that will be populated using a ResultSet and returned.

Make sure your JDBC code uses PreparedStatement with bind variables instead of concatenating values into SQL strings.
 */

// According to comment by Dee, recommend creating and closing a connection inside of each of the DAO methods.

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		/*
		 * Actor actor = null; //... String sql =
		 * "SELECT id, first_name, last_name FROM actor WHERE id = ?"; 
		 * PreparedStatement stmt = conn.prepareStatement(sql); stmt.setInt(1,actorId); ResultSet
		 * actorResult = stmt.executeQuery(); if (actorResult.next()) { actor = new
		 * Actor(); // Create the object // Here is our mapping of query columns to our
		 * object fields: actor.setId(actorResult.getInt(1));
		 * actor.setFirstName(actorResult.getString(2));
		 * actor.setLastName(actorResult.getString(3));
		 * actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films } //...
		 * return actor;
		 */
		Film film = null;
		String user = "student";
		String pass = "student";
		String sql = "SELECT * FROM film WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				) {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println( "Film ID: " + rs.getInt("id") + " || Title: " + rs.getString("title") );
			}
		} // end of try-with-resources block 
		
		

		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		// TODO Auto-generated method stub
		return null;
	}

}
