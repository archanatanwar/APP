package Game;

import java.util.*;

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
	public enum PlayerCardDeck {
		MR_BOGGIS, MR_BENT, THE_BEGGARS_GUILD, THE_BANK_OF_ANKH_MORPORK, THE_ANKH_MORPORK_SUNSHINE_DRAGON_SANCTUARY, SERGEANT_ANGUA, THE_AGONY_AUNTS, THE_DYSK, THE_DUCKMAN, DRUMKNOTT, CMOT_DIBBLER, DR_CRUCES, CAPTAIN_CARROT, MRS_CAKE, GROAT, GIMLETS_DWARF_DELICATESSEN, GASPODE, FRESH_START_CLUB, FOUL_OLE_RON, THE_FOOLS_GUILD, THE_FIRE_BRIGADE, INIGO_SKIMMER, HISTORY_MONKS, HEX, HERE_N_NOW, HARRY_KING, HARGAS_HOUSE_OF_RIBS, MR_GRYLE, THE_PEELED_NUTS, THE_OPERA_HOUSE, NOBBY_NOBS, MODO, THE_MENDED_DRUM, LIBRARIAN, LEONARD_OF_QUIRM, SHONKY_SHOP, SACHARISSA_CRIPSLOCK, ROSIE_PALM, RINCEWIND, THE_ROYAL_MINT, QUEEN_MOLLY, PINK_PUSSYCAT_CLUB, ZORGO_THE_RETRO_PHRENOLOGIST, DR_WHITEFACE, WALLACE_SONKY, THE_SEAMSTRESSES_GUILD, MR_PIN_MR_TULIP, THE_THIEVES_GUILD
	}

	enum playerCardSymbols {
		PLACE_MINON, PLACE_BUILDING, ASSASSINATION, REMOVE_ONE_TROUBLE_MARKER, TAKE_MONEY, SCROLL, RANDOM_EVENT, PLAY_ANOTHER_CARD, INTERRUPT
	}

	public static List<Integer> regionList = Arrays.asList(1, 2, 3, 4, 5, 6, 7,
			8, 9, 10, 11, 12);
	public static List<PlayerCardDeck> greenPlayerCardsList = Arrays
			.asList(PlayerCardDeck.values());
	// Contains Player card color as key and list of card numbers as value
	public static HashMap<String, List<Integer>> playerCards;
	
	// roll die to get a specific number
	public static int rollDie() {
		int result = 0;
		Collections.shuffle(regionList);
		result = regionList.get(0);
		return result;
	}

	// pay specified money to bank
	public static void payToBank(int cash) {
		for (Player playerObj : GameEngine.playerObjList) {
			if (playerObj.pTurn == 1) {
				playerObj.cashHold = playerObj.cashHold - cash;
				GameEngine.BankHold = GameEngine.BankHold + cash;
			}
		}
	}

	// take loan from bank
	public static void takeLoanFromBank(int cash) {
		for (Player playerObj : GameEngine.playerObjList) {
			// current player
			if (playerObj.pTurn == 1) {
				GameEngine.BankHold = GameEngine.BankHold - cash;
				playerObj.cashHold = playerObj.cashHold + cash;
			}
		}
	}

	// take player cards from other players
	public static void takePlayerCard(int playerNumber, String cardName) {
		String result = null;
		for (Player playerObj : GameEngine.playerObjList) {
			
			Pair pair = new Pair(" "," ");
			// player that should give a player card
			if (playerObj.pNumber == playerNumber) {
				// get key by color green first
				List<String> tempList = playerObj.pCards.get("Green");
				// if the card to be removed is same as the card being played
				if(cardName == tempList.get(0))
				{
					result = tempList.get(1);
				}
				else
				{
					result = tempList.get(0);
				}
				tempList.remove(0);
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
		for(Player playerObj : GameEngine.playerObjList)
		{
			// player whose turn is 1
			if (playerObj.pTurn == 1) {
				list = playerObj.pCards.get("Green");
				list.add(result);
				playerObj.pCards.put("Green", list);

			} // end if
		}
	}

	// take money from other players
	public static void takeMoneyFromOtherPlayers(int cash, int playerNumber) {
		for (Player playerObj : GameEngine.playerObjList) {
			// not current player
			if (playerObj.pNumber == playerNumber) {
				playerObj.cashHold = playerObj.cashHold - cash;
			}
		}
	}

	// take money from every other player
		public static void takeMoneyFromEveryPlayer(int cash)
		{
			int count = 0;
			for(Player playerObj : GameEngine.playerObjList)
			{
				if(playerObj.pTurn == 0)
				{
					//takeMoneyFromOtherPlayers(2, playerObj.pNumber);
					playerObj.cashHold = playerObj.cashHold - cash;
					count = count + cash;
				}
			}
			for(Player playerObj : GameEngine.playerObjList)
			{
				// current player
				if(playerObj.pTurn == 1)
				{
					playerObj.cashHold = playerObj.cashHold + count;
				}
			}
		}
	// delete player card from list
	private static void delPlayerCard(String cardName) {
		List<String> greenList = new ArrayList<>();
		for (Player playerObj : GameEngine.playerObjList) {
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

	public static void performLeftToRight(String cardName) {
		checkWinningCondition();
//		cardName = "ROSIE_PALM";
		switch (cardName) {
		case "MR_BOGGIS":
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLACE_MINION", cardName);
			delPlayerCard(cardName);
			break;
		case "MR_BENT":
			populateLoanCards("MR_BENT");
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			delPlayerCard(cardName);
			break;
		case "THE_BEGGARS_GUILD":
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLACE_MINION", cardName);
			delPlayerCard(cardName);
			break;
		case "THE_BANK_OF_ANKH_MORPORK":
			populateLoanCards("THE_BANK_OF_ANKH_MORPORK");
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			delPlayerCard(cardName);
			break;
		case "THE_ANKH_MORPORK_SUNSHINE_DRAGON_SANCTUARY":
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			delPlayerCard(cardName);
			break;
		case "SERGEANT_ANGUA":
			performActionOfSymbol("REMOVE_ONE_TROUBLE_MARKER", cardName);
			performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			delPlayerCard(cardName);
			break;
		case "THE_AGONY_AUNTS":
			performActionOfSymbol("ASSASSINATION", cardName);
			takeLoanFromBank(2);
			performActionOfSymbol("PLACE_MINION", cardName);
			delPlayerCard(cardName);
			break;
		case "THE_DYSK":
			performActionOfSymbol("PLACE_BUILDING", "THE_DYSK");
			//performActionOfSymbol("SCROLL", cardName);
			delPlayerCard(cardName);
			break;
		case "THE_DUCKMAN":
			performActionOfSymbol("SCROLL", cardName);
			delPlayerCard(cardName);
			break;
		case "DRUMKNOTT":
			performActionOfSymbol("SCROLL", cardName);
			delPlayerCard(cardName);
			break;
		case "CMOT_DIBBLER":
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			delPlayerCard(cardName);
			break;
		case "DR_CRUCES":
			performActionOfSymbol("ASSASSINATION", cardName);
			takeLoanFromBank(3);
			delPlayerCard(cardName);
			break;
		case "CAPTAIN_CARROT":
			performActionOfSymbol("PLACE_MINION", cardName);
			performActionOfSymbol("REMOVE_ONE_TROUBLE_MARKER", cardName);
			takeLoanFromBank(1);
			delPlayerCard(cardName);
			break;
		case "MRS_CAKE":
			performActionOfSymbol("SCROLL", cardName);
			takeLoanFromBank(2);
			performActionOfSymbol("PLACE_BUILDING", cardName);
			delPlayerCard(cardName);
			break;
		case "GROAT":
			performActionOfSymbol("PLACE_MINION", cardName);
			delPlayerCard(cardName);
			break;
		case "GIMLETS_DWARF_DELICATESSEN":
			takeLoanFromBank(3);
			performActionOfSymbol("PLACE_MINION", cardName);
			delPlayerCard(cardName);
			break;
		case "GASPODE":
			performActionOfSymbol("INTERRUPT", cardName);
			delPlayerCard(cardName);
			break;
		case "FRESH_START_CLUB":
			performActionOfSymbol("INTERRUPT", cardName);
			delPlayerCard(cardName);
			break;
		case "FOUL_OLE_RON":
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			delPlayerCard(cardName);
			break;
		case "THE_FOOLS_GUILD":
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLACE_MINION", cardName);
			delPlayerCard(cardName);
			break;
		case "THE_FIRE_BRIGADE":
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			delPlayerCard(cardName);
			break;
		case "INIGO_SKIMMER":
			performActionOfSymbol("ASSASSINATION", cardName);
			delPlayerCard(cardName);
			break;
		case "HISTORY_MONKS":
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLACE_MINION", cardName);
			delPlayerCard(cardName);
			break;
		case "HEX":
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLACE_BUILDING", cardName);
			delPlayerCard(cardName);
			break;
		case "HERE_N_NOW":
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			delPlayerCard(cardName);
			break;
		case "HARRY_KING":
			performActionOfSymbol("PLACE_MINION", cardName);
			performActionOfSymbol("SCROLL", cardName);
			delPlayerCard(cardName);
			break;
		case "HARGAS_HOUSE_OF_RIBS":
			takeLoanFromBank(3);
			performActionOfSymbol("PLACE_MINION", cardName);
			delPlayerCard(cardName);
			break;
		case "MR_GRYLE":
			performActionOfSymbol("ASSASSINATION", cardName);
			takeLoanFromBank(1);
			delPlayerCard(cardName);
			break;
		case "THE_PEELED_NUTS":
			// no action specified
			delPlayerCard(cardName);
			break;
		case "THE_OPERA_HOUSE":
			performActionOfSymbol("PLACE_BUILDING", cardName);
			performActionOfSymbol("SCROLL", cardName);
			delPlayerCard(cardName);
			break;
		case "NOBBY_NOBS":
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			delPlayerCard(cardName);
			break;
		case "MODO":
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLACE_MINION", cardName);
			delPlayerCard(cardName);
			break;
		case "THE_MENDED_DRUM":
			performActionOfSymbol("PLACE_BUILDING", cardName);
			takeLoanFromBank(2);
			delPlayerCard(cardName);
			break;
		case "LIBRARIAN":
			performActionOfSymbol("SCROLL", cardName);
			delPlayerCard(cardName);
			break;
		case "LEONARD_OF_QUIRM":
			performActionOfSymbol("SCROLL", cardName);
			delPlayerCard(cardName);
			break;
		case "SHONKY_SHOP":
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLACE_BUILDING", cardName);
			delPlayerCard(cardName);
			break;
		case "SACHARISSA_CRIPSLOCK":
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLACE_MINION", cardName);
			delPlayerCard(cardName);
			break;
		case "ROSIE_PALM":
			performActionOfSymbol("PLACE_MINION", cardName);
			performActionOfSymbol("SCROLL", cardName);
			delPlayerCard(cardName);
			break;
		case "RINCEWIND":
			performActionOfSymbol("RANDOM_EVENT", cardName);
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			delPlayerCard(cardName);
			break;
		case "THE_ROYAL_MINT":
			performActionOfSymbol("PLACE_BUILDING", cardName);
			delPlayerCard(cardName);
			takeLoanFromBank(5);
			break;
		case "QUEEN_MOLLY":
			performActionOfSymbol("PLACE_MINION", cardName);
			performActionOfSymbol("SCROLL", cardName);
			delPlayerCard(cardName);
			break;
		case "PINK_PUSSYCAT_CLUB":
			takeLoanFromBank(3);
			performActionOfSymbol("PLAY_ANOTHER_CARD", cardName);
			delPlayerCard(cardName);
			break;
		case "ZORGO_THE_RETRO_PHRENOLOGIST":
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLACE_BUILDING", cardName);
			delPlayerCard(cardName);
			break;
		case "DR_WHITEFACE":
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLACE_MINION", cardName);
			delPlayerCard(cardName);
			break;
		case "WALLACE_SONKY":
			performActionOfSymbol("INTERRUPT", cardName);
			delPlayerCard(cardName);
			break;
		case "THE_SEAMSTRESSES_GUILD":
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLACE_MINION", cardName);
			delPlayerCard(cardName);
			break;
		case "MR_PIN_MR_TULIP":
			performActionOfSymbol("ASSASSINATION", cardName);
			takeLoanFromBank(1);
			delPlayerCard(cardName);
			break;
		case "THE_THIEVES_GUILD":
			performActionOfSymbol("SCROLL", cardName);
			performActionOfSymbol("PLACE_MINION", cardName);
			delPlayerCard(cardName);
			break;
		default:
			throw new IllegalArgumentException("Invalid player card name: "
					+ cardName);
		}
	}

	public static void performActionOfSymbol(String symbolName,
			String playerCardName) {
		switch (symbolName) {
		case "PLACE_MINION":
			String oldRegion = "";
			String newRegion = "";
			int result;
			int oldReg, newReg;
			for (Player playerObj : GameEngine.playerObjList) {
				if (playerObj.pTurn == 1) {
					newRegion = NewGame
							.displayBox("Select region in which minion should be placed:");
					newReg = Integer.parseInt(newRegion);
					result = playerObj.checkMinionMove(newReg);
					while(result == 0)
					{
						NewGame.showErrorDialog("Wrong Input!");
						newRegion = NewGame
								.displayBox("Select region in which minion should be placed:");
						newReg = Integer.parseInt(newRegion);
						result = playerObj.checkMinionMove(newReg);
					}
					if (playerObj.minionHold < 1) {
						oldRegion = NewGame
								.displayBox("Select region from which minion should be moved:");
						oldReg = Integer.parseInt(oldRegion);
						playerObj.removeMinion(oldReg);
						GameEngine.regionObjList.get(oldReg-1).removeMinion(playerObj.color);
					}					
					playerObj.placeMinion(newReg);
					GameEngine.regionObjList.get(newReg-1).placeMinion(playerObj.color);
					break;
				}								
			}
			break;
		case "PLACE_BUILDING":
			oldRegion = "";
			newRegion = "";			
			for (Player playerObj : GameEngine.playerObjList) {
				if (playerObj.pTurn == 1) {
					newRegion = NewGame
							.displayBox("Select region in which building should be placed:");
					newReg = Integer.parseInt(newRegion);
					result = GameEngine.regionObjList.get(newReg-1).checkBuildingMove(playerObj.color);
					while(result == 0)
					{
						NewGame.showErrorDialog("Wrong Input!");
						newRegion = NewGame
								.displayBox("Select region in which building should be placed:");
						newReg = Integer.parseInt(newRegion);
						result = GameEngine.regionObjList.get(newReg-1).checkBuildingMove(playerObj.color);
					}
					if (playerObj.buildingHold < 1) {
						oldRegion = NewGame
								.displayBox("Select region from which building should be moved:");
						oldReg = Integer.parseInt(oldRegion);
						playerObj.removeBuilding(oldReg);
						GameEngine.regionObjList.get(oldReg-1).removeBuilding(playerObj.color);
					}					
					playerObj.placeBuilding(newReg);
					GameEngine.regionObjList.get(newReg-1).placeBuilding(playerObj.color);
					break;
				}								
			}
			break;
		case "ASSASSINATION":
			result = 0;
			int interruptStatus = 0;
			String choice, playerChoice;
			for (Player playerObj : GameEngine.playerObjList) {
				if (playerObj.pTurn == 1) {
					newRegion = NewGame
							.displayBox("Select region number:");
					newReg = Integer.parseInt(newRegion);
					choice = NewGame
							.displayBox("Select choice (Minion/Demon/Troll) to be removed:");					
					while(result == 0)
					{
						switch(choice)
						{
						case "minion":
							playerChoice = NewGame
									.displayBox("Select player color:");
							for (Player playerObj1 : GameEngine.playerObjList) {
								if (playerObj1.color.equals(playerChoice)) {									
									interruptStatus = checkInterruptCard(playerChoice);
									if(interruptStatus == 0)
									{
										playerObj1.removeMinion(newReg);
										GameEngine.regionObjList.get(newReg-1).removeMinion(playerObj1.color);
									}
									result = 1;
								}
							}
							break;
						case "demon" :
							GameEngine.regionObjList.get(newReg-1).removeDemon();
							result = 1;
							break;
						case "troll":
							GameEngine.regionObjList.get(newReg-1).removeTroll();
							result = 1;
							break;
						default:
							result = 0;
							break;							
						}						
					}
				}					
			}
			break;
		case "REMOVE_ONE_TROUBLE_MARKER":
			int tRegion;
			String tempRegion;
			tempRegion= NewGame
			.displayBox("Select region from which trouble marker should be removed:");
			tRegion = Integer.parseInt(tempRegion);
			GameEngine.regionObjList.get(tRegion-1).removeTroubleMarker();
			/*while(result == 0)
			{
				NewGame.showErrorDialog("Wrong Input!");
				tempRegion = NewGame
						.displayBox("Select region from which trouble marker should be removed:");
						tRegion = Integer.parseInt(tempRegion);
						result = GameEngine.regionObjList.get(tRegion-1).removeTroubleMarker();
			}*/
			break;
		case "TAKE_MONEY":
			for (Player playerObj : GameEngine.playerObjList) {
				if (playerObj.pTurn == 1) {
					GameEngine.BankHold = GameEngine.BankHold - 2;
					playerObj.cashHold = playerObj.cashHold + 2;
				}				
			}
			break;
		case "SCROLL":
			// perform action specified at bottom of card
			performActionInText(playerCardName);
			break;
		case "RANDOM_EVENT":
			String eventChoice  = RandomEventCards.getRandomEventCard();
			System.out.println(eventChoice);
			switch("THE_DRAGON")
			{
				case "THE_DRAGON":
					RandomEventCards dragonEvent = new DragonEventCard();
					dragonEvent.executeRandomEvent();
					break;
				case "FLOOD":
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
					explosionEvent.executeRandomEvent();
					break;
				case "MYSTERIOUS_MURDERS":
					break;
				case "DEMONS_FROM_THE_DUNGEONS_DIMENSIONS":
					RandomEventCards dungeonEvent = new DungeonEventCard();
					dungeonEvent.executeRandomEvent();
					break;
				case "SUBSIDENCE":
					RandomEventCards subsidenceEvent = new SubsidenceEventCard();
					subsidenceEvent.executeRandomEvent();
					break;
				case "BLOODY_STUPID_JOHNSON":
					break;
				case "TROLLS":
					RandomEventCards trollsEvent = new TrollsEventCard();
					trollsEvent.executeRandomEvent();
					break;
				case "EARTHQUAKE":
					RandomEventCards earthquakeEvent = new ExplosionEventCard();
					earthquakeEvent.executeRandomEvent();
					earthquakeEvent.executeRandomEvent();
					break;
			}
			break;
		case "PLAY_ANOTHER_CARD":
		System.out.println("play another card");
			//delPlayerCard(playerCardName);
		//	NewGame.createPlayerFrame();
			break;
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
	public static void performActionInText(String playerCardName)
	{
		switch(playerCardName){
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
			// select one player
			String playerColor1 = NewGame.displayBox("Select one player according to color");
			int playerNumber = 0;
			// they give you two cards of their choice
			for(Player playerObj : GameEngine.playerObjList)
			{
				if(playerObj.color.equals(playerColor1))
				{
					playerNumber = playerObj.pNumber;
				}
			}
			for(int i=0 ; i < 2; i++)
			{
				takePlayerCard(playerNumber, playerCardName);
			}
			break;

		case "THE_BANK_OF_ANKH_MORPORK":
			// take loan of $10 from bank
			takeLoanFromBank(10);
			break;

		case "THE_ANKH_MORPORK_SUNSHINE_DRAGON_SANCTUARY":
			int count1 = 0;
			// each player card should give you either $1 or one of their cards
			for(Player playerObj : GameEngine.playerObjList)
			{
				if(playerObj.pTurn == 0)
				{
					if(playerObj.cashHold >= 1)
					{
						// give $1 to player i turn
						takeMoneyFromOtherPlayers(1, playerObj.pNumber);
						count1 = count1 + 1;
					}
					else
					{
						takePlayerCard(playerObj.pNumber , playerCardName);
					}
				}
			}
			for(Player playerObj : GameEngine.playerObjList)
			{
				// current player
				if(playerObj.pTurn == 1)
				{
					playerObj.cashHold = playerObj.cashHold + count1;
				}
			}
			break;

		case "THE_DYSK":
			// earn $1 for each minion in Isle Of Gods
			int number = GameEngine.objIGods.rMinionNum;
			for(Player playerObj : GameEngine.playerObjList)
			{
				// current player
				if(playerObj.pTurn == 1)
				{
					playerObj.cashHold = playerObj.cashHold + number;
				}
			}
			break;

		case "THE_DUCKMAN":
			// move a minion belonging to another player from one area to adjacent area
			String playerColor2 = NewGame.displayBox("Select one player according to color whose minion is to be moved");
			// call methods to remove and place minion
			moveOtherPlayerMinion(playerColor2);
			break;
			
		case "DRUMKNOTT":
			// Play any two other cards
			delPlayerCard(playerCardName);
			for(int i=0; i<2; i++)
			{
				NewGame.createPlayerFrame();
			}
			break;

		case "CMOT_DIBBLER":
			int result = rollDie();
			NewGame.showErrorDialog("Result after rolling die: "+String.valueOf(result));
			// on roll of 7 or more take $4 from bank
			if(result >= 7)
			{
				takeLoanFromBank(4);
			}
			// on roll of 1, pay $2 to bank or remove one of minions from board
			else if(result == 1)
			{
				for(Player playerObj : GameEngine.playerObjList)
				{
					// current player
					if((playerObj.pTurn == 1) && (playerObj.cashHold >= 2))
					{
						payToBank(2);
					}
					else if((playerObj.pTurn == 1) && (playerObj.cashHold < 2))
					{
						// remove one of your minions from board
						String area3 = NewGame.displayBox("Select area from which minion should be moved");
						int areaBefore1 = Integer.parseInt(area3);
						GameEngine.regionObjList.get(areaBefore1-1).removeMinion(playerObj.color);
						playerObj.removeMinion(areaBefore1);
					}
				}
			}
			break;

		case "MRS_CAKE":
			// look at all but one of unused personality cards
			NewGame.displayUnusedPersonalityCards();
			break;
		case "GASPODE":
			// stop a player from removing your minion area
			break;

		case "FRESH_START_CLUB":
			// if you have minion removed, place it in different area
			break;

		case "FOUL_OLE_RON":
			/// move a minion belonging to another player from one area to adjacent area
			String playerColor3 = NewGame.displayBox("Select one player according to color whose minion is to be moved");
			// call methods to remove and place minion
			moveOtherPlayerMinion(playerColor3);
			break;
		case "THE_FOOLS_GUILD":
			// select another player
			String playerColor4 = NewGame.displayBox("Select another player");
			int count2 = 0;
			// another player gives you $5
			for(Player playerObj : GameEngine.playerObjList)
			{
				if(playerObj.color.equals(playerColor4))
				{
					if(playerObj.cashHold >= 5)
					{
						playerObj.cashHold = playerObj.cashHold - 5;
						count2 = count2 + 5;
					}
					// this card now counts towards another player's player cards
					else
					{
						List<String> greenList = playerObj.pCards.get("Green");
						for(int i =0 ; i<greenList.size();i++)
						{
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
			for(Player playerObj : GameEngine.playerObjList)
			{
				if(playerObj.pTurn == 1)
				{
					playerObj.cashHold = playerObj.cashHold + count2;
				}
			}
			break;
			
		case "THE_FIRE_BRIGADE":
			// select another player
			String playerColor5 = NewGame.displayBox("Select another player");
			int count3 = 0;
			// another player gives you $5
			for(Player playerObj : GameEngine.playerObjList)
			{
				if(playerObj.color.equals(playerColor5))
				{
					if(playerObj.cashHold >= 5)
					{
						playerObj.cashHold = playerObj.cashHold - 5;
						count3 = count3 + 5;
					}
					// current player can remove another player's building from board
					else
					{
						// ask for region info
						String area6 = NewGame.displayBox("Select area from which building should be removed");
						int value = Integer.valueOf(area6);
						// method to remove building
						playerObj.removeBuilding(value);
						GameEngine.regionObjList.get(value-1).removeBuilding(playerObj.color);
					}
				}
			}
			
			// add cash to current player's account
			for(Player playerObj : GameEngine.playerObjList)
			{
				if(playerObj.pTurn == 1)
				{
					playerObj.cashHold = playerObj.cashHold + count3;
				}
			}
			break;
			
		case "HISTORY_MONKS":
			// shuffle discard pile
			Collections.shuffle(GameEngine.discardCards);
			List<String>list1 = new ArrayList<String>();
			// draw four cards randomly
			if(!GameEngine.discardCards.isEmpty())
			{
				for(int i = 0 ; i<4 ; i++)
				{
					String toBeAdded = GameEngine.discardCards.remove(0);
					list1.add(toBeAdded);
				
				}
				for(Player playerObj: GameEngine.playerObjList)
				{
					if(playerObj.pTurn == 1)
					{
						playerObj.pCards.put("Green", list1);
					}
				}
			}
			break;
		case "HEX":
			// take three cards from draw deck
			for(Player playerObj: GameEngine.playerObjList)
			{
				if(playerObj.pTurn == 1)
				{
					List<String>list = playerObj.pCards.get("Green");
					for(int i = 0 ; i<3 ; i++)
					{
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
			NewGame.showErrorDialog("Result after rolling die: "+String.valueOf(result1));
			// on roll of 7 or more take $ from any player
			
			if(result1 >= 7)
			{
				String playerColor6 = NewGame.displayBox("Select another player");
				int count4 =0;
				// another player gives you $3
				for(Player playerObj : GameEngine.playerObjList)
				{
					if(playerObj.color.equals(playerColor6))
					{
						if(playerObj.cashHold >= 3)
						{
							playerObj.cashHold = playerObj.cashHold - 3;
							count4 = count4 + 3;
						}
					}
				}
				
				// add cash to current player's account
				for(Player playerObj : GameEngine.playerObjList)
				{
					if(playerObj.pTurn == 1)
					{
						playerObj.cashHold = playerObj.cashHold + count4;
					}
				}
			}
			// on roll of 1, pay $2 to bank or remove one of minions from board
			else if(result1 == 1)
			{
				for(Player playerObj : GameEngine.playerObjList)
				{
					if(playerObj.pTurn == 1)
					{
						String value1 = NewGame.displayBox("Enter the area from which minion should be removed");
						int valueInt = Integer.valueOf(value1);
						// remove your minion from one of areas
						GameEngine.regionObjList.get(valueInt-1).removeMinion(playerObj.color);
						playerObj.removeMinion(valueInt);
					}
				}
			}
			break;
			
		case "HARRY_KING":
			// discard card and take $2 from bank
			String playerCard = NewGame.displayBox("Enter card name to be discarded in upper case");
			delPlayerCard(playerCard);
			takeLoanFromBank(2);
			break;
			
		case "THE_OPERA_HOUSE":	
			// earn $1 for each minion in Isle Of Gods
			int number1 = GameEngine.objIGods.rMinionNum;
			for(Player playerObj : GameEngine.playerObjList)
			{
				// current player
				if(playerObj.pTurn == 1)
				{
					playerObj.cashHold = playerObj.cashHold + number1;
				}
			}
			break;
			
		case "NOBBY_NOBS":
			// take $3 from a player of your choice
			// select another player
			String playerColor7 = NewGame.displayBox("Select another player");
			int count5 = 0;
			// another player gives you $5
			for(Player playerObj : GameEngine.playerObjList)
			{
				if(playerObj.color.equals(playerColor7))
				{
					if(playerObj.cashHold >= 3)
					{
						playerObj.cashHold = playerObj.cashHold - 3;
						count5 = count5 + 3;
					}
				}
			}
			// add cash to current player's account
			for(Player playerObj : GameEngine.playerObjList)
			{
				if(playerObj.pTurn == 1)
				{
					playerObj.cashHold = playerObj.cashHold + count5;
				}
			}
			break;
			
		case "MODO":
			// discard one card
			String playerCard1 = NewGame.displayBox("Enter card name to be discarded in upper case");
			delPlayerCard(playerCard1);
			break;
			
		case "LIBRARIAN":
			// take 4 cards from draw deck
			for(Player playerObj: GameEngine.playerObjList)
			{
				if(playerObj.pTurn == 1)
				{
					List<String>list = playerObj.pCards.get("Green");
					for(int i = 0 ; i<4 ; i++)
					{
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
			for(Player playerObj: GameEngine.playerObjList)
			{
				if(playerObj.pTurn == 1)
				{
					List<String>list = playerObj.pCards.get("Green");
					for(int i = 0 ; i<4 ; i++)
					{
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
			String playerCard3 = NewGame.displayBox("Enter card name to be discarded in upper case");
			delPlayerCard(playerCard3);
			takeLoanFromBank(1);
			break;
			
		case "SACHARISSA_CRIPSLOCK":
			// Earn $1 for each trouble marker on board
			takeLoanFromBank(12 - (GameEngine.TMarkerHold));
			break;
			
		case "ROSIE_PALM":
			// select another player
			String playerColor8 = NewGame.displayBox("Select another player");
			int count6 = 0;
			// another player gives you $2
			for(Player playerObj : GameEngine.playerObjList)
			{
				if(playerObj.color.equals(playerColor8))
				{
					if(playerObj.cashHold >= 2)
					{
						playerObj.cashHold = playerObj.cashHold - 2;
						count6 = count6 + 2;
					}
				}
			}
			
			String card = null;
			// add cash to current player's account
			for(Player playerObj : GameEngine.playerObjList)
			{
				if(playerObj.pTurn == 1)
				{
					playerObj.cashHold = playerObj.cashHold + count6;
					// remove one card that should be given to another player
					List<String> greenList = playerObj.pCards.get("Green");
					card = greenList.get(0);
					greenList.remove(0);
					playerObj.pCards.remove("Green");
					// add new list to hash map
					playerObj.pCards.put("Green", greenList);
				}
			}
						
			// add cash to current player's account
			for(Player playerObj : GameEngine.playerObjList)
			{
				if(playerObj.color.equals(playerColor8))
				{
					List<String> greenList = playerObj.pCards.get("Green");
					greenList.add(card);
					playerObj.pCards.remove("Green");
					playerObj.pCards.put("Green", greenList);
				}
			}
			break;
			
		case"RINCEWIND":
			// move your minion from an area containing trouble marker to adjacent area
			String areaBefore3 = NewGame.displayBox("Enter area name that contains TM from which minion should be moved");
			String areaBefore4 = NewGame.displayBox("Enter adjacent area name to which minion should be moved");
			// call method to move minion
			break;
			
		case "QUEEN_MOLLY":
			// select player who will give you two cards
			// select one player
			String playerColor9 = NewGame.displayBox("Select one player according to color");
			int playerNumber3 = 0;
			// they give you two cards of their choice
			for(Player playerObj : GameEngine.playerObjList)
			{
				if(playerObj.color.equals(playerColor9))
				{
					playerNumber3 = playerObj.pNumber;
				}
			}
			for(int i=0 ; i < 2; i++)
			{
				takePlayerCard(playerNumber3, playerCardName);
			}
			break;
			
		case "ZORGO_THE_RETRO_PHRENOLOGIST":
			// you may exchange personality card with one chosen from from unused personality cards
			break;
			
		case "DR_WHITEFACE":
			// select another player
			String playerColor10 = NewGame.displayBox("Select another player");
			int count7 = 0;
			// another player gives you $5
			for(Player playerObj : GameEngine.playerObjList)
			{
				if(playerObj.color.equals(playerColor10))
				{
					if(playerObj.cashHold >= 5)
					{
						playerObj.cashHold = playerObj.cashHold - 5;
						count7 = count7 + 5;
					}
					// this card now counts towards another player's player cards
					else
					{
						List<String> greenList = playerObj.pCards.get("Green");
						for(int i =0 ; i<greenList.size();i++)
						{
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
			for(Player playerObj : GameEngine.playerObjList)
			{
				if(playerObj.pTurn == 1)
				{
					playerObj.cashHold = playerObj.cashHold + count7;
				}
			}
			break;
			
		case "WALLACE_SONKY":
			break;
			
		case "THE_SEAMSTRESSES_GUILD":
			// select another player
			String playerColor11 = NewGame.displayBox("Select another player");
			int count8 = 0;
			// another player gives you $2
			for(Player playerObj : GameEngine.playerObjList)
			{
				if(playerObj.color.equals(playerColor11))
				{
					if(playerObj.cashHold >= 2)
					{
						playerObj.cashHold = playerObj.cashHold - 2;
						count8 = count8 + 2;
					}
				}
			}
						
			String card1 = null;
			// add cash to current player's account
			for(Player playerObj : GameEngine.playerObjList)
			{
				if(playerObj.pTurn == 1)
				{
					playerObj.cashHold = playerObj.cashHold + count8;
					// remove one card that should be given to another player
					List<String> greenList = playerObj.pCards.get("Green");
					card1 = greenList.get(0);
					greenList.remove(0);
					playerObj.pCards.remove("Green");
					playerObj.pCards.put("Green", greenList);
				}
			}
			
			for(Player playerObj : GameEngine.playerObjList)
			{
				if(playerObj.color.equals(playerColor11))
				{
					List<String> greenList = playerObj.pCards.get("Green");
					greenList.add(card1);
					playerObj.pCards.remove("Green");
					playerObj.pCards.put("Green", greenList);
				}
			}
			break;
			
		case "THE_THIEVES_GUILD":
			// take $2 from every other player
			int count9 = 0;
			for(Player playerObj : GameEngine.playerObjList)
			{
				if(playerObj.pTurn == 0)
				{
					takeMoneyFromOtherPlayers(2, playerObj.pNumber);
					count9 = count9 + 2;
				}
			}
			for(Player playerObj : GameEngine.playerObjList)
			{
				// current player
				if(playerObj.pTurn == 1)
				{
					playerObj.cashHold = playerObj.cashHold + count9;
				}
			}
			break;
			
		default:
            throw new IllegalArgumentException("Invalid player card name: " + playerCardName);
		}
	}
	
	
	/**
	 * method creates deck of brown and green cards as HashMap
	 * and two lists that shuffle and then gets added to HashMap
	 */
	public static void createPlayerCardsDeck()
	{
		playerCards = new HashMap<String, List<Integer>>();
		List<Integer> listForGreen = new ArrayList<>();
		List<Integer> listForBrown = new ArrayList<>();
		String Green = "Green";
		String Brown = "Brown";

		// add initial 48 cards for Green color list
		for(int i=1; i<=48; i++)
		{
			listForGreen.add(i);
		}

		// 53 cards for Brown color list
		for(int i=1; i<=53; i++)
		{
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
	public static Pair getPlayerCard()
	{
		Collections.shuffle(greenPlayerCardsList);
		String result = greenPlayerCardsList.get(0).toString();
		greenPlayerCardsList = new ArrayList<PlayerCardDeck>(greenPlayerCardsList);
		if(greenPlayerCardsList.isEmpty())
		{
			checkWinningCondition();
		}
		else
		{
			greenPlayerCardsList.remove(0);
		}
		return new Pair(result,"Green");
	}
	
	public static void checkWinningCondition()
	{
		int result = 0;
		for(Player playerObj : GameEngine.playerObjList)
		{
			if(playerObj.pTurn == 1)
			{
				result = playerObj.checkWinningCondition(playerObj.personality);
			}
			if(result == 1)
			{
				NewGame.showErrorDialog("Congratulations "+ playerObj.color +" Win The Game!");
				return;
			}
		}		
	}
	
	public static void populateLoanCards(String loanCard)
	{
		for(Player playerObj : GameEngine.playerObjList)
		{
			if(playerObj.pTurn == 1)
			{
				GameEngine.loanCards.put(loanCard, playerObj.color);
			}
		}
	}
	
	public static void moveOtherPlayerMinion(String pcolor)
	{
		String oldRegion = "";
		String newRegion = "";
		int result;
		int oldReg, newReg;
		for (Player playerObj : GameEngine.playerObjList) {
			if (playerObj.color.equals(pcolor)) {
				newRegion = NewGame
						.displayBox("Select region in which minion should be placed:");
				newReg = Integer.parseInt(newRegion);
				result = playerObj.checkMinionMove(newReg);
				while(result == 0)
				{
					NewGame.showErrorDialog("Wrong Input!");
					newRegion = NewGame
							.displayBox("Select region in which minion should be placed:");
					newReg = Integer.parseInt(newRegion);
					result = playerObj.checkMinionMove(newReg);
				}
				//if (playerObj.minionHold < 1) {
					oldRegion = NewGame
							.displayBox("Select region from which minion should be moved:");
					oldReg = Integer.parseInt(oldRegion);
					playerObj.removeMinion(oldReg);
					GameEngine.regionObjList.get(oldReg-1).removeMinion(playerObj.color);
				//}					
				playerObj.placeMinion(newReg);
				GameEngine.regionObjList.get(newReg-1).placeMinion(playerObj.color);
				break;
			}// end if						
		}
	}
	public static int checkInterruptCard(String pColor)
	{
		int result = 0;
		List<String> greenList = new ArrayList<>();
		for (Player playerObj : GameEngine.playerObjList) {
			if (playerObj.color.equals(pColor)) {
				// create temp list
				greenList = playerObj.pCards.get("Green");
				for (int i = 0; i < greenList.size(); i++) {
					// get index of given cardName in the list	
					if (greenList.get(i).equals("GASPODE") || greenList.get(i).equals("FRESH_START_CLUB") || greenList.get(i).equals("WALLACE_SONKY")) {
						greenList.remove(i);
						NewGame.showErrorDialog(greenList.get(i)+" Interrupt Card Played by "+pColor+" !");
						result = 1;
						break;
					}
				}
				// remove all green colored cards from hashmap
				playerObj.pCards.remove("Green");
				// add new list to hashmap
				playerObj.pCards.put("Green", greenList);
				if(result == 1)
				{
					break;
				}
			}
		}
		return result;
	}	
}



