package Game;

/**
 * <h1>Keeps track of which player has minion or building in a region</h1>
 * <p>
 * PlayerStatus class is used to store the color, number of minions <br>
 * and presence of building in the region for the particular Player.
 * </p>
 */

public class PlayerStatus {
	String color; // color of player
	int pMinionRegionwise; // number of minions of a player in a region
	int pbuildingRegionwise; // number of buildings of a player in a region

	public String getColor() {
		return color;
	}

	public int getMinionRegionwise() {
		return pMinionRegionwise;
	}

	public int getBuildingRegionwise() {
		return pbuildingRegionwise;
	}
}
