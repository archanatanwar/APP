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
public class Player {
	int pNumber; // player number
	static int count = 1;
	int pTurn; // variable that is 0 if not players turn and 1 otherwise
	String color; // color of minions and buildings with player
	String personality; // name of personality card
	int minionHold; // number of minions
	int buildingHold; // number of buildings
	int cashHold; // cash with player

	private int[] defaultRegions = {1,5,7};

	Hashtable<Integer, RegionStatus> H_Region;
	RegionStatus SP;
	HashMap<String, List<String>> pCards; // collection of player cards with
											// color
	List<String> list = new ArrayList<>();

	/**
	 * Constructor initializes data members according to given values
	 * 
	 * @param colorTemp
	 *            String sets value of color
	 * @param personalityTemp
	 *            String sets value of personality
	 */
	public Player(String colorTemp, String personalityTemp) {
		color = colorTemp;
		personality = personalityTemp;
		pNumber = count;
		count++;
	}

	/**
	 * Function initializes minion, building, cash and cards with player
	 */
	public void initialisePlayer() {
		pTurn = 0;
		minionHold = 12;
		buildingHold = 6;
		cashHold = 10;
		// update cash with bank
		GameEngine.BankHold = GameEngine.BankHold - cashHold;
		H_Region = new Hashtable<Integer, RegionStatus>();
		pCards = new HashMap<String, List<String>>();
		// distribute cards
		// five to each player initially
		for (int i = 0; i < 5; i++) {
			Pair pair = PlayerCards.getPlayerCard();
			String color_temp = pair.getCardColor();
			String cardName_temp = pair.getCard();
			if (pCards.containsKey(color_temp)) {
				list = pCards.get(color_temp);
				list.add(cardName_temp);
			} else {
				list.add(cardName_temp);
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
			int temp = defaultRegions[i];
			if (H_Region.containsKey(temp)) {
				H_Region.get(defaultRegions[i]).placedMinion = 1;
			} else {
				SP = new RegionStatus();
				SP.regionNumber = defaultRegions[i];
				SP.placedbuilding = 0;
				SP.placedMinion = 1;
				H_Region.put(defaultRegions[i], SP);
			}
		}
	}

	public String getColor() {
		return color;
	}

	public int getPlayerNo() {
		return pNumber;
	}

	public int getMinion() {
		return minionHold;
	}
	
	/**
	 * Helps place player's minion in the region specified
	 * @param rNum Integer represents region number
	 */
	public void placeMinion(int rNum) {
		minionHold--;
		if (H_Region.containsKey(rNum)) {
			H_Region.get(rNum).placedMinion = H_Region.get(rNum).placedMinion + 1;
		}
		else
		{
			SP = new RegionStatus();
			SP.regionNumber = rNum;
			SP.placedbuilding = 0;
			SP.placedMinion = 1;
			H_Region.put(rNum, SP);
		}
	}
	
	/**
	 * Helps remove player's minion from the given region
	 * @param rNum Integer represents region number
	 */
	public void removeMinion(int rNum) {
		if(H_Region.containsKey(rNum) && H_Region.get(rNum).placedMinion >= 1)
		{
			minionHold++;
			H_Region.get(rNum).placedMinion = H_Region.get(rNum).placedMinion - 1;
		}
	}
	
	/**
	 *  Helps place player's building in the region specified
	 * @param rNum Integer represents region number
	 */
	public void placeBuilding(int rNum) {
		if(buildingHold >= 1)
		{
			buildingHold--;
			H_Region.get(rNum).placedbuilding = H_Region.get(rNum).placedbuilding + 1;
		}
	}
	
	/**
	 * Helps remove player's building from the given region
	 * @param rNum Integer represents region number
	 */
	public void removeBuilding(int rNum) {
		if(H_Region.containsKey(rNum) && H_Region.get(rNum).placedbuilding >= 1)
		{
			buildingHold++;
			H_Region.get(rNum).placedbuilding = H_Region.get(rNum).placedbuilding - 1;
		}
	}
	
	/**
	 * Method checks if a minion can be moved to a region or an adjacent area.
	 * It acts as a utility method for place minion method.
	 * @param rNum Integer represents region number
	 * @return Integer 1 if minion can be placed
	 */
	public int checkMinionMove(int rNum) {
		int result = 0;
		if (H_Region.containsKey(rNum)) {			
			if(H_Region.get(rNum).placedMinion >= 1){
				result = 1;		
			}
		}
		else
		{
			for (int key : H_Region.keySet()) {
				if(GameEngine.regionObjList.get(key-1).listForNeighbours.contains(rNum))
				{
					result = 1;
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * Utility method to execute Subsidence event card
	 * 
	 */
	
	public void handleSubsidenceEvent()
	{
		int buildingExist, count;
		count = 0;
		String sValue = "";
		buildingExist = 6 - buildingHold;
		while(cashHold < (buildingExist * 2))
		{
			count++;
			buildingExist--;
		}
		cashHold = cashHold - (buildingExist * 2);
		if(count > 0)
		{
			sValue = sValue + "Building removed from region:";
			for (int key : H_Region.keySet()) 
			{
				if(H_Region.get(key).placedbuilding == 1)
				{
					buildingHold++;
					H_Region.get(key).placedbuilding = H_Region.get(key).placedbuilding - 1;
					GameEngine.regionObjList.get(key-1).removeBuilding(color);
					count--;
					sValue = sValue + key + " ";
				}
				if(count == 0)
				{
					break;
				}
			}
		}
		sValue = sValue + "\n Cash taken from player "+color+" :"+buildingExist*2;
		NewGame.showErrorDialog(sValue);
	}

	/**
	 * utility method to execute fire event
	 * @param region number
	 */
	
	public void executeFireEvent (int rNum)
	{
		int tempNum = 0;
		if(H_Region.containsKey(rNum))
		{
			tempNum = H_Region.get(rNum).placedbuilding;
		}
		buildingHold = buildingHold + tempNum;
		H_Region.get(rNum).placedbuilding = 0;
	}
	
	/**
	 * Utility method to execute Dragon event card
	 * @param region number
	 */
	
	public void executeDragonEvent(int rNum)
	{
		int tempMinion = 0;
		int tempBuilding = 0;
		if(H_Region.containsKey(rNum))
		{
			tempMinion = H_Region.get(rNum).placedMinion;
			tempBuilding = H_Region.get(rNum).placedbuilding;
			H_Region.get(rNum).placedMinion = 0;	
			H_Region.get(rNum).placedbuilding = 0;	
		}
		minionHold = minionHold + tempMinion;
			
		buildingHold = buildingHold + tempBuilding;
			
	}
	
	/**
	 * Method check winning condition before each player turn
	 * @param personality
	 * @return 1 if any of winning conditions is established
	 */
	
	public int checkWinningCondition(String personality) {
		int result = 0;
		int numPlayers = GameEngine.playerObjList.size();
		switch (personality) {
		case "Lord Vetinari":
			result = checkLordVetinari(numPlayers);
			break;
		case "Lord Selachii":
		case "Lord Rust":
		case "Lord de Worde":
			result = checkLoad(numPlayers);
			break;
		case "Dragon King of Arms":
			result = checkDKArms(numPlayers);
			break;
		case "Chrysoprase":
			result = checkChrysoprase(numPlayers);
			break;
		case "Commander Vimes":
			result = checkVimes(numPlayers);
			break;
		default:
			System.out.println("Enter Your Choice Correctly!");
			break;
		}
		return result;
	}

	/**
	 * Method check if CommanderVimes wins the game
	 * @param number of players
	 * @return 1 all player cards are already played
	 */
	
	public int checkVimes(int numPlayers) {
		int result = 0;
		if(PlayerCards.greenPlayerCardsList.isEmpty())
		{
			result = 1;
		}
		return result;
	}
	
	/**
	 * Method check if Chrysoprase wins the game
	 * @param number of players
	 * @return 1 if player has more than 50$ worth in hand (cash money + buildings - loans)
	 * 
	 */
	
	public int checkChrysoprase(int numPlayers) {
		int result = 0;
		int tempRnumber;
		int MaxCost;
		MaxCost = cashHold;		
		for (int key : H_Region.keySet()) {
			if (H_Region.get(key).placedbuilding == 1)
			{
				tempRnumber = H_Region.get(key).regionNumber;
				MaxCost = MaxCost + GameEngine.regionObjList.get(tempRnumber-1).rBuildingCost;
			}			
		}
		for (String cardKey : GameEngine.loanCards.keySet()) {
			if(GameEngine.loanCards.get(cardKey).equals(color))
			{
				MaxCost = MaxCost - 12;
			}
		}
		if(MaxCost >= 50)
		{
			result = 1;
		}
		return result;
	}
	
	/**
	 * Method check if Dragon King of Arms wins the game
	 * @param number of players
	 * @return 1 if there are more than eight trouble markers on the board
	 */
	
	public int checkDKArms(int numPlayers) {
		int result = 0;
		int tempCount = 0;
		for (int i = 0; i <= 11; i++) {
			if (GameEngine.regionObjList.get(i).rTroubleMarker == 1)
			{
				tempCount++;
			}
		}
		if(tempCount >= 8)
		{
			result = 1;
		}
		return result;
	}
	
	/**
	 * Method check if Lord Selachii wins the game
	 * @param number of players
	 * @return 1 if player has control of 7,5 or 4 areas depends on 2,3 or 4 players
	 */
	
	public int checkLoad(int numPlayers) {
		int result = 0;
		int tempCount = 0;
		int minionPieces;
		int buildingPieces;
		int maxPieces;
		int totalPieces;
		boolean isMaxPieces = true;

		for (int i = 0; i <= 11; i++) {
			maxPieces = 0;
			if (GameEngine.regionObjList.get(i).rDemon == 0 && GameEngine.regionObjList.get(i).H_Player.containsKey(color)) {
				minionPieces = GameEngine.regionObjList.get(i).H_Player
						.get(color).pMinionRegionwise;
				buildingPieces = GameEngine.regionObjList.get(i).H_Player
						.get(color).pbuildingRegionwise;
				maxPieces = minionPieces + buildingPieces;
				if (maxPieces > GameEngine.regionObjList.get(i).rTroll) {
					Set<String> keys = GameEngine.regionObjList.get(i).H_Player
							.keySet();
					
					for (String key : keys) {
						isMaxPieces = true;
						if (!key.equals(color)) {
							minionPieces = GameEngine.regionObjList.get(i).H_Player
									.get(key).pMinionRegionwise;
							buildingPieces = GameEngine.regionObjList.get(i).H_Player
									.get(key).pbuildingRegionwise;
							totalPieces = minionPieces + buildingPieces;
							if (totalPieces >= maxPieces) {
								isMaxPieces = false;
								break;
							}
						}
					}
					if (isMaxPieces) {
						tempCount++;
					}
				}
			}
		}
		switch (numPlayers) {
		case 2:
			if (tempCount >= 7) {
				result = 1;
			}
			break;
		case 3:
			if (tempCount >= 5) {
				result = 1;
			}
			break;
		case 4:
			if (tempCount >= 4) {
				result = 1;
			}
			break;
		default:
			result = 0;
			break;
		}
		return result;
	}

	/**
	 * Method check if Lord Vetinari wins the game
	 * @param number of players
	 * @return 1 if player has minion on 11,10 or 9 areas depend on number of players 2,3 or 4
	 */
	
	public int checkLordVetinari(int numPlayers) {
		int result = 0;
		int tempCount = 0;
		for (int key : H_Region.keySet()) {
			if (H_Region.get(key).placedMinion > 0
					&& GameEngine.regionObjList.get(key).rDemon == 0) {
				tempCount++;
			}
		}
		switch (numPlayers) {
		case 2:
			if (tempCount >= 11) {
				result = 1;
			}
			break;
		case 3:
			if (tempCount >= 10) {
				result = 1;
			}
			break;
		case 4:
			if (tempCount >= 9) {
				result = 1;
			}
			break;
		default:
			result = 0;
			break;
		}
		return result;
	}
	
	public void moveAllMinion(int oldArea, int newArea)
	{
		int tempMinion = 0;
		if(H_Region.containsKey(oldArea))
		{
			tempMinion = H_Region.get(oldArea).placedMinion;
			H_Region.get(oldArea).placedMinion = 0;
			if(H_Region.contains(newArea))
			{
				H_Region.get(newArea).placedMinion = H_Region.get(newArea).placedMinion + tempMinion;
			}
			else
			{
				SP = new RegionStatus();
				SP.regionNumber = newArea;
				SP.placedbuilding = 0;
				SP.placedMinion = tempMinion;			
				H_Region.put(newArea, SP);
			}
			int numMinion = GameEngine.regionObjList.get(oldArea-1).removeAllMinion(color);
			GameEngine.regionObjList.get(newArea).placeAllMinion(color, numMinion);
			
		}		
	}
}





