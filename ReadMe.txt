Project: Discworld: Ankh-Morpork 
BUILD 3

Team Information
-------------------------------------------------------------------
Student Name : Kaur, Navleen       Student Id : 7256264 
Student Name : Hasaneen, Ayman     Student Id : 6355897
Student Name : Tanwar, Archana     Student Id : 7248571
Student Name : Kashyap, Jatin      Student Id : 6997791
Student Name : Eftekhari, Maryam   Student Id : 7734069


FILES AND FOLDERS
-------------------------------------------------------------------
src/doc/Game : Java Doc
src/Game : Java code files
src/tests : JUnit files/suite
DesignDoc : Software Architectural Document


Package Game:
-------------------------------------------------------------------
CityAreaCards.java
This class is used to get the names for the City Area Cards.
This class is responsible for :
-- Returning a City Area Card for the specific city.
-- Maintain consistency so that no card is given twice.
-- Perform action according to the city selected.

GameEngine.java
Driver class of the game application.
This class is responsible for :
--Execution of the entire game.
--Creation of objects for Player and Region Class.
--Saving and Loading XML file to/from class objects.


NewGame.java
An object of this class is created when Player click on the button "Start New Game". 
This class is responsible for :
--Initialization of GUI elements.
--Populate objects of the Player Class.
--Placing minions in the default area.
--Display of City Area Card according corresponding to selected.
--Display of Personality Card according to selected.


Pair.java
This class is a utility class.
This class is responsible for :
--Returning two values from single function i.e. color and list of player class.

PersonalityCards.java
This class is used to assign personalities to the Players.
This class is responsible for :
-- Returning a personality card at random.
-- Maintain consistency so that no player is assigned with the same personality.

Player.java
Player class is used to store the colour, number of minions and presence of building in the region for a particular Player. Number of objects created of this class is equal to number of players in the game.
This class is responsible for :
--Maintaining Player Info like colour, personality, minions, buildings, cash etc.
--Handling of the Random Events.
--Checking the winning condition related to personality.

PlayerCards.java
This class is called from the Players class to assign cards to the players.
This class is responsible for :
--Creation of deck of brown and green cards and shuffling it.
--Returning card numbers for each player at random.
--Maintain consistency so that no player is assigned with the same cards.
--Moving minion of from one region to another.
--Checking the Interrupt Card.


PlayerStatus.java
This class is used by the Region class to store region wise data for each player.
It keeps track of which player has minion or building in a region.

RandomEventCards.java
It is an abstract class used to get the names for the Random Event Cards.
Separate classes for each RandomEvent are created as concrete classes.
This class is responsible for :
--Return of a random RandomEventCard.
--Maintaining consistency so that no card is drawn twice.


Region.java
This class is called from the GameEngine class to assign default values to the all the twelve different regions like Region Name, Region Number, Building Cost etc.
This class is responsible for :
--Maintaining Region Info like number of minions, demons, trolls, existence of building, trouble Marker.
--Perform function relevant to the region selected.

RegionStatus.java
This class is used from the Player class to store playerwise data for each region. 
It keeps track of which region has minion or building of which player.

SavedGame.java
This class object is created when Player click on the button "Load Game". 
This class is responsible for :
--Creation of GUI for showing Players, Regions and other Information by reading XML file.
--Loading of GUI.
--Populate objects of the other class by the reading XML data file.

bsJohnsonEventCard.java	
--This class is responsible for executing Random Event Bloody Stupid Johnson functionality.

DragonEventCard.java	
--This class is responsible for executing Random Event Dragon functionality.

DungeonEventCard.java	
--This class is responsible for executing Random Event Dungeon functionality.

ExplosionEventCard.java	
--This class is responsible for executing Random Event Explosion functionality.

FireEventCard.java	
--This class is responsible for executing Random Event Fire functionality.

FloodEventCard.java	
--This class is responsible for executing Random Event Flood functionality.

FogEventCard.java	
--This class is responsible for executing Random Event Fog functionality.

mMurdersEventCard.java	
--This class is responsible for executing Random Event Mysterious Murders functionality.

FogEventCard.java	
--This class is responsible for executing Random Event Fog functionality.

SubsidenceEventCard.java	
--This class is responsible for executing Random Event Subsidence functionality.

TrollsEventCard.java	
--This class is responsible for executing Random Event Trolls functionality.

Assets_Extractor.java	
This class is responsible for creating the spreadsheets for images. It does memory management by including all images into one file.

Assets_Loader.java	
--This class is responsible for loading the board image.

AssetsDepot.java	
--This class is responsible for cropping images for minions/trolls/buildings/demons etc.

AssetManager.java	
--This class is responsible for updating the board.


JAVA DOC
-------------------------------------------------------------------
..\src\doc


GITHUB PATH
-------------------------------------------------------------------
https://github.com/archanatanwar/APP/tree/Build-3


DEPLOYMENT VIEW
-------------------------------------------------------------------
--Extract the Zip file.
--Import it as an existing Java project into your Java Workspace.
--Sometimes it might throw an error for: jdom-2.0.5.jar . Please include the same in your project and change the classpath if required.
--Run the GameEngine class as a Java application and play the game.