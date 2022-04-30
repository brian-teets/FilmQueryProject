package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;


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
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
			}
		} // end of try-with-resources block 
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException{
		/*
		 * Implement findActorsByFilmId with an appropriate List implementation 
			that will be populated using a ResultSet and returned.
		 */
		List<Actor> actors = new ArrayList<>();
		
		String user = "student";
		String pass = "student";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				) {
			String sql = "SELECT * FROM actor "
					+ "JOIN film_actor ON actor.id = film_actor.actor_id "
					+ "JOIN film ON film.id = film_actor.film_id"
					+ "WHERE film.id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, filmId); 
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int actorId = rs.getInt("id");
				String actorFirstName = rs.getString("first_name");
				String actorLastName = rs.getString("last_name");
				Actor actor = new Actor(actorId, actorFirstName, actorLastName);
				actors.add(actor);
			}
		} // end of try-with-resources block 
		return actors;
	}

}
