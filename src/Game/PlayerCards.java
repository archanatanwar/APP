package Game;

import java.util.*;

import javax.swing.JLabel;

import Game.PersonalityCards.getPersonalityCard;

/**
 * <h1>Representation of Player Cards</h1>
 * <p>
 * Contains Player Cards in HashMap data structure , method that<br>
 * creates the deck of cards and a method that <br>
 * returns a Player Card whenever it is called i.e. for the purpose of
 * distributing cards
 * </p>
 * 
 * @author nav_k
 *
 */
public class PlayerCards {
	/**
	 * <h1>Player cards enum</h1>
	 * <p>
	 * deck of player cards(Green as of now)
	 * </p>
	 * 
	 * @author nav_k
	 *
	 */
	public enum PlayerCardDeck {
		MR_BOGGIS, MR_BENT, THE_BEGGARS_GUILD, THE_BANK_OF_ANKH_MORPORK, THE_ANKH_MORPORK_SUNSHINE_DRAGON_SANCTUARY, SERGEANT_ANGUA, THE_AGONY_AUNTS,
		THE_DYSK, THE_DUCKMAN, DRUMKNOTT, CMOT_DIBBLER, DR_CRUCES, CAPTAIN_CARROT, MRS_CAKE, GROAT, GIMLETS_DWARF_DELICATESSEN, GASPODE, 
		FRESH_START_CLUB, FOUL_OLE_RON, THE_FOOLS_GUILD, THE_FIRE_BRIGADE, INIGO_SKIMMER, HISTORY_MONKS, HEX, HERE_N_NOW, HARRY_KING, 
		HARGAS_HOUSE_OF_RIBS, MR_GRYLE, THE_PEELED_NUTS, THE_OPERA_HOUSE, NOBBY_NOBBS, MODO, THE_MENDED_DRUM, LIBRARIAN, LEONARD_OF_QUIRM, 
		SHONKY_SHOP, SACHARISSA_CRIPSLOCK, ROSIE_PALM, RINCEWIND, THE_ROYAL_MINT, QUEEN_MOLLY, PINK_PUSSYCAT_CLUB, ZORGO_THE_RETRO_PHRENOLOGIST, 
		DR_WHITEFACE, WALLACE_SONKY, THE_SEAMSTRESSES_GUILD, MR_PIN_MR_TULIP, THE_THIEVES_GUILD
	}

	/**
	 * symbols on top of cards
	 * 
	 * @author nav_k
	 *
	 */
	enum playerCardSymbols {
		PLACE_MINON, PLACE_BUILDING, ASSASSINATION, REMOVE_ONE_TROUBLE_MARKER, TAKE_MONEY, SCROLL, RANDOM_EVENT, PLAY_ANOTHER_CARD, INTERRUPT
	}

	// list containing region number
	public static List<Integer> regionList = Arrays.asList(1, 2, 3, 4, 5, 6, 7,
			8, 9, 10, 11, 12);

	// list containing all the names of player cards
	public static List<PlayerCardDeck> greenPlayerCardsList = Arrays
			.asList(PlayerCardDeck.values());
	// Contains Player card color as key and list of card numbers as value
	public static HashMap<String, List<Integer>> playerCards;

	public static List<String> playerListCombo = Arrays.asList("red", "yellow",
			"green", "blue");
	public static List<String> regionListCombo = Arrays.asList("1", "2", "3",
			"4", "5", "6", "7", "8", "9", "10", "11", "12");
	public static ArrayList<String> tempArray = new ArrayList<String>();
	static String comboChoice;
	public static List<String> pCardsTemp = new ArrayList<String>();

	// roll die to get a specific number
	public static int rollDie() {
		int result = 0;
		Collections.shuffle(regionList);
		result = regionList.get(0);
		return result;
	}

	/**
	 * method helps the player to pay specified money to bank
	 * 
	 * @param cash
	 *            Integer amount of money to be paid to bank
	 */
	public static void payToBank(int cash) {
		for (Player playerObj : GameUtility.playerObjList) {
			if (playerObj.pTurn == 1) {
				playerObj.cashHold = playerObj.cashHold - cash;
				GameEngine.BankHold = GameEngine.BankHold + cash;
			}
		}
	}

	/**
	 * method helps the player to take loan from bank
	 * 
	 * @param cash
	 *            Integer amount of money taken from bank
	 */
	public static void takeLoanFromBank(int cash) {
		for (Player playerObj : GameUtility.playerObjList) {
			// current player
			if (playerObj.pTurn == 1 && (GameEngine.BankHold >= cash)) {
				GameEngine.BankHold = GameEngine.BankHold - cash;
				playerObj.cashHold = playerObj.cashHold + cash;
			}
		}
	}

	/**
	 * take player cards from other players
	 * 
	 * @param playerNumber
	 *            Integer denotes player number who will give player card
	 * @param cardName
	 *            String name of card that is being played currently
	 */
	public static void takePlayerCard(String pColor, String cardName) {
		String result = null;
		for (Player playerObj : GameUtility.playerObjList) {

			Pair pair = new Pair(" ", " ");
			// player that should give a player card
			if (playerObj.color == pColor) {
				// get key by color green first
				List<String> tempList = playerObj.pCards.get("Green");
				int index = 0;
				for (String a : tempList) {
					if (a.equals(cardName)) {
						break;
					}
					index++;
				}

				result = tempList.get(index);
				System.out.println("result:   " + result);
				tempList.remove(index);
				playerObj.pCards.remove("Green");
				// make sure list is not empty
				if (tempList.size() >= 1) {
					// add again to the hashmap
					playerObj.pCards.put("Green", tempList);
				}
				pair = new Pair(result, "Green");
			} // end if

		} // end for

		List<String> list = new ArrayList<>();
		for (Player playerObj : GameUtility.playerObjList) {
			// player whose turn is 1
			if (playerObj.pTurn == 1) {
				list = playerObj.pCards.get("Green");
				list.add(result);
				playerObj.pCards.put("Green", list);

			} // end if
		}
	}

	public static void givePlayerCard(String pColor, String cardName) {
		String result = null;
		for (Player playerObj : GameUtility.playerObjList) {

			Pair pair = new Pair(" ", " ");
			// player that should give a player card
			if (playerObj.pTurn == 1) {
				// get key by color green first
				List<String> tempList = playerObj.pCards.get("Green");
				int index = 0;
				for (String a : tempList) {
					if (a.equals(cardName)) {
						break;
					}
					index++;
				}

				result = tempList.get(index);
				System.out.println("result:   " + result);
				tempList.remove(index);
				playerObj.pCards.remove("Green");
				// make sure list is not empty
				if (tempList.size() >= 1) {
					// add again to the hashmap
					playerObj.pCards.put("Green", tempList);
				}
				pair = new Pair(result, "Green");
			} // end if

		} // end for

		List<String> list = new ArrayList<>();
		for (Player playerObj : GameUtility.playerObjList) {
			// player whose turn is 1
			if (playerObj.color.equals(pColor)) {
				list = playerObj.pCards.get("Green");
				list.add(result);
				playerObj.pCards.put("Green", list);

			} // end if
		}

	}

	/**
	 * method helps to take money from other players
	 * 
	 * @param cash
	 *            Integer amount of money to be taken
	 * @param playerNumber
	 *            Integer player from whom money should be taken
	 */
	public static void takeMoneyFromOtherPlayers(int cash, int playerNumber) {
		for (Player playerObj : GameUtility.playerObjList) {
			// not current player
			if (playerObj.pNumber == playerNumber
					&& (playerObj.cashHold >= cash)) {
				playerObj.cashHold = playerObj.cashHold - cash;
			}
		}
	}

	/**
	 * method helps to take money from every other player
	 * 
	 * @param cash
	 *            Integer amount of money to be taken
	 */
	public static void takeMoneyFromEveryPlayer(int cash) {
		int count = 0;
		for (Player playerObj : GameUtility.playerObjList) {
			if (playerObj.pTurn == 0 && (playerObj.cashHold >= cash)) {
				// takeMoneyFromOtherPlayers(2, playerObj.pNumber);
				playerObj.cashHold = playerObj.cashHold - cash;
				count = count + cash;
			}
		}
		// add money into current player's account
		for (Player playerObj : GameUtility.playerObjList) {
			// current player
			if (playerObj.pTurn == 1) {
				playerObj.cashHold = playerObj.cashHold + count;
			}
		}
	}

	/**
	 * Helps delete player card from list
	 * 
	 * @param cardName
	 *            String card name that is supposed to be deleted
	 */
	public static void delPlayerCard(String cardName) {
		List<String> greenList = new ArrayList<>();
		for (Player playerObj : GameUtility.playerObjList) {
			if (playerObj.pTurn == 1) {
				// create temp list
				greenList = playerObj.pCards.get("Green");
				for (int i = 0; i < greenList.size(); i++) {
					// get index of given cardName in the list
					if (greenList.get(i).equals(cardName)) {
						String result = greenList.remove(i);
						// add card name to dicard pile
						GameEngine.discardCards.add(result);
					}
				}
				// remove all green colored cards from hashmap
				playerObj.pCards.remove("Green");
				// add new list to hashmap
				playerObj.pCards.put("Green", greenList);
			}
		}
	}

	// returns the cards of current player except the card being played
	public static void getPcardsExceptCurrent(String cardName) {

		for (Player playerObj : GameUtility.playerObjList) {
			if (playerObj.pTurn == 1) {
			// pCardsTemp.clear();
				pCardsTemp = playerObj.pCards.get("Green");
				if (!((cardName.equals("")) || (cardName.equals("GASPODE"))  || (cardName.equals("FRESH_START_CLUB"))  || (cardName.equals("WALLACE_SONKY")))) {
					pCardsTemp.remove(cardName);
				}
			}
		}
	}

	public static List<String> getPlayerCards(String pColor) {
		List<String> cards = new ArrayList<String>();
		for (Player playerObj : GameUtility.playerObjList) {
			if (playerObj.color.equals(pColor)) {
				cards = playerObj.pCards.get("Green");
			}
		}
		return cards;
	}

	public static int getPlayerNumber(String pColor) {
		int pNumber = 0;
		for (Player playerObj : GameUtility.playerObjList) {
			if (playerObj.color.equals(pColor)) {
				pNumber = playerObj.pNumber;
			}
		}
		return pNumber;
	}

