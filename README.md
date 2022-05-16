## Wildlife Tracker

## Author
Velm Mukanga

## Description
Wildlife tracker is a web application built using java and java-spark framework. The application allows Rangers to track wildlife sightings in the area.

## Prerequisites

You need to have the following installed on your machine

-Java JDK
-Gradle
-JDK
-Maven
-Java IDE (Intellij)

## Setup and Installation
To access this project on your local files, you can clone it using these steps

Open your terminal
Use this command to clone `$ git clone https://github.com/MutheuLinet/animal-tracker.git
This will clone the repository into your local folder
Navigate to the folder you cloned into, within src/main/java/App. java and open it with intellij.
Go to your browser and type localhost:4567



## Contribution

-You may also want to contribute to enhance a functionality:
-Fork the repository to your GitHub account
-Create a new branch (git switch -c ft-development)
-Make the changes you intend
-Add changes to reflect the changes made
-Commit your changes (git commit -m 'additional info')
-Push to the branch (git push origin ft-develop)
-Create a Pull Request.

## Behavior Driven Development
The user is able to;

-Run the App on a browser
-Select the Add ThrivingAnimal, EndangeredAnimal or Sighting tab which will open a form to fill.
-View the ThrivingAnimal, EndangeredAnimal or Sighting details.
-Add edit or delete more ThrivingAnimals, EndangeredAnimals or Sightings as possible (It also allows a user to Test the output before actual running of the App)

## Running Tests

-Navigate to the folder you cloned into, within src/test/java/models and open it with intellij. Select the EndangeredAnimalTests, ThrivingAnimalTests or SightingsTests.
-This is a sample test to check a correct instance of the EndangeredAnimal, ThrivingAnimal and Sightings objects.

@Test

public void getAnimalType_constantTypeProperty_String() {

EndangeredAnimal rhino = new EndangeredAnimal("Rhino","Healthy", "Young");

assertEquals("Endangered_Animal", rhino.getAnimalType());

}

3.Right click within the open test file and run the tests on your terminal.

## SQL

1.Launch postgres
2.Type in psql Run these commands
3.CREATE DATABASE wildlife_tracker;
4.\c wildlife_tracker;
5.CREATE TABLE animals (id serial PRIMARY KEY, name varchar, health varchar, age varchar, type varchar);
6.CREATE TABLE wildlife_tracker=# CREATE TABLE sightings (id serial PRIMARY KEY, animal_id int, location varchar, ranger_name varchar, timestamp timestamp);
7.CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;

## Technologies Used

1.HTML
2.CSS
3.Handlebars
4.Java
5.Gradle
6.Spark
7.Junit
8.Live Site

## Live Link


## License

This project is under the MIT licence

## Copyright

Copyright (c) 2022 Velma Mukanga
