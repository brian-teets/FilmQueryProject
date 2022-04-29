package com.skilldistillery.filmquery.database;

import java.sql.*;
import java.util.List;
import java.time.*;

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
		Film film = null;
		String user = "student";
		String pass = "student";
		String sql = "SELECT * FROM film WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, filmId); 
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				film = new Film(); // create the Film object 
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageID(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
			}
		} // end of try-with-resources block 
		return film;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException{
		Actor actor = null;
		String user = "student";
		String pass = "student";
		String sql = "SELECT * FROM actor WHERE id = ?";
		
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, actorId); 
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				actor = new Actor(); // create the Actor object
				actor.setId(rs.getInt("id"));
			}
		} // end of try-with-resources block 
		
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		// TODO Auto-generated method stub
		return null;
	}

}
