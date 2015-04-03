package Game;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.table.*;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * <h1>New Game</h1>
 * <p>
 * Creates GUI when user clicks to load a New game<br>
 * and fills in the info to be displayed in tables
 * </p>
 * 
 * @author nav_k
 *
 */
public class NewGame extends JFrame {
	// data members that are part of GUI
	public static JFrame frame;
	private static JFrame frame2;
	static JFrame board_frame ;
	private static JPanel panel1;
	public static JButton Two_Players;
	public static JButton Three_Players;
	public static JButton Four_Players;
	private static JScrollPane scrollPane1;
	static JTable Players_Info;
	private static JScrollPane scrollPane2;
	public static JTable Region_Info;
	public static JButton Play_Game;
	private static JButton Save_Game;
	private static JButton Exit;
	public static JTable Card_Info;
	public static JScrollPane scrollPane3;
	public static JTable Bank_Info;
	public static JScrollPane scrollPane4;
	public static JTextField xmlName;
	public static JLabel[] labels;
	public static int index;
	public static JPanel panel;
	static List<String> greenList = new ArrayList<>(); // list for green cards
	static List<String> brownList = new ArrayList<>(); // list for brown cards
	static List<Integer> cityAreaList = new ArrayList<>(); // list for city area  cards
	public static String[][] regionsUpdates = new String[12][5];														
	static ImageIcon playerCardImage;
	static ImageIcon personalityCardImage;
	static ImageIcon cityAreaCardImage;
	static int turnIndex;
	static int currentPlayerTurn;
	static int nextPlayerTurn;
	static int playAnotherCard;
	static int loadGame;
	private static JComboBox comboBox;
	public static JPanel panelCombo;
	public static String resultCombo;
	public static int previous;
	public static int flag;
	public static HashMap<Integer,Integer>cityAreaStatus;
	// Constructor calls method to initialize the elements of GUI
	public NewGame() {
		initComponents();
	}

	/**
	 * Function takes city area card name as parameter and returns image
	 * corresponding to the city area card
	 * 
	 * @param cardName
	 *            Integer city area card name
	 * @return image object
	 */
	public static ImageIcon getCityAreaCardImage(int cardName) {
		switch (cardName) {
		// DOLLY_SISTERS
		case 1:
			cityAreaCardImage = new ImageIcon("CityAreaCards/DollySisters.png");
			break;
		// UNREAL_ESTATE
		case 2:
			cityAreaCardImage = new ImageIcon("CityAreaCards/UnrealEstate.png");
			break;
		// DRAGONS_LANDING
		case 3:
			cityAreaCardImage = new ImageIcon(
					"CityAreaCards/DragonsLanding.png");
			break;
		// SMALL_GODS
		case 4:
			cityAreaCardImage = new ImageIcon("CityAreaCards/SmallGods.png");
			break;
		// THE_SCOURS
		case 5:
			cityAreaCardImage = new ImageIcon("CityAreaCards/TheScours.png");
			break;
		// THE_HIPPO
		case 6:
			cityAreaCardImage = new ImageIcon("CityAreaCards/TheHippo.png");
			break;
		// THE_SHADES
		case 7:
			cityAreaCardImage = new ImageIcon("CityAreaCards/TheShades.png");
			break;
		// DIMWELL
		case 8:
			cityAreaCardImage = new ImageIcon("CityAreaCards/Dimwell.png");
			break;
		// LONGWALL
		case 9:
			cityAreaCardImage = new ImageIcon("CityAreaCards/Longwall.png");
			break;
		// ISLE_OF_GODS
		case 10:
			cityAreaCardImage = new ImageIcon("CityAreaCards/IsleOfGods.png");
			break;
		// SEVEN_SLEEPERS
		case 11:
			cityAreaCardImage = new ImageIcon("CityAreaCards/SevenSleepers.png");
			break;
		// NAP_HILL
		case 12:
			cityAreaCardImage = new ImageIcon("CityAreaCards/NapHill.png");
			break;
		default:
			throw new IllegalArgumentException(
					"Invalid personality card name: " + cardName);
		}
		return cityAreaCardImage;
	}

	/**
	 * Function takes personality card name as parameter and returns image
	 * corresponding to the personality card
	 * 
	 * @param cardName
	 *            String personality card name
	 * @return image object
	 */
	public static ImageIcon getPersonalityCardImage(String cardName) {
		switch (cardName) {
		case "LORD_VETINARI":
			personalityCardImage = new ImageIcon(
					"PersonalityCardImages/LordVetinari.png");
			break;
		case "LORD_SELACHII":
			personalityCardImage = new ImageIcon(
					"PersonalityCardImages/LordSelachii.png");
			break;
		case "LORD_RUST":
			personalityCardImage = new ImageIcon(
					"PersonalityCardImages/LordRust.png");
			break;
		case "LORD_DE_WORDE":
			personalityCardImage = new ImageIcon(
					"PersonalityCardImages/LorddeWorde.png");
			break;
		case "DRAGON_KING_OF_ARMS":
			personalityCardImage = new ImageIcon(
					"PersonalityCardImages/DragonKingOfArms.png");
			break;
		case "CHRYSOPRASE":
			personalityCardImage = new ImageIcon(
					"PersonalityCardImages/Chrysoprase.png");
			break;
		case "COMMANDER_VIMES":
			personalityCardImage = new ImageIcon(
					"PersonalityCardImages/CommanderVimes.png");
			break;
		default:
			throw new IllegalArgumentException(
					"Invalid personality card name: " + cardName);
		}
		return personalityCardImage;
	}

