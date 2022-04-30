package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

	private void test() {
		Film film = null;
		Actor actor = null;
		List<Actor> actors = null;
		try {
			film = db.findFilmById(1);
			actor = db.findActorById(1);
			actors = db.findActorsByFilmId(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(film);
		System.out.println(actor);
		System.out.println(actors);
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		while (true) {
			int userChoice = 3;
			try {
				printMenu();
				userChoice = input.nextInt();
				if (userChoice == 3) {
					System.out.println("You chose to exit. Come back again if you change your mind!");
					break;
				} else if (userChoice == 1) {
					/*
					 * User Story 2: If the user looks up a film by id, they are prompted to enter
					 * the film id. If the film is not found, they see a message saying so. If the
					 * film is found, its title, year, rating, and description are displayed.
					 */
					Film film = null;
					try {
						input.nextLine();
						int filmByIdChoice = 0;
						System.out.println("You chose to find a film using a film Id. Please enter a film Id now: ");
						filmByIdChoice = input.nextInt();
						film = db.findFilmById(filmByIdChoice);
						if (film != null) {
							System.out.println(film);
						} else {
							System.out.println("Sorry, we don't have a record of that film Id. Please try again. ");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.println("Please enter a number from the menu.");
			}

		}
	}

	public void printMenu() {
		System.out.println();
		System.out.println("== Please enter a number from the menu: ==");
		System.out.println("1) Look up a film by its id. ");
		System.out.println("2) Look up a film by a search keyword. ");
		System.out.println("3) Exit the application. ");
	}

}