	public static List<String> getColorsOfPlayers() {
		ArrayList<String> players = new ArrayList<String>();

		for (Player playerObj : GameUtility.playerObjList) {
			// not current player
			if (playerObj.pTurn == 0) {
				// add color of player to the list
				players.add(playerObj.color);
			}
		}
		return players;
	}

	public static void refillHand() {
		// System.out.println("Refill called!!!!!!!!!");
		for (Player playerObj : GameUtility.playerObjList) {
			// player cards
			if (playerObj.pTurn == 1) {
				List<String> greenList = playerObj.pCards.get("Green");
				// check if player has 5 playing cards
				while (greenList.size() < 5) {
					// draw card from deck
					Pair pair = PlayerCards.getPlayerCard();
					String color_temp = pair.getCardColor();
					String cardNo_temp = pair.getCard();
					greenList.add(cardNo_temp);
				}
				playerObj.pCards.put("Green", greenList);
			}
		}
	}

	public static void delCardFromChoices(List<String> actions, String cardToDel) {
		int flag = 0;
		for (String a : actions) {
			// System.out.println("in for");
			if (a.equals(cardToDel)) {
				// System.out.println("in if");
				break;
			}
			flag++;
		}
		actions.remove(flag);
	}

	/**
	 * Method defines actions from left to right at the top of player cards.<br>
	 * It makes sure that all the symbols are being performed <br>
	 * from left to right
	 * 
	 * @param cardName
	 *            String name of player card for which functions should be
	 *            performed
	 */
	public static void performLeftToRight(String cardName) {
		// check winning condition before playing the player card
		getPcardsExceptCurrent(cardName);
		NewGame.playAnotherCard = 0;
		
		ArrayList<String> choice = new ArrayList<String>();
		choice.add("yes");
		choice.add("no");
		String responseChoice = " ";
		List<String> actions = new ArrayList<String>();
		// cardName = "LIBRARIAN";

		// all the player cards' actions are defined by switch case
		switch (cardName) {
		case "MR_BOGGIS":
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLACE_MINION");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLACE_MINION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_MINION");
					if (actions.contains("SCROLL")) {
						delCardFromChoices(actions, "SCROLL");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}

				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("PLACE_MINION"))
			// {
			// performActionOfSymbol(responseChoice, cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a minion?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_MINION", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "MR_BENT":
			populateLoanCards("MR_BENT");
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLAY_ANOTHER_CARD");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");

				} else if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
					// performActionOfSymbol(responseChoice, cardName);
					// delCardFromChoices(actions, "PLAY_ANOTHER_CARD");
					// if(actions.contains("SCROLL"))
					// {
					// delCardFromChoices(actions, "SCROLL");
					// }
					// delPlayerCard(cardName);
					// performActionOfSymbol(responseChoice, cardName);
					break;
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
				delPlayerCard(cardName);
				performActionOfSymbol(responseChoice, cardName);
			}
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to play another card?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			// }
			else {
				delPlayerCard(cardName);
				refillHand();
			}
			break;
		case "THE_BEGGARS_GUILD":
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLACE_MINION");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLACE_MINION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_MINION");
					if (actions.contains("SCROLL")) {
						delCardFromChoices(actions, "SCROLL");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("PLACE_MINION"))
			// {
			// performActionOfSymbol(responseChoice, cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a minion?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_MINION", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "THE_BANK_OF_ANKH_MORPORK":
			populateLoanCards("THE_BANK_OF_ANKH_MORPORK");
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLAY_ANOTHER_CARD");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
					// performActionOfSymbol(responseChoice, cardName);
					// delCardFromChoices(actions, "PLAY_ANOTHER_CARD");
					// if(actions.contains("SCROLL"))
					// {
					// delCardFromChoices(actions, "SCROLL");
					// }
					break;
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
				delPlayerCard(cardName);
				performActionOfSymbol(responseChoice, cardName);
			}
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to play another card?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			// }
			else {
				delPlayerCard(cardName);
				refillHand();
			}
			break;
		case "THE_ANKH_MORPORK_SUNSHINE_DRAGON_SANCTUARY":
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLAY_ANOTHER_CARD");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
					// performActionOfSymbol(responseChoice, cardName);
					// delCardFromChoices(actions, "PLAY_ANOTHER_CARD");
					// if(actions.contains("SCROLL"))
					// {
					// delCardFromChoices(actions, "SCROLL");
					// }
					break;
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
				delPlayerCard(cardName);
				performActionOfSymbol(responseChoice, cardName);
			}
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to play another card?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			// }
			else {
				delPlayerCard(cardName);
				refillHand();
			}
			break;
		case "SERGEANT_ANGUA":
			actions.clear();
			actions.add("REMOVE_ONE_TROUBLE_MARKER");
			actions.add("PLAY_ANOTHER_CARD");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("REMOVE_ONE_TROUBLE_MARKER")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "REMOVE_ONE_TROUBLE_MARKER");
				} else if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
					// performActionOfSymbol(responseChoice, cardName);
					// delCardFromChoices(actions, "PLAY_ANOTHER_CARD");
					// if(actions.contains("REMOVE_ONE_TROUBLE_MARKER"))
					// {
					// delCardFromChoices(actions, "REMOVE_ONE_TROUBLE_MARKER");
					// }
					break;
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
				delPlayerCard(cardName);
				performActionOfSymbol(responseChoice, cardName);
			}
			// responseChoice =
			// NewGame.displayComboBox("Do you want to remove troublemarker?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("REMOVE_ONE_TROUBLE_MARKER", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to play another card?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			// }
			else {
				delPlayerCard(cardName);
				refillHand();
			}
			break;
		case "THE_AGONY_AUNTS":
			actions.clear();
			actions.add("ASSASSINATION");
			actions.add("TAKE_MONEY_FROM_BANK");
			actions.add("PLACE_MINION");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("TAKE_MONEY_FROM_BANK")) {
					takeLoanFromBank(2);
					delCardFromChoices(actions, "TAKE_MONEY_FROM_BANK");
					if (actions.contains("ASSASSINATION")) {
						delCardFromChoices(actions, "ASSASSINATION");
					}
				} else if (responseChoice.equals("ASSASSINATION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "ASSASSINATION");
				} else if (responseChoice.equals("PLACE_MINION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_MINION");
					if (actions.contains("ASSASSINATION")) {
						delCardFromChoices(actions, "ASSASSINATION");
					}
					if (actions.contains("TAKE_MONEY_FROM_BANK")) {
						delCardFromChoices(actions, "TAKE_MONEY_FROM_BANK");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}

				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// responseChoice =
			// NewGame.displayComboBox("Do you want to assassinate?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("ASSASSINATION", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to take money from bank?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// takeLoanFromBank(2);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a minion?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_MINION", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "THE_DYSK":
			actions.clear();
			actions.add("PLACE_BUILDING");
			actions.add("SCROLL");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("PLACE_BUILDING")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_BUILDING");
				} else if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
					if (actions.contains("PLACE_BUILDING")) {
						delCardFromChoices(actions, "PLACE_BUILDING");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("SCROLL"))
			// {
			// performActionOfSymbol(responseChoice, cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a building?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_BUILDING", "THE_DYSK");
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "THE_DUCKMAN":
			actions.clear();
			actions.add("SCROLL");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				}

				else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}
			delPlayerCard(cardName);
			refillHand();
			break;
		case "DRUMKNOTT":
			actions.clear();
			actions.add("SCROLL");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				}

				else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}
			delPlayerCard(cardName);
			refillHand();
			break;
		case "CMOT_DIBBLER":
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLAY_ANOTHER_CARD");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
					// performActionOfSymbol(responseChoice, cardName);
					// delCardFromChoices(actions, "PLAY_ANOTHER_CARD");
					// if(actions.contains("SCROLL"))
					// {
					// delCardFromChoices(actions, "SCROLL");
					// }
					break;
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
				delPlayerCard(cardName);
				performActionOfSymbol(responseChoice, cardName);
			}
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to play another card?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			// }
			else {
				delPlayerCard(cardName);
				refillHand();
			}
			break;
		case "DR_CRUCES":
			actions.clear();
			actions.add("ASSASSINATION");
			actions.add("TAKE_MONEY_FROM_BANK");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("TAKE_MONEY_FROM_BANK")) {
					takeLoanFromBank(3);
					delCardFromChoices(actions, "TAKE_MONEY_FROM_BANK");
					if (actions.contains("ASSASSINATION")) {
						delCardFromChoices(actions, "ASSASSINATION");
					}
				} else if (responseChoice.equals("ASSASSINATION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "ASSASSINATION");
				}

				else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("TAKE_MONEY_FROM_BANK"))
			// {
			// takeLoanFromBank(3);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to assassinate?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("ASSASSINATION", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to take money from bank?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// takeLoanFromBank(3);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "CAPTAIN_CARROT":
			actions.clear();
			actions.add("PLACE_MINION");
			actions.add("REMOVE_ONE_TROUBLE_MARKER");
			actions.add("TAKE_MONEY_FROM_BANK");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("REMOVE_ONE_TROUBLE_MARKER")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "REMOVE_ONE_TROUBLE_MARKER");
					if (actions.contains("PLACE_MINION")) {
						delCardFromChoices(actions, "PLACE_MINION");
					}
				} else if (responseChoice.equals("PLACE_MINION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_MINION");
				} else if (responseChoice.equals("TAKE_MONEY_FROM_BANK")) {
					takeLoanFromBank(1);
					delCardFromChoices(actions, "TAKE_MONEY_FROM_BANK");
					if (actions.contains("PLACE_MINION")) {
						delCardFromChoices(actions, "PLACE_MINION");
					}
					if (actions.contains("REMOVE_ONE_TROUBLE_MARKER")) {
						delCardFromChoices(actions, "REMOVE_ONE_TROUBLE_MARKER");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("TAKE_MONEY_FROM_BANK"))
			// {
			// takeLoanFromBank(1);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a minion?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_MINION", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to remove troublemarker?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("REMOVE_ONE_TROUBLE_MARKER", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to take money from bank?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// takeLoanFromBank(1);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "MRS_CAKE":
			actions.clear();
			actions.add("SCROLL");
			actions.add("TAKE_MONEY_FROM_BANK");
			actions.add("PLACE_BUILDING");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("TAKE_MONEY_FROM_BANK")) {
					takeLoanFromBank(2);
					delCardFromChoices(actions, "TAKE_MONEY_FROM_BANK");
					if (actions.contains("SCROLL")) {
						delCardFromChoices(actions, "SCROLL");
					}
				} else if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLACE_BUILDING")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_BUILDING");
					if (actions.contains("SCROLL")) {
						delCardFromChoices(actions, "SCROLL");
					}
					if (actions.contains("TAKE_MONEY_FROM_BANK")) {
						delCardFromChoices(actions, "TAKE_MONEY_FROM_BANK");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("PLACE_BUILDING"))
			// {
			// performActionOfSymbol(responseChoice, cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to take money from bank?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// takeLoanFromBank(2);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a building?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_BUILDING", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "GROAT":
			actions.clear();
			actions.add("PLACE_MINION");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("PLACE_MINION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_MINION");
				}

				else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}
			delPlayerCard(cardName);
			refillHand();
			break;
		case "GIMLETS_DWARF_DELICATESSEN":
			actions.clear();
			actions.add("TAKE_MONEY_FROM_BANK");
			actions.add("PLACE_MINION");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("PLACE_MINION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_MINION");
					if (actions.contains("TAKE_MONEY_FROM_BANK")) {
						delCardFromChoices(actions, "TAKE_MONEY_FROM_BANK");
					}
				} else if (responseChoice.equals("TAKE_MONEY_FROM_BANK")) {
					takeLoanFromBank(3);
					delCardFromChoices(actions, "TAKE_MONEY_FROM_BANK");
				}

				else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("PLACE_MINION"))
			// {
			// performActionOfSymbol(responseChoice, cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to take money from bank?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// takeLoanFromBank(3);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a minion?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_MINION", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "GASPODE":
			//performActionOfSymbol("INTERRUPT", cardName);
			NewGame.showErrorDialog("Cannot be played!!!");
			NewGame.playAnotherCard = 1;
			NewGame.createPlayerFrame();
			//delPlayerCard(cardName);
			//refillHand();
			break;
		case "FRESH_START_CLUB":
			NewGame.showErrorDialog("Cannot be played!!!");
			NewGame.playAnotherCard = 1;
			NewGame.createPlayerFrame();
			//performActionOfSymbol("INTERRUPT", cardName);
			//delPlayerCard(cardName);
			//refillHand();
			break;
		case "FOUL_OLE_RON":
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLAY_ANOTHER_CARD");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
					// performActionOfSymbol(responseChoice, cardName);
					// delCardFromChoices(actions, "PLAY_ANOTHER_CARD");
					// if(actions.contains("SCROLL"))
					// {
					// delCardFromChoices(actions, "SCROLL");
					// }
					break;
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
				delPlayerCard(cardName);
				performActionOfSymbol(responseChoice, cardName);
			}
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to play another card?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			// }
			else {
				delPlayerCard(cardName);
				refillHand();
			}
			break;
		case "THE_FOOLS_GUILD":
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLACE_MINION");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLACE_MINION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_MINION");
					if (actions.contains("SCROLL")) {
						delCardFromChoices(actions, "SCROLL");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("PLACE_MINION"))
			// {
			// performActionOfSymbol(responseChoice, cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a minion?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_MINION", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "THE_FIRE_BRIGADE":
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLAY_ANOTHER_CARD");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
					// performActionOfSymbol(responseChoice, cardName);
					// delCardFromChoices(actions, "PLAY_ANOTHER_CARD");
					// if(actions.contains("SCROLL"))
					// {
					// delCardFromChoices(actions, "SCROLL");
					// }
					break;
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
				delPlayerCard(cardName);
				performActionOfSymbol(responseChoice, cardName);
			}
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to play another card?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			// }
			else {
				delPlayerCard(cardName);
				refillHand();
			}
			break;
		case "INIGO_SKIMMER":
			actions.clear();
			actions.add("ASSASSINATION");
			actions.add("TAKE_MONEY_FROM_BANK");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("TAKE_MONEY_FROM_BANK")) {
					takeLoanFromBank(2);
					delCardFromChoices(actions, "TAKE_MONEY_FROM_BANK");
					if (actions.contains("ASSASSINATION")) {
						delCardFromChoices(actions, "ASSASSINATION");
					}
				} else if (responseChoice.equals("ASSASSINATION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "ASSASSINATION");
				}

				else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("TAKE_MONEY_FROM_BANK"))
			// {
			// takeLoanFromBank(3);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to assassinate?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("ASSASSINATION", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to take money from bank?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// takeLoanFromBank(2);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "HISTORY_MONKS":
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLACE_MINION");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLACE_MINION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_MINION");
					if (actions.contains("SCROLL")) {
						delCardFromChoices(actions, "SCROLL");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("PLACE_MINION"))
			// {
			// performActionOfSymbol(responseChoice, cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a minion?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_MINION", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "HEX":
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLACE_BUILDING");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLACE_BUILDING")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_BUILDING");
					if (actions.contains("SCROLL")) {
						delCardFromChoices(actions, "SCROLL");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}
			//
			// if(responseChoice.equals("PLACE_BUILDING"))
			// {
			// performActionOfSymbol(responseChoice, cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a building?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_BUILDING", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "HERE_N_NOW":
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLAY_ANOTHER_CARD");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
					// performActionOfSymbol(responseChoice, cardName);
					// delCardFromChoices(actions, "PLAY_ANOTHER_CARD");
					// if(actions.contains("SCROLL"))
					// {
					// delCardFromChoices(actions, "SCROLL");
					// }
					break;
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
				delPlayerCard(cardName);
				performActionOfSymbol(responseChoice, cardName);
			}
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to play another card?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			// }
			else {
				delPlayerCard(cardName);
				refillHand();
			}
			break;
		case "HARRY_KING":
			actions.clear();
			actions.add("PLACE_MINION");
			actions.add("SCROLL");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("PLACE_MINION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_MINION");
				} else if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
					if (actions.contains("PLACE_MINION")) {
						delCardFromChoices(actions, "PLACE_MINION");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}
			// if(responseChoice.equals("SCROLL"))
			// {
			// performActionOfSymbol(responseChoice, cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a minion?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_MINION", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "HARGAS_HOUSE_OF_RIBS":
			actions.clear();
			actions.add("TAKE_MONEY_FROM_BANK");
			actions.add("PLACE_MINION");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("TAKE_MONEY_FROM_BANK")) {
					takeLoanFromBank(3);
					delCardFromChoices(actions, "TAKE_MONEY_FROM_BANK");
				} else if (responseChoice.equals("PLACE_MINION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_MINION");
					if (actions.contains("TAKE_MONEY_FROM_BANK")) {
						delCardFromChoices(actions, "TAKE_MONEY_FROM_BANK");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("PLACE_MINION"))
			// {
			// performActionOfSymbol(responseChoice, cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to take money from bank?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// takeLoanFromBank(3);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a minion?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_MINION", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "MR_GRYLE":
			actions.clear();
			actions.add("ASSASSINATION");
			actions.add("TAKE_MONEY_FROM_BANK");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("TAKE_MONEY_FROM_BANK")) {
					takeLoanFromBank(1);
					delCardFromChoices(actions, "TAKE_MONEY_FROM_BANK");
					if (actions.contains("ASSASSINATION")) {
						delCardFromChoices(actions, "ASSASSINATION");
					}
				} else if (responseChoice.equals("ASSASSINATION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "ASSASSINATION");
				}

				else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("TAKE_MONEY_FROM_BANK"))
			// {
			// takeLoanFromBank(1);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to assassinate?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("ASSASSINATION", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to take money from bank?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// takeLoanFromBank(1);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "THE_PEELED_NUTS":
			// no action specified
			delPlayerCard(cardName);
			refillHand();
			break;
		case "THE_OPERA_HOUSE":
			actions.clear();
			actions.add("PLACE_BUILDING");
			actions.add("SCROLL");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("PLACE_BUILDING")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_BUILDING");
				} else if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
					if (actions.contains("PLACE_BUILDING")) {
						delCardFromChoices(actions, "PLACE_BUILDING");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("SCROLL"))
			// {
			// performActionOfSymbol(responseChoice, cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a building?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_BUILDING", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "NOBBY_NOBBS":
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLAY_ANOTHER_CARD");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
					// performActionOfSymbol(responseChoice, cardName);
					// delCardFromChoices(actions, "PLAY_ANOTHER_CARD");
					// if(actions.contains("SCROLL"))
					// {
					// delCardFromChoices(actions, "SCROLL");
					// }
					break;
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
				delPlayerCard(cardName);
				performActionOfSymbol(responseChoice, cardName);
			}
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to play another card?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			// }
			else {
				delPlayerCard(cardName);
				refillHand();
			}
			break;
		case "MODO":
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLACE_MINION");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLACE_MINION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_MINION");
					if (actions.contains("SCROLL")) {
						delCardFromChoices(actions, "SCROLL");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("PLACE_MINION"))
			// {
			// performActionOfSymbol(responseChoice, cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a minion?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_MINION", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "THE_MENDED_DRUM":
			actions.clear();
			actions.add("PLACE_BUILDING");
			actions.add("TAKE_MONEY_FROM_BANK");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("TAKE_MONEY_FROM_BANK")) {
					takeLoanFromBank(2);
					delCardFromChoices(actions, "TAKE_MONEY_FROM_BANK");
					if (actions.contains("PLACE_BUILDING")) {
						delCardFromChoices(actions, "PLACE_BUILDING");
					}
				} else if (responseChoice.equals("PLACE_BUILDING")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_BUILDING");
				}

				else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("TAKE_MONEY_FROM_BANK"))
			// {
			// takeLoanFromBank(2);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a building?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_BUILDING", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to take money from bank?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// takeLoanFromBank(2);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "LIBRARIAN":
			actions.clear();
			actions.add("SCROLL");

			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				}

				else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}
			delPlayerCard(cardName);
			refillHand();
			break;

		case "LEONARD_OF_QUIRM":
			actions.clear();
			actions.add("SCROLL");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				}

				else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}
			delPlayerCard(cardName);
			refillHand();
			break;
		case "SHONKY_SHOP":
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLACE_BUILDING");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLACE_BUILDING")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_BUILDING");
					if (actions.contains("SCROLL")) {
						delCardFromChoices(actions, "SCROLL");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("PLACE_BUILDING"))
			// {
			// performActionOfSymbol(responseChoice, cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a building?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_BUILDING", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "SACHARISSA_CRIPSLOCK":
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLACE_MINION");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLACE_MINION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_MINION");
					if (actions.contains("SCROLL")) {
						delCardFromChoices(actions, "SCROLL");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("PLACE_MINION"))
			// {
			// performActionOfSymbol(responseChoice, cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a minion?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_MINION", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "ROSIE_PALM":
			actions.clear();
			actions.add("PLACE_MINION");
			actions.add("SCROLL");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("PLACE_MINION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_MINION");
				} else if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
					if (actions.contains("PLACE_MINION")) {
						delCardFromChoices(actions, "PLACE_MINION");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("SCROLL"))
			// {
			// performActionOfSymbol(responseChoice, cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a minion?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_MINION", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "RINCEWIND":
			performActionOfSymbol("RANDOM_EVENT", cardName);
			NewGame.cityAreaStatus = new HashMap<Integer, Integer>();
			for(Player playerObj: GameUtility.playerObjList)
			{
				if(playerObj.pTurn == 1)
				{
					for (int key : playerObj.H_Region.keySet()) {
						if (playerObj.H_Region.get(key).placedbuilding >= 1
								&& GameUtility.regionObjList.get(key - 1).stopBenefit == 0) {
							NewGame.cityAreaStatus.put(key, 1);			
						}  // inner if
					}  // inner for
				} // outer if
			}// outer for	
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLAY_ANOTHER_CARD");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
					// performActionOfSymbol(responseChoice, cardName);
					// delCardFromChoices(actions, "PLAY_ANOTHER_CARD");
					// if(actions.contains("SCROLL"))
					// {
					// delCardFromChoices(actions, "SCROLL");
					// }
					break;
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
				delPlayerCard(cardName);
				performActionOfSymbol(responseChoice, cardName);
			}
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to play another card?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			// }
			else {
				delPlayerCard(cardName);
				refillHand();
			}
			break;
		case "THE_ROYAL_MINT":
			actions.clear();
			actions.add("PLACE_BUILDING");
			actions.add("TAKE_MONEY_FROM_BANK");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("TAKE_MONEY_FROM_BANK")) {
					takeLoanFromBank(5);
					delCardFromChoices(actions, "TAKE_MONEY_FROM_BANK");
					if (actions.contains("PLACE_BUILDING")) {
						delCardFromChoices(actions, "PLACE_BUILDING");
					}
				} else if (responseChoice.equals("PLACE_BUILDING")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_BUILDING");
				}

				else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("TAKE_MONEY_FROM_BANK"))
			// {
			// takeLoanFromBank(5);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a building?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_BUILDING", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to take money from bank?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// takeLoanFromBank(5);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "QUEEN_MOLLY":
			actions.clear();
			actions.add("PLACE_MINION");
			actions.add("SCROLL");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("PLACE_MINION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_MINION");
				} else if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
					if (actions.contains("PLACE_MINION")) {
						delCardFromChoices(actions, "PLACE_MINION");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("SCROLL"))
			// {
			// performActionOfSymbol(responseChoice, cardName);
			// }
			// // responseChoice =
			// NewGame.displayComboBox("Do you want to place a minion?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_MINION", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "PINK_PUSSYCAT_CLUB":
			actions.clear();
			actions.add("TAKE_MONEY_FROM_BANK");
			actions.add("PLAY_ANOTHER_CARD");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("TAKE_MONEY_FROM_BANK")) {
					takeLoanFromBank(3);
					delCardFromChoices(actions, "TAKE_MONEY_FROM_BANK");
				} else if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
					// performActionOfSymbol(responseChoice, cardName);
					// delCardFromChoices(actions, "PLAY_ANOTHER_CARD");
					// if(actions.contains("TAKE_MONEY_FROM_BANK"))
					// {
					// delCardFromChoices(actions, "TAKE_MONEY_FROM_BANK");
					// }
					break;
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			if (responseChoice.equals("PLAY_ANOTHER_CARD")) {
				delPlayerCard(cardName);
				performActionOfSymbol(responseChoice, cardName);
			}
			// responseChoice =
			// NewGame.displayComboBox("Do you want to take money from bank?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// takeLoanFromBank(3);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to play another card?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			// }
			else {
				delPlayerCard(cardName);
				refillHand();
			}
			break;
		case "ZORGO_THE_RETRO_PHRENOLOGIST":
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLACE_BUILDING");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLACE_BUILDING")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_BUILDING");
					if (actions.contains("SCROLL")) {
						delCardFromChoices(actions, "SCROLL");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("PLACE_BUILDING"))
			// {
			// performActionOfSymbol(responseChoice, cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a building?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_BUILDING", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "DR_WHITEFACE":
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLACE_MINION");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLACE_MINION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_MINION");
					if (actions.contains("SCROLL")) {
						delCardFromChoices(actions, "SCROLL");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("PLACE_MINION"))
			// {
			// performActionOfSymbol(responseChoice, cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a minion?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_MINION", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "WALLACE_SONKY":
			NewGame.showErrorDialog("Cannot be played!!!");
			NewGame.createPlayerFrame();
			break;
		case "THE_SEAMSTRESSES_GUILD":
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLACE_MINION");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLACE_MINION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_MINION");
					if (actions.contains("SCROLL")) {
						delCardFromChoices(actions, "SCROLL");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("PLACE_MINION"))
			// {
			// performActionOfSymbol(responseChoice, cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a minion?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_MINION", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "MR_PIN_MR_TULIP":
			actions.clear();
			actions.add("ASSASSINATION");
			actions.add("TAKE_MONEY_FROM_BANK");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("TAKE_MONEY_FROM_BANK")) {
					takeLoanFromBank(1);
					delCardFromChoices(actions, "TAKE_MONEY_FROM_BANK");
					if (actions.contains("ASSASSINATION")) {
						delCardFromChoices(actions, "ASSASSINATION");
					}
				} else if (responseChoice.equals("ASSASSINATION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "ASSASSINATION");
				}

				else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}
			//
			// if(responseChoice.equals("TAKE_MONEY_FROM_BANK"))
			// {
			// takeLoanFromBank(1);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to assassinate?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("ASSASSINATION", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to take money from bank?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// takeLoanFromBank(1);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		case "THE_THIEVES_GUILD":
			actions.clear();
			actions.add("SCROLL");
			actions.add("PLACE_MINION");
			if (NewGame.cityAreaList.size() >= 1) {
				for (int z : NewGame.cityAreaStatus.keySet()) {
					if(NewGame.cityAreaStatus.get(z) != 0)
					{
						actions.add(CityAreaCards.getCityAreaCard(z));
					}
				}
			}
			actions.add("END_TURN");
			responseChoice = NewGame.displayComboBox(
					"Select an action that you wish to perform", actions);
			// if last action on card is not performed
			while (!(responseChoice.equals("END_TURN"))) {
				if (responseChoice.equals("SCROLL")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "SCROLL");
				} else if (responseChoice.equals("PLACE_MINION")) {
					performActionOfSymbol(responseChoice, cardName);
					delCardFromChoices(actions, "PLACE_MINION");
					if (actions.contains("SCROLL")) {
						delCardFromChoices(actions, "SCROLL");
					}
				} else {
					int value = CityAreaCards.getNumberCityArea(responseChoice);
					CityAreaCards.playCityAreaCard(value);
					delCardFromChoices(actions, responseChoice);
					NewGame.cityAreaStatus.put(value, 0);
				}
				responseChoice = NewGame.displayComboBox(
						"Select an action that you wish to perform", actions);
			}

			// if(responseChoice.equals("PLACE_MINION"))
			// {
			// performActionOfSymbol(responseChoice, cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to perform the action described in text at bottom of card??",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("SCROLL", cardName);
			// }
			// responseChoice =
			// NewGame.displayComboBox("Do you want to place a minion?",choice);
			// if(responseChoice.equals("yes"))
			// {
			// performActionOfSymbol("PLACE_MINION", cardName);
			// }
			delPlayerCard(cardName);
			refillHand();
			break;
		default:
			throw new IllegalArgumentException("Invalid player card name: "
					+ cardName);
		}
	}

	/**
	 * Function defines actions for the symbols.<br>
	 * The actions of symbols are performed here depending<br>
	 * on the type of symbol
	 * 
	 * @param symbolName
	 *            String name of symbol to perform the action
	 * @param playerCardName
	 *            String card name that was clicked by user
	 */
	public static void performActionOfSymbol(String symbolName,
			String playerCardName) {
		switch (symbolName) {
		// place minion
		case "PLACE_MINION":
			String oldRegion = "";
			String newRegion = "";
			int result;
			int oldReg,
			newReg;
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.pTurn == 1) {
					comboChoice = NewGame.displayComboBox(
							"Select region in which minion should be placed",
							regionListCombo);
					newReg = Integer.parseInt(comboChoice);
					result = playerObj.checkMinionMove(newReg);
					// user input is not correct, minion cannot be placed in
					// that area
					while (result == 0) {
						comboChoice = NewGame
								.displayComboBox(
										"Wrong Input! , Select region in which minion should be placed",
										regionListCombo);
						newReg = Integer.parseInt(comboChoice);
						result = playerObj.checkMinionMove(newReg);
					}
					// player doesn't have any minion left with him
					// so move a minion from one region to another
					if (playerObj.minionHold < 1) {
						tempArray.clear();
						tempArray.add("Yes");
						tempArray.add("No");
						comboChoice = NewGame
								.displayComboBox(
										"You are not left with minions, Do you want to move from another region",
										tempArray);
						if (comboChoice.equals("Yes")) {
							tempArray.clear();
							for (int key : playerObj.H_Region.keySet()) {
								if (playerObj.H_Region.get(key).placedMinion > 0) {
									tempArray.add(String.valueOf(key));
								}
							}
							oldRegion = NewGame
									.displayComboBox(
											"Select region in which minion should be moved",
											tempArray);
							oldReg = Integer.parseInt(oldRegion);
							GameUtility.removeMinion(playerObj.color, oldReg);
						} else {
							break;
						}
					}
					GameUtility.placeMinion(playerObj.color, newReg);
					break;
				}
			}
			break;
		// place building
		case "PLACE_BUILDING":
			oldRegion = "";
			newRegion = "";
			boolean isContinue = true;
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.pTurn == 1) {

					comboChoice = NewGame.displayComboBox(
							"Select region in which building should be placed",
							regionListCombo);
					newReg = Integer.parseInt(comboChoice);
					result = GameUtility.regionObjList.get(newReg - 1)
							.checkBuildingMove(playerObj.color);
					// user input is not correct, minion cannot be placed in
					// that area
					while (result == 0) {
						List<String> regionListComboTemp = Arrays.asList("1", "2", "3",
								"4", "5", "6", "7", "8", "9", "10", "11", "12", "Exit");
						comboChoice = NewGame
								.displayComboBox(
										"Wrong Input! , Select region in which building should be placed",
										regionListComboTemp);
						if(comboChoice.equals("Exit"))
						{
							isContinue = false;
							break;
						}
						newReg = Integer.parseInt(comboChoice);
						result = GameUtility.regionObjList.get(newReg - 1)
								.checkBuildingMove(playerObj.color);
					}
					if(!isContinue)
					{
						break;
					}

					// player doesn't have any building left with him
					// so move a building from one region to another
					if (playerObj.buildingHold < 1) {
						tempArray.clear();
						tempArray.add("Yes");
						tempArray.add("No");
						comboChoice = NewGame
								.displayComboBox(
										"You are not left with buildings, Do you want to move from another region",
										tempArray);
						if (comboChoice.equals("Yes")) {
							tempArray.clear();
							for (int key : playerObj.H_Region.keySet()) {
								if (playerObj.H_Region.get(key).placedbuilding > 0) {
									tempArray.add(String.valueOf(key));
								}
							}
							oldRegion = NewGame
									.displayComboBox(
											"Select region in which building should be moved",
											tempArray);
							oldReg = Integer.parseInt(oldRegion);
							GameUtility.removeBuilding(playerObj.color, oldReg);
						} else {
							break;
						}
					}
					GameUtility.placeBuilding(playerObj.color, newReg);
					break;
				}
			}
			break;
		// assassination
		case "ASSASSINATION":
			List<String> options = new ArrayList<String>();
			options.add("minion");
			options.add("demon");
			options.add("troll");
			result = 0;
			int interruptStatus = 0;
			String choice,
			playerChoice;
			// for (Player playerObj : GameUtility.playerObjList) {
			// if (playerObj.pTurn == 1) {
			choice = NewGame.displayComboBox(
					"Select the option to be assassinated: ", options);
			// oldRegion = NewGame
			// .displayBox("Select region number from where (Minion/Demon/Troll) to be removed:");
			// oldRegion =
			// NewGame.displayComboBox("Select area from which "+choice+" should be removed",
			// options);
			// oldReg = Integer.parseInt(oldRegion);

			while (result == 0) {
				switch (choice) {
				case "minion":
					List<String> players = getColorsOfPlayers();
					List<String> regionList = new ArrayList<String>();
					for (String str : players) {
						System.out.println("Players:  " + str);
					}
					// color of selected player
					playerChoice = NewGame.displayComboBox("Select one player",
							players);
					for (Player playerObj : GameUtility.playerObjList) {
						// get the list of regions in whichplayer's minions are
						// already there
						if (playerObj.color.equals(playerChoice)) {
							for (int i : playerObj.H_Region.keySet()) {
								if (playerObj.H_Region.get(i).placedMinion > 0 && GameUtility.regionObjList.get(i-1).rTroubleMarker == 1) {
									String str = String.valueOf(i);
									regionList.add(str);
								}
							}
						}
					}
					regionList.add("Exit");
					oldRegion = NewGame
							.displayComboBox(
									"Select region from which minion should be assassinated",
									regionList);
					if(oldRegion.equals("Exit"))
					{
						result = 1;
						break;
					}
					oldReg = Integer.parseInt(oldRegion);
					for (Player playerObj1 : GameUtility.playerObjList) {
						if (playerObj1.color.equals(playerChoice)) {
							interruptStatus = checkInterruptCard(oldReg,
									playerChoice);

							if (interruptStatus == 0) {
								GameUtility.removeMinion(playerObj1.color,
										oldReg);
							}
							result = 1;
						}
					}
					break;
				case "demon":
					tempArray.clear();
					for (Region regionObj : GameUtility.regionObjList) {
						// region contains trouble markers
						if (regionObj.rDemon > 0) {
							String str = String.valueOf(regionObj.rNumber);
							tempArray.add(str);
						}
					}
					comboChoice = NewGame
							.displayComboBox(
									"Select a region to remove demon from ",
									tempArray);
					int tRegion = Integer.parseInt(comboChoice);
					GameUtility.regionObjList.get(tRegion - 1).removeDemon();
					result = 1;
					break;
				case "troll":
					tempArray.clear();
					for (Region regionObj : GameUtility.regionObjList) {
						// region contains trouble markers
						if (regionObj.rTroll > 0) {
							String str = String.valueOf(regionObj.rNumber);
							tempArray.add(str);
						}
					}
					comboChoice = NewGame
							.displayComboBox(
									"Select a region to remove troll from ",
									tempArray);
					tRegion = Integer.parseInt(comboChoice);
					GameUtility.regionObjList.get(tRegion - 1).removeTroll();
					result = 1;
					// GameUtility.regionObjList.get(oldReg-1).removeTroll();
					// result = 1;
					break;
				default:
					result = 0;
					break;
				}
			}
			// }
			// }
			break;
		// remove one trouble marker
		case "REMOVE_ONE_TROUBLE_MARKER":
			tempArray.clear();
			for (Region regionObj : GameUtility.regionObjList) {
				// region contains trouble markers
				if (regionObj.rTroubleMarker > 0) {
					String str = String.valueOf(regionObj.rNumber);
					tempArray.add(str);
				}
			}

			comboChoice = NewGame
					.displayComboBox(
							"Select a region to remove trouble marker from ",
							tempArray);
			int tRegion = Integer.parseInt(comboChoice);
			GameUtility.regionObjList.get(tRegion - 1).removeTroubleMarker();
			/*
			 * while(result == 0) { NewGame.showErrorDialog("Wrong Input!");
			 * tempRegion = NewGame .displayBox(
			 * "Select region from which trouble marker should be removed:");
			 * tRegion = Integer.parseInt(tempRegion); result =
			 * GameUtility.regionObjList.get(tRegion-1).removeTroubleMarker(); }
			 */
			break;
		case "TAKE_MONEY":
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.pTurn == 1) {
					GameEngine.BankHold = GameEngine.BankHold - 2;
					playerObj.cashHold = playerObj.cashHold + 2;
				}
			}
			break;
		// scroll
		case "SCROLL":
			// perform action specified at bottom of card
			System.out.println("scroll called");
			performActionInText(playerCardName);
			break;
		// random event
		case "RANDOM_EVENT":
			// randomly choose an event from random event cards
			String eventChoice = RandomEventCards.getRandomEventCard();
			// cases for random event cards that calls respective methods
			switch (eventChoice) {			
			case "THE_DRAGON":
				RandomEventCards dragonEvent = new DragonEventCard();
				dragonEvent.executeRandomEvent();
				break;
			case "FLOOD":
				System.out.println("Flood event occurred !!!!!!!");
				RandomEventCards floodEvent = new FloodEventCard();
				floodEvent.executeRandomEvent();
				break;
			case "FIRE":
				RandomEventCards fireEvent = new FireEventCard();
				fireEvent.executeRandomEvent();
				break;
			case "FOG":
				RandomEventCards fogEvent = new FogEventCard();
				fogEvent.executeRandomEvent();
				break;
			case "RIOTS":
				RandomEventCards riotsEvent = new RiotEventCard();
				riotsEvent.executeRandomEvent();
				break;
			case "EXPLOSION":
				RandomEventCards explosionEvent = new ExplosionEventCard();
				NewGame.showErrorDialog("Explosion Event occured !");
				explosionEvent.executeRandomEvent();
				break;
			case "MYSTERIOUS_MURDERS":
				System.out.println("MYSTERIOUS_MURDERS event occurred !!!!!!!");
				RandomEventCards mMurdersEvent = new mMurdersEventCard();
				mMurdersEvent.executeRandomEvent();
				break;
			case "DEMONS_FROM_THE_DUNGEONS_DIMENSIONS":
				System.out
						.println("DEMONS_FROM_THE_DUNGEONS_DIMENSIONS event occurred !!!!!!!");
				RandomEventCards dungeonEvent = new DungeonEventCard();
				dungeonEvent.executeRandomEvent();
				break;
			case "SUBSIDENCE":
				RandomEventCards subsidenceEvent = new SubsidenceEventCard();
				subsidenceEvent.executeRandomEvent();
				break;
			case "BLOODY_STUPID_JOHNSON":
				System.out
						.println("BLOODY_STUPID_JOHNSON event occurred !!!!!!!");
				RandomEventCards bsJohnsonEventCard = new bsJohnsonEventCard();
				bsJohnsonEventCard.executeRandomEvent();
				break;
			case "TROLLS":
				RandomEventCards trollsEvent = new TrollsEventCard();
				trollsEvent.executeRandomEvent();
				break;
			case "EARTHQUAKE":
				RandomEventCards earthquakeEvent = new ExplosionEventCard();
				NewGame.showErrorDialog("Earthquake Event occured !");
				earthquakeEvent.executeRandomEvent();
				earthquakeEvent.executeRandomEvent();
				break;
			}
			break;
		// play another card
		case "PLAY_ANOTHER_CARD":
			// delPlayerCard(playerCardName);

			List<String> cards = new ArrayList<String>();
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.pTurn == 1) {
					cards = playerObj.pCards.get("Green");
				}
			}
			// for(String a: cards)
			// {
			// System.out.println("In player Cards:   "+ a);
			// }
			// // player has cards to play
			// if(cards.size() > 1)
			// {
			NewGame.playAnotherCard = 1;
			NewGame.createPlayerFrame();
			// }

			break;
		// interrupt
		case "INTERRUPT":
			System.out.println("interrupt");
			break;
		default:
			throw new IllegalArgumentException("Invalid symbol name: "
					+ symbolName);
		}
	}

	/**
	 * method performs the action specified on the player card at the bottom
	 * when the symbol scroll appears
	 * 
	 * @param String
	 *            name of player card
	 */
	public static void performActionInText(String playerCardName) {
		switch (playerCardName) {
		case "MR_BOGGIS":
			// take $2 from every other player
			takeMoneyFromEveryPlayer(2);
			break;

		case "MR_BENT":
			// take loan of $10 from bank
			takeLoanFromBank(10);
			// at the end of game pay $12 to bank or lose 15 points
			break;

		case "THE_BEGGARS_GUILD":
			List<String> players = getColorsOfPlayers();
			// color of selected player
			String playerColor1 = NewGame.displayComboBox("Select one player",
					players);
			int playerNumber = 0;
			// display cards of selected player from which he selects two cards
			// to be given
			List<String> cards = new ArrayList<String>();
			cards = getPlayerCards(playerColor1);
			// call method to display all the cards
			String pCard1 = NewGame.displayComboBox(
					"Give card of your choice to current player", cards);
			// get the card from the player
			takePlayerCard(playerColor1, pCard1);
			// for second card
			List<String> cards2 = new ArrayList<String>();
			cards = getPlayerCards(playerColor1);
			// call method to display all the cards
			String pCard2 = NewGame.displayComboBox(
					"Give card of your choice to current player", cards);
			takePlayerCard(playerColor1, pCard2);
			break;

		case "THE_BANK_OF_ANKH_MORPORK":
			// take loan of $10 from bank
			takeLoanFromBank(10);
			break;

		case "THE_ANKH_MORPORK_SUNSHINE_DRAGON_SANCTUARY":
			int count1 = 0;
			// each player card should give you either $1 or one of their cards
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.pTurn == 0) {
					if (playerObj.cashHold >= 1) {
						// give $1 to player i turn
						takeMoneyFromOtherPlayers(1, playerObj.pNumber);
						count1 = count1 + 1;
					} else {
						List<String> cards3 = new ArrayList<String>();
						cards3 = getPlayerCards(playerObj.color);
						String pCard3 = NewGame
								.displayComboBox(
										"Doesn't have money to give, so give one of lpayer cards",
										cards3);
						takePlayerCard(playerObj.color, pCard3);
					}
				}
			}
			for (Player playerObj : GameUtility.playerObjList) {
				// current player
				if (playerObj.pTurn == 1) {
					playerObj.cashHold = playerObj.cashHold + count1;
				}
			}
			break;

		case "THE_DYSK":
			// earn $1 for each minion in Isle Of Gods
			int number = GameEngine.objIGods.rMinionNum;
			for (Player playerObj : GameUtility.playerObjList) {
				// current player
				if (playerObj.pTurn == 1) {
					playerObj.cashHold = playerObj.cashHold + number;
					GameEngine.BankHold = GameEngine.BankHold - number;
				}
			}
			break;

		case "THE_DUCKMAN":
			// move a minion belonging to another player from one area to
			// adjacent area
			// select another player
			List<String> players5 = getColorsOfPlayers();
			// color of selected player
			String playerColor12 = NewGame.displayComboBox(
					"Select one player whose minion should be moved", players5);
			// call methods to remove and place minion
			moveOtherPlayerMinion(playerColor12);
			break;

		case "DRUMKNOTT":
			// Play any two other cards
			delPlayerCard(playerCardName);
			List<String> pcards = new ArrayList<String>();
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.pTurn == 1) {
					pcards = playerObj.pCards.get("Green");
				}
			}
			// player has cards to play
			if (pcards.size() > 1) {
				NewGame.playAnotherCard = 1;
				NewGame.createPlayerFrame();
			}
			break;

		case "CMOT_DIBBLER":
			int result = rollDie();
			NewGame.showErrorDialog("Result after rolling die: "
					+ String.valueOf(result));
			// on roll of 7 or more take $4 from bank
			if (result >= 7) {
				takeLoanFromBank(4);
			}
			// on roll of 1, pay $2 to bank or remove one of minions from board
			else if (result == 1) {
				for (Player playerObj : GameUtility.playerObjList) {
					// current player
					if ((playerObj.pTurn == 1) && (playerObj.cashHold >= 2)) {
						payToBank(2);
					} else if ((playerObj.pTurn == 1)
							&& (playerObj.cashHold < 2)) {
						// remove one of your minions from board
						String area3 = NewGame
								.displayBox("Select area from which minion should be moved");
						int areaBefore1 = Integer.parseInt(area3);
						GameUtility.regionObjList.get(areaBefore1 - 1)
								.removeMinion(playerObj.color);
						playerObj.removeMinion(areaBefore1);
					}
				}
			}
			break;

		case "MRS_CAKE":
			// look at all but one of unused personality cards
			List<PersonalityCards.getPersonalityCard> listTemp = PersonalityCards.PersonalityList;
			String personalityTemp = listTemp.get(0).toString();
			NewGame.displayUnusedPersonalityCards(personalityTemp);
			break;
		case "GASPODE":
			// stop a player from removing your minion area
			break;

		case "FRESH_START_CLUB":
			// if you have minion removed, place it in different area
			break;

		case "FOUL_OLE_RON":
			// / move a minion belonging to another player from one area to
			// adjacent area
			// select another player
			List<String> players6 = getColorsOfPlayers();
			// color of selected player
			String playerColor13 = NewGame.displayComboBox(
					"Select one player whose minion is to be moved", players6);
			// call methods to remove and place minion
			moveOtherPlayerMinion(playerColor13);
			break;
		case "THE_FOOLS_GUILD":
			// select another player
			// select another player
			List<String> players7 = getColorsOfPlayers();
			// color of selected player
			String playerColor14 = NewGame.displayComboBox("Select one player",
					players7);
			int count2 = 0;
			// another player gives you $5
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.color.equals(playerColor14)) {
					if (playerObj.cashHold >= 5) {
						playerObj.cashHold = playerObj.cashHold - 5;
						count2 = count2 + 5;
					}
					// this card now counts towards another player's player
					// cards
					else {
						List<String> greenList = playerObj.pCards.get("Green");
						for (int i = 0; i < greenList.size(); i++) {
							greenList.add(playerCardName);
						}
						// remove all green colored cards from hash map
						playerObj.pCards.remove("Green");
						// add new list to hash map
						playerObj.pCards.put("Green", greenList);
					}
				}
			}

			// add cash to current player's account
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.pTurn == 1) {
					playerObj.cashHold = playerObj.cashHold + count2;
				}
			}
			break;

		case "THE_FIRE_BRIGADE":
			// select another player
			// select another player
			List<String> players8 = getColorsOfPlayers();
			// color of selected player
			String playerColor15 = NewGame.displayComboBox("Select one player",
					players8);
			int count3 = 0;
			// another player gives you $5
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.color.equals(playerColor15)) {
					if (playerObj.cashHold >= 5) {
						playerObj.cashHold = playerObj.cashHold - 5;
						count3 = count3 + 5;
					}
					// current player can remove another player's building from
					// board
					else {
						// ask for region info
						String area6 = NewGame
								.displayBox("Select area from which building should be removed");
						int value = Integer.valueOf(area6);
						// method to remove building
						playerObj.removeBuilding(value);
						GameUtility.regionObjList.get(value - 1)
								.removeBuilding(playerObj.color);
					}
				}
			}

			// add cash to current player's account
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.pTurn == 1) {
					playerObj.cashHold = playerObj.cashHold + count3;
				}
			}
			break;

		case "HISTORY_MONKS":
			// shuffle discard pile
			Collections.shuffle(GameEngine.discardCards);
			List<String> list1 = new ArrayList<String>();
			// draw four cards randomly
			if (!GameEngine.discardCards.isEmpty()) {
				for (int i = 0; i < 4; i++) {
					String toBeAdded = GameEngine.discardCards.remove(0);
					list1.add(toBeAdded);

				}
				for (Player playerObj : GameUtility.playerObjList) {
					if (playerObj.pTurn == 1) {
						List<String> greenList = playerObj.pCards.get("Green");
						for (String a : greenList) {
							list1.add(a);
						}
						playerObj.pCards.put("Green", list1);
					}
				}
			}
			break;

		case "HEX":
			// take three cards from draw deck
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.pTurn == 1) {
					List<String> list = playerObj.pCards.get("Green");
					for (int i = 0; i < 3; i++) {
						Pair pair = getPlayerCard();
						String color_temp = pair.getCardColor();
						String cardNo_temp = pair.getCard();
						list.add(cardNo_temp);
					}
					playerObj.pCards.put("Green", list);
				}
			}
			break;

		case "HERE_N_NOW":
			// roll the die
			int result1 = rollDie();
			NewGame.showErrorDialog("Result after rolling die: "
					+ String.valueOf(result1));
			// on roll of 7 or more take $ from any player

			if (result1 >= 7) {
				// select another player
				List<String> players9 = getColorsOfPlayers();
				// color of selected player
				String playerColor18 = NewGame.displayComboBox(
						"Select one player", players9);
				int count4 = 0;
				// another player gives you $3
				for (Player playerObj : GameUtility.playerObjList) {
					if (playerObj.color.equals(playerColor18)) {
						if (playerObj.cashHold >= 3) {
							playerObj.cashHold = playerObj.cashHold - 3;
							count4 = count4 + 3;
						}
					}
				}

				// add cash to current player's account
				for (Player playerObj : GameUtility.playerObjList) {
					if (playerObj.pTurn == 1) {
						playerObj.cashHold = playerObj.cashHold + count4;
					}
				}
			}
			// on roll of 1, pay $2 to bank or remove one of minions from board
			else if (result1 == 1) {
				for (Player playerObj : GameUtility.playerObjList) {
					if (playerObj.pTurn == 1) {
						String value1 = NewGame
								.displayBox("Enter the area from which minion should be removed");
						int valueInt = Integer.valueOf(value1);
						// remove your minion from one of areas
						GameUtility.regionObjList.get(valueInt - 1)
								.removeMinion(playerObj.color);
						playerObj.removeMinion(valueInt);
					}
				}
			}
			break;

		case "HARRY_KING":
			ArrayList<String> choice = new ArrayList<String>();
			choice.add("yes");
			choice.add("no");
			String responseChoice = "yes";
			String pColor = "";
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.pTurn == 1) {
					pColor = playerObj.color;
				}
			}
			while (responseChoice.equals("yes")) {
				responseChoice = NewGame.displayComboBox(
						"Do you want to discard card?", choice);
				if (responseChoice.equals("yes")) {
					// discard card and take $2 from bank
					List<String> cards3 = new ArrayList<String>();
					cards3 = getPlayerCards(pColor);
					// remove the card being played
					// for(String a:cards3)
					// {
					// if(a.equals(playerCardName))
					// {

					// remove the card being played
					cards3.remove(playerCardName);
					// }
					// }
					String pCard3 = NewGame.displayComboBox(
							"Select a card that you want to discard", cards3);
					delPlayerCard(pCard3);
					takeLoanFromBank(2);
				} else {
					break;
				}
			}

			break;

		case "THE_OPERA_HOUSE":
			// earn $1 for each minion in Isle Of Gods
			int number1 = GameEngine.objIGods.rMinionNum;
			for (Player playerObj : GameUtility.playerObjList) {
				// current player
				if (playerObj.pTurn == 1) {
					playerObj.cashHold = playerObj.cashHold + number1;
					GameEngine.BankHold = GameEngine.BankHold - number1;
				}
			}
			break;

		case "NOBBY_NOBBS":
			// take $3 from a player of your choice
			// select another player
			List<String> players9 = getColorsOfPlayers();
			// color of selected player
			String playerColor19 = NewGame.displayComboBox("Select one player",
					players9);
			int count5 = 0;
			// another player gives you $5
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.color.equals(playerColor19)) {
					if (playerObj.cashHold >= 3) {
						playerObj.cashHold = playerObj.cashHold - 3;
						count5 = count5 + 3;
					}
				}
			}
			// add cash to current player's account
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.pTurn == 1) {
					playerObj.cashHold = playerObj.cashHold + count5;
				}
			}
			break;

		case "MODO":
			// discard one card
			String pColor2 = "";
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.pTurn == 1) {
					pColor2 = playerObj.color;
				}
			}
			List<String> cards4 = new ArrayList<String>();
			cards4 = getPlayerCards(pColor2);
			// remove the card being played
			cards4.remove(playerCardName);
			String pCard4 = NewGame.displayComboBox(
					"Select one card that you want to discard", cards4);
			delPlayerCard(pCard4);
			break;

		case "LIBRARIAN":
			// take 4 cards from draw deck
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.pTurn == 1) {
					List<String> list = playerObj.pCards.get("Green");
					for (int i = 0; i < 4; i++) {
						Pair pair = getPlayerCard();
						String color_temp = pair.getCardColor();
						String cardNo_temp = pair.getCard();
						list.add(cardNo_temp);
					}
					playerObj.pCards.put("Green", list);
				}
			}
			break;

		case "LEONARD_OF_QUIRM":
			// take 4 cards from draw deck
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.pTurn == 1) {
					List<String> list = playerObj.pCards.get("Green");
					for (int i = 0; i < 4; i++) {
						Pair pair = getPlayerCard();
						String color_temp = pair.getCardColor();
						String cardNo_temp = pair.getCard();
						list.add(cardNo_temp);
					}
					playerObj.pCards.put("Green", list);
				}
			}
			break;

		case "SHONKY_SHOP":
			// discard card and take $1 from bank
			ArrayList<String> choice2 = new ArrayList<String>();
			choice2.add("yes");
			choice2.add("no");
			String responseChoice2 = "yes";
			String pColor3 = "";
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.pTurn == 1) {
					pColor3 = playerObj.color;
				}
			}
			while (responseChoice2.equals("yes")) {
				responseChoice2 = NewGame.displayComboBox(
						"Do you want to discard card?", choice2);
				if (responseChoice2.equals("yes")) {
					// discard card and take $2 from bank
					List<String> cards3 = new ArrayList<String>();
					cards3 = getPlayerCards(pColor3);

					// remove the card being played
					cards3.remove(playerCardName);

					String pCard3 = NewGame.displayComboBox(
							"Select card that you want to discard", cards3);
					delPlayerCard(pCard3);
					takeLoanFromBank(1);
				} else {
					break;
				}
			}
			break;

		case "SACHARISSA_CRIPSLOCK":
			// Earn $1 for each trouble marker on board
			takeLoanFromBank(12 - (GameEngine.TMarkerHold));
			break;

		case "ROSIE_PALM":
			// select another player
			List<String> players2 = getColorsOfPlayers();
			// color of selected player
			String playerColor8 = NewGame.displayComboBox("Select one player",
					players2);
			int count6 = 0;
			int flag = 0; // denotes if player can pay you $2 or not
			// another player gives you $2
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.color.equals(playerColor8)) {
					if (playerObj.cashHold >= 2) {
						playerObj.cashHold = playerObj.cashHold - 2;
						count6 = count6 + 2;
						flag = 1;
					}
				}
			}

			String card = null;
			// player can pay for card
			if (flag == 1) {
				for (Player playerObj : GameUtility.playerObjList) {
					if (playerObj.pTurn == 1) {
						// discard card and take $2 from bank
						List<String> cards3 = new ArrayList<String>();
						cards3 = getPlayerCards(playerObj.color);

						// remove the card being played
						cards3.remove(playerCardName);

						String pCard3 = NewGame
								.displayComboBox(
										"Select card that you want to give to another player",
										cards3);
						// give them a player card
						givePlayerCard(playerColor8, pCard3);
						playerObj.cashHold = playerObj.cashHold + count6;
					}
				}
			} else {
				NewGame.displayBox("The other player doesn't have enough money to give you");
			}
			break;

		case "RINCEWIND":
			int oldReg,	newReg;
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.pTurn == 1) {					
					tempArray.clear();
					for (int key : playerObj.H_Region.keySet()) {
						if (playerObj.H_Region.get(key).placedMinion > 0 && GameUtility.regionObjList.get(key-1).rTroubleMarker == 1) {
							tempArray.add(String.valueOf(key));
						}
					}	
					tempArray.add("Exit");
					comboChoice = NewGame
							.displayComboBox(
									"Select region from which minion should be moved",
									tempArray);
					if(comboChoice.equals("Exit"))
					{
						break;
					}
					else
					{
						oldReg = Integer.parseInt(comboChoice);
						tempArray.clear();
						for (int key : GameUtility.regionObjList.get(oldReg-1).listForNeighbours) {
							tempArray.add(String.valueOf(key));
						}	
						comboChoice = NewGame
								.displayComboBox(
										"Select region in which minion should be placed",
										tempArray);
						newReg = Integer.parseInt(comboChoice);
					
						GameUtility.removeMinion(playerObj.color, oldReg);
						GameUtility.placeMinion(playerObj.color, newReg);
					}				
				}
			}

			break;

		case "QUEEN_MOLLY":
			// select player who will give you two cards
			// select another player
			List<String> players3 = getColorsOfPlayers();
			// color of selected player
			String playerColor9 = NewGame.displayComboBox("Select one player",
					players3);
			int playerNumber3 = 0;
			// they give you two cards of their choice
			// display cards of selected player from which he selects two cards
			// to be given
			List<String> cards5 = new ArrayList<String>();
			cards5 = getPlayerCards(playerColor9);
			// call method to display all the cards
			String pCard3 = NewGame.displayComboBox(
					"Give card of your choice to current player", cards5);
			// get the card from the player
			takePlayerCard(playerColor9, pCard3);
			// for second card
			List<String> cards6 = new ArrayList<String>();
			cards6 = getPlayerCards(playerColor9);
			// call method to display all the cards
			String pCard5 = NewGame.displayComboBox(
					"Give card of your choice to current player", cards6);
			takePlayerCard(playerColor9, pCard5);
			break;

		case "ZORGO_THE_RETRO_PHRENOLOGIST":
			// you may exchange personality card with one chosen from from
			// unused personality cards
			//List<PersonalityCards.getPersonalityCard> list = PersonalityCards.PersonalityList;
			//String pCard = list.get(0).toString();
			String pCard = PersonalityCards.getPersonalityCard();
			NewGame.displayUnusedPersonalityCards(pCard);
			ArrayList<String> choice3 = new ArrayList<String>();
			choice3.add("yes");
			choice3.add("no");
			String response = " ";
			response = NewGame.displayComboBox(
					"Do you want to replace personality card?", choice3);
			// String response =
			// NewGame.displayBox("Do you want to exchange personality card? (y/n)");
			if (response.equals("yes")) {
				for (Player playerObj : GameUtility.playerObjList) {
					if (playerObj.pTurn == 1) {
						playerObj.personality = pCard;
					}
				}
			}
			break;

		case "DR_WHITEFACE":
			// select another player
			List<String> players10 = getColorsOfPlayers();
			// color of selected player
			String playerColor20 = NewGame.displayComboBox("Select one player",
					players10);
			int count7 = 0;
			// another player gives you $5
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.color.equals(playerColor20)) {
					if (playerObj.cashHold >= 5) {
						playerObj.cashHold = playerObj.cashHold - 5;
						count7 = count7 + 5;
					}
					// this card now counts towards another player's player
					// cards
					else {
						List<String> greenList = playerObj.pCards.get("Green");
						for (int i = 0; i < greenList.size(); i++) {
							greenList.add(playerCardName);
						}
						// remove all green colored cards from hash map
						playerObj.pCards.remove("Green");
						// add new list to hash map
						playerObj.pCards.put("Green", greenList);
					}
				}
			}

			// add cash to current player's account
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.pTurn == 1) {
					playerObj.cashHold = playerObj.cashHold + count7;
				}
			}
			break;

		case "WALLACE_SONKY":
			break;

		case "THE_SEAMSTRESSES_GUILD":
			// select another player
			List<String> players4 = getColorsOfPlayers();
			// color of selected player
			String playerColor11 = NewGame.displayComboBox("Select one player",
					players4);
			int count8 = 0;
			int flag2 = 0; // denotes if player can pay you $2 or not
			// another player gives you $2
			for (Player playerObj : GameUtility.playerObjList) {
				if (playerObj.color.equals(playerColor11)) {
					if (playerObj.cashHold >= 2) {
						playerObj.cashHold = playerObj.cashHold - 2;
						count8 = count8 + 2;
						flag2 = 1;
					}
				}
			}

			String card2 = null;
			// player can pay for card
			if (flag2 == 1) {
				for (Player playerObj : GameUtility.playerObjList) {
					if (playerObj.pTurn == 1) {
						// discard card and take $2 from bank
						List<String> cards3 = new ArrayList<String>();
						cards3 = getPlayerCards(playerObj.color);

						// remove the card being played
						cards3.remove(playerCardName);

						String pCard6 = NewGame
								.displayComboBox(
										"Select card that you want to give to another player",
										cards3);
						// give them a player card
						givePlayerCard(playerColor11, pCard6);
						playerObj.cashHold = playerObj.cashHold + count8;
					}
				}
			} else {
				NewGame.displayBox("The other player doesn't have enough money to give you");
			}
			break;

		case "THE_THIEVES_GUILD":
			// take $2 from every other player
			int count9 = 0;
			for (Player playerObj : GameUtility.playerObjList) {
				if ((playerObj.pTurn == 0) && (playerObj.cashHold >= 2)) {
					takeMoneyFromOtherPlayers(2, playerObj.pNumber);
					count9 = count9 + 2;
				}
			}
			for (Player playerObj : GameUtility.playerObjList) {
				// current player
				if (playerObj.pTurn == 1) {
					playerObj.cashHold = playerObj.cashHold + count9;
				}
			}
			break;

		default:
			throw new IllegalArgumentException("Invalid player card name: "
					+ playerCardName);
		}
	}

	/**
	 * method creates deck of brown and green cards as HashMap and two lists
	 * that shuffle and then gets added to HashMap
	 */
	public static void createPlayerCardsDeck() {
		playerCards = new HashMap<String, List<Integer>>();
		List<Integer> listForGreen = new ArrayList<>();
		List<Integer> listForBrown = new ArrayList<>();
		String Green = "Green";
		String Brown = "Brown";

		// add initial 48 cards for Green color list
		for (int i = 1; i <= 48; i++) {
			listForGreen.add(i);
		}

		// 53 cards for Brown color list
		for (int i = 1; i <= 53; i++) {
			listForBrown.add(i);
		}
		// shuffle values of lists
		Collections.shuffle(listForBrown);
		Collections.shuffle(listForGreen);

		// add values to map
		playerCards.put(Green, listForGreen);
		playerCards.put(Brown, listForBrown);

	}

	/**
	 * method that returns the playerCard values i.e. color and number
	 * 
	 * @return Pair returns an object to a utility class Pair which in turn has <br>
	 *         data members card number and color
	 * 
	 */
	public static Pair getPlayerCard() {
		Collections.shuffle(greenPlayerCardsList);
		String result = null;
		if (greenPlayerCardsList.isEmpty()) {
			checkWinningCondition();
			checkWinningPoints();
		} else {
			result = greenPlayerCardsList.get(0).toString();
			greenPlayerCardsList = new ArrayList<PlayerCardDeck>(
					greenPlayerCardsList);
			greenPlayerCardsList.remove(0);
		}
		return new Pair(result, "Green");
	}
	
	// remove player card from deck
	public static void removeCard(String cardName)
	{
		int count = 0;
		greenPlayerCardsList = new ArrayList<PlayerCardDeck>(greenPlayerCardsList);
		for(PlayerCardDeck a : greenPlayerCardsList)
		{
			String str = a.toString();
			if(str.equals(cardName))
			{
				//System.out.println("in if:   "+str);
				break;
			}
			else{
				//System.out.println("in else:   "+str);
				count++;
			}
		}
		greenPlayerCardsList.remove(count);
		//System.out.println("removed");
	}
	/*
	 * methods checks winning points of all players
	 */

	public static void checkWinningPoints() {
		int minion, mCost, bCost, cash, size, rNum, totalPoints;
		String color;
		String winnerColor = "";
		int winner = 0;
		size = GameUtility.playerObjList.size();
		for (int i = 0; i < size; i++) {
			bCost = 0;
			totalPoints = 0;
			color = GameUtility.playerObjList.get(i).color;
			minion = GameUtility.playerObjList.get(i).minionHold;
			mCost = (12 - minion) * 5;
			cash = GameUtility.playerObjList.get(i).cashHold;
			for (int key : GameUtility.playerObjList.get(i).H_Region.keySet()) {
				if (GameUtility.playerObjList.get(i).H_Region.get(key).placedbuilding == 1) {
					rNum = GameUtility.playerObjList.get(i).H_Region.get(key).regionNumber;
					bCost = bCost
							+ GameUtility.regionObjList.get(rNum - 1).rBuildingCost;
				}
			}
			totalPoints = mCost + cash + bCost;
			if (winner < totalPoints) {
				winnerColor = color;
				winner = totalPoints;
			}
		}
		NewGame.showErrorDialog("Congratulations, Player: " + winnerColor
				+ " wins having total points: " + winner);
		System.exit(0);
	}

	/**
	 * check winning condition of current player accordingly
	 */
	public static void checkWinningCondition() {
		int result = 0;
		for (Player playerObj : GameUtility.playerObjList) {
			if(playerObj.pTurn == 1)
			{
				result = playerObj.checkWinningCondition(playerObj.personality);
				
				// winning condition satisfies
				if (result == 1) {
					NewGame.showErrorDialog("Congratulations " + playerObj.color
							+ " Win The Game!");
					System.exit(0);
				}
			}
		}
	}

	// the cards that might have taken loan from bank and who need to pay back
	// at the end of game
	public static void populateLoanCards(String loanCard) {
		for (Player playerObj : GameUtility.playerObjList) {
			if (playerObj.pTurn == 1) {
				GameEngine.loanCards.put(loanCard, playerObj.color);
			}
		}
	}

	/**
	 * Method helps to move minion of another player from one region<br>
	 * to another
	 * 
	 * @param pcolor
	 *            String represents player whose minion needs to be moved
	 */
	public static void moveOtherPlayerMinion(String pcolor) {
		String oldRegion = "";
		String newRegion = "";
		int result;
		int oldReg, newReg;
		for (Player playerObj : GameUtility.playerObjList) {
			if (playerObj.color.equals(pcolor)) {
				tempArray.clear();
				for (int key : playerObj.H_Region.keySet()) {
					if (playerObj.H_Region.get(key).placedMinion > 0) {
						tempArray.add(String.valueOf(key));
					}
				}				
				comboChoice = NewGame
						.displayComboBox(
								"Select region from which minion should be moved",
								tempArray);
				oldReg = Integer.parseInt(comboChoice);
				tempArray.clear();
				for (int key : GameUtility.regionObjList.get(oldReg-1).listForNeighbours) {
					tempArray.add(String.valueOf(key));
				}	
				comboChoice = NewGame
						.displayComboBox(
								"Select region in which minion should be placed",
								tempArray);
				newReg = Integer.parseInt(comboChoice);
				
				GameUtility.removeMinion(playerObj.color, oldReg);
				GameUtility.placeMinion(playerObj.color, newReg);
				break;
			}// end if
		}
	}

	/**
	 * Method that checks if a player's minion is about to be removed<br>
	 * by another player during assassination and the player has interrupt card.<br>
	 * So he should stop the other player immediately.
	 * 
	 * @param oldRegion
	 *            Integer region from which minion of player is supposed to be
	 *            removed
	 * @param pColor
	 *            String denotes player whose minion is being removed
	 * @return
	 */
	public static int checkInterruptCard(int oldRegion, String pColor) {
		int result = 0;
		List<String> greenList = new ArrayList<>();
		tempArray.clear();
		tempArray.add("Yes");
		tempArray.add("No");
		for (Player playerObj : GameUtility.playerObjList) {
			if (playerObj.color.equals(pColor)) {
				// create temp list
				greenList = playerObj.pCards.get("Green");
				for (int i = 0; i < greenList.size(); i++) {
					// get index of given cardName in the list
					// check for player cards Gaspode and Wallace Sonky
					if (greenList.get(i).equals("GASPODE")
							|| greenList.get(i).equals("WALLACE_SONKY")) {
						comboChoice = NewGame.displayComboBox(
								"Player: " + pColor + " Do you want to play: "
										+ greenList.get(i), tempArray);
						if (comboChoice.equals("Yes")) {
							greenList.remove(i);
							result = 1;
						}
						break;
					}
					// check for fresh start club player card
					else if (greenList.get(i).equals("FRESH_START_CLUB")) {
						comboChoice = NewGame.displayComboBox(
								"Player: " + pColor + " Do you want to play: "
										+ greenList.get(i), tempArray);
						if (comboChoice.equals("Yes")) {
							comboChoice = NewGame
									.displayComboBox(
											"Select region in which minion should be placed",
											regionListCombo);
							int newReg = Integer.parseInt(comboChoice);
							result = playerObj.checkMinionMove(newReg);
							// user input is not correct, minion cannot be
							// placed in
							// that area
							while (result == 0) {
								comboChoice = NewGame
										.displayComboBox(
												"Wrong Input! , Select region in which minion should be placed",
												regionListCombo);
								newReg = Integer.parseInt(comboChoice);
								result = playerObj.checkMinionMove(newReg);
							}
							GameUtility
									.removeMinion(playerObj.color, oldRegion);
							GameUtility.placeMinion(playerObj.color, newReg);
							greenList.remove(i);
							result = 1;
						}
						break;
					}
				}

				// remove all green colored cards from hashmap
				playerObj.pCards.remove("Green");
				// add new list to hashmap
				playerObj.pCards.put("Green", greenList);
				if (result == 1) {
					break;
				}
			}
		}
		return result;
	}

	public static void displayCards()
	{
		for(PlayerCardDeck a:greenPlayerCardsList)
		{
			System.out.println("values in list:  "+a.toString());
		}
	}
}