	/**
	 * Function takes player card name as parameter and returns images
	 * corresponding to the player card
	 * 
	 * @param cardName
	 *            String PlayerCard Name
	 * @return ImageIcon image object
	 */
	public static ImageIcon getPlayerCardImage(String cardName) {
		switch (cardName) {
		case "MR_BOGGIS":
			playerCardImage = new ImageIcon("PlayerCardImages/MrBoggis.png");
			break;
		case "MR_BENT":
			playerCardImage = new ImageIcon("PlayerCardImages/MrBent.png");
			break;
		case "THE_BEGGARS_GUILD":
			playerCardImage = new ImageIcon(
					"PlayerCardImages/TheBeggarsGuild.png");
			break;
		case "THE_BANK_OF_ANKH_MORPORK":
			playerCardImage = new ImageIcon(
					"PlayerCardImages/TheBankOfAnkhMorpork.png");
			break;

		case "THE_ANKH_MORPORK_SUNSHINE_DRAGON_SANCTUARY":
			playerCardImage = new ImageIcon(
					"PlayerCardImages/TheAnkhMorporkSunshineDragonSanctuary.png");
			break;

		case "SERGEANT_ANGUA":
			playerCardImage = new ImageIcon(
					"PlayerCardImages/SergeantAngua.png");
			break;

		case "THE_AGONY_AUNTS":
			playerCardImage = new ImageIcon(
					"PlayerCardImages/TheAgonyAunts.png");
			break;

		case "THE_DYSK":
			playerCardImage = new ImageIcon("PlayerCardImages/TheDysk.png");
			break;

		case "THE_DUCKMAN":
			playerCardImage = new ImageIcon("PlayerCardImages/TheDuckman.png");
			break;

		case "DRUMKNOTT":
			playerCardImage = new ImageIcon("PlayerCardImages/Drumknott.png");
			break;
		case "CMOT_DIBBLER":
			playerCardImage = new ImageIcon("PlayerCardImages/CMOTDibbler.png");
			break;
		case "DR_CRUCES":
			playerCardImage = new ImageIcon("PlayerCardImages/DrCruces.png");
			break;
		case "CAPTAIN_CARROT":
			playerCardImage = new ImageIcon(
					"PlayerCardImages/CaptainCarrot.png");
			break;
		case "MRS_CAKE":
			playerCardImage = new ImageIcon("PlayerCardImages/MrsCake.png");
			break;
		case "GROAT":
			playerCardImage = new ImageIcon("PlayerCardImages/Groat.png");
			break;
		case "GIMLETS_DWARF_DELICATESSEN":
			playerCardImage = new ImageIcon(
					"PlayerCardImages/GimletsDwarfDelicatessen.png");
			break;
		case "GASPODE":
			playerCardImage = new ImageIcon("PlayerCardImages/Gaspode.png");
			break;
		case "FRESH_START_CLUB":
			playerCardImage = new ImageIcon(
					"PlayerCardImages/FreshStartClub.png");
			break;
		case "FOUL_OLE_RON":
			playerCardImage = new ImageIcon("PlayerCardImages/FoulOleRon.png");
			break;
		case "THE_FOOLS_GUILD":
			playerCardImage = new ImageIcon(
					"PlayerCardImages/TheFoolsGuild.png");
			break;
		case "THE_FIRE_BRIGADE":
			playerCardImage = new ImageIcon(
					"PlayerCardImages/TheFireBrigade.png");
			break;
		case "INIGO_SKIMMER":
			playerCardImage = new ImageIcon("PlayerCardImages/InigoSkimmer.png");
			break;
		case "HISTORY_MONKS":
			playerCardImage = new ImageIcon("PlayerCardImages/HistoryMonks.png");
			break;
		case "HEX":
			playerCardImage = new ImageIcon("PlayerCardImages/Hex.png");
			break;
		case "HERE_N_NOW":
			playerCardImage = new ImageIcon("PlayerCardImages/HereNNow.png");
			break;
		case "HARRY_KING":
			playerCardImage = new ImageIcon("PlayerCardImages/HarryKing.png");
			break;
		case "HARGAS_HOUSE_OF_RIBS":
			playerCardImage = new ImageIcon(
					"PlayerCardImages/HargasHouseOfRibs.png");
			break;
		case "MR_GRYLE":
			playerCardImage = new ImageIcon("PlayerCardImages/MrGryle.png");
			break;
		case "THE_PEELED_NUTS":
			playerCardImage = new ImageIcon(
					"PlayerCardImages/ThePeeledNuts.png");
			break;
		case "THE_OPERA_HOUSE":
			playerCardImage = new ImageIcon(
					"PlayerCardImages/TheOperaHouse.png");
			break;
		case "NOBBY_NOBBS":
			playerCardImage = new ImageIcon("PlayerCardImages/NobbyNobbs.png");
			break;
		case "MODO":
			playerCardImage = new ImageIcon("PlayerCardImages/Modo.png");
			break;
		case "THE_MENDED_DRUM":
			playerCardImage = new ImageIcon(
					"PlayerCardImages/TheMendedDrum.png");
			break;
		case "LIBRARIAN":
			playerCardImage = new ImageIcon("PlayerCardImages/Librarian.png");
			break;
		case "LEONARD_OF_QUIRM":
			playerCardImage = new ImageIcon(
					"PlayerCardImages/LeonardOfQuirm.png");
			break;
		case "SHONKY_SHOP":
			playerCardImage = new ImageIcon("PlayerCardImages/ShonkyShop.png");
			break;
		case "SACHARISSA_CRIPSLOCK":
			playerCardImage = new ImageIcon(
					"PlayerCardImages/SacharissaCripslock.png");
			break;
		case "ROSIE_PALM":
			playerCardImage = new ImageIcon("PlayerCardImages/RosiePalm.png");
			break;
		case "RINCEWIND":
			playerCardImage = new ImageIcon("PlayerCardImages/RinceWind.png");
			break;
		case "THE_ROYAL_MINT":
			playerCardImage = new ImageIcon("PlayerCardImages/TheRoyalMint.png");
			break;
		case "QUEEN_MOLLY":
			playerCardImage = new ImageIcon("PlayerCardImages/QueenMolly.png");
			break;
		case "PINK_PUSSYCAT_CLUB":
			playerCardImage = new ImageIcon(
					"PlayerCardImages/PinkPussyCatClub.png");
			break;
		case "ZORGO_THE_RETRO_PHRENOLOGIST":
			playerCardImage = new ImageIcon(
					"PlayerCardImages/ZorgoTheRetroPhrenologist.png");
			break;
		case "DR_WHITEFACE":
			playerCardImage = new ImageIcon("PlayerCardImages/DrWhiteface.png");
			break;
		case "WALLACE_SONKY":
			playerCardImage = new ImageIcon("PlayerCardImages/WallaceSonky.png");
			break;
		case "THE_SEAMSTRESSES_GUILD":
			playerCardImage = new ImageIcon(
					"PlayerCardImages/TheSeamstressesGuild.png");
			break;
		case "MR_PIN_MR_TULIP":
			playerCardImage = new ImageIcon("PlayerCardImages/MrPinMrTulip.png");
			break;
		case "THE_THIEVES_GUILD":
			playerCardImage = new ImageIcon(
					"PlayerCardImages/TheThievesGuild.png");
			break;
		default:
			throw new IllegalArgumentException("Invalid player card name:"
					+ cardName);

		}
		return playerCardImage;
	}

