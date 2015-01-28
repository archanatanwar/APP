package Game;

import java.util.Hashtable;

/**
 * 
 */

/**
 * @author Archana
 *
 */
public class RegionStatus {
	String rName;
	int rNumber;
	int buildingCost;
	int minionNum;
	int troubleMarker;
	int demon;
	int troll;
		
	Hashtable<String,PlayerStatus> H_Player;
	PlayerStatus SP;
	public RegionStatus(String Name, int Number, int bCost) {
		rName = Name;
		rNumber = Number;
		buildingCost = bCost;
		minionNum = 0;
		troubleMarker = 0;
		demon = 0;
		troll = 0;
		H_Player = new Hashtable<String, PlayerStatus>();
	}
	
	public void placeDefaultMinion(String color)
	{
		minionNum = minionNum + 1;
		if (minionNum > 1)
		{
			troubleMarker = 1;
		}
		if (H_Player.containsKey(color)) {
			H_Player.get(color).pMinionRegionwise = minionNum;
		}
		else
		{
			SP = new PlayerStatus();
			SP.color = color;
			SP.pMinionRegionwise = minionNum;
			SP.pbuildingRegionwise = 0;			
			H_Player.put(color, SP);
		}
	}
}
