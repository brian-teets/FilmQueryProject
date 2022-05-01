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
//		String sql = "SELECT * FROM film WHERE id = ?";
		String sql = "SELECT film.id, film.title, film.release_year, language.name, film.language_id, language.id, film.description, film.rating FROM film JOIN language ON film.language_id = language.id WHERE film.id = ?";
//		String sql = "SELECT film.id, film.title, film.release_year, language.name, film.language_id, language.id, film.description, film.rating, actor.id, actor.first_name, actor.last_name FROM film JOIN film_actor ON film.id = film_actor.film_id"
//				+ "JOIN actor ON film_actor.actor_id = actor.id JOIN language ON film.language_id = language.id WHERE film.id = ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, filmId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				film = new Film(); // create the Film object
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguage(rs.getString("language.name"));
//				film.setRentalDuration(rs.getInt("rental_duration"));
//				film.setRentalRate(rs.getDouble("rental_rate"));
//				film.setLength(rs.getInt("length"));
//				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
//				film.setSpecialFeatures(rs.getString("special_features"));
				film.setActors(findActorsByFilmId(filmId)); 
			}
		} // end of try-with-resources block
		return film;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		String user = "student";
		String pass = "student";
		String sql = "SELECT * FROM actor WHERE id = ?";

		try (Connection conn = DriverManager.getConnection(URL, user, pass);) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, actorId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				actor = new Actor(); // create the Actor object
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
			}
		} // end of try-with-resources block
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		/*
		 * Implement findActorsByFilmId with an appropriate List implementation that
		 * will be populated using a ResultSet and returned.
		 */
		List<Actor> actors = new ArrayList<>();

		String user = "student";
		String pass = "student";

		String sql = "SELECT actor.id ,actor.first_name, actor.last_name FROM actor JOIN film_actor ON actor.id = film_actor.actor_id JOIN film ON film.id = film_actor.film_id WHERE film.id = ?";

		try (Connection conn = DriverManager.getConnection(URL, user, pass);) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, filmId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int actorId = rs.getInt("id");
				String actorFirstName = rs.getString("first_name");
				String actorLastName = rs.getString("last_name");

				Actor actor = new Actor(actorId, actorFirstName, actorLastName);
				actors.add(actor);
			}
		} // end of try-with-resources block
		return actors;
	}

	@Override
	public List<Film> findFilmsByKeyword(String searchWord) throws SQLException {
		// TODO Implement findFilmsByKeyword allowing wild card for bind variables for title and description
		List<Film> films = new ArrayList<>();
		String user = "student";
		String pass = "student";
		
		
		String sql = "SELECT film.id, film.title, film.release_year, language.name, film.language_id, language.id, film.description, film.rating FROM film JOIN language ON film.language_id = language.id WHERE title LIKE ? OR description LIKE ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchWord + "%");
			pstmt.setString(2, "%" + searchWord + "%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				// Display: id, title, release year, rating, description 
				int filmId = rs.getInt("film.id");
				String filmTitle = rs.getString("title");
				int releaseYear = rs.getInt("release_year");
				String rating = rs.getString("rating");
				String description = rs.getString("description");
				String language = rs.getString("language.name");
				
				List<Actor> cast = findActorsByFilmId(filmId); 
				
				Film film = new Film(filmId, filmTitle, releaseYear, rating, description, language, cast);
				films.add(film);
			}
		} // end of try-with-resources block 
		return films;
	}

}