	// places minions in default areas to initiate game
	public static void setDefaultRegionStatus(String color) {
		GameEngine.objTScours.placeDefaultMinion(color);
		GameEngine.objTShades.placeDefaultMinion(color);
		GameEngine.objDSisters.placeDefaultMinion(color);
	}

	// populates values of objects into tables for GUI
	public static void reLaunchDialog() {		
		
		if (playAnotherCard == 0 && loadGame == 0) {	
			currentPlayerTurn = nextPlayerTurn;
		}
		if (playAnotherCard == 0) {						
			System.out.println("turn after:   "+currentPlayerTurn);
			previous = 100;
			cityAreaStatus = new HashMap<Integer, Integer>();
			int tempPlayerTurn = currentPlayerTurn
					% (GameUtility.playerObjList.size());
			if (tempPlayerTurn == 0) {
				tempPlayerTurn = GameUtility.playerObjList.size();
			}
			
			for(Player playerObj: GameUtility.playerObjList)
			{
				if(playerObj.pNumber == tempPlayerTurn)
				{
					for (int key : playerObj.H_Region.keySet()) {
						if (playerObj.H_Region.get(key).placedbuilding >= 1
								&& GameUtility.regionObjList.get(key - 1).stopBenefit == 0) {
							cityAreaStatus.put(key, 1);			
						}  // inner if
					}  // inner for
				} // outer if
			}// outer for		
		}
		loadGame = 0;
		currentPlayerTurn = currentPlayerTurn
				% (GameUtility.playerObjList.size());
		if (currentPlayerTurn == 0) {
			currentPlayerTurn = GameUtility.playerObjList.size();
		}
		setPlayerInfo();
		setRegionInfo();
		setCardInfo();
		setBoardInfo();
		nextPlayerTurn = currentPlayerTurn + 1;
	}

	public static void setBoardInfo() {
		Bank_Info.setValueAt(GameEngine.BankHold, 0, 0);
		Bank_Info.setValueAt(GameEngine.DemonsHold, 0, 1);
		Bank_Info.setValueAt(GameEngine.TrollsHold, 0, 2);
		Bank_Info.setValueAt(GameEngine.TMarkerHold, 0, 3);
	}

	public static void setPlayerInfo() {
		int count = 0;
		turnIndex = currentPlayerTurn;
		while (count < GameUtility.playerObjList.size()) {
			for (int i = 0; i < GameUtility.playerObjList.size(); i++) {
				if (turnIndex == GameUtility.playerObjList.get(i).pNumber) {
					Players_Info.setValueAt(
							GameUtility.playerObjList.get(i).pNumber, count, 0);
					Players_Info.setValueAt(
							GameUtility.playerObjList.get(i).color, count, 1);
					Players_Info.setValueAt(
							GameUtility.playerObjList.get(i).personality,
							count, 2);
					Players_Info.setValueAt(
							GameUtility.playerObjList.get(i).minionHold, count,
							3);
					Players_Info.setValueAt(
							GameUtility.playerObjList.get(i).buildingHold,
							count, 4);
					Players_Info
							.setValueAt(
									GameUtility.playerObjList.get(i).cashHold,
									count, 5);
					count++;
				}
			}
			turnIndex++;
			turnIndex = turnIndex % (GameUtility.playerObjList.size());
			if (turnIndex == 0) {
				turnIndex = GameUtility.playerObjList.size();
			}
		}
		for (Player obj : GameUtility.playerObjList) {
			if (obj.pNumber == turnIndex) {
				obj.pTurn = 1;
			} else {
				obj.pTurn = 0;
			}
		}
	}

