

package Game;

import java.util.*;
/** 
 * <h1>Info about player</h1>
 * <p>
 * Player class is used to store the color, number of minions <br>
 * and presence of building in the region for the particular Player.<br>
 * It also allows the players to place their minions<br>
 * and trouble markers in the pre-specified regions.
 * </p>
 */
public class Player 
{
	int pNumber;                                 // player number
	static int count = 1;                        
	int pTurn;                                   // variable that is 0 if not players turn and 1 otherwise
	String color;                                // color of minions and buildings with player
	String personality;                          // name of personality card
	int minionHold;                              // number of minions
	int buildingHold;                            // number of buildings
	int cashHold;                                // cash with player
	
	private String[] defaultRegions = { "Dolly Sisters", "The Scours", "The Shades" };

	Hashtable<String, RegionStatus> H_Region;       
	RegionStatus SP;
	HashMap<String, List<Integer>> pCards;         // collection of player cards with color
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
		pNumber = count++;
	}
	
	/**
	 * Function initializes minion, building, cash and cards with player
	 */
	public void initialisePlayer()
	{
		pTurn = 0;
		minionHold = 12;
		buildingHold = 6;
		cashHold = 10;
		// update cash with bank
		GameEngine.BankHold = GameEngine.BankHold - cashHold;
		H_Region = new Hashtable<String, RegionStatus>();
		pCards = new HashMap<String, List<Integer>>();
		// distribute cards
		// five to each player initially
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
	
	/**
	 * Every player places three minions in different regions
	 */
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
	
	public String getColor()
	{
		return color;
	}
	
	public int getPlayerNo()
	{
		return pNumber;
	}
	
	public int getMinion()
	{
		return minionHold;
	}
}



