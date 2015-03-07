package Game;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
	private static JFrame frame;
	private static JPanel panel1;
	private static JButton Two_Players;
	private static JButton Three_Players;
	private static JButton Four_Players;
	private static JScrollPane scrollPane1;
	static JTable Players_Info;
	private static JScrollPane scrollPane2;
	public static JTable Region_Info;
	private static JButton Play_Game;
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
	static ImageIcon playerCardImage;
	static ImageIcon personalityCardImage;
	static int turnIndex;
	static int PlayerTurn;
	// Constructor calls method to initialize the elements of GUI
	public NewGame() {
		initComponents();
	}

	/**
	 * Function takes personality card name as parameter and returns image
	 * corresponding to the personality card
	 * 
	 * @param String
	 *            personality card name
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
		case "NOBBY_NOBS":
			playerCardImage = new ImageIcon("PlayerCardImages/NobbyNobs.png");
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
			throw new IllegalArgumentException("Invalid player card name: "
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

	public static void reLaunchDialog() {
		initComponents();
		Two_Players.setEnabled(false);
		Three_Players.setEnabled(false);
		Four_Players.setEnabled(false);
		int count = 0;
		turnIndex  = PlayerTurn;
		while (count < GameEngine.playerObjList.size()) {
			for (int i = 0; i < GameEngine.playerObjList.size(); i++) {
				if (turnIndex == GameEngine.playerObjList.get(i).pNumber) {					
					greenList = GameEngine.playerObjList.get(i).pCards
							.get("Green");
					brownList = GameEngine.playerObjList.get(i).pCards
							.get("Brown");
					String greenCards = "";
					String brownCards = "";
					if (greenList != null)
						greenCards = greenList.toString();
					if (brownList != null)
						brownCards = brownList.toString();
					Players_Info.setValueAt(
							GameEngine.playerObjList.get(i).pNumber, count, 0);
					Players_Info.setValueAt(
							GameEngine.playerObjList.get(i).color, count, 1);
					Players_Info.setValueAt(
							GameEngine.playerObjList.get(i).personality, count, 2);
					Players_Info.setValueAt(
							GameEngine.playerObjList.get(i).minionHold, count, 3);
					Players_Info.setValueAt(
							GameEngine.playerObjList.get(i).buildingHold, count, 4);
					Players_Info.setValueAt(
							GameEngine.playerObjList.get(i).cashHold, count, 5);
					Players_Info.setValueAt("G: " + greenCards + " B: "
							+ brownCards, count, 6);
					count++;
				}
			}
			turnIndex++;
			turnIndex = turnIndex % (GameEngine.playerObjList.size());
			if (turnIndex == 0) {
				turnIndex = GameEngine.playerObjList.size();
			}
		}
		for (Player obj : GameEngine.playerObjList) {
			if (obj.pNumber == turnIndex) {
				obj.pTurn = 1;
			}
			else
			{
				obj.pTurn = 0;
			}
		}
		setRegionInfo();
		Bank_Info.setValueAt(GameEngine.BankHold, 0, 0);
		PlayerTurn++;
		PlayerTurn = PlayerTurn % (GameEngine.playerObjList.size());
		if (PlayerTurn == 0) {
			PlayerTurn = GameEngine.playerObjList.size();
		}
	}

	/**
	 * utility method used to place values in the table for region using objects
	 * of regions and shows the number of minions and buildings according to
	 * player color
	 */
	public static void setRegionInfo() {
		for (int i = 0; i <= 11; i++) {

			String showMinion = "";
			String showBuilding = "0";
			Set<String> keys = GameEngine.regionObjList.get(i).H_Player
					.keySet();
			// show the minion and building according to color of player
			for (String key : keys) {
				showMinion = showMinion
						+ " "
						+ GameEngine.regionObjList.get(i).H_Player.get(key).pMinionRegionwise
						+ key.charAt(0);
				int numBuilding = GameEngine.regionObjList.get(i).H_Player
						.get(key).pbuildingRegionwise;
				if (numBuilding == 1) {
					showBuilding = GameEngine.regionObjList.get(i).H_Player
							.get(key).color;
				}
			}
			// place values in table using objects in particular row and column
			Region_Info.setValueAt(GameEngine.regionObjList.get(i).rName, i, 0);

			Region_Info.setValueAt(GameEngine.regionObjList.get(i).rNumber, i,
					1);
			Region_Info.setValueAt(
					GameEngine.regionObjList.get(i).rBuildingCost, i, 2);

			Region_Info.setValueAt(showMinion, i, 3);
			Region_Info.setValueAt(showBuilding, i, 4);
			Region_Info.setValueAt(
					GameEngine.regionObjList.get(i).rTroubleMarker, i, 5);
			Region_Info
					.setValueAt(GameEngine.regionObjList.get(i).rDemon, i, 6);
			Region_Info
					.setValueAt(GameEngine.regionObjList.get(i).rTroll, i, 7);
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
		GameEngine.playerObjList.remove(3);
		GameEngine.playerObjList.remove(2);
		if (e.getSource() == Two_Players) {
			for (int i = 0; i < Players_Info.getRowCount(); i++)
				for (int j = 0; j < Players_Info.getColumnCount(); j++) {
					// place value in player table
					Players_Info.setValueAt("", i, j);
				}

			// set player's minions in three default areas
			for (int i = 0; i <= 1; i++) {
				GameEngine.playerObjList.get(i).initialisePlayer();
				setDefaultRegionStatus(GameEngine.playerObjList.get(i).color);
				GameEngine.playerObjList.get(i).initialPlayerStatus();
			}

		}

		// shuffle list of objects to randomly select first player
		Collections.shuffle(GameEngine.playerObjList);

		turnIndex = GameEngine.playerObjList.get(0).pNumber;
		PlayerTurn = turnIndex;
		for (int i = 0; i <= 1; i++) {
			for (Player obj : GameEngine.playerObjList) {
				if (obj.pNumber == turnIndex) {
					greenList = obj.pCards.get("Green");
					brownList = obj.pCards.get("Brown");
					String greenCards = "";
					String brownCards = "";
					if (greenList != null)
						greenCards = greenList.toString();
					if (brownList != null)
						brownCards = brownList.toString();
					Players_Info.setValueAt(obj.pNumber, i, 0);
					Players_Info.setValueAt(obj.color, i, 1);
					Players_Info.setValueAt(obj.personality, i, 2);
					Players_Info.setValueAt(obj.minionHold, i, 3);
					Players_Info.setValueAt(obj.buildingHold, i, 4);
					Players_Info.setValueAt(obj.cashHold, i, 5);
					Players_Info.setValueAt("G: " + greenCards + " B: "
							+ brownCards, i, 6);
				}
			}
			// to make the players play game in turns sequentially
			turnIndex++;
			turnIndex = turnIndex % (GameEngine.playerObjList.size());
			if (turnIndex == 0) {
				turnIndex = 2;
			}
		}
		for (Player obj : GameEngine.playerObjList) {
			if (obj.pNumber == turnIndex) {
				obj.pTurn = 1;
			}
		}
		setRegionInfo();
		Bank_Info.setValueAt(GameEngine.BankHold, 0, 0);
		PlayerTurn++;
		PlayerTurn = PlayerTurn % (GameEngine.playerObjList.size());
		if (PlayerTurn == 0) {
			PlayerTurn = GameEngine.playerObjList.size();
		}
	}

	// ///////////// Three PLAYERS Info Fill //////////////////////////////

	private static void Three_PlayersActionPerformed(ActionEvent e) {
		// disable the buttons once game starts
		Two_Players.setEnabled(false);
		Three_Players.setEnabled(false);
		Four_Players.setEnabled(false);
		// remove last object from list
		GameEngine.playerObjList.remove(3);
		if (e.getSource() == Three_Players) {
			for (int i = 0; i < Players_Info.getRowCount(); i++)
				for (int j = 0; j < Players_Info.getColumnCount(); j++) {
					// place value in player table
					Players_Info.setValueAt("", i, j);
				}

			// set player's minions in three default areas
			for (int i = 0; i <= 2; i++) {
				GameEngine.playerObjList.get(i).initialisePlayer();
				setDefaultRegionStatus(GameEngine.playerObjList.get(i).color);
				GameEngine.playerObjList.get(i).initialPlayerStatus();
			}
		}

		// shuffle list of objects to randomly select first player
		Collections.shuffle(GameEngine.playerObjList);

		turnIndex = GameEngine.playerObjList.get(0).pNumber;
		PlayerTurn = turnIndex;
		for (int i = 0; i <= 2; i++) {
			for (Player obj : GameEngine.playerObjList) {
				if (obj.pNumber == turnIndex) {
					greenList = obj.pCards.get("Green");
					brownList = obj.pCards.get("Brown");
					String greenCards = "";
					String brownCards = "";
					if (greenList != null)
						greenCards = greenList.toString();
					if (brownList != null)
						brownCards = brownList.toString();
					Players_Info.setValueAt(obj.pNumber, i, 0);
					Players_Info.setValueAt(obj.color, i, 1);
					Players_Info.setValueAt(obj.personality, i, 2);
					Players_Info.setValueAt(obj.minionHold, i, 3);
					Players_Info.setValueAt(obj.buildingHold, i, 4);
					Players_Info.setValueAt(obj.cashHold, i, 5);
					Players_Info.setValueAt("G: " + greenCards + " B: "
							+ brownCards, i, 6);
				}
			}
			// to make the players play game in turns sequentially
			turnIndex++;
			turnIndex = turnIndex % (GameEngine.playerObjList.size());
			if (turnIndex == 0) {
				turnIndex = 3;
			}
		}
		for (Player obj : GameEngine.playerObjList) {
			if (obj.pNumber == turnIndex) {
				obj.pTurn = 1;
			}
		}
		PlayerTurn++;
		PlayerTurn = PlayerTurn % (GameEngine.playerObjList.size());
		if (PlayerTurn == 0) {
			PlayerTurn = GameEngine.playerObjList.size();
		}
		setRegionInfo();
		Bank_Info.setValueAt(GameEngine.BankHold, 0, 0);
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
				GameEngine.playerObjList.get(i).initialisePlayer();
				setDefaultRegionStatus(GameEngine.playerObjList.get(i).color);
				GameEngine.playerObjList.get(i).initialPlayerStatus();
			}
		}

		// shuffle list of objects to randomly select first player
		Collections.shuffle(GameEngine.playerObjList);

		turnIndex = GameEngine.playerObjList.get(0).pNumber;
		PlayerTurn = turnIndex;
		for (int i = 0; i <= 3; i++) {
			for (Player obj : GameEngine.playerObjList) {
				if (obj.pNumber == turnIndex) {
					greenList = obj.pCards.get("Green");
					brownList = obj.pCards.get("Brown");
					String greenCards = "";
					String brownCards = "";
					if (greenList != null)
						greenCards = greenList.toString();
					if (brownList != null)
						brownCards = brownList.toString();
					Players_Info.setValueAt(obj.pNumber, i, 0);
					Players_Info.setValueAt(obj.color, i, 1);
					Players_Info.setValueAt(obj.personality, i, 2);
					Players_Info.setValueAt(obj.minionHold, i, 3);
					Players_Info.setValueAt(obj.buildingHold, i, 4);
					Players_Info.setValueAt(obj.cashHold, i, 5);
					Players_Info.setValueAt("G: " + greenCards + " B: "
							+ brownCards, i, 6);
				}
			}
			// to make the players play game in turns sequentially
			turnIndex++;
			turnIndex = turnIndex % (GameEngine.playerObjList.size());
			if (turnIndex == 0) {
				turnIndex = 4;
			}
		}
		// set player's turn
		for (Player obj : GameEngine.playerObjList) {
			if (obj.pNumber == turnIndex) {
				obj.pTurn = 1;
			}
		}
		PlayerTurn++;
		PlayerTurn = PlayerTurn % (GameEngine.playerObjList.size());
		if (PlayerTurn == 0) {
			PlayerTurn = GameEngine.playerObjList.size();
		}
		setRegionInfo();
		Bank_Info.setValueAt(GameEngine.BankHold, 0, 0);
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
	
	public static void showErrorDialog(String errMessage)
	{
		JOptionPane.showMessageDialog(null, errMessage);
	}

	public static void displayUnusedPersonalityCards() {
		JFrame frame = new JFrame("Unused Personality Cards");
		panel = new JPanel();
		List<PersonalityCards.getPersonalityCard> list = PersonalityCards.PersonalityList;
		labels = new JLabel[list.size()];
		for (PersonalityCards.getPersonalityCard a : list) {
			ImageIcon playerPath1 = getPersonalityCardImage(a.toString());
			final JLabel label = new JLabel("", playerPath1, JLabel.CENTER);
			label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			panel.add(label);
		}
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
	}

	public static void createPlayerFrame() {
		final JFrame frame = new JFrame("Player Info");
		panel = new JPanel();

		JLabel playerTurn = new JLabel(" ");

		String personalityCard = " ";
		ImageIcon personalityPath;
		for (Player playerObj : GameEngine.playerObjList) {
			if (playerObj.pTurn == 1) {
				playerTurn = new JLabel(playerObj.color, JLabel.CENTER);
				greenList = playerObj.pCards.get("Green");
			}
		}

		for (Player playerObj : GameEngine.playerObjList) {
			if (playerObj.pTurn == 1) {
				personalityCard = playerObj.personality;
			}
		}
		panel.add(playerTurn);
		int labelSize = greenList.size(); // size of label
		labels = new JLabel[labelSize]; // array of label that contains images
										// of player cards
		index = 0;
		for (final String a : greenList) {
			ImageIcon playerPath1 = getPlayerCardImage(a);
			final JLabel label = new JLabel("", playerPath1, JLabel.CENTER);
			label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			label.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					// call the function to be invoked to perform function
					// according to actions on specific player card
					PlayerCards.performLeftToRight(a);
					frame.dispatchEvent(new WindowEvent(frame,
							WindowEvent.WINDOW_CLOSING));
					reLaunchDialog();
				}
			});
			panel.add(label);

		}
		personalityPath = getPersonalityCardImage(personalityCard);
		JLabel playerPersonality = new JLabel("", personalityPath, JLabel.RIGHT);

		/*
		 * for(int j =0 ;j <labels.length; j++) { panel.add(labels[j]); }
		 */
		panel.add(playerPersonality);
		frame.setContentPane(panel);
		frame.pack();

		frame.setVisible(true);
	}

	public static String displayBox(String input) {
		String response = JOptionPane.showInputDialog(null, input,
				"Enter input", JOptionPane.QUESTION_MESSAGE);
		return response;
	}

	// initialize GUI elements
	private static void initComponents() {
		// Component initialization -
		frame = new JFrame("New Game");
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
						{ "", null, null, null, null, "", "" },
						{ "", null, null, null, null, null, null },
						{ "", null, null, null, null, null, null },
						{ "", null, null, null, null, null, null }, },
						new String[] { "Players Turn", "Color", "Personality",
								"Number Of Minions", "Number Of Buildings",
								"Money", "Card Numbers" }));
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
					{ PersonalityCards.getPersonalityNames.get(0),
							CityAreaCards.getCityCardsName.get(0),
							RandomEventCards.getRandomCardsName.get(0) },
					{ PersonalityCards.getPersonalityNames.get(1),
							CityAreaCards.getCityCardsName.get(1),
							RandomEventCards.getRandomCardsName.get(1) },
					{ PersonalityCards.getPersonalityNames.get(2),
							CityAreaCards.getCityCardsName.get(2),
							RandomEventCards.getRandomCardsName.get(2) },
					{ PersonalityCards.getPersonalityNames.get(3),
							CityAreaCards.getCityCardsName.get(3),
							RandomEventCards.getRandomCardsName.get(3) },
					{ PersonalityCards.getPersonalityNames.get(4),
							CityAreaCards.getCityCardsName.get(4),
							RandomEventCards.getRandomCardsName.get(4) },
					{ PersonalityCards.getPersonalityNames.get(5),
							CityAreaCards.getCityCardsName.get(5),
							RandomEventCards.getRandomCardsName.get(5) },
					{ PersonalityCards.getPersonalityNames.get(6),
							CityAreaCards.getCityCardsName.get(6),
							RandomEventCards.getRandomCardsName.get(6) },
					{ null, CityAreaCards.getCityCardsName.get(7),
							RandomEventCards.getRandomCardsName.get(7) },
					{ null, CityAreaCards.getCityCardsName.get(8),
							RandomEventCards.getRandomCardsName.get(8) },
					{ null, CityAreaCards.getCityCardsName.get(9),
							RandomEventCards.getRandomCardsName.get(9) },
					{ null, CityAreaCards.getCityCardsName.get(10),
							RandomEventCards.getRandomCardsName.get(10) },
					{ null, CityAreaCards.getCityCardsName.get(11),
							RandomEventCards.getRandomCardsName.get(11) }, },
					new String[] { "Personality Cards", "City Cards",
							"Random Event Cards" }));
			Card_Info.setForeground(new Color(0, 102, 102));
			Card_Info.setBackground(new Color(255, 255, 204));
			Card_Info.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			scrollPane3.setViewportView(Card_Info);

			Bank_Info.setModel(new DefaultTableModel(
					new Object[][] { { 120, }, },
					new String[] { "Available Cash with the Bank", }));
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
					frame.dispatchEvent(new WindowEvent(frame,
							WindowEvent.WINDOW_CLOSING));
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
													187,
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
