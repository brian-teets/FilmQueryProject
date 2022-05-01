# Film Query Project

## Week 7 - Weekend Homework Project for Skill Distillery

### Overview

Create a command-line application using Java that retrieves and displays film data from a MySQL database.

### Learning Objectives

* Create a DatabaseAccessorObject class with well encapsulated methods for JDBC code
  * Connect to and retrieve data from a relational database (implemented in this project with MySQL)
  * Implement abstract methods declared in a DatabaseAccessor interface
  * Methods implemented in the DatabaseAccessorObject (DAO) should return objects defined in entity classes
    * Use well encapsulated fields and methods in entity classes:
      * Actor class entity
      * Film class entity
    * These methods also need to implement getters / setters, overriden toString, equals and hashcode
* Implement methods in an app class to:
  * Present a menu of options in the console which:
    * Should be implemented in a loop to allow the user to continue choosing from menu options or exit the program
    * Calling methods implemented in the DAO, should be able to retrieve and display data records from database which are created and stored as Java objects

## How to Run

#### Step 0

* Download the project code
* Download and connect to sdvid database

#### Run project code in Eclipse

* Run as a Java Application from FilmQueryApp.java
  * Main menu should display in the console
    * From the console enter a number corresponding to a menu option
      * Selecting option `1` from the menu should display a prompt to `search for a film by Id`
        * At which point, you should enter a whole number
        * If a `film` record is found and retrieved from the database, it will be output to the console
          * `Film object's :` `title, release_year, language, rating, description, and cast of actors` (which is a list / collection of Actor objects) should be displayed
        * If a `film` record is not found, you should see a message indicating as such
      * Selecting option `2` from the menu should display a prompt to `search for films by a keyword`
        * At which point, you should enter a string of text
        * If a `film` or `list of films` is found and retrieved from the database, the data objects will be displayed in the console
          * Collection of `films` should be displayed, each with `title, release_year, language, rating, description, and cast of actors`
        * If no records are found, you should see a message indicating as such
      * Selecting option `3` from the menu will exit the program and should display a message indicating as such

## Technologies Used

* Java
* Eclipse IDE
* MySQL
* git
* GitHub

## Lessons Learned

* Gained exposure and practice implementing JDBC code
* Gained exposure and practice with method chaining - which was particularly helpful in the DatabaseAccessorObject (DAO) 
* Gained more practice working with Java object-oriented code practices - particularly with encapsulation and abstraction
