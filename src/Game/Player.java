/** 
<<<<<<< HEAD
 * <h1>Info about player</h1>
 * <p>
 * Player class is used to store the color, number of minions <br>
 * and presence of building in the region for the particular Player.<br>
 * It also allows the players to place their minions<br>
 * and trouble markers in the pre-specified regions.
 * </p>
=======
 * Player class is used to store the color, number of minions 
 * and presence of building in the region for the particular Player.
>>>>>>> 66f9cae7621215b40472ad8d9a2e8c07ef8bf09e
 */

package Game;

import java.util.*;
<<<<<<< HEAD
public class Player 
{
	int pNumber;
	static int count = 1;
	int pTurn;
=======
public class Player {
>>>>>>> 66f9cae7621215b40472ad8d9a2e8c07ef8bf09e
	String color;
	String personality;
	int minionHold;
	int buildingHold;
	int cashHold;
	private String[] defaultRegions = { "Dolly Sisters", "The Scours",
			"The Shades" };

	Hashtable<String, RegionStatus> H_Region;
	RegionStatus SP;
	HashMap<String, List<Integer>> pCards;
	List<Integer> list = new ArrayList<>();
	
	
	/**
	 * Constructor initializes data members according to given values
	 * @param colorTemp String sets value of color
	 * @param personalityTemp String sets value of personality
	 */
	public Player(String colorTemp, String personalityTemp) 
	{
		color = colorTemp;
		personality = personalityTemp;
<<<<<<< HEAD
		pNumber = count++;
	}
	
	/**
	 * Function initializes minion, building, cash with player
	 */
	public void initialisePlayer()
	{
		pTurn = 0;
=======
	}
	
	public void initialisePlayer()
	{
>>>>>>> 66f9cae7621215b40472ad8d9a2e8c07ef8bf09e
		minionHold = 12;
		buildingHold = 6;
		cashHold = 10;
		GameEngine.BankHold = GameEngine.BankHold - cashHold;
		H_Region = new Hashtable<String, RegionStatus>();
		pCards = new HashMap<String, List<Integer>>();

		for (int i = 1; i <= 5; i++) {
			Pair pair = PlayerCards.getPlayerCard();
			String color_temp = pair.getCardColor();
			int cardNo_temp = pair.getCard();
			if (pCards.containsKey(color_temp)) {
				list = pCards.get(color_temp);
				list.add(cardNo_temp);
			} else {
				list.add(cardNo_temp);
			}
			pCards.put(color_temp, list);
		}
	}
	
	
	public void initialPlayerStatus() {
		minionHold = minionHold - 3;
		for (int i = 0; i < defaultRegions.length; i++) {
			String temp = defaultRegions[i];
			if (H_Region.containsKey(temp)) {
				H_Region.get(defaultRegions[i]).placedMinion = 3;
			} else {
				SP = new RegionStatus();
				SP.placedbuilding = 0;
				SP.placedMinion = 3;
				H_Region.put(defaultRegions[i], SP);
			}
		}
	}
}