	/**
	 * utility method used to place values in the table for region using objects
	 * of regions and shows the number of minions and buildings according to
	 * player color
	 */
	public static void setRegionInfo() {
		for (int i = 0; i <= 11; i++) {
			board_frame.dispatchEvent(new WindowEvent(board_frame,
					WindowEvent.WINDOW_CLOSING));
			board_frame = new JFrame();
			String showMinion = "";
			String showBuilding = "0";
			Set<String> keys = GameUtility.regionObjList.get(i).H_Player
					.keySet();
			// show the minion and building according to color of player
			for (String key : keys) {
				showMinion = showMinion
						+ " "
						+ GameUtility.regionObjList.get(i).H_Player.get(key).pMinionRegionwise
						+ key.charAt(0);
				int numBuilding = GameUtility.regionObjList.get(i).H_Player
						.get(key).pbuildingRegionwise;
				if (numBuilding == 1) {
					showBuilding = GameUtility.regionObjList.get(i).H_Player
							.get(key).color;
				}
			}
			// place values in table using objects in particular row and column
			Region_Info
					.setValueAt(GameUtility.regionObjList.get(i).rName, i, 0);

			Region_Info.setValueAt(GameUtility.regionObjList.get(i).rNumber, i,
					1);
			Region_Info.setValueAt(
					GameUtility.regionObjList.get(i).rBuildingCost, i, 2);

			Region_Info.setValueAt(showMinion, i, 3);
			Region_Info.setValueAt(showBuilding, i, 4);
			Region_Info.setValueAt(
					GameUtility.regionObjList.get(i).rTroubleMarker, i, 5);
			Region_Info.setValueAt(GameUtility.regionObjList.get(i).rDemon, i,
					6);
			Region_Info.setValueAt(GameUtility.regionObjList.get(i).rTroll, i,
					7);
		}
		String[][] regions = new String[12][5];
		for(int i=0; i<12; i++){
		for(int j=0; j<5; j++){

			regions[i][0]= Region_Info.getValueAt(i, 3).toString();
			regions[i][1]= Region_Info.getValueAt(i, 4).toString();
			regions[i][2]= Region_Info.getValueAt(i, 5).toString();
			regions[i][3]= Region_Info.getValueAt(i, 6).toString();
			regions[i][4]= Region_Info.getValueAt(i, 7).toString();
//			
		}
		}
				regionsUpdates = regions;
//
//		for(int n=0; n<12; n++){
//		for(int m=0; m<5; m++){
//		System.out.println(regions[n][m]); 
//		
//			}
//		System.out.println(" //////////////  Region No. :  "+n+"//////////////////////");
//		}
//		
//		System.out.println("======================================================"+"\n"+"*************************************************");
	
		AssetsManager test = new AssetsManager();
		
		test.updateBoard(test.getUpdates(regions),board_frame);
		
		
		
	}

	/**
	 * utility method used to place values in the table for cards using objects
	 * of players
	 */
	public static void setCardInfo() {
		List<String> greenListTemp = new ArrayList<>();
		int i;
		int rIndex = 0;
		int cIndex = 0;
		for (Player obj : GameUtility.playerObjList) {
			if (obj.color.equals("red")) {
				cIndex = 0;
			} else if (obj.color.equals("yellow")) {
				cIndex = 1;
			} else if (obj.color.equals("green")) {
				cIndex = 2;
			} else if (obj.color.equals("blue")) {
				cIndex = 3;
			}

			for (i = 0; i < 10; i++) {
				Card_Info.setValueAt("", i, cIndex);
			}

			rIndex = 0;
			greenListTemp = obj.pCards.get("Green");
			// System.out.println(obj.color + "   " + greenListTemp.toString());
			// brownList = obj.pCards.get("Brown");
			if (!greenListTemp.isEmpty()) {
				for (i = 0; i < greenListTemp.size(); i++) {
					Card_Info.setValueAt("G: " + greenListTemp.get(i), rIndex,
							cIndex);
					rIndex++;
				}
			}
			/*
			 * for (i = 0; i < brownList.size(); i++) {
			 * Card_Info.setValueAt("B: " + brownList.get(i), rIndex, cIndex);
			 * rIndex++; }
			 */
		}
	}

	/**
	 * called when button for two players is clicked
	 * 
	 * @param e
	 *            action listener
	 */
	private static void Two_PlayersActionPerformed(ActionEvent e) {
		// disable the buttons once game starts
		Two_Players.setEnabled(false);
		Three_Players.setEnabled(false);
		Four_Players.setEnabled(false);
		// remove last two objects from list

		PersonalityCards.PersonalityList
				.add(PersonalityCards.getPersonalityCard
						.valueOf(GameUtility.playerObjList.get(3).personality));
		PersonalityCards.PersonalityList
				.add(PersonalityCards.getPersonalityCard
						.valueOf(GameUtility.playerObjList.get(2).personality));
		GameUtility.playerObjList.remove(3);
		GameUtility.playerObjList.remove(2);
		if (e.getSource() == Two_Players) {
			for (int i = 0; i < Players_Info.getRowCount(); i++)
				for (int j = 0; j < Players_Info.getColumnCount(); j++) {
					// place value in player table
					Players_Info.setValueAt("", i, j);
				}

			// set player's minions in three default areas
			for (int i = 0; i <= 1; i++) {
				GameUtility.playerObjList.get(i).initialisePlayer();
				setDefaultRegionStatus(GameUtility.playerObjList.get(i).color);
				GameUtility.playerObjList.get(i).initialPlayerStatus();
			}
		}

		// shuffle list of objects to randomly select first player
		Collections.shuffle(GameUtility.playerObjList);

		turnIndex = GameUtility.playerObjList.get(0).pNumber;
		currentPlayerTurn = turnIndex;
		for (int i = 0; i <= 1; i++) {
			for (Player obj : GameUtility.playerObjList) {
				if (obj.pNumber == turnIndex) {

					Players_Info.setValueAt(obj.pNumber, i, 0);
					Players_Info.setValueAt(obj.color, i, 1);
					Players_Info.setValueAt(obj.personality, i, 2);
					Players_Info.setValueAt(obj.minionHold, i, 3);
					Players_Info.setValueAt(obj.buildingHold, i, 4);
					Players_Info.setValueAt(obj.cashHold, i, 5);
				}
			}
			// to make the players play game in turns sequentially
			turnIndex++;
			turnIndex = turnIndex % (GameUtility.playerObjList.size());
			if (turnIndex == 0) {
				turnIndex = 2;
			}
		}
		for (Player obj : GameUtility.playerObjList) {
			if (obj.pNumber == turnIndex) {
				obj.pTurn = 1;
			}
		}
		setRegionInfo();
		setCardInfo();
		Bank_Info.setValueAt(GameEngine.BankHold, 0, 0);
		Bank_Info.setValueAt(GameEngine.DemonsHold, 0, 1);
		Bank_Info.setValueAt(GameEngine.TrollsHold, 0, 2);
		Bank_Info.setValueAt(GameEngine.TMarkerHold, 0, 3);
		nextPlayerTurn = currentPlayerTurn + 1;
		/*
		 * PlayerTurn = PlayerTurn % (GameUtility.playerObjList.size()); if
		 * (PlayerTurn == 0) { PlayerTurn = GameUtility.playerObjList.size(); }
		 */
	}

