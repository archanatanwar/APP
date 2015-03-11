package Game;
import java.util.*;


/**
*
* @author nav_k
* <h1>Representation of City Area Cards</h1>
* <p>
* Contains City Area Cards in enum data structure and a method that <br>
* returns a City Area Card whenever it is called i.e. for <br>
* the purpose of distributing cards
* </p>
*  
*/

public class CityAreaCards
{
	// Contains City Area Card names to which details will be added
	public enum getCityAreaCard
	{
		DOLLY_SISTERS, UNREAL_ESTATE, DRAGONS_LANDING, SMALL_GODS, THE_SCOURS, 
		THE_HIPPO, THE_SHADES, DIMWELL, LONGWALL, ISLE_OF_GODS, SEVEN_SLEEPERS, NAP_HILL
	}
	

	// Contains all the values of enum
	public static List<getCityAreaCard> cityAreaList = Arrays.asList(getCityAreaCard.values());
	public static List<getCityAreaCard> getCityCardsName = Arrays.asList(getCityAreaCard.values());
	
	// Contains key as City Number and Value as Name of City Area
	public static HashMap<Integer, String> mapCityArea = new HashMap<Integer, String>();
	

	/**
	 * One parameterized function that takes integer value as 
	 * City Are Number
	 * and whenever a player requests for a particular City Area Card,
	 * method returns the details(name as of now) of the same.
	 * @param cityAreaToMatch Matches given number with city area name
	 * @return String returns name of city area
	 */
	public static String getCityAreaCard(int cityAreaToMatch)
	{
		String valueToAdd = "";
		int counter = 1;
//		for(getCityAreaCard a: tempList)
//		{
//			System.out.println(a.toString());
//		}
		
		for(getCityAreaCard a: cityAreaList)
		{
			 valueToAdd = a.toString();
			 mapCityArea.put(counter, valueToAdd);
			 counter++;
		}
		// Map that removes the Name of city area corresponding to the number given
		String valueToReturn = mapCityArea.get(cityAreaToMatch);
		mapCityArea.remove(cityAreaToMatch);
		return valueToReturn.toString();
	}
	
	// player gets money from bank
	static void takeMoneyFromBank(int amount)
	{
		GameEngine.BankHold = GameEngine.BankHold - amount;
		for(Player playerObj : GameEngine.playerObjList)
		{
			if(playerObj.pTurn == 1)
			{
				playerObj.cashHold = playerObj.cashHold + amount;
			}
		}
	}
	
