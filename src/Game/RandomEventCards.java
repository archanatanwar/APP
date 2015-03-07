package Game;

import java.util.*;


/**
*
* @author nav_k
* <h1>Represenation of Random Event Cards</h1>
* <p>
* Contains Random Cards in enum data structure and a method that <br>
* returns a Random Event Card whenever it is called i.e. for <br>
* the purpose of distributing cards 
* </p>
*/
public class RandomEventCards
{
	// Contains Random Event card names to which details will be added
	public enum getRandomEventCard{
		THE_DRAGON, FLOOD, FIRE, FOG, RIOTS, EXPLOSION, MYSTERIOUS_MURDERS, DEMONS_FROM_THE_DUNGEONS_DIMENSIONS, SUBSIDENCE, BLOODY_STUPID_JOHNSON, TROLLS, EARTHQUAKE
	}
	
	// Contains all the values of enum
	public static List<getRandomEventCard> shuffleList = Arrays.asList(getRandomEventCard.values());
	public static List<getRandomEventCard> getRandomCardsName = Arrays.asList(getRandomEventCard.values());
	public static List<Integer> regionList = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12);
	public static HashMap<String, List<String>> fogCards;
	static List<String> list = new ArrayList<>();
	/**
	 * Non parameterized function that returns
	 * name of random event card after shuffling the deck
	 * whenever a player requests for a particular random event Card
	 * 
	 * @return String returns name of Random Event Card
	 */
	public static String getRandomEventCard()
	{
		Collections.shuffle(shuffleList);
		getRandomEventCard valueToReturn = shuffleList.get(0);
		String result = valueToReturn.toString();
		//shuffleList.remove(0);
		shuffleList = new ArrayList<getRandomEventCard>(shuffleList);
		shuffleList.remove(0);
		return valueToReturn.toString();
	}
	
	public static int getRollDiceNumber()
	{
		int result = 0;
		Collections.shuffle(regionList);
		result = regionList.get(0);
		return result;
	}
	
	public static void FireEvent()
	{
		int result = 0;
		int resultTemp = 0;
		int isBuildingExist = 0;
		int numNeighbour;
		result = getRollDiceNumber();
		do
		{			
			isBuildingExist = GameEngine.regionObjList.get(result-1).executeFireEvent();
			int size = GameEngine.playerObjList.size();
			for(int i=0; i<size; i++){
				GameEngine.playerObjList.get(i).executeFireEvent(result);
			}
			
			resultTemp = getRollDiceNumber();
			numNeighbour = GameEngine.regionObjList.get(result-1).listForNeighbours.size();
			for(int i=0; i<numNeighbour; i++)
			{
				if (resultTemp == GameEngine.regionObjList.get(result-1).listForNeighbours.get(i))
				{
					result = resultTemp;
					break;
				}				
			}
			if(result != resultTemp)
			{
				break;
			}			
		}while(isBuildingExist == 1);		
	}
	
	public static void dragonEvent()
	{
		int result = 0;
		result = getRollDiceNumber();
		GameEngine.regionObjList.get(result-1).executeDragonEvent();
		int size = GameEngine.playerObjList.size();
		for(int i=0; i<size; i++){
			GameEngine.playerObjList.get(i).executeDragonEvent(result);
		}
	}
	
	public static void FogEvent()
	{
		fogCards = new HashMap<String, List<String>>();
		for (int i = 1; i <= 5; i++) {
			Pair pair = PlayerCards.getPlayerCard();
			String color_temp = pair.getCardColor();
			String cardName_temp = pair.getCard();
			if (fogCards.containsKey(color_temp)) {
				list = fogCards.get(color_temp);
				list.add(cardName_temp);
			} else {
				list.add(cardName_temp);
			}
			fogCards.put(color_temp, list);			
		}
	}
	
	public static void RiotsEvent()
	{
		int minion, mCost, bCost, cash, size, rNum, totalPoints; 
		String color;
		String winnerColor;
		int winner = 0;
		if(GameEngine.TMarkerHold <= 4)
		{
			size = GameEngine.playerObjList.size();
			for(int i=0; i<size; i++)
			{
				bCost = 0;
				totalPoints = 0;
				color = GameEngine.playerObjList.get(i).color;
				minion = GameEngine.playerObjList.get(i).minionHold;				
				mCost = (12-minion)*5;
				cash = GameEngine.playerObjList.get(i).cashHold;
				for (int key : GameEngine.playerObjList.get(i).H_Region.keySet()) {
					if(GameEngine.playerObjList.get(i).H_Region.get(key).placedbuilding == 1)
					{
						rNum = GameEngine.playerObjList.get(i).H_Region.get(key).regionNumber;
						bCost = bCost + GameEngine.regionObjList.get(rNum-1).rBuildingCost;
					}
				}
				totalPoints = mCost + cash + bCost;
				if(winner < totalPoints)
				{
					winnerColor = color;
					winner = totalPoints;
				}
			}
		}
	}
	
	public static void ExplosionEvent()
	{
		int result;
		result = getRollDiceNumber();
		GameEngine.regionObjList.get(result-1).removeAllBuilding();
		for(int i=0; i<GameEngine.playerObjList.size(); i++)
		{
			GameEngine.playerObjList.get(i).removeBuilding(result);
		}
	}
	
	public static void SubsidenceEvent()
	{
		for(int i=0; i<GameEngine.playerObjList.size(); i++)
		{
			GameEngine.playerObjList.get(i).handleSubsidenceEvent();
		}		
	}
	
	public static void EarthquakeEvent()
	{
		ExplosionEvent();
		ExplosionEvent();
	}
	
	public static void TrollsEvent()
	{
		int result;
		for(int i=0; i<3; i++)
		{
			result = getRollDiceNumber();
			GameEngine.regionObjList.get(result-1).placeTroll(result);
		}
	}
	
	public static void DungeonEvent()
	{
		int result;
		for(int i=0; i<4; i++)
		{
			result = getRollDiceNumber();
			GameEngine.regionObjList.get(result-1).placeDemon();
			//TODO
		}
	}
}