	// ///////////// Three PLAYERS Info Fill //////////////////////////////

	private static void Three_PlayersActionPerformed(ActionEvent e) {
		// disable the buttons once game starts
		Two_Players.setEnabled(false);
		Three_Players.setEnabled(false);
		Four_Players.setEnabled(false);
		// remove last object from list
		PersonalityCards.PersonalityList
				.add(PersonalityCards.getPersonalityCard
						.valueOf(GameUtility.playerObjList.get(3).personality));
		GameUtility.playerObjList.remove(3);
		if (e.getSource() == Three_Players) {
			for (int i = 0; i < Players_Info.getRowCount(); i++)
				for (int j = 0; j < Players_Info.getColumnCount(); j++) {
					// place value in player table
					Players_Info.setValueAt("", i, j);
				}

			// set player's minions in three default areas
			for (int i = 0; i <= 2; i++) {
				GameUtility.playerObjList.get(i).initialisePlayer();
				setDefaultRegionStatus(GameUtility.playerObjList.get(i).color);
				GameUtility.playerObjList.get(i).initialPlayerStatus();
			}
		}

		// shuffle list of objects to randomly select first player
		Collections.shuffle(GameUtility.playerObjList);

		turnIndex = GameUtility.playerObjList.get(0).pNumber;
		currentPlayerTurn = turnIndex;
		for (int i = 0; i <= 2; i++) {
			for (Player obj : GameUtility.playerObjList) {
				if (obj.pNumber == turnIndex) {
					Players_Info.setValueAt(obj.pNumber, i, 0);
					Players_Info.setValueAt(obj.color, i, 1);
					Players_Info.setValueAt(obj.personality, i, 2);
					Players_Info.setValueAt(obj.minionHold, i, 3);
					Players_Info.setValueAt(obj.buildingHold, i, 4);
					Players_Info.setValueAt(obj.cashHold, i, 5);
				}
			}
			// to make the players play game in turns sequentially
			turnIndex++;
			turnIndex = turnIndex % (GameUtility.playerObjList.size());
			if (turnIndex == 0) {
				turnIndex = 3;
			}
		}
		for (Player obj : GameUtility.playerObjList) {
			if (obj.pNumber == turnIndex) {
				obj.pTurn = 1;
			}
		}
		nextPlayerTurn = currentPlayerTurn + 1;
		/*
		 * PlayerTurn = PlayerTurn % (GameUtility.playerObjList.size()); if
		 * (PlayerTurn == 0) { PlayerTurn = GameUtility.playerObjList.size(); }
		 */
		setRegionInfo();
		setCardInfo();
		Bank_Info.setValueAt(GameEngine.BankHold, 0, 0);
		Bank_Info.setValueAt(GameEngine.DemonsHold, 0, 1);
		Bank_Info.setValueAt(GameEngine.TrollsHold, 0, 2);
		Bank_Info.setValueAt(GameEngine.TMarkerHold, 0, 3);
	}

	private static void Four_PlayersActionPerformed(ActionEvent e) {
		// disable the buttons once game starts
		Two_Players.setEnabled(false);
		Three_Players.setEnabled(false);
		Four_Players.setEnabled(false);
		if (e.getSource() == Four_Players) {
			for (int i = 0; i < Players_Info.getRowCount(); i++)
				for (int j = 0; j < Players_Info.getColumnCount(); j++) {
					// place value in player table
					Players_Info.setValueAt("", i, j);
				}

			// set player's minions in three default areas
			for (int i = 0; i <= 3; i++) {
				GameUtility.playerObjList.get(i).initialisePlayer();
				setDefaultRegionStatus(GameUtility.playerObjList.get(i).color);
				GameUtility.playerObjList.get(i).initialPlayerStatus();
			}
		}

		// shuffle list of objects to randomly select first player
		Collections.shuffle(GameUtility.playerObjList);

		turnIndex = GameUtility.playerObjList.get(0).pNumber;
		currentPlayerTurn = turnIndex;
		for (int i = 0; i <= 3; i++) {
			for (Player obj : GameUtility.playerObjList) {
				if (obj.pNumber == turnIndex) {
					Players_Info.setValueAt(obj.pNumber, i, 0);
					Players_Info.setValueAt(obj.color, i, 1);
					Players_Info.setValueAt(obj.personality, i, 2);
					Players_Info.setValueAt(obj.minionHold, i, 3);
					Players_Info.setValueAt(obj.buildingHold, i, 4);
					Players_Info.setValueAt(obj.cashHold, i, 5);
				}
			}
			// to make the players play game in turns sequentially
			turnIndex++;
			turnIndex = turnIndex % (GameUtility.playerObjList.size());
			if (turnIndex == 0) {
				turnIndex = 4;
			}
		}
		// set player's turn
		for (Player obj : GameUtility.playerObjList) {
			if (obj.pNumber == turnIndex) {
				obj.pTurn = 1;
			}
		}
		nextPlayerTurn = currentPlayerTurn + 1;
		/*
		 * PlayerTurn = PlayerTurn % (GameUtility.playerObjList.size()); if
		 * (PlayerTurn == 0) { PlayerTurn = GameUtility.playerObjList.size(); }
		 */
		setRegionInfo();
		setCardInfo();
		Bank_Info.setValueAt(GameEngine.BankHold, 0, 0);
		Bank_Info.setValueAt(GameEngine.DemonsHold, 0, 1);
		Bank_Info.setValueAt(GameEngine.TrollsHold, 0, 2);
		Bank_Info.setValueAt(GameEngine.TMarkerHold, 0, 3);
	}