	// player gives money to bank
	static void giveMoneyToBank(int amount)
	{
		GameEngine.BankHold = GameEngine.BankHold + amount;
		for(Player playerObj : GameEngine.playerObjList)
		{
			if(playerObj.pTurn == 1)
			{
				playerObj.cashHold = playerObj.cashHold - amount;
			}
		}
	}
	
	
	/**
	 * Function gets name of city area and performs the actions accordingly
	 * 					whose action is to be performed
	 * @param cityArea Integer name of region in which player's building is placed
	 */
	public static void playCityAreaCard(int cityArea)
	{
		switch(cityArea){
		// Dolly Sisters
		case 1:			
			//pay $3
			giveMoneyToBank(3);
			// place minion in dolly sisters or adjacent area
			placeMinion(1);			
			break;
		// Unreal Estate	
		case 2:
			for(Player playerObj: GameEngine.playerObjList)
			{
				if(playerObj.pTurn == 1)
				{
					// take one card from deck
					List<String>list = playerObj.pCards.get("Green");
					Pair pair = PlayerCards.getPlayerCard();
					String color_temp = pair.getCardColor();
					String cardNo_temp = pair.getCard();
					list.add(cardNo_temp);
					playerObj.pCards.put("Green", list);
					
					// discard one card
					String cardName = NewGame.displayBox("Enter name of player card you want to discard");
					List<String> greenList = new ArrayList<>();
					greenList = playerObj.pCards.get("Green");
					for (int i = 0; i < greenList.size(); i++) {
						// get index of given cardName in the list
						if (greenList.get(i).equals(cardName)) {
							greenList.remove(i);
						}
					}
					// remove all green colored cards from hashmap
					playerObj.pCards.remove("Green");
					// add new list to hashmap
					playerObj.pCards.put("Green", greenList);
				}
			}
			break;
		// Dragon's Landing
		case 3:
			// take $2 from the bank
			takeMoneyFromBank(2);
			break;
		// Small Gods
		case 4:
			break;
		// The Scours
		case 5:
			// discard one card
			for(Player playerObj: GameEngine.playerObjList)
			{
				if(playerObj.pTurn == 1)
				{
					String cardName = NewGame.displayBox("Enter name of player card you want to discard");
					List<String> greenList = new ArrayList<>();
					greenList = playerObj.pCards.get("Green");
					for (int i = 0; i < greenList.size(); i++) {
						// get index of given cardName in the list
						if (greenList.get(i).equals(cardName)) {
							greenList.remove(i);
						}
					}
					// remove all green colored cards from hashmap
					playerObj.pCards.remove("Green");
					// add new list to hashmap
					playerObj.pCards.put("Green", greenList);
				}
			}	
			// take $2 from the bank
			takeMoneyFromBank(2);
			break;
		// The Hippo
		case 6:
			// player takes $2 from the bank
			takeMoneyFromBank(2);
			break;
		// The Shades
		case 7:
			// place trouble marker in Shades or adjacent area			
			int result=0;
			String newRegion;
			int newReg;
			newRegion = NewGame
					.displayBox("Select region number (Shades or adjacent area) in which trouble marker should be placed:");
			newReg = Integer.parseInt(newRegion);
			for(Player playerObj: GameEngine.playerObjList)
			{
				if(playerObj.pTurn == 1)
				{
					if(GameEngine.regionObjList.get(6).listForNeighbours.contains(newReg) || newReg==7)
					{
						result = 1;
					}
					while(result == 0)
					{
						NewGame.showErrorDialog("Wrong Input!");
						newRegion = NewGame
								.displayBox("Select region number (Shades or adjacent area) in which trouble marker should be placed:");
						newReg = Integer.parseInt(newRegion);
						if(GameEngine.regionObjList.get(0).listForNeighbours.contains(newReg) || newReg==7)
						{
							result = 1;
						}
					}
					if(result == 1)
					{
						GameEngine.regionObjList.get(newReg-1).placeTroubleMarker();
						break;
					}
				}
			}
			
			break;
		// Dimwell
		case 8:
			// pay $3
			giveMoneyToBank(3);
			// place minion in Dimwell or adjacent areas
			placeMinion(8);
			break;	
		// Longwall
		case 9:
			// Take $1 from bank
			takeMoneyFromBank(1);
			break;
		// Isle Of Gods
		case 10:
			//pay $2
			giveMoneyToBank(2);
			// remove trouble marker from board			
			newRegion = NewGame
					.displayBox("Select region number from which trouble marker should be removed:");
			newReg = Integer.parseInt(newRegion);
			GameEngine.regionObjList.get(newReg-1).removeTroubleMarker();			
			break;
		// Seven Sleepers	
		case 11:
			// take $3 from bank
			takeMoneyFromBank(3);
			break;
		// Nap Hill
		case 12:
			//take $1 from bank
			takeMoneyFromBank(1);
			break;

		 default:
             throw new IllegalArgumentException("Invalid city area: " + cityArea);
		}
	}
	
	/**
	 * Helps player to place minion in the given region
	 * or an adjacent region
	 * @param region Integer in which player's minion can be placed
	 */
	public static void placeMinion(int region)
	{
		String newRegion;
		int newReg, result=0;
		
		newRegion = NewGame
				.displayBox("Select region number ("+region+" or adjacent area) in which minion should be placed:");
		newReg = Integer.parseInt(newRegion);
		for(Player playerObj: GameEngine.playerObjList)
		{
			if(playerObj.pTurn == 1)
			{
				if(GameEngine.regionObjList.get(region-1).listForNeighbours.contains(newReg) || newReg==region)
				{
					result = 1;
				}
				while(result == 0)
				{
					NewGame.showErrorDialog("Wrong Input!");
					newRegion = NewGame
							.displayBox("Select region number ("+region+" or adjacent area) in which minion should be placed:");
					newReg = Integer.parseInt(newRegion);
					if(GameEngine.regionObjList.get(region-1).listForNeighbours.contains(newReg) || newReg==region)
					{
						result = 1;
					}
				}
				if(playerObj.minionHold >= 1 && result == 1)
				{
					playerObj.placeMinion(newReg);
					GameEngine.regionObjList.get(newReg-1).placeMinion(playerObj.color);
					break;
				}
			}
		}
		
	}
}


