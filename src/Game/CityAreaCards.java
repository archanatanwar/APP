package Game;

import java.util.*;

/**
 *
 * @author nav_k <h1>Representation of City Area Cards</h1>
 *         <p>
 *         Contains City Area Cards in enum data structure and a method that <br>
 *         returns a City Area Card whenever it is called i.e. for <br>
 *         the purpose of distributing cards
 *         </p>
 * 
 */

public class CityAreaCards {
	/**
	 * <h1>City Area Cards Enum</h1>
	 * <p>
	 * Contains City Area Card names to which details will be added
	 * </p>
	 * 
	 * @author nav_k
	 *
	 */
	public enum getCityAreaCard {
		DOLLY_SISTERS, UNREAL_ESTATE, DRAGONS_LANDING, SMALL_GODS, THE_SCOURS, THE_HIPPO, THE_SHADES, DIMWELL, LONGWALL, ISLE_OF_GODS, SEVEN_SLEEPERS, NAP_HILL
	}

	// Contains all the values of enum
	public static List<getCityAreaCard> cityAreaList = Arrays
			.asList(getCityAreaCard.values());
	public static List<getCityAreaCard> getCityCardsName = Arrays
			.asList(getCityAreaCard.values());

	// Contains key as City Number and Value as Name of City Area
	public static HashMap<Integer, String> mapCityArea = new HashMap<Integer, String>();

	public static HashMap<String, Integer> mapCityArea2 = new HashMap<String, Integer>();

	/**
	 * One parameterized function that takes integer value as City Are Number
	 * and whenever a player requests for a particular City Area Card, method
	 * returns the details(name as of now) of the same.
	 * 
	 * @param cityAreaToMatch
	 *            Matches given number with city area name
	 * @return String returns name of city area
	 */
	public static String getCityAreaCard(int cityAreaToMatch) {
		String valueToAdd = "";
		int counter = 1;

		for (getCityAreaCard a : cityAreaList) {
			valueToAdd = a.toString();
			mapCityArea.put(counter, valueToAdd);
			counter++;
		}
		// Map that removes the Name of city area corresponding to the number
		// given
		String valueToReturn = mapCityArea.get(cityAreaToMatch);
		// mapCityArea.remove(cityAreaToMatch);
		return valueToReturn.toString();
	}

	public static Integer getNumberCityArea(String value) {
		String valueToAdd = "";
		int counter = 1;

		for (getCityAreaCard a : cityAreaList) {
			valueToAdd = a.toString();
			mapCityArea2.put(valueToAdd, counter);
			counter++;
		}
		int result = mapCityArea2.get(value);
		return result;
	}

	// player gets money from bank
	public static void takeMoneyFromBank(int amount) {
		GameEngine.BankHold = GameEngine.BankHold - amount;
		for (Player playerObj : GameUtility.playerObjList) {
			if (playerObj.pTurn == 1) {
				playerObj.cashHold = playerObj.cashHold + amount;
			}
		}
	}

	// player gives money to bank
	public static void giveMoneyToBank(int amount) {
		GameEngine.BankHold = GameEngine.BankHold + amount;
		for (Player playerObj : GameUtility.playerObjList) {
			if (playerObj.pTurn == 1) {
				playerObj.cashHold = playerObj.cashHold - amount;
			}
		}
	}

