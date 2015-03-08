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
		for (int i = 1; i <= 5; i++) {
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
	
	public void placeMinion(int rNum) {
		minionHold--;
		if (H_Region.containsKey(rNum)) {
			H_Region.get(rNum).placedMinion = H_Region.get(rNum).placedMinion + 1;
		}
		else
		{
			SP = new RegionStatus();
			SP.placedbuilding = 0;
			SP.placedMinion = 1;
			H_Region.put(rNum, SP);
		}
	}
	
	public void removeMinion(int rNum) {
		if(H_Region.get(rNum).placedMinion >= 1)
		{
			minionHold++;
			H_Region.get(rNum).placedMinion = H_Region.get(rNum).placedMinion - 1;
		}
	}
	
	public void placeBuilding(int rNum) {
		if(buildingHold >= 1)
		{
			buildingHold--;
			H_Region.get(rNum).placedbuilding = H_Region.get(rNum).placedbuilding + 1;
		}
	}
	
	public void removeBuilding(int rNum) {
		if(H_Region.get(rNum).placedbuilding >= 1)
		{
			buildingHold++;
			H_Region.get(rNum).placedbuilding = H_Region.get(rNum).placedbuilding - 1;
		}
	}
	
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
	
	public void handleSubsidenceEvent()
	{
		int buildingExist, count;
		count = 0;
		buildingExist = 6 - buildingHold;
		while(cashHold < (buildingExist * 2))
		{
			count++;
			buildingExist--;
		}
		cashHold = cashHold - (buildingExist * 2);
		if(count > 0)
		{
			for (int key : H_Region.keySet()) 
			{
				if(H_Region.get(key).placedbuilding == 1)
				{
					buildingHold++;
					H_Region.get(key).placedbuilding = H_Region.get(key).placedbuilding - 1;
					GameEngine.regionObjList.get(key-1).removeBuilding(color);
					count--;
				}
				if(count == 0)
				{
					break;
				}
			}
		}
	}

	public void executeFireEvent (int rNum)
	{
		int tempNum;
		tempNum = H_Region.get(rNum).placedbuilding;
		buildingHold = buildingHold + tempNum;
		H_Region.get(rNum).placedbuilding = 0;
	}
	
	public void executeDragonEvent(int rNum)
	{
		int tempNum;
		tempNum = H_Region.get(rNum).placedMinion;
		minionHold = minionHold + tempNum;
		H_Region.get(rNum).placedMinion = 0;
		tempNum = H_Region.get(rNum).placedbuilding;
		buildingHold = buildingHold + tempNum;
		H_Region.get(rNum).placedbuilding = 0;
		
	}
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

	public int checkVimes(int numPlayers) {
		int result = 0;
		if(PlayerCards.greenPlayerCardsList.isEmpty())
		{
			result = 1;
		}
		return result;
	}
	
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
}
