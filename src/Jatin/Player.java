/**
 * 
 */
package Jatin;

import java.util.List;

/**
 * @author Jatin
 *
 */
public class Player {
	public Building buildings;
	public Minion minions;
	public int buildingCount;
	public int minionCount;
	public int cardCount;
	public int playerAidCard;
	public int cash;
	public List<CityAreaCard> cityList;
	public static final int MAX_BUILDING_COUNT = 6;
	
	Player(String color, String Personality){
		buildingCount=6;
		minionCount=12;
		cardCount = 5;
		playerAidCard=1;
		cash = 10;
	}
}
