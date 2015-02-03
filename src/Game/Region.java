package Game;

import java.util.Hashtable;

/**
 * 
 */

/**
 * @author Archana
 *
 */
public class Region {
	String rName;
	int rNumber;
	int rBuildingCost;
	int rMinionNum;
	int rTroubleMarker;
	int rDemon;
	int rTroll;
		
	Hashtable<String,PlayerStatus> H_Player;
	PlayerStatus SP;
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
	
	public void placeDefaultMinion(String color)
	{	
		rMinionNum = rMinionNum + 1;
		if (rMinionNum > 1)
		{
			rTroubleMarker = 1;
		}
		if (H_Player.containsKey(color)) {
			
			H_Player.get(color).pMinionRegionwise = 1;			
		}
		else
		{
			SP = new PlayerStatus();
			SP.color = color;
			SP.pMinionRegionwise = 1;
			SP.pbuildingRegionwise = 0;			
			H_Player.put(color, SP);
		}
	}
}
