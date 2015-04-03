package Game;

import java.util.ArrayList;
import java.util.List;

public class GameUtility {

	public static List<Player> playerObjList = new ArrayList<Player>(); // list
																		// of
																		// player
																		// objects
	public static List<Region> regionObjList = new ArrayList<Region>(); // list
																		// of
																		// region
																		// objects

	public static void placeMinion(String pColor, int regionNum) {
		for (Player playerObj : GameUtility.playerObjList) {
			if (playerObj.color.equals(pColor)) {
				playerObj.placeMinion(regionNum);
				break;
			}
		}
		GameUtility.regionObjList.get(regionNum - 1).placeMinion(pColor);
	}

	public static void removeMinion(String pColor, int regionNum) {
		for (Player playerObj : GameUtility.playerObjList) {
			if (playerObj.color.equals(pColor)) {
				playerObj.removeMinion(regionNum);
			}
		}
		GameUtility.regionObjList.get(regionNum - 1).removeMinion(pColor);
	}

	public static void placeBuilding(String pColor, int regionNum) {
		boolean isAllowed = false;
		for (Player playerObj : GameUtility.playerObjList) {
			if (playerObj.color.equals(pColor)) {
				if(playerObj.cashHold >= GameUtility.regionObjList.get(regionNum - 1).rBuildingCost)
				{
					isAllowed = true;
					break;
				}
			}
		}
		
		if(isAllowed)
		{
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.color.equals(pColor)) {
					playerObj.placeBuilding(regionNum);
				}
			}
			GameUtility.regionObjList.get(regionNum - 1).placeBuilding(pColor);
		}
		else
		{
			NewGame.showErrorDialog("You are not having enough money to place the building in region: "+regionNum);
		}		
	}

	public static void removeBuilding(String pColor, int regionNum) {
		for (Player playerObj : GameUtility.playerObjList) {
			if (playerObj.color.equals(pColor)) {
				playerObj.removeBuilding(regionNum);
			}
		}
		GameUtility.regionObjList.get(regionNum - 1).removeBuilding(pColor);
	}
}