	/**
	 * Function gets name of city area and performs the actions accordingly
	 * whose action is to be performed
	 * 
	 * @param cityArea
	 *            Integer name of region in which player's building is placed
	 */
	public static void playCityAreaCard(int cityArea) {
		//cityArea = 1;
		switch (cityArea) {
		// Dolly Sisters
		case 1:
			// pay $3
			giveMoneyToBank(3);
			// place minion in dolly sisters or adjacent area
			List<String> regionList = new ArrayList<String>();
			String str = String.valueOf(1);
			regionList.add(str);
			for (int i : GameEngine.objDSisters.listForNeighbours) {
				String str1 = String.valueOf(i);
				regionList.add(str1);
			}

			String area = NewGame.displayComboBox(
				 "Select region to place minion: ",
					regionList);
			int area2 = Integer.parseInt(area);
			String playerColor = "";
			for(Player playerobj : GameUtility.playerObjList)
			{
				if(playerobj.pTurn == 1)
				{
					playerColor = playerobj.color;
				}
			}
			GameUtility.placeMinion(playerColor, area2);
			break;

		// Unreal Estate
		case 2:
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.pTurn == 1) {
					// take one card from deck
					List<String> list = playerObj.pCards.get("Green");
					Pair pair = PlayerCards.getPlayerCard();
					String cardNo_temp = pair.getCard();
					NewGame.displayPlayerCardFromDeck(cardNo_temp);
					ArrayList<String> choice3 = new ArrayList<String>();
					choice3.add("yes");
					choice3.add("no");
					String response = " ";
					response = NewGame.displayComboBox(
							"Do you want to take player card?", choice3);

					if (response.equals("yes")) {
						list.add(cardNo_temp);
						playerObj.pCards.put("Green", list);

						// discard one card
						List<String> cards = PlayerCards.pCardsTemp;
						System.out.println("outside for");

//						for (String a : PlayerCards.pCardsTemp) {
//							System.out.println("In City Area:   " + a);
//						}
						String cardName = NewGame.displayComboBox(
								"Select card to be discarded",
								PlayerCards.pCardsTemp);
						List<String> greenList = new ArrayList<>();
						greenList = playerObj.pCards.get("Green");
						for (int i = 0; i < greenList.size(); i++) {
							// get index of given cardName in the list
							if (greenList.get(i).equals(cardName)) {
								greenList.remove(i);
							}
						}
						// remove all green colored cards from hashmap
						// playerObj.pCards.remove("Green");
						// add new list to hashmap
						playerObj.pCards.put("Green", greenList);

					}

					else {
						// put the card back to deck

					}

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
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.pTurn == 1) {
					// discard one card
					List<String> cards = PlayerCards.pCardsTemp;
					String cardName = NewGame.displayComboBox(
							"Select card to be discarded", PlayerCards.pCardsTemp);
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
			List<String> regionList3 = new ArrayList<String>();
			String str4 = String.valueOf(7);
			regionList3.add(str4);
			for (int i : GameEngine.objTShades.listForNeighbours) {
				String str1 = String.valueOf(i);
				regionList3.add(str1);
			}

			String area5 = NewGame.displayComboBox(
					"Select region to place trouble marker: ",
					regionList3);
			int area6 = Integer.parseInt(area5);
			GameUtility.regionObjList.get(area6 - 1).placeTroubleMarker();
			break;

		// Dimwell
		case 8:
			// pay $3
			giveMoneyToBank(3);
			// place minion in Dimwell or adjacent area
			List<String> regionList2 = new ArrayList<String>();
			String str2 = String.valueOf(8);
			regionList2.add(str2);
			for (int i : GameEngine.objDimwell.listForNeighbours) {
				String str1 = String.valueOf(i);
				regionList2.add(str1);
			}

			String area3 = NewGame.displayComboBox(
					"Select region to place minion : ",
					regionList2);
			int area4 = Integer.parseInt(area3);
			String playerColor1 = "";
			for(Player playerobj : GameUtility.playerObjList)
			{
				if(playerobj.pTurn == 1)
				{
					playerColor1 = playerobj.color;
				}
			}
			GameUtility.placeMinion(playerColor1, area4);
			break;
		// Longwall
		case 9:
			// Take $1 from bank
			takeMoneyFromBank(1);
			break;
		// Isle Of Gods
		case 10:
			// pay $2
			giveMoneyToBank(2);
			// remove trouble marker from board
			List<String> tempArray = new ArrayList<String>();
			for (Region regionObj : GameUtility.regionObjList) {
				// region contains trouble markers
				if (regionObj.rTroubleMarker > 0) {
					String str3 = String.valueOf(regionObj.rNumber);
					tempArray.add(str3);
				}
			}

			String comboChoice = NewGame
					.displayComboBox(
							"Select a region to remove trouble marker from ",
							tempArray);
			int newReg2 = Integer.parseInt(comboChoice);
			GameUtility.regionObjList.get(newReg2 - 1).removeTroubleMarker();
			break;
		// Seven Sleepers
		case 11:
			// take $3 from bank
			takeMoneyFromBank(3);
			break;
		// Nap Hill
		case 12:
			// take $1 from bank
			takeMoneyFromBank(1);
			break;

		default:
			throw new IllegalArgumentException("Invalid city area: " + cityArea);
		}
	}

	/**
	 * Helps player to place minion in the given region or an adjacent region
	 * 
	 * @param region
	 *            Integer in which player's minion can be placed
	 */
	public static void placeMinion(int region) {
		String newRegion;
		int newReg, result = 0;

		newRegion = NewGame.displayBox("Select region number (" + region
				+ " or adjacent area) in which minion should be placed:");
		newReg = Integer.parseInt(newRegion);
		for (Player playerObj : GameUtility.playerObjList) {
			if (playerObj.pTurn == 1) {
				if (GameUtility.regionObjList.get(region - 1).listForNeighbours
						.contains(newReg) || newReg == region) {
					result = 1;
				}
				while (result == 0) {
					NewGame.showErrorDialog("Wrong Input!");
					newRegion = NewGame
							.displayBox("Select region number ("
									+ region
									+ " or adjacent area) in which minion should be placed:");
					newReg = Integer.parseInt(newRegion);
					if (GameUtility.regionObjList.get(region - 1).listForNeighbours
							.contains(newReg) || newReg == region) {
						result = 1;
					}
				}
				if (playerObj.minionHold >= 1 && result == 1) {
					playerObj.placeMinion(newReg);
					GameUtility.regionObjList.get(newReg - 1).placeMinion(
							playerObj.color);
					break;
				}
			}
		}

	}
}