	private static void button1ActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private static void Save_GameActionPerformed(ActionEvent e) {
		if (Two_Players.isEnabled()) {
			JOptionPane.showMessageDialog(null,
					"Please Select Number of Players !");
		} else {
			String XMLFileName = xmlName.getText();
			String pattern = "(.*?).xml";
			Pattern pCheck = Pattern.compile(pattern);
			Matcher mCheck = pCheck.matcher(XMLFileName);
			if (mCheck.matches()) {
				GameEngine.createXML(XMLFileName);
			} else {
				xmlName.setText("Wrong Input!");
			}
		}
	}

	// shows a message on dialog box
	public static void showErrorDialog(String errMessage) {
		JOptionPane.showMessageDialog(null, errMessage);
	}

	// displays the unused personality cards that have not been distributed to
	// the players
	public static void displayUnusedPersonalityCards(String toDisplay) {
		JFrame frame = new JFrame("Unused Personality Cards");
		panel = new JPanel();
		List<PersonalityCards.getPersonalityCard> list = PersonalityCards.PersonalityList;
		// array of labels to save all the personality card images
		labels = new JLabel[list.size()];
		//String toDisplay = (list.get(0).toString());
		// get path for image of personality card
		ImageIcon playerPath1 = getPersonalityCardImage(toDisplay);
		final JLabel label = new JLabel("", playerPath1, JLabel.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		// add label to panel
		panel.add(label);
		// display frame
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
	}

	// display player card chosen from deck
	public static void displayPlayerCardFromDeck(String playerCard) {
		JFrame frame = new JFrame("Player Card chosen from deck");
		panel = new JPanel();
		// array of labels to save all the personality card images
		labels = new JLabel[1];
		// get path for image of personality card
		ImageIcon playerPath1 = getPlayerCardImage(playerCard);
		final JLabel label = new JLabel("", playerPath1, JLabel.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		// add label to panel
		panel.add(label);
		// display frame
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * The window opens up when user clicks on Play Game button. The method
	 * helps to display player cards, personality card and city area card if any
	 * according to the player's turn.
	 */
	public static void createPlayerFrame() {
		final JFrame frame = new JFrame("Player Info");
		panel = new JPanel();
		// label holds player's color according to the turn
		JLabel playerTurn = new JLabel(" ");

		String personalityCard = " ";
		ImageIcon personalityPath;
		for (Player playerObj : GameUtility.playerObjList) {
			// player cards
			if (playerObj.pTurn == 1) {
				playerTurn = new JLabel(playerObj.color, JLabel.CENTER);
				greenList = playerObj.pCards.get("Green");
				cityAreaList.clear();
				// city area cards
				if (playerObj.buildingHold < 6) {
					// get region numbers
					for (int key : playerObj.H_Region.keySet()) {
						if (playerObj.H_Region.get(key).placedbuilding >= 1
								&& GameUtility.regionObjList.get(key - 1).stopBenefit == 0) {

							cityAreaList.add(key);
						}
					}
				} // end if
			}// end outer if
		} // end for

		for (Player playerObj : GameUtility.playerObjList) {
			if (playerObj.pTurn == 1) {
				// personality card
				personalityCard = playerObj.personality;
			}
		}
		panel.add(playerTurn);
		int labelSize = greenList.size(); // size of label
		labels = new JLabel[labelSize]; // array of label that contains images
										// of player cards
		index = 0;
		setCardInfo();
		for (final String a : greenList) {
			// player Cards
			// System.out.println("Get image for card: "+a);
			ImageIcon playerPath1 = getPlayerCardImage(a);
			final JLabel label = new JLabel("", playerPath1, JLabel.CENTER);
			label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			// on click event on player card
			label.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					// call the function to be invoked to perform function
					// according to actions on specific player card
					PlayerCards.performLeftToRight(a);
					// disable the frame once the action gets performed
					frame.dispatchEvent(new WindowEvent(frame,
							WindowEvent.WINDOW_CLOSING));
					// call method to display updates region and player info
					reLaunchDialog();
				}
			});
			panel.add(label);
		}// end for

		// city area cards
		if (cityAreaList.size() >= 1) {
			for (final int z : cityAreaList) {
				for(Region regionObj : GameUtility.regionObjList)
				{
					if((regionObj.rNumber == z) && (regionObj.rDemon > 0))
					{
						cityAreaStatus.put(z, 0);
					}
				}
				PlayerCards.getPcardsExceptCurrent("");
				ImageIcon cityAreaPath = getCityAreaCardImage(z);
				final JLabel label = new JLabel("", cityAreaPath, JLabel.CENTER);
				label.setBorder(BorderFactory
						.createLineBorder(Color.LIGHT_GRAY));
				label.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						// call the function to be invoked to perform function
						// according to actions on specific player card

					if (cityAreaStatus.get(z) != 0) {
						previous = z;
						cityAreaStatus.put(z,0);
						CityAreaCards.playCityAreaCard(z);
						setPlayerInfo();
						setRegionInfo();
						setCardInfo();
						setBoardInfo();
						// remove the card from list once it has been played
						flag = 0;
						for (int y : cityAreaList) {
							if (y == z) {
								break;
							}
							flag++;
										
						}
						// System.out.println("index:   "+flag);
						// System.out.println("card:   "+cityAreaList.get(flag));
						cityAreaStatus.put(z, 0);
						frame.dispatchEvent(new WindowEvent(frame,
								WindowEvent.WINDOW_CLOSING));
						createPlayerFrame();
						} else {
							showErrorDialog("Cannot be performed");
						}

					}
				});
				panel.add(label);
			}
		}
		personalityPath = getPersonalityCardImage(personalityCard);
		JLabel playerPersonality = new JLabel("", personalityPath, JLabel.RIGHT);
		panel.add(playerPersonality);
		frame.setContentPane(panel);
		frame.pack();

		frame.setVisible(true);
		PlayerCards.checkWinningCondition();
	}

	// method asks for input from user and the parameter contains string to be
	// displayed for the user
	public static String displayBox(String input) {
		String response = JOptionPane.showInputDialog(null, input,
				"Enter input", JOptionPane.QUESTION_MESSAGE);
		return response;
	}

	public static String displayComboBox(String toDisplay, List<String> values) {
		String[] array = values.toArray(new String[values.size()]);
		String result = (String) JOptionPane.showInputDialog(frame2, toDisplay,
				"Selection", JOptionPane.QUESTION_MESSAGE, null, array,
				array[0]);
		return result;
	}

	// initialize GUI elements
	private static void initComponents() {
		// Component initialization -
		frame = new JFrame("New Game");
		board_frame = new JFrame();
		panel1 = new JPanel();
		Two_Players = new JButton();
		Three_Players = new JButton();
		Four_Players = new JButton();
		scrollPane1 = new JScrollPane();
		Players_Info = new JTable();
		scrollPane2 = new JScrollPane();
		Region_Info = new JTable();
		Card_Info = new JTable();
		Play_Game = new JButton();
		Save_Game = new JButton();
		Exit = new JButton();
		scrollPane3 = new JScrollPane();
		Bank_Info = new JTable();
		scrollPane4 = new JScrollPane();
		xmlName = new JTextField();

		// Container contentPane = frame.getContentPane();

		// ======== this ========
		// setTitle("New Game");
		// Container contentPane = getContentPane();
		// contentPane.add(panel1);
		// ======== panel1 ========
		{

			// ---- Two_Players ----
			Two_Players.setText("Start Two Players Game ");
			Two_Players.setFont(new Font("Tahoma", Font.PLAIN, 20));
			Two_Players.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					button1ActionPerformed(e);
					Two_PlayersActionPerformed(e);
				}
			});

			// ---- Three_Players ----
			Three_Players.setText("Start Three Players Game ");
			Three_Players.setFont(new Font("Tahoma", Font.PLAIN, 20));
			Three_Players.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Three_PlayersActionPerformed(e);
				}
			});

			// ---- Four_Players ----
			Four_Players.setText("Start Four Players Game ");
			Four_Players.setFont(new Font("Tahoma", Font.PLAIN, 20));
			Four_Players.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Four_PlayersActionPerformed(e);
				}
			});

			// ======== scrollPane1 ========
			{

				// ---- Players_Info ----
				Players_Info.setModel(new DefaultTableModel(new Object[][] {
						{ "", null, null, null, null, null },
						{ "", null, null, null, null, null },
						{ "", null, null, null, null, null },
						{ "", null, null, null, null, null }, }, new String[] {
						"Players Turn", "Color", "Personality",
						"Number Of Minions", "Number Of Buildings", "Money" }));
				Players_Info.setForeground(new Color(0, 102, 102));
				Players_Info.setBackground(new Color(255, 255, 204));
				Players_Info.setFont(new Font("Tahoma",
						Font.BOLD | Font.ITALIC, 11));
				scrollPane1.setViewportView(Players_Info);
			}

			// ======== scrollPane2 ========
			{

				// ---- Region_Info ----
				Region_Info.setModel(new DefaultTableModel(
						new Object[][] {
								{ "Dolly Sisters", null, null, null, null,
										null, null, null },
								{ "Unreal Estate", null, null, null, null,
										null, null, null },
								{ "Dragon's Landing", null, null, null, null,
										null, null, null },
								{ "Small Gods", null, null, null, null, null,
										null, null },
								{ "The Scours", null, null, null, null, null,
										null, null },
								{ "The Hippo", null, null, null, null, null,
										null, null },
								{ "The Shades", null, null, null, null, null,
										null, null },
								{ "Dimwell", null, null, null, null, null,
										null, null },
								{ "Longwall", null, null, null, null, null,
										null, null },
								{ "Isle of Gods", null, null, null, null, null,
										null, null },
								{ "Seven Sleepers", null, null, null, null,
										null, null, null },
								{ "Nap Hill", null, null, null, null, null,
										null, null }, }, new String[] {
								"Region", "Region Number", "Building Cost",
								"No. Of Minions", "No. Of Buildings",
								"Trouble Markers", "Demons", "Trolls" }));
				Region_Info.setBackground(new Color(255, 255, 204));
				Region_Info.setForeground(new Color(0, 102, 102));
				Region_Info.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC,
						11));
				scrollPane2.setViewportView(Region_Info);
			}

			Card_Info.setModel(new DefaultTableModel(new Object[][] {
					{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
					{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
					{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
					{ "", "", "", "" }, }, new String[] { "Player Red Cards",
					"Player Yellow Cards", "Player Green Cards",
					"Player Blue Cards" }));
			Card_Info.setForeground(new Color(0, 102, 102));
			Card_Info.setBackground(new Color(255, 255, 204));
			Card_Info.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			scrollPane3.setViewportView(Card_Info);

			Bank_Info.setModel(new DefaultTableModel(new Object[][] { { 120, 4,
					3, 12, }, }, new String[] { "Available Cash with the Bank",
					"Denoms", "Trolls", "Trouble Marker", }));
			Bank_Info.setForeground(new Color(0, 102, 102));
			Bank_Info.setBackground(new Color(255, 255, 204));
			Bank_Info.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			scrollPane4.setViewportView(Bank_Info);

			xmlName.setText("Enter XML Name To Be Created !");

			// ---- Play_Game ----
			Play_Game.setText("Play Game");
			Play_Game.setFont(new Font("Tahoma", Font.PLAIN, 18));
			Play_Game.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// contentPane.setVisible(false);
					// frame.dispatchEvent(new
					// WindowEvent(frame,WindowEvent.WINDOW_CLOSING));
					createPlayerFrame();

				}
			});
			// ---- Save_Game ----
			Save_Game.setText("Save Current Game");
			Save_Game.setFont(new Font("Tahoma", Font.PLAIN, 18));
			Save_Game.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Save_GameActionPerformed(e);
				}
			});

			// ---- Exit ----
			Exit.setText("Leave Ankh-Morpork");
			Exit.setFont(new Font("Tahoma", Font.PLAIN, 18));
			Exit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			GroupLayout panel1Layout = new GroupLayout(panel1);
			panel1.setLayout(panel1Layout);
			panel1Layout
					.setHorizontalGroup(panel1Layout
							.createParallelGroup()
							.addGroup(
									GroupLayout.Alignment.TRAILING,
									panel1Layout
											.createSequentialGroup()
											.addGap(23, 23, 23)
											.addGroup(
													panel1Layout
															.createParallelGroup(
																	GroupLayout.Alignment.TRAILING)
															.addGroup(
																	panel1Layout
																			.createSequentialGroup()
																			.addComponent(
																					Exit,
																					GroupLayout.PREFERRED_SIZE,
																					200,
																					GroupLayout.PREFERRED_SIZE)
																			.addGap(46,
																					46,
																					46)
																			.addComponent(
																					Save_Game,
																					GroupLayout.PREFERRED_SIZE,
																					200,
																					GroupLayout.PREFERRED_SIZE)
																			.addGap(46,
																					46,
																					46)
																			.addComponent(
																					xmlName,
																					GroupLayout.PREFERRED_SIZE,
																					200,
																					GroupLayout.PREFERRED_SIZE)
																			.addPreferredGap(
																					LayoutStyle.ComponentPlacement.RELATED,
																					47,
																					Short.MAX_VALUE)
																			.addComponent(
																					Play_Game,
																					GroupLayout.PREFERRED_SIZE,
																					200,
																					GroupLayout.PREFERRED_SIZE))
															.addComponent(
																	scrollPane1,
																	GroupLayout.PREFERRED_SIZE,
																	1200,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	scrollPane2,
																	GroupLayout.Alignment.LEADING)
															.addComponent(
																	scrollPane3)
															.addComponent(
																	scrollPane4)
															.addGroup(
																	GroupLayout.Alignment.LEADING,
																	panel1Layout
																			.createSequentialGroup()
																			.addComponent(
																					Two_Players)
																			.addGap(41,
																					41,
																					41)
																			.addComponent(
																					Three_Players)
																			.addPreferredGap(
																					LayoutStyle.ComponentPlacement.RELATED,
																					42,
																					Short.MAX_VALUE)
																			.addComponent(
																					Four_Players)))
											.addGap(26, 26, 26)));
			panel1Layout
					.setVerticalGroup(panel1Layout
							.createParallelGroup()
							.addGroup(
									panel1Layout
											.createSequentialGroup()
											.addContainerGap()
											.addGroup(
													panel1Layout
															.createParallelGroup(
																	GroupLayout.Alignment.BASELINE)
															.addComponent(
																	Two_Players,
																	GroupLayout.PREFERRED_SIZE,
																	37,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	Four_Players,
																	GroupLayout.PREFERRED_SIZE,
																	37,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	Three_Players,
																	GroupLayout.PREFERRED_SIZE,
																	37,
																	GroupLayout.PREFERRED_SIZE))
											.addGap(18, 18, 18)
											.addComponent(scrollPane1,
													GroupLayout.PREFERRED_SIZE,
													87,
													GroupLayout.PREFERRED_SIZE)
											.addGap(15, 15, 15)
											.addComponent(scrollPane2,
													GroupLayout.PREFERRED_SIZE,
													215,
													GroupLayout.PREFERRED_SIZE)
											.addGap(15, 15, 15)
											.addComponent(scrollPane3,
													GroupLayout.PREFERRED_SIZE,
													195,
													GroupLayout.PREFERRED_SIZE)
											.addGap(15, 15, 15)
											.addComponent(scrollPane4,
													GroupLayout.PREFERRED_SIZE,
													40,
													GroupLayout.PREFERRED_SIZE)
											.addGap(27, 27, 27)
											.addGroup(
													panel1Layout
															.createParallelGroup(
																	GroupLayout.Alignment.BASELINE)
															.addComponent(
																	Play_Game,
																	GroupLayout.PREFERRED_SIZE,
																	38,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	Exit,
																	GroupLayout.PREFERRED_SIZE,
																	38,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	xmlName,
																	GroupLayout.PREFERRED_SIZE,
																	38,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	Save_Game,
																	GroupLayout.PREFERRED_SIZE,
																	38,
																	GroupLayout.PREFERRED_SIZE))
											.addContainerGap(18,
													Short.MAX_VALUE)));
		}
		// contentPane.add(panel1);
		frame.setContentPane(panel1);
		/*
		 * GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		 * contentPane.setLayout(contentPaneLayout);
		 * contentPaneLayout.setHorizontalGroup(contentPaneLayout
		 * .createParallelGroup().addComponent(panel1, GroupLayout.DEFAULT_SIZE,
		 * GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		 * contentPaneLayout.setVerticalGroup(contentPaneLayout
		 * .createParallelGroup().addComponent(panel1, GroupLayout.DEFAULT_SIZE,
		 * GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		 */
		// frame.add(contentPane);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();

		frame.setVisible(true);

		// pack();
		// setLocationRelativeTo(getOwner());
		// - End of component initialization
	}
}
