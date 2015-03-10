package Game;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

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
 * 
 * @author Archana
 *
 */
public class Region {
	String rName; // name of region
	int rNumber; // number of region to be diobjSPlayed on game board
	int rBuilding;
	int rBuildingCost; // building cost of region
	int rMinionNum; // number of minions in each area
	int rTroubleMarker; // number of troublemarkers
	int rDemon; // number of demons
	int rTroll; // number of trolls
	List<Integer> listForNeighbours = new ArrayList<>();

	// keeps track of player associated with minion or building in a region
	Hashtable<String, PlayerStatus> H_Player;

	// class PlayerStatus that contains information of player
	// in the form of color and number of minions and buildings in that region
	PlayerStatus objSP;

	/**
	 * Three parameterized constructor that initializes the name of region,
	 * building cost and number according to given parameters and initializes
	 * all other data members to 0
	 * 
	 * @param Name
	 *            used to initialize data member region name
	 * @param Number
	 *            used to initialize data member region number
	 * @param bCost
	 *            used to initialize data member region building cost
	 */
	public Region(String Name, int Number, int bCost, List<Integer> array) {
		rName = Name;
		rNumber = Number;
		rBuildingCost = bCost;
		rMinionNum = 0;
		rTroubleMarker = 0;
		rDemon = 0;
		rTroll = 0;
		listForNeighbours.addAll(array);
		H_Player = new Hashtable<String, PlayerStatus>();
	}

	public String getRegionName() {
		return this.rName;
	}

	/**
	 * One parameterized function that places the minion of the given color of
	 * player in the three default regions to start the game
	 * 
	 * @param color
	 *            that places minion in the region according to color of player
	 */
	public void placeDefaultMinion(String color) {
		rMinionNum = rMinionNum + 1;
		// if number of minions is more than one in a region
		if (rMinionNum > 1 && rTroubleMarker == 0) {
			// place a troublemarker
			rTroubleMarker = 1;
			GameEngine.TMarkerHold--;
		}
		if (H_Player.containsKey(color)) {
			// add the values to the HashMap if key is already there
			H_Player.get(color).pMinionRegionwise = 1;
		} else {
			// otherwise create a new key for the player and save the info
			objSP = new PlayerStatus();
			objSP.color = color;
			objSP.pMinionRegionwise = 1;
			objSP.pbuildingRegionwise = 0;
			H_Player.put(color, objSP);
		}
	}

	public int getRBCost() {
		return this.rBuildingCost;
	}

	public int getMinion() {
		return this.rMinionNum;
	}

	public int getTMarker() {
		return this.rTroubleMarker;
	}

	public int placeDemon() {
		int result = 0;
		if(GameEngine.DemonsHold >= 1)
		{
			if(rDemon > 1)
			{
				result = placeTroubleMarker();
			}
			rDemon++;
			GameEngine.DemonsHold--;
			result = 1;
		}
		return result;
	}

	public int removeDemon() {
		int result = 0;
		if(rDemon >= 1)
		{
			rDemon = rDemon - 1;
			GameEngine.DemonsHold++;
			removeTroubleMarker();
			result = 1;
		}
		return result;
	}

	public int placeTroll() {
		int result = 0;
		if(GameEngine.TrollsHold >= 1)
		{
			rTroll = rTroll + 1;
			GameEngine.TrollsHold--;
			placeTroubleMarker();
			result = 1;
		}
		return result;
	}

	public int removeTroll() {
		int result = 0;
		if(rTroll >= 1)
		{
			rTroll = rTroll - 1;
			GameEngine.TrollsHold++;
			removeTroubleMarker();
			result = 1;
		}
		return result;
	}
	
	public int placeTroubleMarker() {
		int result = 0;
		if(GameEngine.TMarkerHold >= 0)
		{
			if(rTroubleMarker == 0)
			{
				GameEngine.TMarkerHold--;
				rTroubleMarker = 1;	
			}					
			result = 1;
		}
		return result;
	}

	public void removeTroubleMarker() {
		if(rTroubleMarker == 1)
		{
			rTroubleMarker = 0;
			GameEngine.TMarkerHold++;
		}
	}
	
	public int checkBuildingMove(String tColor)
	{
		int result = 0;
		if(rBuilding == 0 && H_Player.containsKey(tColor)&& H_Player.get(tColor).pMinionRegionwise >= 1)
		{
			result = 1;			
		}		
		return result;		
	}
	
	public void placeMinion(String color) {	
		rMinionNum++;
		if (H_Player.containsKey(color)) {
			H_Player.get(color).pMinionRegionwise = H_Player.get(color).pMinionRegionwise + 1;
		}
		else
		{
			objSP = new PlayerStatus();
			objSP.color = color;
			objSP.pbuildingRegionwise = 0;
			objSP.pMinionRegionwise = 1;
			H_Player.put(color, objSP);
		}
		if(rMinionNum > 1)
		{
			placeTroubleMarker();
		}
	}
	
	public void removeMinion(String color) {
		if(H_Player.get(color).pMinionRegionwise >= 1)
		{
			rMinionNum--;
			H_Player.get(color).pMinionRegionwise = H_Player.get(color).pMinionRegionwise - 1;
			removeTroubleMarker();
		}
	}
	
	public void placeBuilding(String color) {
		rBuilding++;
		H_Player.get(color).pbuildingRegionwise = H_Player.get(color).pbuildingRegionwise + 1;
	}
	
	public void removeBuilding(String color) {
		if(H_Player.get(color).pbuildingRegionwise >= 1)
		{
			rBuilding--;
			H_Player.get(color).pbuildingRegionwise = H_Player.get(color).pbuildingRegionwise - 1;
		}
	}
	
	public void executeDragonEvent()
	{
		rMinionNum = 0;
		rBuilding = 0;
		Set<String> keys = H_Player.keySet();
		for (String key : keys) {
			H_Player.get(key).pbuildingRegionwise = 0;
			H_Player.get(key).pMinionRegionwise = 0;
		}
		GameEngine.DemonsHold = GameEngine.DemonsHold + rDemon;
		GameEngine.TrollsHold = GameEngine.TrollsHold + rTroll;
		GameEngine.TMarkerHold = GameEngine.TMarkerHold + rTroubleMarker;
		rDemon = 0;
		rTroll = 0;
		rTroubleMarker = 0;		
	}
	
	public int executeFireEvent()
	{
		int result = 0;
		if(rBuilding == 1)
		{
			Set<String> keys = H_Player.keySet();
			for (String key : keys) {
				H_Player.get(key).pbuildingRegionwise = 0;
			}
			result = 1;
		}
		return result;
	}
	
	public void removeAllBuilding() 
	{
		rBuilding = 0;
		for (String key : H_Player.keySet()) {
			H_Player.get(key).pbuildingRegionwise  = 0;
		}
	}
}




