<<<<<<< HEAD
package Game;

import java.util.Hashtable;

import org.w3c.dom.Node;

/**
 * 
 */

/**
 * <h1>Details of all regions</h1>
 * <p>
 * CLass contains all the fields related to areas on the board as data members,<br>
 * a constructor that initializes their values<br>
 * and a function placeDefaultMinion() to start the game<br>
 * when all players are supposed to put one minion in the areas<br>
 * The Shades, The Scours and Dolly Sisters<br>
 * and one troubleMarker in each area
 * 
 * </p>
 * @author Archana
 *
 */
public class Region 
{
	String rName;                                // name of region
	int rNumber;                                 // number of region to be diobjSPlayed on game board
	int rBuildingCost;                           // building cost of region
	int rMinionNum;                              // number of minions in each area
	int rTroubleMarker;                          // number of troublemarkers
	int rDemon;                                  // number of demons
	int rTroll;                                  // number of trolls
	
	// keeps track of player associated with minion or building in a region
	Hashtable<String,PlayerStatus> H_Player;
	
	// class PlayerStatus that contains information of player 
	//in the form of color and number of minions and buildings in that region
	PlayerStatus objSP;
	
	/**
	 * Three parameterized constructor that initializes the name of region,
	 * building cost and number according to given parameters
	 * and initializes all other data members to 0
	 * @param Name used to initialize data member region name
	 * @param Number used to initialize data member region number
	 * @param bCost used to initialize data member region building cost
	 */
	public Region(String Name, int Number, int bCost) {
		rName = Name;
		rNumber = Number;
		rBuildingCost = bCost;
		rMinionNum = 0;
		rTroubleMarker = 0;
		rDemon = 0;
		rTroll = 0;
		H_Player = new Hashtable<String, PlayerStatus>();
	}
	
	/**
	 * One parameterized function that places the minion of the given
	 * color of player in the three default regions
	 * to start the game
	 * @param color that places minion in the region according to color of player
	 */
	public void placeDefaultMinion(String color)
	{	
		rMinionNum = rMinionNum + 1;
		// if number of minions is more than one in a region
		if (rMinionNum > 1)
		{
			// place a troublemarker
			rTroubleMarker = 1;
		}
		if (H_Player.containsKey(color))
		{
			// add the values to the HashMap if key is already there
			H_Player.get(color).pMinionRegionwise = 1;			
		}
		else
		{
			// otherwise create a new key for the player and save the info
			objSP = new PlayerStatus();
			objSP.color = color;
			objSP.pMinionRegionwise = 1;
			objSP.pbuildingRegionwise = 0;			
			H_Player.put(color, objSP);
		}
	}
}
=======
package Game;

import java.util.Hashtable;

import org.w3c.dom.Node;

/**
 * 
 */

/**
 * <h1>Details of all regions</h1>
 * <p>
 * CLass contains all the fields related to areas on the board as data members,<br>
 * a constructor that initializes their values<br>
 * and a function placeDefaultMinion() to start the game<br>
 * when all players are supposed to put one minion in the areas<br>
 * The Shades, The Scours and Dolly Sisters<br>
 * and one troubleMarker in each area
 * 
 * </p>
 * @author Archana
 *
 */
public class Region 
{
	String rName;                                // name of region
	int rNumber;                                 // number of region to be diobjSPlayed on game board
	int rBuildingCost;                           // building cost of region
	int rMinionNum;                              // number of minions in each area
	int rTroubleMarker;                          // number of troublemarkers
	int rDemon;                                  // number of demons
	int rTroll;                                  // number of trolls
	
	// keeps track of player associated with minion or building in a region
	Hashtable<String,PlayerStatus> H_Player;
	
	// class PlayerStatus that contains information of player 
	//in the form of color and number of minions and buildings in that region
	PlayerStatus objSP;
	
	/**
	 * Three parameterized constructor that initializes the name of region,
	 * building cost and number according to given parameters
	 * and initializes all other data members to 0
	 * @param Name used to initialize data member region name
	 * @param Number used to initialize data member region number
	 * @param bCost used to initialize data member region building cost
	 */
	public Region(String Name, int Number, int bCost) {
		rName = Name;
		rNumber = Number;
		rBuildingCost = bCost;
		rMinionNum = 0;
		rTroubleMarker = 0;
		rDemon = 0;
		rTroll = 0;
		H_Player = new Hashtable<String, PlayerStatus>();
	}
	
	/**
	 * One parameterized function that places the minion of the given
	 * color of player in the three default regions
	 * to start the game
	 * @param color that places minion in the region according to color of player
	 */
	public void placeDefaultMinion(String color)
	{	
		rMinionNum = rMinionNum + 1;
		// if number of minions is more than one in a region
		if (rMinionNum > 1)
		{
			// place a troublemarker
			rTroubleMarker = 1;
		}
		if (H_Player.containsKey(color))
		{
			// add the values to the HashMap if key is already there
			H_Player.get(color).pMinionRegionwise = 1;			
		}
		else
		{
			// otherwise create a new key for the player and save the info
			objSP = new PlayerStatus();
			objSP.color = color;
			objSP.pMinionRegionwise = 1;
			objSP.pbuildingRegionwise = 0;			
			H_Player.put(color, objSP);
		}
	}
}
>>>>>>> 66f9cae7621215b40472ad8d9a2e8c07ef8bf09e
