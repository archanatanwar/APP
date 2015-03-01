Project: Discworld: Ankh-Morpork 
BUILD 1

Team Information
-------------------------------------------------------------------
Student Name : Kaur, Navleen       Student Id : 7256264 
Student Name : Hasaneen, Ayman     Student Id : 6355897
Student Name : Tanwar, Archana     Student Id : 7248571
Student Name : Kashyap, Jatin      Student Id : 6997791
Student Name : Eftekhari, Maryam   Student Id : 7734069


Package Game:
-------------------------------------------------------------------
CityAreaCards.java
This class is used to get the names for the City Area Cards.
This class is responsible for :
-- Returning a City Area Card for the specific city.
-- Maintain consistency so that no card is given twice.

GameEngine.java
It is the main class for the Game Package.
This class is responsible for :
-- Controls the execution of the entire game.
-- Creates object for Player and Region Class.
-- Saves and Load XML file to/from class objects

NewGame.java
This class is used to create GUI. 
This class is responsible for :
-- Showing Players, Regions and other Information in GUI.
-- Populate objects of the Player Class.

Pair.java
This class is a utility class.
This class is responsible for :
-- Returning two values from single function i.e. color and list of player class.

PersonalityCards.java
This class is used to assign personalities to the Players.
This class is responsible for :
-- Returning a personality card at random.
-- Maintain consistency so that no player is assigned with the same personality.

Players.java
This class is used to maintaining Player Info like color, personality, minions, buildings, cash etc.

PlayerCards.java
This class is used to assign cards to the players.
This class is responsible for :
-- Returning card numbers for each player at random.
-- Maintain consistency so that no player is assigned with the same cards.

PlayerStatus.java
This class is used from the region class to store regionwise data for each player. 

RandomEventCards.java
This class is used to get the names for the Random Event Cards.
This class is responsible for :
-- Returning a RandomEventCard at random.
-- Maintain consistency so that no card is drawn twice.

Region.java
This class is used to assign default values to the all the 12 Regions like Region Name, Region Number, Building Cost etc..
This class is responsible for :
-- Maintaining Region Info like number of minions, demons, trolls, existence of building, trouble Marker.

RegionStatus.java
This class is used from the players class to store playerwise data for each region.

SavedGame.java
This class is used for creating GUI for showing Players, Regions and other Information by reading XML file.
-- Populate objects of the other class by the data read from XML.

HTML FILES
-------------------------------------------------------------------
..\src\doc


DEPLOYMENT VIEW
-------------------------------------------------------------------
-- Extract the Zip file.
--Open the same in your Java Workspace.
-- Sometimes it might give an error for : jdom-2.0.5.jar . Please include the same in your project and change the classpath if required.
-- Run the GameEngine class.


